package umich.ms.fileio.util;

import umich.ms.external.ucar.KMPMatch;
import umich.ms.external.ucar.RandomAccessFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.nio.channels.WritableByteChannel;
import java.util.List;

/**
 * A wrapper around {@link umich.ms.external.ucar.RandomAccessFile}, implementing input stream.
 * Created by dmitriya on 2015-02-27.
 */
public class RandomInputStream extends InputStream {
    RandomAccessFile braf;

    public RandomInputStream(RandomAccessFile braf) {
        this.braf = braf;
    }

    @Override
    public int read() throws IOException {
        return braf.read();
    }

    public static boolean getDebugLeaks() {
        return RandomAccessFile.getDebugLeaks();
    }

    public int readUnsignedByte() throws IOException {
        return braf.readUnsignedByte();
    }

    public static List<String> getOpenFiles() {
        return RandomAccessFile.getOpenFiles();
    }

    public byte[] readBytes(int count) throws IOException {
        return braf.readBytes(count);
    }

    public java.io.RandomAccessFile getRandomAccessFile() {
        return braf.getRandomAccessFile();
    }

    public char readChar() throws IOException {
        return braf.readChar();
    }

    public double readDouble() throws IOException {
        return braf.readDouble();
    }

    public void writeByte(int v) throws IOException {
        braf.writeByte(v);
    }

    public long skipBytes(long n) throws IOException {
        return braf.skipBytes(n);
    }

    public void flush() throws IOException {
        braf.flush();
    }

    public void writeBytes(char[] b, int off, int len) throws IOException {
        braf.writeBytes(b, off, len);
    }

    public void readFully(byte[] b, int off, int len) throws IOException {
        braf.readFully(b, off, len);
    }

    public String readLine() throws IOException {
        return braf.readLine();
    }

    public static long getOpenFileCount() {
        return RandomAccessFile.getOpenFileCount();
    }

    public void writeLong(long v) throws IOException {
        braf.writeLong(v);
    }

    public boolean isAtEndOfFile() {
        return braf.isAtEndOfFile();
    }

    public void setBufferSize(int bufferSize) {
        braf.setBufferSize(bufferSize);
    }

    public int skipBytes(int n) throws IOException {
        return braf.skipBytes(n);
    }

    public boolean searchForward(KMPMatch match, int maxBytes) throws IOException {
        return braf.searchForward(match, maxBytes);
    }

    public static int getMaxOpenFileCount() {
        return RandomAccessFile.getMaxOpenFileCount();
    }

    public short readShort() throws IOException {
        return braf.readShort();
    }

    public void writeShort(int v) throws IOException {
        braf.writeShort(v);
    }

    public static void setDebugAccess(boolean b) {
        RandomAccessFile.setDebugAccess(b);
    }

    public void readShort(short[] pa, int start, int n) throws IOException {
        braf.readShort(pa, start, n);
    }

    public void order(int endian) {
        braf.order(endian);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        braf.write(b, off, len);
    }

    public void writeInt(int[] pa, int start, int n) throws IOException {
        braf.writeInt(pa, start, n);
    }

    public String getLocation() {
        return braf.getLocation();
    }

    public void writeBoolean(boolean v) throws IOException {
        braf.writeBoolean(v);
    }

    public void readInt(int[] pa, int start, int n) throws IOException {
        braf.readInt(pa, start, n);
    }

    public void unread() {
        braf.unread();
    }

    public long length() throws IOException {
        return braf.length();
    }

    public String readStringMax(int nbytes) throws IOException {
        return braf.readStringMax(nbytes);
    }

    public void writeDouble(double v) throws IOException {
        braf.writeDouble(v);
    }

    public void write(int b) throws IOException {
        braf.write(b);
    }

    public int readIntUnbuffered(long pos) throws IOException {
        return braf.readIntUnbuffered(pos);
    }

    public void writeUTF(String str) throws IOException {
        braf.writeUTF(str);
    }

    public byte readByte() throws IOException {
        return braf.readByte();
    }

    public void order(ByteOrder bo) {
        braf.order(bo);
    }

    public void readFloat(float[] pa, int start, int n) throws IOException {
        braf.readFloat(pa, start, n);
    }

    public String readString(int nbytes) throws IOException {
        return braf.readString(nbytes);
    }

    public void writeDouble(double[] pa, int start, int n) throws IOException {
        braf.writeDouble(pa, start, n);
    }

    public void writeShort(short[] pa, int start, int n) throws IOException {
        braf.writeShort(pa, start, n);
    }

    public int getBufferSize() {
        return braf.getBufferSize();
    }

    public void writeChar(int v) throws IOException {
        braf.writeChar(v);
    }

    public void writeChars(String s) throws IOException {
        braf.writeChars(s);
    }

    public void writeLong(long[] pa, int start, int n) throws IOException {
        braf.writeLong(pa, start, n);
    }

    public void readFully(byte[] b) throws IOException {
        braf.readFully(b);
    }

    public void writeBytes(byte[] b, int off, int len) throws IOException {
        braf.writeBytes(b, off, len);
    }

    public long getFilePointer() throws IOException {
        return braf.getFilePointer();
    }

    public long readToByteChannel(WritableByteChannel dest, long offset, long nbytes) throws IOException {
        return braf.readToByteChannel(dest, offset, nbytes);
    }

    public void write(byte[] b) throws IOException {
        braf.write(b);
    }

    public static void setDebugLeaks(boolean b) {
        RandomAccessFile.setDebugLeaks(b);
    }

    public void writeBytes(String s) throws IOException {
        braf.writeBytes(s);
    }

    public boolean readBoolean() throws IOException {
        return braf.readBoolean();
    }

    public int readUnsignedShort() throws IOException {
        return braf.readUnsignedShort();
    }

    public void writeFloat(float[] pa, int start, int n) throws IOException {
        braf.writeFloat(pa, start, n);
    }

    public float readFloat() throws IOException {
        return braf.readFloat();
    }

    public void writeBoolean(boolean[] pa, int start, int n) throws IOException {
        braf.writeBoolean(pa, start, n);
    }

    public int readInt() throws IOException {
        return braf.readInt();
    }

    public void readLong(long[] pa, int start, int n) throws IOException {
        braf.readLong(pa, start, n);
    }

    public static long getDebugNbytes() {
        return RandomAccessFile.getDebugNbytes();
    }

    public void writeInt(int v) throws IOException {
        braf.writeInt(v);
    }

    public void readDouble(double[] pa, int start, int n) throws IOException {
        braf.readDouble(pa, start, n);
    }

    public void setExtendMode() {
        braf.setExtendMode();
    }

    public void writeFloat(float v) throws IOException {
        braf.writeFloat(v);
    }

    public String readUTF() throws IOException {
        return braf.readUTF();
    }

    public static List<String> getAllFiles() {
        return RandomAccessFile.getAllFiles();
    }

    public static int getDebugNseeks() {
        return RandomAccessFile.getDebugNseeks();
    }

    public void setMinLength(long minLength) {
        braf.setMinLength(minLength);
    }

    public long readLong() throws IOException {
        return braf.readLong();
    }

    public void seek(long pos) throws IOException {
        braf.seek(pos);
    }

    public void writeChar(char[] pa, int start, int n) throws IOException {
        braf.writeChar(pa, start, n);
    }
}
