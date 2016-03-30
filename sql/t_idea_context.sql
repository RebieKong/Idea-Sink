/*
Navicat MySQL Data Transfer

Source Server         : 120.25.2.220
Source Server Version : 50537
Source Host           : 120.25.2.220:3306
Source Database       : idea

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2015-12-18 16:06:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_idea_context
-- ----------------------------
DROP TABLE IF EXISTS `t_idea_context`;
CREATE TABLE `t_idea_context` (
  `idea_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(16) DEFAULT '' COMMENT '昵称',
  `title` varchar(64) DEFAULT NULL COMMENT '标题',
  `text` varchar(255) DEFAULT NULL COMMENT '描述',
  `tag_list` varchar(128) DEFAULT '' COMMENT 'TAG列表（逗号分隔）',
  `add_time` timestamp NULL DEFAULT NULL,
  `get_times` int(11) DEFAULT '0' COMMENT '顶数',
  `drop_times` int(11) DEFAULT '0' COMMENT '踩数',
  `show_times` int(11) DEFAULT '0',
  `pop` tinyint(4) DEFAULT '0' COMMENT '离开sink进入孵化pool（即不显示）',
  PRIMARY KEY (`idea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
