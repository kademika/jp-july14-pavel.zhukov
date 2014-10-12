package com.kademika.day10.junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HouseWifeTests {
    private Cat[] cats;
    private HouseWife houseWife;

    @Before
    public void init() {
        houseWife = new HouseWife();
        cats = new Cat[2];

        cats[0] = new Cat();
        cats[0].setName("Mikky1");

        cats[1] = new Cat();
        cats[1].setName("Mikky2");
    }

    @Test
    public void testFeedCats() {
        houseWife.feed(cats);
        for (Cat cat : cats) {
            assertFalse(cat.isHungry());
        }
    }

    @Test(expected = CatNotHungryException.class)
    public void testCatNotHungryException() {
        cats[1].setName("Mikky3");
        cats[1].setHungry(false);

        houseWife.feed(cats);
        for (Cat cat : cats) {
            assertFalse(cat.isHungry());
        }
    }
}
