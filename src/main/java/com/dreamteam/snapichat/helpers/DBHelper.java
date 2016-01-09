/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class DBHelper {
    
    //Only for openshift database
    private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final static String HOSTNAME = "jdbc:mysql://localhost:3306/";
    private final static String DB_NAME = "snapichat";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "root";
    
    public static Connection getConnection() {
        Connection conn = null;

        try {
            conn = attemptConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conn;
    }

    private static Connection attemptConnection()
            throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);

        Connection conn = DriverManager.getConnection(HOSTNAME + DB_NAME,
                DB_USER, DB_PASS);

        return conn;
    }
    
}
