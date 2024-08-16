-- Insertar en la tabla country
INSERT INTO country (codeCountry, name) VALUES ('US', 'United States');
INSERT INTO country (codeCountry, name) VALUES ('MX', 'Mexico');

-- Insertar en la tabla unitmeasurement
INSERT INTO unitmeasurement (name) VALUES ('Milligram');
INSERT INTO unitmeasurement (name) VALUES ('Milliliter');

-- Insertar en la tabla modeadmin
INSERT INTO modeadmin (description) VALUES ('Oral');
INSERT INTO modeadmin (description) VALUES ('Topical');

-- Insertar en la tabla activeprinciple
INSERT INTO activeprinciple (name) VALUES ('Paracetamol');
INSERT INTO activeprinciple (name) VALUES ('Ibuprofen');

-- Insertar en la tabla region
INSERT INTO region (codReg, name, codeCountry) VALUES ('TX', 'Texas', 'US');
INSERT INTO region (codReg, name, codeCountry) VALUES ('CDMX', 'Ciudad de Mexico', 'MX');

-- Insertar en la tabla city
INSERT INTO city (codCity, name, codReg) VALUES ('AUS', 'Austin', 'TX');
INSERT INTO city (codCity, name, codReg) VALUES ('CEN', 'Centro', 'CDMX');

-- Insertar en la tabla laboratory
INSERT INTO laboratory (name, codCity) VALUES ('Pfizer', 'AUS');
INSERT INTO laboratory (name, codCity) VALUES ('Genomma Lab', 'CEN');

-- Insertar en la tabla pharmacy
INSERT INTO pharmacy (name, addres, longi, lat, logo, codCity) VALUES ('Walgreens', '123 Main St', -97.7431, 30.2672, 'walgreens.png', 'AUS');
INSERT INTO pharmacy (name, addres, longi, lat, logo, codCity) VALUES ('Farmacias del Ahorro', 'Av. Insurgentes 456', -99.1332, 19.4326, 'ahorro.png', 'CEN');

-- Insertar en la tabla customer
INSERT INTO customer (idCust, name, lastName, email, birthDate, longi, lat, codCity) VALUES ('C001', 'John', 'Doe', 'john.doe@example.com', '1985-05-20', -97.7431, 30.2672, 'AUS');
INSERT INTO customer (idCust, name, lastName, email, birthDate, longi, lat, codCity) VALUES ('C002', 'Maria', 'Perez', 'maria.perez@example.com', '1990-08-15', -99.1332, 19.4326, 'CEN');

-- Insertar en la tabla medicine
INSERT INTO medicine (procedings, name, healthregister, description, desShort, nameRol, idMode, idum, idLab, idap) 
VALUES ('PROC001', 'Tylenol', 'HR001', 'Pain reliever', 'Pain relief', 'Analgesic', 1, 1, 1, 1);
INSERT INTO medicine (procedings, name, healthregister, description, desShort, nameRol, idMode, idum, idLab, idap) 
VALUES ('PROC002', 'Advil', 'HR002', 'Anti-inflammatory', 'Reduces inflammation', 'NSAID', 2, 2, 2, 2);

-- Insertar en la tabla pharmacymedicine
INSERT INTO pharmacymedicine (idPha, idMed, price) VALUES (1, 1, 4.99);
INSERT INTO pharmacymedicine (idPha, idMed, price) VALUES (2, 2, 5.99);
