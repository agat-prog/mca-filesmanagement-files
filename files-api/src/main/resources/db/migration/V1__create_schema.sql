-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: Files
-- ------------------------------------------------------
-- Server version	5.7.13-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `DOCUMENT`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `DOCUMENT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `DOCUMENT_CODE_IDX` (`CODE`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DOCUMENT`
--

LOCK TABLES `DOCUMENT` WRITE;
/*!40000 ALTER TABLE `DOCUMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `DOCUMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FILE`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `FILE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(100) NOT NULL,
  `USER` varchar(20) NOT NULL,
  `ID_INIT_OPTION` int(11) NOT NULL,
  `ACTIVE` bit(1) NOT NULL DEFAULT b'0',
  `DESCRIPTION` longtext,
  `PHASE_ACTUAL` varchar(100) NOT NULL,
  `PROCES_CODE` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `FILE_PROCES_CODE_IDX` (`PROCES_CODE`) USING BTREE,
  UNIQUE KEY `FILE_CODE_IDX` (`CODE`) USING BTREE,
  KEY `FILE_FK` (`ID_INIT_OPTION`),
  CONSTRAINT `FILE_FK` FOREIGN KEY (`ID_INIT_OPTION`) REFERENCES `INIT_OPTION` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FILE`
--

LOCK TABLES `FILE` WRITE;
/*!40000 ALTER TABLE `FILE` DISABLE KEYS */;
/*!40000 ALTER TABLE `FILE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FILE_DOCUMENT`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `FILE_DOCUMENT` (
  `FILE_ID` int(11) NOT NULL,
  `DOCUMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`FILE_ID`,`DOCUMENT_ID`),
  KEY `FILE_DOCUMENT_FK` (`DOCUMENT_ID`),
  CONSTRAINT `FILE_DOCUMENT_FK` FOREIGN KEY (`DOCUMENT_ID`) REFERENCES `DOCUMENT` (`ID`),
  CONSTRAINT `FILE_DOCUMENT_FK_1` FOREIGN KEY (`FILE_ID`) REFERENCES `FILE` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FILE_DOCUMENT`
--

LOCK TABLES `FILE_DOCUMENT` WRITE;
/*!40000 ALTER TABLE `FILE_DOCUMENT` DISABLE KEYS */;
/*!40000 ALTER TABLE `FILE_DOCUMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `INIT_OPTION`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `INIT_OPTION` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(100) NOT NULL,
  `CODE` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `INIT_OPTION_CODE_IDX` (`CODE`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `INIT_OPTION`
--

LOCK TABLES `INIT_OPTION` WRITE;
INSERT IGNORE INTO `PHASES` SET `ID` = 1, `CODE` = 'SEDE', `DESCRIPTION` = 'Sede electrónica';
INSERT IGNORE INTO `PHASES` SET `ID` = 2, `CODE` = 'PETICION_DTO', `DESCRIPTION` = 'A petición departamento';
/*!40000 ALTER TABLE `INIT_OPTION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (9);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 18:11:10
