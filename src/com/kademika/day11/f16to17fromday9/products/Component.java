package com.kademika.day11.f16to17fromday9.products;

import com.kademika.day11.f16to17fromday9.*;

public class Component extends Product {

    public Component(String id, String model, String producer,
                     String character, String color, int number, double price) {
        super(id, model, producer, character, color, number, price);
        dept = Department.COMPONENTS;
    }

    @Override
    public String getInfo() {
        return id + " Component: " + model + ", made in "
                + producer + ", basic characteristics: " + character
                + ", color: " + color + ", department: " + dept + " \nnumber: "
                + number + " \nprice: " + price;
    }
}
