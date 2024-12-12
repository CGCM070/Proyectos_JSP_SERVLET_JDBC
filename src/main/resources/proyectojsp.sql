DROP DATABASE IF EXISTS proyectojsp;
CREATE DATABASE proyectojsp;
USE proyectojsp;

CREATE TABLE users (
                       userID INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin'),
 ('hash', 'd04b98f48e8f8bcc15c6ae5ac050801cd6dcfd428fb5f9e65c4e16e7807340fa');