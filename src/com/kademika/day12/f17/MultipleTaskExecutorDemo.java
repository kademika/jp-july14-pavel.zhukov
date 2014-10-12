package com.kademika.day12.f17;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Admin on 07.09.2014.
 */
public class MultipleTaskExecutorDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<Integer>> futures = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {
                futures.add(executor.submit(new Task()));
            }

            for (Future<Integer> future : futures) {
                results.add(future.get());
            }
        } finally {
            executor.shutdown();
        }

        for (Integer i : results) {
            System.out.println(i);
        }
    }
}
