/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50559
Source Host           : localhost:3306
Source Database       : umanager

Target Server Type    : MYSQL
Target Server Version : 50559
File Encoding         : 65001

Date: 2018-10-21 01:28:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'ja', '123', '江苏');
INSERT INTO `user` VALUES ('2', 'BL', '123', '新加坡');


DROP TABLE IF EXISTS `songList`;
create table songList(
  songListId varchar (255) not null,
  songListPic varchar(255) not null,
  songListCount int(11),
  songListPlayCount int(11),
  songListDescription varchar(255),
  primary key(songListId)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `song`;
create table song(
  id varchar (255) not null,
  name varchar(255),
  singer varchar(255),
  pic varchar(255),
  lrc varchar(255),
  url varchar(255),
  time int(11),
  primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `songListInSong`;
create table songListInSong(
  id int(11) not null,
  songListId int(11),
  songId int(11)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tag`;
create table tag(
  id int(11) not null,
  name varchar(255),
  type int(11),
  category int(11),
  hot int(2)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tagInSongList`;
create table tagInSongList(
  id int(11) not null,
  songListId varchar(255),
  tagId int(11),
  primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

create table user_song_sheet(
  id int(11) not null,
  userId varchar(255),
  songSheetIds varchar(255),
  songId varchar(255)
  primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

create table user_profile(
  id int(11) not null,
  userId varchar(255),
  tagBody text,
  songId varchar(255)
  primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



