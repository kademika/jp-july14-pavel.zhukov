package com.kademika.day14.shop;

import com.kademika.day14.shop.db.DBConnection;

public class Launcher {


    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
//        DBSelect dbSelect = new DBSelect(dbConnection);
//        DBInsert dbInsert = new DBInsert(dbConnection);
//        DBDelete dbDelete = new DBDelete(dbConnection);
//        DBUpdate dbUpdate = new DBUpdate(dbConnection);

//        TestData td = new TestData();
        Report report = new Report(dbConnection);
//
//        ArrayList<Client> clients = dbSelect.selectClients();
//        ArrayList<Personal> personal = dbSelect.selectPersonal();
//        ArrayList<Watch> watches = dbSelect.selectWatches();
//        ArrayList<Transaction> transactions = dbSelect.selectTransactions();

//        dbInsert.insertClient(new Client("Kukushkin Ivan", "ivan@gmail.com", "557446"));
//        dbInsert.insertPersonal(new Personal("Shevchuk Oksana11", 26, "oksana@shop.ru"));
//        dbInsert.insertWatch(new Quartz("Casio EQW-A1000DB-1A", 193, WatchType.Wrist, "1001", 4, 500, true, "Men, waterproof, stopwatch, countdown timer, perpetual calendar"));
//        dbDelete.deleteWatch("1012");
//
        Shop shop = new Shop(dbConnection);
//
//        getTransactionsList(shop);
        ShopUI shopUI = new ShopUI(shop, report, dbConnection);


//        dbUpdate.updateClient(new Client("Sidorov Semen1", "semen@yandex.ru", "89198526344"), "Sidorov Semen", "", "89198526355");
//        dbUpdate.updateWatch(new Mechanic("ORIENT BNQ1H003C", 78, WatchType.Wrist, "0006", 4, 380, 3, "Women, waterproof, calendar, none numerals"),
//                "", 84, WatchType.None, 6, 0, true, 0, "");
        dbConnection.closeConnection();
    }

//    public static void getTransactionsList(com.kademika.day14.shop.Shop shop) {
//        shop.setDay(com.kademika.day14.shop.Week.MONDAY.getId());
//        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(1), 1,
//                shop.getPersonal().get(0));
//        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
//                shop.getPersonal().get(1));
//
//        shop.setDay(com.kademika.day14.shop.Week.TUESDAY.getId());
//        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(11), 2,
//                shop.getPersonal().get(2));
//        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(13), 1,
//                shop.getPersonal().get(3));
//        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(7), 1,
//                shop.getPersonal().get(4));
//        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(15), 1,
//                shop.getPersonal().get(0));
//
//        shop.setDay(com.kademika.day14.shop.Week.WEDNESDAY.getId());
//        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(6), 1,
//                shop.getPersonal().get(1));
//        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(17), 1,
//                shop.getPersonal().get(2));
//
//        shop.setDay(com.kademika.day14.shop.Week.THURSDAY.getId());
//        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(19), 1,
//                shop.getPersonal().get(3));
//        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(14), 1,
//                shop.getPersonal().get(4));
//        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(9), 1,
//                shop.getPersonal().get(0));
//        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(6), 1,
//                shop.getPersonal().get(1));
//
//        shop.setDay(com.kademika.day14.shop.Week.FRIDAY.getId());
//        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(10), 1,
//                shop.getPersonal().get(2));
//        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(13), 1,
//                shop.getPersonal().get(3));
//
//        shop.setDay(com.kademika.day14.shop.Week.SATURDAY.getId());
//        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(16), 1,
//                shop.getPersonal().get(4));
//        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(8), 1,
//                shop.getPersonal().get(0));
//        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(4), 1,
//                shop.getPersonal().get(1));
//        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(3), 1,
//                shop.getPersonal().get(2));
//        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
//                shop.getPersonal().get(3));
//        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(9), 1,
//                shop.getPersonal().get(4));
//
//        shop.setDay(com.kademika.day14.shop.Week.SUNDAY.getId());
//        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(11), 1,
//                shop.getPersonal().get(0));
//        shop.setTransaction(shop.getClients().get(3), shop.getWatches().get(17), 1,
//                shop.getPersonal().get(1));
//        shop.setTransaction(shop.getClients().get(4), shop.getWatches().get(12), 1,
//                shop.getPersonal().get(2));
//        shop.setTransaction(shop.getClients().get(1), shop.getWatches().get(1), 1,
//                shop.getPersonal().get(3));
//        shop.setTransaction(shop.getClients().get(2), shop.getWatches().get(0), 1,
//                shop.getPersonal().get(4));
//        shop.setTransaction(shop.getClients().get(0), shop.getWatches().get(0), 1,
//                shop.getPersonal().get(0));
//    }

}
