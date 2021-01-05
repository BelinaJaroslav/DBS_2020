-- todo replace with actual insert script

begin tran T1;

insert into doctors (name, surname, salary) values ('Jan', 'Novák', '65000');
insert into doctors (name, surname, salary) values ('Josef', 'Frýdek', '62350');
insert into doctors (name, surname, salary) values ('Bořivoj', 'Čech', '82000');
insert into doctors (name, surname, salary) values ('Vojtěch', 'Přibyl', '56000');
insert into doctors (name, surname, salary) values ('Simon', 'Hunyad', '67500');
insert into doctors (name, surname, salary) values ('Jiří', 'Starý', '52000');
insert into doctors (name, surname, salary) values ('Robert', 'Chlup', '45000');
insert into doctors (name, surname, salary) values ('Adéla', 'Krönhofer', '56000');
insert into doctors (name, surname, salary) values ('Anna', 'Potáhalová', '64500');
insert into doctors (name, surname, salary) values ('Petr', 'Šmíd', '71200');
insert into doctors (name, surname, salary) values ('Pavel', 'Pěšinka', '39500');
insert into doctors (name, surname, salary) values ('Dominika', 'Fullerová', '48000');
insert into doctors (name, surname, salary) values ('Bernard', 'Kreis', '62000');
insert into doctors (name, surname, salary) values ('Nikola', 'Greisů', '80000');

insert into vaccines (name, manufacturer, price) values ('AstraZaneca COVID-19 vaccine', 'AstraZaneca', 970);
insert into vaccines (name, manufacturer, price) values ('Janssen’s COVID-19 vaccine', 'Janssen', 399);
insert into vaccines (name, manufacturer, price) values ('Novavax’s COVID-19 vaccine', 'Novavax', 353);
insert into vaccines (name, manufacturer, price) values ('Moderna’s COVID-19 vaccine', 'Moderna', 488);
insert into vaccines (name, manufacturer, price) values ('Pfizer-BioNTech COVID-19 vaccine', 'Moderna-BioNtech', 828);
insert into vaccines (name, manufacturer, price) values ('Sputnik V', 'Gamaleya Research Institute', 746);
insert into vaccines (name, manufacturer, price) values ('BBIBP-CorV', 'Beijing Institute of Biological Products', 975);
insert into vaccines (name, manufacturer, price) values ('CoronaVac', 'Sinovac', 772);
insert into vaccines (name, manufacturer, price) values ('EpiVacCorona', 'FBRISRC of Virology and Biotechnology', 759);
insert into vaccines (name, manufacturer, price) values ('RandoCoVac', 'African bureau of Biotechnology', 486);

insert into patients (name, surname, birth_number) values ('David', 'Kolka', '8406091749');
insert into patients (name, surname, birth_number) values ('Kamila', 'Kusínová', '7751023555');
insert into patients (name, surname, birth_number) values ('Albert', 'Plný', '6402280170');
insert into patients (name, surname, birth_number) values ('Kateřina', 'Murná', '456016383');
insert into patients (name, surname, birth_number) values ('Kateřina', 'Zídková', '8853286277');
insert into patients (name, surname, birth_number) values ('Joela', 'Newman', '8055129082');
insert into patients (name, surname, birth_number) values ('Andrea', 'Kuncová', '7958036383');
insert into patients (name, surname, birth_number) values ('Boris', 'Angyal', '8305258038');
insert into patients (name, surname, birth_number) values ('Karel', 'Hanykýř', '7411073780');
insert into patients (name, surname, birth_number) values ('Irena', 'Šmidílková', '446125780');
insert into patients (name, surname, birth_number) values ('Krystýna', 'Angyal', '6653182602');
insert into patients (name, surname, birth_number) values ('Iva', 'Malá', '9755273869');
insert into patients (name, surname, birth_number) values ('Melánie', 'Koulouchová', '6653171899');
insert into patients (name, surname, birth_number) values ('Issa', 'Mussoli', '0212073780');
insert into patients (name, surname, birth_number) values ('Jan', 'Kolář', '8505076096');
insert into patients (name, surname, birth_number) values ('Lenka', 'Nováková', '8155195686');
insert into patients (name, surname, birth_number) values ('Michal', 'Pavouk', '7807229353');
insert into patients (name, surname, birth_number) values ('Hubert', 'Bednář', '8606119709');
insert into patients (name, surname, birth_number) values ('Daniela', 'Stehlíková', '8558139931');
insert into patients (name, surname, birth_number) values ('Martin', 'Koller', '7804139915');
insert into patients (name, surname, birth_number) values ('Gerhart', 'Berger', '450204938');
insert into patients (name, surname, birth_number) values ('Lucie', 'Ester', '9457104360');
insert into patients (name, surname, birth_number) values ('Štefan', 'Koloc', '5905190742');
insert into patients (name, surname, birth_number) values ('David', 'Miner', '6502159774');
insert into patients (name, surname, birth_number) values ('František', 'Doležal', '0411091296');

