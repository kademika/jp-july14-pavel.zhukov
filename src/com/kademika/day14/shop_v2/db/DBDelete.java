package com.kademika.day14.shop_v2.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Admin on 11.01.2015.
 */
public class DBDelete {
    private Connection connection = null;
    private DBConnection dbConnection = null;

    public DBDelete(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
        this.dbConnection = dbConnection;
    }

    public void deletePersonal(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM personal where id=?");
            pst.setInt(1, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to delete data from the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM client where id=?");
            pst.setInt(1, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to delete data from the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWatch(String id) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM watch where id=?");
            pst.setString(1, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to delete data from the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
