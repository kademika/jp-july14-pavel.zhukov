package com.kademika.day10.f4generics;

/**
 * Created by Admin on 26.06.14.
 */
public class Launcher {
    public static void main(String[] args) {
        MyContainer mc = new MyContainer<String>();
        mc.addObj("one");
        mc.addObj("two");
        mc.addObj("three");
        System.out.println(mc.getObj());
        System.out.println();
        mc.print(mc);
        System.out.println();
        mc.removeObj("three");
        System.out.println(mc.getObj());
        System.out.println();
        mc.print(mc);
    }
}
