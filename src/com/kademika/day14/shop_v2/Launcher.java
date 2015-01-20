package com.kademika.day14.shop_v2;

import com.kademika.day14.shop_v2.db.DBConnection;

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
//        dbInsert.insertPersonal(new Personal("Shevchuk Oksana11", 26, "oksana@shop_v2.ru"));
//        dbInsert.insertWatch(new Quartz("Casio EQW-A1000DB-1A", 193, WatchType.Wrist, "1001", 4, 500, true, "Men, waterproof, stopwatch, countdown timer, perpetual calendar"));
//        dbDelete.deleteWatch("1012");
//
        Shop shop = new Shop(dbConnection);
//
//        getTransactionsList(shop_v2);
        ShopUI shopUI = new ShopUI(shop, report, dbConnection);


//        dbUpdate.updateClient(new Client("Sidorov Semen1", "semen@yandex.ru", "89198526344"), "Sidorov Semen", "", "89198526355");
//        dbUpdate.updateWatch(new Mechanic("ORIENT BNQ1H003C", 78, WatchType.Wrist, "0006", 4, 380, 3, "Women, waterproof, calendar, none numerals"),
//                "", 84, WatchType.None, 6, 0, true, 0, "");
        dbConnection.closeConnection();
    }
}
