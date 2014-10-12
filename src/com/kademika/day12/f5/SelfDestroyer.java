package com.kademika.day12.f5;


public class SelfDestroyer extends Thread {
    private volatile boolean killMe = false;

    public void killMyself() {
        killMe = true;
    }

    @Override
    public void run() {
        while (!killMe) {
            System.out.println("Enjoying Life...");
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Life is so cool aaaaa(Dying...)");
    }
}
