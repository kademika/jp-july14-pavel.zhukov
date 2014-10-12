package com.kademika.day11.f6to12;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JDKReader implements FileReader {
    @Override
    public String read(String fileName) {
        StringBuilder builder = new StringBuilder();

        try (
                FileInputStream fis = new FileInputStream(fileName);
                InputStreamReader reader = new InputStreamReader(fis, StandardCharsets.ISO_8859_1);
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
