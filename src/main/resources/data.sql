INSERT INTO country(name, iso_code) VALUES ('Latvia', 'LV');
INSERT INTO country(name, iso_code) VALUES ('Lithuania', 'LT');
INSERT INTO country(name, iso_code) VALUES ('Estonia', 'EE');
INSERT INTO country(name, iso_code) VALUES ('Finland', 'FI');
INSERT INTO country(name, iso_code) VALUES ('Austria', 'AT');
INSERT INTO country(name, iso_code) VALUES ('France', 'FR');
INSERT INTO country(name, iso_code) VALUES ('Malta', 'MT');
INSERT INTO country(name, iso_code) VALUES ('Japan', 'JP');
INSERT INTO country(name, iso_code) VALUES ('Italy', 'IT');
INSERT INTO country(name, iso_code) VALUES ('Lebanon', 'LB');
INSERT INTO country(name, iso_code) VALUES ('Romania', 'RO');
INSERT INTO country(name, iso_code) VALUES ('Mexico', 'MX');
INSERT INTO country(name, iso_code) VALUES ('Russian Federation', 'RU');
INSERT INTO country(name, iso_code) VALUES ('United States of America', 'USA');

INSERT INTO person(name, surname, personal_number) VALUES ('Arturs', 'Burkans', '020498-12812');
INSERT INTO person(name, surname, personal_number) VALUES ('Valters', 'Abols', '010392-12313');
INSERT INTO person(name, surname, personal_number) VALUES ('Kalvis', 'Bumbiers', '120691-12414');
INSERT INTO person(name, surname, personal_number) VALUES ('Raimonds', 'Uzgrieznis', '020693-12516');
INSERT INTO person(name, surname, personal_number) VALUES ('Austris', 'Koks', '010693-122516');
INSERT INTO person(name, surname, personal_number) VALUES ('Laimonis', 'Taure', '030693-32516');
INSERT INTO person(name, surname, personal_number) VALUES ('Igors', 'Irsis', '040693-42516');
INSERT INTO person(name, surname, personal_number) VALUES ('Maksims', 'Tavarish', '050693-82516');

INSERT INTO blacklist(person_id) VALUES ('1');
INSERT INTO blacklist(person_id) VALUES ('2');
INSERT INTO blacklist(person_id) VALUES ('3');

INSERT INTO user(user_name) VALUES ('Raitis');
INSERT INTO user(user_name) VALUES ('Igors');
INSERT INTO user(user_name) VALUES ('Admin');

INSERT INTO loan(amount, term, person_id, country_id) VALUES ('100.00', 'Second loan', '4', '1');
INSERT INTO loan(amount, term, person_id, country_id) VALUES ('200.00', 'Second loan', '5', '1');
INSERT INTO loan(amount, term, person_id, country_id) VALUES ('200000.00', 'Mortgage loan', '6', '1');
INSERT INTO loan(amount, term, person_id, country_id) VALUES ('74000.00', 'Mortgage loan', '7', '1');
INSERT INTO loan(amount, term, person_id, country_id) VALUES ('1200.00', 'Car leasing', '8', '2');

INSERT INTO userloans(loan_id, user_id) VALUES ('1','1');
INSERT INTO userloans(loan_id, user_id) VALUES ('2','1');
INSERT INTO userloans(loan_id, user_id) VALUES ('3','2');
INSERT INTO userloans(loan_id, user_id) VALUES ('4','2');
INSERT INTO userloans(loan_id, user_id) VALUES ('5','1');
