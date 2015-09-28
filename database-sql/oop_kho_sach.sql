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
-- Table structure for table `kho_sach`
--

DROP TABLE IF EXISTS `kho_sach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kho_sach` (
  `Tên SP` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Mã SP` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Tên T/G` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Thể Loại` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Giá Bán` double NOT NULL,
  `Số Lượng` int(11) NOT NULL,
  `Ngày Nhập` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Số Phiếu` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Chiết Khấu` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kho_sach`
--

LOCK TABLES `kho_sach` WRITE;
/*!40000 ALTER TABLE `kho_sach` DISABLE KEYS */;
INSERT INTO `kho_sach` VALUES ('Vật Lý Đại Cương 3','VL1','Phan Tân','Khoa Học',40000,3,'20/11/2014','VL101',0,1),('Cách chăn rau','CR01','Đặng Văn Đại','Nông Nghiệp',36000,90,'20/11/2014','NN101',0,2),('Trà sữa chân trâu','TS1','Phan Tân','Ẩm Thực',40000,90,'20/11/2014','AMT101',0,3),('99aaaa','89898','9898','98989',989898,89898,'11/12/2014','9898988',98,4),('Vật Lý Đại Cương 3','VL1','Phan Tân','Khoa Học',40000,3,'20/11/2014','VL101',0,5),('Cách chăn rau','CR01','Đặng Văn Đại','Nông Nghiệp',36000,90,'20/11/2014','NN101',0,6),('Trà sữa chân trâu','TS1','Phan Tân','Ẩm Thực',40000,90,'20/11/2014','AMT101',0,7),('LÝ 3','L3','tan phan','khoa hoc',90000,19,'20/11/2014','9898988',98,8),('Vật Lý Đại Cương 3','VL1','Phan Tân','Khoa Học',40000,3,'20/11/2014','VL101',0,10),('Cách chăn rau','CR01','Đặng Văn Đại','Nông Nghiệp',36000,90,'20/11/2014','NN101',0,11),('Trà sữa chân trâu','TS1','Phan Tân','Ẩm Thực',40000,90,'20/11/2014','AMT101',0,12),('99aaaa','89898','9898','98989',989898,89898,'11/12/2014','aaaa',98,13);
/*!40000 ALTER TABLE `kho_sach` ENABLE KEYS */;
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
