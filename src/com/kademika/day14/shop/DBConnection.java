package com.kademika.day14.shop;

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
}
