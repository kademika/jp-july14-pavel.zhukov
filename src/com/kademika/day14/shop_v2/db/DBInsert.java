package com.kademika.day14.shop_v2.db;

import com.kademika.day14.shop_v2.client.Client;
import com.kademika.day14.shop_v2.Personal;
import com.kademika.day14.shop_v2.Transaction;
import com.kademika.day14.shop_v2.watches.Mechanic;
import com.kademika.day14.shop_v2.watches.Quartz;
import com.kademika.day14.shop_v2.watches.Watch;
import com.kademika.day14.shop_v2.watches.WatchType;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Admin on 11.01.2015.
 */
public class DBInsert {
    private Connection connection = null;
    private DBConnection dbConnection = null;
    private DBSelect dbSelect = null;

    public DBInsert(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
        this.dbConnection = dbConnection;
        dbSelect = new DBSelect(dbConnection);
    }

    public void insertClient(Client client) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO client (id, fio, email, tel) VALUES (last_insert_id(), ?, ?, ?)");
//            pst.setInt(1, dbConnection.getNumClient() + 1);
            pst.setString(1, client.getFio());
            pst.setString(2, client.getEmail());
            pst.setString(3, client.getTel());
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertPersonal(Personal personal) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO personal (id, fio, age, email) VALUES (last_insert_id(), ?, ?, ?)");
//            pst.setInt(1, dbConnection.getNumPersonal() + 1);
            pst.setString(1, personal.getFio());
            pst.setInt(2, personal.getAge());
            pst.setString(3, personal.getEmail());
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertWatch(Watch watch) {
        if (watch instanceof Quartz) {
            try {
                PreparedStatement pst = connection.prepareStatement
                        ("INSERT INTO watch (id, name, weight, mechanism, type, number, price, backlight, numberArrows, moreFeatures) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                pst.setString(1, "" + (dbConnection.getNumQuartz() + 1));
                pst.setString(2, watch.getName());
                pst.setInt(3, watch.getWeight());
                pst.setInt(4, 1);
                if (watch.getType() == WatchType.Wrist) {
                    pst.setInt(5, 1);
                } else {
                    pst.setInt(5, 2);
                }
                pst.setInt(6, watch.getNumber());
                pst.setDouble(7, watch.getPrice());
                if (((Quartz) watch).isBacklight()) {
                    pst.setInt(8, 1);
                } else {
                    pst.setInt(8, 0);
                }
                pst.setNull(9, 9);
                pst.setString(10, ((Quartz) watch).getMoreFeatures());

                if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement pst = connection.prepareStatement
                        ("INSERT INTO watch (id, name, weight, mechanism, type, number, price, backlight, numberArrows, moreFeatures) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                if (dbConnection.getNumMechanic() >= 99) {
                    pst.setString(1, "0" + (dbConnection.getNumMechanic() + 1));
                } else if (dbConnection.getNumMechanic() >= 9) {
                    pst.setString(1, "00" + (dbConnection.getNumMechanic() + 1));
                } else {
                    pst.setString(1, "000" + (dbConnection.getNumMechanic() + 1));
                }
                pst.setString(2, watch.getName());
                pst.setInt(3, watch.getWeight());
                pst.setInt(4, 2);
                if (watch.getType() == WatchType.Wrist) {
                    pst.setInt(5, 1);
                } else {
                    pst.setInt(5, 2);
                }
                pst.setInt(6, watch.getNumber());
                pst.setDouble(7, watch.getPrice());
                pst.setNull(8, 8);
                pst.setInt(9, ((Mechanic) watch).getNumberArrows());
                pst.setString(10, ((Mechanic) watch).getAdditionalDials());
                if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void insertTransaction(Transaction transaction) {
        try {
            PreparedStatement pst = connection.prepareStatement
                    ("INSERT INTO transactions (id, date, client, watch, personal, number, price, discount, total) " +
                            "VALUES (last_insert_id(), ?, ?, ?, ?, ?, ?, ?, ?)");
//            pst.setInt(1, dbConnection.getNumTransaction() + 1);
            pst.setDate(1, transaction.getDate());
            pst.setInt(2, dbSelect.selectIdClientByParam(transaction.getClient().getFio(), transaction.getClient().getEmail()));
            pst.setString(3, transaction.getIdProd());
            pst.setInt(4, dbSelect.selectIdPersByParam(transaction.getSeller().getFio(), transaction.getSeller().getAge()));
            pst.setInt(5, transaction.getNumber());
            pst.setDouble(6, transaction.getPrice());
            pst.setInt(7, transaction.getDiscount());
            pst.setDouble(8, transaction.getTotalPrice());
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
