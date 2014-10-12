package com.kademika.day12.f10to15;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class LauncherWithJoin {
    public static void main(String[] args) throws Exception {
        System.out.println("Good morning, everyone we are opened!!!");

        final SkatingRink skatingRink = new SchoolSkatingRink();

        final Random random = new Random();

        final CountDownLatch countDownLatch = new CountDownLatch(4);

        for (int i = 0; i < 10; i++) {
            final Skater skater = new Skater("Skater " + i);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Skates skates = skatingRink.getSkates(skater);
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(skater.getName() + " on ice");
                    sleep(random.nextInt(2000));
                    skatingRink.returnSkates(skates, skater);
                }
            });
            thread.start();
//            thread.join();
            countDownLatch.countDown();
            sleep(random.nextInt(1000));
        }
        System.out.println("Good night. See you tomorrow!!!");
    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
