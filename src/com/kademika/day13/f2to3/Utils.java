package com.kademika.day13.f2to3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

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

    public static void process(Socket socket) {
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println("Connection from " + socket);
        try (
                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
        ) {
            int data;
            int c = 0;
            while ((data = in.read()) != -1) {
                c = data;
                data = transmogrify(c);
                stringBuilder.append(data + " ");

                if (c == 13) {
                    String str = stringBuilder.toString();
                    for (String s : str.split(" ")) {
                        out.write(Integer.parseInt(s));
                    }
                    out.write(10);
                    out.write(13);
                    stringBuilder = new StringBuilder();
                }
            }
        } catch (IOException e) {
            System.out.println("Connection problem -" + e);
        }
    }
}
