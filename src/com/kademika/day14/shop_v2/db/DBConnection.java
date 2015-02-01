package com.kademika.day14.shop_v2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Admin on 10.01.2015.
 */
public class DBConnection {
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/shop";
    private String driver = "com.mysql.jdbc.Driver";
    private Connection connection = null;
    private int numClient;
    private int numPersonal;
    private int numQuartz;
    private int numMechanic;
    private int numWatch;
    private int numTransactionInDay;
    private int numTransaction;


    public DBConnection() {
        try {
            Class.forName(driver);//Регистрируем драйвер
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, user, password);//Установка соединения с БД
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public int getNumClient() {
        return numClient;
    }

    public void setNumClient(int numClient) {
        this.numClient = numClient;
    }

    public int getNumPersonal() {
        return numPersonal;
    }

    public void setNumPersonal(int numPersonal) {
        this.numPersonal = numPersonal;
    }

    public int getNumQuartz() {
        return numQuartz;
    }

    public void setNumQuartz(int numQuartz) {
        this.numQuartz = numQuartz;
    }

    public int getNumMechanic() {
        return numMechanic;
    }

    public void setNumMechanic(int numMechanic) {
        this.numMechanic = numMechanic;
    }

    public int getNumWatch() {
        return numWatch;
    }

    public void setNumWatch(int numWatch) {
        this.numWatch = numWatch;
    }

    public int getNumTransactionInDay() {
        return numTransactionInDay;
    }

    public void setNumTransactionInDay(int numTransactionInDay) {
        this.numTransactionInDay = numTransactionInDay;
    }

    public int getNumTransaction() {
        return numTransaction;
    }

    public void setNumTransaction(int numTransaction) {
        this.numTransaction = numTransaction;
    }
}
