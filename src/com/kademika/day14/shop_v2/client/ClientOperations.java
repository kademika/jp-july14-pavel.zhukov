package com.kademika.day14.shop_v2.client;

import java.util.ArrayList;

/**
 * Created by Admin on 01.02.2015.
 */
public class ClientOperations {
    private ClientDB clientDB;

    public ClientOperations() {

    }

    public boolean checkClientById(int id) {
        clientDB = new ClientDB();
        if (clientDB.selectClientById(id) != null) {
            return true;
        }
        return false;
    }

    public int getIdClientByParam(String fio, String email) {
        clientDB = new ClientDB();
        return clientDB.selectIdClientByParam(fio, email);
    }

    public Client getClientById(int id) {
        clientDB = new ClientDB();
        return clientDB.selectClientById(id);
    }

    public ArrayList<Client> getAllClients() {
        clientDB = new ClientDB();
        return clientDB.selectClients();
    }
}
