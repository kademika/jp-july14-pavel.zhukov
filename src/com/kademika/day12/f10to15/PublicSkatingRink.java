package com.kademika.day12.f10to15;


import java.util.ArrayList;

public class PublicSkatingRink implements SkatingRink {
    private ArrayList<Skates> skatesList;


    public PublicSkatingRink(int num) {
        skatesList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            skatesList.add(new Skates("Skates " + i));
        }
    }

    @Override
    public Skates getSkates(Skater skater) {
//        id++;
        if (skatesList.isEmpty()) {
            synchronized (skatesList) {
                System.out.println(skater.getName() + " waiting skates");
                try {
                    skatesList.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        synchronized (skatesList) {
            Skates skates = skatesList.get(0);
            skatesList.remove(0);
            System.out.println(skater.getName() + " got skates and sent to the rink");
            return skates;
        }
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {
        System.out.println(skater.getName() + " return " + skates.getName());
        synchronized (skatesList) {
            skatesList.add(skates);
            skatesList.notify();
        }
    }

}
