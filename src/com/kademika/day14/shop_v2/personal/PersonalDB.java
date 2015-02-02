package com.kademika.day14.shop_v2.personal;

import com.kademika.day14.shop_v2.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Admin on 02.02.2015.
 */
public class PersonalDB {
    private Connection connection;
    private DBConnection dbConnection;

    public PersonalDB() {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    //***************************************************************************
    //    delete Personal
    //***************************************************************************

    public boolean deletePersonal(int id) {
        try {
            PreparedStatement pst = connection.prepareStatement("DELETE FROM personal where id=?");
            pst.setInt(1, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to delete data from the database.");
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        closePersonalDB();
        return true;
    }

    //***************************************************************************
    //    insert Personal
    //***************************************************************************

    public boolean insertPersonal(Personal personal) {
        try {
            PreparedStatement pst = connection.prepareStatement("INSERT INTO personal (id, fio, age, email) VALUES (last_insert_id(), ?, ?, ?)");
            pst.setString(1, personal.getFio());
            pst.setInt(2, personal.getAge());
            pst.setString(3, personal.getEmail());
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to add data to the database.");
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        closePersonalDB();
        return true;
    }

    //***************************************************************************
    //    select Personal
    //***************************************************************************

    public ArrayList<Personal> selectPersonal() {
        ArrayList<Personal> personal = new ArrayList<>();
        Personal pers;
        int numPers = 0;

        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM personal ORDER BY id");
            while (rs.next()) {
//                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                pers = new Personal(rs.getString(2), rs.getInt(3), rs.getString(4));
                personal.add(pers);
                pers.setId(rs.getInt(1));
                numPers++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbConnection.setNumPersonal(numPers);
        closePersonalDB();
        return personal;
    }


    public Personal selectPersonalById(int id) {
        Personal personal = null;
        try {
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM personal WHERE id=?");
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                personal = new Personal(rs.getString(2), rs.getInt(3), rs.getString(4));
                personal.setId(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        closePersonalDB();
        return personal;
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
//        closePersonalDB();
        return id;
    }

    //***************************************************************************
    //    update Personal
    //***************************************************************************

    public boolean updatePersonal(Personal personal, String fio, int age, String email) {
        String upFio = personal.getFio();
        String upEmail = personal.getEmail();
        int upAge = personal.getAge();
        int id = 0;
        if (!fio.equals("")) upFio = fio;
        if (!email.equals("")) upEmail = email;
        if (age > 0) upAge = age;
        id = selectIdPersByParam(personal.getFio(), personal.getAge());

        try {
            PreparedStatement pst = connection.prepareStatement
                    ("UPDATE client SET fio = ?, age = ?, email = ? WHERE id = ?");
            pst.setString(1, upFio);
            pst.setString(3, upEmail);
            pst.setInt(2, upAge);
            pst.setInt(4, id);
            if (pst.executeUpdate() <= 0) System.out.println("Error! Unable to update data in the database.");
        } catch (Exception e) {
            return false;
        }
        closePersonalDB();
        return true;
}

    public void closePersonalDB() {
        dbConnection.closeConnection();
    }
}
