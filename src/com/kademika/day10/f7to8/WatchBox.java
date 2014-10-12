package com.kademika.day10.f7to8;


import com.kademika.day10.f2domain.Watch;

import java.util.ArrayList;
import java.util.Comparator;
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

    public void removeWatch(T watch) {
        watchList.remove(watch);
    }

    public T getWatch(int n) {
        return watchList.get(n);
    }

    public List<T> getWatchList() {
        return watchList;
    }

    public void copy(List<? extends Watch> src, List<? super Watch> dest) {
        for (Watch w : src) {
            dest.add(w);
        }
    }

}

class SortByName implements Comparator<Watch> {
    @Override
    public int compare(Watch o1, Watch o2) {

        String str1 = o1.getName();
        String str2 = o2.getName();

        return str1.compareTo(str2);

    }
}

class SortByWeight implements Comparator<Watch> {
    @Override
    public int compare(Watch o1, Watch o2) {
        int w1 = o1.getWeight();
        int w2 = o2.getWeight();

        if (w1 > w2) {
            return 1;
        } else if (w1 < w2) {
            return -1;
        } else {
            return 0;
        }
    }
}
