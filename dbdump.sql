-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_RPRBaza1
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `film`
--

DROP TABLE IF EXISTS `film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `film` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ime` varchar(45) DEFAULT NULL,
  `zanr` varchar(45) DEFAULT NULL,
  `trajanje` int DEFAULT NULL,
  `cijena` int DEFAULT NULL,
  `broj_sale` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `film`
--

LOCK TABLES `film` WRITE;
/*!40000 ALTER TABLE `film` DISABLE KEYS */;
INSERT INTO `film` VALUES (2,'PUT OKO SVIJETA ZA 80 DANA','ANIMIRANI',82,5,3),(3,'AVATAR: PUT VODE','AVANTURA',192,10,4),(5,'DIVLJA NOC','KOMEDIJA',111,10,2),(38,'BLACK PANTHER: WAKANDA ZAUVIJEK','AKCIJA',161,12,5),(39,'ASTERIX & OBELIX: SRENDJE KRALJEVSTVO','AVANTURA',111,12,4),(40,'ANT-MAN I WASP: KVANTUMANIJA','AKCIJA',125,14,1),(41,'BABYLON','DRAMA',189,8,5),(42,'VJENCANJE ZA UMRIJETI','KOMEDIJA',100,10,1),(43,'TROUGAO TUGE','DRAMA',147,8,2);
/*!40000 ALTER TABLE `film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `karta`
--

DROP TABLE IF EXISTS `karta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `karta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `film_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_karta_film_idx` (`film_id`),
  KEY `fk_karta_kupac_idx` (`user_id`),
  CONSTRAINT `fk_karta_film` FOREIGN KEY (`film_id`) REFERENCES `film` (`id`),
  CONSTRAINT `fk_karta_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `karta`
--

LOCK TABLES `karta` WRITE;
/*!40000 ALTER TABLE `karta` DISABLE KEYS */;
INSERT INTO `karta` VALUES (45,4,5),(46,4,43),(47,4,40),(48,4,2),(49,5,38),(50,5,38),(51,5,41),(52,5,41),(53,8,40),(54,8,42),(55,7,40),(56,7,3);
/*!40000 ALTER TABLE `karta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `ime` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `adresa` varchar(45) DEFAULT NULL,
  `grad` varchar(45) DEFAULT NULL,
  `datum_rodjenja` date DEFAULT NULL,
  `admin` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'khalilovic','password','Kerim Halilovic','khalilovic@gmail.com','Ferhadija 1','Sarajevo','2022-12-03',1),(2,'ahodzic','password','Amar Hodzic','ahodzic@gmail.com','Ferhadija 5','Sarajevo','2022-05-15',1),(3,'kahmetovic','password','Kemal Ahmetovic','kahmetovic@gmail.com','Kosevo 30','Sarajevo','2000-10-05',1),(4,'edzeko','password','Edin Dzeko','edzeko@gmail.com','Zvornicka 301','','2022-12-31',0),(5,'ecivic','password','Eldar Civic','ecivic@gmail.com','Drinska 22','Sarajevo','2022-12-15',0),(7,'admin','admin','admin','admin@admin.com','admin 1','admin','2022-12-22',0),(8,'proba','proba','proba','proba','proba','proba','2022-12-15',0),(9,'bakir','bakir','bakir','bakir','bakir@gmail.com','sarajevo','2002-03-26',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-01-22  0:21:38
