package com.kademika.day14.shop_v2.personal;

import com.kademika.day14.shop_v2.domain.Personal;

import java.util.ArrayList;

/**
 * Created by Admin on 02.02.2015.
 */
public class PersonalOperations {
    private PersonalDB personalDB;

    public PersonalOperations() {

    }

    public boolean checkPersonalById(int id) {
        personalDB = new PersonalDB();
        if (personalDB.selectPersonalById(id) != null) {
            personalDB.closePersonalDB();
            return true;
        }
        personalDB.closePersonalDB();
        return false;
    }

    //***************************************************************
    //    get
    //***************************************************************
    public int getIdPersByParam(String fio, int age) {
        int k = 0;
        personalDB = new PersonalDB();
        k = personalDB.selectIdPersByParam(fio, age);
        personalDB.closePersonalDB();
        return k;
    }

    public Personal getPersonalById(int id) {
        Personal personal = null;
        personalDB = new PersonalDB();
        personal = personalDB.selectPersonalById(id);
        personalDB.closePersonalDB();
        return personal;
    }

    public ArrayList<Personal> getAllPers() {
        ArrayList<Personal> personals = null;
        personalDB = new PersonalDB();
        personals = personalDB.selectPersonal();
        personalDB.closePersonalDB();
        return personals;
    }

    //***************************************************************
    //    update
    //***************************************************************
    public boolean updatePersonal(Personal personal, String fio, int age, String email) {
        boolean bool;
        personalDB = new PersonalDB();
        bool = personalDB.updatePersonal(personal, fio, age, email);
        personalDB.closePersonalDB();
        return bool;
    }

    //***************************************************************
    //    insert
    //***************************************************************
    public boolean insertPersonal(Personal personal) {
        boolean bool;
        personalDB = new PersonalDB();
        bool = personalDB.insertPersonal(personal);
        personalDB.closePersonalDB();
        return bool;
    }

    //***************************************************************
    //    delete
    //***************************************************************
    public boolean deletePersonal(int id) {
        boolean bool;
        personalDB = new PersonalDB();
        bool = personalDB.deletePersonal(id);
        personalDB.closePersonalDB();
        return bool;
    }

}
