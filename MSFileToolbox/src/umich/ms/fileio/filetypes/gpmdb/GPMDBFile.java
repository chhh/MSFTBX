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
package umich.ms.fileio.filetypes.gpmdb;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import umich.ms.fileio.exceptions.FileParsingException;
import umich.ms.fileio.filetypes.gpmdb.jaxb.Bioml;
import umich.ms.util.file.AbstractFile;

/**
 * Data source for GPMDB XML files with identifications. There is no corresponding data access API
 * defined for such data yet.
 *
 * @author Dmitry Avtonomov
 */
public class GPMDBFile extends AbstractFile {

  public GPMDBFile(String path) {
    super(path);
  }

  public Bioml parse() throws FileParsingException {
    //ClassLoader classLoaderOrig = Thread.currentThread().getContextClassLoader();
    try {

      //String filePath = "D:\\projects\\GPMDB\\gpm-xml-parser\\GPM32310000038_indented_cropped.xml";
      //String filePath = "D:\\projects\\GPMDB\\gpm-xml-parser\\GPM64510013519.xml";
      Path filePath = Paths.get(this.getPath());
      FileInputStream fis = new FileInputStream(filePath.toFile());
      BufferedInputStream bis = new BufferedInputStream(fis, 1024 * 1024 * 32); // 32 MB buffer

      Class<Bioml> clazz = Bioml.class;

      JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      Object unmarshalled = unmarshaller.unmarshal(bis);

      Bioml bioml = convertJAXBObjectToDomain(clazz, unmarshalled);
      return bioml;

    } catch (JAXBException | FileNotFoundException e) {
      throw new FileParsingException(e);
    } finally {
//            Thread.currentThread().setContextClassLoader(classLoaderOrig);
    }
  }

  @SuppressWarnings("unchecked")
  protected <T> T convertJAXBObjectToDomain(Class<T> clazz, Object unmarshalled) {
    if (unmarshalled == null) {
      throw new RuntimeException("Unmarshalled run header object was null");
    }

    if (!(clazz.isAssignableFrom(unmarshalled.getClass()))) {
      throw new RuntimeException(String.format(
          "When parsing XML, JAXB object's declared type was wrong. Expected: %s; Found: %s",
          clazz.getSimpleName(), unmarshalled.getClass().getSimpleName()));

    }
    return (T) unmarshalled;
  }
}
