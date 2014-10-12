package com.kademika.day12.f5;

public class SelfDestroyerDemo {
    public static void main(String[] args) throws Exception {
        SelfDestroyer selfDestroyer = new SelfDestroyer();
        selfDestroyer.start();

        Thread.currentThread().sleep(1000);

//        selfDestroyer.killMyself();
        selfDestroyer.interrupt();
    }
}
