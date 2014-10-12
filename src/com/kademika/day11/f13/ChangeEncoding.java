package com.kademika.day11.f13;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ChangeEncoding {
    private static String dir = "TestDir";

    public static void main(String[] args) throws IOException {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
//        String dir = "TestDir";
        String fileName = "testEncoding.txt";
        File file = new File(dir + File.separator + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileOutputStream fos = new FileOutputStream(file);
        String str;
        for (int i = 0; i < alphabet.length(); i++) {
            str = alphabet.charAt(i) + " ";
            fos.write(str.getBytes(StandardCharsets.ISO_8859_1));
        }
        fos.close();
        changeEncoding(file, StandardCharsets.ISO_8859_1, StandardCharsets.UTF_8);
    }

    public static void changeEncoding(File file, Charset currentEncoding, Charset neededEncoding) {
        FileInputStream fis = null;
        InputStreamReader reader = null;
        FileOutputStream fos = null;
        OutputStreamWriter writer = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        StringBuilder builder = new StringBuilder();

        try {
            fis = new FileInputStream(file);
            reader = new InputStreamReader(fis, currentEncoding);
            bufferedReader = new BufferedReader(reader, 256);

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos = new FileOutputStream(file);
            writer = new OutputStreamWriter(fos, neededEncoding);
            bufferedWriter = new BufferedWriter(writer, 256);

            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
                reader.close();
                writer.close();
                fos.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}