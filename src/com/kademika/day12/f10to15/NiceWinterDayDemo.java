package com.kademika.day12.f10to15;

import java.util.Random;

/**
 * Created by Admin on 26.08.2014.
 */
public class NiceWinterDayDemo {
    public static void main(String[] args) {
        System.out.println("Good morning, everyone we are opened!!!");

        final SkatingRink skatingRink = new VipSkatingRinkQueued();

        final Random random = new Random();

        for (int i = 0; i < 1000; i++) {
            final Skater skater = new Skater("Skater " + i);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Skates skates = null;
                    while (skates == null) {
                        sleep(random.nextInt(2000));
                        skates = skatingRink.getSkates(skater);
                    }
                    sleep(random.nextInt(2000));
                    skatingRink.returnSkates(skates, skater);
                }
            }).start();
            sleep(random.nextInt(1000));
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
