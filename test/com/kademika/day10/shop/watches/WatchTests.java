package com.kademika.day10.shop.watches;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class WatchTests {

    private Mechanic mechanic;
    private String name;
    private int weight;
    private WatchType type;
    private String id;
    private int number;
    private double price;
    private int arrowsNum;
    private String info;

    @Before
    public void init() {
        name = "WatchName";
        weight = 100;
        type = WatchType.Wrist;
        id = "0000";
        number = 1;
        price = 10.0;
        arrowsNum = 2;
        info = "Other";
        mechanic = new Mechanic(name, weight, type, id, number, price, arrowsNum, info);
    }

    @Test
    public void checkNameValue() {
        assertEquals(name, mechanic.getName());
    }

    @Test
    public void checkWeightValue() {
        assertEquals(weight, mechanic.getWeight());
    }

    @Test
    public void checkType() {
        assertTrue(mechanic.getType() == type);
    }

    @Test
    public void checkIdValue() {
        assertEquals(id, mechanic.getId());
    }

    @Test
    public void checkNumber() {
        assertTrue(mechanic.getNumber() == number);
    }

    @Test
    public void checkArrows() {
        assertTrue(mechanic.getNumberArrows() == arrowsNum);
    }

    @Test
    public void checkInfo() {
        assertEquals(info, mechanic.getAdditionalDials());
    }
}
