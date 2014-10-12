package com.kademika.day12.f1;


public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Running " + Thread.currentThread().getName() + " " + this.getClass().getSimpleName());
    }
}
