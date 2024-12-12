DROP DATABASE IF EXISTS proyectojsp;
CREATE DATABASE proyectojsp;
USE proyectojsp;

CREATE TABLE users (
                       userID INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');