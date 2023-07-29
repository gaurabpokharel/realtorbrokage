package com.project.controller;

import com.project.dao.DatabaseConnectivityDao;
import com.project.model.Properties;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @project RealtorBrokage
 * @since 7/28/2023
 **/
public class PropertiesController {
    //method to insert data into database
    public void insertData(Properties properties){
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String insertQuery = "Insert into properties (propertyId, agentName, askingPrice, region, propertiesType, closingDate) VALUES(?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1,properties.getPropertyId());
            preparedStatement.setString(2, properties.getAgentName());
            preparedStatement.setString(3, properties.getAskingPrice());
            preparedStatement.setString(4, properties.getRegion());
            preparedStatement.setString(5, properties.getPropertiesType());
            preparedStatement.setString(6, properties.getClosingDate());
            preparedStatement.executeUpdate();
            con.close();
            System.out.println("Insert Query is execute successfully");
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to delete data from database using propertyId
    public void deleteDataFromProperties(int propertyId){
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String deleteQuery = "DELETE FROM  properties WHERE propertyId=?";
            preparedStatement = con.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,propertyId);
            preparedStatement.executeUpdate();
            con.close();
            System.out.println("Delete Query is execute successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to update data into database using propertyId
    public void updateDataFromPropertyId(Properties properties,int propertyId){
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String dateString = properties.getClosingDate();
            SimpleDateFormat sdfParse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date date = sdfParse.parse(dateString);

            SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = sdfFormat.format(date);
            String updateQuery = "UPDATE properties SET agentName = ?, askingPrice =?, region =?, propertiesType =?, closingDate=? WHERE propertyId=?";
            preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, properties.getAgentName());
            preparedStatement.setString(2, properties.getAskingPrice());
            preparedStatement.setString(3, properties.getRegion());
            preparedStatement.setString(4, properties.getPropertiesType());
            preparedStatement.setString(5, formattedDate);
            preparedStatement.setInt(6,propertyId);
            preparedStatement.executeUpdate();
            con.close();
            System.out.println("Update Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //method to get all data from database
    public List<Properties> getAllPropertiesDetail(){
        ArrayList<Properties> propertiesArrayList = new ArrayList<>();
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String selectQuery = "SELECT * FROM properties";
            preparedStatement = con.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Properties properties = new Properties();
                properties.setPropertyId(rs.getInt(1));
                properties.setAgentName(rs.getString(2));
                properties.setAskingPrice(rs.getString(3));
                properties.setRegion(rs.getString(4));
                properties.setPropertiesType(rs.getString(5));
                properties.setClosingDate(rs.getString(6));
                propertiesArrayList.add(properties);
            }
            con.close();
            System.out.println("Select All Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesArrayList;
    }

    //method to get data from database using agent name
    public List<Properties> getPropertiesDetailByAgentName(String agentName){
        ArrayList<Properties> propertiesArrayList = new ArrayList<>();
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String selectQuery = "SELECT * FROM properties WHERE agentName =?";
            preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1,agentName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Properties properties = new Properties();
                properties.setPropertyId(rs.getInt(1));
                properties.setAgentName(rs.getString(2));
                properties.setAskingPrice(rs.getString(3));
                properties.setRegion(rs.getString(4));
                properties.setPropertiesType(rs.getString(5));
                properties.setClosingDate(rs.getString(6));
                propertiesArrayList.add(properties);
            }
            con.close();
            System.out.println("Select with agent name filter Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesArrayList;
    }
    public Properties getPropertiesDetailByPropertyId(int propertyId){
        Properties properties = new Properties();
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String selectQuery = "SELECT * FROM properties WHERE propertyId =?";
            preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setInt(1,propertyId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                properties.setPropertyId(rs.getInt(1));
                properties.setAgentName(rs.getString(2));
                properties.setAskingPrice(rs.getString(3));
                properties.setRegion(rs.getString(4));
                properties.setPropertiesType(rs.getString(5));
                properties.setClosingDate(rs.getString(6));
            }
            con.close();
            System.out.println("Select with agent name filter Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
