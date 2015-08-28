/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umich.ms.fileio.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 *
 * @author dmitriya
 */
public class ReverseLineInputStream extends InputStream {
    RandomAccessFile in;
    long currentLineStart = -1;
    long currentLineEnd = -1;
    long currentPos = -1;
    long lastPosInFile = -1;

    public ReverseLineInputStream(RandomAccessFile raf) throws IOException {
        in = raf;
        currentLineStart = raf.length();
        currentLineEnd = raf.length();
        lastPosInFile = raf.length() - 1;
        currentPos = currentLineEnd;
    }

    public void setFilePointer(long pos) {
        currentLineEnd = pos;
        currentLineStart = pos;
        currentPos = pos;
    }

    public void findPrevLine() throws IOException {
        currentLineEnd = currentLineStart;
        // There are no more lines, since we are at the beginning of the file and no lines.
        if (currentLineEnd == 0) {
            currentLineEnd = -1;
            currentLineStart = -1;
            currentPos = -1;
            return;
        }
        long filePointer = currentLineStart - 1;
        while (true) {
            filePointer--;
            // we are at start of file so this is the first line in the file.
            if (filePointer < 0) {
                break;
            }
            in.seek(filePointer);
            int readByte = in.readByte();
            // We ignore last LF in file. search back to find the previous LF.
            if (readByte == 0xA && filePointer != lastPosInFile) {
                break;
            }
        }
        // we want to start at pointer +1 so we are after the LF we found or at 0 the start of the file.
        currentLineStart = filePointer + 1;
        currentPos = currentLineStart;
    }

    @Override
    public int read() throws IOException {
        if (currentPos < currentLineEnd) {
            in.seek(currentPos++);
            int readByte = in.readByte();
            return readByte;
        } else if (currentPos < 0) {
            return -1;
        } else {
            findPrevLine();
            return read();
        }
    }
    
}
