DROP TABLE IF EXISTS sub_services;
CREATE TABLE daotalk.sub_services
(
    sub_id INT(11),
    service_id INT(11)DEFAULT '0',
    deleted TINYINT(1) DEFAULT '0'
);