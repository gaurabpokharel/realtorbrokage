CREATE DATABASE IF NOT EXISTS brokerage;

USE brokerage;

CREATE TABLE IF NOT EXISTS properties (
    propertyId int NOT NULL  PRIMARY KEY,
    agentName VARCHAR(255),
    askingPrice VARCHAR(255),
    region VARCHAR(255),
    propertiesType VARCHAR(255),
    closingDate VARCHAR(255)
);