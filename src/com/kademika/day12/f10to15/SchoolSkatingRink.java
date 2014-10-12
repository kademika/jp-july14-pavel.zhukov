package com.kademika.day12.f10to15;

public class SchoolSkatingRink implements SkatingRink {
    @Override
    public Skates getSkates(Skater skater) {
        System.out.println(skater.getName() + " get skates");
        return new Skates("Skates");
    }

    @Override
    public void returnSkates(Skates skates, Skater skater) {
        System.out.println(skater.getName() + " return skates");
    }
}
