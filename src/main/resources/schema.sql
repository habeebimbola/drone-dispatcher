DROP TABLE IF EXISTS MY_DRONE;
--DROP TABLE IF EXISTS MEDICATION;
--DROP TABLE IF EXISTS DRONE_MEDICATION;

CREATE TABLE MY_DRONE( ID INT NOT NULL PRIMARY KEY);

--create table DRONE
--(
--  ID INTEGER IDENTITY PRIMARY KEY,
--  SERIAL_NO VARCHAR(100) NOT NULL,
--  MODEL SMALLINT NOT NULL,
--  WEIGHT_LIMIT DECIMAL NOT NULL,
--  BATTERY_CAPACITY DECIMAL NOT NULL
--);
--
--CREATE TABLE MEDICATION(
--ID INTEGER IDENTITY NOT NULL PRIMARY KEY,
--NAME IDENTITY VARCHAR(100) NOT NULL,
--WEIGHT DECIMAL NOT NULL,
--CODE VARCHAR(100) NOT NULL
--);

--CREATE TABLE DRONE_MEDICATION
--(
--   CREATION_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--   DRONE_ID INTEGER FOREIGN KEY REFERENCES DRONE(DRONE_ID),
--   MEDICATION_ID INTEGER FOREIGN KEY REFERENCES MEDICATION(MEDICATION_ID)
--);