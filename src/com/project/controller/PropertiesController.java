package com.project.controller;

import com.project.dao.DatabaseConnectivityDao;
import com.project.model.Property;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *<p>Seperate Controller class that includes crud operation and additional method for search</p>
 * @author Ajay Shrestha, Gaurab Pokharel, Nirajan Karki, Sakar Thapa
 * @since 7/28/2023
 **/
public class PropertiesController {
    //method to insert data into database
    public void insertData(Property property) throws ParseException{
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
        	 String dateString = property.getClosingDate();
             SimpleDateFormat sdfParse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
             Date date = sdfParse.parse(dateString);

             SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy/MM/dd");
             String formattedDate = sdfFormat.format(date);
        	//Connect with the database
            con = databaseConnectivityDao.setUpConnection();
            //query to insert into properties
            String insertQuery = "Insert into properties (propertyId, agentName, askingPrice, region, propertiesType, closingDate) VALUES(?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertQuery);
            preparedStatement.setInt(1,property.getPropertyId());
            preparedStatement.setString(2, property.getAgentName());
            preparedStatement.setString(3, property.getAskingPrice());
            preparedStatement.setString(4, property.getRegion());
            preparedStatement.setString(5, property.getPropertiesType());
            preparedStatement.setString(6, formattedDate);
            //execute the prepared statement
            preparedStatement.executeUpdate();
            //Close the connection
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
        	//Connect with the database
            con = databaseConnectivityDao.setUpConnection();
            //query to delete from the table
            String deleteQuery = "DELETE FROM  properties WHERE propertyId=?";
            preparedStatement = con.prepareStatement(deleteQuery);
            preparedStatement.setInt(1,propertyId);
            //execute the query
            preparedStatement.executeUpdate();
            //Close the connection
            con.close();
            System.out.println("Delete Query is execute successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //method to update data into database using propertyId
    public void updateDataFromPropertyId(Property property,int propertyId){
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            String dateString = property.getClosingDate();
            SimpleDateFormat sdfParse = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
            Date date = sdfParse.parse(dateString);

            SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy/MM/dd");
            String formattedDate = sdfFormat.format(date);
            //Update data query 
            String updateQuery = "UPDATE properties SET agentName = ?, askingPrice =?, region =?, propertiesType =?, closingDate=? WHERE propertyId=?";
            preparedStatement = con.prepareStatement(updateQuery);
            preparedStatement.setString(1, property.getAgentName());
            preparedStatement.setString(2, property.getAskingPrice());
            preparedStatement.setString(3, property.getRegion());
            preparedStatement.setString(4, property.getPropertiesType());
            preparedStatement.setString(5, formattedDate);
            preparedStatement.setInt(6,propertyId);
            preparedStatement.executeUpdate();
            //Close the connection
            con.close();
            System.out.println("Update Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    //method to get all data from database
    public List<Property> getAllPropertiesDetail(){
    	//List to hold multiple row data of properties
        ArrayList<Property> propertiesArrayList = new ArrayList<>();
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            //select query to retrieve all data from properties table
            String selectQuery = "SELECT * FROM properties";
            preparedStatement = con.prepareStatement(selectQuery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Property property = new Property();
                property.setPropertyId(rs.getInt(1));
                property.setAgentName(rs.getString(2));
                property.setAskingPrice(rs.getString(3));
                property.setRegion(rs.getString(4));
                property.setPropertiesType(rs.getString(5));
                property.setClosingDate(rs.getString(6));
                propertiesArrayList.add(property);
            }
            con.close();
            System.out.println("Select All Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesArrayList;
    }

    //method to get data from database using agent name
    public List<Property> getPropertiesDetailByAgentName(String agentName){
    	//List to hold multiple row data of properties
        ArrayList<Property> propertiesArrayList = new ArrayList<>();
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = databaseConnectivityDao.setUpConnection();
            //select query using agent name
            String selectQuery = "SELECT * FROM properties WHERE agentName =?";
            preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setString(1,agentName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Property property = new Property();
                property.setPropertyId(rs.getInt(1));
                property.setAgentName(rs.getString(2));
                property.setAskingPrice(rs.getString(3));
                property.setRegion(rs.getString(4));
                property.setPropertiesType(rs.getString(5));
                property.setClosingDate(rs.getString(6));
                //setting the individual data into the list
                propertiesArrayList.add(property);
            }
            con.close();
            System.out.println("Select with agent name filter Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return propertiesArrayList;
    }
    public Property getPropertiesDetailByPropertyId(int propertyId){
        Property property = new Property();
        DatabaseConnectivityDao databaseConnectivityDao = new DatabaseConnectivityDao();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
        	//Connect with the database
            con = databaseConnectivityDao.setUpConnection();
            //query to select detail from table using the propertyId
            String selectQuery = "SELECT * FROM properties WHERE propertyId =?";
            preparedStatement = con.prepareStatement(selectQuery);
            preparedStatement.setInt(1,propertyId);
            ResultSet rs = preparedStatement.executeQuery();
            //contain only one row in resultset
            while (rs.next()){
                property.setPropertyId(rs.getInt(1));
                property.setAgentName(rs.getString(2));
                property.setAskingPrice(rs.getString(3));
                property.setRegion(rs.getString(4));
                property.setPropertiesType(rs.getString(5));
                property.setClosingDate(rs.getString(6));
            }
            //Close the connection
            con.close();
            System.out.println("Select with agent name filter Query is execute successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return property;
    }

}
