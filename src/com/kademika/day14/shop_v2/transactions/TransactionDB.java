package com.kademika.day14.shop_v2.transactions;

import com.kademika.day14.shop_v2.client.ClientOperations;
import com.kademika.day14.shop_v2.db.DBConnection;
import com.kademika.day14.shop_v2.domain.Transaction;
import com.kademika.day14.shop_v2.personal.PersonalOperations;
import com.kademika.day14.shop_v2.watches.WatchOperations;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Admin on 09.02.2015.
 */
public class TransactionDB {

    private Connection connection;
    private DBConnection dbConnection;
    private ClientOperations co;
    private PersonalOperations po;
    private WatchOperations wo;

    public TransactionDB() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
        co = new ClientOperations();
        po = new PersonalOperations();
        wo = new WatchOperations();
    }

    //***************************************************************************
    //    insert Watch
    //***************************************************************************

    public boolean insertTransaction(Transaction transaction) {
        try {
            PreparedStatement pst = connection.prepareStatement
                    ("INSERT INTO transactions (id, date, client, watch, personal, number, price, discount, total) " +
                            "VALUES (last_insert_id(), ?, ?, ?, ?, ?, ?, ?, ?)");
//            pst.setInt(1, dbConnection.getNumTransaction() + 1);
            pst.setDate(1, transaction.getDate());
            pst.setInt(2, co.getIdClientByParam(transaction.getClient().getFio(), transaction.getClient().getEmail()));
            pst.setString(3, transaction.getIdProd());
            pst.setInt(4, po.getIdPersByParam(transaction.getSeller().getFio(), transaction.getSeller().getAge()));
            pst.setInt(5, transaction.getNumber());
            pst.setDouble(6, transaction.getPrice());
            pst.setInt(7, transaction.getDiscount());
            pst.setDouble(8, transaction.getTotalPrice());
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
        } catch (Exception e) {
//            e.printStackTrace();
            closeTransactionDB();
            return false;
        }

//        closeTransactionDB();
        return true;
    }

    //***************************************************************************
    //    select Watch
    //***************************************************************************

    public ArrayList<Transaction> selectTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        int numTransactions = 0;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM transactions ORDER BY id");
            while (rs.next()) {
                transactions.add(new Transaction
                        (rs.getInt(1), co.getClientById(rs.getInt(3)), wo.getWatchById(rs.getString(4)),
                                rs.getInt(6), po.getPersonalById(rs.getInt(5)), rs.getDate(2)));
                numTransactions++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumTransaction(numTransactions);
//        closeTransactionDB();
        return transactions;
    }

    public ArrayList<Transaction> selectTransactionsByDate(Date date) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        int numTransactions = 0;

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM transactions WHERE 'date' = ? ORDER BY id");
            pst.setDate(1, date);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction
                        (rs.getInt(1), co.getClientById(rs.getInt(3)), wo.getWatchById(rs.getString(4)),
                                rs.getInt(6), po.getPersonalById(rs.getInt(5)), rs.getDate(2)));
                numTransactions++;
            }
            if (transactions.size() == 0) System.out.println("Data not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumTransactionInDay(numTransactions);
//        closeTransactionDB();
        return transactions;
    }

    public void closeTransactionDB() {
        dbConnection.closeConnection();
    }
}


