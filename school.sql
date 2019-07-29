-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: school
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade` varchar(4) NOT NULL COMMENT '年级，如2015级',
  `class_number` int(4) NOT NULL COMMENT '班级，如1班，2班',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (13,'2015',1),(14,'2015',2),(24,'2019',5),(37,'2019',1),(38,'2019',2);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'语文'),(2,'数学'),(3,'英语');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course1`
--

DROP TABLE IF EXISTS `course1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `course1` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程名称',
  `open` int(11) NOT NULL DEFAULT '1',
  `teacher_id` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course1`
--

LOCK TABLES `course1` WRITE;
/*!40000 ALTER TABLE `course1` DISABLE KEYS */;
INSERT INTO `course1` VALUES (1,'程序设计',1,'T08110175'),(2,'Java',1,'T08110176'),(3,'高等数学',1,'T08110177'),(4,'线性代数',1,'T08110179'),(5,'概率论',1,'T09120016'),(6,'软件工程',1,'T09120019'),(7,'软件测试',1,'T09120025'),(8,'软件需求',1,'T13121215'),(9,'数据结构',1,'T16112255');
/*!40000 ALTER TABLE `course1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `score` (
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` varchar(9) NOT NULL COMMENT '学号',
  `score_number` int(11) NOT NULL COMMENT ' 成绩',
  `rank_in_class` int(11) NOT NULL DEFAULT '0',
  `rank_in_grade` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`course_id`,`student_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,'151220077',66,2,7),(1,'151220084',89,1,2),(1,'151220101',87,3,4),(1,'151220102',88,2,3),(1,'151220104',88,2,3),(1,'151220108',-1,1,1),(1,'151220112',85,4,5),(1,'151221155',75,5,6),(1,'151332211',60,6,8),(1,'174112200',100,1,1),(2,'151220077',85,2,4),(2,'151220084',89,1,3),(2,'151220101',89,3,3),(2,'151220102',75,5,6),(2,'151220104',75,5,6),(2,'151220108',-1,1,1),(2,'151220112',89,3,3),(2,'151221155',77,4,5),(2,'151332211',100,1,1),(2,'174112200',96,2,2),(3,'151220077',85,2,5),(3,'151220084',89,1,2),(3,'151220101',78,4,6),(3,'151220102',87,3,4),(3,'151220104',57,6,8),(3,'151220108',-1,1,1),(3,'151220112',60,5,7),(3,'151221155',88,2,3),(3,'151332211',98,1,1),(3,'174112200',98,1,1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `id` varchar(9) NOT NULL COMMENT '学号',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `gender` varchar(2) NOT NULL COMMENT '性别',
  `class_id` int(11) DEFAULT NULL COMMENT '学生所属班级，外键所引导class表的id字段',
  PRIMARY KEY (`id`),
  KEY `student` (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('151220077','小芳芳','女',14),('151220084','乔韦龙','男',14),('151220101','张佳','女',13),('151220102','李俊杰','男',13),('151220104','赵娜','女',13),('151220108','静静','女',24),('151220112','王强','男',13),('151221155','王五','男',13),('151332211','李丽丽','女',13),('174112200','王五','男',13);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `id` varchar(9) NOT NULL COMMENT '老师编号',
  `name` varchar(30) NOT NULL COMMENT '老师姓名',
  `gender` varchar(2) NOT NULL COMMENT '性别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('T08110175','孙悦','女'),('T08110176','张英军','男'),('T08110177','王俊','男'),('T08110179','张丽娟','女'),('T09120016','李强','男'),('T09120019','赵丽','女'),('T09120025','王秀秀','女'),('T13121215','吴军','男'),('T16112255','赵静','女');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher_class`
--

DROP TABLE IF EXISTS `teacher_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher_class` (
  `class_id` int(11) NOT NULL COMMENT '班级编号',
  `course_id` int(11) NOT NULL,
  `teacher_id` varchar(9) NOT NULL COMMENT '老师编号',
  `advisor` int(1) NOT NULL COMMENT '是否为班主任，1代表是班主任，2代表是普通任课老师',
  PRIMARY KEY (`class_id`,`course_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher_class`
--

LOCK TABLES `teacher_class` WRITE;
/*!40000 ALTER TABLE `teacher_class` DISABLE KEYS */;
INSERT INTO `teacher_class` VALUES (13,1,'T08110175',1),(13,2,'T08110176',0),(13,3,'T08110177',0),(14,1,'T08110179',1),(14,2,'T08110177',0),(14,3,'T09120019',0),(24,1,'T09120025',0),(24,2,'T13121215',1),(24,3,'T16112255',0),(37,1,'T13121215',0),(37,2,'T08110179',0),(37,3,'T09120016',1),(38,1,'T09120025',0),(38,2,'T13121215',0),(38,3,'T09120019',1);
/*!40000 ALTER TABLE `teacher_class` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-29 21:02:57
