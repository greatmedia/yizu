package com.yizu.proj.sys.action.form;

import java.io.File;
import java.util.List;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.City;
import com.yizu.proj.sys.beans.Province;
import com.yizu.proj.sys.beans.TfindPwdLog;
import com.yizu.proj.sys.beans.Town;
import com.yizu.proj.sys.beans.UserInfo;

public class UserInfoForm extends BaseForm<UserInfo> {
	private File[] upFile; //待上传的文件
    private String[] upFileFileName;;  //待上传的真实文件名
    /**当前页数*/
	private int pageNum;
	/**每页显示行数*/
	private int pageSize;

	private String name;
	private UserInfo instance; 
	private List<UserInfo> userInfoList;
	
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	private String code;
	private TfindPwdLog tfindPwdLog;
	
	public String ref;
	public String hrefRef;
	private String email;
	private String pwd;
	
	private int userId;
	private int coverImgId;
	
	private String sex;
	private String birthday;
	private String habby;
	private String address;
	private String what;
	private String nick;
	
	private Province province;
	private City city;
	private Town town;

	
	private String imgx;  
    private String imgy;  
    private String imgwidth;  
    private String imgheight;  
    private String imgpath;
    
	public File filedata;
	public String filename;
	public String images;
	
	
	
	public int getCoverImgId() {
		return coverImgId;
	}

	public void setCoverImgId(int coverImgId) {
		this.coverImgId = coverImgId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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

	public String getImgx() {
		return imgx;
	}

	public void setImgx(String imgx) {
		this.imgx = imgx;
	}

	public String getImgy() {
		return imgy;
	}

	public void setImgy(String imgy) {
		this.imgy = imgy;
	}

	public String getImgwidth() {
		return imgwidth;
	}

	public void setImgwidth(String imgwidth) {
		this.imgwidth = imgwidth;
	}

	public String getImgheight() {
		return imgheight;
	}

	public void setImgheight(String imgheight) {
		this.imgheight = imgheight;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getHabby() {
		return habby;
	}

	public void setHabby(String habby) {
		this.habby = habby;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getHrefRef() {
		return hrefRef;
	}

	public void setHrefRef(String hrefRef) {
		this.hrefRef = hrefRef;
	}

	

	public String getName() {
		return name;
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

	public void setName(String name) {
		this.name = name;
	}

	public UserInfo getInstance() {
		return instance;
	}

	public void setInstance(UserInfo instance) {
		this.instance = instance;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public TfindPwdLog getTfindPwdLog() {
		return tfindPwdLog;
	}

	public void setTfindPwdLog(TfindPwdLog tfindPwdLog) {
		this.tfindPwdLog = tfindPwdLog;
	}
	private List<Province> listProvinces;
	private List<City> listCitys;
	private List<Town> listTowns;

	public List<Province> getListProvinces() {
		return listProvinces;
	}

	public void setListProvinces(List<Province> listProvinces) {
		this.listProvinces = listProvinces;
	}

	public List<City> getListCitys() {
		return listCitys;
	}

	public void setListCitys(List<City> listCitys) {
		this.listCitys = listCitys;
	}

	public List<Town> getListTowns() {
		return listTowns;
	}

	public void setListTowns(List<Town> listTowns) {
		this.listTowns = listTowns;
	}
	
}
