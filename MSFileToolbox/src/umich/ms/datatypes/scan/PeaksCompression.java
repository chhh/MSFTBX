/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.datatypes.scan;

/**
 * Peaks (m/z and intensity) compression methods used in mzML and mzXML files.
 * @author Dmitry Avtonomov
 */
public enum PeaksCompression {
    /** No compression. */
    NONE,
    /** Zlib compression. */
    ZLIB,
    /** MSNumPress compression - linear prediction. Only supported by mzML. */
    NUMPRESS_LINPRED,
    /** MSNumPress compression - positive integer. Only supported by mzML. */
    NUMPRESS_POSINT,
    /** MSNumPress compression - short logged float. Only supported by mzML. */
    NUMPRESS_SHLOGF
}
