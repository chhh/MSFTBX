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
package umich.ms.fileio.filetypes.util;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import umich.ms.datatypes.LCMSDataSubset;
import umich.ms.datatypes.scan.IScan;
import umich.ms.fileio.filetypes.LCMSDataSource;

/**
 * Base class for parsers used by mzXML/mzML files.
 * Created by dmitriya on 2015-02-05.
 */
public abstract class MultiSpectraParser implements Callable<List<IScan>> {
    protected final InputStream is;
    protected LCMSDataSubset subset;

    /** this will serve as a hint only, not required */
    protected Integer numScansToProcess = null;


    protected static DatatypeFactory DATA_FACTORY = null;
    static {
        try {
            // this is the name of the implementation that we want to get for DatatypeFactory, it's in JDK
            //String pkgName = "org.apache.xerces.jaxp.datatype.DatatypeFactoryImpl";
            ClassLoader orig = Thread.currentThread().getContextClassLoader();
            // setting the classloader to the classloader of THIS class' context,
            // otherwise in this class' context we won't be able to see DatatypeFactory service providers
            // this is because Xerces uses it's own context classloader, which breaks everything.
            Thread.currentThread().setContextClassLoader(MultiSpectraParser.class.getClassLoader());
            try {
                DATA_FACTORY = DatatypeFactory.newInstance();
            } finally {
                // restore the classloader to the original Context classloader of this thread
                Thread.currentThread().setContextClassLoader(orig);
            }
        } catch (DatatypeConfigurationException e) {
            Logger.getLogger(MultiSpectraParser.class.getCanonicalName()).log(Level.SEVERE,
                    "Couldn't construst DatatypeFactory.newInstance() in a static block");
        }
    }

    public MultiSpectraParser(InputStream is, LCMSDataSubset subset) {
        this.is = is;
        this.subset = subset;
    }

    public Integer getNumScansToProcess() {
        return numScansToProcess;
    }

    public void setNumScansToProcess(Integer numScansToProcess) {
        this.numScansToProcess = numScansToProcess;
    }

    public abstract <T extends LCMSDataSource<?>> T getSource();


}
