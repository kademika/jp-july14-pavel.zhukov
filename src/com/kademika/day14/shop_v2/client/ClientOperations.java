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

    //***************************************************************
    //    get
    //***************************************************************
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

    //***************************************************************
    //    update
    //***************************************************************
    public boolean updateClient(Client client, String fio, String email, String tel) {
        clientDB = new ClientDB();
        return clientDB.updateClient(client, fio, email, tel);
    }

    //***************************************************************
    //    insert
    //***************************************************************
    public boolean insertClient(Client client) {
        clientDB = new ClientDB();
        return clientDB.insertClient(client);
    }

    //***************************************************************
    //    delete
    //***************************************************************
    public boolean deleteClient(int id) {
        clientDB = new ClientDB();
        return clientDB.deleteClient(id);
    }
}
