package com.yizu.proj.sys.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.TopicDetailForm;
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
import com.yizu.proj.sys.beans.TopicPk;
import com.yizu.proj.sys.beans.UserInfo;
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
@Scope("prototype")
@Controller("topicDetailAction")
public class TopicDetailAction extends BaseAction implements ModelDriven{
	private TopicDetailForm form = new TopicDetailForm();
	@Autowired
	private CircleCommentInfoAgreeService circleCommentInfoAgreeService;
	@Autowired
	private UserInfoService userInfoService;
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
	private InformService informService;
	@Autowired
	private TopicPkService topicPkService;
	/**
	 * 圈子详细信息
	 * @return
	 */
	public String topicDetail()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			/**
			 * 话题详细信息
			 */
			CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
			json.put("circleDetailInfo", circleDetailInfo);
			
			/**
			 * 热门话题
			 */
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" def3+0 desc");
			hotDetailInfoExample.setLimitStart(0);
			hotDetailInfoExample.setRowsPerPage(7);
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			json.put("hotTopics", hotTopics);
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
			countHotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			int countHotTopic = circleDetailInfoService.countByExample(countHotDetailInfoExample);
			json.put("countHotTopic", countHotTopic);
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
			 * 热门用户数量
			 */
			UserTopicExample counthotUserTopicExample = new UserTopicExample();
			int countHotUser = userTopicService.countByExample(counthotUserTopicExample);
			json.put("countHotUser", countHotUser);
			/**
			 * 判断用户是否已经登录
			 */
			boolean islogin = false;
			if(user!=null)
			{
				islogin = true;
				/**
				 * 是否已经关注当前话题
				 */
				GztopicsExample gztopicsExample = new GztopicsExample();
				gztopicsExample.createCriteria().andUseridEqualTo(user.getUserid()).andCircledetailidEqualTo(form.getId());
				List<Gztopics> gz = gztopicsService.selectByExample(gztopicsExample);
				if(gz.size()>0)
				{
					circleDetailInfo.setIsgz("1");
				}
				
				GztopicsExample gzsExample = new GztopicsExample();
				gzsExample.createCriteria().andUseridEqualTo(user.getUserid());
				List<Gztopics> gztopics = gztopicsService.selectByExample(gzsExample);
				Map<String,String> map = new HashMap<String,String>();
				for(Gztopics gztopic : gztopics)
				{
					map.put(gztopic.getCircledetailid(), "1");
				}
				
				/**
				 * 热门话题  关注
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 更多的热门话题
	 * @return
	 */
	public String moreTopics()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			CircleDetailInfoExample hotDetailInfoExample = new CircleDetailInfoExample();
			hotDetailInfoExample.createCriteria().andDef1EqualTo("1");
			hotDetailInfoExample.setOrderByClause(" def3 desc");
			hotDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			hotDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> hotTopics = circleDetailInfoService.selectByExample(hotDetailInfoExample);
			json.put("hotTopics", hotTopics);
			
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
	 * 一个评论的所有的回复
	 * @return
	 */
	public String getComtocom()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			ComtocomExample comtocomExample = new ComtocomExample();
			comtocomExample.createCriteria().andCcidEqualTo(form.getId()).andTouserEqualTo(form.getUid());
			comtocomExample.setOrderByClause(" createTime desc");
			comtocomExample.setLimitStart(0);
			comtocomExample.setRowsPerPage(200);
			List<Comtocom> comtocoms = comtocomService.selectByExample(comtocomExample);
			json.put("comtocoms", comtocoms);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 更多评论
	 * @return
	 */
	public String moreComment()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleCommentInfoExample circleCommentInfoExample = new CircleCommentInfoExample();
			circleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId());
			circleCommentInfoExample.setOrderByClause(" createdate desc ");
			circleCommentInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleCommentInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleCommentInfo> circleCommentInfos = circleCommentInfoService.selectByExample(circleCommentInfoExample);
			json.put("circleCommentInfos", circleCommentInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 赞同
	 * @return
	 */
	public String agree()
	{
		
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			boolean islogin = false;
			int r = 0;
			UserInfo user = getCurrentUser();
			if(getCurrentUser()==null)
			{
				islogin = false;
				json.put("r", 0);
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			CircleCommentInfoAgreeExample agreeExmple = new CircleCommentInfoAgreeExample();
			agreeExmple.createCriteria().andUseridEqualTo(user.getUserid()).andCcidEqualTo(form.getId());
			int agrees = circleCommentInfoAgreeService.countByExample(agreeExmple);
			if(agrees>0)
			{
				islogin = true;
				json.put("r", 1);
				json.put("islogin", islogin);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			CircleCommentInfoAgree agree = new CircleCommentInfoAgree();
			agree.setAid(String.valueOf(UUID.randomUUID()));
			agree.setCcid(form.getId());
			agree.setUserid(user.getUserid());
			agree.setAgreedate(new Date());
			int result = circleCommentInfoAgreeService.insert(agree);
			if(result>0)
			{
				/**
				 * 更新赞同数量
				 */
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
					islogin = true;
					json.put("islogin", islogin);
					json.put("r", 2);
					form.setJsonMsg("success", true, json);
					return JSON_PAGE;
				}else{
					islogin = true;
					json.put("islogin", islogin);
					form.setJsonMsg("赞同失败", false, null);
					return JSON_PAGE;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//不赞成
	public String notAgree()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			int r = 0;
			if(getCurrentUser()==null)
			{
				json.put("r", 0);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			CircleCommentInfoAgreeExample agreeExmple = new CircleCommentInfoAgreeExample();
			agreeExmple.createCriteria().andUseridEqualTo(user.getUserid()).andCcidEqualTo(form.getId());
			int agrees = circleCommentInfoAgreeService.countByExample(agreeExmple);
			if(agrees>0)
			{
				json.put("r", 1);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			CircleCommentInfoAgree agree = new CircleCommentInfoAgree();
			agree.setAid(String.valueOf(UUID.randomUUID()));
			agree.setCcid(form.getId());
			agree.setUserid(user.getUserid());
			agree.setAgreedate(new Date());
			int result = circleCommentInfoAgreeService.insert(agree);
			if(result>0)
			{
				/**
				 * 更新数量
				 */
				CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(form.getId(), CircleCommentInfo.class);
				String df2 = circleCommentInfo.getDef2();
				if(df2==null)
				{
					circleCommentInfo.setDef2("0");
				}
				int df = Integer.parseInt(df2);
				df = df+1;
				circleCommentInfo.setDef2(String.valueOf(df));
				int count = circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
				json.put("r", 2);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}else{
				form.setJsonMsg("不赞同失败", false, null);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 获取PK话题的评论  红方和蓝方
	 * @return
	 */
	public String getPkComment()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**
			 * 红方的评论
			 */
			CircleCommentInfoExample redCircleCommentInfoExample = new CircleCommentInfoExample();
			redCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(1);
			redCircleCommentInfoExample.setOrderByClause(" createdate desc ");
			redCircleCommentInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			redCircleCommentInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleCommentInfo> redCircleCommentInfos = circleCommentInfoService.selectByExample(redCircleCommentInfoExample);
			json.put("redCircleCommentInfos", redCircleCommentInfos);
			
			/**
			 * 蓝方的评论
			 */
			CircleCommentInfoExample blueCircleCommentInfoExample = new CircleCommentInfoExample();
			blueCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(2);
			blueCircleCommentInfoExample.setOrderByClause(" createdate desc ");
			blueCircleCommentInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			blueCircleCommentInfoExample.setRowsPerPage(form.getPageSize());
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
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * PK话题评论
	 * @return
	 */
	public String pkComment()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			if(user==null)
			{
				islogin = false;
				json.put("islogin", islogin);
				json.put("result", "你还没有登录，请先登录吧！");
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
			if(circleDetailInfo!=null)
			{
				islogin = true;
				json.put("islogin", islogin);
				/**
				 * 判断用户是否已经参与本话题PK评论
				 */
				CircleCommentInfoExample countCircleCommentInfoExample = new CircleCommentInfoExample();
				countCircleCommentInfoExample.createCriteria().andUseridEqualTo(user.getUserid()).andCircledetailidEqualTo(form.getId());
				int ccount = circleCommentInfoService.countByExample(countCircleCommentInfoExample);
				if(ccount>0)
				{
					json.put("result", "你已经参与本PK话题评论了！");
					form.setJsonMsg("success", true, json);
					return JSON_PAGE;
				}
				/**
				 * 评论
				 */
				CircleCommentInfo circleCommentInfo = new CircleCommentInfo();
				circleCommentInfo.setCcid(String.valueOf(UUID.randomUUID()));
				circleCommentInfo.setCircledetailid(form.getId());
				circleCommentInfo.setCommentinfo(form.getContent());
				circleCommentInfo.setCreatedate(new Date());
				circleCommentInfo.setUserid(user.getUserid());
				circleCommentInfo.setIspktype(form.getComType());
				circleCommentInfo.setDef1("0");
				circleCommentInfo.setDef2("0");
				circleCommentInfo.setDef3("0");
				circleCommentInfo.setDef4("0");
				circleCommentInfo.setDef5("0");
				circleCommentInfo.setDef6("0");
				circleCommentInfo.setDef7("0");
				int r = circleCommentInfoService.insert(circleCommentInfo);
				String or = "";
				if(r>0)
				{
					/**
					 * 更新红方、蓝方的评论数量
					 */
					TopicPk topicPk = (TopicPk) topicPkService.selectByPrimaryKey(form.getId(), TopicPk.class);
					if(topicPk!=null)
					{
						if(circleCommentInfo.getIspktype()==1)
						{
							or = "红方";
							if(topicPk.getRedcount()==null)
							{
								topicPk.setRedcount(0);
							}
							System.out.println(topicPk.getRedcount());
							int rCount = (topicPk.getRedcount())+1;
							topicPk.setRedcount(rCount);
						}
						if(circleCommentInfo.getIspktype()==2)
						{
							or = "蓝方";
							if(topicPk.getBluecount()==null)
							{
								topicPk.setBluecount(0);
							}
							int bCount = (topicPk.getRedcount())+1;
							topicPk.setBluecount(bCount);
						}
						int r_topicpk = topicPkService.updateByPrimaryKey(topicPk);
					}
					
					/**
					 * 更新评论话题总数量
					 */
					circleDetailInfo.setComcount((circleDetailInfo.getComcount())+1);
					int  rs = circleDetailInfoService.updateByPrimaryKey(circleDetailInfo);
					json.put("result", "评论"+or+"成功！");
					form.setJsonMsg("success", true, json);
				}else{
					json.put("result", "评论失败！");
					form.setJsonMsg("success", true, json);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("result", "评论失败！");
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**
	 * 获取评论
	 * @return
	 */
	public String getComment()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**
			 * 红方的评论
			 */
			CircleCommentInfoExample redCircleCommentInfoExample = new CircleCommentInfoExample();
			redCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(1);
			redCircleCommentInfoExample.setOrderByClause(" createdate desc ");
			redCircleCommentInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			redCircleCommentInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleCommentInfo> redCircleCommentInfos = circleCommentInfoService.selectByExample(redCircleCommentInfoExample);
			json.put("redCircleCommentInfos", redCircleCommentInfos);
			
			/**
			 * 蓝方的评论
			 */
			CircleCommentInfoExample blueCircleCommentInfoExample = new CircleCommentInfoExample();
			blueCircleCommentInfoExample.createCriteria().andCircledetailidEqualTo(form.getId()).andIspktypeEqualTo(2);
			blueCircleCommentInfoExample.setOrderByClause(" createdate desc ");
			blueCircleCommentInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			blueCircleCommentInfoExample.setRowsPerPage(form.getPageSize());
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
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 删除评论
	 * @return
	 */
	public String deleteComment()
	{
		String result = "";
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			
			/**判断用户是否已经登录*/
			if(user==null)
			{
				islogin = false;
				json.put("islogin", islogin);
				result = "你还没有登录！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			
			islogin = true;
			json.put("islogin", islogin);
			String ccid =  form.getId();
			
			/**判断该评论是否还存在*/
			CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(ccid, CircleCommentInfo.class);
			if(circleCommentInfo == null)
			{
				result = "该条评论已经被删除了！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			
			/**判断该评论是否是本人评论的*/
			if(!circleCommentInfo.getUserid().equals(user.getUserid()))
			{
				result = "该条评论不是你评论的，不能删除！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			int re = circleCommentInfoService.deleteByPrimaryKey(ccid, CircleCommentInfo.class);
			if(re>0)
			{
				result = "删除成功！";
				ComtocomExample comtocomExample = new ComtocomExample();
				comtocomExample.createCriteria().andCcidEqualTo(ccid);
				List<Comtocom> comtocom = comtocomService.selectByExample(comtocomExample);
				if(comtocom.size()>0){
					comtocomService.deleteByExample(comtocomExample);
				}
				/**更新话题的评论数量*/
				CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getIds(), CircleDetailInfo.class);
				circleDetailInfo.setComcount((circleDetailInfo.getComcount())-1);
				int c = circleDetailInfoService.updateByPrimaryKey(circleDetailInfo);
				
			}
			else
			{
				result = "删除失败！";
			}
			json.put("result", result);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "删除失败！";
			json.put("result", result);
			form.setJsonMsg("success", true, json);
			return JSON_PAGE;
		}
		return JSON_PAGE;
	}
	/**
	 * 编辑评论
	 * @return
	 */
	public String editComment()
	{
		String result = "";
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			boolean islogin = false;
			
			/**判断用户是否已经登录*/
			if(user==null)
			{
				islogin = false;
				json.put("islogin", islogin);
				result = "你还没有登录！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			
			islogin = true;
			json.put("islogin", islogin);
			String ccid =  form.getId();
			
			/**判断该评论是否还存在*/
			CircleCommentInfo circleCommentInfo = (CircleCommentInfo) circleCommentInfoService.selectByPrimaryKey(ccid, CircleCommentInfo.class);
			if(circleCommentInfo == null)
			{
				result = "该条评论已经被删除了,不能修改！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			
			/**判断该评论是否是本人评论的*/
			if(circleCommentInfo.getUserid()!=user.getUserid())
			{
				result = "该条评论不是你评论的，不能修改！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			/**修改评论*/
			circleCommentInfo.setCommentinfo(form.getContent());
			int re = circleCommentInfoService.updateByPrimaryKey(circleCommentInfo);
			if(re>0)
			{
				result = "修改成功！";
			}
			else
			{
				result = "修改失败！";
			}
			json.put("result", result);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = "修改失败！";
			json.put("result", result);
			form.setJsonMsg("success", true, json);
			return JSON_PAGE;
		}
		return JSON_PAGE;
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}

}
