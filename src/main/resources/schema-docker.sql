#create database if not exists demo;
use demo;
DROP TABLE IF EXISTS User;

CREATE TABLE IF NOT EXISTS User (
                      userId int NOT NULL AUTO_INCREMENT,
                      firstName varchar(255),
                      lastName varchar(255),
                      login varchar(255),
                      PRIMARY KEY (userId)
);
