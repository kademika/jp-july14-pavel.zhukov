package com.kademika.day14.shop;

import com.kademika.day14.shop.watches.Mechanic;
import com.kademika.day14.shop.watches.Quartz;
import com.kademika.day14.shop.watches.Watch;
import com.kademika.day14.shop.watches.WatchType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Admin on 10.01.2015.
 */
public class DBSelect {
    private Connection connection = null;

    public DBSelect(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Client> arrayClients() {
        ArrayList<Client> clients = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM client ORDER BY id");
            while (rs.next()) {
//                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                clients.add(new Client(rs.getString(2), rs.getString(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }

    public ArrayList<Personal> arrayPersonal() {
        ArrayList<Personal> personal = new ArrayList<>();

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personal ORDER BY id");
            while (rs.next()) {
//                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                personal.add(new Personal(rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personal;
    }

    public ArrayList<Watch> arrayWatches() {
        ArrayList<Watch> watches = new ArrayList<>();
        WatchType wt;
        Boolean backlight;

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
                } else {
                    if (rs.getInt(5) == 1) {
                        wt = WatchType.Wrist;
                    } else {
                        wt = WatchType.Pocket;
                    }
                    watches.add(
                            new Mechanic(rs.getString(2), rs.getInt(3), wt, rs.getString(1), rs.getInt(6),
                                    rs.getDouble(7), rs.getInt(9), rs.getString(10)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return watches;
    }
}
