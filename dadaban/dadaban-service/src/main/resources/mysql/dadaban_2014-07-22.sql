# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: 127.0.0.1 (MySQL 5.6.17)
# Database: dadaban
# Generation Time: 2014-07-22 14:56:59 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table ddb_task
# ------------------------------------------------------------

CREATE DATABASE `dadaban` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS `ddb_task`;

CREATE TABLE `ddb_task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ddb_task` WRITE;
/*!40000 ALTER TABLE `ddb_task` DISABLE KEYS */;

INSERT INTO `ddb_task` (`id`, `title`, `description`, `user_id`)
VALUES
	(1,'Study PlayFramework 2.0','http://www.playframework.org/',2),
	(2,'Study Grails 2.0','http://www.grails.org/',2),
	(3,'Try SpringFuse','http://www.springfuse.com/',2),
	(4,'Try Spring Roo','http://www.springsource.org/spring-roo',2),
	(5,'Release SpringSide 4.0','As soon as posibble.',2),
	(6,'abc','ddd',1),
	(7,'sss','d',1),
	(8,'ccc','ddd',1),
	(9,'111','22',1),
	(10,'222','222',1);

/*!40000 ALTER TABLE `ddb_task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ddb_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ddb_user`;

CREATE TABLE `ddb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(64) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `ddb_user` WRITE;
/*!40000 ALTER TABLE `ddb_user` DISABLE KEYS */;

INSERT INTO `ddb_user` (`id`, `login_name`, `name`, `password`, `salt`, `roles`, `register_date`)
VALUES
	(1,'admin','Admin','e445cfaa67c87264625c1def8bcc50a4875b7423','f081d601145e7dc7','admin','2012-06-04 01:00:00'),
	(2,'user','Calvin','2488aa0c31c624687bd9928e0a5d29e7d1ed520b','6d65d24122c30500','user','2012-06-04 02:00:00');

/*!40000 ALTER TABLE `ddb_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
