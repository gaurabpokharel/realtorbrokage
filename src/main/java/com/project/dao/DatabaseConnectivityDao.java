package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author
 * @project RealtorBrokage
 * @since 7/28/2023
 **/
public class DatabaseConnectivityDao {
    Connection con ;
    public Connection setUpConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Url to connect with database mysql
            String mysqlUrl = "jdbc:mysql://localhost:3306/brokerage";
            Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
            if (con != null) {
                System.out.println("Database connected!");
            }
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to the database. Please check connection.");
            e.printStackTrace();
        }
        if (con == null) {
            System.out.println("Database not connected. Please check connection.");
        }
        return con;
    }
}