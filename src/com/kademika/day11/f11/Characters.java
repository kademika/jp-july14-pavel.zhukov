package com.kademika.day11.f11;

import java.util.Arrays;

public class Characters {
    public static void main(String[] args) {
        char copyright = 169;
        System.out.println(copyright);
        System.out.println();

        copyright = '\u00A9';
        System.out.println(copyright);
        System.out.println();

        printCodePointInfo(100);
        printCodePointInfo(500);
        printCodePointInfo(1000);
        printCodePointInfo(10000);
        printCodePointInfo(50000);
        printCodePointInfo(65600);
        printCodePointInfo(90000);
    }

    private static void printCodePointInfo(int codepoint) {
        char[] chars = Character.toChars(codepoint);
        String str = new String(chars);

        System.out.println("Code Point: " + codepoint);
        System.out.println("Char Array: " + Arrays.toString(chars));
        System.out.println("Res String: " + str);
        System.out.println("String lenght: " + str.length());
        System.out.println("Code count: " + str.codePointCount(0, str.length()));
        System.out.println();
    }
}
