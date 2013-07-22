package com.yizu.proj.sys.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.sys.action.form.FeiWenForm;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.FeiWen;
import com.yizu.proj.sys.beans.FeiWenExample;
import com.yizu.proj.sys.beans.FeiWenType;
import com.yizu.proj.sys.beans.FeiWenTypeExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.Yizubanner;
import com.yizu.proj.sys.beans.YizubannerExample;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.FeiWenService;
import com.yizu.proj.sys.service.FeiWenTypeService;
import com.yizu.proj.sys.service.YizubannerService;

@Scope("prototype")
@Controller("feiWenAction")
public class FeiWenAction extends BaseAction implements ModelDriven {
	private static final long serialVersionUID = -1517391340327305523L;
	private FeiWenForm form = new FeiWenForm();
	@Autowired
	private FeiWenService feiWenService;
	@Autowired
	private FeiWenTypeService feiWenTypeService;
	@Autowired
	private YizubannerService yizubannerService;
	@Autowired
	private CircleDetailInfoService circleDetailInfoService;
	public String indexFeiWen()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			FeiWenTypeExample feiWenTypeExample = new FeiWenTypeExample();
			List<FeiWenType> feiWenTypes = feiWenTypeService.selectByExample(feiWenTypeExample);
			json.put("feiWenTypes", feiWenTypes);
			for (int i = 0; i < feiWenTypes.size(); i++) {
				FeiWenType feiWenType = feiWenTypes.get(i);
				String tid = feiWenType.getTid();
				System.out.println(tid);
				FeiWenExample feiWenExample = new FeiWenExample();
				feiWenExample.createCriteria().andTidEqualTo(tid);
				feiWenExample.setOrderByClause(" createdatetime desc");
				feiWenExample.setLimitStart(0);
				feiWenExample.setRowsPerPage(1);
				List<FeiWen> feiwens = feiWenService.selectByExample(feiWenExample);
				feiWenType.setFeiWen(feiwens);
			}
//			YizubannerExample yBanner = new YizubannerExample();
//			List<Yizubanner> banner = yizubannerService.selectByExample(yBanner);
//			form.setBannerList(banner);
//			json.put("banner", banner);
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String selectFeiWen()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			if(user==null)
			{
				form.setJsonMsg("fail", false, null);
				return JSON_PAGE;
			}
			FeiWenTypeExample feiWenTypeExample = new FeiWenTypeExample();
			List<FeiWenType> feiWenTypes = feiWenTypeService.selectByExample(feiWenTypeExample);
			json.put("feiWenTypes", feiWenTypes);
			for (int i = 0; i < feiWenTypes.size(); i++) {
				FeiWenType feiWenType = feiWenTypes.get(i);
				String tid = feiWenType.getTid();
				System.out.println(tid);
				FeiWenExample feiWenExample = new FeiWenExample();
				feiWenExample.createCriteria().andTidEqualTo(tid).andUseridEqualTo(user.getUserid());
				feiWenExample.setOrderByClause(" createdatetime desc");
				feiWenExample.setLimitStart(0);
				feiWenExample.setRowsPerPage(20);
				List<FeiWen> feiwens = feiWenService.selectByExample(feiWenExample);
				feiWenType.setFeiWen(feiwens);
			}
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * feiWenAction_create.do
	 * @return
	 */
	public String create()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			
			UserInfo user = getCurrentUser();
			if(user!=null)
			{
				/*得到所有的飞闻分类*/
				FeiWenTypeExample feiWenTypeExample = new FeiWenTypeExample();
				List<FeiWenType> feiWenType = feiWenTypeService.selectByExample(feiWenTypeExample);
				json.put("islogin", true);
				json.put("feiWenType", feiWenType);
				form.setJsonMsg("success", true, json);
			}else{
				json.put("islogin", false);
				json.put("result", "你还没有登录！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 創建飞闻
	 * @return
	 */
	public String createFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			System.out.println(form.getFeiwentag());
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				FeiWenType feiWenType = (FeiWenType) feiWenTypeService.selectByPrimaryKey(form.getId(), FeiWenType.class);
				if(feiWenType!=null)
				{
					json.put("islogin", islogin);
					FeiWen feiWen = new FeiWen();
					feiWen.setFwid(form.getDatetimeid()+"-"+String.valueOf(new Date().getTime()));
					feiWen.setUserid(user.getUserid());
					feiWen.setCreatedatetime(new Date());
					feiWen.setTid(feiWenType.getTid());
					feiWen.setContent(form.getContent());
					feiWen.setComcount(0);
					feiWen.setTitle(form.getTitle());
					feiWen.setTag(form.getFeiwentag());
					feiWen.setDef1(String.valueOf(new Date().getTime()));
					feiWen.setDef2("0");
					feiWen.setDef3("0");
					feiWen.setDef4("0");
					feiWen.setDef5("0");
					feiWen.setDef6("0");
					feiWen.setDef7("0");
					feiWen.setDef8("0");
					int res = feiWenService.insert(feiWen);
					if(res>0)
					{
						json.put("result",1);
					}else{
						json.put("result",0);
					}
				}else{
					json.put("result", "这个飞闻分类已经被删除了，不能在该分类创建飞闻！");
				}
				form.setJsonMsg("success", true, json);
			}else{
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
			json.put("user", user);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("result", 0);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String getFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				json.put("islogin", islogin);
			}else{
				islogin = false;
				json.put("islogin", islogin);
			}
			FeiWen feiwen = (FeiWen) feiWenService.selectByPrimaryKey(form.getId(), FeiWen.class);
			
			if(!feiwen.getUserid().equals(user.getUserid())){
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				out.print("<script>alert('此条飞闻不是您创建，您不能进行编辑');window.location.href='"+getContextPath()+"'+'feiwen/feiwenList.jsp';</script>");
//				form.setJsonMsg("<script>alert('此条飞闻不是您创建，您不能进行编辑');</script>", false, null);
				return null;
			}
			form.setFeiWen(feiwen);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "update";
	}
	
