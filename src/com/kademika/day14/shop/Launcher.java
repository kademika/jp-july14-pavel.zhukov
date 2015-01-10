package com.kademika.day14.shop;

import com.kademika.day14.shop.watches.Watch;

import java.util.ArrayList;

public class Launcher {


    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        DBSelect dbSelect = new DBSelect(dbConnection.getConnection());

//        TestData td = new TestData();
//        Report report = new Report();
//
        ArrayList<Client> clients = dbSelect.arrayClients();
        ArrayList<Personal> personal = dbSelect.arrayPersonal();
        ArrayList<Watch> watches = dbSelect.arrayWatches();
//
//        Shop shop = new Shop(clients, personal, watches);
//
//        getTransactionsList(shop);
        dbConnection.closeConnection();
    }

    public static void getTransactionsList(com.kademika.day14.shop.Shop shop) {
        shop.setDay(com.kademika.day14.shop.Week.MONDAY.getId());
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(1), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
                shop.getPersonal().get(1));

        shop.setDay(com.kademika.day14.shop.Week.TUESDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(11), 2,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(13), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(7), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(15), 1,
                shop.getPersonal().get(0));

        shop.setDay(com.kademika.day14.shop.Week.WEDNESDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(6), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(17), 1,
                shop.getPersonal().get(2));

        shop.setDay(com.kademika.day14.shop.Week.THURSDAY.getId());
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(19), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(14), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(9), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(6), 1,
                shop.getPersonal().get(1));

        shop.setDay(com.kademika.day14.shop.Week.FRIDAY.getId());
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(10), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(13), 1,
                shop.getPersonal().get(3));

        shop.setDay(com.kademika.day14.shop.Week.SATURDAY.getId());
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(16), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(8), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(4), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(9), 1,
                shop.getPersonal().get(4));

        shop.setDay(com.kademika.day14.shop.Week.SUNDAY.getId());
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(11), 1,
                shop.getPersonal().get(0));
        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
                shop.getPersonal().get(1));
        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(12), 1,
                shop.getPersonal().get(2));
        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(1), 1,
                shop.getPersonal().get(3));
        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(0), 1,
                shop.getPersonal().get(4));
        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(0), 1,
                shop.getPersonal().get(0));
    }

}
