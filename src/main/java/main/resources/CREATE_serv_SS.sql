DROP TABLE IF EXISTS daotalk.sub_services;
CREATE TABLE daotalk.sub_services
(
    sub_id INT(11),
    service_id INT(11)DEFAULT '0',
    deleted TINYINT(1) DEFAULT '0'
);
DROP TABLE IF EXISTS daotalk.tel_service;
CREATE TABLE daotalk.tel_service
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    price DOUBLE NOT NULL,
    deleted TINYINT(1) DEFAULT '0'
);
CREATE UNIQUE INDEX tel_service_id_uindex ON tel_service (id);
DROP TABLE IF EXISTS daotalk.abonents;
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
  deleted TINYINT(1) DEFAULT '0'
);