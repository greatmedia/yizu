package com.yizu.proj.sys.action.form;

import java.io.File;
import java.util.List;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.Circlerecommend;
import com.yizu.proj.sys.beans.Circletagrelate;
import com.yizu.proj.sys.beans.Circletalkabout;
import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserInfo;

public class JsonInfoForm extends BaseForm<UserInfo> {

	/**id*/
	private String id;
	/**当前页数*/
	private int pageNum;
	/**每页显示行数*/
	private int pageSize;
	/**类型*/
	private int type;
	/**热门话题类型*/
	private int hot;
	/**标签*/
	private String tag;
	/**发布信息内容*/
	private String content;
	/**批量加入圈子的数组信息*/
	private String cir;
	
	private File[] upFile; //待上传的文件
    private String[] upFileFileName;;  //待上传的真实文件名
	private String image;
	private String email;
	private int uid;
	private String pid;
	/**推送通知目标ID*/
	private int userid;
	/**CircleInfo	 */
	private CircleInfo circleleft;
	
	private String islogin;
	
	private int isAddCircle;
	
	private UserInfo user;
	
	private List<UserCircleRls> userCircleRlss;
	
	private List<UserCircleRls> circleAlluser;
	
	private CircleDetailInfo circleDetailInfo;

	private List<CircleDetailInfo> circleDetailInfos;
	
	private Circletalkabout talkabout;
	/**某圈子下的话题总数*/
	private int count;
	
	private String birthday;
	
	private String address;
	
	private String hobby;
	
	private String declaration;
	
	private List<Circletagrelate> circletagrelate;
	
	private List<Circlerecommend> circlerecommend;
	
	

	public List<UserCircleRls> getCircleAlluser() {
		return circleAlluser;
	}

	public void setCircleAlluser(List<UserCircleRls> circleAlluser) {
		this.circleAlluser = circleAlluser;
	}

	public List<Circlerecommend> getCirclerecommend() {
		return circlerecommend;
	}

	public void setCirclerecommend(List<Circlerecommend> circlerecommend) {
		this.circlerecommend = circlerecommend;
	}

	public List<Circletagrelate> getCircletagrelate() {
		return circletagrelate;
	}

	public void setCircletagrelate(List<Circletagrelate> circletagrelate) {
		this.circletagrelate = circletagrelate;
	}

	public Circletalkabout getTalkabout() {
		return talkabout;
	}

	public void setTalkabout(Circletalkabout talkabout) {
		this.talkabout = talkabout;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<CircleDetailInfo> getCircleDetailInfos() {
		return circleDetailInfos;
	}

	public void setCircleDetailInfos(List<CircleDetailInfo> circleDetailInfos) {
		this.circleDetailInfos = circleDetailInfos;
	}

	public CircleDetailInfo getCircleDetailInfo() {
		return circleDetailInfo;
	}

	public void setCircleDetailInfo(CircleDetailInfo circleDetailInfo) {
		this.circleDetailInfo = circleDetailInfo;
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

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getCir() {
		return cir;
	}

	public void setCir(String cir) {
		this.cir = cir;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public File[] getUpFile() {
		return upFile;
	}

	public void setUpFile(File[] upFile) {
		this.upFile = upFile;
	}

	public String[] getUpFileFileName() {
		return upFileFileName;
	}

	public void setUpFileFileName(String[] upFileFileName) {
		this.upFileFileName = upFileFileName;
	}
	
	
	
}
