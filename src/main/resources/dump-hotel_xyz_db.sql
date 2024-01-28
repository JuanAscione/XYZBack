-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (arm64)
--
-- Host: localhost    Database: hotel_xyz_db
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL,
  `random_password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'juanignacio.ascione@gmail.com','Juan','Ascione','12341234','324'),(18,'juanignacio.ascione76@gmail.com','Juan','Ascione','0789469830','379'),(19,'gascione@gmail.com','Gabriela','Ascione','0642617452','966');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_rooms`
--

DROP TABLE IF EXISTS `reservation_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_rooms` (
  `reservation_id` bigint NOT NULL,
  `has_air_conditioner` bit(1) NOT NULL,
  `has_bathtub` bit(1) NOT NULL,
  `has_double_bed` bit(1) NOT NULL,
  `has_minibar` bit(1) NOT NULL,
  `hastv` bit(1) NOT NULL,
  `has_terrace` bit(1) NOT NULL,
  `has_wifi` bit(1) NOT NULL,
  `price_per_night` int DEFAULT NULL,
  `type` enum('STANDARD','SUITE','SUPERIOR') DEFAULT NULL,
  KEY `FK96vqam6q3l5mic9bf9unp1q9j` (`reservation_id`),
  CONSTRAINT `FK96vqam6q3l5mic9bf9unp1q9j` FOREIGN KEY (`reservation_id`) REFERENCES `reservations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_rooms`
--

LOCK TABLES `reservation_rooms` WRITE;
/*!40000 ALTER TABLE `reservation_rooms` DISABLE KEYS */;
INSERT INTO `reservation_rooms` VALUES (5,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',50,'STANDARD'),(5,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',50,'STANDARD'),(7,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',50,'STANDARD'),(14,_binary '',_binary '\0',_binary '',_binary '',_binary '',_binary '\0',_binary '',100,'SUPERIOR'),(15,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',50,'STANDARD'),(16,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',50,'STANDARD'),(17,_binary '\0',_binary '\0',_binary '\0',_binary '\0',_binary '',_binary '\0',_binary '',50,'STANDARD'),(18,_binary '',_binary '\0',_binary '',_binary '',_binary '',_binary '\0',_binary '',100,'SUPERIOR');
/*!40000 ALTER TABLE `reservation_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_date` datetime(6) DEFAULT NULL,
  `number_of_nights` int DEFAULT NULL,
  `status` tinyint DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `client_email` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlxqvprnylshu592y86sm9nm8i` (`client_email`),
  CONSTRAINT `FKlxqvprnylshu592y86sm9nm8i` FOREIGN KEY (`client_email`) REFERENCES `clients` (`id`),
  CONSTRAINT `reservations_chk_1` CHECK ((`status` between 0 and 2))
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (5,'2024-02-10 00:00:00.000000',3,0,300,1),(7,'2024-02-02 00:00:00.000000',2,1,100,1),(14,'2024-02-08 00:00:00.000000',1,0,100,1),(15,'2024-01-31 00:00:00.000000',1,0,50,1),(16,'2024-02-10 00:00:00.000000',1,0,50,1),(17,'2024-02-01 00:00:00.000000',1,0,50,1),(18,'2024-02-09 00:00:00.000000',1,0,100,1);
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallets`
--

DROP TABLE IF EXISTS `wallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallets` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` decimal(38,2) DEFAULT NULL,
  `currency` varchar(3) DEFAULT NULL,
  `client_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h1msak25um4x4pxmxytm0vho0` (`client_id`),
  CONSTRAINT `FKrog16b0y1jucy75kpvij66b80` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallets`
--

LOCK TABLES `wallets` WRITE;
/*!40000 ALTER TABLE `wallets` DISABLE KEYS */;
INSERT INTO `wallets` VALUES (14,325.00,'EUR',1);
/*!40000 ALTER TABLE `wallets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotel_xyz_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-28 21:37:21
