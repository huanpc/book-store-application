CREATE DATABASE  IF NOT EXISTS `oop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `oop`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: oop
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `kho_cd`
--

DROP TABLE IF EXISTS `kho_cd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kho_cd` (
  `Tên SP` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Mã SP` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Tên Ca Sĩ` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Tên Nhạc Sĩ` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Giá Bán` double NOT NULL,
  `Số Lượng` int(11) NOT NULL,
  `Thể Loại` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Năm Phát Hành` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Ngày Nhập` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Số Phiếu` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Chiết Khấu` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kho_cd`
--

LOCK TABLES `kho_cd` WRITE;
/*!40000 ALTER TABLE `kho_cd` DISABLE KEYS */;
INSERT INTO `kho_cd` VALUES ('Flute cover 123','FL1','Phan Tân','Phan Tân',90000,90,'Không lời','2014','20/11/2014','KL101',0,1),('aaaaaaaaaaa','90909','90','90909',909090,90909,'90909','09090','09090','999090',0,2),('sáo trúc','fl1','999','99999',9999,9999,'kl','999','999','99',99,19),('hòa tấu','aaa','aaaa','aaaa',9999999999,909090,'aaaaa','909090','909090','909090',9090,20);
/*!40000 ALTER TABLE `kho_cd` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-16 22:52:59
