package com.kademika.day10.basics.wildcards;

import com.kademika.day10.f2domain.Quartz;
import com.kademika.day10.f2domain.Mechanic;
import com.kademika.day10.f2domain.Watch;

/**
 * Created by Admin on 28.06.14.
 */
public class WatchBoxDemo {
    public static void main(String[] args) {
        WatchBox<Watch> watches = new WatchBox<>();
        WatchBox<Mechanic> quartz = new WatchBox<>();
        WatchBox<Quartz> electronic = new WatchBox<>();

//        watches.addWatch(new Quartz());
//        watches.addWatch(new Electronic());

        for (Watch w : watches.getWatchList()) {
            w.getName();
        }
    }
}
