package umich.ms.fileio.filetypes.util;


import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Paths;

/**
 * Author: Dmitry Avtonomov (dmitriya)
 * Email: dmitriy.avtonomov@gmail.com
 * Date: 2/18/13
 * Time: 11:01 AM
 */
public abstract class AbstractFile implements Closeable {
    protected String path = "";
    private BufferedInputStream bis;
    private RandomAccessFile raf;

    protected static int BUF_SIZE = 8192;


    public AbstractFile(String path) {
        this.path = Paths.get(path).toAbsolutePath().normalize().toString();
    }

    public synchronized BufferedInputStream getBufferedInputStream() throws FileNotFoundException {
        if (bis != null) {
            throw new IllegalStateException("getBufferedInputStream() called while previous BufferedInputStream has not been closed.");
        }
        bis = new BufferedInputStream(new FileInputStream(path));
        return bis;
    }

    public synchronized RandomAccessFile getRandomAccessFile() throws IOException {
        if (raf != null) {
            throw new IllegalStateException("getRandomAccessFile() called while previous RandomAccessFile has not been closed.");
        }
        raf = new RandomAccessFile(path, "r");

//        raf = new BufferedRandomAccessFile(path, "r", 8192);
//        DataInputStream dataInputStream = new DataInputStream(new FileInputStream(""));
//        dataInputStream.readDouble()
//        FileChannel channel = raf.getChannel();
//        ReadableByteChannel readableByteChannel = Channels.newChannel(new FileInputStream(Paths.get(path).toFile()));
//
//        InputStream s = Channels.newInputStream(channel);
//        Channels.
//        BufferedInputStream bis = new BufferedInputStream(s, 8192);

//        raf = new BufferedRandomAccessFile(path, "r", 8096);
        return raf;
    }

    @Override
    public void close() {
        synchronized (this) {
            try {
                if (raf != null) {
                    raf.close();
                    raf = null;
                }
                if (bis != null) {
                    bis.close();
                    bis = null;
                }
            } catch (IOException e) {
                throw new RuntimeException("Unrecoverable situation - couldn't close the file", e);
            }
        }
    }

    /**
     * Absolute normalized path to the file.
     * @return
     */
    public String getPath() {
        return path;
    }

    @Override
    public void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
