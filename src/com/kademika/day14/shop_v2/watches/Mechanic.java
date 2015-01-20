package com.kademika.day14.shop_v2.watches;

public class Mechanic extends Watch {
    private int numberArrows;
    private String additionalDials;

    public Mechanic() {
    }

    public Mechanic(String name, int weight, WatchType wt, String id, int number, double price, int numberArrows, String additionalDials) {
        super(name, weight, wt, id, number, price);
        this.numberArrows = numberArrows;
        this.additionalDials = additionalDials;
    }

    @Override
    public String getInfo() {
        return id + " Mechanic watch: " + name + "; weight: " + weight + "g; type: " + type + "; numberArrows: "
                + numberArrows + "; additionalDials: " + additionalDials + ";  number: "
                + number + "; price: " + price + "$";
    }

    public String getAdditionalDials() {
        return additionalDials;
    }

    public void setAdditionalDials(String additionalDials) {
        this.additionalDials = additionalDials;
    }

    public int getNumberArrows() {
        return numberArrows;
    }

    public void setNumberArrows(int numberArrows) {
        this.numberArrows = numberArrows;
    }
}
