package com.kademika.day13.f2to3;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Admin on 16.09.2014.
 */
public class NastyChump {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 2000; i++) {
            try{
                new Socket("localhost", 8080);
                System.out.println(i);
            }catch (IOException e){
                System.out.println("could not connect - "+e);
            }
        }
        Thread.sleep(10000000);
    }
}
