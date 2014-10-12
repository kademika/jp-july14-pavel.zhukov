package com.kademika.day13.f6;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Admin on 14.09.2014.
 */
public class Utils {

    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return data ^ ' ';
        }
        return data;
    }

    public static void process(SocketChannel sc) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Connection from " + sc);
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (sc.read(byteBuffer) != -1) {

                byteBuffer.flip();
                for (int i = 0; i < byteBuffer.limit(); i++) {
                    byteBuffer.put(i, (byte) transmogrify(byteBuffer.get(i)));
                }
                sc.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            System.out.println("Connection problem -" + e);
        }
    }
}
