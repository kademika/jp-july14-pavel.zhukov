package com.kademika.day11.f6to12;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class JDKWriter implements FileWriter {

    @Override
    public void write(String data, String fileName) throws IOException {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, StandardCharsets.ISO_8859_1);
        ) {

            outputStreamWriter.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
