package com.kademika.day14.shop_v2.client;

import com.kademika.day14.shop_v2.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Admin on 26.01.2015.
 */
public class ClientDB {
    private Connection connection;
    private DBConnection dbConnection;

    public ClientDB() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    //***************************************************************************
    //    delete Client
    //***************************************************************************
    public boolean deleteClient(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM client where id=?");
            pst.setInt(1, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to delete data from the database.");
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        closeClientDB();
        return true;
    }

    //***************************************************************************
    //    insert Client
    //***************************************************************************
    public boolean insertClient(Client client) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO client (id, fio, email, tel) VALUES (last_insert_id(), ?, ?, ?)");
            pst.setString(1, client.getFio());
            pst.setString(2, client.getEmail());
            pst.setString(3, client.getTel());
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        closeClientDB();
        return true;
    }

    //***************************************************************************
    //    select Client
    //***************************************************************************

    public ArrayList<Client> selectClients() {
        ArrayList<Client> clients = new ArrayList<>();
        Client client;
//        int numClients = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM client ORDER BY id");
            while (rs.next()) {
//                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                client = new Client(rs.getString(2), rs.getString(3), rs.getString(4));
                clients.add(client);
                client.setId(rs.getInt(1));
//                numClients = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeClientDB();
        return clients;
    }

    public Client selectClientById(int id) {
        Client client = null;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM client WHERE id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                client = new Client(rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeClientDB();
        return client;
    }

    public int selectIdClientByParam(String fio, String email) {
        int id = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT id FROM client WHERE fio=? AND email=?");
            pst.setString(1, fio);
            pst.setString(2, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeClientDB();
        return id;
    }

    //***************************************************************************
    //    update Client
    //***************************************************************************

    public boolean updateClient(Client client, String fio, String email, String tel) {
        String upFio = client.getFio();
        String upEmail = client.getEmail();
        String upTel = client.getTel();
        int id = 0;
        if (!fio.equals("")) upFio = fio;
        if (!email.equals("")) upEmail = email;
        if (!tel.equals("")) upTel = tel;
        id = selectIdClientByParam(client.getFio(), client.getEmail());

        try {
            PreparedStatement pst = connection.prepareStatement
                    ("UPDATE client SET fio = ?, email = ?, tel = ? WHERE id = ?");
            pst.setString(1, upFio);
            pst.setString(2, upEmail);
            pst.setString(3, upTel);
            pst.setInt(4, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to update data in the database.");
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        closeClientDB();
        return true;
    }

    public void closeClientDB() {
        dbConnection.closeConnection();
    }
}
