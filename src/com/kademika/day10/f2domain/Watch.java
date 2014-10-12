package com.kademika.day10.f2domain;

public abstract class Watch {
    public String name;
    public int weight;
    public WatchType type;

    public Watch(String name, int weight, WatchType wt) {
        this.name = name;
        this.weight = weight;
        this.type = wt;
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
}
