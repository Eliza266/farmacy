CREATE DATABASE IF NOT EXISTS farmay;
USE farmay;

CREATE TABLE country(
    codeCountry VARCHAR(5),
    name VARCHAR(50),
    CONSTRAINT PK_codeCountry PRIMARY KEY (codeCountry)
);

CREATE TABLE unitmeasurement(
    idum INT AUTO_INCREMENT,
    name VARCHAR(50),
    CONSTRAINT PK_Idum PRIMARY KEY (idum)
);

CREATE TABLE modeadmin(
    idMode INT AUTO_INCREMENT,
    description VARCHAR(60),
    CONSTRAINT PK_IdMode PRIMARY KEY (idMode)
);

CREATE TABLE activeprinciple(
    idap INT AUTO_INCREMENT,
    name VARCHAR(60),
    CONSTRAINT PK_idap PRIMARY KEY (idap)
);

CREATE TABLE region(
    codReg VARCHAR(5),
    name VARCHAR(60),
    codeCountry VARCHAR(5),
    CONSTRAINT PK_codReg PRIMARY KEY (codReg),
    CONSTRAINT fk_codeCountry_country FOREIGN KEY (codeCountry) REFERENCES country(codeCountry)
);

CREATE TABLE city(
    codCity VARCHAR(5),
    name VARCHAR(50),
    codReg VARCHAR(5),
    CONSTRAINT PK_codCity PRIMARY KEY (codCity),
    CONSTRAINT fk_codReg_region FOREIGN KEY (codReg) REFERENCES region(codReg)
);

CREATE TABLE laboratory(
    idLab INT AUTO_INCREMENT,
    name VARCHAR(50),
    codCity VARCHAR(5),
    CONSTRAINT PK_idLab PRIMARY KEY (idLab),
    CONSTRAINT fk_codCity_city_LAB FOREIGN KEY (codCity) REFERENCES city(codCity)
);

CREATE TABLE pharmacy(
    idPha INT AUTO_INCREMENT,
    name VARCHAR(60),
    addres VARCHAR(100),
    longi FLOAT(8),
    lat FLOAT(8),
    logo VARCHAR(50),
    codCity VARCHAR(5),
    CONSTRAINT PK_idPha PRIMARY KEY (idPha),
    CONSTRAINT fk_codCity_city_Phar FOREIGN KEY (codCity) REFERENCES city(codCity)
);

CREATE TABLE customer(
    idCust VARCHAR(20),
    name VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(100),
    birthDate DATE,
    longi FLOAT(8),
    lat FLOAT(8),
    codCity VARCHAR(5),
    CONSTRAINT PK_idCust PRIMARY KEY (idCust),
    CONSTRAINT fk_codCity_city_CUS FOREIGN KEY (codCity) REFERENCES city(codCity)
);

CREATE TABLE medicine(
    idMed INT AUTO_INCREMENT,
    procedings VARCHAR(10),
    name VARCHAR(100),
    healthregister VARCHAR(500),
    description TEXT,
    desShort VARCHAR(60),
    nameRol VARCHAR(100),
    idMode INT,
    idum INT,
    idLab INT,
    idap INT,
    CONSTRAINT PK_idMed PRIMARY KEY (idMed),
    CONSTRAINT fk_idMode_modAdmin_medicine FOREIGN KEY (idMode) REFERENCES modeadmin(idMode),
    CONSTRAINT fk_idum_unitMeas_medicine FOREIGN KEY (idum) REFERENCES unitmeasurement(idum),
    CONSTRAINT fk_idLab_labs_medicine FOREIGN KEY (idLab) REFERENCES laboratory(idLab),
    CONSTRAINT fk_idap_activeprinciple_medicine FOREIGN KEY (idap) REFERENCES activeprinciple(idap)
);

CREATE TABLE pharmacymedicine(
    idPha INT,
    idMed INT,
    price DECIMAL(10,2),
    CONSTRAINT PK_pharmacy_medicine PRIMARY KEY (idPha, idMed),
    CONSTRAINT fk_idPha_pharmacy FOREIGN KEY (idPha) REFERENCES pharmacy(idPha),
    CONSTRAINT fk_idMed_medicine FOREIGN KEY (idMed) REFERENCES medicine(idMed)
);
