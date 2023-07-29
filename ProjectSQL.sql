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

INSERT INTO properties(propertyId,agentName,askingPrice,region,propertiesType,closingDate)
VALUES(1,'Ajay Shrestha','40,000','Ontario','Condo','2022/01/01');

INSERT INTO properties(propertyId,agentName,askingPrice,region,propertiesType,closingDate)
VALUES(2,'Gaurab Pokharel','40,000','Ontario','Condo','2022/01/01');

INSERT INTO properties(propertyId,agentName,askingPrice,region,propertiesType,closingDate)
VALUES(3,'Nirajan karki','40,000','Ontario','Condo','2022/01/01');

INSERT INTO properties(propertyId,agentName,askingPrice,region,propertiesType,closingDate)
VALUES(4,'Sakar Thapa','40,000','Ontario','Condo','2022/01/01');