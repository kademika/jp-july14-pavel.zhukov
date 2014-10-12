package com.kademika.day10.f20;

import com.kademika.day10.shop.watches.Mechanic;
import com.kademika.day10.shop.watches.WatchType;

/**
 * Created by Admin on 07.07.14.
 */
public class ClassTester {
    public static void main(String[] args) {
        Mechanic mechanic = new Mechanic("ORIENT DE00004D", 98, WatchType.Pocket, "0001", 4, 500, 3, "Men, waterproof, calendar");
        System.out.println(mechanic.getClass().getName());
        System.out.println(mechanic.getClass().getSimpleName());
        System.out.println(mechanic.getClass().getSuperclass());
        System.out.println(mechanic.getClass().getClassLoader());
    }
}
