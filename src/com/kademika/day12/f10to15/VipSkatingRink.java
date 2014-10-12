package com.kademika.day12.f10to15;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VipSkatingRink implements SkatingRink {
    private Lock skatesLock;
    private List<Skates> skatesList;

    public VipSkatingRink() {
        skatesList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            skatesList.add(new Skates("Skates " + i));
        }

        skatesLock = new ReentrantLock();
    }

    @Override
    public Skates getSkates(Skater skater) {
        if (skatesList.isEmpty()) {

            try {
                synchronized (skatesList) {
                    skatesList.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        skatesLock.lock();
        Skates skates = skatesList.remove(0);
        skatesLock.unlock();
        System.out.println(skater.getName() + " get " + skates.getName());

        return skates;
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {
        skatesList.add(skates);
        synchronized (skatesList) {
            skatesList.notify();
        }

        System.out.println(skater.getName() + " return " + skates.getName());
    }
}
