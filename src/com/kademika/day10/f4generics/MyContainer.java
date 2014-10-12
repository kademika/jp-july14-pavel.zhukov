package com.kademika.day10.f4generics;

import java.util.LinkedList;

/**
 * Created by Admin on 26.06.14.
 */
public class MyContainer<T> extends LinkedList {
//    public T obj;

    public void addObj(T obj) {
        add(obj);
    }

    public void removeObj(T obj) {
        remove(obj);
    }

    public T getObj() {
        return (T) getLast();
    }

    public <T> void print(LinkedList<T> list) {
        for (T t : list) {
            System.out.println(t.toString());
        }
    }
}
