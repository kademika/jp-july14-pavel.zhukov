package com.kademika.day12.f17;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Admin on 07.09.2014.
 */
public class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        Random rand = new Random();

        int result = 0;
        for (int i = 0; i < rand.nextInt(Integer.MAX_VALUE); i++) {
            result++;
        }
        return result;
    }
}
