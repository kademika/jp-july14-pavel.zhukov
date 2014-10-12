package com.kademika.day10.f29;


import java.util.List;

public interface StorageService {
    public <T> T store(T obj);

    public <T> T getById(Long l);

    public <T> List<T> getAll(Class clazz);
}
