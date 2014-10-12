package com.kademika.day11.f16to17fromday9.products;

public enum VideoType {
    TV(0), PLAYER(1), PROJECTOR(2);

    private int id;

    private VideoType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
