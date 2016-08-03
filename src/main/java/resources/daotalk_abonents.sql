-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: daotalk
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abonents`
--

DROP TABLE IF EXISTS daotalk.abonents;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE daotalk.abonents (
  `first_name` varchar(45) NOT NULL,
  `second_name` varchar(45) NOT NULL,
  `contract` int(11) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT '0',
  `password` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `current_service` varchar(45) DEFAULT 'Default Service',
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `blocked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`contract`),
  UNIQUE KEY `users_contract_uindex` (`contract`),
  UNIQUE KEY `abonents_login_uindex` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonents`
--

LOCK TABLES daotalk.abonents WRITE;
/*!40000 ALTER TABLE daotalk.abonents DISABLE KEYS */;
INSERT INTO daotalk.abonents VALUES ('Иван','Петров',0,0,'pass','Ivan','Default Service',1,0),('Steve','Pet',13,891,'1','1','3g+',0,0),('Semen','Slepakov',14,0,'s','s','Default Service',0,0),('Piter','Griffin',15,-50,'g','p','Default Service',0,0),('Oleg','Valuev',16,-20,'v','o','Default Service',0,1),('1','2',17,0,'qwe','qwe','Default Service',0,0);
/*!40000 ALTER TABLE daotalk.abonents ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-29 23:42:31
