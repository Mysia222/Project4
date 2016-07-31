DROP TABLE IF EXISTS sub_services;
CREATE TABLE sub_services
(
    sub_id INT(11),
    service_id INT(11),
    deleted TINYINT(1) DEFAULT '0'
);