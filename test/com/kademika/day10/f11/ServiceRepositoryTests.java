package com.kademika.day10.f11;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

/**
 * Created by Admin on 07.07.14.
 */
@RunWith(JUnit4.class)
public class ServiceRepositoryTests {

    private Service serv;
    private int k;
    private ServiceRepository<Service> sr;

    @Before
    public void init() {
        serv = new Service() {
            @Override
            public void someMethod() {
                k = 2;
            }
        };

        sr = new ServiceRepository<>();
        sr.add(serv);
    }

    @Test
    public void testService() {
        assertTrue(serv instanceof Service);
    }

    @Test
    public void testServiceMethod() {
        assertNotNull(k);
    }

    @Test
    public void testSRElement() {
        assertNotNull(sr.get(0));
    }

    @Test
    public void testServiceRep() {
        assertEquals(serv, sr.get(0));
    }

}