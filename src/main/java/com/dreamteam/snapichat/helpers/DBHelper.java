/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dreamteam.snapichat.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class DBHelper {
    
    //Only for openshift database
    private final static String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private final static String HOSTNAME = "jdbc:mysql://127.7.232.130:3306/";
    private final static String DB_NAME = "snapichat";
    private final static String DB_USER = "adminZ4LAQSe";
    private final static String DB_PASS = "cJ7usqLZ3zvD";
    
    public static Statement connect() {
        Statement st = null;
        
        try {
            st = attemptConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return st;
    }
    
    private static Statement attemptConnection() throws ClassNotFoundException, SQLException {
        Class.forName(MYSQL_DRIVER);
        
        Connection con = DriverManager.getConnection(HOSTNAME + DB_NAME,
                DB_USER, DB_PASS);
        Statement st = con.createStatement();
        
        return st;
    }
    
}
