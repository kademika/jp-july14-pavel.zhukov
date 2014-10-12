package com.kademika.day10.f29;


import java.util.Map;

public class ServiceClass {
    public StorageService storageService;

    public void storeUserData(Map<String, Object> data) {
        storageService.store(new Object());
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }
}
