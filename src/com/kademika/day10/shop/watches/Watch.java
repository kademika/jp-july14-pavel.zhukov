package com.kademika.day10.shop.watches;

public abstract class Watch {
    protected String name;
    protected int weight;
    protected WatchType type;
    protected String id;
    protected int number;
    protected double price;

    public Watch() {
    }

    public Watch(String name, int weight, WatchType wt, String id, int number, double price) {
        this.name = name;
        this.weight = weight;
        this.type = wt;
        this.id = id;
        this.number = number;
        this.price = price;
    }

    public String getInfo() {
        return id + " Watch: " + name + "; weight: " + weight + "g; type: " + type + "; number: " + number + "; price: " + price + "$";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public WatchType getType() {
        return type;
    }

    public void setType(WatchType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
