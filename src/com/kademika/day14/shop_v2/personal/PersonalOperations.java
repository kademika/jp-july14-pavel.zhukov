package com.kademika.day14.shop_v2.personal;

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
            return true;
        }
        return false;
    }

    //***************************************************************
    //    get
    //***************************************************************
    public int getIdPersByParam(String fio, int age) {
        personalDB = new PersonalDB();
        return personalDB.selectIdPersByParam(fio, age);
    }

    public Personal getPersonalById(int id) {
        personalDB = new PersonalDB();
        return personalDB.selectPersonalById(id);
    }

    public ArrayList<Personal> getAllPers() {
        personalDB = new PersonalDB();
        return personalDB.selectPersonal();
    }

    //***************************************************************
    //    update
    //***************************************************************
    public boolean updatePersonal(Personal personal, String fio, int age, String email) {
        personalDB = new PersonalDB();
        return personalDB.updatePersonal(personal, fio, age, email);
    }

    //***************************************************************
    //    insert
    //***************************************************************
    public boolean insertPersonal(Personal personal) {
        personalDB = new PersonalDB();
        return personalDB.insertPersonal(personal);
    }

    //***************************************************************
    //    delete
    //***************************************************************
    public boolean deletePersonal(int id) {
        personalDB = new PersonalDB();
        return personalDB.deletePersonal(id);
    }

}
