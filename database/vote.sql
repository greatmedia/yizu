
CREATE TABLE `tfind_pwd_log` (
  `ID` varchar(70) DEFAULT NULL,
  `USER_ID` varchar(70) DEFAULT NULL,
  `CREATE_TIME` date DEFAULT NULL,
  `CODE` varchar(50) DEFAULT NULL,
  `IP` varchar(20) DEFAULT NULL,
  `USED_TIME` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `circlecommentinfo_agree` (
  `aId` varchar(255) NOT NULL DEFAULT '',
  `ccId` varchar(255) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  `agreeDate` datetime DEFAULT NULL,
  `notAgreeDate` datetime DEFAULT NULL,
  PRIMARY KEY (`aId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- vote
CREATE TABLE `myvoteinfo` (
  `vote_id` varchar(100) NOT NULL DEFAULT '',
  `price` double DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `vote_Tag` varchar(100) DEFAULT NULL,
  `summary` varchar(200) DEFAULT NULL,
  `visitCount` INT DEFAULT NULL,
  `joinCount` INT DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`vote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `myvoteinfo_image` (
  `image_id` varchar(100) NOT NULL DEFAULT '',
  `vote_id` varchar(100)  DEFAULT NULL,
  `img_address` varchar(100) DEFAULT NULL,
  `img_updatetime` datetime DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `votecommentinfo` (
  `comment_id` varchar(100) NOT NULL DEFAULT '',
  `vote_id` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `commentdate` datetime DEFAULT NULL,
  `commentinfo` varchar(100) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `joinvote` (
  `joinvote_id` varchar(100) NOT NULL DEFAULT '',
  `vote_id` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `partkeTime` datetime DEFAULT NULL,
  `resultType` int DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`joinvote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--评论
CREATE TABLE `comtocom` (
  `ctid` varchar(100) NOT NULL DEFAULT '',
  `ccid` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `touser` int(11) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ctid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


---test
INSERT INTO `user` VALUES ('1', 'maliwei@163.com', 'xiaomage', '123456', '马立伟', null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 'http://qzapp.qlogo.cn/qzapp/100255656/A5EC4D1A73BA784A5C6F7C0F7ADF9FDB/30');


INSERT INTO `tagtopic` VALUES ('1', '全部', '2012-05-31 22:49:58', '1', '1', '1');
INSERT INTO `tagtopic` VALUES ('10', '休闲时光', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('11', '运动', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('12', '职场', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('15', '旅游', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('5', '城市', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('14', '家是温暖的巢', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('8', '爱摄影', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('16', '一日不可无书', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('17', '时尚/潮流', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('18', '音乐/戏剧/电影', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('2', '文学艺术', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('4', '爱车人', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('6', '美食', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('9', '故乡', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('23', '云游海外', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('19', '短片', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('20', '最热话题', '2012-06-01 22:46:55', '2', '1', '1');
INSERT INTO `tagtopic` VALUES ('21', '最新话题', '2012-06-01 22:46:58', '2', '2', '1');
INSERT INTO `tagtopic` VALUES ('22', '推荐活体', '2012-06-01 22:47:01', '2', '2', '1');
INSERT INTO `tagtopic` VALUES ('3', '旅游', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('7', '生活百科', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('24', '童真/童趣', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('25', '校园逸事', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('27', '美女', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('28', '爱情', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('29', '星座秘语', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('30', '益智游戏', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('31', '宠物', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('32', '媒体', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('33', '幽默/笑话', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('34', '企业/企业家', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('38', '亲子教育', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('39', '未知世界', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('35', '国学', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('37', '凡人大事/名人小事', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('40', '网络时代', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('41', '创意天地', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('42', '爱心/公益', '2012-05-31 22:49:58', '1', '2', '1');
INSERT INTO `tagtopic` VALUES ('43', '其他', '2012-05-31 22:49:58', '1', '2', '1');


INSERT INTO `ciecledetailimg` VALUES ('1', '1', 'images/img_01.jpg', 'images/img_01.jpg', 'images/img_01.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('11', '2', 'images/img_02.jpg', 'images/img_02.jpg', 'images/img_02.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('12', '2', 'images/img_03.jpg', 'images/img_03.jpg', 'images/img_03.jpg', '2012-05-31 21:24:13', null, null);
INSERT INTO `ciecledetailimg` VALUES ('13', '3', 'images/img_04.jpg', 'images/img_04.jpg', 'images/img_04.jpg', '2012-05-31 21:24:13', null, null);


INSERT INTO `circledetailinfo` VALUES ('1', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('10', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('11', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);
INSERT INTO `circledetailinfo` VALUES ('12', '1', '圈子详细信息标题', '圈子详细内容', '2012-05-31 21:04:24', '2012-05-31 21:04:27', '1', '测试  测试  测试', '1', '1', '1', '1', null, null, null, null);


INSERT INTO `myvoteinfo` VALUES ('1', '100', SYSDATE(), '2012-10-10', 1,'美丽的女孩','女孩,美丽','美丽的女孩的简介',1,1,null,null,null,null);
INSERT INTO `myvoteinfo` VALUES ('2', '500', SYSDATE(), '2012-09-10', 1,'电饭煲','电饭煲,美丽','电饭煲的简介',1,1,null,null,null,null);
INSERT INTO `myvoteinfo` VALUES ('3', '700', SYSDATE(), '2012-08-10', 1,'汽车','汽车,美丽','汽车的简介',1,1,null,null,null,null);
INSERT INTO `myvoteinfo` VALUES ('4', '600', SYSDATE(), '2012-12-10', 1,'水杯','水杯,美丽','水杯的简介',1,1,null,null,null,null);

INSERT INTO `myvoteinfo_image` VALUES ('1', '1','images/img_08.jpg', SYSDATE(), null, null,null,null);
INSERT INTO `myvoteinfo_image` VALUES ('2', '2','images/img_07.jpg', SYSDATE(), null, null,null,null);
INSERT INTO `myvoteinfo_image` VALUES ('3', '2','images/img_06.jpg', SYSDATE(), null, null,null,null);
INSERT INTO `myvoteinfo_image` VALUES ('4', '1','images/img_05.jpg', SYSDATE(), null, null,null,null);

INSERT INTO `votecommentinfo` VALUES ('1', '1', 1, SYSDATE(), '价格太贵了', null,null,null,null);
INSERT INTO `votecommentinfo` VALUES ('2', '2', 2, SYSDATE(), '价格还可以接收', null,null,null,null);
INSERT INTO `votecommentinfo` VALUES ('3', '1', 3, SYSDATE(), '价格坑爹呀', null,null,null,null);
INSERT INTO `votecommentinfo` VALUES ('4', '2', 1, SYSDATE(), '真便宜', null,null,null,null);

INSERT INTO `joinvote` VALUES ('1', '1', 1, SYSDATE(), 1, null,null,null,null);
INSERT INTO `joinvote` VALUES ('2', '1', 2, SYSDATE(), 2, null,null,null,null);
INSERT INTO `joinvote` VALUES ('3', '2', 3, SYSDATE(), 3, null,null,null,null);
INSERT INTO `joinvote` VALUES ('4', '1', 4, SYSDATE(), 4, null,null,null,null);

--增加数据库表myvoteinfo字段
ALTER TABLE `myvoteinfo` ADD createDate DATETIME;
ALTER TABLE `myvoteinfo` ADD pricess DOUBLE;
ALTER TABLE `myvoteinfo` ADD isThrough INT;
ALTER TABLE `myvoteinfo` ADD commentCount INT;
ALTER TABLE `myvoteinfo` ADD gzCount INT;
ALTER TABLE `myvoteinfo` ADD love INT;
ALTER TABLE `myvoteinfo` ADD notLove INT;
ALTER TABLE `myvoteinfo` ADD buy INT;
ALTER TABLE `myvoteinfo` ADD isTop INT;
ALTER TABLE `myvoteinfo` ADD isRecommend INT;
ALTER TABLE `myvoteinfo` ADD isEndVote INT;


--关注
CREATE TABLE `gztopics` (
  `gzId` varchar(100) NOT NULL DEFAULT '',
  `CircledetailId` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`gzId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--通知
CREATE TABLE `inform` (
  `ifId` varchar(100) NOT NULL DEFAULT '',
  `gzId` varchar(100) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `CircledetailId` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `ccId` varchar(1000) DEFAULT NULL,
  `isRead` int(11) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ifId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--用户与话题
CREATE TABLE `usertopic` (
  `userId` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `def1` varchar(100) DEFAULT NULL,
  `def2` varchar(100) DEFAULT NULL,
  `def3` varchar(100) DEFAULT NULL,
  `def4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

