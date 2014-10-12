package com.kademika.day11.tanks;

public enum Direction {
    UP(0), DOWN(1), LEFT(2), RIGHT(3), NONE(4);

    private int id;

    private Direction(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
