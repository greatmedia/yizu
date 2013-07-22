package com.yizu.proj.sys.action.form;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.Circlerecommend;
import com.yizu.proj.sys.beans.Circletag;
import com.yizu.proj.sys.beans.Circletagrelate;
import com.yizu.proj.sys.beans.TagTopic;
import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserInfo;

import java.io.File;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-6-3
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class CircleInfoForm extends BaseForm<CircleInfo> {
    private File[] upFile; //待上传的文件
    private String[] upFileFileName;;  //待上传的真实文件名
    
    private File filedata;
    private String filename;

    private String circleName;
    private CircleInfo instance;
    private List<CircleInfo> circleInfoList;
    private List<Circletagrelate> circletagrelateList;
    private String images;
    private CircleDetailInfo circleDetail;
    private CircleInfo circleInfo;
    
    private CircleInfo circleleft;
	private String islogin;
	private int isAddCircle;
	private UserInfo user;
	private List<Circlerecommend> circlerecommend;
	
	private List<UserCircleRls> userCircleRlss;
	//创建圈子页面所有圈子分类。
	private List<TagTopic> tag;
	private List<Circletag> circleTags;
	private String recommendId;
	private String title1;
	private String title2;
	private String title3;
	private String title4;
	
	public String getTitle1() {
		return title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public String getTitle2() {
		return title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	public String getTitle3() {
		return title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	public String getTitle4() {
		return title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	public String getRecommendId() {
		return recommendId;
	}

	public void setRecommendId(String recommendId) {
		this.recommendId = recommendId;
	}

	public List<Circlerecommend> getCirclerecommend() {
		return circlerecommend;
	}

	public void setCirclerecommend(List<Circlerecommend> circlerecommend) {
		this.circlerecommend = circlerecommend;
	}

	public List<Circletagrelate> getCircletagrelateList() {
		return circletagrelateList;
	}

	public void setCircletagrelateList(List<Circletagrelate> circletagrelateList) {
		this.circletagrelateList = circletagrelateList;
	}

	public List<Circletag> getCircleTags() {
		return circleTags;
	}

	public void setCircleTags(List<Circletag> circleTags) {
		this.circleTags = circleTags;
	}

	private String topictype;
	
    

    public String getTopictype() {
		return topictype;
	}

	public void setTopictype(String topictype) {
		this.topictype = topictype;
	}

	public List<TagTopic> getTag() {
		return tag;
	}

	public void setTag(List<TagTopic> tag) {
		this.tag = tag;
	}

	public CircleInfo getCircleleft() {
		return circleleft;
	}

	public void setCircleleft(CircleInfo circleleft) {
		this.circleleft = circleleft;
	}

	public String getIslogin() {
		return islogin;
	}

	public void setIslogin(String islogin) {
		this.islogin = islogin;
	}

	public int getIsAddCircle() {
		return isAddCircle;
	}

	public void setIsAddCircle(int isAddCircle) {
		this.isAddCircle = isAddCircle;
	}

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<UserCircleRls> getUserCircleRlss() {
		return userCircleRlss;
	}

	public void setUserCircleRlss(List<UserCircleRls> userCircleRlss) {
		this.userCircleRlss = userCircleRlss;
	}

	public CircleInfo getCircleInfo() {
		return circleInfo;
	}

	public void setCircleInfo(CircleInfo circleInfo) {
		this.circleInfo = circleInfo;
	}

	public String[] getUpFileFileName() {
        return upFileFileName;
    }

    public void setUpFileFileName(String[] upFileFileName) {
        this.upFileFileName = upFileFileName;
    }

    public File[] getUpFile() {
        return upFile;
    }

    public void setUpFile(File[] upFile) {
        this.upFile = upFile;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public CircleInfo getInstance() {
        return instance;
    }

    public void setInstance(CircleInfo instance) {
        this.instance = instance;
    }

    public List<CircleInfo> getCircleInfoList() {
        return circleInfoList;
    }

    public void setCircleInfoList(List<CircleInfo> circleInfoList) {
        this.circleInfoList = circleInfoList;
    }

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public CircleDetailInfo getCircleDetail() {
		return circleDetail;
	}

	public void setCircleDetail(CircleDetailInfo circleDetail) {
		this.circleDetail = circleDetail;
	}


}
