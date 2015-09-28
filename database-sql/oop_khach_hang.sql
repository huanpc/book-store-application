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
-- Table structure for table `khach_hang`
--

DROP TABLE IF EXISTS `khach_hang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `khach_hang` (
  `makh` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tenkh` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sdt` varchar(11) COLLATE utf8_unicode_ci DEFAULT NULL,
  `diachi` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tichdiem` int(11) DEFAULT NULL,
  PRIMARY KEY (`makh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khach_hang`
--

LOCK TABLES `khach_hang` WRITE;
/*!40000 ALTER TABLE `khach_hang` DISABLE KEYS */;
INSERT INTO `khach_hang` VALUES ('20121181','Đặng Phương Anh 123','1659630604','Yên Bái',100000),('20121224','Nguyễn Tuấn Anh','968648043','Unknow',100000),('20121246','Bùi Thị ánh','1667019741','Unknow',100000),('20121340','Nguyễn Sỹ Thành Công','1688416901','Unknown',10000000),('20121351','Lưu Đức Cương','1674313908','Unknow',1872368),('20121373','Trần Văn Cường','979456907','Unknow',100000),('20121387','Nguyễn Xuân Duẩn','979678943','Unknow',100000),('20121403','Phan Văn Duy','979868036','Unknow',100000),('20121469','Đặng Văn Đại','1659683065','hà nội',100000),('20121499','Nguyễn Văn Đạt','1626458626','Unknow',100000),('20121540','Hoàng Văn Được','1636641482','Unknow',100000),('20121647','Nguyễn Văn Hải','963889834','Unknow',100000),('20121687','Nguyễn Thị Ngọc Hiếu','897198273','Unknow',100000),('20121694','Phạm Ngọc Hiếu','1686172589','Unknow',100000),('20121757','Phùng Thế Hoàng','8279817214','Unknow',20000000),('20121776','Phan Công Huân','1632305708','Unknow',100000),('20121814','Đồng Thị Huyền','987889419','Unknow',100000),('20121830','Nguyễn Duy Hùng','1665937926','Unknow',100000),('20121900','Đỗ Văn Khanh','164789065','Unknow',100000),('20121946','Nguyễn Tuấn Kiên','1639042050','Unknow',100000),('20121963','Vương  Lâm','1636003801','Unknow',100000),('20121976','Đỗ Xuân Linh','984759056','Unknow',100000),('20122000','Cao Văn Long','985419212','Unknow',100000),('20122023','Tạ Văn Lợi','982701049','Unknow',100000),('20122024','Bùi Ngọc Luân','1659856822','Unknow',100000),('20122032','Lê Văn Luật','973550054','Unknow',100000),('20122042','Dương Văn Lực','1654921661','Unknow',100000),('20122049','Phạm Thị Lý','162703114','Unknow',100000),('20122073','Đỗ Đức Minh','987198237','Unknow',100000),('20122087','Nguyễn     Văn Minh','1645509447','Unknow',100000),('20122327','Trần Đức Sang','943560758','Unknow',100000),('20122332','Dương Hùng Sơn','1665325593','Unknow',100000),('20122379','Đàm Khắc Tạo','1699764707','Unknow',100000),('20122389','Phan  Tân','1686613731','Unknow',90000000),('20122401','Nguyễn Văn Thanh','917505994','Unknow',100000),('20122442','Nguyễn Quang Thái','979369407','Unknow',100000),('20122529','Phạm Văn Thuyết','972781395','Unknow',100000),('20122547','Lê Mạnh Tiến','1253828422','Unknow',100000),('20122581','Phạm Văn Toản','974060451','Unknow',100000),('20122620','Lê Quang Trung','972365092','Unknow',100000),('20122687','Nguyễn Anh Tuấn','1659693759','Unknow',100000),('20122698','Nguyễn Minh Tuấn','982947835','Unknow',100000),('20122767','Ngô Minh Tú','1638558868','Unknow',100000),('20124971','Bùi Huy Châu','977579430','Unknow',100000),('20124973','Lê Hồng Duy','966987293','Unknow',100000),('20124974','Lê Hồng Duy','966987293','Unknown',1000000),('CH001','Nguyễn Viết CHương','09349023','Giải Phóng, hà Nội',15000000),('KH000','Đặng Văn Đại','0123456789','Giải Phóng, Hà Nội',200000),('KH001','Phan Tân','0712638763','Phương Mat',300000),('KH002','Hoàng Tất Thành','0712638763','Phương Mai, hà nội',300000),('KH003','Hoàng Tất Thành','0712638763','Phương Mai, hà nội',300000);
/*!40000 ALTER TABLE `khach_hang` ENABLE KEYS */;
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
