package com.kademika.day10.shop;

import com.kademika.day10.shop.watches.Quartz;
import com.kademika.day10.shop.watches.Watch;
import com.kademika.day10.shop.watches.WatchType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TransactionTests {

    private Transaction tr;
    private Client client;
    private Watch watch;
    private int num;
    private Personal seller;
    private double total = 0.0;

    @Before
    public void init() {
        client = new Client("Ivanov Ivan", "ivan@yandex.ru", "530215");
        watch = new Quartz("Diesel DZ7285", 162, WatchType.Wrist, "1009", 5, 450, true, "Men, waterproof, second time zone, arab numerals");
        num = 1;
        seller = new Personal("Shevchuk Oksana", 25, "oksana@shop.ru");
        tr = new Transaction(client, watch, num, seller);
    }

    @Test
    public void checkWatchNumber() {
        assertTrue(watch.getNumber() > 0);
    }

    @Test
    public void checkPrice() {
        assertTrue(watch.getPrice() > 0.0);
    }

    @Test
    public void checkNum() {
        assertTrue(num > 0);
    }

    @Test
    public void checkDiscount() {
        total = watch.getPrice() * num;
        if (total >= 1000) {
            total = total * 0.9;
            tr.setTotalPrice(total);
            assertEquals(total, tr.getTotalPrice() * 0.9);
        } else if (total >= 500 && total < 1000) {
            total = total * 0.95;
            tr.setTotalPrice(total);
            assertEquals(total, tr.getTotalPrice() * 0.95);
        }
    }

    @Test
    public void checkTotalPrice() {
        total = watch.getPrice() * num;
        assertTrue(tr.getTotalPrice() == total);
    }
}
