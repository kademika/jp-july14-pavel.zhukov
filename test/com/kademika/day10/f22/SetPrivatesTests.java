package com.kademika.day10.f22;

import static org.junit.Assert.*;

import com.kademika.day10.shop.watches.Mechanic;
import com.kademika.day10.shop.watches.Quartz;
import com.kademika.day10.shop.watches.WatchType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RunWith(JUnit4.class)
public class SetPrivatesTests {
    private Map<String, Object> map;
    private Mechanic mechanic;

    @Before
    public void init() {
        map = new HashMap<>();
        map.put("name", "Thomas Earnshaw ES-8008-04");
        map.put("weight", 103);
        map.put("type", WatchType.Wrist);
        map.put("id", "0002");
        map.put("number", 3);
        map.put("price", 600.0);
        map.put("additionalDials", "Men, waterproof, date, caseback");
        map.put("numberArrows", 5);
        mechanic = new Mechanic();
    }

    @Test
    public void verificationMapNotNull() {
        for (Map.Entry entry : map.entrySet()) {
            assertNotNull(entry.getValue());
        }
    }

    @Test
    public void verificationNumParameters() {
        Constructor[] constructors = mechanic.getClass().getConstructors();
        for (Constructor con : constructors) {
            if (con.getParameterTypes().length > 0) {
                assertTrue(map.size() == con.getParameterTypes().length);
            }
        }
    }

    @Test
    public void verificationValue() {
        try {
            ClassInfo.setPrivates(mechanic, map);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assertEquals(mechanic.getName(), map.get("name"));
    }
}
