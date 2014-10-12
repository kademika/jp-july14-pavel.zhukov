package com.kademika.day10.f29;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ServiceClassTest {

    private ServiceClass serviceClass;
    private StorageServiceMock storageService;

    @Before
    public void init() {
        storageService = new StorageServiceMock();
        serviceClass = new ServiceClass();
        serviceClass.setStorageService(storageService);
    }

    @Test
    public void testStoreUserData() {
        serviceClass.storeUserData(new HashMap<String, Object>());
        assertTrue(storageService.getStoreCounter() == 1);

    }

    @Test
    public void testStoreUserData1() {
        serviceClass.storeUserData(new HashMap<String, Object>());
        assertTrue(storageService.getStoreCounter() != 2);

    }
}
