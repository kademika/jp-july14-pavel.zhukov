package com.kademika.day10.f26;


import java.util.ArrayList;
import java.util.List;


public class ServiceRepository<T> {
    private List<T> serviceList;

    public ServiceRepository() {
        serviceList = new ArrayList<>();
    }

    public void add(T obj) {
        serviceList.add(obj);
    }

    public void remove(T obj) {
        serviceList.remove(obj);
    }

    public T get(int n) {
        return serviceList.get(n);
    }

    public List<T> getServiceList() {
        return serviceList;
    }
}
