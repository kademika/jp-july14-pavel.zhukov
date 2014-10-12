package com.kademika.day13.f7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlConnectionReader {
    public static void main(String[] args) throws Exception {
        URL adress = new URL("http://www.google.com/");
        URLConnection urlConnection = adress.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}