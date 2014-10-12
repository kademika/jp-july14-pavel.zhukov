package com.kademika.day12.f16;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    AtomicLong idGenerator = new AtomicLong();

    public long getNextId() {
//        return idGenerator.getAndIncrement();
        return idGenerator.getAndAdd(5);
    }

    public static void main(String[] args) {
        final IdGenerator id = new IdGenerator();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(id.getNextId());
                }
            });
            thread.start();
        }

    }
}
