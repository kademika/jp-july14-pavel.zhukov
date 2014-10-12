package com.kademika.day11.f5;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class DirWithFile {
    public static void main(String[] args) throws IOException {
        String dir = "TestDir";
        String fname = "test.txt";

        File fdir = new File(dir);
        fdir.mkdirs();

        File file = new File(dir + File.separator + fname);
        file.createNewFile();

        String usrDir = System.getProperty("user.dir");
        System.out.println(usrDir);

        String[] strDirList = fdir.list();
        for (String s : strDirList) {
            System.out.println(s);
        }

//        dirList(usrDir);

        dirList(dir);
    }

    public static void dirList(String dir) {
        File fDir = new File(dir);
        String[] strDirList = fDir.list();

        for (int i = 0; i < strDirList.length; i++) {
            File tmpFile = new File(dir + File.separator + strDirList[i]);
            if (tmpFile.isFile()) {
                System.out.println(dir + File.separator + strDirList[i]);
            } else {
                dirList(dir + File.separator + strDirList[i]);
            }
        }
    }
}

