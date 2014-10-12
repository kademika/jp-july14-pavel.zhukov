package com.kademika.day11.f7;


import java.io.*;

public class Launcher {
    public static void main(String[] args) throws IOException {
        String dir = "TestDir";
        String fileName = "test.txt";
        File file = new File(dir + File.separator + fileName);
        copyFile(file);
    }

    public static void copyFile(File file) throws IOException {
        String fileName = file.getName().substring(0, file.getName().indexOf(".")) + "Copy" +
                file.getName().substring(file.getName().indexOf("."), file.getName().length());
        File newFile = new File(file.getPath().substring(0, file.getPath().indexOf(File.separator)) +
                File.separator + fileName);
        newFile.createNewFile();
        StringBuilder builder = new StringBuilder();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(file);
            fos = new FileOutputStream(newFile);
            int i;
            while ((i = fis.read()) != -1) {
                builder.append((char) i);
            }
            fos.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
        }
    }
}
