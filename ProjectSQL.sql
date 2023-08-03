CREATE DATABASE IF NOT EXISTS brokerage;

USE brokerage;

CREATE TABLE IF NOT EXISTS properties (
    propertyId int NOT NULL AUTO_INCREMENT  PRIMARY KEY,
    agentName VARCHAR(255),
    askingPrice VARCHAR(255),
    region VARCHAR(255),
    propertyType VARCHAR(255),
    closingDate VARCHAR(255)
);

--INSERT INTO properties(propertyId,agentName,askingPrice,region,propertyType,closingDate)
--VALUES(1,'Ajay Shrestha','40,000','Ontario','Condo','2022/01/01');
--
--INSERT INTO properties(propertyId,agentName,askingPrice,region,propertyType,closingDate)
--VALUES(2,'Gaurab Pokharel','40,000','Ontario','Condo','2022/01/01');
--
--INSERT INTO properties(propertyId,agentName,askingPrice,region,propertyType,closingDate)
--VALUES(3,'Nirajan karki','40,000','Ontario','Condo','2022/01/01');
--
--INSERT INTO properties(propertyId,agentName,askingPrice,region,propertyType,closingDate)
--VALUES(4,'Sakar Thapa','40,000','Ontario','Condo','2022/01/01');
--
--INSERT INTO properties(propertyId,agentName,askingPrice,region,propertyType,closingDate)
--VALUES(5,'Sakar Thapa','40,000','Ottawa','Town house','2022/01/01');
--
--INSERT INTO properties(propertyId,agentName,askingPrice,region,propertyType,closingDate)
--VALUES(6,'Ajay Shrestha','40,000','Ottawa','Town house','2022/01/01');