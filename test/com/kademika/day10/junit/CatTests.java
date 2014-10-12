package com.kademika.day10.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import com.kademika.day10.junit.*;

/**
 * Created by Admin on 01.07.14.
 */
@RunWith(JUnit4.class)
public class CatTests {

    private com.kademika.day10.junit.Cat cat;

    @Before
    public void init() {
        cat = new com.kademika.day10.junit.Cat();
    }

    @Test
    public void checkCatNameDefaultValue() {
        assertNull("Default name of Cat should be null", cat.getName());
    }

    @Test
    public void checkCatWeightDefaultValue() {
        assertTrue("Default name of Cat should be null", cat.getWeight() == 0.0);
    }

    @Test
    public void checkCatIsHungryDefaultValue() {
        assertTrue("Default name of Cat should be null", cat.isHungry());
    }

    @Test
    public void checkCatSetName() {
        String name = "Mikky";
        cat.setName(name);
        assertEquals(name, cat.getName());
    }
}
