package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>DAO class for database connectivity</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
public class DatabaseConnectivityDao {
    Connection con ;
    public Connection setUpConnection() {
        try {
        	//Driver name is com.mysql.jdbc.driver for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //Url to connect with database mysql
            String mysqlUrl = "jdbc:mysql://localhost:3306/brokerage";
            //Create the connection with username root and password null
            Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
            if (con != null) {
            	//If database connected print the line
                System.out.println("Database connected!");
            }
            return con;
        } catch (SQLException | ClassNotFoundException e) {
        	//If database fails print the line
            System.out.println("Error connecting to the database. Please check connection.");
            e.printStackTrace();
        }
        if (con == null) {
            System.out.println("Database not connected. Please check connection.");
        }
        return con;
    }
}