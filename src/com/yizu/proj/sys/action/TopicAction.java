package com.yizu.proj.sys.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.TopicForm;
import com.yizu.proj.sys.beans.CiecleDetailImg;
import com.yizu.proj.sys.beans.CiecleDetailImgExample;
import com.yizu.proj.sys.beans.CircleCommentInfo;
import com.yizu.proj.sys.beans.CircleCommentInfoAgree;
import com.yizu.proj.sys.beans.CircleCommentInfoAgreeExample;
import com.yizu.proj.sys.beans.CircleCommentInfoExample;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.Comtocom;
import com.yizu.proj.sys.beans.ComtocomExample;
import com.yizu.proj.sys.beans.Gztopics;
import com.yizu.proj.sys.beans.GztopicsExample;
import com.yizu.proj.sys.beans.Inform;
import com.yizu.proj.sys.beans.TopicPk;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.UserTopic;
import com.yizu.proj.sys.beans.UserTopicExample;
import com.yizu.proj.sys.service.CiecleDetailImgService;
import com.yizu.proj.sys.service.CircleCommentInfoAgreeService;
import com.yizu.proj.sys.service.CircleCommentInfoService;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.ComtocomService;
import com.yizu.proj.sys.service.GztopicsService;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.TopicPkService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.UserTopicService;
import com.yizu.proj.utils.Contents;
import com.yizu.proj.utils.DateUtil;
import com.yizu.proj.utils.ReplaceContent;

