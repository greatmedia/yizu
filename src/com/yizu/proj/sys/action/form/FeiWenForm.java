package com.yizu.proj.sys.action.form;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.FeiWen;
import com.yizu.proj.sys.beans.FeiWenType;
import com.yizu.proj.sys.beans.Yizubanner;

import java.util.List;


public class FeiWenForm extends BaseForm<FeiWen> {
	/**id*/
	private String id;
	/**当前页数*/
	private int pageNum;
	/**每页显示行数*/
	private int pageSize;
	public List<FeiWen> feiWens;
	private List<FeiWen> otherFeiWen;
	private List<CircleDetailInfo> newTopics;
	private List<CircleDetailInfo> otherDetail;
	private FeiWenType type;
	
	

	public FeiWenType getType() {
		return type;
	}

	public void setType(FeiWenType type) {
		this.type = type;
	}

	public List<CircleDetailInfo> getOtherDetail() {
		return otherDetail;
	}

	public void setOtherDetail(List<CircleDetailInfo> otherDetail) {
		this.otherDetail = otherDetail;
	}

	public List<CircleDetailInfo> getNewTopics() {
		return newTopics;
	}

	public void setNewTopics(List<CircleDetailInfo> newTopics) {
		this.newTopics = newTopics;
	}

	public List<FeiWen> getOtherFeiWen() {
		return otherFeiWen;
	}

	public void setOtherFeiWen(List<FeiWen> otherFeiWen) {
		this.otherFeiWen = otherFeiWen;
	}

	public List<FeiWen> getFeiWens() {
		return feiWens;
	}

	public void setFeiWens(List<FeiWen> feiWens) {
		this.feiWens = feiWens;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	/**类型ID*/
	private String tid;
	/**飞闻ID*/
	private String fid;
	private String pageUPfid;
	private String pageDOWNfid;
	/**评论ID*/
	private String cid;
	/**内容*/
	public String content;
	/**标题*/
	public String title;
	/**飞闻*/
	private String feiwentag;
	
	public String getFeiwentag() {
		return feiwentag;
	}

	public void setFeiwentag(String feiwentag) {
		this.feiwentag = feiwentag;
	}
	/**飞闻類型*/
	
	public List<FeiWenType> feiWenType;
	
	public String getPageUPfid() {
		return pageUPfid;
	}

	public void setPageUPfid(String pageUPfid) {
		this.pageUPfid = pageUPfid;
	}

	public String getPageDOWNfid() {
		return pageDOWNfid;
	}

	public void setPageDOWNfid(String pageDOWNfid) {
		this.pageDOWNfid = pageDOWNfid;
	}

	public List<FeiWenType> getFeiWenType() {
		return feiWenType;
	}

	public void setFeiWenType(List<FeiWenType> feiWenType) {
		this.feiWenType = feiWenType;
	}
	public FeiWen feiWen;
	
	public FeiWen getFeiWen() {
		return feiWen;
	}

	public void setFeiWen(FeiWen feiWen) {
		this.feiWen = feiWen;
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
		if(pageSize>20)
		{
			pageSize=20;
		}
		this.pageSize = pageSize;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	
	
}
