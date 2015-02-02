package com.kademika.day14.shop_v2.client;

import java.io.Serializable;

public class Client implements Serializable {
    private String fio;
    private String email;
    private String tel;
    private int id;

    public Client(String fio, String email, String tel) {
        this.fio = fio;
        this.email = email;
        this.tel = tel;
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
