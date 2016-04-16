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
package umich.ms.fileio.util.jaxb;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 * Implementation of an XML filter that can add or remove namespaces from XML elements during parsing.
 * This is needed in some cases, for example, when the schema name contains a version number and
 * a target XML document was generated against an earlier version than your JAXB parser. In this case
 * JAXB won't recognize the elements, as every element name is preffixed with the namespace.<br/><br/>
 *
 * One solution is to remove the namespace from the {@code ObjectFactory} annotations and use an
 * {@code XMLStreamReader} that is non-namespace aware, like so:
 * <pre><code>
 *     JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
 *     XMLInputFactory xif = XMLInputFactory.newFactory();
 *     xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
 *     StreamSource source = new StreamSource(f);
 *     XMLStreamReader xsr = xif.createXMLStreamReader(source);
 *     Unmarshaller unmarshaller = jc.createUnmarshaller();
 *     Object unmarshal = unmarshaller.unmarshal(xsr);
 * </code></pre>
 * <br/>
 * Using this filter:<br/>
 * To add namespaces where missing
 * <pre><code>
 *      new NamespaceFilter("http://www.example.com/namespaceurl", true);
 * </code></pre>
 * <br/>
 * To remove namespaces
 * <pre><code>
 *      new NamespaceFilter(null, false);
 * </code></pre>
 * <br/>
 *
 * <br/>
 *
 * Created by Dmitry Avtonomov on 2016-04-13.
 *
 * Taken from: http://stackoverflow.com/questions/277502/jaxb-how-to-ignore-namespace-during-unmarshalling-xml-document/2148541#2148541
 *
 * @see org.xml.sax.XMLFilter
 * @author Kristofer (http://stackoverflow.com/users/259485/kristofer)
 */
public class NamespaceFilter extends XMLFilterImpl {

    private String usedNamespaceUri;
    private boolean addNamespace;

    //State variable
    private boolean addedNamespace = false;

    public NamespaceFilter(String namespaceUri,
                           boolean addNamespace) {
        super();

        if (addNamespace)
            this.usedNamespaceUri = namespaceUri;
        else
            this.usedNamespaceUri = "";
        this.addNamespace = addNamespace;
    }


    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        if (addNamespace) {
            startControlledPrefixMapping();
        }
    }


    @Override
    public void startElement(String arg0, String arg1, String arg2,
                             Attributes arg3) throws SAXException {

        super.startElement(this.usedNamespaceUri, arg1, arg2, arg3);
    }

    @Override
    public void endElement(String arg0, String arg1, String arg2)
            throws SAXException {

        super.endElement(this.usedNamespaceUri, arg1, arg2);
    }

    @Override
    public void startPrefixMapping(String prefix, String url)
            throws SAXException {


        if (addNamespace) {
            this.startControlledPrefixMapping();
        } else {
            //Remove the namespace, i.e. don´t call startPrefixMapping for parent!
        }

    }

    private void startControlledPrefixMapping() throws SAXException {

        if (this.addNamespace && !this.addedNamespace) {
            //We should add namespace since it is set and has not yet been done.
            super.startPrefixMapping("", this.usedNamespaceUri);

            //Make sure we dont do it twice
            this.addedNamespace = true;
        }
    }

}
