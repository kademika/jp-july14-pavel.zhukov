package com.kademika.day11.f4;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Launcher {
    public static void main(String[] args) {
        byte[] bytes = {65, 85, 47, 12, 32, -21, -128, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        printStreamData(in);
    }

    public static void printStreamData(ByteArrayInputStream inputStream) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int i;
        while ((i = inputStream.read()) != -1) {
            System.out.print((byte) i + " ");
        }
    }
}
