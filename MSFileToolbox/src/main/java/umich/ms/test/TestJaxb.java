package umich.ms.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import umich.ms.fileio.filetypes.pepxml.jaxb.standard.MsmsPipelineAnalysis;

public class TestJaxb {

  /**
   * This entry point may be used to test if JAXB is functioning properly after all
   * the java packaging and minification.<br/>
   * To run use `java -cp path-to.jar umich.ms.test.TestJaxb`.<br/>
   */
  public static void main(String[] args) throws JAXBException {
    // Some known implementations
    Map<String, String> map = new HashMap<>();
    map.put("org.eclipse.persistence.jaxb", "EclipseLink MOXy");
    map.put("com.sun.xml.bind.v2", "Oracle Metro (early)");
    map.put("com.sun.xml.internal.bind.v2", "Oracle Metro (late)");
    map.put("org.apache.camel.converter.jaxb", "Apache Camel");
    map.put("?", "Glassfish");// TODO: check what's returned

    Class<?> clazz = MsmsPipelineAnalysis.class;
    // http://docs.oracle.com/javaee/7/api/javax/xml/bind/JAXBContext.html
    JAXBContext jc = JAXBContext.newInstance(clazz);
    String jcClassName = jc.getClass().getName();

    System.out.println("============================");
    StringBuilder sb = new StringBuilder();

    final String javaSpec = "java.specification.version";
    final String javaRuntime = "java.runtime.version";
    System.out.println(javaSpec + " = " + System.getProperty(javaSpec));
    System.out.println(javaRuntime + " = " + System.getProperty(javaRuntime));

    sb.append("JAXB Implementation: \"").append(jcClassName).append("\"");
    Optional<Entry<String, String>> known = map.entrySet().stream()
        .filter(kv -> jcClassName.toLowerCase().startsWith(kv.getKey().toLowerCase())).findFirst();
    if (known.isPresent())
      sb.append(" [from: ").append(known.get().getValue()).append("]");
    else
      sb.append(" [not a source known to me]");
    System.out.println(sb.toString());
    System.out.println("============================");
  }
}
