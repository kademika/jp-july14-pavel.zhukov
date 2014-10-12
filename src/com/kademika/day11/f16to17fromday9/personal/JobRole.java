package com.kademika.day11.f16to17fromday9.personal;

public enum JobRole {
    CONSULTANT(0), PAYMASTER(1);
    private int id;

    private JobRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
