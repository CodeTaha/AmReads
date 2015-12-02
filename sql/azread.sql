-- MySQL dump 10.13  Distrib 5.6.19, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: azread
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `client_tbl`
--

DROP TABLE IF EXISTS `client_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_tbl` (
  `client_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_secret` varchar(300) DEFAULT NULL,
  `client_name` varchar(45) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_tbl`
--

LOCK TABLES `client_tbl` WRITE;
/*!40000 ALTER TABLE `client_tbl` DISABLE KEYS */;
INSERT INTO `client_tbl` VALUES (1,'cs1',NULL,NULL,NULL),(2,'cs2',NULL,NULL,NULL),(3,'flnvntben99vvnsc00830f6ok3','client_name','mosh','address'),(4,'d0o69nqf2hq0crtnq2148vkcig','flipkart','flipkart.com','mumbai'),(5,'mqmvetahps05failmmd94upinf','snapdeal','snapdeal.com','mumbai'),(6,'r6cnb73kjs4f5t63rq4rlughnj','Myntra','myntra.com','indore'),(7,'p7gtagb4ep3g08h806fa3lu46s','taha','taha.com','India'),(8,'pb7a0pgsig9cedotn0r8btpq09','AmReads','http://localhost:8084/BookRead/','Helsinki');
/*!40000 ALTER TABLE `client_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_tbl`
--

DROP TABLE IF EXISTS `purchase_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_tbl` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `isbn` varchar(45) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `redirect_url` varchar(45) DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_tbl`
--

LOCK TABLES `purchase_tbl` WRITE;
/*!40000 ALTER TABLE `purchase_tbl` DISABLE KEYS */;
INSERT INTO `purchase_tbl` VALUES (1,'0747584664',8.99,'No Details','81p6atjbjtli3r2avc6m3da4pr',1),(2,'0439708184',10.99,'No Details','dm6jutt8ab81q8thduekdd22s2',1),(3,'0439708184',10.99,'No Details','afb7tgvinou4bq11sa08omb5ic',1),(4,'0439708184',10.99,'No Details','35rq790ciae0qlnqrljc25goec',0),(5,'0439708184',10.99,'No Details','8ncdrub50lr5e7q69kvmuh4ued',1);
/*!40000 ALTER TABLE `purchase_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `Transaction_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_id` int(11) DEFAULT NULL,
  `amount` double NOT NULL,
  `red_url` varchar(100) DEFAULT NULL,
  `flag` int(11) DEFAULT '0',
  `Activation_url` varchar(1000) DEFAULT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `trans_details` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`Transaction_id`),
  KEY `fk_Transaction_tbl_1_idx` (`client_id`),
  CONSTRAINT `fk_Transaction_tbl_1` FOREIGN KEY (`client_id`) REFERENCES `client_tbl` (`client_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,1,10,'yellowkorp.com',0,'taha','2015-11-10 21:07:08',NULL),(4,1,123,'www.red.com',0,'random_Auth','2015-11-11 00:30:20',NULL),(6,1,123,'https://www.red.com',1,'t0ddg9u8bkn2qqnhg276tqosnf','2015-11-14 16:38:37','{\"trans_details\":0}'),(7,1,123,'www.red.com',0,'t0ddg9u8bkn2qqnhg276tqosnf2','2015-11-11 17:33:04',NULL),(8,1,123,'www.red.com',0,'t0ddg9u8bkn2qqnhg276tqosnf1','2015-11-11 17:33:04',NULL),(9,1,100,'http://www.twitter.com',0,'mkl0irntibu7fef8c6jtdjikld','2015-11-13 23:57:41',NULL),(11,3,444,'http://www.flipkart.com',0,'69qiggo6vrflp9nb49jbdsnolg','2015-11-14 18:05:58',NULL),(12,3,444,'http://www.flipkart.com',0,'7iv9pilfsfrt4kups3h2hqop5b','2015-11-14 21:08:12',NULL),(13,4,555,'http://www.flipkarts.com',1,'2li0higb412tb6kld5vcfsg63a','2015-11-14 22:06:17','{\"trans_details\":0}'),(14,4,666,'http://www.flipkarts.com',0,'gnu9a6um383i5kg7r48vulmbh0','2015-11-14 22:07:17',NULL),(15,4,666,'http://www.flipkarts.com',0,'2vor22b6tte85bkqlkmtr33pbo','2015-11-14 22:11:45',NULL),(16,5,100,'http://www.twitter.com',0,'cq03ddn47iegg7qt5tv6rfbm3h','2015-11-14 22:33:41',NULL),(17,5,785,'facebook.com',0,'b99geh9q0fbqd4piufptvs8ce6','2015-11-14 22:50:32',NULL),(18,5,1234,'facebook.com',0,'j1a0gvhel5srrhr34jhh7esd1r','2015-11-15 00:55:52','something'),(19,5,1000,'google.com',1,'88itmppvl4gvn61q1mf1f3l754','2015-11-14 23:23:31','this'),(20,4,13,'http://www.flipkarts.com',0,'5i764rakhn459calouvumo1jjg','2015-12-01 16:10:24',NULL),(21,4,12.77,'http://www.flipkarts.com',1,'gbk2eh137maqds5j5vto10ieeq','2015-12-01 16:15:27','{\"trans_details\":0}'),(22,8,10.99,'http://www.yellowkorp.com',0,'28nivnr96fq5clk7ogbjfkd5ad','2015-12-01 17:29:33',NULL),(23,8,10.99,'http://www.yellowkorp.com',0,'c9tak3rnd61bn6c01cka3lhkbs','2015-12-01 17:31:10',NULL),(24,8,10.99,'http://www.yellowkorp.com',0,'1g04dotp7t9sdp32qiamatlnut','2015-12-01 17:42:00',NULL),(25,8,8.99,'http://www.yellowkorp.com',0,'46gdu7b9ddnvg1jspg30ansohn','2015-12-01 17:43:32',NULL),(26,8,8.99,'http://www.yellowkorp.com',1,'5mpn0ipl3bgtdsp0soiebupajk','2015-12-01 17:57:15','{\"trans_details\":0}'),(27,8,8.99,'http://localhost:8084/AmRead/complete.jsp?trans=81p6atjbjtli3r2avc6m3da4pr',1,'cuu40u9r9mbua1delnlje98s79','2015-12-01 19:13:34','{\"trans_details\":0}'),(28,8,10.99,'http://localhost:8084/AmRead/complete.jsp?trans=dm6jutt8ab81q8thduekdd22s2',1,'j00c7hglp0ofcad3jh78caevcj','2015-12-01 21:42:11','{\"trans_details\":0}'),(29,8,10.99,'http://localhost:8084/AmRead/complete.jsp?trans=afb7tgvinou4bq11sa08omb5ic',1,'kov6tj2a32hsulnscqiork4nvc','2015-12-02 17:25:08','{\"trans_details\":0}'),(30,8,10.99,'http://localhost:8084/AmRead/complete.jsp?trans=35rq790ciae0qlnqrljc25goec',0,'9pasipcmc30f7k19b1t0vn7kt3','2015-12-02 20:42:42',NULL),(31,8,10.99,'http://localhost:8084/AmRead/complete.jsp?trans=8ncdrub50lr5e7q69kvmuh4ued',1,'vh8r3n3gcv8th8q8aarrbt959c','2015-12-02 22:03:52','{\"trans_details\":0}');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'azread'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-03  0:53:22
