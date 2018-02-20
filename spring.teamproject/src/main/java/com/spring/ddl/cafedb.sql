-- --------------------------------------------------------
-- 호스트:                          192.168.0.254
-- 서버 버전:                        5.7.21-0ubuntu0.16.04.1 - (Ubuntu)
-- 서버 OS:                        Linux
-- HeidiSQL 버전:                  9.4.0.5174
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- cafedb 데이터베이스 구조 내보내기
DROP DATABASE IF EXISTS `cafedb`;
CREATE DATABASE IF NOT EXISTS `cafedb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `cafedb`;

-- 테이블 cafedb.tb_cafe_cafeinfo 구조 내보내기
DROP TABLE IF EXISTS `tb_cafe_cafeinfo`;
CREATE TABLE IF NOT EXISTS `tb_cafe_cafeinfo` (
  `cafeno` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(50) NOT NULL,
  `cafename` varchar(30) NOT NULL,
  `cafeaddr` varchar(30) NOT NULL,
  `cafephone` varchar(30) DEFAULT NULL,
  `avg_grade` double DEFAULT '0',
  `review_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `cafebigtype` varchar(50) NOT NULL DEFAULT '카페',
  `cafesmalltype` varchar(50) NOT NULL DEFAULT '프렌차이즈',
  `businessnum` varchar(50) DEFAULT '사업자번호 없음',
  `deliveryloc` varchar(50) DEFAULT '배달 불가',
  `opentime` varchar(50) DEFAULT '12시~24시',
  PRIMARY KEY (`cafeno`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 cafedb.tb_cafe_like 구조 내보내기
DROP TABLE IF EXISTS `tb_cafe_like`;
CREATE TABLE IF NOT EXISTS `tb_cafe_like` (
  `cafeno` int(11) NOT NULL,
  `userno` int(11) NOT NULL,
  KEY `FK_tb_cafe_like_tb_cafe_cafeinfo` (`cafeno`),
  KEY `FK_tb_cafe_like_tb_cafe_user` (`userno`),
  CONSTRAINT `FK_tb_cafe_like_tb_cafe_cafeinfo` FOREIGN KEY (`cafeno`) REFERENCES `tb_cafe_cafeinfo` (`cafeno`),
  CONSTRAINT `FK_tb_cafe_like_tb_cafe_user` FOREIGN KEY (`userno`) REFERENCES `tb_cafe_user` (`userno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 cafedb.tb_cafe_menu 구조 내보내기
DROP TABLE IF EXISTS `tb_cafe_menu`;
CREATE TABLE IF NOT EXISTS `tb_cafe_menu` (
  `brand` varchar(50) DEFAULT NULL,
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menucd` varchar(50) DEFAULT NULL,
  `menu_name` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 cafedb.tb_cafe_review 구조 내보내기
DROP TABLE IF EXISTS `tb_cafe_review`;
CREATE TABLE IF NOT EXISTS `tb_cafe_review` (
  `commentno` int(11) NOT NULL AUTO_INCREMENT,
  `usernickname` varchar(30) NOT NULL,
  `cafeno` int(11) DEFAULT NULL,
  `content` varchar(9999) NOT NULL,
  `grade` double NOT NULL,
  `regdate` datetime DEFAULT NULL,
  `updateDate` datetime DEFAULT NULL,
  PRIMARY KEY (`commentno`),
  KEY `cafereview_cafeinfo_fk` (`cafeno`),
  CONSTRAINT `cafereview_cafeinfo_fk` FOREIGN KEY (`cafeno`) REFERENCES `tb_cafe_cafeinfo` (`cafeno`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 cafedb.tb_cafe_user 구조 내보내기
DROP TABLE IF EXISTS `tb_cafe_user`;
CREATE TABLE IF NOT EXISTS `tb_cafe_user` (
  `userno` int(11) NOT NULL AUTO_INCREMENT,
  `userlevel` int(11) DEFAULT '1',
  `email` varchar(40) NOT NULL,
  `passwd` varchar(30) NOT NULL,
  `userphone` varchar(30) NOT NULL,
  `useraddr` varchar(100) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `emailselect` int(11) DEFAULT '0',
  `usernickname` varchar(30) NOT NULL,
  PRIMARY KEY (`userno`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 cafedb.tb_upload_file 구조 내보내기
DROP TABLE IF EXISTS `tb_upload_file`;
CREATE TABLE IF NOT EXISTS `tb_upload_file` (
  `uploadFileNo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fileName` varchar(50) NOT NULL,
  `fileSize` int(10) unsigned NOT NULL,
  `contentType` varchar(30) NOT NULL,
  PRIMARY KEY (`uploadFileNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 cafedb.tb_upload_image 구조 내보내기
DROP TABLE IF EXISTS `tb_upload_image`;
CREATE TABLE IF NOT EXISTS `tb_upload_image` (
  `uploadImageNo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fileName` varchar(50) NOT NULL,
  `fileSize` int(10) unsigned NOT NULL,
  `contentType` varchar(30) NOT NULL,
  `imageBytes` longblob,
  `imageBase64` longtext,
  PRIMARY KEY (`uploadImageNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 프로시저 cafedb.update_loop 구조 내보내기
DROP PROCEDURE IF EXISTS `update_loop`;
DELIMITER //
CREATE DEFINER=`cafe1`@`%` PROCEDURE `update_loop`()
begin
select @minno := (select min(cafeno) from tb_cafe_cafeinfo);select @maxno := (select max(cafeno) from tb_cafe_cafeinfo);while (@minno <= @maxno) DO
	UPDATE tb_cafe_cafeinfo 
   SET like_count = (select count(cafeno) from tb_cafe_like where cafeno = @minno)
       ,review_count= (select count(cafeno) from tb_cafe_review where cafeno = @minno)
       ,avg_grade = (select avg(grade) from tb_cafe_review where cafeno = @minno)
		WHERE cafeno = @minno ;set @minno = @minno+1 ;end while;end//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
