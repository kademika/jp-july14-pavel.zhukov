package com.kademika.day10.f22;

import com.kademika.day10.shop.watches.Quartz;
import com.kademika.day10.shop.watches.WatchType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

@RunWith(JUnit4.class)
public class MethodInitTests {
    private ArrayList<Object> list;
    private Quartz quartz;

    @Before
    public void init() {
        list = new ArrayList();
        list.add("Timex T2N958");
        list.add(157);
        list.add(WatchType.Wrist);
        list.add("1007");
        list.add(6);
        list.add(370.0);
        list.add(true);
        list.add("Men, waterproof, number, depth gauge, thermometer, arab + roman numerals");
        quartz = new Quartz();
    }

    @Test
    public void listNotEmptyCheck() {
        for (Object obj : list) {
            assertNotNull(obj);
        }
    }

    @Test
    public void verificationNumParameters() {
        Constructor[] constructors = quartz.getClass().getConstructors();
        for (Constructor con : constructors) {
            if (con.getParameterTypes().length > 0) {
                assertTrue(con.getParameterTypes().length == list.size());
            }
        }
    }

    @Test
    public void verificationNotNullObj() {
        Object[] obj = list.toArray();
        Constructor[] constructors = quartz.getClass().getConstructors();
        for (Constructor con : constructors) {
            if (con.getParameterTypes().length > 0) {
                try {
                    try {
                        assertNotNull(con.newInstance(obj));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void verificationTypeObj() {
        Object[] obj = list.toArray();
        Constructor[] constructors = quartz.getClass().getConstructors();
        for (Constructor con : constructors) {
            if (con.getParameterTypes().length > 0) {
                try {
                    try {
                        assertTrue(con.newInstance(obj).getClass().getSimpleName().equals(quartz.getClass().getSimpleName()));
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
