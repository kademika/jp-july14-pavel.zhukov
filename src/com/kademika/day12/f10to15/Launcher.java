package com.kademika.day12.f10to15;

import com.kademika.day12.f2to3ball.ThreadForBall;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Launcher {
    public static void main(String[] args) throws Exception {
        System.out.println("Good morning, everyone we are opened!!!");

        final SkatingRink skatingRink = new PublicSkatingRink(5);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        final Random random = new Random();

        try {
            for (int i = 0; i < 10; i++) {
                final Skater skater = new Skater("Skater " + i);
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        Skates skates = skatingRink.getSkates(skater);
                        sleep(random.nextInt(2000));
                        skatingRink.returnSkates(skates, skater);
                    }
                });
            }
        } finally {
            executorService.shutdown();
        }
    }

    public static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
