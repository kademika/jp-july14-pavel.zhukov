package com.kademika.day11.f16to17fromday9.products;

public enum ComputerType {
    NOTEBOOK(0), PC(1), TABLETPC(2);
    private int id;

    private ComputerType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
