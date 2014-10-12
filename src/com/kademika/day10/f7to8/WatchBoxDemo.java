package com.kademika.day10.f7to8;

import com.kademika.day10.f2domain.Quartz;
import com.kademika.day10.f2domain.Mechanic;
import com.kademika.day10.f2domain.Watch;
import com.kademika.day10.f2domain.WatchType;

import java.util.Collections;

/**
 * Created by Admin on 28.06.14.
 */
public class WatchBoxDemo {
    public static void main(String[] args) {
        WatchBox<Watch> watches = new WatchBox<>();

        watches.addWatch(new Quartz("Q&Q", 65, WatchType.Wrist, true, "Timer, calendar"));
        watches.addWatch(new Mechanic("Swiss Military", 95, WatchType.Wrist, 3, "None"));
        watches.addWatch(new Mechanic("Casio", 80, WatchType.Wrist, 3, "Calendar"));
        watches.addWatch(new Mechanic("Orient", 100, WatchType.Pocket, 3, "Calendar, compass"));
        watches.addWatch(new Quartz("Seiko", 73, WatchType.Wrist, false, "Timer, calendar"));


        System.out.println("============ no sorted ==============");
        for (Watch w : watches.getWatchList()) {
            System.out.println(w.getName() + ", " + w.getWeight() + "g, " + w.getType().toString());
        }

        System.out.println("========== sorted by name ===========");
        Collections.sort(watches.getWatchList(), new SortByName());
        for (Watch w : watches.getWatchList()) {
            System.out.println(w.getName() + ", " + w.getWeight() + "g, " + w.getType().toString());
        }

        System.out.println("========== sorted by weight ===========");
        Collections.sort(watches.getWatchList(), new SortByWeight());
        for (Watch w : watches.getWatchList()) {
            System.out.println(w.getName() + ", " + w.getWeight() + "g, " + w.getType().toString());
        }
    }
}
