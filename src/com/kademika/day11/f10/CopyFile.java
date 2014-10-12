package com.kademika.day11.f10;

import java.io.*;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        String dir = "TestDir";
        String fileName = "test.txt";
        File file = new File(dir + File.separator + fileName);
        FileOutputStream fos = new FileOutputStream(file);
        String str;
        for (int i = 0; i < 300; i++) {
            str = i + " ";
            fos.write(str.getBytes());
        }
        fos.write("Hello World!".getBytes());
        fos.close();
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
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis, 256);
            fos = new FileOutputStream(newFile);
            bos = new BufferedOutputStream(fos, 256);
            int i;
            while ((i = bis.read()) != -1) {
                builder.append((char) i);
            }
            bos.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fis.close();
            fos.close();
            bis.close();
            bos.flush();
            bos.close();
        }
    }
}
