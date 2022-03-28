-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 192.168.219.103    Database: shxdb
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
-- Table structure for table `CODE_CHILD`
--

DROP TABLE IF EXISTS `CODE_CHILD`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CODE_CHILD` (
  `PARENT_CODE` varchar(6) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '부모코드',
  `CHILD_CODE` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '자식코드',
  `CHILD_NAME` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '코드명',
  `SORT_ORDER` int DEFAULT NULL COMMENT '출력순서',
  `USE_YN` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '사용여부',
  PRIMARY KEY (`PARENT_CODE`,`CHILD_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='자식코드';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CODE_CHILD`
--

LOCK TABLES `CODE_CHILD` WRITE;
/*!40000 ALTER TABLE `CODE_CHILD` DISABLE KEYS */;
INSERT INTO `CODE_CHILD` VALUES ('DOC001','0001','제증명',NULL,'Y'),('DOC001','0002','휴가신청서',NULL,'Y'),('DOC002','0001','결재대기',NULL,'Y'),('DOC002','0002','결재완료',NULL,'Y'),('DOC002','0003','반려',NULL,'Y'),('DOC002','0004','임시저장',NULL,'Y'),('FRM001','daum.net','daum.net',4,'Y'),('FRM001','gmail.com','gmail.com',1,'Y'),('FRM001','hanmail.net','hanmail.net',3,'Y'),('FRM001','hotmail.com','hotmail.com',6,'Y'),('FRM001','nate.com','nate.com',5,'Y'),('FRM001','naver.com','naver.com',2,'Y'),('FRM001','user','직접입력',99,'Y'),('LVE001','A001','년차',NULL,'Y'),('LVE001','A002','병가',NULL,'Y'),('LVE001','A003','훈련',NULL,'Y'),('LVE002','D001','전일',NULL,'Y'),('LVE002','D002','오전',NULL,'Y'),('LVE002','D003','오후',NULL,'Y'),('POS001','1000','회장',1,'N'),('POS001','2000','사장',2,'N'),('POS001','3000','전무이사',3,'Y'),('POS001','4000','상무이사',4,'Y'),('POS001','5000','부장',5,'Y'),('POS001','6000','차장',6,'Y'),('POS001','7000','과장',7,'Y'),('POS001','8000','대리',8,'Y'),('POS001','9000','사원',9,'Y'),('POS003','POS001','일반직',1,'Y'),('POS003','POS002','연구직',2,'Y'),('ROLE','ADMIN','관리자',1,'Y'),('ROLE','MANAGER','매니저',2,'Y'),('ROLE','USER','일반사용자',3,'Y');
/*!40000 ALTER TABLE `CODE_CHILD` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-28 22:31:20
