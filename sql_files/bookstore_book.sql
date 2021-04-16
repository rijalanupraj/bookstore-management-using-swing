-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: bookstore
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isbn` varchar(25) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `published_date` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `num_available` int DEFAULT NULL,
  `num_sold` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ISBN_UNIQUE` (`isbn`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (10,'978-3-16-148410-0','Harry Potter','JK Rowling','Bloomsbury','2001-01-01',700,20,0),(11,'968-3-16-149410-0','Da Vinci Code','Dan Brown','Bloomsbury','2010-09-08',1000.9,100,0),(12,'888-3-11-448410-0','Origin','Dan Brown','Bloomsbury','2018-09-06',1500,20,0),(13,'978-3-16-14897-0','Inferno','Dan Brown','Bloomsbury','2017-09-08',890.99,30,20),(14,'978-3-16-158410-0','Getting Things Done','David Alled','Janta','2001-09-08',89.09,10,NULL),(15,'978-3-16-248410-0','Ready For Anything','David Allen','Janta','2006-12-09',760.99,10,NULL),(16,'976-3-16-148410-0','Five Point Someone','Chetan Bhagat','Janta','2004-04-03',500.5,20,NULL),(17,'978-3-16-148910-0','Half Girlfriend','Chetan Bhagat','Janta','2020-03-09',900,35,5),(18,'978-3-16-198410-0','2 States','Chetan Bhagat','Ekta','2009-06-01',650,5,NULL),(19,'978-2-16-148410-0','Himself','Jess Kidd','Bam','2012-09-03',899.99,2,NULL),(20,'978-3-16-138410-0','How to win friends','Dale Carnegie','Ekta','2009-06-22',798.9,30,NULL),(21,'978-3-16-140410-0','Think and Grow Rich','Napoleon Hill','Ekta','1937-08-09',2000,20,NULL),(22,'978-3-16-128410-0','The Law of success','Napoleon Hill','Ramita','1928-06-25',600,20,NULL),(23,'978-3-16-148419-0','Muna Madan','Laxmi Prasad','Ekta','1960-09-01',600,19,1),(24,'978-3-16-148440-0','Champa','Laxmi Prasad','Ekta','2009-09-06',89,20,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-16 16:39:36
