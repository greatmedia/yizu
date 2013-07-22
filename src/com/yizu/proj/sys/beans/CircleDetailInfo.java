package com.yizu.proj.sys.beans;

import java.util.List;

import com.yizu.proj.sys.beans.gen.CircleDetailInfoBase;

/**
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table circledetailinfo
*/
public class CircleDetailInfo extends CircleDetailInfoBase {
	/**创建圈子的用户信息*/
	private UserInfo userinfo;
	/**当前用户是否已加入该圈子*/
	private String isgz;
	
	/**谁关注了该话题*/
	private Gztopics gztopics;
	/**圈子详细图片集合*/
	private List<CiecleDetailImg> circleDetailImg;
	/**圈子详细回复集合*/
	private List<CircleCommentInfo> circleCommentInfo;
	private CircleCommentInfo topicCommentInfo;
	/**PK话题详细*/
	private TopicPk topicPks;
	/**是否PK*/
	private String ispk;
	

	public CircleCommentInfo getTopicCommentInfo() {
		return topicCommentInfo;
	}

	public void setTopicCommentInfo(CircleCommentInfo topicCommentInfo) {
		this.topicCommentInfo = topicCommentInfo;
	}

	public String getIspk() {
		return ispk;
	}

	public void setIspk(String ispk) {
		this.ispk = ispk;
	}

	public TopicPk getTopicPks() {
		return topicPks;
	}

	public void setTopicPks(TopicPk topicPks) {
		this.topicPks = topicPks;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

	public List<CiecleDetailImg> getCircleDetailImg() {
		return circleDetailImg;
	}

	public void setCircleDetailImg(List<CiecleDetailImg> circleDetailImg) {
		this.circleDetailImg = circleDetailImg;
	}

	public List<CircleCommentInfo> getCircleCommentInfo() {
		return circleCommentInfo;
	}

	public void setCircleCommentInfo(List<CircleCommentInfo> circleCommentInfo) {
		this.circleCommentInfo = circleCommentInfo;
	}
	public Gztopics getGztopics() {
		return gztopics;
	}

	public void setGztopics(Gztopics gztopics) {
		this.gztopics = gztopics;
	}

	public String getIsgz() {
		return isgz;
	}

	public void setIsgz(String isgz) {
		this.isgz = isgz;
	}
	
}