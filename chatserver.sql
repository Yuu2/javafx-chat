CREATE DATABASE  IF NOT EXISTS `chatserver` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `chatserver`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: chatserver
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `blacklist`
--

DROP TABLE IF EXISTS `blacklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blacklist` (
  `ip` varchar(20) NOT NULL,
  PRIMARY KEY (`ip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blacklist`
--

LOCK TABLES `blacklist` WRITE;
/*!40000 ALTER TABLE `blacklist` DISABLE KEYS */;
INSERT INTO `blacklist` VALUES ('123.123.123.123');
/*!40000 ALTER TABLE `blacklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_info`
--

DROP TABLE IF EXISTS `log_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_info` (
  `log_date` datetime NOT NULL,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `ip` varchar(20) NOT NULL,
  `LOG` varchar(50) DEFAULT NULL,
  KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_info`
--

LOCK TABLES `log_info` WRITE;
/*!40000 ALTER TABLE `log_info` DISABLE KEYS */;
INSERT INTO `log_info` VALUES ('2019-03-04 16:19:09','A','127.0.0.1:12113','/w B');
/*!40000 ALTER TABLE `log_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice_info`
--

DROP TABLE IF EXISTS `notice_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_info` (
  `user_name` varchar(20) NOT NULL,
  `notice_message` varchar(100) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_info`
--

LOCK TABLES `notice_info` WRITE;
/*!40000 ALTER TABLE `notice_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_info`
--

DROP TABLE IF EXISTS `room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_info` (
  `room_no` int(11) NOT NULL AUTO_INCREMENT,
  `room_title` varchar(20) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `room_curCount` smallint(6) DEFAULT NULL,
  `room_limitCount` smallint(6) DEFAULT NULL,
  `room_type` varchar(5) DEFAULT NULL,
  `room_pw` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`room_no`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `room_info_ibfk_1` FOREIGN KEY (`user_name`) REFERENCES `user_info` (`user_name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_info`
--

LOCK TABLES `room_info` WRITE;
/*!40000 ALTER TABLE `room_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_list`
--

DROP TABLE IF EXISTS `room_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room_list` (
  `user_name` varchar(20) NOT NULL,
  `room_no` int(11) DEFAULT NULL,
  `c1` varchar(20) DEFAULT NULL,
  `c2` varchar(20) DEFAULT NULL,
  `c3` varchar(20) DEFAULT NULL,
  `c4` varchar(20) DEFAULT NULL,
  `c5` varchar(20) DEFAULT NULL,
  `c6` varchar(20) DEFAULT NULL,
  `c7` varchar(20) DEFAULT NULL,
  `c8` varchar(20) DEFAULT NULL,
  `c9` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_list`
--

LOCK TABLES `room_list` WRITE;
/*!40000 ALTER TABLE `room_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_name` varchar(20) NOT NULL,
  `ip/port` varchar(20) NOT NULL,
  `room_no` int(11) DEFAULT NULL,
  `connectDate` datetime DEFAULT NULL,
  `disconnectDate` datetime DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  KEY `room_no` (`room_no`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`room_no`) REFERENCES `room_info` (`room_no`),
  CONSTRAINT `user_info_ibfk_2` FOREIGN KEY (`room_no`) REFERENCES `room_info` (`room_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'chatserver'
--

--
-- Dumping routines for database 'chatserver'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-04 16:27:01
