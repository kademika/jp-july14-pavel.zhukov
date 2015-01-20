package com.kademika.day14.shop_v2;

import com.kademika.day14.shop_v2.watches.Mechanic;
import com.kademika.day14.shop_v2.watches.Quartz;
import com.kademika.day14.shop_v2.watches.Watch;
import com.kademika.day14.shop_v2.watches.WatchType;

import java.util.ArrayList;

public class TestData {

    public ArrayList<Watch> arrayWatches() {
        ArrayList<Watch> watches = new ArrayList<>();

        watches.add(new Mechanic("ORIENT DE00004D", 98, WatchType.Pocket, "0001", 4, 500, 3, "Men, waterproof, calendar"));
        watches.add(new Mechanic("Thomas Earnshaw ES-8008-04", 103, WatchType.Wrist, "0002", 3, 600, 5, "Men, waterproof, date, caseback"));
        watches.add(new Mechanic("Seiko SRP257J", 88, WatchType.Wrist, "0003", 5, 450, 3, "Men, waterproof, roman numerals"));
        watches.add(new Mechanic("Invicta 1827", 85, WatchType.Wrist, "0004", 6, 470, 3, "Women, waterproof, none numerals"));
        watches.add(new Mechanic("Candino C4479_1", 95, WatchType.Wrist, "0005", 3, 550, 4, "Men, waterproof, calendar, day of the week"));
        watches.add(new Mechanic("ORIENT BNQ1H003C", 78, WatchType.Wrist, "0006", 4, 380, 3, "Women, waterproof, calendar, none numerals"));
        watches.add(new Mechanic("ORIENT EM5J00SW", 93, WatchType.Wrist, "0007", 7, 200, 3, "Men, waterproof, none numerals"));
        watches.add(new Mechanic("ORIENT DH01002W", 90, WatchType.Wrist, "0008", 5, 320, 4, "Men, waterproof, day of the week"));
        watches.add(new Mechanic("Seiko SSA051K", 100, WatchType.Wrist, "0009", 6, 530, 4, "Men, waterproof, compass, none numerals"));
        watches.add(new Mechanic("Tissot T95.1.483.51", 110, WatchType.Pocket, "0010", 4, 600, 3, "Men, waterproof, date"));

        watches.add(new Quartz("Casio EQW-A1000DB-1A", 193, WatchType.Wrist, "1001", 4, 500, true, "Men, waterproof, stopwatch, countdown timer, perpetual calendar"));
        watches.add(new Quartz("Casio EQW-A1200DB-1A", 156, WatchType.Wrist, "1002", 6, 600, true, "Men, waterproof, stopwatch, countdown timer, perpetual calendar, compass"));
        watches.add(new Quartz("GUESS W18547G1", 168, WatchType.Wrist, "1003", 7, 300, true, "Men, waterproof, calendar"));
        watches.add(new Quartz("Suunto Elementum Terra n/brown", 149, WatchType.Wrist, "1004", 4, 650, true, "Men, waterproof, altimeter, barometer, thermometer, compass, power reserve indicator, alarm clock"));
        watches.add(new Quartz("GUESS W15071G3", 153, WatchType.Wrist, "1005", 6, 270, true, "Men, waterproof, date, day of week"));
        watches.add(new Quartz("Romanson PL3208HMB(BK)", 169, WatchType.Wrist, "1006", 4, 480, true, "Men, waterproof, stopwatch, date"));
        watches.add(new Quartz("Timex T2N958", 157, WatchType.Wrist, "1007", 6, 370, true, "Men, waterproof, number, depth gauge, thermometer, arab + roman numerals"));
        watches.add(new Quartz("Skagen 901XLBLB", 193, WatchType.Wrist, "1008", 4, 490, true, "Men, waterproof, date, stopwatch, moon phase indicator "));
        watches.add(new Quartz("Diesel DZ7285", 162, WatchType.Wrist, "1009", 5, 450, true, "Men, waterproof, second time zone, arab numerals"));
        watches.add(new Quartz("Suunto Core Alu Pure White", 170, WatchType.Wrist, "1010", 7, 460, true, "Men, waterproof, depth gauge, altimeter, barometer, thermometer, compass"));

        return watches;
    }

    public ArrayList<Client> arrayClients() {
        ArrayList<Client> clients = new ArrayList<>();

        clients.add(new Client("Ivanov Ivan", "ivan@yandex.ru", "530215"));
        clients.add(new Client("Petrov Petr", "petr@yandex.ru", "567425"));
        clients.add(new Client("Sidorov Semen", "semen@yandex.ru",
                "89198526344"));
        clients.add(new Client("Litvin Andrey", "andrey@mail.ru",
                "89422312545"));
        clients.add(new Client("Smirnov Boris", "boris@gmail.com", "357446"));

        return clients;
    }

    public ArrayList<Personal> arrayPersonal() {
        ArrayList<Personal> personal = new ArrayList<>();

        personal.add(new Personal("Shevchuk Oksana", 25, "oksana@shop_v2.ru"));
        personal.add(new Personal("Vinnikova Lilia", 32, "lilia@shop_v2.ru"));
        personal.add(new Personal("Ovchinnik Nikolay", 28, "nikolay@shop_v2.ru"));
        personal.add(new Personal("Reznikov Stas", 23, "stats@shop_v2.ru"));
        personal.add(new Personal("Pahmutov Evgeniy", 32, "evgeniy@shop_v2.ru"));

        return personal;
    }

}
