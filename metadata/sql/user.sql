/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : owntest

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2020-12-03 10:42:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `sex` varchar(64) DEFAULT NULL,
  `age` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8;
