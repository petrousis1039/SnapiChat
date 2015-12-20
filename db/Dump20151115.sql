-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: snapchatdb
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `passwordhash` varchar(150) NOT NULL,
  `user_email` varchar(45) NOT NULL,
  `user_phone_num` varchar(50) DEFAULT NULL,
  `user_city` varchar(45) DEFAULT NULL,
  `user_country` varchar(45) DEFAULT NULL,
  `user_photo` blob,
  `userfirstname` varchar(50) DEFAULT NULL,
  `userlastname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usernameIn` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'na','1000:471cee224a73c16495e5f35ede52074ff792f13a8abd3cf9:d68aa6453adc07f87849944096cc93ace2f9fc91a407d35e','nat@nat.gr','','','0','','na','na'),(2,'nat','1000:7ec74a0663562fceb26f5ea5c5c3d1917084d969c2f6f791:d15714911a56064ab539f836af0b9e990203865b94e8f7ee','nat@nat.gr','','','0','','nat','nat'),(3,'alex','1000:b213abd5bc2a610c4ab67d8be20d1da3bdd6707a0832cd39:d8fc0a76bc286d4d6d22ade2414114b272c50f649dec2a28','alex@al.gr','','','0','','alex','alex'),(4,'nat','1000:0f865e3b0e01510b55b77f1c447163d9896a06d9a3ce2baf:7c22cb5f6cc4c31cd8f6669db6880d467b6f8dcea07c99bc','nat@nat.gr','','','0','','nat','nat'),(5,'natasia','1000:b736d2237eaab0b5821d0b4ad60cd6e1370b7c90ea502a4b:cfeab5331bebf0fc14f8fd67a951f1872bb90f0be6798fec','natgoutzel@hotmail.com','','','0','','natasia','natasia`'),(6,'na','1000:e62df2dda1d0c0b6d7d254f877680fe174ee365317da16e8:b187046001b2a929cb96fe9ae0f37d94d7fe81b3c63ecdff','nat@nat.gr','','','0','','na','na'),(7,'nat','1000:f67e428e0c33b101d267225f91d1b1ca83222d2f8fb0cb9e:becb8c2137b5f2a86faa364b632c61482e1c8b040100ae79','nat@nat.gr','','','0','','nat','nat');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_friendlist`
--

DROP TABLE IF EXISTS `user_friendlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_friendlist` (
  `userid1` int(11) NOT NULL,
  `userid2` int(11) NOT NULL,
  KEY `fk1_idx` (`userid1`),
  KEY `fk2_idx` (`userid2`),
  CONSTRAINT `fk1` FOREIGN KEY (`userid1`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk2` FOREIGN KEY (`userid2`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_friendlist`
--

LOCK TABLES `user_friendlist` WRITE;
/*!40000 ALTER TABLE `user_friendlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_friendlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_story_messages`
--

DROP TABLE IF EXISTS `user_story_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_story_messages` (
  `idmessage` int(11) NOT NULL AUTO_INCREMENT,
  `iduser` int(11) NOT NULL,
  `message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idmessage`),
  KEY `iduser_idx` (`iduser`),
  CONSTRAINT `iduser` FOREIGN KEY (`iduser`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_story_messages`
--

LOCK TABLES `user_story_messages` WRITE;
/*!40000 ALTER TABLE `user_story_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_story_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_story_pictures`
--

DROP TABLE IF EXISTS `user_story_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_story_pictures` (
  `idpictures` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) NOT NULL,
  `story_picture` longtext DEFAULT NULL,
  PRIMARY KEY (`idpictures`),
  KEY `id_user_idx` (`id_user`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_story_pictures`
--

LOCK TABLES `user_story_pictures` WRITE;
/*!40000 ALTER TABLE `user_story_pictures` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_story_pictures` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-15 17:56:03
