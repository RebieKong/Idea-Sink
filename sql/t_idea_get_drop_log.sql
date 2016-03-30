/*
Navicat MySQL Data Transfer

Source Server         : 120.25.2.220
Source Server Version : 50537
Source Host           : 120.25.2.220:3306
Source Database       : idea

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2015-12-18 16:06:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_idea_get_drop_log
-- ----------------------------
DROP TABLE IF EXISTS `t_idea_get_drop_log`;
CREATE TABLE `t_idea_get_drop_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `idea_id` int(11) DEFAULT NULL COMMENT '操作的idea_id',
  `get_drop` tinyint(4) DEFAULT NULL COMMENT 'get 1 drop 0',
  `ip` varchar(16) DEFAULT NULL COMMENT 'ip地址',
  `add_time` timestamp NULL DEFAULT NULL COMMENT 'log时间',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
