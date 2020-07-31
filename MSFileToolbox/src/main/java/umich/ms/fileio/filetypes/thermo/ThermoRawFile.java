package umich.ms.fileio.filetypes.thermo;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.nio.ByteOrder;
import java.nio.DoubleBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import com.dmtavt.batmass.io.ms.api.CheckResponse;
import com.dmtavt.batmass.io.ms.api.DataType;
import com.dmtavt.batmass.io.ms.api.ErrorStatus;
import com.dmtavt.batmass.io.ms.api.FragmentationInfo;
import com.dmtavt.batmass.io.ms.api.FrameQueryOpts;
import com.dmtavt.batmass.io.ms.api.FramesCountRequest;
import com.dmtavt.batmass.io.ms.api.FramesCountResponse;
import com.dmtavt.batmass.io.ms.api.GetFramesByIndexRequest;
import com.dmtavt.batmass.io.ms.api.GetFramesResponse;
import com.dmtavt.batmass.io.ms.api.ISourceFactoryGrpc;
import com.dmtavt.batmass.io.ms.api.ISourceFactoryGrpc.ISourceFactoryBlockingStub;
import com.dmtavt.batmass.io.ms.api.ISourceGrpc;
import com.dmtavt.batmass.io.ms.api.ISourceGrpc.ISourceBlockingStub;
import com.dmtavt.batmass.io.ms.api.ISvcInstrumentInfoGrpc;
import com.dmtavt.batmass.io.ms.api.ISvcInstrumentInfoGrpc.ISvcInstrumentInfoBlockingStub;
import com.dmtavt.batmass.io.ms.api.ISvcMsFramesIndexedGrpc;
import com.dmtavt.batmass.io.ms.api.ISvcMsFramesIndexedGrpc.ISvcMsFramesIndexedBlockingStub;
import com.dmtavt.batmass.io.ms.api.InstrumentInfoRequest;
import com.dmtavt.batmass.io.ms.api.InstrumentInfoResponse;
import com.dmtavt.batmass.io.ms.api.Ion;
import com.dmtavt.batmass.io.ms.api.IsReadyRequest;
import com.dmtavt.batmass.io.ms.api.IsReadyResponse;
import com.dmtavt.batmass.io.ms.api.IsolationRange;
import com.dmtavt.batmass.io.ms.api.ListServicesRequest;
import com.dmtavt.batmass.io.ms.api.ListServicesResponse;
import com.dmtavt.batmass.io.ms.api.MsFrame;
import com.dmtavt.batmass.io.ms.api.MsFrameData;
import com.dmtavt.batmass.io.ms.api.MsFrameMetaStandard;
import com.dmtavt.batmass.io.ms.api.OpenRequest;
import com.dmtavt.batmass.io.ms.api.OpenResponse;
import com.dmtavt.batmass.io.ms.api.Options;
import com.dmtavt.batmass.io.ms.api.Polarity.Type;
import com.dmtavt.batmass.io.ms.api.SelectSourceRequest;
import com.dmtavt.batmass.io.ms.api.SelectSourceResponse;
import com.dmtavt.batmass.io.ms.api.ServiceDescription;
import com.dmtavt.batmass.io.ms.api.TriState;
import com.dmtavt.batmass.io.thermo.IGrpcServerProcess;
import com.dmtavt.batmass.io.thermo.ThermoGrpcServerProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import umich.ms.datatypes.LCMSData;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.index.Index;
import umich.ms.datatypes.index.impl.IndexDefault;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.impl.ScanDefault;
import umich.ms.datatypes.scan.props.ActivationInfo;
import umich.ms.datatypes.scan.props.Instrument;
import umich.ms.datatypes.scan.props.Polarity;
import umich.ms.datatypes.scan.props.PrecursorInfo;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.datatypes.spectrum.impl.SpectrumDefault;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.AbstractLCMSDataSource;
import umich.ms.fileio.filetypes.thermo.ThermoRawFile.ThermoIndexElement;

