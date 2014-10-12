package com.kademika.day10.f2domain;


public class Mechanic extends Watch {
    private int numberArrows;
    private String additionalDials;

    public Mechanic(String name, int weight, WatchType wt, int numberArrows, String additionalDials) {
        super(name, weight, wt);
        this.numberArrows = numberArrows;
        this.additionalDials = additionalDials;
    }

    public int getNumberArrows() {
        return numberArrows;
    }

    public void setNumberArrows(int numberArrows) {
        this.numberArrows = numberArrows;
    }

    public String getAdditionalDials() {
        return additionalDials;
    }

    public void setAdditionalDials(String additionalDials) {
        this.additionalDials = additionalDials;
    }
}
