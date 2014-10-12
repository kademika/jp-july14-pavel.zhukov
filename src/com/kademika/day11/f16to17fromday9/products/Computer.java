package com.kademika.day11.f16to17fromday9.products;

import com.kademika.day11.f16to17fromday9.Department;

public class Computer extends Product {
    private String processor;
    private double cpufrequency;
    private String ramtype;
    private int ram;
    private String hdd;
    private int hddcapacity;
    private boolean wifi;
    private String os;
    private ComputerType comptype;

    public Computer(String id, String model, String producer, String character,
                    String color, ComputerType comptype, String processor,
                    double cpufrequency, String ramtype, int ram, String hdd,
                    int hddcapacity, String os, int number, double price) {
        super(id, model, producer, character, color, number, price);
        dept = Department.COMPUTERS;
        this.comptype = comptype;
        this.processor = processor;
        this.cpufrequency = cpufrequency;
        this.ramtype = ramtype;
        this.ram = ram;
        this.hdd = hdd;
        this.hddcapacity = hddcapacity;
        this.os = os;
        this.wifi = setWifi();
    }

    @Override
    public String getInfo() {
        return id + " Computer: " + model + ", type: " + comptype
                + ", made in " + producer + ", basic characteristics: "
                + character + ", \ncolor: " + color + ", processor: "
                + processor + " " + cpufrequency + "GHz, ram: " + ramtype + " "
                + ram + "Gb, hdd: " + hdd + " " + hddcapacity + "Gb, \nwifi: "
                + wifi + ", OS: " + os + ", department: " + dept + "\nnumber: "
                + number + " \nprice: " + price + " rub";
    }

    public String getProcessor() {
        return processor;
    }

    public double getCpufrequency() {
        return cpufrequency;
    }

    public String getRamtype() {
        return ramtype;
    }

    public double getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    public int getHddcapacity() {
        return hddcapacity;
    }

    public boolean isWifi() {
        return wifi;
    }

    public ComputerType getComptype() {
        return comptype;
    }

    private boolean setWifi() {
        if (comptype == ComputerType.PC) {
            return false;
        }
        return true;
    }
}
