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
-- Table structure for table `dang_nhap`
--

DROP TABLE IF EXISTS `dang_nhap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dang_nhap` (
  `Mã Nhân Viên` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Tên Nhân Viên` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Tên Đăng Nhập` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Mật Khẩu` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Địa Chỉ` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SĐT` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `Loại Tài Khoản` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`Mã Nhân Viên`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dang_nhap`
--

LOCK TABLES `dang_nhap` WRITE;
/*!40000 ALTER TABLE `dang_nhap` DISABLE KEYS */;
INSERT INTO `dang_nhap` VALUES ('nv001','Vương Lâm','vuonglam','001','Hà Nội','09812345678','user'),('nv002','Phan Tân','takkun yukijo','007','Hòa Bình','01221234567','admin');
/*!40000 ALTER TABLE `dang_nhap` ENABLE KEYS */;
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