@Scope("prototype")
@Controller("topicAction")
public class TopicAction  extends BaseAction implements ModelDriven {
	private TopicForm form = new TopicForm();
	@Autowired
	private CircleCommentInfoAgreeService circleCommentInfoAgreeService;
	@Autowired
	private CircleDetailInfoService circleDetailInfoService;
	@Autowired
	private CiecleDetailImgService ciecleDetailImgService;
	@Autowired
	private ComtocomService comtocomService;
	@Autowired
	private GztopicsService gztopicsService;
	@Autowired
	private CircleCommentInfoService circleCommentInfoService;
	@Autowired
	private UserTopicService userTopicService;
	@Autowired
	private TopicPkService topicPkService;
	@Autowired
	private InformService informService;
	@Autowired
	private UserInfoService userInfoService;
	/**
	 * 搜索话题
	 * @return
	 */
	public String searchTopic()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user==null)
			{
				islogin = false;
				json.put("islogin", islogin);
			}else{
				islogin = true;
				json.put("islogin", islogin);
			}
			System.out.println(form.getContent());
			/**根据标题搜索*/
			CircleDetailInfoExample titleDetailExample = new CircleDetailInfoExample();
			titleDetailExample.createCriteria().andDef1EqualTo("1").andTitleLike("%"+form.getContent()+"%");
			/**得到总数量*/
			int titlecount = circleDetailInfoService.countByExample(titleDetailExample);
			json.put("titlecount", titlecount);
			System.out.println(titlecount);
			/**用分页查询数据、得到搜索内容*/
			titleDetailExample.setLimitStart((form.getPageSize()-1)*form.getPageNum());
			titleDetailExample.setRowsPerPage(form.getPageSize());
			titleDetailExample.setDistinct(true);
			List<CircleDetailInfo> titleTopics = circleDetailInfoService.selectByExample(titleDetailExample);
			json.put("titleTopics", titleTopics);
			System.out.println(titleTopics.size());
			
			/**用户是否已经关注该话题*/
			if(user!=null)
			{
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
			
				for (CircleDetailInfo topic : titleTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				json.put("gztopics", gztopics);//当前用户关注的话题
			}
			/**根据内容搜索*/
			form.setJsonMsg("success", true, json);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//获取所有的话题
	public String getAllTopics()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andDef1EqualTo("1");
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
			
			CircleDetailInfoExample countDetailInfoExample = new CircleDetailInfoExample();
			countDetailInfoExample.createCriteria().andDef1EqualTo("1");
			int count = circleDetailInfoService.countByExample(countDetailInfoExample);
			json.put("count", count);
			json.put("allTopics", allTopics);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//得到话题图片
	public String getTopicImg()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			System.out.println(form.getId());
			CiecleDetailImgExample cirCiecleDetailImgExample = new CiecleDetailImgExample();
			cirCiecleDetailImgExample.createCriteria().andCircledetailidEqualTo(form.getId());
			List<CiecleDetailImg> ciecleDetailImgs  = ciecleDetailImgService.selectByExample(cirCiecleDetailImgExample);
			System.out.println(ciecleDetailImgs.size());
			json.put("ciecleDetailImgs", ciecleDetailImgs);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//获取最热话题
	public String getHotTopic(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" createdatetime desc");
			hotDetailInfoExample.setLimitStart(0);
			hotDetailInfoExample.setRowsPerPage(5);
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			
			json.put("hotTopics", hotTopics);
			form.setJsonMsg("success",true,json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String comtossss(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			Comtocom comtocom = new Comtocom();
			comtocom.setCtid(String.valueOf(UUID.randomUUID()));
			comtocom.setCcid(form.getTid());
			comtocom.setContent(form.getComcontent());
			comtocom.setCreatetime(new Date());
			comtocom.setUserid(user.getUserid());
			comtocom.setTouser(form.getUserId());
			
			CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(form.getTid(), CircleCommentInfo.class);
			if(circleCommentInfo.getDef3()!=null){
				int def3 = Integer.valueOf(circleCommentInfo.getDef3())+1;
				circleCommentInfo.setDef3(String.valueOf(def3));
				circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
			}else{
				circleCommentInfo.setDef3("1");
				circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
			}
			int result = comtocomService.insert(comtocom);
			if(result>0)
			{
				System.out.println("评论成功");
			}else{
				System.out.println("评论失败");
			}
			json.put("result", result);
			form.setJsonMsg("success",true,json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//查询评论
	public String showcom(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			ComtocomExample comtocom = new ComtocomExample();
			comtocom.setOrderByClause("  createtime desc");
			comtocom.createCriteria().andCcidEqualTo(form.getTid()).andUseridEqualTo(form.getUserId());
			List<Comtocom> comList = comtocomService.selectByExample(comtocom);
			json.put("comList", comList);
			form.setJsonMsg("success",true,json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 获取一个话题下的评论
	 * 1-4
	 */
	public String getComForPhone(){
		try{
			System.out.println(form.getTid());
			CircleDetailInfo circleinfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getTid(), CircleDetailInfo.class);
			if(circleinfo == null){
				form.setJsonMsg("话题不存在", false, null, 1);
				return JSON_PAGE;
			}
			if(form.getPageNum()<=0){
				form.setJsonMsg("分页输入错误", false, null, 2);
				return JSON_PAGE;
			}
			Map<String, Object> json = new HashMap<String, Object>();
			CircleCommentInfoExample circleComment = new CircleCommentInfoExample();
			circleComment.createCriteria().andCircledetailidEqualTo(form.getTid());
			int comCount = circleCommentInfoService.countByExample(circleComment);
			json.put("comCount", comCount);
			circleComment.setOrderByClause(" createDate desc ");
			circleComment.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleComment.setRowsPerPage(form.getPageSize());
			List<CircleCommentInfo> circleCommentList = circleCommentInfoService.selectByExample(circleComment);
			json.put("commentList", circleCommentList);
			for (CircleCommentInfo circleCommentInfo : circleCommentList) {
				String s = ReplaceContent.replaceHtmlFromContent(circleCommentInfo.getCommentinfo());
				circleCommentInfo.setCommentinfo(s);
			}
			form.setJsonMsg("查询成功", true, json, 3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * PK话题
	 */
	public String pkTopics()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andDef2EqualTo(String.valueOf(2)).andDef1EqualTo(String.valueOf(1));
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			circleDetailInfoExample.setOrderByClause(" createdatetime desc ");
			List<CircleDetailInfo> circleDetailInfos = circleDetailInfoService.selectByExample(circleDetailInfoExample);
			json.put("circleDetailInfos", circleDetailInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 3个最新话题
	 * @return
	 */
	public String getTopic3new()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample new3DetailInfoExample = new CircleDetailInfoExample();
			new3DetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			new3DetailInfoExample.setOrderByClause(" createdatetime desc");
			new3DetailInfoExample.setLimitStart(0);
			new3DetailInfoExample.setRowsPerPage(3);
			List<CircleDetailInfo> new3Topics = circleDetailInfoService.selectByExample(new3DetailInfoExample);
			for(CircleDetailInfo topicinfo : new3Topics){
				topicinfo.setUserinfo(null);
				topicinfo.setCircleCommentInfo(null);
			}
			json.put("new3Topics", new3Topics);
			
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 3个最热话题
	 * @return
	 */
	public String getTopic3hot()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample hot3DetailInfoExample = new CircleDetailInfoExample();
			hot3DetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			hot3DetailInfoExample.setOrderByClause(" comcount desc");
			hot3DetailInfoExample.setLimitStart(0);
			hot3DetailInfoExample.setRowsPerPage(3);
			List<CircleDetailInfo> hot3Topics = circleDetailInfoService.selectByExample(hot3DetailInfoExample);
			for(CircleDetailInfo topicinfo : hot3Topics){
				topicinfo.setUserinfo(null);
				topicinfo.setCircleCommentInfo(null);
			}
			json.put("hot3Topics", hot3Topics);
			
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
		
	}
	/**
	 * 通过给定的userId获取user
	 * 2013-7-1
	 */
	public String findUserByUserId()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user  = (UserInfo) userInfoService.selectByPrimaryKey(form.getUserId(), UserInfo.class);
			user.setPassword("");
			json.put("user", user);
			form.setJsonMsg("success", true, json, 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return JSON_PAGE;
	}
	/**
	 * 查询最新数据 forphone
	 * 12-27
	 */
	public String getNewTopicForPhone() {
		try{
			Map<String, Object> json = new HashMap<String, Object>();
			if(form.getPageNum()>0){
				String type = form.getType();
				CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
				if(form.getUserId() > 0 ){
					circleDetailInfoExample.createCriteria().andUseridEqualTo(form.getUserId()).andDef1EqualTo("1").andDef2IsNull();
				}else{
					circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
				}
				if("1".equals(type)){
					circleDetailInfoExample.setOrderByClause(" createdatetime desc");
				}else if("2".equals(type)){
					circleDetailInfoExample.setOrderByClause(" comcount desc");
				}else{
					circleDetailInfoExample.setOrderByClause(" createdatetime desc");
				}
				circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				circleDetailInfoExample.setRowsPerPage(form.getPageSize());
				List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
				for (CircleDetailInfo circleInfo : allTopics) {
					if(circleInfo.getCircleCommentInfo().size()>0){
						circleInfo.setCircleCommentInfo(null);
					}
					String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
					circleInfo.setCirclecontent(s);
				}
				json.put("allTopics", allTopics);
				CircleDetailInfoExample countDetailInfoExample = new CircleDetailInfoExample();
				if(form.getUserId() > 0){
					countDetailInfoExample.createCriteria().andUseridEqualTo(form.getUserId()).andDef1EqualTo("1").andDef2IsNull();
				}else{
					countDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
				}
				int count = circleDetailInfoService.countByExample(countDetailInfoExample);
				json.put("count", count);
				
				form.setJsonMsg("success", true, json,1);
			}else{
				form.setJsonMsg("请传入正确的参数", false, null);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return JSON_PAGE;
	}
	/**
	 * 最热的  comcount desc
	 * 给手机提供数据  pageNum  pageSize
	 * 2013-4-10
	 */
	public String getHotTopicForPhone(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			circleDetailInfoExample.setOrderByClause(" comcount desc");
			if(form.getPageNum()>0){
				circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				circleDetailInfoExample.setRowsPerPage(form.getPageSize());
				List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
				for (CircleDetailInfo circleInfo : allTopics) {
					if(circleInfo.getCircleCommentInfo().size()>0){
						circleInfo.setCircleCommentInfo(null);
						circleInfo.setTopicCommentInfo(null);
					}
					String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
					circleInfo.setCirclecontent(s);
				}
				json.put("allTopics", allTopics);
				form.setJsonMsg("success", true, json);
			}else{
				form.setJsonMsg("请传入正确的参数", false, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
		public String getGreaterTopicForPhone(){
				try{
					Map<String, Object> json = new HashMap<String, Object>();
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
					circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull().andCreatedatetimeGreaterThan(tempDate.parse(form.getDatetime()));
					circleDetailInfoExample.setOrderByClause(" createdatetime desc ");
					if(form.getPageNum()>0){
						circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
						circleDetailInfoExample.setRowsPerPage(form.getPageSize());
						List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
						for (CircleDetailInfo circleInfo : allTopics) {
							if(circleInfo.getCircleCommentInfo().size()>0){
								circleInfo.setCircleCommentInfo(null);
								circleInfo.setTopicCommentInfo(null);
							}
							String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
							circleInfo.setCirclecontent(s);
						}
						json.put("allTopics", allTopics);
						form.setJsonMsg("success", true, json);
					}else{
						form.setJsonMsg("请传入正确的参数", false, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return JSON_PAGE;
			}
			public String getLessTopicForPhone(){
				try{
					Map<String, Object> json = new HashMap<String, Object>();
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
					circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull().andCreatedatetimeLessThan(tempDate.parse(form.getDatetime()));
					circleDetailInfoExample.setOrderByClause(" createdatetime desc ");
					if(form.getPageNum()>0){
						circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
						circleDetailInfoExample.setRowsPerPage(form.getPageSize());
						List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
						for (CircleDetailInfo circleInfo : allTopics) {
							if(circleInfo.getCircleCommentInfo().size()>0){
								circleInfo.setCircleCommentInfo(null);
								circleInfo.setTopicCommentInfo(null);
							}
							String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
							circleInfo.setCirclecontent(s);
						}
						json.put("allTopics", allTopics);
						form.setJsonMsg("success", true, json);
					}else{
						form.setJsonMsg("请传入正确的参数", false, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return JSON_PAGE;
			}
			public String getGreaterTopicPKForPhone(){
				try{
					Map<String, Object> json = new HashMap<String, Object>();
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
					circleDetailInfoExample.createCriteria().andDef2EqualTo("1").andDef1EqualTo("1").andCreatedatetimeGreaterThan(tempDate.parse(form.getDatetime()));
					circleDetailInfoExample.setOrderByClause(" createdatetime desc ");
					if(form.getPageNum()>0){
						circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
						circleDetailInfoExample.setRowsPerPage(form.getPageSize());
						List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
						for (CircleDetailInfo circleInfo : allTopics) {
							if(circleInfo.getCircleCommentInfo().size()>0){
								circleInfo.setCircleCommentInfo(null);
								circleInfo.setTopicCommentInfo(null);
							}
							String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
							circleInfo.setCirclecontent(s);
						}
						json.put("allTopics", allTopics);
						form.setJsonMsg("success", true, json);
					}else{
						form.setJsonMsg("请传入正确的参数", false, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return JSON_PAGE;
			}
			public String getLessTopicPKForPhone(){
				try{
					Map<String, Object> json = new HashMap<String, Object>();
					SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
					circleDetailInfoExample.createCriteria().andDef2EqualTo("1").andDef1EqualTo("1").andCreatedatetimeLessThan(tempDate.parse(form.getDatetime()));
					circleDetailInfoExample.setOrderByClause(" createdatetime desc ");
					if(form.getPageNum()>0){
						circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
						circleDetailInfoExample.setRowsPerPage(form.getPageSize());
						List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
						for (CircleDetailInfo circleInfo : allTopics) {
							if(circleInfo.getCircleCommentInfo().size()>0){
								circleInfo.setCircleCommentInfo(null);
								circleInfo.setTopicCommentInfo(null);
							}
							String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
							circleInfo.setCirclecontent(s);
						}
						json.put("allTopics", allTopics);
						form.setJsonMsg("success", true, json);
					}else{
						form.setJsonMsg("请传入正确的参数", false, null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return JSON_PAGE;
			}

	/**
	 * 给手机提供数据   datetime  pageNum  pageSize
	 * 根据时间查询此时间之前的数据
	 * 2013-04-10
	 */
	public String getTimeTopicForPhone(){
		try{
			Map<String, Object> json = new HashMap<String, Object>();
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull().andCreatedatetimeLessThan(tempDate.parse(form.getDatetime()));
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			if(form.getPageNum()>0){
				circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				circleDetailInfoExample.setRowsPerPage(form.getPageSize());
				List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
				for (CircleDetailInfo circleInfo : allTopics) {
					if(circleInfo.getCircleCommentInfo().size()>0){
						circleInfo.setCircleCommentInfo(null);
						circleInfo.setTopicCommentInfo(null);
					}
					String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
					circleInfo.setCirclecontent(s);
				}
				json.put("allTopics", allTopics);
				form.setJsonMsg("success", true, json);
			}else{
				form.setJsonMsg("请传入正确的参数", false, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 给手机提供数据   userId  pageNum  pageSize
	 * 返回json
	 * 2013-03-21
	 */
	public String getUserTopicForPhone(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			int userId = form.getUserId();
			if(form.getPageSize()<0){form.setJsonMsg("请传入正确的参数", false, null,2);return JSON_PAGE;}
			if(form.getPageNum()>0){
				CircleDetailInfoExample circleDetailInfo = new CircleDetailInfoExample();
				circleDetailInfo.createCriteria().andUseridEqualTo(userId).andDef1EqualTo("1").andDef2IsNull();
				circleDetailInfo.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				circleDetailInfo.setRowsPerPage(form.getPageSize());
				circleDetailInfo.setOrderByClause(" createdatetime desc");
				List<CircleDetailInfo> userTopics = circleDetailInfoService.selectByExample(circleDetailInfo);
				if(userTopics.size()<=0){form.setJsonMsg("该用户没有发表话题", true, null,3);return JSON_PAGE;}
				for (CircleDetailInfo circleInfo : userTopics) {
					if(circleInfo.getCircleCommentInfo().size()>0){
						circleInfo.setCircleCommentInfo(null);
					}
					String s = ReplaceContent.replaceHtmlFromContent(circleInfo.getCirclecontent());
					circleInfo.setCirclecontent(s);
				}
				json.put("userTopics", userTopics);
				form.setJsonMsg("success", true, json,1);
			}else{
				form.setJsonMsg("请传入正确的参数", false, null,2);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 根据分页得到最新话题信息,是否已经关注该话题
	 * @return
	 */
	public String getIndexTopicnew()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			
			/**
			 * 最新话题
			 */
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			System.out.println(form.getPageNum()+"-----------------------");
			if(form.getPageNum()==1 || form.getPageNum()<=0){
				circleDetailInfoExample.setLimitStart(3);
				circleDetailInfoExample.setRowsPerPage(7);
			}else{
				circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			}
			List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
//			for(CircleDetailInfo detailinfo : allTopics){
//				detailinfo.setCircleCommentInfo(null);
//			}
			json.put("allTopics", allTopics);
			
			UserTopicExample counthotUserTopicExample = new UserTopicExample();
			int hotUserCount = userTopicService.countByExample(counthotUserTopicExample);
			json.put("hotUserCount", hotUserCount);
			
			/**
			 * 参与用户数量
			 */
//			UserTopicExample userTopicExample = new UserTopicExample();
//			int userCount = userTopicService.countByExample(userTopicExample);
//			json.put("userCount", userCount);
			
			/**
			 * 热门用户
			 */
			UserTopicExample hotUserTopicExample = new UserTopicExample();
			hotUserTopicExample.setOrderByClause(" count desc");
			hotUserTopicExample.setLimitStart(0);
			hotUserTopicExample.setRowsPerPage(7);
			List<UserTopic> userTopics = userTopicService.selectByExample(hotUserTopicExample);
			json.put("userTopics", userTopics);
			
			/**
			 * 话题总数
			 */
			CircleDetailInfoExample countDetailInfoExample = new CircleDetailInfoExample();
			countDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			int count = circleDetailInfoService.countByExample(countDetailInfoExample);
			json.put("count", count);
			
			/**
			 * 判断用户是否已经登录
			 */
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
				/**
				 * 最新话题
				 */
				for (CircleDetailInfo topic : allTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				form.setJsonMsg("success", true, json);
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 根据分页得到最热话题信息,是否已经关注该话题
	 * @return
	 */
	public String getIndexTopichot(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**
			 * 热门话题
			 */
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" comcount desc");
			if(form.getPageNum()==1){
				hotDetailInfoExample.setLimitStart(3);
				hotDetailInfoExample.setRowsPerPage(7);
			}else{
				hotDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				hotDetailInfoExample.setRowsPerPage(form.getPageSize());
			}
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			json.put("hotTopics", hotTopics);
			
			/**
			 * 话题总数
			 */
			CircleDetailInfoExample countDetailInfoExample = new CircleDetailInfoExample();
			countDetailInfoExample.createCriteria().andDef1EqualTo("1");
			int count = circleDetailInfoService.countByExample(countDetailInfoExample);
			json.put("count", count);
			
			/**
			 * 判断用户是否已经登录
			 */
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
			
				/**
				 * 热门话题
				 */
				for (CircleDetailInfo topic : hotTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
		
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				form.setJsonMsg("success", true, json);
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	/**
	 * 根据分页得到PK话题信息,是否已经关注该话题
	 * @return
	 */
	public String getIndexTopicPK(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**
			 * PK话题
			 */
			CircleDetailInfoExample pkCircleDetailInfoExample = new CircleDetailInfoExample();
			pkCircleDetailInfoExample.createCriteria().andDef2EqualTo("1").andDef1EqualTo("1");
			pkCircleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			pkCircleDetailInfoExample.setRowsPerPage(form.getPageSize());
			pkCircleDetailInfoExample.setOrderByClause(" createdatetime desc ");
			List<CircleDetailInfo> pkTopics = circleDetailInfoService.selectByExample(pkCircleDetailInfoExample);
			json.put("pkTopics", pkTopics);
			
			/**
			 * PK话题的总数量
			 */
			CircleDetailInfoExample pkCountCircleDetailInfoExample = new CircleDetailInfoExample();
			pkCountCircleDetailInfoExample.createCriteria().andDef2EqualTo("1").andDef1EqualTo("1");
			int pkcount = circleDetailInfoService.countByExample(pkCountCircleDetailInfoExample);
			json.put("pkcount", pkcount);
			
			/**
			 * 判断用户是否已经登录
			 */
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
				
				/**
				 * PK话题
				 */
				for (CircleDetailInfo topic : pkTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				form.setJsonMsg("success", true, json);
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 热门话题
	 * @return
	 */
	public String getHotTopics()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" comcount desc");
			hotDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			hotDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andDef1EqualTo("1");
			int hotCount = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("hotCount", hotCount);
			json.put("hotTopics", hotTopics);
			form.setJsonMsg("success",true,json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * Pk 话题页面  得到Pk 话题
	 */
	public String getTopicPk(){
		Map<String, Object> json = new HashMap<String,Object>();
		String topicPkId = form.getId();
		UserInfo user = getCurrentUser();
		
		try {
			CircleDetailInfo pkTopic = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(topicPkId, CircleDetailInfo.class);
			json.put("pkTopic", pkTopic);
			
			TopicPk topicPk = (TopicPk) topicPkService.selectByPrimaryKey(topicPkId, TopicPk.class);
			json.put("topicPk", topicPk);
			
			/**
			 * 红方的评论
			 */
			CircleCommentInfoExample redCircleCommentInfoExample = new CircleCommentInfoExample();
			redCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(1);
			redCircleCommentInfoExample.setOrderByClause(" createdate desc ");
			redCircleCommentInfoExample.setLimitStart(0);
			redCircleCommentInfoExample.setRowsPerPage(10);
			List<CircleCommentInfo> redCircleCommentInfos = circleCommentInfoService.selectByExample(redCircleCommentInfoExample);
			json.put("redCircleCommentInfos", redCircleCommentInfos);
			
			/**
			 * 蓝方的评论
			 */
			CircleCommentInfoExample blueCircleCommentInfoExample = new CircleCommentInfoExample();
			blueCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(2);
			blueCircleCommentInfoExample.setOrderByClause(" createdate desc ");
			blueCircleCommentInfoExample.setLimitStart(0);
			blueCircleCommentInfoExample.setRowsPerPage(10);
			List<CircleCommentInfo> blueCircleCommentInfos = circleCommentInfoService.selectByExample(blueCircleCommentInfoExample);
			json.put("blueCircleCommentInfos", blueCircleCommentInfos);
			
			/**
			 * 红方的评论数量
			 */
			CircleCommentInfoExample redCountCircleCommentInfoExample = new CircleCommentInfoExample();
			redCountCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(1);
			int redCount = circleCommentInfoService.countByExample(redCountCircleCommentInfoExample);
			json.put("redCount", redCount);
			/**
			 * 蓝方的评论数量
			 */
			CircleCommentInfoExample blueCountCircleCommentInfoExample = new CircleCommentInfoExample();
			blueCountCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(2);
			int blueCount = circleCommentInfoService.countByExample(blueCountCircleCommentInfoExample);
			json.put("blueCount", blueCount);
			
			
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" def3+0 desc");
			hotDetailInfoExample.setLimitStart(0);
			hotDetailInfoExample.setRowsPerPage(7);
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			int hotCount = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("hotCount", hotCount);
			json.put("hotTopics", hotTopics);
			
			/**
			 * 热门用户
			 */
			UserTopicExample userTopicExample = new UserTopicExample();
			userTopicExample.setOrderByClause(" count desc ");
			userTopicExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userTopicExample.setRowsPerPage(form.getPageSize());
			List<UserTopic> userTopics = userTopicService.selectByExample(userTopicExample);
			
			/**
			 * 热门用户数量
			 */
			UserTopicExample counthotUserTopicExample = new UserTopicExample();
			int countHotUser = userTopicService.countByExample(counthotUserTopicExample);
			json.put("countHotUser", countHotUser);
			
			/**
			 * 谁关注了该话题
			 */
			for (int s = 0;s<hotTopics.size();s++) {
				CircleDetailInfo c = hotTopics.get(s);
				String cid = c.getCircledetailid();
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andCircledetailidEqualTo(cid);
				gztopicsExample.setLimitStart(0);
				gztopicsExample.setRowsPerPage(1);
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				for (int l = 0; l < gztopics.size(); l++) {
					Gztopics  gztopics2 = gztopics.get(l);
					c.setGztopics(gztopics2);
				}
			}
			
			/**
			 * 热门话题数量
			 */
			CircleDetailInfoExample countHotDetailInfoExample = new CircleDetailInfoExample();
			int countHotTopic = circleDetailInfoService.countByExample(countHotDetailInfoExample);
			json.put("countHotTopic", countHotTopic);
			
			UserTopicExample countUserExample = new UserTopicExample();
			int count = userTopicService.countByExample(countUserExample);
			json.put("count", count);
			json.put("userTopics", userTopics);
			
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				CircleCommentInfoExample circleCommentInfoExample = new CircleCommentInfoExample();
				circleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andUseridEqualTo(user.getUserid());
				List<CircleCommentInfo> ccid = circleCommentInfoService.selectByExample(circleCommentInfoExample);
				if(ccid.size()>0)
				{
					pkTopic.setIspk("1");
				}else{
					pkTopic.setIspk("0");
				}
				json.put("ccid", ccid);
				/**
				 * 当前话题是否已经关注
				 */
				GztopicsExample ngztopicsExample = new GztopicsExample();
				ngztopicsExample.createCriteria().andUseridEqualTo(user.getUserid()).andCircledetailidEqualTo(topicPkId);
				List<Gztopics> ngztopics = gztopicsService.selectByExample(ngztopicsExample);
				if(ngztopics.size()>0)
				{
					pkTopic.setIsgz("1");
				}
				/**
				 * 热门话题是否已经关注
				 */
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
			
				/**
				 * 热门话题
				 */
				for (CircleDetailInfo topic : hotTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("user", user);
		form.setJsonMsg("success",true,json);
		return JSON_PAGE;
	}
	/**
	 * 新的话题
	 * @return
	 */
	public String newPageTopics()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andDef1EqualTo("1").andDef2IsNull();
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> allTopics = circleDetailInfoService.selectByExample(circleDetailInfoExample);
			json.put("allTopics", allTopics);
			
			CircleDetailInfoExample countDetailInfoExample = new CircleDetailInfoExample();
			countDetailInfoExample.createCriteria().andDef1EqualTo("1");
			int count = circleDetailInfoService.countByExample(countDetailInfoExample);
			json.put("count", count);
			
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
			
				/**
				 * 热门话题
				 */
				for (CircleDetailInfo topic : allTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				form.setJsonMsg("success", true, json);
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 火热的话题
	 * @return
	 */
	public String hotPageTopics()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" comcount desc");
			hotDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			hotDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			json.put("hotTopics", hotTopics);
			
			CircleDetailInfoExample countDetailInfoExample = new CircleDetailInfoExample();
			countDetailInfoExample.createCriteria().andDef1EqualTo("1");
			int count = circleDetailInfoService.countByExample(countDetailInfoExample);
			json.put("count", count);
			
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
			
				/**
				 * 热门话题
				 */
				for (CircleDetailInfo topic : hotTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				form.setJsonMsg("success", true, json);
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * PK的话题
	 * @return
	 */
	public String pkPageTopics()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			CircleDetailInfoExample pkCircleDetailInfoExample = new CircleDetailInfoExample();
			pkCircleDetailInfoExample.createCriteria().andDef2EqualTo("1").andDef1EqualTo("1");
			pkCircleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			pkCircleDetailInfoExample.setRowsPerPage(form.getPageSize());
			pkCircleDetailInfoExample.setOrderByClause(" createdatetime desc ");
			List<CircleDetailInfo> pkTopics = circleDetailInfoService.selectByExample(pkCircleDetailInfoExample);
			json.put("pkTopics", pkTopics);
			
			
			
			CircleDetailInfoExample pkCountCircleDetailInfoExample = new CircleDetailInfoExample();
			pkCountCircleDetailInfoExample.createCriteria().andDef2EqualTo("1").andDef1EqualTo("1");
			int pkcount = circleDetailInfoService.countByExample(pkCountCircleDetailInfoExample);
			json.put("pkcount", pkcount);
			
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
			
				/**
				 * 热门话题
				 */
				for (CircleDetailInfo topic : pkTopics) {
					String val = map.get(topic.getCircledetailid());
					if(!StringUtils.isEmpty(val)){
						topic.setIsgz("1");
					}else{
						topic.setIsgz("0");
					}
				}
				json.put("islogin", islogin);//是否已经登录
				json.put("nowuser", user);//当前登录的用户
				json.put("gztopics", gztopics);//当前用户关注的话题
				form.setJsonMsg("success", true, json);
				
			}else{
				islogin = false;
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
			}
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 评论话题
	 * @return
	 */
	public String insertComment()
	{
//		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			if(user==null)
			{
				form.setJsonMsg("你还没有登录！", false, null);
			}else{
				//评论内容
				CircleCommentInfo circleCommentInfo = new CircleCommentInfo();
				String cid = String.valueOf(UUID.randomUUID());
				circleCommentInfo.setCcid(cid);
				circleCommentInfo.setCircledetailid(form.getTid());
				circleCommentInfo.setCreatedate(new Date());
				circleCommentInfo.setCommentinfo(form.getComcontent());
				circleCommentInfo.setUserid(user.getUserid());
				circleCommentInfo.setDef1("0");
				circleCommentInfo.setDef2("0");
				int result = circleCommentInfoService.insert(circleCommentInfo);
				if(result>0)
				{
					//增加话题评论数量
					CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getTid(), CircleDetailInfo.class);
					circleDetailInfo.setComcount((circleDetailInfo.getComcount())+1);
					circleDetailInfoService.updateByPrimaryKey(circleDetailInfo);
					
					informTopic(circleDetailInfo,user);
					
					UserTopic userTopic = (UserTopic) userTopicService.selectByPrimaryKey(user.getUserid(), UserTopic.class);
					CircleCommentInfoExample circCommentInfoExample = new CircleCommentInfoExample();
					circCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getTid()).andUseridEqualTo(user.getUserid());
					List<CircleCommentInfo> circcount = circleCommentInfoService.selectByExample(circCommentInfoExample);
					if(circcount.size()>1)
					{

					}else{
						
						if(userTopic!=null)
						{
							//更新数量
							userTopic.setCount((userTopic.getCount())+1);
							userTopicService.updateByPrimaryKey(userTopic);
						}else{
							//还没有参与评论
							UserTopic u = new UserTopic();
							u.setUserid(user.getUserid());
							u.setCreatetime(new Date());
							u.setCount(1);
							u.setDef1("0");
							u.setDef2("0");
							u.setDef3("0");
							u.setDef4("0");
							userTopicService.insert(u);
						}
					}
					
					//推送通知
//					CircleCommentInfoExample circleCommentInfoExample = new CircleCommentInfoExample();
//					circleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getTid());
//					List<CircleCommentInfo> circleCommentInfos = circleCommentInfoService.selectByExample(circleCommentInfoExample);
//					if(circleCommentInfos.size()>0)
//					{
//						for (int i = 0; i < circleCommentInfos.size(); i++) 
//						{
//							CircleCommentInfo gzcomment = circleCommentInfos.get(i);
//							Inform inform = new Inform();
//							inform.setIfid(String.valueOf(UUID.randomUUID()));
//							inform.setCcid(cid);
//							inform.setCircledetailid(form.getId());
//							inform.setCreatetime(new Date());
//							inform.setUserid(gzcomment.getUserid());
//							inform.setIsread(0);
//							int reifno = informService.insert(inform);
//						}
//					}
				}
				form.setJsonMsg("评论成功！", true, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 评论话题  为手机端提供接口
	 * 4-1
	 * @return
	 */
	public String insertCommentForPhone()
	{
		try {
			int userid = form.getUserId();
			Map<String, Object> json = new HashMap<String, Object>();
			if(form.getUserId() == 0){
				UserInfo user = new UserInfo();
				user.setNick("匿名用户");
				user.setRegistertime(DateUtil.getCurrentTime());
				int r = userInfoService.insert(user);
				if(r == 1){
					UserInfoExample uExample = new UserInfoExample();
					uExample.createCriteria().andNickEqualTo("匿名用户");
					uExample.setOrderByClause(" userID desc");
					uExample.setLimitStart(0);
					uExample.setRowsPerPage(1);
					List<UserInfo> users = userInfoService.selectByExample(uExample);
					json.put("user", users.get(0));
					userid = users.get(0).getUserid();
				}
			}
			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(userid, UserInfo.class);
			//评论内容
			CircleCommentInfo circleCommentInfo = new CircleCommentInfo();
			String cid = String.valueOf(UUID.randomUUID());
			circleCommentInfo.setCcid(cid);
			circleCommentInfo.setCircledetailid(form.getTid());
			circleCommentInfo.setCreatedate(new Date());
			circleCommentInfo.setCommentinfo(form.getComcontent());
			circleCommentInfo.setUserid(userid);
			circleCommentInfo.setDef1("0");
			circleCommentInfo.setDef2("0");
			//def3 为1 iOS 2 Android 3 WP 4 BB 5ubuntu 6 simban null 来自互联网
			if(form.getSource() > 0){
				circleCommentInfo.setDef3(String.valueOf(form.getSource()));
			}
			int result = circleCommentInfoService.insert(circleCommentInfo);
			if(result>0){
				//增加话题评论数量
				CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getTid(), CircleDetailInfo.class);
				circleDetailInfo.setComcount((circleDetailInfo.getComcount())+1);
				circleDetailInfoService.updateByPrimaryKey(circleDetailInfo);
				if(form.getUserId() != 0){
					informTopic(circleDetailInfo,user);
					UserTopic userTopic = (UserTopic) userTopicService.selectByPrimaryKey(form.getUserId(), UserTopic.class);
					CircleCommentInfoExample circCommentInfoExample = new CircleCommentInfoExample();
					circCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getTid()).andUseridEqualTo(userid);
					List<CircleCommentInfo> circcount = circleCommentInfoService.selectByExample(circCommentInfoExample);
					if(circcount.size()>1)
					{
					}else{
						if(userTopic!=null)
						{
							//更新数量
							userTopic.setCount((userTopic.getCount())+1);
							userTopicService.updateByPrimaryKey(userTopic);
						}else{
							//还没有参与评论
							UserTopic u = new UserTopic();
							u.setUserid(form.getUserId());
							u.setCreatetime(new Date());
							u.setCount(1);
							u.setDef1("0");
							u.setDef2("0");
							u.setDef3("0");
							u.setDef4("0");
							userTopicService.insert(u);
						}
					}
					form.setJsonMsg("评论成功！", true, null,1);
				}else{
					form.setJsonMsg("匿名用户评论成功！", true, json,0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 话题评论通知
	 * @param circleDetailInfo
	 */
	public void informTopic(CircleDetailInfo circleDetailInfo , UserInfo user)
	{
		try {
//			UserInfo user = getCurrentUser();
			Inform inform = new Inform();
			inform.setIfid(UUID.randomUUID()+"");
			inform.setCircleid(circleDetailInfo.getCircleid());
			inform.setUserid(user.getUserid());
			inform.setTouserid(circleDetailInfo.getUserid());
			inform.setCircledetailid(circleDetailInfo.getCircledetailid());
			inform.setGzid(Contents.DEF_NULL);
			inform.setCircledetailtype(Contents.TOPIC);
			inform.setCreatetime(new Date());
			inform.setInformcontent(form.getComcontent());
			inform.setInformtype(Contents.TOPIC_COMMENT);
			inform.setIsread(null);
			inform.setDef1(Contents.DEF_NULL);
			inform.setDef2(Contents.DEF_NULL);
			inform.setDef3(Contents.DEF_NULL);
			inform.setDef4(Contents.DEF_NULL);
			informService.insert(inform);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 热门用户
	 * @return
	 */
	public String moreUser()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserTopicExample userTopicExample = new UserTopicExample();
			userTopicExample.setOrderByClause(" count desc ");
			userTopicExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userTopicExample.setRowsPerPage(form.getPageSize());
			List<UserTopic> userTopics = userTopicService.selectByExample(userTopicExample);
			
			UserTopicExample countUserExample = new UserTopicExample();
			int count = userTopicService.countByExample(countUserExample);
			json.put("count", count);
			json.put("userTopics", userTopics);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	/**
	 * 话题赞和不赞的方法  为手机提共数据
	 * 2013-04-02
	 */
	public String topicAgreeOrNotForPhone(){
		try {
			System.out.println(form.getType()+"  "+form.getId()+" "+form.getUserId());
			String type = form.getType();//0 为赞同  1为不赞同
			if(type.equals("0")){
				//赞同
				CircleCommentInfoAgreeExample agreeExmple = new CircleCommentInfoAgreeExample();
				agreeExmple.createCriteria().andUseridEqualTo(form.getUserId()).andCcidEqualTo(form.getId());
				int agrees = circleCommentInfoAgreeService.countByExample(agreeExmple);
				if(agrees>0)
				{
					form.setJsonMsg("您已经赞同过了", true, null, 3);
					return JSON_PAGE;
				}
				CircleCommentInfoAgree agree = new CircleCommentInfoAgree();
				agree.setAid(String.valueOf(UUID.randomUUID()));
				agree.setCcid(form.getId());
				agree.setUserid(form.getUserId());
				agree.setAgreedate(new Date());
				int result = circleCommentInfoAgreeService.insert(agree);
				if(result>0){
					// 更新赞同数量
					CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(form.getId(), CircleCommentInfo.class);
					String df1 = circleCommentInfo.getDef1();
					if(df1==null)
					{
						circleCommentInfo.setDef1("0");
						df1 = "0";
					}
					int df = Integer.parseInt(df1);
					df = df+1;
					circleCommentInfo.setDef1(String.valueOf(df));
					int count = circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
					if(count>0)
					{
						form.setJsonMsg("赞成功", true, null, 1);
						return JSON_PAGE;
					}else{
						form.setJsonMsg("赞同失败", false, null,2);
						return JSON_PAGE;
					}
				}
			}else if(type.equals("1")){
				//不赞同
				CircleCommentInfoAgreeExample agreeExmple = new CircleCommentInfoAgreeExample();
				agreeExmple.createCriteria().andUseridEqualTo(form.getUserId()).andCcidEqualTo(form.getId());
				int agrees = circleCommentInfoAgreeService.countByExample(agreeExmple);
				if(agrees>0)
				{
					form.setJsonMsg("您已经赞同过了", true, null,3);
					return JSON_PAGE;
				}
				CircleCommentInfoAgree agree = new CircleCommentInfoAgree();
				agree.setAid(String.valueOf(UUID.randomUUID()));
				agree.setCcid(form.getId());
				agree.setUserid(form.getUserId());
				agree.setNotagreedate(new Date());
				int result = circleCommentInfoAgreeService.insert(agree);
				if(result>0)
				{
					CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(form.getId(), CircleCommentInfo.class);
					String df2 = circleCommentInfo.getDef2();
					if(df2==null)
					{
						circleCommentInfo.setDef2("0");
						df2 = "0";
					}
					int df = Integer.parseInt(df2);
					df = df+1;
					circleCommentInfo.setDef2(String.valueOf(df));
					int count = circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
					form.setJsonMsg("不赞同成功", true, null,1);
					return JSON_PAGE;
				}else{
					form.setJsonMsg("不赞同失败", false, null,2);
					return JSON_PAGE;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;	
	}
	/**
	 * 通过给定的commentId获取评论信息
	 * 2013-7-10
	 */
	public String findCommentForPhone(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			String commentId = form.getCommentId();
//			CircleCommentInfoExample commentExample = new CircleCommentInfoExample();
			CircleCommentInfo commentinfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(commentId, CircleCommentInfo.class);
			json.put("commentinfo", commentinfo);
			form.setJsonMsg("success", true, json,1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}

}
