package com.kademika.day14.shop_v2.watches;

import com.kademika.day14.shop_v2.db.DBConnection;
import com.kademika.day14.shop_v2.domain.Mechanic;
import com.kademika.day14.shop_v2.domain.Quartz;
import com.kademika.day14.shop_v2.domain.Watch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Admin on 09.02.2015.
 */
public class WatchDB {
    private Connection connection;
    private DBConnection dbConnection;

    public WatchDB() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    //***************************************************************************
    //    delete Watch
    //***************************************************************************
    public boolean deleteClient(String id) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM watch where id=?");
            pst.setString(1, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to delete data from the database.");
        } catch (Exception e) {
            return false;
        }
//        closeWatchDB();
        return true;
    }

    //***************************************************************************
    //    insert Watch
    //***************************************************************************

    public boolean insertWatch(Watch watch) {
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
//                closeWatchDB();
                return false;
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
//                e.printStackTrace();
//                closeWatchDB();
                return false;
            }
        }
//        closeWatchDB();
        return true;
    }

    //***************************************************************************
    //    select Watch
    //***************************************************************************

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
//        closeWatchDB();
        return watches;
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
            return null;
        }
        return watch;
    }

    //***************************************************************************
    //    update Watch
    //***************************************************************************

    public boolean updateWatch(Watch watch, String name, int weight, WatchType watchType, int number,
                               double price, boolean backlight, int numberArrows, String moreFeatures) {
        String upName = watch.getName();
        int upWeight = watch.getWeight();
        WatchType upWt = watch.getType();
        int upNum = watch.getNumber();
        double upPrice = watch.getPrice();
        String upMoreFeat = "";
        boolean upBacklight = true;
        int upNumArrows = 0;


        if (!name.equals("")) upName = name;
        if (weight > 0) upWeight = weight;
        if (watchType != WatchType.None) upWt = watchType;
        if (number >= 0) upNum = number;
        if (price > 0) upPrice = price;

        if (watch instanceof Quartz) {
            upBacklight = backlight;
            upMoreFeat = ((Quartz) watch).getMoreFeatures();
        } else {
            upNumArrows = ((Mechanic) watch).getNumberArrows();
            upMoreFeat = ((Mechanic) watch).getAdditionalDials();
            if (numberArrows > 0) upNumArrows = numberArrows;
        }
        if (!moreFeatures.equals("")) upMoreFeat = moreFeatures;

        try {
            PreparedStatement pst = connection.prepareStatement
                    ("UPDATE watch SET name=?, weight=?, type=?, number=?, price=?, backlight=?, numberArrows=?, moreFeatures=? WHERE id = ?");
            pst.setString(1, upName);
            pst.setInt(2, upWeight);
            if (upWt == WatchType.Wrist) {
                pst.setInt(3, 1);
            } else {
                pst.setInt(3, 2);
            }
            pst.setInt(4, upNum);
            pst.setDouble(5, upPrice);
            if (watch instanceof Quartz) {
                if (upBacklight) {
                    pst.setInt(6, 1);
                } else {
                    pst.setInt(6, 0);
                }
                pst.setNull(7, 7);
            } else {
                pst.setNull(6, 6);
                pst.setInt(7, upNumArrows);
            }
            pst.setString(8, upMoreFeat);
            pst.setString(9, watch.getId());
            int res = pst.executeUpdate();
            if (res <= 0) System.out.println("Error! Unable to update data in the database.");
        } catch (Exception e) {
//            e.printStackTrace();
//            closeWatchDB();
            return false;
        }
//        closeWatchDB();
        return true;
    }

    public void closeWatchDB() {
        dbConnection.closeConnection();
    }
}
