package com.kademika.day14.shop_v2.db;

import com.kademika.day14.shop_v2.client.Client;
import com.kademika.day14.shop_v2.Personal;
import com.kademika.day14.shop_v2.watches.Mechanic;
import com.kademika.day14.shop_v2.watches.Quartz;
import com.kademika.day14.shop_v2.watches.Watch;
import com.kademika.day14.shop_v2.watches.WatchType;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Admin on 11.01.2015.
 */
public class DBUpdate {
    private Connection connection = null;
    private DBConnection dbConnection = null;
    private DBSelect dbSelect = null;

    public DBUpdate(DBConnection dbConnection) {
        this.connection = dbConnection.getConnection();
        this.dbConnection = dbConnection;
        dbSelect = new DBSelect(dbConnection);
    }

    public void updateClient(Client client, String fio, String email, String tel) {
        String upFio = client.getFio();
        String upEmail = client.getEmail();
        String upTel = client.getTel();
        int id = 0;
        if (!fio.equals("")) upFio = fio;
        if (!email.equals("")) upEmail = email;
        if (!tel.equals("")) upTel = tel;
        id = dbSelect.selectIdClientByParam(client.getFio(), client.getEmail());

        try {
            PreparedStatement pst = connection.prepareStatement
                    ("UPDATE client SET fio = ?, email = ?, tel = ? WHERE id = ?");
            pst.setString(1, upFio);
            pst.setString(2, upEmail);
            pst.setString(3, upTel);
            pst.setInt(4, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to update data in the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePersonal(Personal personal, String fio, int age, String email) {
        String upFio = personal.getFio();
        String upEmail = personal.getEmail();
        int upAge = personal.getAge();
        int id = 0;
        if (!fio.equals("")) upFio = fio;
        if (!email.equals("")) upEmail = email;
        if (age > 0) upAge = age;
        id = dbSelect.selectIdPersByParam(personal.getFio(), personal.getAge());

        try {
            PreparedStatement pst = connection.prepareStatement
                    ("UPDATE client SET fio = ?, age = ?, email = ? WHERE id = ?");
            pst.setString(1, upFio);
            pst.setString(3, upEmail);
            pst.setInt(2, upAge);
            pst.setInt(4, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to update data in the database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWatch(Watch watch, String name, int weight, WatchType watchType, int number,
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
            e.printStackTrace();
        }
    }
}
