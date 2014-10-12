package com.kademika.day12.f2to3ball;

public class Launcher {
    public static void main(String[] args) throws InterruptedException {
        Balls bls = new Balls();
        while (true) {
            bls.update();
            Thread.sleep(1000 / 60);
        }
    }
}
