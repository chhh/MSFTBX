/*
 * Copyright 2016 Dmitry Avtonomov.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package umich.ms.fileio.filetypes.mzml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javolution.xml.internal.stream.XMLStreamReaderImpl;
import org.apache.commons.pool2.ObjectPool;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.lcmsrun.LCMSRunInfo;
import umich.ms.datatypes.scan.IScan;
import umich.ms.datatypes.scan.StorageStrategy;
import umich.ms.datatypes.scancollection.IScanCollection;
import umich.ms.datatypes.scancollection.impl.ScanCollectionDefault;
import umich.ms.datatypes.spectrum.ISpectrum;
import umich.ms.fileio.Opts;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.xmlbased.AbstractXMLBasedDataSource;
import umich.ms.fileio.filetypes.xmlbased.IndexBuilder;

import static umich.ms.logging.LogHelper.configureJavaUtilLogging;

import umich.ms.fileio.util.FileListing;
import umich.ms.util.IntervalST;

/**
 *
 * @author Dmitry Avtonomov
 */
public class MZMLFile extends AbstractXMLBasedDataSource<MZMLIndexElement, MZMLIndex> {
    private MZMLIndex index;

    public MZMLFile(String path) {
        super(path);
    }

    public ObjectPool<XMLStreamReaderImpl> getReaderPool() {
        return readerPool;
    }

    @Override
    public MZMLIndex getIndex() {
        return index;
    }

    @Override
    public MZMLIndex fetchIndex() throws FileParsingException {
        MZMLIndex tmp = index;
        if (tmp == null) {
            synchronized (this) {
                tmp = getIndex();
                if (tmp == null) {
                    tmp = parseIndex();
                    index = tmp;
                }
            }
        }
        return tmp;
    }

    @Override
    public MZMLIndex parseIndex() throws FileParsingException {
        MZMLIndexParser parser = new MZMLIndexParser(this);
        return parser.parse();
    }

    @Override
    public MZMLRunInfo fetchRunInfo() throws FileParsingException {
        MZMLRunInfo info = (MZMLRunInfo)runInfo;
        if (runInfo == null) {
            synchronized (this) {
                info = (MZMLRunInfo)runInfo;
                if (info == null) {
                    runInfo = info = parseRunInfo();
                }
            }
        }
        return info;
    }

    @Override
    public MZMLRunInfo parseRunInfo() throws FileParsingException {
        MZMLRunHeaderParser parser = new MZMLRunHeaderParser(this);
        return parser.parse();
    }

    @Override
    protected void releaseResources() {
        index = null;
    }

    @Override
    public MZMLMultiSpectraParser getSpectraParser(InputStream inputStream,
            LCMSDataSubset subset, ObjectPool<XMLStreamReaderImpl> readerPool, Integer numSpectra) {
        MZMLMultiSpectraParser parser;
        try {
            parser = new MZMLMultiSpectraParser(inputStream, subset, this);
        } catch (FileParsingException ex) {
            throw new IllegalStateException(ex);
        }
        parser.setNumScansToProcess(numSpectra);
        parser.setReaderPool(readerPool);
        return parser;
    }

    @Override
    public IndexBuilder<MZMLIndexElement> getIndexBuilder(IndexBuilder.Info info) {
        return new MZMLIndexBuilder(info, getReaderPool());
    }
}
