-- Insert data into country table
INSERT INTO country (codeCountry, name)
VALUES
('US', 'United States'),
('MX', 'Mexico');

-- Insert data into region table
INSERT INTO region (codReg, name, codeCountry)
VALUES
('CA', 'California', 'US'),
('JAL', 'Jalisco', 'MX');

-- Insert data into city table
INSERT INTO city (codCity, name, codReg)
VALUES
('LA', 'Los Angeles', 'CA'),
('GDL', 'Guadalajara', 'JAL');

-- Insert data into unitmeasurement table
INSERT INTO unitmeasurement (name)
VALUES
('Milligram'),
('Milliliter');

-- Insert data into modeadmin table
INSERT INTO modeadmin (description)
VALUES
('Oral Administration'),
('Topical Application');

-- Insert data into activeprinciple table
INSERT INTO activeprinciple (name)
VALUES
('Ibuprofen'),
('Paracetamol');

-- Insert data into laboratory table
INSERT INTO laboratory (name, codCity)
VALUES
('Pfizer Inc.', 'LA'),
('Laboratorios Liomont', 'GDL');

-- Insert data into pharmacy table
INSERT INTO pharmacy (name, addres, long, lat, logo, codCity)
VALUES
('CVS Pharmacy', '123 Main St, Los Angeles, CA', -118.2437, 34.0522, 'cvs_logo.png', 'LA'),
('Farmacia Guadalajara', 'Av. Vallarta 1234, Guadalajara, Jalisco', -103.3918, 20.6767, 'farmacia_gdl_logo.png', 'GDL');

-- Insert data into customer table
INSERT INTO customer (idCust, name, lastName, email, birthDate, long, lat, codCity)
VALUES
('CUST001', 'John', 'Doe', 'john.doe@example.com', '1985-05-15', -118.2437, 34.0522, 'LA'),
('CUST002', 'Maria', 'Gonzalez', 'maria.gonzalez@example.com', '1990-08-22', -103.3918, 20.6767, 'GDL');

-- Insert data into medicine table
INSERT INTO medicine (procedings, name, healthregister, description, desShort, nameRol, idMode, idum, idLab)
VALUES
('M-001', 'Ibuprofen 200mg', 'HR12345', 'Used to reduce fever and treat pain or inflammation.', 'Pain reliever', 'Tablet', 1, 1, 1),
('M-002', 'Paracetamol 500mg', 'HR54321', 'Commonly used for pain relief and fever reduction.', 'Fever reducer', 'Tablet', 1, 1, 2);

-- Insert data into pharmacymedicine table
INSERT INTO pharmacymedicine (idPha, idMed)
VALUES
(1, 1), -- CVS Pharmacy sells Ibuprofen 200mg
(2, 2); -- Farmacia Guadalajara sells Paracetamol 500mg
