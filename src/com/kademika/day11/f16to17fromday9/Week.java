package com.kademika.day11.f16to17fromday9;

public enum Week {
    MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

    private int id;

    private Week(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
