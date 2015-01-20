package com.kademika.day10.shop;

import com.kademika.day10.shop.watches.Watch;

import java.util.ArrayList;

public class Report {
    private int[] productsOnWeek;

    public Report() {
        productsOnWeek = new int[7];
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
        System.out.println("In the shop_v1 found " + p + " types of products");
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
        System.out.println("In the shop_v1 found " + count + " products");
        System.out.println("-----------------------------------------");
        return info;
    }

    public ArrayList<String> printDeleteProducts(ArrayList<Watch> deleteWatches) {
        ArrayList<String> info = new ArrayList<>();
        for (Watch w : deleteWatches) {
            if (!isNullCopy(w)) {
                info.add("ID: " + w.getId() + "	Watch: "
                        + w.getName() + "	type: "
                        + w.getType() + "	price: "
                        + w.getPrice() + " $");
            }
        }
        return info;
    }

    public ArrayList<String> getTransactions(ArrayList<Transaction> transactions) {
        double oneDayTotalPrice = 0;
        int oneDayProducts = 0;
        if (!transactions.isEmpty()) {
            ArrayList<String> info = new ArrayList<>();
            for (Transaction tr : transactions) {
                if (tr != null) {
                    oneDayProducts += tr.getNumber();
                    oneDayTotalPrice += tr.getTotalPrice();
                    info.add("ID: " + tr.getNumTransaction()
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
            System.out.println("Total:	In this day sold " + oneDayProducts
                    + " pcs. of watches for the total amount "
                    + oneDayTotalPrice + " $");
            System.out.println("-----------------------------------------");
            return info;
        } else {
            ArrayList<String> info = new ArrayList<>();
            info.add("No transactions");
            return info;
        }
    }

    public ArrayList<String>[] getWeekTransactions(ArrayList<Transaction>[] transactionsPerWeek) {
        int weekProducts = 0;
        double weekTotalPrice = 0;
        ArrayList<String>[] infoArray = new ArrayList[7];
        for (int i = 0; i < 7; i++) {
            ArrayList<String> info = new ArrayList<>();
            if (!transactionsPerWeek[i].isEmpty()) {
                for (Transaction tr : transactionsPerWeek[i]) {
                    if (tr != null) {
                        weekProducts += tr.getNumber();
                        weekTotalPrice += tr.getTotalPrice();
                        info.add("ID: "
                                + tr.getNumTransaction()
                                + "	Client: "
                                + tr.getClient().getFio()
                                + "	ID watch: "
                                + tr.getIdProd()
                                + "	Watch: "
                                + tr.getName()
                                + " price: "
                                + tr.getPrice()
                                + "	number: "
                                + tr.getNumber()
                                + "	total: "
                                + tr.getTotalPrice()
                                + " seller: "
                                + tr.getSeller().getFio());
                    } else {
                        info.add("Null transaction");
                    }
                }
            }
            infoArray[i] = info;
        }
        System.out.println("Total:	In this week sold " + weekProducts
                + " pcs. of watches for the total amount " + weekTotalPrice
                + " $");
        System.out.println("-----------------------------------------");
        return infoArray;
    }

    public String getNumWeekSoldProducts(ArrayList<Transaction>[] transactionsPerWeek) {
        String tmp = "";
        for (int i = 0; i < transactionsPerWeek.length; i++) {
            if (transactionsPerWeek[i] != null) {
                for (Transaction tr : transactionsPerWeek[i]) {
                    if (tr != null) {
                        productsOnWeek[i] += tr.getNumber();
                    }
                }
                tmp += " " + productsOnWeek[i];
            }
        }
        return "Number of watches sold per week " + tmp;
    }

    public String getNumWeekTransactions(ArrayList<Transaction>[] transactionsPerWeek) {
        String tmp = "";
        int count = 0;
        for (int i = 0; i < transactionsPerWeek.length; i++) {
            if (transactionsPerWeek[i] != null) {
                for (Transaction tr : transactionsPerWeek[i]) {
                    if (tr != null) {
                        count++;
                    }
                }
                tmp += " " + count;
            }
            count = 0;
        }
        return "Number of purchases per week " + tmp;
    }

    public ArrayList<String> getCategoryProducts(ArrayList<Watch> watches) {
        ArrayList<String> info = new ArrayList<>();
        for (Watch w : watches) {
            info.add(w.getInfo());
        }
        return info;
    }

    public String getLastTransaction(ArrayList<Transaction>[] transactionsPerWeek,
                                     Shop shop) {
        Transaction[] tmp = (Transaction[]) transactionsPerWeek[shop.getDay() - 1].toArray();
        int k = -1;
        String res;
        for (int i = tmp.length - 1; i >= 0; i--) {
            if (tmp[i] == null) {
                k = i;
            }
        }
        res = "ID: " + tmp[k - 1].getNumTransaction() + "	Client: "
                + tmp[k - 1].getClient().getFio() + "	ID watch: "
                + tmp[k - 1].getIdProd() + "	Watch: " + tmp[k - 1].getName()
                + "     Price: " + tmp[k - 1].getPrice() + "	Number: "
                + tmp[k - 1].getNumber() + "	Total: "
                + tmp[k - 1].getTotalPrice() + "    Seller: "
                + tmp[k - 1].getSeller().getFio();
        return res;
    }
}
