package com.kademika.day10.f22;


import com.kademika.day10.shop.watches.Mechanic;
import com.kademika.day10.shop.watches.Quartz;
import com.kademika.day10.shop.watches.WatchType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Launcher {
    public static void main(String[] args) throws Exception {
//        ClassInfo.printClassInfo(Mechanic.class);
//        ClassInfo.printClassMethods(Mechanic.class);
//        ClassInfo.printClassFields(Mechanic.class);


        Map<String, Object> m1 = new HashMap<>();
        m1.put("name", "Thomas Earnshaw ES-8008-04");
        m1.put("weight", 103);
        m1.put("type", WatchType.Wrist);
        m1.put("id", "0002");
        m1.put("number", 3);
        m1.put("price", 600.0);
        m1.put("additionalDials", "Men, waterproof, date, caseback");
        m1.put("numberArrows", 5);
        Mechanic mechanic = ClassInfo.initClass(Mechanic.class, m1);
        System.out.println(mechanic.getInfo());

        Map<String, Object> m2 = new HashMap<>();
        m2.put("name", "Diesel DZ7285");
        m2.put("weight", 162);
        m2.put("type", WatchType.Wrist);
        m2.put("id", "1009");
        m2.put("number", 5);
        m2.put("price", 450.0);
        m2.put("isBacklight", true);
        m2.put("moreFeatures", "Men, waterproof, second time zone, arab numerals");
        Quartz quartz = ClassInfo.initClass(Quartz.class, m2);
        System.out.println(quartz.getInfo());

        List<Object> list = new ArrayList<>();
        list.add("Timex T2N958");
        list.add(157);
        list.add(WatchType.Wrist);
        list.add("1007");
        list.add(6);
        list.add(370.0);
        list.add(true);
        list.add("Men, waterproof, number, depth gauge, thermometer, arab + roman numerals");
        Quartz quartz1 = ClassInfo.initClass(Quartz.class, list);
        System.out.println(quartz1.getInfo());

        Mechanic mechanic1 = new Mechanic();
        ClassInfo.setPrivates(mechanic1, m1);
        System.out.println(mechanic1.getInfo());
    }
}
