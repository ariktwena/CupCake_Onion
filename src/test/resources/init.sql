DROP DATABASE IF EXISTS cupcaketest;
CREATE DATABASE cupcaketest;

USE cupcaketest;

CREATE TABLE properties
(
    name  VARCHAR(255) PRIMARY KEY,
    value VARCHAR(255) NOT NULL
);

INSERT INTO properties (name, value)
VALUES ('version', '0');