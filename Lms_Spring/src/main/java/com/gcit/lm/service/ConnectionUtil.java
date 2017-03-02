package com.gcit.lm.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by shash on 2/25/2017.
 */
public class ConnectionUtil {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost/library";
    private String user =  "root";
    private String password = "shashi";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName(driver);
         conn =  DriverManager.getConnection(url, user, password);
        conn.setAutoCommit(false);
        return conn;
    }


}
