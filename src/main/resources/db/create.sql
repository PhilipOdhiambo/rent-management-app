SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS properties (
 id int PRIMARY KEY auto_increment,
 name VARCHAR,
 type VARCHAR,
 location VARCHAR,
 description VARCHAR,
 rent INTEGER
);
