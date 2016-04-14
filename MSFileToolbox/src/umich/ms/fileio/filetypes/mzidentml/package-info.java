/**
 * The difference between {@code jaxb.primitive} and {@code jaxb.standard} sub-packages is in interpretation of
 * XML types as Java types. The {@code jaxb.standard} code uses java wrapper classes for  values which are not
 * marked as `required` in the xml schema. This way if, for example, an attribute was not present in an actual xml
 * file (and that's fine if it wasn't `required`) then the corresponding filed in the java POJO will be null.
 * This might lead to higher memory consumption overall, so to help with that there is the {@code jaxb.standard}
 * package which tries to use java primitive types instead of wrapper classes wherever possible.
 *
 * Created by Dmitry Avtonomov on 2016-04-12.
 */
package umich.ms.fileio.filetypes.mzidentml;
