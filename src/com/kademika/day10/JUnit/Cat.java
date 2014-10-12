package com.kademika.day10.junit;

/**
 * Created by Жуков Павел on 01.07.14.
 */
public class Cat {
    private String name;
    private double weight;
    private boolean isHungry = true;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean isHungry) {
        this.isHungry = isHungry;
    }
}
