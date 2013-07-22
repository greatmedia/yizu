package com.yizu.proj.sys.action.form;

import java.io.File;
import java.util.List;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.CiecleDetailImg;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.MyVoteInfo;
import com.yizu.proj.sys.beans.MyVoteInfoImage;

public class MyVoteInfoForm extends BaseForm<CircleInfo> {
	/** id */
	private String id;
	/** 当前页数 */
	private int pageNum;
	/** 每页显示行数 */
	private int pageSize;
	/** 发布信息内容 */
	private String content;
	private List<MyVoteInfoImage> myVoteInfoImage;
	public String getEndTime() {
		return endTime;
	}
	public List<MyVoteInfoImage> getMyVoteInfoImage() {
		return myVoteInfoImage;
	}
	public void setMyVoteInfoImage(List<MyVoteInfoImage> myVoteInfoImage) {
		this.myVoteInfoImage = myVoteInfoImage;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	private File[] upFile; // 待上传的文件
	private String[] upFileFileName;; // 待上传的真实文件名
	private File filedata;
	private String filename;
	private String circleName;
	private MyVoteInfo myVoteInfo;
	private String images;
	private String endTime;
	private String startTime;
	private String vid;
	private int resultType;
	public String getVid() {
		return vid;
	}
	public void setVid(String vid) {
		this.vid = vid;
	}
	public int getResultType() {
		return resultType;
	}
	public void setResultType(int resultType) {
		this.resultType = resultType;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getId() {
		return id;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCircleName() {
		return circleName;
	}
	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}
	public MyVoteInfo getMyVoteInfo() {
		return myVoteInfo;
	}
	public void setMyVoteInfo(MyVoteInfo myVoteInfo) {
		this.myVoteInfo = myVoteInfo;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
}
