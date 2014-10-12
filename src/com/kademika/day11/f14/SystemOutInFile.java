package com.kademika.day11.f14;


import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class SystemOutInFile {
    public static void main(String[] args) throws IOException {
        String dir = "TestDir";
        String fileName = "sysOutFile.txt";
        File file = new File(dir + File.separator + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        PrintStream sysOut = System.out;
        PrintStream out = new PrintStream(file);
        System.setOut(out);
        System.out.println("Hello World!");
        System.out.println("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("0123456789");
        System.setOut(null);
        System.setOut(sysOut);
        System.out.println("1");
    }
}
