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

package com.dmtavt.batmass.io.thermo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import com.dmtavt.batmass.io.ms.api.CheckResponse;
import com.dmtavt.batmass.io.ms.api.CloseRequest;
import com.dmtavt.batmass.io.ms.api.CloseResponse;
import com.dmtavt.batmass.io.ms.api.DataType;
import com.dmtavt.batmass.io.ms.api.ErrorStatus;
import com.dmtavt.batmass.io.ms.api.FrameQueryOpts;
import com.dmtavt.batmass.io.ms.api.FramesCountRequest;
import com.dmtavt.batmass.io.ms.api.FramesCountResponse;
import com.dmtavt.batmass.io.ms.api.GetFramesByIndexRequest;
import com.dmtavt.batmass.io.ms.api.GetFramesResponse;
import com.dmtavt.batmass.io.ms.api.ISourceFactoryGrpc;
import com.dmtavt.batmass.io.ms.api.ISourceFactoryGrpc.ISourceFactoryBlockingStub;
import com.dmtavt.batmass.io.ms.api.ISourceGrpc;
import com.dmtavt.batmass.io.ms.api.ISourceGrpc.ISourceBlockingStub;
import com.dmtavt.batmass.io.ms.api.ISvcMsFramesIndexedGrpc;
import com.dmtavt.batmass.io.ms.api.ISvcMsFramesIndexedGrpc.ISvcMsFramesIndexedBlockingStub;
import com.dmtavt.batmass.io.ms.api.ListServicesRequest;
import com.dmtavt.batmass.io.ms.api.ListServicesResponse;
import com.dmtavt.batmass.io.ms.api.MsFrame;
import com.dmtavt.batmass.io.ms.api.OpenRequest;
import com.dmtavt.batmass.io.ms.api.OpenResponse;
import com.dmtavt.batmass.io.ms.api.Options;
import com.dmtavt.batmass.io.ms.api.SelectSourceRequest;
import com.dmtavt.batmass.io.ms.api.SelectSourceResponse;
import com.dmtavt.batmass.io.ms.api.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThermoGrpcClient {
  private static final Logger log;

  static {
    // set log level programmatically
//    System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "info");
//    System.setProperty("org.slf4j.simpleLogger.log.batmass", "debug");
    log = LoggerFactory.getLogger(ThermoGrpcClient.class);
  }

  private static BufferedReader from(InputStream is) {
    return new BufferedReader(new InputStreamReader(new BufferedInputStream(is), StandardCharsets.UTF_8));
  }

  public static void main(String[] args) {

    IGrpcServerProcess server;
    if (args.length > 0) {
      int port = Integer.parseInt(args[0]);
      server = new ExternalRunningServer(port);
    } else {
      server = new ThermoGrpcServerProcess().start();
    }

    if (!server.isRunning() || server.port() == null) {
      log.error("Server process not running or port unknown, stopping program.");
      System.exit(1);
    }

    if (server.isRunning() && server.port() != null) {
      String usage =
          "\nChoice (press 'Enter' after text input):\n"
              + "  'q': terminate gRPC server nicely\n"
              + "  'e': exit only the java process without killing grpc server\n"
              + "  'd': System.exit(1)\n"
              + "  'f <path/to/file.RAW>': Try to open a RAW file and read some info from it\n"
              + "  'r': Try to open a RAW file C:\\data\\human-rajamaran-pxd008984\\LNZ308_CT-VT-RT_class1_#1.raw\n";

      AtomicBoolean keepGoing = new AtomicBoolean(true);

      while (keepGoing.get() && server.isRunning()) {
        System.out.println(usage);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input.substring(0, 1)) {
          case "q":
            log.info("Command 'q': Trying to terminate the grpc server process");
            try {
              if (server.isRunning()) {
                server.stop().waitFor(5, TimeUnit.SECONDS);
              }
            } catch (InterruptedException e) {
              log.error("Something happened while terminating the process", e);
            }
            break;
          case "e":
            log.info("Command 'e': Exiting java process without terminating the server");
            keepGoing.set(false);
            break;
          case "d":
            log.info("Command 'd': Running System.exit(1)");
            System.exit(1);
            break;
          case "f":
            log.info("Command 'f': User input '{}'", input);
            Pattern re = Pattern.compile("f\\s+?(.+)");
            Matcher matcher = re.matcher(input);
            log.debug("Checking if input matches pattern");
            if (!matcher.matches()) {
              log.info("Command 'f' input does not match the parsing regex '{}'", re.pattern());
              break;
            } else {
              log.debug("Input matches pattern");
            }
            String inputPath = matcher.group(1).trim();
            log.debug("Regex group 1 capture: {}", inputPath);
            try {
              log.debug("Trying: Paths.get({});", inputPath);
              Path p = Paths.get(inputPath);
              if (!Files.exists(p)) {
                log.info("Path not exist: " + p.toAbsolutePath().toString());
                break;
              } else {
                log.info("Input file path: " + p.toAbsolutePath().toString());
              }
              readSomeData(server, p);
            } catch (Exception ex) {
              log.error("Error parsing user input file", ex);
            }
            break;

          case "r":
            readSomeData(server, Paths.get("C:\\data\\human-rajamaran-pxd008984\\LNZ308_CT-VT-RT_class1_#1.raw"));
            break;

          default:
            log.warn("Unrecognized user input: '{}'", input);
            break;
        }
      }
      log.info("Broke out of While loop");
      log.info("Program exiting");
    }


  }

  private static String from(ErrorStatus es) {
    if (es == null || !es.getIsError())
      return "No errors";
    return es.getMessage();
  }

  private static boolean checkNoError(ErrorStatus es, String rpcMethod) {
    if (es == null || !es.getIsError())
      return true;
    log.error("PRC method {} returned ErrorStatus. Messages: {}", rpcMethod, from(es));
    return false;
  }

  private static void readSomeData(IGrpcServerProcess server, Path p) {
    p = p.toAbsolutePath();
    log.info("Trying to read data using gRPC from: {}", p.toString());

    int maxRecvSizeBytes = 1024 * 1024 * 1024;
    ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder
        .forAddress("localhost", server.port())
        .maxInboundMessageSize(maxRecvSizeBytes)
        .usePlaintext();
    ManagedChannel channel = channelBuilder.build();

    try {
      ISourceFactoryBlockingStub sourceFactory = ISourceFactoryGrpc.newBlockingStub(channel);

      final String location = p.toString();
      try {
        OpenRequest openReq = OpenRequest.newBuilder().setLocation(location).build();
        log.debug("Making RPC call IsSupported()");
        CheckResponse supportedResp = sourceFactory.check(openReq);
        if (!checkNoError(supportedResp.getError(), "Check()")) {
          return;
        }
        final boolean isSupported = supportedResp.getIsSupported();
        log.info("RPC call Check() returned: {}", isSupported);
        if (!isSupported)
          return;

        log.debug("Making RPC call Open()");
        OpenResponse openResp = sourceFactory.open(openReq);
        if (!checkNoError(openResp.getError(), "Open()")) {
          return;
        }
        final int countSources = openResp.getSourcesCount();
        log.info("Open() succeeded. File has {} MS sources", countSources);
        openResp.getDescription().getMessageList()
            .forEach(descMsg -> log.info("File description received: " + descMsg));
        if (countSources == 0) {
          log.warn("No services, stopping.");
          return;
        }

        final int sourceIdx = 0;
        SelectSourceResponse selectSourceResponse = sourceFactory
            .selectDataSource(SelectSourceRequest.newBuilder().setSourceIdx(sourceIdx).build());
        if (selectSourceResponse.hasError() && selectSourceResponse.getError().getIsError()) {
          log.error("Could not select source index: " + sourceIdx + ", stopping");
          return;
        }
        if (!selectSourceResponse.getIsSelected()) {
          log.warn("Source was not selected, but no errors returned, don't know what to do. Stopping.");
          return;
        }

        // ok the source is selected
        ISourceBlockingStub source = ISourceGrpc.newBlockingStub(channel);
        ListServicesResponse listServicesResponse = source
            .listServices(ListServicesRequest.newBuilder().build());
        if (listServicesResponse.hasError() && listServicesResponse.getError().getIsError()) {
          log.error("Error listing available services. Message: {}. Stopping.", listServicesResponse.getError().getMessage());
          return;
        }
        List<String> serviceNames = listServicesResponse.getServicesList().stream()
            .map(ServiceDescription::getServiceName).collect(Collectors.toList());
        log.info("The following services are available: " + String.join(", ", serviceNames));
        final String svcname = "ISvcMsFramesIndexed";
        if (!serviceNames.contains(svcname)) {
          log.warn("Service {} not available, stopping", svcname);
          return;
        }

        ISvcMsFramesIndexedBlockingStub svcFrames = ISvcMsFramesIndexedGrpc.newBlockingStub(channel);
        FramesCountRequest framesCountRequest = FramesCountRequest.newBuilder().build();
        FramesCountResponse framesCountResponse = svcFrames.framesCount(framesCountRequest);
        int framesCount = framesCountResponse.getFramesCount();
        log.info("File has {} frames.", framesCount);


        Options frameReqOpts = Options.newBuilder().putMap(FrameQueryOpts.PREFER_CENTROID.name(), "1")
            .build();

        final int batchSize = 10;
        log.info("Reading all spectra in batches of {}", batchSize);

        double totalIntensity = 0;

        long timeLo = System.currentTimeMillis();
        for (int pos = 0; pos < framesCount; pos += batchSize) {
          int hi = Math.min(pos+batchSize, framesCount);
          log.debug("Reading scan indexes [{}, {}]", pos, hi);
          GetFramesByIndexRequest framesReq = GetFramesByIndexRequest.newBuilder()
              .setFrameIndexLo(pos)
              .setFrameIndexHi(hi)
              .setIncludeData(true)
              .setIncludeMetaExtras(true)
              .setIncludeMetaExtras(true)
              .setIncludeMetaStandard(true)
              .setOpts(frameReqOpts)
              .build();

          GetFramesResponse framesByIndex = svcFrames
              .getFramesByIndex(framesReq);
          if (framesByIndex.hasError() && framesByIndex.getError().getIsError()) {
            log.error("Error getting frames: {} ", framesByIndex.getError().getMessage());
            return;
          }

          List<MsFrame> frames = framesByIndex.getFramesList();
          MsFrame msFrame = frames.get(0);
          if (msFrame.getData().getMassesDataType() != DataType.DOUBLE) {
            log.error("Can't decode masses, unsupported encoding");
            return;
          }
          if (msFrame.getData().getIntensitiesDataType() != DataType.DOUBLE) {
            log.error("Can't decode intensities, unsupported encoding");
            return;
          }

          java.nio.ByteOrder order;
          switch (msFrame.getData().getByteOrder()) {
            case LE:
              order = ByteOrder.LITTLE_ENDIAN;
              break;
            case BE:
              order = ByteOrder.BIG_ENDIAN;
              break;

            default:
              log.error("Unknown byte order in msframe data");
              return;
          }
          DoubleBuffer mzBuf = msFrame.getData().getMasses().asReadOnlyByteBuffer().order(order).asDoubleBuffer();
          double[] mz = new double[mzBuf.remaining()];
          mzBuf.get(mz);

          DoubleBuffer abBuf = msFrame.getData().getIntensities().asReadOnlyByteBuffer().order(order).asDoubleBuffer();
          double[] ab = new double[abBuf.remaining()];
          abBuf.get(ab);

          double sum = 0;
          for (int i = 0; i < ab.length; i++) {
            sum += ab[i];
          }
          totalIntensity += sum;

          int a = 1;
        }
        double elapsed = (System.currentTimeMillis() - timeLo) / 1e3d;
        double speed = framesCount / elapsed;
        log.info(String.format("Done reading scans. Took %.2fs to read %d scans. %.2f scans/sec", elapsed, framesCount, speed));
        log.debug("Total summed intensity: {}", totalIntensity);



        CloseRequest closeReq = CloseRequest.newBuilder().build();
        log.debug("Making RPC call Close()");
        CloseResponse closeResp = sourceFactory.close(closeReq);
        if (!checkNoError(closeResp.getError(), "Close()")) {
          return;
        }

      } catch (StatusRuntimeException e) {
        log.error("RPC failed", e);
      }
      log.info("Done reading data using gRPC from: {}", p.toString());
    } finally {
      channel.shutdownNow();
    }
  }
}
