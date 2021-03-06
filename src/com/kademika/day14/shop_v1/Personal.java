package com.kademika.day14.shop_v1;

import java.io.Serializable;

/**
 * Created by Admin on 05.07.14.
 */
public class Personal implements Serializable {
    private String fio;
    private String email;
    private int age;

    public Personal(String fio, int age) {
        this(fio, age, "info@shop_v1.com");
    }

    public Personal(String fio, int age, String email) {
        this.fio = fio;
        this.age = age;
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}