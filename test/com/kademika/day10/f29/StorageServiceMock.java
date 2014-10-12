package com.kademika.day10.f29;


import java.util.ArrayList;
import java.util.List;

public class StorageServiceMock implements StorageService {

    private List<Object> objects;

    private int storeCounter;

    public StorageServiceMock() {
        this.objects = new ArrayList<>();
        storeCounter = 0;
    }

    @Override
    public <T> T store(T obj) {
        storeCounter++;
        objects.add(obj);
        return obj;
    }

    @Override
    public <T> T getById(Long l) {
        return null;
    }

    @Override
    public <T> List<T> getAll(Class clazz) {
        return (List<T>) new ArrayList<>(objects);
    }

    public int getStoreCounter() {
        return storeCounter;
    }
}
