package com.kademika.day11.f8;

import java.io.*;

public class PrintData {
    public static void main(String[] args) throws IOException {
        String dir = "TestDir";
        String fname = "printData.txt";

        File fdir = new File(dir);
        fdir.mkdirs();

        File file = new File(dir + File.separator + fname);
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        String str;
        for (int i = 0; i < 100; i++) {
            str = i + " ";
            fos.write(str.getBytes());
        }
        fos.write("Hello World!".getBytes());
        FileInputStream fis = new FileInputStream(file);
        printStreamData(fis);

        fos.close();
        fis.close();
    }

    public static void printStreamData(InputStream inputStream) throws IOException {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringBuilder builder = new StringBuilder();

        int i;
        while ((i = inputStream.read()) != -1) {
            System.out.print((char) i);
        }
    }
}
