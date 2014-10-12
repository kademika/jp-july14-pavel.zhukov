package com.kademika.day12.f17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Admin on 07.09.2014.
 */
public class SingleTaskExecutorDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<Integer> future = executor.submit(new Task());
            System.out.println(future.get());
        } finally {
            executor.shutdown();
        }
    }
}
