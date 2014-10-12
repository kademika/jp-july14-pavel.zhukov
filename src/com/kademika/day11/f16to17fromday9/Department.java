package com.kademika.day11.f16to17fromday9;

public enum Department {
    COMPUTERS(0), VIDEO(1), AUDIO(2), PHONES(3), COMPONENTS(4);

    private int id;

    private Department(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
