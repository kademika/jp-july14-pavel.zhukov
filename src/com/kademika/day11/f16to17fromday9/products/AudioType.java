package com.kademika.day11.f16to17fromday9.products;

public enum AudioType {
    LOUDSPEAKERS(0), STEREO(1), HEADPHONES(2), MICROPHONE(3);

    private int id;

    private AudioType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
