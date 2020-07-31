/*
 * Copyright (c) 2019 Dmitry Avtonomov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package umich.ms.fileio.filetypes.bruker;

import umich.ms.datatypes.scan.props.ScanType;
import umich.ms.fileio.filetypes.bruker.dao.*;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BrukerTdfSql implements AutoCloseable {
  private static final Logger log = LoggerFactory.getLogger(BrukerTdfSql.class);

  private final Path analysisTdfPath;
  private Connection con;
  private PreparedStatement selAllPasefFrameMsMsInfoWhereFrame;
  private PreparedStatement selAllPasefFrameMsMsInfoWherePrecursor;
  private PreparedStatement selAllPrecursorsWhereId;
  private PreparedStatement selAllPrecursorsWhereParentId;
  private PreparedStatement selDiaFrameMsMsWindowsWhereGroup;
  private PreparedStatement selDiaFrameMsMsInfoWhereFrame;

  BrukerTdfSql(Path analysisTdfPath) {
    this.analysisTdfPath = analysisTdfPath;
    setupSql();
  }

  private void setupSql() {
    try {
      Class.forName("org.sqlite.JDBC"); // just in case, needed only for older sqlite jdbc drivers
    } catch (ClassNotFoundException e) {
      throw new IllegalStateException("Couldn't load org.sqlite.JDBC driver");
    }

    try {
      con = DriverManager.getConnection("jdbc:sqlite:" + analysisTdfPath.toString());
    } catch (SQLException e) {
      throw new IllegalStateException("Could not establish SQL connection to the TDF file.");
    }

    // Precursors table
    try {
      selAllPrecursorsWhereId = con.prepareStatement("select * from Precursors p where p.Id=?;");
      selAllPrecursorsWhereParentId = con.prepareStatement("select * from Precursors where Parent=?;");
    } catch (SQLException e) {
      log.debug("Precursors table is not present, queries using it won't be available");
    }

    // PasefFrameMsMsInfo table
    try {
      selAllPasefFrameMsMsInfoWhereFrame = con
              .prepareStatement("select * from PasefFrameMsMsInfo mmi where mmi.Frame=?;");
      selAllPasefFrameMsMsInfoWherePrecursor = con
              .prepareStatement("select * from PasefFrameMsMsInfo mmi where mmi.Precursor=?;");
    } catch (SQLException e) {
      log.debug("PasefFrameMsMsInfo table is not present, queries using it won't be available");
    }

    // DiaFrameMsMsWindows table
    {
      final String table = "DiaFrameMsMsWindows";

      try {
        selDiaFrameMsMsWindowsWhereGroup = con.prepareStatement("select * from " + table + " mmw where mmw.WindowGroup=?;");
      } catch (SQLException e) {
        log.debug("{}} table is not present, queries using it won't be available", table);
      }
    }

    // DiaFrameMsMsInfo table
    {
      final String table = "DiaFrameMsMsInfo";

      try {
        selDiaFrameMsMsInfoWhereFrame = con.prepareStatement("select * from " + table + " mmw where mmw.Frame=?;");
      } catch (SQLException e) {
        log.debug("{}} table is not present, queries using it won't be available", table);
      }
    }

//    //  table
//    {
//      final String table = ;
//
//      try {
//        selDiaFrameMsMsInfoWhereFrame = con.prepareStatement("select * from " + table + " mmw where mmw.WindowGroup=?;");
//      } catch (SQLException e) {
//        log.debug("{}} table is not present, queries using it won't be available", table);
//      }
//    }
  }

  @Override
  public void close() {
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) {
        log.error("Could not release sqlite connection", e);
      }
    }
  }

  static class InstrumentInfo {
    final String make;
    final String model;

    private InstrumentInfo(String make, String model) {
      this.make = make;
      this.model = model;
    }
  }

  InstrumentInfo sqlGetInstrumentInfo(Connection con) throws SQLException {
    try (Statement st = con.createStatement()) {
      st.setQueryTimeout(30);
      String make = "Bruker (default)";
      String model = "N/A";
      try (ResultSet rs = st.executeQuery("SELECT `Key`, `Value` FROM GlobalMetadata WHERE `Key`='InstrumentVendor'")) {
        if (!rs.next()) {
          log.warn("No 'InstrumentVendor' found in `GlobalMetadata` table");
        } else {
          make = rs.getString(2);
        }
      }
      try (ResultSet rs = st.executeQuery("SELECT `Key`, `Value` FROM GlobalMetadata WHERE `Key`='InstrumentName'")) {
        if (!rs.next()) {
          log.warn("No 'InstrumentName' found in `GlobalMetadata` table");
        } else {
          model = rs.getString(2);
        }
      }
      return new InstrumentInfo(make, model);
    }
  }

  int sqlGetNumScans(Connection con) throws SQLException {
    try (Statement st = con.createStatement()) {
      st.setQueryTimeout(30);
      try (ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM Frames")) {
        rs.next();
        return rs.getInt(1);
      }
    }
  }

  List<PasefFrameMsMsInfo> sqlPasefFrameMsMsInfos(FrameInfo f) throws SQLException {
    long id = f.getFrameId();
    if (f.getMsMsType() != 8) {
      throw new UnsupportedOperationException("MS/MS info for Non-PASEF MS2 scans is not implemented.");
    }
    selAllPasefFrameMsMsInfoWhereFrame.setLong(1, id);
    try (ResultSet rs = selAllPasefFrameMsMsInfoWhereFrame.executeQuery()) {
      if (!rs.next()) {
        log.warn("No precursors found in PasefFrameMsMsInfo table for Frame #{}", id);
        return Collections.emptyList();
      } else {
        List<PasefFrameMsMsInfo> list = new ArrayList<>();
        do {
          PasefFrameMsMsInfo info = new PasefFrameMsMsInfo();
          info.setFrame(f.getFrameId());
          info.setScanNumBegin(rs.getInt("ScanNumBegin"));
          info.setScanNumEnd(rs.getInt("ScanNumEnd"));
          info.setIsolationMz(rs.getDouble("IsolationMz"));
          info.setIsolationWidth(rs.getDouble("IsolationWidth"));
          info.setCollisionEnergy(rs.getDouble("CollisionEnergy"));
          info.setPrecursor(rs.getLong("Precursor"));
          list.add(info);
        } while (rs.next());
        return list;
      }
    }
  }

  List<PasefFrameMsMsInfo> sqlPasefFrameMsMsInfo(long precursorId) throws SQLException {
    selAllPasefFrameMsMsInfoWherePrecursor.setLong(1, precursorId);
    try (ResultSet rs = selAllPasefFrameMsMsInfoWherePrecursor.executeQuery()) {
      if (!rs.next()) {
        log.warn("No precursors found in PasefFrameMsMsInfo table for PrecursorId {}", precursorId);
        return Collections.emptyList();
      } else {
        List<PasefFrameMsMsInfo> list = new ArrayList<>();
        do {
          PasefFrameMsMsInfo info = new PasefFrameMsMsInfo();
          info.setFrame(rs.getLong("Frame"));
          info.setScanNumBegin(rs.getInt("ScanNumBegin"));
          info.setScanNumEnd(rs.getInt("ScanNumEnd"));
          info.setIsolationMz(rs.getDouble("IsolationMz"));
          info.setIsolationWidth(rs.getDouble("IsolationWidth"));
          info.setCollisionEnergy(rs.getDouble("CollisionEnergy"));
          info.setPrecursor(rs.getLong("Precursor"));
          list.add(info);
        } while (rs.next());
        return list;
      }
    }
  }

  List<Frame> sqlReadAllFrames() throws SQLException {
    try (PreparedStatement st = con.prepareStatement("select * from Frames f;")) {
      st.setQueryTimeout(30);
      try (ResultSet rs = st.executeQuery()) {
        List<Frame> frames = new ArrayList<>();
        if (!rs.next()) {
          throw new SQLException("No frames in Frames table");
        } else {
          do {
            frames.add(pojoFromFramesQuery(rs));
          } while (rs.next());
        }
        return frames;
      }
    }
  }

  private Frame pojoFromFramesQuery(ResultSet rs) throws SQLException {
    Frame f = new Frame();
    f.setId(rs.getLong("Id"));
    f.setTime(rs.getDouble("Time"));
    f.setNumScans(rs.getInt("NumScans"));
    f.setNumPeaks(rs.getInt("NumPeaks"));
    f.setPolarity(rs.getString("Polarity"));
    f.setSummedIntensities(rs.getLong("SummedIntensities"));
    f.setMaxIntensity(rs.getLong("MaxIntensity"));
    f.setScanMode(rs.getInt("ScanMode"));
    f.setMsMsType(rs.getInt("MsMsType"));
    f.setTimsId(rs.getLong("TimsId"));
    f.setPropertyGroup(rs.getInt("PropertyGroup"));
    return f;
  }

  Frame sqlReadFrame(long frameId) throws SQLException {
    try (PreparedStatement st = con.prepareStatement("select * from Frames f where f.Id=?;")) {
      st.setQueryTimeout(30);
      st.setLong(1, frameId);
      try (ResultSet rs = st.executeQuery()) {
        if (!rs.next()) {
          throw new SQLException(String.format("No frame with Id=%d in Frames table", frameId));
        } else {
          return pojoFromFramesQuery(rs);
        }
      }
    }
  }

  Precursor sqlReadPrecursorById(long precursorId) throws SQLException {
    selAllPrecursorsWhereId.setLong(1, precursorId);
    try (ResultSet rs = selAllPrecursorsWhereId.executeQuery()) {
      if (!rs.next()) {
        throw new SQLException(String.format("No precursor with Id=%d in Precursors table", precursorId));
      } else {
        Precursor p = new Precursor();
        p.setId(rs.getLong("Id"));
        p.setLargestPeakMz(rs.getDouble("LargestPeakMz"));
        p.setAverageMz(rs.getDouble("AverageMz"));
        p.setMonoisotopicMz(rs.getDouble("MonoisotopicMz"));
        p.setCharge(rs.getLong("Charge"));
        p.setScanNumber(rs.getDouble("ScanNumber"));
        p.setIntensity(rs.getDouble("Intensity"));
        p.setParent(rs.getLong("Parent"));
        return p;
      }
    }
  }

  List<Precursor> sqlReadPrecursorsByParent(long parentMs1FrameId) throws SQLException {
    selAllPrecursorsWhereId.setLong(1, parentMs1FrameId);
    try (ResultSet rs = selAllPrecursorsWhereParentId.executeQuery()) {
      if (!rs.next()) {
        throw new SQLException(String.format("No precursor with Parent=%d in Precursors table", parentMs1FrameId));
      }
      List<Precursor> list = new ArrayList<>();
      do {
        Precursor p = new Precursor();
        p.setId(rs.getLong("Id"));
        p.setLargestPeakMz(rs.getDouble("LargestPeakMz"));
        p.setAverageMz(rs.getDouble("AverageMz"));
        p.setMonoisotopicMz(rs.getDouble("MonoisotopicMz"));
        p.setCharge(rs.getLong("Charge"));
        p.setScanNumber(rs.getDouble("ScanNumber"));
        p.setIntensity(rs.getDouble("Intensity"));
        p.setParent(rs.getLong("Parent"));
        list.add(p);
      } while (rs.next());
      return list;
    }
  }

  DiaFrameMsMsInfo sqlReadDiaFrameMsMsInfo(long frameId) throws SQLException {
    selDiaFrameMsMsInfoWhereFrame.setLong(1, frameId);
    try (ResultSet rs = selDiaFrameMsMsInfoWhereFrame.executeQuery()) {
      if (!rs.next()) {
        throw new SQLException(String.format("No DiaFrameMsMsInfo entries for FrameId %d", frameId));
      }
      DiaFrameMsMsInfo i = new DiaFrameMsMsInfo();
      i.setFrame(frameId);
      i.setWindowGroup(rs.getLong("WindowGroup"));
      return i;
    }
  }

  List<DiaFrameMsMsWindow> sqlReadDiaFrameMsMsWindow(long windowGroupId) throws SQLException {
    selDiaFrameMsMsWindowsWhereGroup.setLong(1, windowGroupId);
    try (ResultSet rs = selDiaFrameMsMsWindowsWhereGroup.executeQuery()) {
      if (!rs.next()) {
        throw new SQLException(String.format("No DiaFrameMsMsWindow has WindowGroupId %d", windowGroupId));
      }
      List<DiaFrameMsMsWindow> res = new ArrayList<>();
      do {
        DiaFrameMsMsWindow w = new DiaFrameMsMsWindow();
        w.setCollisionEnergy(rs.getDouble("CollisionEnergy"));
        w.setIsolationMz(rs.getDouble("IsolationMz"));
        w.setIsolationWidth(rs.getDouble("IsolationWidth"));
        w.setScanNumBegin(rs.getLong("ScanNumBegin"));
        w.setScanNumEnd(rs.getLong("ScanNumEnd"));
        w.setWindowGroup(windowGroupId);
        res.add(w);
      } while (rs.next());
      return res;
    }
  }
}
