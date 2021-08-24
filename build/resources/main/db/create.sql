SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS tenants(
id int PRIMARY KEY auto_increment,
name VARCHAR,
phoneNumber VARCHAR,
propertyId INTEGER
);