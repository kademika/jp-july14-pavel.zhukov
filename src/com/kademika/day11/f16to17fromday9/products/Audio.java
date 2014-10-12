package com.kademika.day11.f16to17fromday9.products;

import com.kademika.day11.f16to17fromday9.*;

public class Audio extends Product {

    private int totalPower;
    private String format;
    private String typeConnection;
    private AudioType at;

    public Audio(String id, String model, AudioType at, String producer,
                 String character, String color, int totalPower,
                 String typeConnection, String format, int number, double price) {
        super(id, model, producer, character, color, number, price);
        dept = Department.AUDIO;
        this.at = at;
        this.totalPower = totalPower;
        this.format = format;
        this.typeConnection = typeConnection;
    }

    @Override
    public String getInfo() {
        if (at == AudioType.STEREO) {
            return id + " Stereo system: " + model + ", made in "
                    + producer + ", basic characteristics: " + character
                    + ", \ncolor: " + color + ", total power: " + totalPower
                    + "W, \nformat: " + format + ", department: " + dept
                    + "\nnumber: " + number + " \nprice: " + price + " rub";
        } else if (at == AudioType.LOUDSPEAKERS) {
            return id + " Loudspeakers: " + model + ", made in "
                    + producer + ", basic characteristics: " + character
                    + ", \ncolor: " + color + ", total power: " + totalPower
                    + "W, department: " + dept + "\nnumber: " + number
                    + " \nprice: " + price + " rub";
        } else {
            return id + " Audio device: " + model + ", made in "
                    + producer + ", basic characteristics: " + character
                    + ", \ncolor: " + color + ", type connection: "
                    + typeConnection + ", \nformat: " + format
                    + ", department: " + dept + "\nnumber: " + number
                    + " \nprice: " + price + " rub";
        }
    }

    public int getTotalPower() {
        return totalPower;
    }

    public String getFormat() {
        return format;
    }

    public String getTypeConnection() {
        return typeConnection;
    }

    public AudioType getAt() {
        return at;
    }
}
