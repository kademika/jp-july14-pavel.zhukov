package com.kademika.day11.f3;


import java.io.ByteArrayOutputStream;
import java.util.Arrays;


public class BAISWriter {
    public static void main(String[] args) {
        byte[] bytes = {65, 85, 47, 12, 32, -21, -128, 0};
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; i++) {
            out.write(bytes[i]);
        }
        System.out.println(Arrays.toString(out.toByteArray()));
    }
}
