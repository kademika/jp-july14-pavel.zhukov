package com.kademika.day14.shop_v2;


import com.kademika.day14.shop_v2.watches.Watch;

import java.io.Serializable;
import java.sql.Date;

public class Transaction implements Serializable {
    private int numTransaction;
    private Client client;
    private Watch watch;
    private int number;
    private Personal seller;
    private String name;
    private double price;
    private double totalPrice;
    private String idProd;
    private Date date;
    private int discount = 0;

    public Transaction(int id, Client client, Watch watch,
                       int number, Personal seller, Date date) {
        this.numTransaction = id;
        this.client = client;
        this.idProd = watch.getId();
        this.name = watch.getName();
        this.price = watch.getPrice();
        this.number = number;
        this.totalPrice = discount(watch, number);
        this.seller = seller;
        this.date = date;
    }

    public double discount(Watch watch, int num) {
        double total = watch.getPrice() * number;
//        System.out.println(total);
        if (total >= 1000) {
            total = total * 0.9;
//            System.out.println("Discount 10%");
            this.discount = 10;
        } else if (total >= 500 && total < 1000) {
            total = total * 0.95;
            this.discount = 5;
//            System.out.println("Discount 5%");
        }
//        System.out.println(total);
        return total;
    }

    public int getNumTransaction() {
        return numTransaction;
    }

    public void setNumTransaction(int numTransaction) {
        this.numTransaction = numTransaction;
    }

    public Client getClient() {
        return client;
    }

    public Watch getWatch() {
        return watch;
    }

    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    public int getNumber() {
        return number;
    }

    public Personal getSeller() {
        return seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getIdProd() {
        return idProd;
    }

    public Date getDate() {
        return date;
    }

    public int getDiscount() {
        return discount;
    }
}
