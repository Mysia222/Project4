

DROP TABLE IF EXISTS daotalk.sub_services;
DROP TABLE IF EXISTS daotalk.abonents;
DROP TABLE IF EXISTS daotalk.abonent_info;
DROP TABLE IF EXISTS daotalk.tel_service;


CREATE TABLE daotalk.tel_service
(
  name VARCHAR(45) NOT NULL,
  price DOUBLE NOT NULL,
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  deleted TINYINT(1) DEFAULT '0' NOT NULL,
  edit TINYINT(1) DEFAULT '0'
);



CREATE TABLE daotalk.abonent_info
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(45) NOT NULL,
  second_name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  login VARCHAR(45) NOT NULL,
  deleted TINYINT(1) DEFAULT '0' NOT NULL

);
CREATE TABLE daotalk.abonents
(
  contract INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  balance DOUBLE DEFAULT '0',
  admin TINYINT(1) DEFAULT '0' NOT NULL,
  blocked TINYINT(1) DEFAULT '0' NOT NULL,
  deleted TINYINT(1) DEFAULT '0' NOT NULL,
  FOREIGN KEY (contract)
  REFERENCES daotalk.abonent_info (id)
);
CREATE TABLE daotalk.sub_services (
  service_id INT(11) NOT NULL DEFAULT '1',
  sub_id INT(11) NOT NULL,
  #   INDEX fk_sub_services_tel_service1_idx (service_id ASC),
  #   INDEX fk_sub_services_abonents1_idx (sub_id ASC),
  PRIMARY KEY (sub_id, service_id),
  #   CONSTRAINT fk_sub_services_tel_service1
  FOREIGN KEY (service_id)
  REFERENCES daotalk.tel_service (id),
  #     ON DELETE NO ACTION
  #     ON UPDATE NO ACTION,
  #   CONSTRAINT fk_sub_services_abonents1
  FOREIGN KEY (sub_id)
  REFERENCES daotalk.abonents (contract)
  #     ON DELETE NO ACTION
  #     ON UPDATE NO ACTION
);
# 'Admin','Admin','Admin', 'e3afed0047b08059d0fada10f400c1e5',
# CREATE UNIQUE INDEX abonents_login_uindex ON abonents (login);
# CREATE UNIQUE INDEX users_contract_uindex ON abonents (contract);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('Default Service',0,0);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('3g',75,0);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('3g+',85,0);
INSERT INTO daotalk.tel_service  (name, price, id) VALUES ('4g+',100,0);

INSERT INTO daotalk.abonent_info (first_name,second_name, login,password,id)
VALUES ('Admin','Admin','Admin', 'e3afed0047b08059d0fada10f400c1e5',1);
INSERT INTO daotalk.abonents (contract,admin) VALUES ( 1,TRUE );
INSERT INTO daotalk.sub_services (sub_id, service_id) VALUES (1, 1);

