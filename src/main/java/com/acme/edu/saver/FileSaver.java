package com.acme.edu.saver;

import com.acme.edu.exception.SaveException;
import com.acme.edu.message.AbstractMessage;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileSaver implements Saver {
    public RandomAccessFile file = null;
    private final String fileName;

    public FileSaver(String fileName){
        this.fileName = fileName;
    }

    @Override
    public void save(AbstractMessage message) throws SaveException {
        try {
            if(file == null) {
                file = new RandomAccessFile(fileName, "rw");
            }
            byte[] strToBytes = (message.getPreparedMessage()+ System.lineSeparator()).getBytes();
            file.write(strToBytes);
        } catch (IOException e) {
            throw new SaveException("cannot write to this file or it does not exist!", e);
        }
    }

}
