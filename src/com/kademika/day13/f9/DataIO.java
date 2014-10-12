package com.kademika.day13.f9;

import java.io.*;

/**
 * Created by Admin on 07.10.2014.
 */
public class DataIO {
    public static void main(String[] args) throws Exception {
        writeToFile("output.dat");
        readFromFile("output.dat");
    }

    private static void readFromFile(String file) throws Exception {
        try (
                DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        ) {
            System.out.println(dis.readInt());
            System.out.println(dis.readFloat());
            System.out.println(dis.readChar());
        }
    }

    private static void writeToFile(String file) throws Exception {
        try (
                DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        ) {
            dos.writeInt(10);
            dos.writeFloat(25.3f);
            dos.writeChar('a');
        }
    }
}
