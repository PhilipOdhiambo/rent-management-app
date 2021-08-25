CREATE DATABASE rent_management;

\c  rent_management;

CREATE TABLE tenants(
id SERIAL PRIMARY KEY ,
name VARCHAR,
phoneNumber VARCHAR,
propertyId INTEGER
);


CREATE TABLE  properties (
id SERIAL PRIMARY KEY,
 name VARCHAR,
 type VARCHAR,
 location VARCHAR,
 description VARCHAR,
 rent INTEGER
);

CREATE TABLE  payments (
 id SERIAL PRIMARY KEY,
 propertyid INTEGER,
 tenantid INTEGER,
 amount int,
 paidby VARCHAR,
 date TIMESTAMP
);