insert into hospitals (name, city) values ('Nemocnice Rudolfa a Stefanie Benešov', 'Benešov');
insert into hospitals (name, city) values ('Oblastní nemocnice Kladno, a.s.', 'Kladno');
insert into hospitals (name, city) values ('Oblastní nemocnice Kolín - nemocnice Středočeského kraje, a.s.', 'Kolín 3');
insert into hospitals (name, city) values ('Nemocnice Prachatice, a.s.', 'Prachatice 2');
insert into hospitals (name, city) values ('Fakultní nemocnice u sv. Anny v Brně ', 'Brno');
insert into hospitals (name, city) values ('Vojenská nemocnice Brno ', 'Brno-Zábrdovice');
insert into hospitals (name, city) values ('Nemocnice Valtice, s.r.o.', 'Valtice');
insert into hospitals (name, city) values ('Karlovarská krajská nemocnice, a.s. ', 'Karlovy Vary');
insert into hospitals (name, city) values ('Fakultní nemocnice Hradec Králové ', 'Hradec Králové');
insert into hospitals (name, city) values ('Nemocnice Náchod ', 'Náchod');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('1', '100');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('1', '101');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('1', '102');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('1', '103');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('1', '104');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('1', '105');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('2', '100');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('2', '104');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('2', '108');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('2', '109');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('3', '114');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('3', '112');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('4', '106');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('4', '107');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('4', '112');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('5', '109');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('6', '110');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('6', '111');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('7', '111');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('8', '113');
insert into hospitals_doctos (hospital_id, doctor_id)
values ('8', '114');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('9', '106');

insert into hospitals_doctos (hospital_id, doctor_id)
values ('10', '114');

insert into registered_vaccinations (patient_id,vaccine_id,doctor_id, completed, time)
values ('1', '10','100', '1',  '2021-02-01 10:50:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('2', '10', '100', '1', '2021-02-01 10:55:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('3', '10', '112', '1', '2021-02-02 11:30:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('4', '12', '111', '1', '2021-02-05 12:50:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('5', '10', '108', '1', '2021-02-10 15:00:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('6', '10', '100', '1', '2021-02-12 10:15:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('7', '10', '100', '1', '2021-02-13 10:25:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('8', '10', '100', '1', '2021-02-14 12:20:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('9', '10', '100', '1', '2021-02-20 12:50:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('10', '10', '100', '1', '2021-02-28 8:00:00');
insert into registered_vaccinations (patient_id,vaccine_id,doctor_id, completed, time)
values ('11', '10','100', '1',  '2021-03-01 8:25:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('12', '10', '100', '1', '2021-03-01 8:30:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('13', '10', '100', '1', '2021-03-01 10:00:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('14', '10', '100', '1', '2021-03-02 8:50:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('15', '10', '100', '1', '2021-03-20 12:00:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('16', '10', '100', '1', '2021-02-28 8:00:00');
insert into registered_vaccinations (patient_id,vaccine_id,doctor_id, completed, time)
values ('17', '10','100', '1',  '2021-04-05 8:25:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('18', '10', '100', '1', '2021-04-05 8:30:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('19', '10', '100', '1', '2021-04-25 10:15:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('20', '10', '100', '1', '2021-10-02 8:00:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('21', '10', '100', '1', '2021-10-03 8:50:00');
insert into registered_vaccinations (patient_id,vaccine_id,doctor_id, completed, time)
values ('17', '10','100', '1',  '2021-010-05 12:25:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('18', '10', '100', '1', '2021-11-06 10:30:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('19', '10', '100', '1', '2021-11-25 10:15:00');
insert into registered_vaccinations (patient_id, vaccine_id, doctor_id, completed, time)
values ('20', '10', '100', '1', '2021-11-26 13:00:00');

go

commit tran T1;
