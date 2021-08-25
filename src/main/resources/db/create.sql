SET MODE PostgreSQL;


CREATE TABLE IF NOT EXISTS tenants(
id int PRIMARY KEY auto_increment,
name VARCHAR,
phoneNumber VARCHAR,
propertyId INTEGER
);

CREATE TABLE IF NOT EXISTS property (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 type VARCHAR,
 location VARCHAR,
 description VARCHAR,
 rent INTEGER
);

CREATE TABLE IF NOT EXISTS payments (
 id int PRIMARY KEY auto_increment,
 propertyid INTEGER,
 tenantid INTEGER,
 amount int,
 paidby VARCHAR,
 date TIMESTAMP
);


