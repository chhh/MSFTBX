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

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generic serializer (marshaller/unmarshaller) class that uses JAXB.
 *
 * @see JAXB
 *
 * @author Arno Moonen <info@arnom.nl>
 * @author Dmitry Avtonomov
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class JaxbUtils
{
    /**
     * To improve the performance, we'll cache the last {@link JAXBContext} used.
     */
    protected static final class Cache {
        final Class<?> type;
        final JAXBContext context;

        public Cache(Class<?> type) throws JAXBException {
            this.type = type;
            this.context = JAXBContext.newInstance(type);
        }
    }

    /**
     * Cache. We don't want to prevent the {@link JaxbUtils.Cache} from being GC-ed,
     * hence {@link SoftReference}.
     */
    protected static volatile SoftReference<Cache> CACHE;

    /**
     * Obtains the {@link JAXBContext} from the given type, by using the cache if possible.
     * <p>
     * The original code in {@link JAXB} class claimed that {@code volatile} on the {@code WeakReference} variable
     * that they stored the cache in was enough to provide thread safety, but I don't think so as the reference itself,
     * inside the {@code WeakReference} wrapper isn't volatile.
     * <p>
 *     My improvement
     *
     */
    private synchronized static <T> JAXBContext getContext(Class<T> type) throws JAXBException {
        final SoftReference<Cache> existingCacheRef= CACHE;
        if (existingCacheRef != null) {
            Cache existingCache = existingCacheRef.get();
            if(existingCache != null && existingCache.type == type)
                return existingCache.context;
        }

        // overwrite the cache
        Cache newCache = new Cache(type);
        CACHE = new SoftReference<Cache>(newCache);

        return newCache.context;
    }

    /**
     * Creates an XMLStreamReader based on a file path.
     *
     * @param path the path to the file to be parsed
     * @param namespaceAware if {@code false} the XMLStreamReader will remove all namespaces from all XML elements
     * @return platform-specific XMLStreamReader implementation
     * @throws JAXBException if the XMLStreamReader could not be created
     */
    public static XMLStreamReader createXmlStreamReader(Path path, boolean namespaceAware) throws JAXBException {
        XMLInputFactory xif = getXmlInputFactory(namespaceAware);
        XMLStreamReader xsr = null;
        try {
            xsr = xif.createXMLStreamReader(new StreamSource(path.toFile()));
        } catch (XMLStreamException e) {
            throw new JAXBException(e);
        }
        return xsr;
    }

    /**
     * Creates an XMLStreamReader based on an input stream.
     *
     * @param is the input stream from which the data to be parsed will be taken
     * @param namespaceAware if {@code false} the XMLStreamReader will remove all namespaces from all XML elements
     * @return platform-specific XMLStreamReader implementation
     * @throws JAXBException if the XMLStreamReader could not be created
     */
    public static XMLStreamReader createXmlStreamReader(InputStream is, boolean namespaceAware) throws JAXBException {
        XMLInputFactory xif = getXmlInputFactory(namespaceAware);
        XMLStreamReader xsr = null;
        try {
            xsr = xif.createXMLStreamReader(is);
        } catch (XMLStreamException e) {
            throw new JAXBException(e);
        }
        return xsr;
    }

    /**
     * Creates an XMLStreamReader based on a Reader.
     *
     * @param reader Note that XMLStreamReader, despite the name, does not implement the Reader interface!
     * @param namespaceAware if {@code false} the XMLStreamReader will remove all namespaces from all XML elements
     * @return platform-specific XMLStreamReader implementation
     * @throws JAXBException if the XMLStreamReader could not be created
     */
    public static XMLStreamReader createXmlStreamReader(Reader reader, boolean namespaceAware) throws JAXBException {
        XMLInputFactory xif = getXmlInputFactory(namespaceAware);
        XMLStreamReader xsr = null;
        try {
            xsr = xif.createXMLStreamReader(reader);
        } catch (XMLStreamException e) {
            throw new JAXBException(e);
        }
        return xsr;
    }

    protected static XMLInputFactory getXmlInputFactory(boolean namespaceAware) throws JAXBException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        if (!namespaceAware) {
            if (!xif.isPropertySupported(XMLInputFactory.IS_NAMESPACE_AWARE))
                throw new JAXBException(
                        "The XMLInputFactory on this system does not support non-namespace aware parsing. " +
                                "Look at the source of 'umich.ms.fileio.filetypes.pepxml.PepXmlParser#parse(Path) " +
                                "method as a reference to implement something else :)");

            xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
        }
        return xif;
    }

    public static <T> T unmarshall(Class<T> clazz, XMLStreamReader xsr) throws JAXBException {
        JAXBContext jaxb = getContext(clazz);

        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        JAXBElement<T> jaxbElement = unmarshaller.unmarshal(xsr, clazz);
        return jaxbElement.getValue();
    }

    public static <T> T unmarshall(Class<T> clazz, XMLStreamReader xsr, Unmarshaller unmarshaller) throws JAXBException {
        JAXBElement<T> jaxbElement = unmarshaller.unmarshal(xsr, clazz);
        return jaxbElement.getValue();
    }

    /**
     * Convert a string to an object of a given class.
     *
     * @param cl Type of object
     * @param s Input string
     * @return Object of the given type
     * @throws JAXBException
     */
    public static <T> T unmarshal(Class<T> cl, String s) throws JAXBException
    {
        return unmarshal(cl, new StringReader(s));
    }

    /**
     * Convert the contents of a file to an object of a given class.
     *
     * @param cl Type of object
     * @param f File to be read
     * @return Object of the given type
     * @throws JAXBException
     */
    public static <T> T unmarshal(Class<T> cl, File f) throws JAXBException
    {
        return unmarshal(cl, new StreamSource(f));
    }

    /**
     * Convert the contents of a Reader to an object of a given class.
     *
     * @param cl Type of object
     * @param r Reader to be read
     * @return Object of the given type
     * @throws JAXBException
     */
    public static <T> T unmarshal(Class<T> cl, Reader r) throws JAXBException
    {
        return unmarshal(cl, new StreamSource(r));
    }

    /**
     * Convert the contents of an InputStream to an object of a given
     * class.
     *
     * @param cl Type of object
     * @param s InputStream to be read
     * @return Object of the given type
     * @throws JAXBException
     */
    public static <T> T unmarshal(Class<T> cl, InputStream s) throws JAXBException
    {
        return unmarshal(cl, new StreamSource(s));
    }

    /**
     * Convert the contents of a Source to an object of a given class.
     *
     * @param cl Type of object
     * @param s Source to be used
     * @return Object of the given type
     * @throws JAXBException
     */
    public static <T> T unmarshal(Class<T> cl, Source s) throws JAXBException
    {
        JAXBContext ctx = JAXBContext.newInstance(cl);
        Unmarshaller u = ctx.createUnmarshaller();
        return u.unmarshal(s, cl).getValue();
    }

    /**
     * Converts the contents of the string to a List with objects of
     * the given class.
     *
     * @param cl Type to be used
     * @param s Input string
     * @return List with objects of the given type
     * @throws JAXBException
     */
    public static <T> List<T> unmarshalCollection(Class<T> cl, String s) throws JAXBException
    {
        return unmarshalCollection(cl, new StringReader(s));
    }

    /**
     * Converts the contents of the Reader to a List with objects of
     * the given class.
     *
     * @param cl Type to be used
     * @param r Input
     * @return List with objects of the given type
     * @throws JAXBException
     */
    public static <T> List<T> unmarshalCollection(Class<T> cl, Reader r) throws JAXBException
    {
        return unmarshalCollection(cl, new StreamSource(r));
    }

    /**
     * Converts the contents of the InputStream to a List with objects
     * of the given class.
     *
     * @param cl Type to be used
     * @param s Input
     * @return List with objects of the given type
     * @throws JAXBException
     */
    public static <T> List<T> unmarshalCollection(Class<T> cl, InputStream s) throws JAXBException
    {
        return unmarshalCollection(cl, new StreamSource(s));
    }

    /**
     * Converts the contents of the Source to a List with objects of
     * the given class.
     *
     * @param cl Type to be used
     * @param s Input
     * @return List with objects of the given type
     * @throws JAXBException
     */
    public static <T> List<T> unmarshalCollection(Class<T> cl, Source s) throws JAXBException
    {
        JAXBContext ctx = JAXBContext.newInstance(JAXBCollection.class, cl);
        Unmarshaller u = ctx.createUnmarshaller();
        JAXBCollection<T> collection = u.unmarshal(s, JAXBCollection.class).getValue();
        return collection.getItems();
    }

    /**
     * Convert an object to a string.
     *
     * @param obj Object that needs to be serialized / marshalled.
     * @return String representation of obj
     * @throws JAXBException
     */
    public static <T> String marshal(T obj) throws JAXBException
    {
        StringWriter sw = new StringWriter();
        marshal(obj, sw);
        return sw.toString();
    }

    /**
     * Convert an object to a string and send it to a Writer.
     *
     * @param obj Object that needs to be serialized / marshalled
     * @param wr Writer used for outputting the marshalled object
     * @throws JAXBException
     */
    public static <T> void marshal(T obj, Writer wr) throws JAXBException
    {
        JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
        Marshaller m = ctx.createMarshaller();
        m.marshal(obj, wr);
    }

    /**
     * Convert an object to a string and save it to a File.
     *
     * @param obj Object that needs to be serialized / marshalled
     * @param f Save file
     * @throws JAXBException
     */
    public static <T> void marshal(T obj, File f) throws JAXBException
    {
        JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
        Marshaller m = ctx.createMarshaller();
        m.marshal(obj, f);
    }

    /**
     * Convert an object to a string and send it to an OutputStream.
     *
     * @param obj Object that needs to be serialized / marshalled
     * @param s Stream used for output
     * @throws JAXBException
     */
    public static <T> void marshal(T obj, OutputStream s) throws JAXBException
    {
        JAXBContext ctx = JAXBContext.newInstance(obj.getClass());
        Marshaller m = ctx.createMarshaller();
        m.marshal(obj, s);
    }

    /**
     * Convert a collection to a string.
     *
     * @param rootName Name of the XML root element
     * @param c Collection that needs to be marshalled
     * @return String representation of the collection
     * @throws JAXBException
     */
    public static <T> String marshal(String rootName, Collection<T> c) throws JAXBException
    {
        StringWriter sw = new StringWriter();
        marshal(rootName, c, sw);
        return sw.toString();
    }

    /**
     * Convert a collection to a string and sends it to the Writer.
     *
     * @param rootName Name of the XML root element
     * @param c Collection that needs to be marshalled
     * @param w Output
     * @throws JAXBException
     */
    public static <T> void marshal(String rootName, Collection<T> c, Writer w) throws JAXBException
    {
        // Create context with generic type
        JAXBContext ctx = JAXBContext.newInstance(findTypes(c));
        Marshaller m = ctx.createMarshaller();

        // Create wrapper collection
        JAXBElement element = createCollectionElement(rootName, c);
        m.marshal(element, w);
    }

    /**
     * Convert a collection to a string and stores it in a File.
     *
     * @param rootName Name of the XML root element
     * @param c Collection that needs to be marshalled
     * @param f Output file
     * @throws JAXBException
     */
    public static <T> void marshal(String rootName, Collection<T> c, File f) throws JAXBException
    {
        // Create context with generic type
        JAXBContext ctx = JAXBContext.newInstance(findTypes(c));
        Marshaller m = ctx.createMarshaller();

        // Create wrapper collection
        JAXBElement element = createCollectionElement(rootName, c);
        m.marshal(element, f);
    }

    /**
     * Convert a collection to a string and sends it to the
     * OutputStream.
     *
     * @param rootName Name of the XML root element
     * @param c Collection that needs to be marshalled
     * @param s Output
     * @throws JAXBException
     */
    public static <T> void marshal(String rootName, Collection<T> c, OutputStream s) throws JAXBException
    {
        // Create context with generic type
        JAXBContext ctx = JAXBContext.newInstance(findTypes(c));
        Marshaller m = ctx.createMarshaller();

        // Create wrapper collection
        JAXBElement element = createCollectionElement(rootName, c);
        m.marshal(element, s);
    }


    /**
     * Discovers all the classes in the given Collection. These need
     * to be in the JAXBContext if you want to marshal those objects.
     * Unfortunatly there's no way of getting the generic type at
     * runtime.
     *
     * @param c Collection that needs to be scanned
     * @return Classes found in the collection, including
     * JAXBCollection.
     */
    protected static <T> Class[] findTypes(Collection<T> c)
    {
        Set<Class> types = new HashSet<Class>();
        types.add(JAXBCollection.class);
        for (T o : c)
        {
            if (o != null)
            {
                types.add(o.getClass());
            }
        }
        return types.toArray(new Class[0]);
    }

    /**
     * Create a JAXBElement containing a JAXBCollection. Needed for
     * marshalling a generic collection without a seperate wrapper
     * class.
     *
     * @param rootName Name of the XML root element
     * @param c
     * @return JAXBElement containing the given Collection, wrapped in
     * a JAXBCollection.
     */
    protected static <T> JAXBElement createCollectionElement(String rootName, Collection<T> c)
    {
        JAXBCollection collection = new JAXBCollection(c);
        return new JAXBElement<JAXBCollection>(new QName(rootName), JAXBCollection.class, collection);
    }

}
