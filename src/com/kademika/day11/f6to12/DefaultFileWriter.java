package com.kademika.day11.f6to12;


import java.io.FileOutputStream;
import java.io.IOException;

public class DefaultFileWriter implements FileWriter {

    @Override
    public void write(String data, String fileName) {

        try (FileOutputStream fos = new FileOutputStream(fileName)) {

            fos.write(data.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
