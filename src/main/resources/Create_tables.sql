DROP TABLE IF EXISTS daotalk.abonents;
DROP TABLE IF EXISTS daotalk.tel_service;
DROP TABLE IF EXISTS daotalk.sub_services;

CREATE TABLE daotalk.abonents
(
  first_name VARCHAR(45) NOT NULL,
  second_name VARCHAR(45) NOT NULL,
  contract INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  balance DOUBLE DEFAULT '0',
  password VARCHAR(45) NOT NULL,
  login VARCHAR(45) NOT NULL,
  admin TINYINT(1) DEFAULT '0' NOT NULL,
  blocked TINYINT(1) DEFAULT '0' NOT NULL,
  deleted TINYINT(1) DEFAULT '0' NOT NULL
);
CREATE TABLE daotalk.sub_services
(
  sub_id INT(11),
  service_id INT(11) DEFAULT '1',
  deleted TINYINT(1) DEFAULT '0'
);
CREATE TABLE daotalk.tel_service
(
  name VARCHAR(45) NOT NULL,
  price DOUBLE NOT NULL,
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  deleted TINYINT(1) DEFAULT '0' NOT NULL,
  edit TINYINT(1) DEFAULT '0'
);

# CREATE UNIQUE INDEX abonents_login_uindex ON abonents (login);
# CREATE UNIQUE INDEX users_contract_uindex ON abonents (contract);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('Default Service',0,0);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('3g',75,0);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('3g+',85,0);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('4g+',100,0);
INSERT INTO daotalk.sub_services (sub_id, service_id, deleted) VALUES (1, 1, 0);
INSERT INTO daotalk.abonents (first_name,second_name,login, password, admin,contract) VALUES ('Admin','Admin','Admin', 'e3afed0047b08059d0fada10f400c1e5', TRUE , 0);