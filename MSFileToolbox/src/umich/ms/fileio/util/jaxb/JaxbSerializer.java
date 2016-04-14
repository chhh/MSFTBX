/*
 * Copyright (c) 2013, Arno Moonen <info@arnom.nl>
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
 * BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
 * ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package umich.ms.fileio.util.jaxb;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * Generic serializer (marshaller/unmarshaller) class that uses JAXB.
 *
 * @author Arno Moonen <info@arnom.nl>
 */
public class JaxbSerializer
{
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
