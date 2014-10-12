package com.kademika.day11.f15zip;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.zip.*;

public class ZipUnzipFile {
    private static String dir = "TestDir";
    private static String dirIn = "TestDirIn";

    public static void main(String[] args) throws IOException {
        StringBuilder cmd = new StringBuilder();
        int in;
        while ((in = System.in.read()) != '\n') {
            cmd.append((char) in);
        }
        String command = cmd.toString().substring(0, cmd.toString().indexOf(" "));
        String nameDir = cmd.toString().substring(cmd.toString().indexOf(" ") + 1, cmd.toString().length());

//        String command = "unzip";
//        String nameDir = "TestDirIn";

        String nameDirZip = nameDir;

        for (int i = 0; i < nameDir.length(); i++) {
            if (nameDir.indexOf(".") != -1) {
                nameDirZip = nameDir.substring(0, nameDir.indexOf("."));
            }
        }

        if (command.equals("zip")) {
            testData();
            zip(dir + File.separator + nameDir, dir + File.separator + nameDirZip + ".zip");
        } else if (command.equals("unzip")) {
            unzip(dir + File.separator + nameDir + ".zip");
        } else {
            System.out.println("Wrong command");
        }


    }

    public static void testData() throws IOException {
        String a1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\nHello World!\n0123456789";
        String a2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ\nHello World!\n0123456789\nABCDEFGHIJKLMNOPQRSTUVWXYZ\n" +
                "Hello World!\n0123456789";


        String dirInIn1 = "TestDirIn1";
        String dirInIn2 = "TestDirIn2";
        String fileName1 = "test1.txt";
        String fileName2 = "test2.doc";

        File dirFile = new File(dir);
        dirFile.mkdir();

        File dirInFile = new File(dir + File.separator + dirIn);
        dirInFile.mkdir();

        File dir1 = new File(dir + File.separator + dirIn + File.separator + dirInIn1);
        dir1.mkdir();
        File dir2 = new File(dir + File.separator + dirIn + File.separator + dirInIn2);
        dir2.mkdir();

        File file1 = new File(dir + File.separator + dirIn + File.separator + dirInIn1 + File.separator + fileName1);
        File file2 = new File(dir + File.separator + dirIn + File.separator + dirInIn2 + File.separator + fileName2);
        file1.createNewFile();
        file2.createNewFile();

        String str;
        FileOutputStream fos = new FileOutputStream(file1);
        for (int i = 0; i < a1.length(); i++) {
            str = a1.charAt(i) + " ";
            fos.write(str.getBytes(StandardCharsets.UTF_8));
        }
        fos.close();

        fos = new FileOutputStream(file2);
        for (int i = 0; i < a2.length(); i++) {
            str = a2.charAt(i) + " ";
            fos.write(str.getBytes());
        }
        fos.close();
    }

    public static boolean zip(String dirName, String zipName) {
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName));
            zos.setLevel(Deflater.BEST_COMPRESSION);
            zipDir(dirName, zos);
            zos.close();
            File f = new File(zipName);
            if (f.exists()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is not possible to archive this file");
            return false;
        }
    }

    private static void zipDir(String dirName, ZipOutputStream zos) {
        try {
            File zipFile = new File(dirName);

            int in;
            if (zipFile.isFile()) {
                FileInputStream fis = new FileInputStream(zipFile);
                ZipEntry anEntry = new ZipEntry(dirName.substring(dirName.indexOf(File.separator) + 1, dirName.length()));
                zos.putNextEntry(anEntry);
                while ((in = fis.read()) != -1) {
                    zos.write(in);
                }
                fis.close();
                zos.closeEntry();
            } else {
                zos.putNextEntry(new ZipEntry(dirName.substring(dirName.indexOf(File.separator) + 1, dirName.length()) + File.separator));
                zos.closeEntry();
                String[] list = zipFile.list();
                for (String n : list) {
                    zipDir(dirName + File.separator + n, zos);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean unzip(String zipName) {
        try (
                ZipFile zipFile = new ZipFile(zipName);
        ) {
            String fdir = zipName.substring(0, zipName.indexOf(File.separator));

            Enumeration entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry ze = (ZipEntry) entries.nextElement();
                File tmpFile = new File(fdir + File.separator + ze.getName());

                if (!new File(tmpFile.getParent()).exists()) {
                    new File(tmpFile.getParent()).mkdirs();
                }

                byte[] buf = new byte[(int) ze.getSize()];

                BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(ze));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tmpFile));

                while (bufferedInputStream.read(buf) > 0) {
                    bufferedOutputStream.write(buf);
                }
                bufferedOutputStream.close();
                bufferedInputStream.close();

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There is not possible to archive this file");
            return false;
        }

    }
}

