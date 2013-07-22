/*
MySQL Data Transfer
Source Host: localhost
Source Database: quanzi
Target Host: localhost
Target Database: quanzi
Date: 2012/6/3 22:21:26
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for ciecledetailimg
-- ----------------------------
CREATE TABLE `ciecledetailimg` (
  `cdid` varchar(100) NOT NULL DEFAULT '',
  `circleDetailId` varchar(100) DEFAULT NULL,
  `bigImg` varchar(100) DEFAULT NULL,
  `middleImg` varchar(100) DEFAULT NULL,
  `smallImg` varchar(100) DEFAULT NULL,
  `uploadTime` datetime DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cdid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for circlecommentinfo
-- ----------------------------
CREATE TABLE `circlecommentinfo` (
  `ccid` varchar(100) NOT NULL DEFAULT '',
  `circleDetailId` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `commentinfo` varchar(2000) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ccid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for circledetailinfo
-- ----------------------------
CREATE TABLE `circledetailinfo` (
  `circleDetailId` varchar(100) NOT NULL DEFAULT '',
  `circleId` varchar(100) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `circleContent` varchar(2000) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `updateDateTime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `ctag` varchar(100) DEFAULT NULL,
  `comCount` int(11) DEFAULT NULL,
  `visitCount` int(11) DEFAULT NULL,
  `isTop` int(11) DEFAULT NULL,
  `display` int(11) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`circleDetailId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for circleinfo
-- ----------------------------
CREATE TABLE `circleinfo` (
  `circleId` varchar(100) NOT NULL DEFAULT '',
  `circleName` varchar(64) DEFAULT NULL,
  `createDateTime` datetime DEFAULT NULL,
  `updateDateTime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `summary` varchar(2000) DEFAULT NULL,
  `circleTag` varchar(100) DEFAULT NULL,
  `circleBigImg` varchar(100) DEFAULT NULL,
  `circleMiddleImg` varchar(100) DEFAULT NULL,
  `circleSmallImg` varchar(100) DEFAULT NULL,
  `joinCount` int(11) DEFAULT NULL,
  `comCount` int(11) DEFAULT NULL,
  `visitCount` int(11) DEFAULT NULL,
  `csort` int(11) DEFAULT NULL,
  `isTop` int(11) DEFAULT NULL,
  `display` int(11) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`circleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collect
-- ----------------------------
CREATE TABLE `collect` (
  `cid` varchar(100) NOT NULL DEFAULT '',
  `circleId` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `collectDateTime` datetime DEFAULT NULL,
  `ctype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
CREATE TABLE `favorite` (
  `fid` varchar(100) NOT NULL DEFAULT '',
  `circleId` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `fDateTime` datetime DEFAULT NULL,
  `ftype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tagtopic
-- ----------------------------
CREATE TABLE `tagtopic` (
  `ttid` varchar(100) NOT NULL DEFAULT '',
  `tagName` varchar(100) DEFAULT NULL,
  `tDateTime` datetime DEFAULT NULL,
  `tagtype` int(11) DEFAULT NULL,
  `tsort` int(11) DEFAULT NULL,
  `display` int(11) DEFAULT NULL,
  PRIMARY KEY (`ttid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `nick` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `registerTime` varchar(19) DEFAULT NULL,
  `registerIP` varchar(20) DEFAULT NULL,
  `loginTime` varchar(19) DEFAULT NULL,
  `loginIP` varchar(20) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `what` varchar(60) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `townID` int(11) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `deliveryTypeID` int(11) DEFAULT NULL,
  `deliveryTimeID` int(11) DEFAULT NULL,
  `payTypeID` int(11) DEFAULT NULL,
  `userMoney` float DEFAULT NULL,
  `buyScore` int(11) DEFAULT NULL,
  `activationCode` varchar(32) DEFAULT NULL,
  `activationDate` varchar(10) DEFAULT NULL,
  `firstName` varchar(20) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `hobby` varchar(50) DEFAULT NULL,
  `declaration` varchar(500) DEFAULT NULL,
  `birthday` varchar(10) DEFAULT NULL,
  `degreeID` int(11) DEFAULT NULL,
  `incomeTypeID` int(11) DEFAULT NULL,
  `joinStatus` int(11) DEFAULT NULL,
  `onlineFlag` char(11) DEFAULT NULL,
  `topicNumber` int(11) DEFAULT NULL,
  `voteNumber` int(11) DEFAULT NULL,
  `fansNumber` int(11) DEFAULT NULL,
  `followNumber` int(11) DEFAULT NULL,
  `userLevel` int(11) DEFAULT NULL,
  `membersFlag` char(11) DEFAULT NULL,
  `activationCodeJoinCommunity` varchar(32) DEFAULT NULL,
  `website` varchar(200) DEFAULT NULL,
  `food` varchar(200) DEFAULT NULL,
  `books` varchar(200) DEFAULT NULL,
  `plot` varchar(200) DEFAULT NULL,
  `programs` varchar(200) DEFAULT NULL,
  `entertainment` varchar(200) DEFAULT NULL,
  `tourist` varchar(200) DEFAULT NULL,
  `digital` varchar(200) DEFAULT NULL,
  `sports` varchar(200) DEFAULT NULL,
  `newspapers` varchar(200) DEFAULT NULL,
  `bgID` varchar(20) DEFAULT NULL,
  `communityTownID` int(11) DEFAULT NULL,
  `personality` varchar(200) DEFAULT NULL,
  `figure` varchar(200) DEFAULT NULL,
  `dressStyle` varchar(200) DEFAULT NULL,
  `interests` varchar(200) DEFAULT NULL,
  `travelDestination` varchar(200) DEFAULT NULL,
  `loveToReadMagazinesAndBooks` varchar(200) DEFAULT NULL,
  `oftenGoToWebSite` varchar(200) DEFAULT NULL,
  `likeStar` varchar(200) DEFAULT NULL,
  `goodAtSports` varchar(200) DEFAULT NULL,
  `likeMovie` varchar(200) DEFAULT NULL,
  `likeFood` varchar(200) DEFAULT NULL,
  `likeMakingFriends` varchar(200) DEFAULT NULL,
  `lastActionTime` varchar(19) DEFAULT NULL,
  `otherAccount` varchar(100) DEFAULT NULL,
  `otherAccountFlag` char(11) DEFAULT NULL,
  `otherAccounTypeID` int(11) DEFAULT NULL,
  `otherAccountUserImage` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_circle_rls
-- ----------------------------
CREATE TABLE `user_circle_rls` (
  `ucid` varchar(100) NOT NULL DEFAULT '',
  `circleId` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `uc_role` int(11) DEFAULT NULL,
  `joinDateTime` datetime DEFAULT NULL,
  `jtype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ucid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `ciecledetailimg` VALUES ('1', '1', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('11', '2', 'images/img_02.jpg', 'images/img_02.jpg', 'images/img_02.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('12', '2', 'images/img_03.jpg', 'images/img_03.jpg', 'images/img_03.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('13', '3', 'images/img_04.jpg', 'images/img_04.jpg', 'images/img_04.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('14', '4', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('15', '4', 'images/img_02.jpg', 'images/img_02.jpg', 'images/img_02.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('16', '5', 'images/img_03.jpg', 'images/img_03.jpg', 'images/img_03.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('17', '6', 'images/img_04.jpg', 'images/img_04.jpg', 'images/img_04.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('18', '7', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('19', '8', 'images/img_02.jpg', 'images/img_02.jpg', 'images/img_02.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('2', '8', 'images/img_03.jpg', 'images/img_03.jpg', 'images/img_03.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('3', '8', 'images/img_04.jpg', 'images/img_04.jpg', 'images/img_04.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('4', '9', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('5', '10', 'images/img_02.jpg', 'images/img_02.jpg', 'images/img_02.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('6', '11', 'images/img_03.jpg', 'images/img_03.jpg', 'images/img_03.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('7', '12', 'images/img_04.jpg', 'images/img_04.jpg', 'images/img_04.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('8', '13', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('9', '14', 'images/img_02.jpg', 'images/img_02.jpg', 'images/img_02.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `circlecommentinfo` VALUES ('1', '1', '1', '2012-06-02 21:03:07', '第1条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('10', '1', '1', '2012-06-03 01:24:40', '第10条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('11', '1', '1', '2012-06-03 01:24:43', '第11条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('12', '1', '1', '2012-06-03 01:24:46', '第12条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('13', '1', '1', '2012-06-03 01:24:49', '第13条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('14', '1', '1', '2012-06-03 01:24:51', '第14条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('15', '1', '1', '2012-06-03 01:24:54', '第15条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('16', '1', '1', '2012-06-03 01:24:57', '第16条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('17', '1', '1', '2012-06-03 01:26:07', '第17条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('2', '1', '1', '2012-06-02 21:03:25', '第2条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('3', '1', '1', '2012-06-02 21:03:40', '第3条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('4', '1', '1', '2012-06-02 21:03:42', '第4条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('5', '1', '1', '2012-06-02 21:03:46', '第5条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('6', '1', '1', '2012-06-03 01:24:28', '第6条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('7', '1', '1', '2012-06-03 01:24:30', '第7条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('8', '1', '1', '2012-06-03 01:24:33', '第8条回复信息', null, null);
INSERT INTO `circlecommentinfo` VALUES ('9', '1', '1', '2012-06-03 01:24:37', '第9条回复信息', null, null);
INSERT INTO `circledetailinfo` VALUES ('1', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('10', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('11', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('12', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('13', '2', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('14', '2', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('15', '2', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('16', '2', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('17', '2', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('18', '3', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('19', '3', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('2', '4', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('20', '4', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('21', '4', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('22', '4', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('23', '4', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('24', '5', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('25', '5', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('26', '6', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('27', '6', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('28', '7', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('29', '8', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('3', '9', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('30', '9', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('31', '9', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('32', '9', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('33', '10', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('34', '10', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('35', '11', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('4', '11', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('5', '12', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('6', '13', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('7', '13', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('8', '14', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('9', '14', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('1', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('10', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('11', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('12', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('2', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('3', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('4', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('5', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('6', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('7', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('8', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `circleinfo` VALUES ('9', '第一个', '2012-05-28 21:31:45', '2012-05-28 21:31:48', '1', '第一个测试的圈子信息', '第一，圈子', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '3', '3', '3', null, null, '1', null, null, null, null);
INSERT INTO `tagtopic` VALUES ('1', '全部', '2012-05-31 22:49:58', '1', '1', '1');
INSERT INTO `tagtopic` VALUES ('10', '美女', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('11', '图片', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('12', '汽车', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('13', '房子', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('14', '美景', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('15', '山河', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('16', '城市', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('17', '家电', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('18', '兵器', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('19', '短片', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('2', '家具', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('20', '最热话题', '2012-06-01 22:46:55', '2', '1', '1');
INSERT INTO `tagtopic` VALUES ('21', '最新话题', '2012-06-01 22:46:58', '2', '2', '1');
INSERT INTO `tagtopic` VALUES ('22', '推荐活体', '2012-06-01 22:47:01', '2', '2', '1');
INSERT INTO `tagtopic` VALUES ('3', '旅游', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('4', '网站设计制作', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('5', '美食', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('6', '创意', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('7', '摄影', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('8', '音乐/电影/图书', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('9', '艺术', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `user` VALUES ('1', 'maliwei@163.com', 'xiaomage', '123456', '马立伟', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'http://qzapp.qlogo.cn/qzapp/100255656/A5EC4D1A73BA784A5C6F7C0F7ADF9FDB/30');
INSERT INTO `user_circle_rls` VALUES ('95ad50ed-5fe3-4879-8ea0-574398ccc64f', '11', '1', '1', '2012-06-03 19:41:37', '1');
