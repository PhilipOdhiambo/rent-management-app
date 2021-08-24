SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS property (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 type VARCHAR,
 location VARCHAR,
 description VARCHAR,
 rent INTEGER
);
