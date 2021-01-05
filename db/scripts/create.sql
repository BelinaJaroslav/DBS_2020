-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2021-01-03 17:55:12.197

-- tables
-- Table: doctors
CREATE TABLE doctors
(
    id      int      NOT NULL IDENTITY (100,1),
    name    nvarchar(50) NOT NULL,
    surname nvarchar(50) NOT NULL,
    salary  int      NULL,
    CONSTRAINT doctors_pk PRIMARY KEY (id)
)

-- Table: hospitals
CREATE TABLE hospitals
(
    id   int      NOT NULL IDENTITY (1,1),
    name nvarchar(50) NOT NULL,
    city nvarchar(50) NOT NULL,
    CONSTRAINT hospitals_pk PRIMARY KEY (id)
)

-- Table: hospitals_doctors
CREATE TABLE hospitals_doctors
(
    id          int NOT NULL IDENTITY,
    hospital_id int NOT NULL,
    doctor_id   int NOT NULL,
    CONSTRAINT hospitals_doctors_pk PRIMARY KEY (id)
)

-- Table: patients
CREATE TABLE patients
(
    id           int NOT NULL IDENTITY,
    name         int NOT NULL,
    surname      int NOT NULL,
    birth_number bigint NOT NULL,
    CONSTRAINT patients_birth_number UNIQUE (birth_number),
    CONSTRAINT patients_pk PRIMARY KEY (id)
)

-- Table: registered_vaccinations
CREATE TABLE registered_vaccinations
(
    id         int      NOT NULL IDENTITY (1000,1),
    patient_id int      NOT NULL,
    vaccine_id int      NOT NULL,
    doctor_id  int      NOT NULL,
    completed  bit      NOT NULL,
    time       datetime NOT NULL,
    CONSTRAINT registered_vaccinations_pk PRIMARY KEY (id)
)

-- Table: vaccines
CREATE TABLE vaccines
(
    id           int      NOT NULL IDENTITY (10,2),
    name         nvarchar(50) NOT NULL,
    manufacturer nvarchar(50) NOT NULL,
    price        int      NOT NULL,
    CONSTRAINT vaccines_pk PRIMARY KEY (id)
)

-- foreign keys
-- Reference: hospitals_doctors_doctors (table: hospitals_doctors)
ALTER TABLE hospitals_doctors
    ADD CONSTRAINT hospitals_doctors_doctors
        FOREIGN KEY (doctor_id)
            REFERENCES doctors (id)
            ON DELETE CASCADE

-- Reference: hospitals_doctors_hospitals (table: hospitals_doctors)
ALTER TABLE hospitals_doctors
    ADD CONSTRAINT hospitals_doctors_hospitals
        FOREIGN KEY (hospital_id)
            REFERENCES hospitals (id)

-- Reference: registered_vaccination_doctors (table: registered_vaccinations)
ALTER TABLE registered_vaccinations
    ADD CONSTRAINT registered_vaccination_doctors
        FOREIGN KEY (doctor_id)
            REFERENCES doctors (id)

-- Reference: registered_vaccination_patients (table: registered_vaccinations)
ALTER TABLE registered_vaccinations
    ADD CONSTRAINT registered_vaccination_patients
        FOREIGN KEY (patient_id)
            REFERENCES patients (id)
            ON DELETE CASCADE

-- Reference: registered_vaccination_vaccines (table: registered_vaccinations)
ALTER TABLE registered_vaccinations
    ADD CONSTRAINT registered_vaccination_vaccines
        FOREIGN KEY (vaccine_id)
            REFERENCES vaccines (id)

-- End of file.