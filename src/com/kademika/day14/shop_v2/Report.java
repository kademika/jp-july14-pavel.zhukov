package com.kademika.day14.shop_v2;

import com.kademika.day14.shop_v2.domain.Transaction;
import com.kademika.day14.shop_v2.db.*;
import com.kademika.day14.shop_v2.transactions.TransactionOperations;
import com.kademika.day14.shop_v2.domain.Watch;

import java.util.ArrayList;

public class Report {
    //    private int[] productsOnWeek;
//    private DBSelect dbSelect;
    private DBConnection dbConnection;
    private TransactionOperations transactionOperations;

    public Report() {
//        productsOnWeek = new int[7];
//        dbSelect = new DBSelect(dbConnection);
        transactionOperations = new TransactionOperations();
    }

    private boolean isNullCopy(Watch watch) {
        if (watch.getId().equals("0000")) {
            return true;
        }
        return false;
    }

    public boolean isNullStr(String str) {
        if (str.equals("Null copy") || str.equals("Null transaction")) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getPriceProducts(ArrayList<Watch> watches) {
        int p = 0;
        ArrayList<String> info = new ArrayList<>();
        for (Watch w : watches) {
            if (!isNullCopy(w)) {
                info.add("ID: " + w.getId() + "	Watch: "
                        + w.getName() + "	type: "
                        + w.getType() + "	price: "
                        + w.getPrice() + " $");
                p++;
            } else {
                info.add("Null copy");
            }
        }
        System.out.println("In the shop_v2 found " + p + " types of products");
        System.out.println("-----------------------------------------");
        return info;
    }

    public ArrayList<String> getNumberProducts(ArrayList<Watch> watches) {
        int count = 0;
        ArrayList<String> info = new ArrayList<>();
        for (Watch w : watches) {
            if (!isNullCopy(w)) {
                info.add("ID: " + w.getId() + "	Watch: "
                        + w.getName() + "	type: "
                        + w.getType() + "	number: "
                        + w.getNumber());
                count += w.getNumber();
            } else {
                info.add("Null copy");
            }
        }
        System.out.println("In the shop_v2 found " + count + " products");
        System.out.println("-----------------------------------------");
        return info;
    }
//
//    public ArrayList<String> printDeleteProducts(ArrayList<Watch> deleteWatches) {
//        ArrayList<String> info = new ArrayList<>();
//        for (Watch w : deleteWatches) {
//            if (!isNullCopy(w)) {
//                info.add("ID: " + w.getId() + "	Watch: "
//                        + w.getName() + "	type: "
//                        + w.getType() + "	price: "
//                        + w.getPrice() + " $");
//            }
//        }
//        return info;
//    }

    public ArrayList<String> getTransactions() {
        ArrayList<Transaction> transactions = transactionOperations.getAllTrans();
        double oneDayTotalPrice = 0;
        int oneDayProducts = 0;
        if (!transactions.isEmpty()) {
            ArrayList<String> info = new ArrayList<>();
            for (Transaction tr : transactions) {
                if (tr != null) {
                    oneDayProducts += tr.getNumber();
                    oneDayTotalPrice += tr.getTotalPrice();
                    info.add("ID: " + tr.getNumTransaction()
                            + " date: " + tr.getDate()
                            + "	Client: "
                            + tr.getClient().getFio()
                            + "	ID watch: " + tr.getIdProd()
                            + "	Watch: " + tr.getName()
                            + " price: " + tr.getPrice()
                            + "	number: " + tr.getNumber()
                            + "	total: " + tr.getTotalPrice()
                            + " seller: "
                            + tr.getSeller().getFio());
                } else {
                    info.add("Null transaction");
                }
            }
//            System.out.println("Total:	In this day sold " + oneDayProducts
//                    + " pcs. of watches for the total amount "
//                    + oneDayTotalPrice + " $");
//            System.out.println("-----------------------------------------");
            return info;
        } else {
            ArrayList<String> info = new ArrayList<>();
            info.add("No transactions");
            return info;
        }
    }

//    public ArrayList<String>[] getWeekTransactions(ArrayList<transaction>[] transactionsPerWeek) {
//        int weekProducts = 0;
//        double weekTotalPrice = 0;
//        ArrayList<String>[] infoArray = new ArrayList[7];
//        for (int i = 0; i < 7; i++) {
//            ArrayList<String> info = new ArrayList<>();
//            if (!transactionsPerWeek[i].isEmpty()) {
//                for (transaction tr : transactionsPerWeek[i]) {
//                    if (tr != null) {
//                        weekProducts += tr.getNumber();
//                        weekTotalPrice += tr.getTotalPrice();
//                        info.add("ID: "
//                                + tr.getNumTransaction()
//                                + "	Client: "
//                                + tr.getClient().getFio()
//                                + "	ID watch: "
//                                + tr.getIdProd()
//                                + "	Watch: "
//                                + tr.getName()
//                                + " price: "
//                                + tr.getPrice()
//                                + "	number: "
//                                + tr.getNumber()
//                                + "	total: "
//                                + tr.getTotalPrice()
//                                + " seller: "
//                                + tr.getSeller().getFio());
//                    } else {
//                        info.add("Null transaction");
//                    }
//                }
//            }
//            infoArray[i] = info;
//        }
//        System.out.println("Total:	In this week sold " + weekProducts
//                + " pcs. of watches for the total amount " + weekTotalPrice
//                + " $");
//        System.out.println("-----------------------------------------");
//        return infoArray;
//    }

//    public String getNumWeekSoldProducts(ArrayList<transaction>[] transactionsPerWeek) {
//        String tmp = "";
//        for (int i = 0; i < transactionsPerWeek.length; i++) {
//            if (transactionsPerWeek[i] != null) {
//                for (transaction tr : transactionsPerWeek[i]) {
//                    if (tr != null) {
//                        productsOnWeek[i] += tr.getNumber();
//                    }
//                }
//                tmp += " " + productsOnWeek[i];
//            }
//        }
//        return "Number of watches sold per week " + tmp;
//    }

//    public String getNumWeekTransactions(ArrayList<transaction>[] transactionsPerWeek) {
//        String tmp = "";
//        int count = 0;
//        for (int i = 0; i < transactionsPerWeek.length; i++) {
//            if (transactionsPerWeek[i] != null) {
//                for (transaction tr : transactionsPerWeek[i]) {
//                    if (tr != null) {
//                        count++;
//                    }
//                }
//                tmp += " " + count;
//            }
//            count = 0;
//        }
//        return "Number of purchases per week " + tmp;
//    }

    public ArrayList<String> getCategoryProducts(ArrayList<Watch> watches) {
        ArrayList<String> info = new ArrayList<>();
        for (Watch w : watches) {
            info.add(w.getInfo());
        }
        return info;
    }

    public String getLastTransaction(Shop shop) {
        dbConnection = new DBConnection();
//        dbSelect = new DBSelect(dbConnection);
        ArrayList<Transaction> transactions = transactionOperations.getAllTrans();
        Transaction tmp = transactions.get(transactions.size() - 1);
//
//        int k = -1;
        String res;
//        for (int i = tmp.length - 1; i >= 0; i--) {
//            if (tmp[i] == null) {
//                k = i;
//            }
//        }
        res = "ID:" + tmp.getNumTransaction() + " " + tmp.getDate() + " "
                + tmp.getClient().getFio() + " ID watch:"
                + tmp.getIdProd() + " " + tmp.getName()
                + " Price: " + tmp.getPrice() + " Number: "
                + tmp.getNumber() + " Total: "
                + tmp.getTotalPrice() + "    "
                + tmp.getSeller().getFio();
        return res;
    }
}
