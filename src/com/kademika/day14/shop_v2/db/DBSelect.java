package com.kademika.day14.shop_v2.db;

import com.kademika.day14.shop_v2.client.Client;
import com.kademika.day14.shop_v2.personal.Personal;
import com.kademika.day14.shop_v2.transactions.Transaction;
import com.kademika.day14.shop_v2.watches.Mechanic;
import com.kademika.day14.shop_v2.watches.Quartz;
import com.kademika.day14.shop_v2.watches.Watch;
import com.kademika.day14.shop_v2.watches.WatchType;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Admin on 10.01.2015.
 */
public class DBSelect {
    private Connection connection = null;
    private DBConnection dbConnection = null;

    public DBSelect(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
        this.dbConnection = dbConnection;
    }

//************************************************************
//select all data from db
//************************************************************

    public ArrayList<Client> selectClients() {
        ArrayList<Client> clients = new ArrayList<>();
        int numClients = 0;
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM client ORDER BY id");
            while (rs.next()) {
//                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                clients.add(new Client(rs.getString(2), rs.getString(3), rs.getString(4)));
                numClients = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumClient(numClients);
        return clients;
    }

    public ArrayList<Personal> selectPersonal() {
        ArrayList<Personal> personal = new ArrayList<>();
        int numPers = 0;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personal ORDER BY id");
            while (rs.next()) {
//                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                personal.add(new Personal(rs.getString(2), rs.getInt(3), rs.getString(4)));
                numPers = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumPersonal(numPers);
        return personal;
    }

    public ArrayList<Watch> selectWatches() {
        ArrayList<Watch> watches = new ArrayList<>();
        WatchType wt;
        Boolean backlight;
        int numQuartz = 0;
        int numMechanic = 0;
        int numWatch = 0;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM watch ORDER BY id");
            while (rs.next()) {
                wt = null;
                backlight = false;
                if (rs.getInt(4) == 1) {
                    if (rs.getInt(5) == 1) {
                        wt = WatchType.Wrist;
                    } else {
                        wt = WatchType.Pocket;
                    }
                    if (rs.getInt(8) == 1) backlight = true;
                    watches.add(
                            new Quartz(rs.getString(2), rs.getInt(3), wt, rs.getString(1), rs.getInt(6),
                                    rs.getDouble(7), backlight, rs.getString(10)));
                    numQuartz = rs.getInt(1);
                } else {
                    if (rs.getInt(5) == 1) {
                        wt = WatchType.Wrist;
                    } else {
                        wt = WatchType.Pocket;
                    }
                    watches.add(
                            new Mechanic(rs.getString(2), rs.getInt(3), wt, rs.getString(1), rs.getInt(6),
                                    rs.getDouble(7), rs.getInt(9), rs.getString(10)));
                    numMechanic = rs.getInt(1);
                }
                numWatch++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        dbConnection.setNumMechanic(numMechanic);
        dbConnection.setNumQuartz(numQuartz);
        dbConnection.setNumWatch(numWatch);
        return watches;
    }

    public ArrayList<Transaction> selectTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        int numTransactions = 0;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM transactions ORDER BY id");
            while (rs.next()) {
                transactions.add(new Transaction
                        (rs.getInt(1), selectClientById(rs.getInt(3)), selectWatchById(rs.getString(4)),
                                rs.getInt(6), selectPersonalById(rs.getInt(5)), rs.getDate(2)));
                numTransactions++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumTransaction(numTransactions);
        return transactions;
    }

    //************************************************************
//select data by id
//************************************************************
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
        return client;
    }

    public Personal selectPersonalById(int id) {
        Personal personal = null;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM personal WHERE id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                personal = new Personal(rs.getString(2), rs.getInt(3), rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personal;
    }

    public Watch selectWatchById(String id) {
        Watch watch = null;
        WatchType wt;
        Boolean backlight;

        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM watch WHERE id=?");
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                wt = null;
                backlight = false;
                if (rs.getInt(4) == 1) {
                    if (rs.getInt(5) == 1) {
                        wt = WatchType.Wrist;
                    } else {
                        wt = WatchType.Pocket;
                    }
                    if (rs.getInt(8) == 1) backlight = true;
                    watch = new Quartz(rs.getString(2), rs.getInt(3), wt, rs.getString(1), rs.getInt(6),
                            rs.getDouble(7), backlight, rs.getString(10));
                } else {
                    if (rs.getInt(5) == 1) {
                        wt = WatchType.Wrist;
                    } else {
                        wt = WatchType.Pocket;
                    }
                    watch = new Mechanic(rs.getString(2), rs.getInt(3), wt, rs.getString(1), rs.getInt(6),
                            rs.getDouble(7), rs.getInt(9), rs.getString(10));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watch;
    }

//************************************************************
//select data by name and other parameters
//************************************************************

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
        return id;
    }

    public int selectIdPersByParam(String fio, int age) {
        int id = 0;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT id FROM personal WHERE fio=? AND age=?");
            pst.setString(1, fio);
            pst.setInt(2, age);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
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
                        (rs.getInt(1), selectClientById(rs.getInt(3)), selectWatchById(rs.getString(4)),
                                rs.getInt(6), selectPersonalById(rs.getInt(5)), rs.getDate(2)));
                numTransactions++;
            }
            if (transactions.size() == 0) System.out.println("Data not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumTransactionInDay(numTransactions);
        return transactions;
    }
}
