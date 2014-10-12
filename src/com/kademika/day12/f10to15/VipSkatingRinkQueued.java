package com.kademika.day12.f10to15;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Admin on 31.08.2014.
 */
public class VipSkatingRinkQueued implements SkatingRink {


    private final Queue<Skates> skatesShelf = new LinkedBlockingQueue<>();

    public VipSkatingRinkQueued() {
        for (int i = 0; i < 3; i++) {
            skatesShelf.add(new Skates("Skates " + i));
        }
    }

    @Override
    public Skates getSkates(Skater skater) {
  /*      if (skatesShelf.isEmpty()) {

            try {
                synchronized (skatesShelf) {
                    skatesShelf.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        Skates skates = skatesShelf.poll();
        if (skates != null) {
            System.out.println(skater.getName() + " get " + skates.getName());
        }

        return skates;

    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {
        skatesShelf.add(skates);
//        synchronized (skatesShelf) {
//            skatesShelf.notify();
//        }

        System.out.println(skater.getName() + " return " + skates.getName());
    }

}
