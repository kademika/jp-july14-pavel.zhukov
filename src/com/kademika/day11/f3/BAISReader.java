package com.kademika.day11.f3;


import java.io.ByteArrayInputStream;

public class BAISReader {
    public static void main(String[] args) {
        byte[] bytes = {65, 85, 47, 12, 32, -21, -128, 0};
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        int i;
        while ((i = in.read()) != -1) {
            System.out.print((byte) i);
            System.out.print(" ");
        }
    }

}
