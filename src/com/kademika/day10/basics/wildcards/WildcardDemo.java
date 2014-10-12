package com.kademika.day10.basics.wildcards;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 28.06.14.
 */
public class WildcardDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(new Integer[]{1, 2, 3});
        print(numbers);

        List<Long> longs = Arrays.asList(new Long[]{1L, 2L, 3L});
        print(longs);
    }

    private static void print(List<? extends Number> numbers) {
        for (Number num : numbers) {
            System.out.println(num);
        }
    }
}
