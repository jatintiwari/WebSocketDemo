CREATE DATABASE  IF NOT EXISTS `websocket` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `websocket`;
-- MySQL dump 10.13  Distrib 5.6.17, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: websocket
-- ------------------------------------------------------
-- Server version	5.6.19

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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dateCreated` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1440911851321,'abc','ADMIN','abc','','abc','def'),(2,1440911851321,'jatin','ADMIN','jatin','','jatin',NULL),(3,1440911851321,'rajesh','ADMIN','rajesh','','Rajesh','Upadhayaya');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webmessage`
--

DROP TABLE IF EXISTS `webmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `webmessage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` bigint(20) DEFAULT NULL,
  `fromUser` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `toUser` varchar(255) DEFAULT NULL,
  `messageRead` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webmessage`
--

LOCK TABLES `webmessage` WRITE;
/*!40000 ALTER TABLE `webmessage` DISABLE KEYS */;
INSERT INTO `webmessage` VALUES (70,NULL,'jatin','123',1441381778555,'abc',''),(71,NULL,'abc','123',1441381867282,'jatin',''),(72,NULL,'abc','123',1441381950925,'jatin',''),(73,NULL,'jatin','123',1441382068035,'abc',''),(74,NULL,'abc','now?',1441382131129,'jatin',''),(75,NULL,'jatin','no!??',1441382237113,'abc',''),(76,NULL,'jatin','now?',1441382371138,'abc',''),(77,NULL,'abc','yo!',1441382886281,'jatin',''),(78,NULL,'abc','yo!!',1441383075604,'jatin','');
/*!40000 ALTER TABLE `webmessage` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-06 11:55:23
