CREATE DATABASE  IF NOT EXISTS `main` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `main`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: main
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `blog_list`
--

DROP TABLE IF EXISTS `blog_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_list` (
  `BLOG_UUID` varchar(50) NOT NULL,
  `BLOG_ID` int NOT NULL COMMENT 'blogId',
  `BLOG_NM` varchar(30) DEFAULT NULL COMMENT 'title',
  `BLOG_TITLE` varchar(50) DEFAULT NULL,
  `BLOG_URL` varchar(200) DEFAULT NULL COMMENT 'url',
  `SCND_URL` varchar(200) DEFAULT NULL COMMENT 'secondary_url',
  `NICK_NM` varchar(30) DEFAULT NULL,
  `DESCP` varchar(500) DEFAULT NULL COMMENT '''description''',
  `DEFAULT_YN` char(1) DEFAULT 'N',
  `ROLE_NM` varchar(10) DEFAULT NULL,
  `BLOG_ICON_URL` varchar(200) DEFAULT NULL,
  `FAVICON_URL` varchar(200) DEFAULT NULL COMMENT 'FAVICON_URL',
  `PROF_THMNL_IMG_URL` varchar(200) DEFAULT NULL COMMENT '''profile_thumbnail_image_url''\n',
  `PROF_IMG_URL` varchar(200) DEFAULT NULL COMMENT '''profileImageUrl''\n',
  `STAT_POST` int DEFAULT '0',
  `STAT_COMNT` int DEFAULT '0' COMMENT 'stat_comment',
  `STAT_TRKBK` int DEFAULT '0' COMMENT '\\nstat_trackback',
  `STAT_GSTBOOK` int DEFAULT '0' COMMENT 'stat_guestbook',
  `STAT_INVTN` int DEFAULT '0' COMMENT '\\nstat_invitation',
  PRIMARY KEY (`BLOG_UUID`),
  UNIQUE KEY `blog_id_UNIQUE` (`BLOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_list`
--

LOCK TABLES `blog_list` WRITE;
/*!40000 ALTER TABLE `blog_list` DISABLE KEYS */;
INSERT INTO `blog_list` VALUES ('52e806b4-e78f-4856-b2a1-a5a6945ce54e',4109303,'hellorecord98','처음 하는 기록장','https://hellorecord98.tistory.com','','yesliz98','','Y','소유자','https://t1.daumcdn.net/tistory_admin/assets/blog/20221108140629/blogs/410/4109303/index.gif?_version_=20221108140629','https://t1.daumcdn.net/tistory_admin/assets/blog/20221108140629/blogs/410/4109303/index.ico?_version_=20221108140629','','',64,5,0,0,NULL);
/*!40000 ALTER TABLE `blog_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_list`
--

DROP TABLE IF EXISTS `category_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category_list` (
  `CTGY_UUID` varchar(50) NOT NULL COMMENT 'CATEGORY_UUID',
  `CTGY_ID` int NOT NULL COMMENT '\\nid',
  `BLOG_UUID` varchar(50) NOT NULL,
  `CTGY_NM` varchar(100) DEFAULT NULL COMMENT 'ctgy_name',
  `UP_CTGY_ID` int DEFAULT NULL COMMENT 'parent',
  `CTGY_PATH` varchar(300) DEFAULT NULL COMMENT 'label',
  `POST_CNT` int DEFAULT '0' COMMENT 'entries',
  PRIMARY KEY (`CTGY_UUID`),
  UNIQUE KEY `CTGY_ID_UNIQUE` (`CTGY_ID`),
  KEY `blog` (`BLOG_UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_list`
--

LOCK TABLES `category_list` WRITE;
/*!40000 ALTER TABLE `category_list` DISABLE KEYS */;
INSERT INTO `category_list` VALUES ('0b77eea3-d534-4201-b2e6-eef2605ce8a2',889011,'582876aa-7338-439d-8fb3-0c2dff914f23','Kotlin',889010,'독학/Kotlin',15),('2337586d-bfeb-43a6-be9f-e360fcb43480',963672,'582876aa-7338-439d-8fb3-0c2dff914f23','잡담',NULL,'잡담',9),('349190c5-25f8-454f-89c8-c13717772c84',889010,'582876aa-7338-439d-8fb3-0c2dff914f23','독학',NULL,'독학',32),('37646cf6-09ad-4d5f-9e19-5bd51ed8dad2',1003531,'582876aa-7338-439d-8fb3-0c2dff914f23','게임',1003530,'기타/게임',2),('379f8a2f-da54-4e75-8f38-4e1e5b515c8e',889214,'582876aa-7338-439d-8fb3-0c2dff914f23','안드로이드',889010,'독학/안드로이드',1),('3e128819-b792-4033-8ef6-d7a1c1b5e6de',889009,'582876aa-7338-439d-8fb3-0c2dff914f23','구글링 기록',NULL,'구글링 기록',2),('604e8ec0-6bf4-4169-bed5-6190a329533f',889858,'582876aa-7338-439d-8fb3-0c2dff914f23','필기',NULL,'필기',3),('89caef17-ea5a-4615-869c-1537f6f4cb96',1003530,'582876aa-7338-439d-8fb3-0c2dff914f23','기타',NULL,'기타',3),('de7df6cc-125b-476f-990d-30440e9d22a1',908637,'582876aa-7338-439d-8fb3-0c2dff914f23','기타',889010,'독학/기타',3),('dedcce10-43fd-4985-8263-02a0df754120',963670,'582876aa-7338-439d-8fb3-0c2dff914f23','작심삼일',NULL,'작심삼일',7),('edb859eb-2648-480c-bd3b-de087d1e6a1e',1040113,'582876aa-7338-439d-8fb3-0c2dff914f23','forge modding',889010,'독학/forge modding',13);
/*!40000 ALTER TABLE `category_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_list`
--

DROP TABLE IF EXISTS `post_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post_list` (
  `POST_UUID` varchar(50) NOT NULL,
  `POST_ID` int NOT NULL COMMENT 'id',
  `BLOG_UUID` varchar(50) NOT NULL COMMENT 'blog_uuid',
  `CTGY_UUID` varchar(50) NOT NULL,
  `POST_NM` varchar(200) DEFAULT NULL COMMENT 'title',
  `POST_URL` varchar(200) DEFAULT NULL COMMENT 'post_url',
  `PUBLIC_CD` int DEFAULT '0' COMMENT 'visibility(0: 비공개, 15: 보호, 20: 발행)',
  `POST_DT` varchar(20) DEFAULT NULL COMMENT 'date',
  `STAT_COMNT` int DEFAULT '0' COMMENT 'comment',
  `STAT_TRKBK` int DEFAULT '0' COMMENT 'trackbbacks',
  PRIMARY KEY (`POST_UUID`),
  UNIQUE KEY `POST_ID_UNIQUE` (`POST_ID`),
  KEY `blog` (`BLOG_UUID`,`CTGY_UUID`) /*!80000 INVISIBLE */
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_list`
--

LOCK TABLES `post_list` WRITE;
/*!40000 ALTER TABLE `post_list` DISABLE KEYS */;
INSERT INTO `post_list` VALUES ('038a124b-f9bc-462b-b04e-1323a880ce92',47,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','네이버 웨이 사이드바 html 포맷 만들고싶','https://hellorecord98.tistory.com/47',20,'2022-09-30 10:44:21',0,0),('05e31902-2204-408b-8561-74443ee9869b',24,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 모드 문서 번역 6일차','https://hellorecord98.tistory.com/24',20,'2022-09-01 15:42:14',0,0),('0687a066-087b-46e3-aa9a-1563e05a6458',7,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','379f8a2f-da54-4e75-8f38-4e1e5b515c8e','스밍리스트 어플 제작기','https://hellorecord98.tistory.com/7',20,'2020-08-23 17:51:28',0,0),('089336df-5bf0-405f-b331-9e39a2075ce8',53,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 4. Operators - 연산자','https://hellorecord98.tistory.com/53',20,'2022-09-30 17:23:40',0,0),('0b3a5c27-cc56-4afb-9eff-657462b2cd4f',44,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','티스토리 에디터 적응 안된다','https://hellorecord98.tistory.com/44',20,'2022-09-30 09:46:03',0,0),('0ddf427b-714e-4907-9b1f-4d84ba63ee88',66,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','89caef17-ea5a-4615-869c-1537f6f4cb96','221027','https://hellorecord98.tistory.com/66',20,'2022-10-27 10:25:47',0,0),('0f08b566-bd5b-4936-93f8-46b5a0e0876e',15,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','정보처리기사','https://hellorecord98.tistory.com/15',20,'2021-07-20 21:49:56',0,0),('141d226b-a54d-459f-b275-f65a361ab319',64,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','221024','https://hellorecord98.tistory.com/64',20,'2022-10-24 16:15:30',0,0),('159a23b4-1f7a-4eee-9706-2521718f0b42',58,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 9. if Statement - if문','https://hellorecord98.tistory.com/58',20,'2022-10-11 11:13:34',0,0),('174ea238-e225-46f0-9928-ae48bd9750dd',71,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','3e128819-b792-4033-8ef6-d7a1c1b5e6de','spring boot/gradle/mybatis/mysql/log4jdbc 연동(DTO/DAO 없이 연결)','https://hellorecord98.tistory.com/71',20,'2022-11-09 17:07:04',0,0),('1815f755-f5e4-47ce-834d-40d7e9acf9d9',23,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 모드 문서 번역 5일차','https://hellorecord98.tistory.com/23',20,'2022-08-04 17:57:56',0,0),('1a0365f0-2d35-47a3-bed3-43ac4663ccc0',6,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] null 자료형','https://hellorecord98.tistory.com/6',20,'2020-08-23 14:38:37',0,0),('2172268b-1407-4905-b263-13faa6db4caa',20,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 모드 문서 한글 번역 2일차','https://hellorecord98.tistory.com/20',20,'2022-07-27 17:29:10',0,0),('2a3bcb4f-6ac9-463f-941f-c27fab30db7e',12,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','de7df6cc-125b-476f-990d-30440e9d22a1','VBA 엑셀 빈칸 뚫기 문제 만드는 파일 코드1','https://hellorecord98.tistory.com/12',20,'2021-01-20 00:11:07',1,0),('2b19e534-47be-4c44-a1af-01d900367839',59,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 10. Conditional Expressions - 조건문','https://hellorecord98.tistory.com/59',20,'2022-10-11 14:52:55',0,0),('2d0cdf40-bfc6-46bb-bb98-aad9f7cb26bc',29,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','rtd slug 함수 찾아 삼만리는 7일차','https://hellorecord98.tistory.com/29',20,'2022-09-24 22:06:34',0,0),('30e7a422-2163-4d61-af0f-264a40627bde',31,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','3e128819-b792-4033-8ef6-d7a1c1b5e6de','이클립스에서 깃허브에 새 레파지토리 생성하기','https://hellorecord98.tistory.com/31',20,'2022-09-26 17:59:40',0,0),('34c6270a-510c-4cb9-88ee-1b22fc63ebe2',33,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','rtd slug custom 1일차','https://hellorecord98.tistory.com/33',20,'2022-09-28 09:13:26',0,0),('3769fcff-721d-4447-a5af-67f26ea51e42',51,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 2. Data Types - 자료형','https://hellorecord98.tistory.com/51',20,'2022-09-30 14:30:15',1,0),('49908a7d-e6df-4df0-876e-9dd0ae9009c1',26,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 문서 번역 7일차','https://hellorecord98.tistory.com/26',20,'2022-09-21 17:40:30',1,0),('4da65257-f5fa-4e9d-a08e-8f787d2aaacf',5,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 자료형과 변수','https://hellorecord98.tistory.com/5',20,'2020-08-23 13:50:15',0,0),('4f566465-f79b-47da-a55a-ab394294f4ea',43,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 1. Introduction','https://hellorecord98.tistory.com/43',20,'2022-09-29 17:59:09',0,0),('537db627-0495-4eef-91ce-cdf3af73148e',4,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] String에 변수 삽입','https://hellorecord98.tistory.com/4',20,'2020-08-22 21:10:56',0,0),('586a7fc9-7cdc-4467-9c5f-85686a7ab6de',65,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','221025','https://hellorecord98.tistory.com/65',20,'2022-10-25 09:00:47',0,0),('5a4dd6cc-144f-4022-9321-1ffd3d46d762',1,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] Hello, world','https://hellorecord98.tistory.com/1',20,'2020-08-22 20:15:59',0,0),('5b7c03fe-2c0b-42d9-8dd2-6aeef1fb4506',28,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','rtd slug 함수 찾아 삼만리는 6일차','https://hellorecord98.tistory.com/28',20,'2022-09-23 17:51:38',1,0),('5c8b91cb-06b5-4dc6-abfa-85324bba3571',56,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 6. Input - 입력','https://hellorecord98.tistory.com/56',20,'2022-10-07 11:23:45',1,0),('5f84718b-4f49-4afb-a0a9-a3e69823aeb3',40,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','하나 시작하다가 딴길로 새기','https://hellorecord98.tistory.com/40',20,'2022-09-29 17:29:40',0,0),('60af7f26-1590-4177-b3c8-4e610c2b6ffb',72,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','221109','https://hellorecord98.tistory.com/72',20,'2022-11-09 17:12:43',0,0),('63c9f886-65ac-4bf6-a758-77a0c9568d3c',32,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','rtd slug 함수 찾았음','https://hellorecord98.tistory.com/32',20,'2022-09-26 21:18:14',0,0),('717d020b-5157-4d1e-befa-42e0215e2437',9,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','604e8ec0-6bf4-4169-bed5-6190a329533f','HTML','https://hellorecord98.tistory.com/9',20,'2020-08-25 10:43:19',0,0),('766d8333-3127-4df8-9c97-d5f3a3e19932',18,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','37646cf6-09ad-4d5f-9e19-5bd51ed8dad2','[마인크래프트]철골램 공장 기준 1.18','https://hellorecord98.tistory.com/18',20,'2022-01-30 19:35:00',0,0),('7857b0df-2a04-4197-b6a0-490f75837f92',60,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 11.while  - 반복문','https://hellorecord98.tistory.com/60',20,'2022-10-11 15:33:46',0,0),('7946a5d4-a40d-49f2-a4a9-e0e37fa37ce2',57,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 7,8. Basic Concepts Quiz','https://hellorecord98.tistory.com/57',20,'2022-10-07 13:18:20',0,0),('8e79b39f-5488-41a8-a37c-2082aa758c8c',62,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','221020','https://hellorecord98.tistory.com/62',20,'2022-10-20 10:41:52',0,0),('8ef64dea-bfbe-47bd-8fe3-6108d633e818',17,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','회사에서 인터넷 될 줄 알았는데','https://hellorecord98.tistory.com/17',20,'2021-07-20 21:55:23',0,0),('92387034-a814-4d0b-a40d-a22e4da4cbc9',10,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','604e8ec0-6bf4-4169-bed5-6190a329533f','Javascript','https://hellorecord98.tistory.com/10',20,'2020-08-25 10:44:12',0,0),('9607509f-dc57-4851-ab45-149ab35d281a',30,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','rtd slug 함수 찾아 삼만리는 9일차','https://hellorecord98.tistory.com/30',20,'2022-09-26 14:58:31',0,0),('a31b74ae-e74a-4920-b57c-b48d84609ad7',63,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','221021','https://hellorecord98.tistory.com/63',20,'2022-10-21 16:58:46',0,0),('a326fa54-4b17-4bdc-8f71-5f047784d252',27,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','rtd slug 함수 찾아 삼만리는 5일차','https://hellorecord98.tistory.com/27',20,'2022-09-23 09:20:17',0,0),('a5a2cbe0-13ad-4f2e-ada7-c2d1313b04c0',61,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 12.break&continue','https://hellorecord98.tistory.com/61',20,'2022-10-11 15:58:59',0,0),('a5a9bf01-37e5-4e52-aa1a-7e7cd54df5f5',8,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','604e8ec0-6bf4-4169-bed5-6190a329533f','딥러닝 정리','https://hellorecord98.tistory.com/8',20,'2020-08-25 10:23:52',0,0),('aaca5748-ca0d-4f93-8afd-3792ab534688',21,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 모드 문서 번역 및 모드 만들기 3일차','https://hellorecord98.tistory.com/21',20,'2022-07-28 10:25:19',0,0),('af356353-8c94-4aed-83cc-227944a281a8',68,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','221101','https://hellorecord98.tistory.com/68',20,'2022-11-01 08:52:11',0,0),('b0b70714-af27-47d2-ac72-f1abb84b648f',14,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','한국사능력검정시험','https://hellorecord98.tistory.com/14',20,'2021-07-20 21:47:42',0,0),('b0e0ef08-8129-4726-b0df-a657677a3192',13,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','마인크래프트','https://hellorecord98.tistory.com/13',20,'2021-07-20 21:44:46',0,0),('b688dc5a-ae1a-417d-9c10-ab481bf01387',25,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','dedcce10-43fd-4985-8263-02a0df754120','spring 공부 시작','https://hellorecord98.tistory.com/25',20,'2022-09-04 16:48:20',0,0),('c033987a-4a29-4b77-b1f1-d76e13212ced',69,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','221101','https://hellorecord98.tistory.com/69',20,'2022-11-01 13:45:10',0,0),('c91d4b81-234d-4eda-8ae3-fb35905599ff',52,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 3. Variables 변수','https://hellorecord98.tistory.com/52',20,'2022-09-30 16:39:47',0,0),('d60ccc8c-572f-4bcc-b24a-e969faa854d1',54,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','37646cf6-09ad-4d5f-9e19-5bd51ed8dad2','[래프트] crafting tree?','https://hellorecord98.tistory.com/54',20,'2022-10-05 19:44:36',0,0),('e097aaba-0295-4a8c-b595-0d03bd5b986f',55,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','0b77eea3-d534-4201-b2e6-eef2605ce8a2','[Kotlin] 5. Comments - 주석','https://hellorecord98.tistory.com/55',20,'2022-10-07 10:33:28',0,0),('e21c0abb-e3c0-4187-ab12-b0ee4de63e6e',22,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 모드 문서 번역 4일차','https://hellorecord98.tistory.com/22',20,'2022-08-04 10:22:33',0,0),('e7a29ae5-b2c9-483b-a977-6f82b4b8aac9',19,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','edb859eb-2648-480c-bd3b-de087d1e6a1e','포지 모드 문서 한글 번역 시작','https://hellorecord98.tistory.com/19',20,'2022-07-26 14:49:31',0,0),('e8c7bcb2-6314-40ea-bf11-2205e4b849e3',70,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','2337586d-bfeb-43a6-be9f-e360fcb43480','221107','https://hellorecord98.tistory.com/70',20,'2022-11-07 16:53:15',0,0),('f8f436f7-7093-42d1-a79c-731ce2150800',67,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','de7df6cc-125b-476f-990d-30440e9d22a1','티스토리 api 쓰는 웹서비스','https://hellorecord98.tistory.com/67',20,'2022-10-28 09:37:48',0,0),('fee1d4fc-3550-421b-a794-a47bd4d71a00',11,'52e806b4-e78f-4856-b2a1-a5a6945ce54e','de7df6cc-125b-476f-990d-30440e9d22a1','시간 계산기 웹사이트 틀','https://hellorecord98.tistory.com/11',20,'2020-11-12 22:26:10',0,0);
/*!40000 ALTER TABLE `post_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'main'
--
/*!50003 DROP FUNCTION IF EXISTS `fn_ctgy_list` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_ctgy_list`(param_ctgy_uuid varchar(50)) RETURNS varchar(120) CHARSET utf8mb3
BEGIN
	declare result varchar(120);
	declare next_uuid varchar(50);
	declare parent_id int;
    set result = concat(param_ctgy_uuid);
    
    select up_ctgy_id
    into parent_id
    from category_list
    where ctgy_uuid = param_ctgy_uuid;
    
    while parent_id is not null do
		select ctgy_uuid, up_ctgy_id
		into next_uuid, parent_id
		from category_list
		where ctgy_id = parent_id;
        
        set result = concat(result,",",next_uuid);
    end while;
    
RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_get_ctgy_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_ctgy_id`(
	ctgy_name varchar(100)
) RETURNS varchar(50) CHARSET utf8mb3
BEGIN
	declare result varchar(50);
    
	select ctgy_uuid
    into result
    from main.category_list
    where ctgy_name = ctgy_name;
RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `fn_get_ctgy_nm` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_ctgy_nm`(
	ctgy_uuid varchar(50)
) RETURNS varchar(100) CHARSET utf8mb3
BEGIN
	declare result varchar(100);
    
	select ctgy_name
    into result
    from main.category_list
    where ctgy_uuid = ctgy_uuid;
RETURN result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-15 10:08:37
