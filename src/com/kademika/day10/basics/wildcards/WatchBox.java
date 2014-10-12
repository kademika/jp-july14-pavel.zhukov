package com.kademika.day10.basics.wildcards;

import com.kademika.day10.f2domain.Watch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 28.06.14.
 */
public class WatchBox<T extends Watch> {
    private List<T> watchList;

    public WatchBox() {
        watchList = new ArrayList<>();
    }

    public void addWatch(T watch) {
        watchList.add(watch);
    }

    public List<T> getWatchList() {
        return new ArrayList<>(watchList);
    }
}
