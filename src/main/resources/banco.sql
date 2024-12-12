
DROP DATABASE  IF EXISTS banco;

CREATE DATABASE banco;

USE banco;

CREATE TABLE cliente (
                         clienteID INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         direccion VARCHAR(255),
                         telefono VARCHAR(20),
                         fechaNacimiento DATE
);