/**
 * For reading Thermo RAW files you will need 'ext/thermo' folder with Thermo specific libraries
 * and binaries from Batmass-IO project. This folder is currently  distributed along with MSFragger
 * search engine. When using this library and this folder will need to be wither next to the
 * JAR being executed or somewhere on classpath. If you can't update those, you can also set
 * the system property '<b>batmass.io.libs.thermo.dir</b>' either programmatically:<br/>
 * <pre>
 *   System.setProperty("batmass.io.libs.thermo.dir", "/path/ext/thermo");
 * </pre>
 * Or via a command line setting: 'java -Dbatmass.io.libs.thermo.dir="/path/ext/thermo" -jar ...'.
 * <b>Note that the path is to the final destination, i.e. should include the 'ext/thermo' part.</b>
 */
public class ThermoRawFile extends AbstractLCMSDataSource<Index<ThermoIndexElement>> {
  private static final Logger log = LoggerFactory.getLogger(ThermoRawFile.class);
  private volatile ThermoIndex index;
  private volatile FrameQueryOpts centroidOption = FrameQueryOpts.PREFER_CENTROID;

  private static ConcurrentLinkedQueue<IGrpcServerProcess> processes = new ConcurrentLinkedQueue<>();
  static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      for (IGrpcServerProcess process : processes) {
        try {
          if (process != null) {
            Process stop = process.stop();
            stop.destroyForcibly().waitFor();
          }
        } catch (Exception e) {
          log.error("Something happened while trying to destory child processes in runtime shutdown hook", e);
        }
      }
    }));
  }

  private ThermoGrpcServerProcess server;
  private ManagedChannel channel;
  private ISourceFactoryBlockingStub sourceFactory;
  private ISvcInstrumentInfoBlockingStub serviceInstrumentInfo;
  private ISourceBlockingStub serviceSource;
  private ISvcMsFramesIndexedBlockingStub serviceFramesIndexed;

  public static void main(String[] args) throws FileParsingException {
    if (args.length == 0) {
      throw new IllegalArgumentException("Must provide one argument - path to RAW file");
    }
    Path path = Paths.get(args[0]);
    if (!Files.exists(path)) {
      throw new IllegalArgumentException("Path must exist: " + path.toString());
    }

    try (ThermoRawFile rawFile = new ThermoRawFile(path.toString())) {
      LCMSData lcmsData = new LCMSData(rawFile);
      lcmsData.load(LCMSDataSubset.STRUCTURE_ONLY);
      System.out.printf("Read %d scans without spectra\n", lcmsData.getScans().getMapNum2scan().size());
    }
    System.out.println("Done.");
  }

  public void setCentroidOption(FrameQueryOpts centroidOption) {
    this.centroidOption = centroidOption;
  }

  /**
   * This is a very basic check that the Batmass Thermo Server binary is present.
   */
  public static boolean isAvailable() {
    return ThermoGrpcServerProcess.findServerBin() != null;
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

  public ThermoRawFile(String path) {
    super(path);
    int attempts = 0;
    while (attempts < 4) {
      try {
        init();
        break;
      } catch (BatmassServerInitError e) {
        if (e.attemptRetry) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException ignored) {
            log.error("Sleep interrupted while retrying BatmassServer instantiation");
          }
          continue;
        }
        throw e;
      } finally {
        attempts += 1;
      }
    }
  }

  @Override
  public void close() {
    super.close();
    log.debug("Calling close on ThermoRawFile");

    if (channel != null) {
      log.debug("Calling channel shutdown()");
      channel.shutdownNow();
      try {
        log.debug("Awaiting channel termination for 5 sec.");
        if (!channel.awaitTermination(5, TimeUnit.SECONDS)) {
          log.warn("Could not shut down communication channel within 5 sec.");
        }
      } catch (InterruptedException e) {
        log.warn("Did not finish waiting for the channel to close.", e);
      }
    }
    if (server != null) {
      server.stop();
    }
  }

  private void init() {
    server = new ThermoGrpcServerProcess();
    processes.add(server);
    server.start();
    if (!server.isRunning()) {
      throw new BatmassServerInitError("Batmass Thermo Server did not start", true);
    }
    if (server.port() == null || server.port() <= 0) {
      throw new BatmassServerInitError("Batmass Thermo Server port error", true);
    }

    int maxRecvSizeBytes = 1 << 30; // 1GB
    channel = ManagedChannelBuilder
        .forAddress("localhost", server.port())
        .maxInboundMessageSize(maxRecvSizeBytes)
        .usePlaintext().build();
    if (channel == null || channel.isShutdown() || channel.isTerminated()) {
      throw new BatmassServerInitError("Something is wrong with the server communication channel",
          true);
    }
    sourceFactory = ISourceFactoryGrpc.newBlockingStub(channel);
    OpenRequest openRequest = OpenRequest.newBuilder().setLocation(path).build();
    log.debug("Making RPC call Check(OpenRequest)");
    CheckResponse checkResponse = sourceFactory.check(openRequest);
    if (!checkNoError(checkResponse.getError(), "Check()") || !checkResponse.getIsSupported()) {
      throw new RuntimeException("File not supported by the server");
    }
    log.debug("Making RPC call Open(OpenRequest)");
    OpenResponse openResp = sourceFactory.open(openRequest);
    if (!checkNoError(openResp.getError(), "Open()")) {
      throw new RuntimeException("Server could not open location");
    }
    final int countSources = openResp.getSourcesCount();
    log.debug("Open() succeeded. File has {} MS sources", countSources);
    if (countSources == 0) {
      log.warn("No data sources");
      throw new RuntimeException("Server exposed no data sources");
    }
    SelectSourceResponse selectSourceResponse = sourceFactory
        .selectDataSource(SelectSourceRequest.newBuilder().setSourceIdx(0).build());
    if (!checkNoError(selectSourceResponse.getError(), "SelectSource()") || !selectSourceResponse.getIsSelected()) {
      throw new RuntimeException("Data source could not be selected");
    }
    serviceSource = ISourceGrpc.newBlockingStub(channel);
    IsReadyResponse ready = serviceSource.isReady(IsReadyRequest.newBuilder().build());
    if (ready.hasError() && ready.getError().getIsError()) {
      throw new RuntimeException("Source was not ready after being selected.");
    }
    ListServicesResponse services = serviceSource
        .listServices(ListServicesRequest.newBuilder().build());
    if (services.hasError() && services.getError().getIsError()) {
      throw new RuntimeException("Could not get service list from source");
    }
    log.debug("Source provides the following services: " + services.getServicesList().stream().map(
        ServiceDescription::getServiceName).collect(Collectors.joining(", ")));

    List<String> providedServices = services.getServicesList().stream()
        .map(ServiceDescription::getServiceName).map(String::toLowerCase)
        .collect(Collectors.toList());
    final List<String> requiredServices = Arrays.asList("ISvcInstrumentInfo", "ISvcMsFramesIndexed");
    for (String requiredService : requiredServices) {
      if (!providedServices.contains(requiredService.toLowerCase()))
        throw new RuntimeException("Data source does not provide required service: " + requiredService);
    }
    serviceInstrumentInfo = ISvcInstrumentInfoGrpc.newBlockingStub(channel);
    serviceFramesIndexed = ISvcMsFramesIndexedGrpc.newBlockingStub(channel);
  }

  @Override
  public String getName() {
    return getPath();
  }

  @Override
  public void releaseMemory() {

  }

  @Override
  public LCMSRunInfo parseRunInfo() throws FileParsingException {
    LCMSRunInfo info = new LCMSRunInfo();
    ISvcInstrumentInfoBlockingStub stub = ISvcInstrumentInfoGrpc
        .newBlockingStub(channel);
    InstrumentInfoResponse r = stub.getInstrumentInfo(InstrumentInfoRequest.newBuilder().build());
    if (r.hasError() && r.getError().getIsError()) {
      throw new FileParsingException("Could not get isntrument info from Thermo GRPC service. Message: " + r.getError().getMessage());
    }
    Instrument inst = new Instrument();
    inst.setManufacturer(r.getInstrumentInfo().getMake());
    inst.setModel(r.getInstrumentInfo().getModel());
    info.addInstrument(inst, "1");
    return info;
  }

  @Override
  public ThermoIndex getIndex() {
    return index;
  }

  @Override
  public ThermoIndex fetchIndex() throws FileParsingException {
    ThermoIndex tmp = index;
    if (index == null) {
      synchronized (this) {
        tmp = index;
        if (tmp == null) {
          tmp = parseIndex();
          index = tmp;
        }
      }
    }
    return tmp;
  }

  @Override
  public ThermoIndex parseIndex() throws FileParsingException {
    try {
      FramesCountResponse framesCount = serviceFramesIndexed
          .framesCount(FramesCountRequest.newBuilder().build());
      if (framesCount.hasError() && framesCount.getError().getIsError()) {
        throw new FileParsingException("Coudl not get frames count");
      }
      int count = framesCount.getFramesCount();
      ThermoIndex idx = new ThermoIndex();
      for (int i = 0; i < count; i++) {
        idx.add(new ThermoIndexElement(i));
      }

      return idx;
    } catch (Exception e) {
      throw new FileParsingException("Could not get ThermoIndex");
    }
  }

  private static class ArrayStore {
    public static final ArrayStore EMPTY = new ArrayStore(null, null);
    final double[] mz;
    final double[] ab;

    ArrayStore(double[] mz, double[] ab) {
      this.mz = mz;
      this.ab = ab;
    }
  }

  private static ArrayStore frameToArrays(MsFrameData data) throws FileParsingException {
    if (data == null)
      return ArrayStore.EMPTY;
    if (data.getMassesDataType() != DataType.DOUBLE) {
      throw new FileParsingException("Can't decode masses, unsupported encoding");
    }
    if (data.getIntensitiesDataType() != DataType.DOUBLE) {
      throw new FileParsingException("Can't decode intensities, unsupported encoding");
    }

    java.nio.ByteOrder order;
    switch (data.getByteOrder()) {
      case LE:
        order = ByteOrder.LITTLE_ENDIAN;
        break;
      case BE:
        order = ByteOrder.BIG_ENDIAN;
        break;

      default:
        throw new FileParsingException("Unknown byte order");
    }
    DoubleBuffer mzBuf = data.getMasses().asReadOnlyByteBuffer().order(order).asDoubleBuffer();
    double[] mz = new double[mzBuf.remaining()];
    mzBuf.get(mz);

    DoubleBuffer abBuf = data.getIntensities().asReadOnlyByteBuffer().order(order).asDoubleBuffer();
    double[] ab = new double[abBuf.remaining()];
    abBuf.get(ab);
    return new ArrayStore(mz, ab);
  }

  @Override
  public ISpectrum parseSpectrum(int num) throws FileParsingException {
    GetFramesByIndexRequest request = GetFramesByIndexRequest.newBuilder()
        .setFrameIndexLo(num)
        .setFrameIndexHi(num + 1)
        .setIncludeMetaStandard(false)
        .setIncludeMetaExtras(false)
        .setIncludeData(true)
        .setOpts(
            Options.newBuilder().putMap(centroidOption.name(), "1").build())
        .build();
    GetFramesResponse frames = serviceFramesIndexed.getFramesByIndex(request);
    validateGetFramesResponse(frames);
    MsFrame frame = frames.getFramesList().get(0);
    ArrayStore arrayStore = frameToArrays(frame.getData());
    return new SpectrumDefault(arrayStore.mz, arrayStore.ab, null);
  }

  private void validateGetFramesResponse(GetFramesResponse r) throws FileParsingException {
    if (r.hasError() && r.getError().getIsError()) {
      throw new FileParsingException("Could not get spectra: " + r.getError().getMessage());
    }
    if (r.getFramesList().size() != 1) {
      throw new FileParsingException("Returned list of frames was of incorrect size");
    }
  }

  @Override
  public IScan parseScan(int num, boolean parseSpectrum) throws FileParsingException {
    GetFramesByIndexRequest request = GetFramesByIndexRequest.newBuilder()
        .setFrameIndexLo(num)
        .setFrameIndexHi(num + 1)
        .setIncludeMetaStandard(true)
        .setIncludeMetaExtras(false)
        .setIncludeData(parseSpectrum)
        .setOpts(
            Options.newBuilder().putMap(centroidOption.name(), "1").build())
        .build();

    GetFramesResponse frames = serviceFramesIndexed.getFramesByIndex(request);
    validateGetFramesResponse(frames);
    MsFrame frame = frames.getFramesList().get(0);

    ScanAndSpectrum sas = frameToScanAndSpectrum(num, frame);
    sas.scan.setSpectrum(sas.spectrum, true);
    return sas.scan;
  }

  private static class ScanAndSpectrum {
    final IScan scan;
    final ISpectrum spectrum;

    private ScanAndSpectrum(IScan scan, ISpectrum spectrum) {
      this.scan = scan;
      this.spectrum = spectrum;
    }
  }

  private ScanAndSpectrum frameToScanAndSpectrum(int scanNumInternal, MsFrame frame) throws FileParsingException {
    MsFrameMetaStandard meta = frame.hasMetaStandard() ? frame.getMetaStandard() : null;
    MsFrameData data = frame.hasData() ? frame.getData() : null;
    final int scanNumOneBased = scanNumInternal + 1;
    final boolean isCentroided = data != null && data.getIsProfile().equals(TriState.FALSE);
    ScanDefault scan;
    if (meta == null) {
      scan = new ScanDefault(scanNumOneBased, 0, 0, false);

    } else {
      scan = new ScanDefault(scanNumOneBased, meta.getTimeInSeconds() / 60.0d, meta.getMsLevel(),
          isCentroided);
      Type polarity = meta.getPolarity();
      switch (polarity) {
        case POSITIVE:
          scan.setPolarity(Polarity.POSITIVE);
          break;
        case NEGATIVE:
          scan.setPolarity(Polarity.NEGATIVE);
          break;

        default:
          throw new FileParsingException("Unknown polarity type");
      }

      if (meta.getMsLevel() > 1) {
        // there must be precursor info
        if (meta.getFragmentationInfoList().isEmpty()) {
          throw new FileParsingException("MSn frame's FragmentationInfoList was empty");
        }
        if (meta.getFragmentationInfoList().size() != meta.getMsLevel() - 1) {
          log.warn("A received MS Frame FragmentationInfoList size does not correlate to Frame's MS Level");
        }

        for (int i = 0; i < meta.getFragmentationInfoCount(); i++) {
          FragmentationInfo fi = meta.getFragmentationInfoList().get(i);
          List<IsolationRange> irl = fi.getIsolationRangesList();
          if (irl.size() > 1) {
            log.warn("IsolationRangesList longer than 1 element");
          }


          PrecursorInfo pi = new PrecursorInfo();
          pi.setPrecursorMsLevel(i+1);
          ActivationInfo ai = new ActivationInfo();
          pi.setActivationInfo(ai);

          IsolationRange ir = irl.get(0);
          ai.setActivationMethod(ir.getActivation(0).getActivationType());
          pi.setMzRangeStart(ir.getMzLo());
          pi.setMzRangeEnd(ir.getMzHi());
          pi.setMzTarget((ir.getMzHi() + ir.getMzLo()) / 2.0);

          if (ir.getTargetsList().isEmpty()) {
            throw new FileParsingException("Isolation range targets list was empty");
          }
          Ion ion = ir.getTargets(0);

          pi.setMzTargetMono(ion.getMz());
          pi.setCharge(ion.getCharge());
          scan.addPrecursor(pi);
        }
      }
    }

    ArrayStore as = frameToArrays(data);
    ISpectrum spec = as == ArrayStore.EMPTY ? null : new SpectrumDefault(as.mz, as.ab, null);
    return new ScanAndSpectrum(scan, spec);
  }

  /**
   * @param hi Exclusive
   */
  private List<ScanAndSpectrum> parseRange(int lo, int hi, LCMSDataSubset subset) throws FileParsingException {

    List<ScanAndSpectrum> scans = new ArrayList<>(hi - lo);

    Options frameReqOpts = Options.newBuilder()
        .putMap(centroidOption.name(), "1")
        .build();

    int batchSize = getTasksPerCpuPerBatch();
    for (int pos = lo; pos < hi; pos += batchSize) {
      int to = Math.min(pos+batchSize, hi);
      log.debug("Reading scan indexes [{}, {}]", pos, to);
      boolean structureOnly = LCMSDataSubset.isStructureOnly(subset);
      boolean isIncludeData = !structureOnly && LCMSDataSubset.isInScanNumRange(subset, pos);
      GetFramesByIndexRequest framesReq = GetFramesByIndexRequest.newBuilder()
          .setFrameIndexLo(pos)
          .setFrameIndexHi(hi)
          .setIncludeData(isIncludeData)
          .setIncludeMetaStandard(true)
          .setIncludeMetaExtras(true)
          .setOpts(frameReqOpts)
          .build();

      GetFramesResponse framesByIndex = serviceFramesIndexed.getFramesByIndex(framesReq);
      if (framesByIndex.hasError() && framesByIndex.getError().getIsError()) {
        log.error("Error getting frames: " + framesByIndex.getError().getMessage());
        throw new FileParsingException("Error getting frames: " + framesByIndex.getError().getMessage());
      }

      List<MsFrame> frames = framesByIndex.getFramesList();
      for (int i = 0; i < frames.size(); i++) {
        ScanAndSpectrum sas = frameToScanAndSpectrum(pos + i, frames.get(i));
        scans.add(sas);
      }
    }

    return scans;
  }

  @Override
  public List<IScan> parse(LCMSDataSubset subset) throws FileParsingException {
    ThermoIndex idx = fetchIndex();
    if (idx.getMapByNum().isEmpty()) {// if the index was empty - there's nothing to parse
      return Collections.emptyList();
    }
    // figure out which scans are to be read
    NavigableMap<Integer, ThermoIndexElement> mapByNum = idx.getMapByNum();
    Integer scanNumLo = subset.getScanNumLo() == null ? mapByNum.firstKey()
        : mapByNum.ceilingKey(subset.getScanNumLo());
    Integer scanNumHi =
        subset.getScanNumHi() == null ? mapByNum.lastKey() : mapByNum.floorKey(subset.getScanNumHi());
    NavigableMap<Integer, ThermoIndexElement> subIdx = mapByNum.subMap(scanNumLo, true, scanNumHi, true);
    if (subIdx.isEmpty()) {
      throw new FileParsingException(
          "The run does not contain any spectra in the number range you provided!");
    }

    List<IScan> result = new ArrayList<>();
    int batchSize = getTasksPerCpuPerBatch();
    final int hi = scanNumHi + 1;
    for (int pos = scanNumLo; pos < hi; pos += batchSize) {
      int to = Math.min(pos + batchSize, hi);
      List<ScanAndSpectrum> scanAndSpectrums = parseRange(pos, to, subset);
      for (ScanAndSpectrum sas : scanAndSpectrums) {
        sas.scan.setSpectrum(sas.spectrum, true);
        result.add(sas.scan);
      }
    }
    return result;
  }

  @Override
  public List<IScan> parse(List<Integer> scanNums) throws FileParsingException {
    if (scanNums.isEmpty())
      return Collections.emptyList();
    int lo = scanNums.get(0);
    int prev = lo;
    int count = 1;
    final int batchSize = getTasksPerCpuPerBatch();

    List<IScan> result = new ArrayList<>(scanNums.size());
    for (int i = 1; i < scanNums.size(); i++) {
      int hi = scanNums.get(i);
      count++;
      if (hi != prev + 1 || count >= batchSize) {
        List<ScanAndSpectrum> scanAndSpectrums = parseRange(lo, prev + 1, LCMSDataSubset.WHOLE_RUN);
        for (ScanAndSpectrum sas : scanAndSpectrums) {
          sas.scan.setSpectrum(sas.spectrum, true);
          result.add(sas.scan);
        }
        lo = hi;
        prev = lo;
        count = 1;
      } else if (i == scanNums.size() - 1) {
        List<ScanAndSpectrum> scanAndSpectrums = parseRange(lo, hi + 1, LCMSDataSubset.WHOLE_RUN);
        for (ScanAndSpectrum sas : scanAndSpectrums) {
          sas.scan.setSpectrum(sas.spectrum, true);
          result.add(sas.scan);
        }
      }
    }
    return result;
  }

  public static class ThermoIndexElement implements umich.ms.datatypes.index.IndexElement {
    public final int internalNumber;

    public ThermoIndexElement(int internalNumber) {
      this.internalNumber = internalNumber;
    }

    @Override
    public int getNumber() {
      return internalNumber;
    }

    @Override
    public int getRawNumber() {
      return internalNumber + 1;
    }

    @Override
    public String getId() {
      return Integer.toString(getRawNumber());
    }
  }

  public static class ThermoIndex extends IndexDefault<ThermoIndexElement> {

  }
}
