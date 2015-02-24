package com.kademika.day14.shop_v2.client;

import com.kademika.day14.shop_v2.domain.Client;

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
            clientDB.closeClientDB();
            return true;
        }
        clientDB.closeClientDB();
        return false;
    }

    //***************************************************************
    //    get
    //***************************************************************
    public int getIdClientByParam(String fio, String email) {
        int k = 0;
        clientDB = new ClientDB();
        k = clientDB.selectIdClientByParam(fio, email);
        clientDB.closeClientDB();
        return k;
    }

    public Client getClientById(int id) {
        Client client = null;
        clientDB = new ClientDB();
        client = clientDB.selectClientById(id);
        clientDB.closeClientDB();
        return client;
    }

    public ArrayList<Client> getAllClients() {
        ArrayList<Client> clients = null;
        clientDB = new ClientDB();
        clients = clientDB.selectClients();
        clientDB.closeClientDB();
        return clients;
    }

    //***************************************************************
    //    update
    //***************************************************************
    public boolean updateClient(Client client, String fio, String email, String tel) {
        boolean bool;
        clientDB = new ClientDB();
        bool = clientDB.updateClient(client, fio, email, tel);
        clientDB.closeClientDB();
        return bool;
    }

    //***************************************************************
    //    insert
    //***************************************************************
    public boolean insertClient(Client client) {
        boolean bool;
        clientDB = new ClientDB();
        bool = clientDB.insertClient(client);
        clientDB.closeClientDB();
        return bool;
    }

    //***************************************************************
    //    delete
    //***************************************************************
    public boolean deleteClient(int id) {
        boolean bool;
        clientDB = new ClientDB();
        bool = clientDB.deleteClient(id);
        clientDB.closeClientDB();
        return bool;
    }
}