	public String deleteFeiwen(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			if(user!=null)
			{
				json.put("islogin", true);
				FeiWen feiwen = (FeiWen) feiWenService.selectByPrimaryKey(form.getId(), FeiWen.class);
				if(feiwen==null)
				{
					json.put("res", "你创建的飞闻已经不存在了！");
					form.setJsonMsg("success", true, json);
					return JSON_PAGE;
				}
				if(!feiwen.getUserid().equals(user.getUserid()))
				{
					json.put("res", "该飞闻不是你创建的，不能删除！");
					form.setJsonMsg("success", true, json);
					return JSON_PAGE;
				}
				FeiWenExample feiWenExample = new FeiWenExample();
				feiWenExample.createCriteria().andFwidEqualTo(form.getId());
				int res = feiWenService.deleteByExample(feiWenExample);
				if(res==1){
					json.put("res", res);
				}else{
					json.put("res", 0);
				}
			}else{
				json.put("islogin", false);
				json.put("result", "你还没有登录！");
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String updateFeiwen(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				FeiWenType feiWenType = (FeiWenType) feiWenTypeService.selectByPrimaryKey(form.getTid(), FeiWenType.class);
				if(feiWenType!=null)
				{
					FeiWen feiwen =(FeiWen) feiWenService.selectByPrimaryKey(form.getFid(), FeiWen.class);
					feiwen.setTid(form.getTid());
					feiwen.setContent(form.getContent());
					feiwen.setTitle(form.getTitle());
					int res = feiWenService.updateByPrimaryKeySelective(feiwen);
					if(res>0)
					{
						json.put("result", "更新成功！");
					}else{
						json.put("result", "更新失败！");
					}
				}else{
					json.put("result", "这个飞闻分类已经被删除了，请更换分类！");
				}
				json.put("islogin", islogin);
			}else{
				json.put("islogin", islogin);
			}
			json.put("user", user);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("result", "更新失败！");
			form.setJsonMsg("success", true, json);
		}
		
		return JSON_PAGE;
	}
	//获取一个类别下的飞闻getFeiwenByTid
	public String getFeiwenByTid(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andTidEqualTo(form.getTid());
			int count = feiWenService.countByExample(feiWenExample);
			
			feiWenExample.setOrderByClause(" createDateTime desc");
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(30);
			List<FeiWen> feiwenByTid = feiWenService.selectByExample(feiWenExample);
			json.put("count", count);
			json.put("feiwenByTid", feiwenByTid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		form.setJsonMsg("success", true, json);
		return JSON_PAGE;
	}
	public String showone(){
		try{
//			String tid = form.getTid();
//			String fid = form.getFid();
//			String pageUPfid = form.getPageUPfid();
//			String pageDOWNfid = form.getPageDOWNfid();
			int pageNum = form.getPageNum();
			FeiWenType feiwentype = (FeiWenType) feiWenTypeService.selectByPrimaryKey(form.getTid(),FeiWenType.class);
			form.setType(feiwentype);
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andTidEqualTo(form.getTid());
			feiWenExample.setOrderByClause(" createDateTime desc");
			int userId ;
			if(pageNum <= 1){
				feiWenExample.setLimitStart(0);
				feiWenExample.setRowsPerPage(2);
				List<FeiWen> feiwenByTid = feiWenService.selectByExample(feiWenExample);
				for (int i = 0; i<feiwenByTid.size(); i++) {
					//其他飞闻
					userId= feiwenByTid.get(0).getUserid();
					fromUserIdGetOther(userId);
					form.setFeiWen(feiwenByTid.get(0));
					form.setPageDOWNfid(feiwenByTid.get(1).getFwid());
					form.setTitle(feiwenByTid.get(1).getTitle());
				}
			}else{
				feiWenExample.setLimitStart(form.getPageNum()-2);
				feiWenExample.setRowsPerPage(3);
				List<FeiWen> feiwenByTid = feiWenService.selectByExample(feiWenExample);
				for (int i = 0; i<feiwenByTid.size(); i++) {
					form.setPageUPfid(feiwenByTid.get(0).getFwid());
					form.setContent(feiwenByTid.get(0).getTitle());
					userId = feiwenByTid.get(1).getUserid();
					fromUserIdGetOther(userId);
					form.setFeiWen(feiwenByTid.get(1));
					form.setPageDOWNfid(feiwenByTid.get(2).getFwid());
					form.setTitle(feiwenByTid.get(2).getTitle());
				}
			}
			CircleDetailInfoExample new3DetailInfoExample = new CircleDetailInfoExample();
			new3DetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			new3DetailInfoExample.setOrderByClause(" createdatetime desc");
			new3DetailInfoExample.setLimitStart(0);
			new3DetailInfoExample.setRowsPerPage(4);
			List<CircleDetailInfo> newTopics = circleDetailInfoService.selectByExample(new3DetailInfoExample);
			for(CircleDetailInfo topicinfo : newTopics){
				topicinfo.setUserinfo(null);
				topicinfo.setCircleCommentInfo(null);
			}
			form.setNewTopics(newTopics);
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return "feiwen";
	}
	public void fromUserIdGetOther(int userId){
		try {
		//其他文章
		CircleDetailInfoExample otherDetail = new CircleDetailInfoExample();
		otherDetail.createCriteria().andUseridEqualTo(userId).andDef1IsNull();
		otherDetail.setLimitStart(0);
		otherDetail.setRowsPerPage(4);
		otherDetail.setOrderByClause(" createdatetime desc");
		List<CircleDetailInfo> circleDetailInfos = circleDetailInfoService.selectByExample(otherDetail);
		form.setOtherDetail(circleDetailInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public FeiWenForm getModel() {
		return form;
	}

}
