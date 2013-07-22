package com.yizu.proj.sys.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.email.Mail;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.config.AppParametersConfig;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.sys.action.form.JsonInfoForm;
import com.yizu.proj.sys.beans.CiecleDetailImg;
import com.yizu.proj.sys.beans.CiecleDetailImgExample;
import com.yizu.proj.sys.beans.CircleCommentInfo;
import com.yizu.proj.sys.beans.CircleCommentInfoExample;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.CircleInfoExample;
import com.yizu.proj.sys.beans.Gztopics;
import com.yizu.proj.sys.beans.GztopicsExample;
import com.yizu.proj.sys.beans.Inform;
import com.yizu.proj.sys.beans.InformExample;
import com.yizu.proj.sys.beans.JoinVote;
import com.yizu.proj.sys.beans.JoinVoteExample;
import com.yizu.proj.sys.beans.MyVoteInfo;
import com.yizu.proj.sys.beans.MyVoteInfoExample;
import com.yizu.proj.sys.beans.MyVoteInfoImage;
import com.yizu.proj.sys.beans.MyVoteInfoImageExample;
import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserCircleRlsExample;
import com.yizu.proj.sys.beans.UserEmaillog;
import com.yizu.proj.sys.beans.UserEmaillogExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.Visitor;
import com.yizu.proj.sys.beans.VisitorExample;
import com.yizu.proj.sys.service.CiecleDetailImgService;
import com.yizu.proj.sys.service.CircleCommentInfoService;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.CircleInfoService;
import com.yizu.proj.sys.service.GztopicsService;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.JoinVoteService;
import com.yizu.proj.sys.service.MyVoteInfoImageService;
import com.yizu.proj.sys.service.MyVoteInfoService;
import com.yizu.proj.sys.service.UserCircleRlsService;
import com.yizu.proj.sys.service.UserEmaillogService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.VisitorService;

@Scope("prototype")
@Controller("userCenterAction")
public class UserCenterAction extends BaseAction implements ModelDriven {
	private JsonInfoForm form = new JsonInfoForm();
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CircleInfoService circleInfoService;
	@Autowired
	private UserCircleRlsService userCircleRlsService;
	@Autowired
	private CircleDetailInfoService circleDetailInfoService;
	@Autowired
	private CiecleDetailImgService ciecleDetailImgService;
	@Autowired
	private CircleCommentInfoService circleCommentInfoService;
	@Autowired
	private MyVoteInfoService myVoteInfoService;
	@Autowired
	private MyVoteInfoImageService myVoteInfoImageService;
	@Autowired
	private JoinVoteService joinVoteService;
	@Autowired
	private GztopicsService gztopicsService;
	@Autowired
	private InformService informService;
	@Autowired
	private UserEmaillogService userEmaillogService;
	@Autowired
	private VisitorService visitorService;
	public String center() {
		try {
			UserInfo user = getCurrentUser();
			Map<String, Object> json = new HashMap<String, Object>();
			json.put("user", user);
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	/**
	 * 我的发言
	 * @return
	 */
	public String topic() {
		Map<String, Object> json = new HashMap<String, Object>();
		System.out.println("我的话题");
		try {
			UserInfo user = getCurrentUser();
			//我发表的所有话题
			System.out.println("getPageNum"+form.getPageNum()+"     getPageSize"+form.getPageSize());
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			//我的话题一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			//我创建的圈子数
			CircleInfoExample circleinfoCount = new CircleInfoExample();
			circleinfoCount.createCriteria().andUseridEqualTo(user.getUserid());
			int circleCount = circleInfoService.countByExample(circleinfoCount);
			//我创建的投票数
			MyVoteInfoExample voteCount = new MyVoteInfoExample();
			voteCount.createCriteria().andUseridEqualTo(user.getUserid());
			int myvoteCount = myVoteInfoService.countByExample(voteCount);
			json.put("user", user);
			json.put("count", count);
			json.put("circleCount", circleCount);
			json.put("myvoteCount", myvoteCount);
			json.put("user_topics", circleDetailInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我的话题  的所有的图片
	 * @return
	 */
	public String getImg()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		System.out.println("我的话题的所有的图片"+form.getId());
		try {
			CiecleDetailImgExample ciecleDetailImgExample  = new CiecleDetailImgExample();
			ciecleDetailImgExample.createCriteria().andCircledetailidEqualTo(form.getId());
			List<CiecleDetailImg> ciecleDetailImgs = ciecleDetailImgService.selectByExample(ciecleDetailImgExample);
			System.out.println("多少张图片："+ciecleDetailImgs.size());
			json.put("ciecleDetailImgs", ciecleDetailImgs);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我加入的圈子
	 * @return
	 */
	public String myAddCircle() {
		System.out.println("我加入的圈子");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause("jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(user.getUserid());
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			System.out.println("用户ID"+user.getUserid());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			System.out.println("我加入多少个圈子"+add_circles.size());
			
			UserCircleRlsExample countCircleRlsExample = new UserCircleRlsExample();
			countCircleRlsExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = userCircleRlsService.countByExample(countCircleRlsExample);
			json.put("count", count);
			json.put("user", user);
			json.put("add_circles", add_circles);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我创建的圈子
	 * @return
	 */
	public String myCreateCircle() {
		System.out.println("我创建的圈子");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleInfoExample.setRowsPerPage(form.getPageSize());
			System.out.println("userid="+user.getUserid());
			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
			
			CircleInfoExample countCircleInfoExample = new CircleInfoExample();
			countCircleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleInfoService.countByExample(countCircleInfoExample);
			json.put("count", count);
			json.put("my_create_circle", my_create_circle);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	/**
	 * 根据ID删除
	 * @return
	 */
	public String deleteById() {
		Map<String, Object> json = new HashMap<String, Object>();
				
		CircleDetailInfoExample circleExample = new CircleDetailInfoExample();
		CircleCommentInfoExample circleComExample = new CircleCommentInfoExample();
		CiecleDetailImgExample detailImgExample = new CiecleDetailImgExample();
		UserCircleRlsExample  userCircleRlsExample = new UserCircleRlsExample();
		
		int circleUserNum = 0;
		int huatiNum = 0;
		int quanziNum = 0;
		int pinglunNum = 0;
		int tupianNum = 0;
		try {
			boolean islogin = false;
			UserInfo user = getCurrentUser();
			String result = "";
			if(user==null)
			{
				islogin = false;
				json.put("islogin", islogin);
				result ="你还没有登录！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			islogin = true;
			String circleId = form.getId();
			CircleInfo circleInfo = (CircleInfo) circleInfoService.selectByPrimaryKey(circleId, CircleInfo.class);
			if(circleInfo==null)
			{
				json.put("islogin", islogin);
				result ="该圈子已经不存在了！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
			}
			if(!circleInfo.getUserid().equals(user.getUserid()))
			{
				json.put("islogin", islogin);
				result ="该圈子不是你创建的，不能删除！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
			}
			
			//删除圈子里面的成员
			userCircleRlsExample.createCriteria().andCircleidEqualTo(circleId);
			List<UserCircleRls> userCircleRls = userCircleRlsService.selectByExample(userCircleRlsExample);
			if(userCircleRls.size()>0){
				circleUserNum = userCircleRlsService.deleteByExample(userCircleRlsExample);
				System.out.println("圈子成员----"+circleUserNum);
			}
			//删除圈子里面的话题
			circleExample.createCriteria().andCircleidEqualTo(circleId);
			List<CircleDetailInfo> cirlist = circleDetailInfoService.selectByExample(circleExample);
			
			if(cirlist.size()>0){
				for (CircleDetailInfo circleDetailInfo : cirlist) {
					String circledetailid = circleDetailInfo.getCircleid();
					System.out.println("话题Id+++++++++"+circledetailid);
					//每个话题对应的评论
					circleComExample.createCriteria().andCircledetailidEqualTo(circledetailid);
					List<CircleCommentInfo> circleComment = circleCommentInfoService.selectByExample(circleComExample);
					if(circleComment.size()>0){
						pinglunNum = circleCommentInfoService.deleteByExample(circleComExample);
						System.out.println("删除话题评论----"+pinglunNum);
					}
					//话题对应的图片
					detailImgExample.createCriteria().andCircledetailidEqualTo(circledetailid);
					List<CiecleDetailImg> circleDetailImg = ciecleDetailImgService.selectByExample(detailImgExample);
					if(circleDetailImg.size()>0){
						tupianNum = ciecleDetailImgService.deleteByExample(detailImgExample);
						System.out.println("删除话题图片----"+tupianNum);
					}
				}
			
				huatiNum = circleDetailInfoService.deleteByExample(circleExample);
				System.out.println("删除话题-----"+huatiNum);
			}
			//删除圈子
			quanziNum = circleInfoService.deleteByPrimaryKey(circleId,CircleInfo.class);
			
			if (quanziNum > 0) {
				json.put("result", "删除圈子成功！");
				form.setJsonMsg("success", true, json);
			}else{
				json.put("result", "删除圈子失败！");
				form.setJsonMsg("success", true, json);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON_PAGE;
	}
	/**
	 * 他的个人中心
	 */
	public String getTAusercenter(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(Integer.parseInt(form.getId()),UserInfo.class);
			json.put("user", user);
			if(getCurrentUser() != null && !getCurrentUser().getUserid().equals(user.getUserid())){
				VisitorExample viExample = new VisitorExample();
				viExample.createCriteria().andUseridEqualTo(user.getUserid()).andVisitoruseridEqualTo(getCurrentUser().getUserid());
				int count = visitorService.countByExample(viExample);
				if(count < 1){
					Visitor visitor = new Visitor();
					visitor.setVid(UUID.randomUUID()+"");
					visitor.setUserid(user.getUserid());
					visitor.setVisitoruserid(getCurrentUser().getUserid());
					visitor.setVisitorname(getCurrentUser().getNick());
					visitor.setVisittime(new Date());
					visitorService.insert(visitor);
				}else{
					Visitor visitor = new Visitor();
					visitor.setVisittime(new Date());
					visitorService.updateByExampleSelective(visitor, viExample);
				}
				
			}
			//TA的话题一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			//TA创建的圈子数
			CircleInfoExample circleinfoCount = new CircleInfoExample();
			circleinfoCount.createCriteria().andUseridEqualTo(user.getUserid());
			int circleCount = circleInfoService.countByExample(circleinfoCount);
			json.put("circleCount", circleCount);
			//TA创建的投票数
			MyVoteInfoExample voteCount = new MyVoteInfoExample();
			voteCount.createCriteria().andUseridEqualTo(user.getUserid());
			int myvoteCount = myVoteInfoService.countByExample(voteCount);
			json.put("myvoteCount", myvoteCount);
			//ta加入的圈子数
			UserCircleRlsExample addcircle = new UserCircleRlsExample();
			addcircle.createCriteria().andUseridEqualTo(user.getUserid());
			int addcircleCount = userCircleRlsService.countByExample(addcircle);
			json.put("addcircleCount", addcircleCount);
			//ta加入的圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause("jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			for(UserCircleRls usercircle : add_circles){
					usercircle.setUserinfo(null);
					if(usercircle.getCircleInfo() != null){
						usercircle.getCircleInfo().setUserinfo(null);
					}else{
						userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
					}
					
			}
			json.put("add_circles", add_circles);
			//TA创建的圈子
//			CircleInfoExample circleInfoExample = new CircleInfoExample();
//			circleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
//			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
//			circleInfoExample.setRowsPerPage(form.getPageSize());
//			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
//			for (CircleInfo circleInfo : my_create_circle) {
//				circleInfo.setUserinfo(null);
//			}
//			json.put("createCircle", my_create_circle);
			//TA的发布话题
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()-6));
			circleDetailInfoExample.setRowsPerPage(form.getPageSize()-6);
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			for(CircleDetailInfo circledetail : circleDetailInfos){
				circledetail.setUserinfo(null);
				circledetail.setGztopics(null);
				circledetail.setCircleCommentInfo(null);
			}
			json.put("circleDetailInfos", circleDetailInfos);
			// TA 创建的投票
//			MyVoteInfoExample myvoteinfo = new MyVoteInfoExample();
//			myvoteinfo.createCriteria().andUseridEqualTo(user.getUserid());
//			myvoteinfo.setOrderByClause(" createDate desc");
//			myvoteinfo.setLimitStart((form.getPageNum()-1)*(form.getPageSize()-3));
//			myvoteinfo.setRowsPerPage(form.getPageSize()-3);
//			List<MyVoteInfo> voteList = myVoteInfoService.selectByExample(myvoteinfo);
//			for (MyVoteInfo vote : voteList) {
//				vote.setUserinfo(null);
//			}
//			json.put("voteList", voteList);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String hemoresaid(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA的话题一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			
			//TA的发布话题
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*12);
			circleDetailInfoExample.setRowsPerPage(12);
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			for(CircleDetailInfo circledetail : circleDetailInfos){
				circledetail.setUserinfo(null);
				circledetail.setGztopics(null);
				circledetail.setCircleCommentInfo(null);
			}
			json.put("circleDetailInfos", circleDetailInfos);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他的信息
	 * @return
	 */
	public String getUser()
	{
		try {
			
			Map<String, Object> json = new HashMap<String, Object>();
			System.out.println("用户信息ID"+form.getId());
			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(Integer.parseInt(form.getId()),UserInfo.class);
			json.put("user", user);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他的话题
	 * @return
	 */
	public String getUserTopic()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			System.out.println("用户ID==="+form.getId());
			circleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			
			json.put("count", count);
			json.put("user_topics", circleDetailInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他加入的圈子
	 * @return
	 */
	public String userAddCircle()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause("jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			System.out.println(form.getId());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			System.out.println("他加入多少个圈子"+add_circles.size());
			
			UserCircleRlsExample countCircleRlsExample = new UserCircleRlsExample();
			countCircleRlsExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int count = userCircleRlsService.countByExample(countCircleRlsExample);
			json.put("count", count);
			json.put("add_circles", add_circles);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他创建的圈子
	 * @return
	 */
	public String userCreateCircle()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(Integer.parseInt(form.getId()),UserInfo.class);
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleInfoExample.setRowsPerPage(form.getPageSize());
			System.out.println("userid="+user.getUserid());
			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
			System.out.println("他加入的圈子多少"+my_create_circle.size());
			
			CircleInfoExample countCircleInfoExample = new CircleInfoExample();
			countCircleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleInfoService.countByExample(countCircleInfoExample);
			json.put("my_create_circle", my_create_circle);
			json.put("count", count);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String setting()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			json.put("user", user);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常UserCenterAction类的方法userCreateCircle："+ e.getMessage());
		}
		return JSON_PAGE;
	}
	public String profile_setting() {
        try {
        	if (LOG.isDebugEnabled()) {
        		UserInfo user = getCurrentUser();
        		setCurrentUser(user);
        		LOG.debug("当前登录用户：" + getCurrentUser().getNick() + "==" + getCurrentUser().getOtheraccount());
			}
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return SUCCESS;
    }
	/**
	 * 设置个人资料
	 * @return
	 */
	public String setData()
	{
		System.out.println("设置个人资料");
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			String email = user.getEmail();
			if(user==null)
			{
				json.put("result", "你还没有登录！");
				form.setJsonMsg("success", true, json);
			}
			/**检查邮箱是否已经注册*/
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail()).andUseridNotEqualTo(user.getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				json.put("result", "该邮箱已经被其他用户抢先注册了,请换一个邮箱把！");
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			String isemail = "";
			if(!email.equals(form.getEmail()))
			{
				Mail m = new Mail();
				try {
					m.setMail_to(form.getEmail());
					
					/**邮件日志*/
					String eid = String.valueOf(UUID.randomUUID());
					UserEmaillog userEmaillog = new UserEmaillog();
					userEmaillog.setEid(eid);
					userEmaillog.setLogdatetime(new Date());
					userEmaillog.setUserid(user.getUserid());
					userEmaillog.setEmail(form.getEmail());
					
					userEmaillog.setIp(form.getIp());
					System.out.println(form.getIp());
					userEmaillog.setDisplay(1);
					userEmaillog.setModifytype(0);
					userEmaillog.setDef1("0");
					userEmaillog.setDef2("0");
					userEmaillog.setDef3("0");
					userEmaillog.setDef4("0");
					userEmaillogService.insert(userEmaillog);
					
				} catch (Exception e) {
				}
			}
			user.setEmail(form.getEmail());
			user.setNick(form.getId());
			user.setWhat(form.getIds());
			user.setImage(form.getImage());
			user.setBirthday(form.getBirthday());
			user.setAddress(form.getAddress());
			user.setHobby(form.getHobby());
			user.setDeclaration(form.getDeclaration());
			int result = userInfoService.updateByPrimaryKey(user);
			if(result>0)
			{
				json.put("result", "资料修改成功！"+isemail);
			}else{
				json.put("result", "修改失败！");
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 保存个人中心背景
	 * @return
	 */
	public String setUserImg(){
		try{
			UserInfo user = getCurrentUser();
			user.setWebsite(form.getImage());
			int result = userInfoService.updateByPrimaryKey(user);
			if(result>0)
			{
				form.setJsonMsg("success", true, null);
			}else{
				form.setJsonMsg("fail", true, null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String modifyEmail()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserEmaillogExample userEmaillogExample = new UserEmaillogExample();
			userEmaillogExample.createCriteria().andEidEqualTo(form.getId()).andUseridEqualTo(form.getUid()).andDisplayEqualTo(1);
			List<UserEmaillog> userEmaillogs =  userEmaillogService.selectByExample(userEmaillogExample);
			if(userEmaillogs.size()>0)
			{
				UserEmaillog userEmail = userEmaillogs.get(0);
				/**更新用户邮件*/
				UserInfo userInfo = (UserInfo) userInfoService.selectByPrimaryKey(userEmail.getUserid(), UserInfo.class);
				userInfo.setEmail(userEmail.getEmail());
				setCurrentUser(userInfo);
				int u = userInfoService.updateByPrimaryKey(userInfo);
				/**把当前的display设置为0,连接地址无效*/
				userEmail.setDisplay(0);
				int e = userEmaillogService.updateByPrimaryKey(userEmail);
				if(u>0)
				{
					json.put("result", "邮箱更改成功，现在邮箱地址:"+userEmail.getEmail()+"！");
				}else{
					json.put("result", "邮箱更改失败！");
				}
			}else{
				json.put("result", "连接地址无效！");
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("result", "连接地址无效！");
			return JSON_PAGE;
		}
		return JSON_PAGE;
	}
	public String activationEmail()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserEmaillogExample userEmaillogExample = new UserEmaillogExample();
			userEmaillogExample.createCriteria().andEidEqualTo(form.getId()).andUseridEqualTo(form.getUid()).andDisplayEqualTo(1);
			List<UserEmaillog> userEmaillogs =  userEmaillogService.selectByExample(userEmaillogExample);
			if(userEmaillogs.size()>0)
			{
				UserEmaillog userEmail = userEmaillogs.get(0);
				/**更新用户邮件*/
				UserInfo userInfo = (UserInfo) userInfoService.selectByPrimaryKey(userEmail.getUserid(), UserInfo.class);
				userInfo.setEmail(userEmail.getEmail());
				setCurrentUser(userInfo);
				int u = userInfoService.updateByPrimaryKey(userInfo);
				/**把当前的display设置为0,连接地址无效*/
				userEmail.setDisplay(0);
				int e = userEmaillogService.updateByPrimaryKey(userEmail);
				if(u>0)
				{
					json.put("result", "账号激活成功，邮箱地址:"+userEmail.getEmail());
				}else{
					json.put("result", "账号激活失败！");
				}
			}else{
				json.put("result", "连接地址无效！");
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("result", "连接地址无效！");
			return JSON_PAGE;
		}
		return JSON_PAGE;
	}
	/**
	 * 图片上传
	 * @return
	 */
	public String uploadFile() {
		try {
			File uploadPathDir = new File(AppParametersConfig.getParameter("file.tmp.path"));
			if (!uploadPathDir.exists())
				uploadPathDir.mkdirs();
			FileOutputStream fos = null;
			FileInputStream fis = null;
			File[] files = form.getUpFile();

			// 只有一个图片附件
			if (form.getUpFile() != null && form.getUpFile().length > 0) {
				// 拼接上传文件的新文件名
				int point = form.getUpFileFileName()[0].lastIndexOf(".");
				StringBuffer newName = new StringBuffer();
				newName.append(System.currentTimeMillis());
				newName.append(0);
				newName.append(form.getUpFileFileName()[0].substring(point));

				File f = new File(uploadPathDir, newName.toString());

				// 以服务器的文件保存地址和原文件名建立上传文件输出流
				fos = new FileOutputStream(f);
				fis = new FileInputStream(files[0]);
				byte[] buffer = new byte[1024 * 1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();// 注意：流应当关闭。
				fis.close();

				form.setJsonMsg("上传成功！", true, "uploadPath/tmp/" + newName.toString());
			} else {
				form.setJsonMsg("上传失败,请选择文件！", true, null);
			}

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("上传失败，请稍候重试！", false, null);
		}
		return JSON_PAGE;
	}
	/**
	 * 我创建的投票
	 * @return
	 */
	public String myCreateVote()
	{
		System.out.println("我创建的投票");
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> myVoteInfos = myVoteInfoService.selectByExample(myVoteInfoExample);
			
			MyVoteInfoExample countMyVoteInfoExample = new MyVoteInfoExample();
			countMyVoteInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count =myVoteInfoService.countByExample(countMyVoteInfoExample);
			json.put("count", count);
			json.put("myVoteInfos", myVoteInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 用户创建的投票
	 * @return
	 */
	public String userVote()
	{
		System.out.println("我创建的投票");
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> myVoteInfos = myVoteInfoService.selectByExample(myVoteInfoExample);

			MyVoteInfoExample countMyVoteInfoExample = new MyVoteInfoExample();
			countMyVoteInfoExample.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
			int count = myVoteInfoService.countByExample(countMyVoteInfoExample);
			json.put("count", count);
			json.put("myVoteInfos", myVoteInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 投票 图片
	 * @return
	 */
	public String userVoteImg()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			MyVoteInfoImageExample myVoteInfoImageExample = new MyVoteInfoImageExample();
			System.out.println(form.getId());
			myVoteInfoImageExample.createCriteria().andVoteIdEqualTo(form.getId());
			myVoteInfoImageExample.setLimitStart(0);
			myVoteInfoImageExample.setRowsPerPage(1);
			List<MyVoteInfoImage> myVoteInfoImages = myVoteInfoImageService.selectByExample(myVoteInfoImageExample);
			System.out.println("投票共有多少张图片"+myVoteInfoImages.size());
			json.put("myVoteInfoImages", myVoteInfoImages);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我关注的商品
	 * @return
	 */
	public String myGz()
	{
		System.out.println("我关注的商品");
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			JoinVoteExample joinVoteExample = new JoinVoteExample();
			joinVoteExample.createCriteria().andUseridEqualTo(user.getUserid()).andResulttypeEqualTo(1);
			joinVoteExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			joinVoteExample.setRowsPerPage(form.getPageSize());
			List<JoinVote> joinVotes = joinVoteService.selectByExample(joinVoteExample);
			System.out.println("我关注的商品有多少"+joinVotes.size());
			ArrayList map =new ArrayList();
			for (int i = 0; i < joinVotes.size(); i++) {
				JoinVote joinVote = joinVotes.get(i);
				String vid = joinVote.getVoteId();
				MyVoteInfoImageExample myVoteInfoImageExample = new MyVoteInfoImageExample();
				myVoteInfoImageExample.createCriteria().andVoteIdEqualTo(vid);
				myVoteInfoImageExample.setLimitStart(0);
				myVoteInfoImageExample.setRowsPerPage(1);
				List<MyVoteInfoImage> myVoteInfoImages = myVoteInfoImageService.selectByExample(myVoteInfoImageExample);
				System.out.println("投票共有多少张图片"+myVoteInfoImages.size());
				map.add(myVoteInfoImages);
//				json.put("myVoteInfoImages", myVoteInfoImages);
			}
			JoinVoteExample countJoinVoteExample = new JoinVoteExample();
			countJoinVoteExample.createCriteria().andUseridEqualTo(user.getUserid()).andResulttypeEqualTo(1);
			int count = joinVoteService.countByExample(countJoinVoteExample);
			json.put("myVoteInfoImages", map);
			json.put("count", count);
			json.put("joinVotes", joinVotes);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 取消我关注的话题
	 */
	public String deleteMytopic()
	{
		try {
			int result = gztopicsService.deleteByPrimaryKey(form.getId(), Gztopics.class);
			if(result>0)
			{
				//减少话题的关注数量
				//更新话题关注数量
				CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getIds(), CircleDetailInfo.class);
				if(circleDetailInfo.getDef3()==null)
				{
					circleDetailInfo.setDef3("0");
				}
				circleDetailInfo.setDef3(String.valueOf(Integer.parseInt(circleDetailInfo.getDef3())-1));
				circleDetailInfoService.updateByPrimaryKey(circleDetailInfo);
				UserInfo user = getCurrentUser();
				//删除我关注的话题通知
				InformExample informExample = new InformExample();
				informExample.createCriteria().andUseridEqualTo(user.getUserid()).andCircledetailidEqualTo(form.getIds());
				informService.deleteByExample(informExample);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 查询我关注的话题
	 * @return
	 */
	public String myGzTopics()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			GztopicsExample gztopicsExample = new GztopicsExample();
			gztopicsExample.createCriteria().andUseridEqualTo(currentUser.getUserid());
			gztopicsExample.setOrderByClause(" createtime desc ");
			List<Gztopics> gztopics = gztopicsService.selectByExample(gztopicsExample);
			json.put("gztopics", gztopics);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我没有查看的话题通知
	 * @return
	 */
	public String myResdInform()
	{
		try {
//			Map<String, Object> json = new HashMap<String, Object>();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我关注的话题通知
	 * @return
	 */
	public String myInform(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			InformExample informExample = new InformExample();
			informExample.createCriteria().andUseridEqualTo(currentUser.getUserid());
			informExample.setOrderByClause(" createtime desc ");
			List<Inform> informs = informService.selectByExample(informExample);
			json.put("informs", informs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 分页  用户创建的圈子
	 * @return
	 */
	public String pageUserCreateCircle()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleInfo> user_create_circle = circleInfoService.selectByExample(circleInfoExample);
			json.put("user_create_circle", user_create_circle);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 分页  用户创建的圈子
	 * @return
	 * 11-05
	 */
	public String TApageUserCreateCircle()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleInfo> user_create_circle = circleInfoService.selectByExample(circleInfoExample);
			json.put("user_create_circle", user_create_circle);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 分页 用户加入的圈子 TA
	 * @return
	 * 11-05
	 */
	public String TApageUserJoinCircle()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			//ta加入的圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause(" jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			for(UserCircleRls usercircle : add_circles){
				usercircle.setUserinfo(null);
				if(usercircle.getCircleInfo()!=null){
					usercircle.getCircleInfo().setUserinfo(null);	
				}else{
					userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
				}
			}
			json.put("add_circles", add_circles);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String pageUserJoinCircle()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			//ta加入的圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause(" jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			for(UserCircleRls usercircle : add_circles){
				usercircle.setUserinfo(null);
				if(usercircle.getCircleInfo()!=null){
					usercircle.getCircleInfo().setUserinfo(null);	
				}else{
					userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
				}
				
			}
			json.put("add_circles", add_circles);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 新版个人中心
	 * 10-31
	 */
	public String getusercenter(){
		try {
			UserInfo user = getCurrentUser();
			Map<String, Object> json = new HashMap<String, Object>();
			if(user==null){
				json.put("isLogin", 0);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}else{
				json.put("isLogin", 1);
			}
			json.put("user", user);
			
			//TA的话题一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			//TA创建的圈子数
			CircleInfoExample circleinfoCount = new CircleInfoExample();
			circleinfoCount.createCriteria().andUseridEqualTo(user.getUserid());
			int circleCount = circleInfoService.countByExample(circleinfoCount);
			json.put("circleCount", circleCount);
			//TA创建的投票数
			MyVoteInfoExample voteCount = new MyVoteInfoExample();
			voteCount.createCriteria().andUseridEqualTo(user.getUserid());
			int myvoteCount = myVoteInfoService.countByExample(voteCount);
			json.put("myvoteCount", myvoteCount);
			// 加入的圈子数
			UserCircleRlsExample addcircle = new UserCircleRlsExample();
			addcircle.createCriteria().andUseridEqualTo(user.getUserid());
			int addcircleCount = userCircleRlsService.countByExample(addcircle);
			json.put("addcircleCount", addcircleCount);
			
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他的个人中心
	 */
	public String getMEusercenter(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			if(user!=null){
				json.put("isLogin", 1);
			}else{
				json.put("isLogin", 0);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put("user", user);
			
			//话题一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			//创建的圈子数
			CircleInfoExample circleinfoCount = new CircleInfoExample();
			circleinfoCount.createCriteria().andUseridEqualTo(user.getUserid());
			int circleCount = circleInfoService.countByExample(circleinfoCount);
			json.put("circleCount", circleCount);
			//创建的投票数
			MyVoteInfoExample voteCount = new MyVoteInfoExample();
			voteCount.createCriteria().andUseridEqualTo(user.getUserid());
			int myvoteCount = myVoteInfoService.countByExample(voteCount);
			json.put("myvoteCount", myvoteCount);
			//加入的圈子数
			UserCircleRlsExample addcircle = new UserCircleRlsExample();
			addcircle.createCriteria().andUseridEqualTo(user.getUserid());
			int addcircleCount = userCircleRlsService.countByExample(addcircle);
			json.put("addcircleCount", addcircleCount);
			//加入的圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause("jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(user.getUserid());
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			if(add_circles.size()>0){
				for(UserCircleRls usercircle : add_circles){
					usercircle.setUserinfo(null);
					if(usercircle.getCircleInfo() != null){
						usercircle.getCircleInfo().setUserinfo(null);
					}else{
						userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
					}
				}
			}
			json.put("add_circles", add_circles);
			//创建的圈子
//			CircleInfoExample circleInfoExample = new CircleInfoExample();
//			circleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
//			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
//			circleInfoExample.setRowsPerPage(form.getPageSize());
//			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
//			for (CircleInfo circleInfo : my_create_circle) {
//				circleInfo.setUserinfo(null);
//			}
//			json.put("createCircle", my_create_circle);
			//发布话题
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()-6));
			circleDetailInfoExample.setRowsPerPage(form.getPageSize()-6);
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			for(CircleDetailInfo circledetail : circleDetailInfos){
				circledetail.setUserinfo(null);
				circledetail.setGztopics(null);
				circledetail.setCircleCommentInfo(null);
			}
			json.put("circleDetailInfos", circleDetailInfos);
			//创建的投票
//			MyVoteInfoExample myvoteinfo = new MyVoteInfoExample();
//			myvoteinfo.createCriteria().andUseridEqualTo(user.getUserid());
//			myvoteinfo.setOrderByClause(" createDate desc");
//			myvoteinfo.setLimitStart((form.getPageNum()-1)*(form.getPageSize()-3));
//			myvoteinfo.setRowsPerPage(form.getPageSize()-3);
//			List<MyVoteInfo> voteList = myVoteInfoService.selectByExample(myvoteinfo);
//			for (MyVoteInfo vote : voteList) {
//				vote.setUserinfo(null);
//			}
//			json.put("voteList", voteList);
//			
//			VisitorExample viExample = new VisitorExample();
//			viExample.createCriteria().andUseridEqualTo(user.getUserid());
//			int vCount = visitorService.countByExample(viExample);
//			json.put("vCount", vCount);
//			viExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()-3));
//			viExample.setRowsPerPage(form.getPageSize()-3);
//			viExample.setOrderByClause(" visitTime desc");
//			List<Visitor> viList = visitorService.selectByExample(viExample);
//			json.put("viList", viList);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 查询24个圈子
	 * 10-31
	 */
	public String selectcircle(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			CircleInfoExample circleinfo = new CircleInfoExample();
			circleinfo.setOrderByClause(" comCount desc ");
			circleinfo.setLimitStart(0);
			circleinfo.setRowsPerPage(24);
			List<CircleInfo> circleList = circleInfoService.selectByExample(circleinfo);
			for (CircleInfo circleInfo2 : circleList) {
				circleInfo2.setUserinfo(null);
			}
			json.put("circleList", circleList);
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 查询24个圈子 usercenter_me
	 * 11-1
	 */
	public String selectcircleall(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			CircleInfoExample circleinfo = new CircleInfoExample();
			circleinfo.setOrderByClause(" comCount desc ");
			circleinfo.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleinfo.setRowsPerPage(form.getPageSize());
			List<CircleInfo> circleList = circleInfoService.selectByExample(circleinfo);
			for (CircleInfo circleInfo2 : circleList) {
				circleInfo2.setUserinfo(null);
			}
			json.put("circleList", circleList);
			//加入的圈子
			UserCircleRlsExample usercircle = new UserCircleRlsExample();
			usercircle.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			List<UserCircleRls> addcircle = userCircleRlsService.selectByExample(usercircle);
			for (UserCircleRls userCircleRls : addcircle) {
				userCircleRls.setUserinfo(null);
				userCircleRls.setCircleInfo(null);
			}
			json.put("addcircle", addcircle);
			
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 个人中心批量加入圈子
	 * 10-31
	 */
	public String confimcircle(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			String[] circlearr = form.getCir().split(",");
			for(int i = 0;i<circlearr.length;i++){
				String circleid = circlearr[i];
				UserCircleRlsExample checkRlsExample = new UserCircleRlsExample();
				checkRlsExample.createCriteria().andCircleidEqualTo(circleid).andUseridEqualTo(getCurrentUser().getUserid());
				int checkRlsCount = userCircleRlsService.countByExample(checkRlsExample);
				if(checkRlsCount<=0){
					CircleInfo circleinfo = (CircleInfo) circleInfoService.selectByPrimaryKey(circleid, CircleInfo.class);
					UserCircleRls ucr = new UserCircleRls();
					ucr.setCircleid(circleid);
					ucr.setJoindatetime(new Date());
					ucr.setJtype("1");
					ucr.setUcid(UUID.randomUUID()+"");
					ucr.setUcRole(3);
					ucr.setUserid(getCurrentUser().getUserid());
					userCircleRlsService.insert(ucr);
					circleinfo.setJoincount(circleinfo.getJoincount()+1);
					circleInfoService.updateByPrimaryKey(circleinfo);
					
					Inform inform = new Inform();
					inform.setIfid(UUID.randomUUID()+"");
					inform.setCircleid(circleid);
					inform.setUserid(getCurrentUser().getUserid());
					inform.setTouserid(circleinfo.getUserid());
					inform.setInformtype(1);
					inform.setCreatetime(new Date());
					inform.setIsread(null);
					informService.insert(inform);
					
					UserCircleRlsExample userRlsExample = new UserCircleRlsExample();
					userRlsExample.createCriteria().andCircleidEqualTo(circleid);
					List<UserCircleRls> userRls = userCircleRlsService.selectByExample(userRlsExample);
					for (UserCircleRls userCircleRls : userRls) {
						if(userCircleRls.getUserid().equals(getCurrentUser().getUserid()) ){ continue;}
						Inform inform1 = new Inform();
						inform1.setIfid(UUID.randomUUID()+"");
						inform1.setCircleid(circleid);
						inform1.setUserid(getCurrentUser().getUserid());
						inform1.setTouserid(userCircleRls.getUserid());
						inform1.setInformtype(1);
						inform1.setCreatetime(new Date());
						inform1.setIsread(null);
						informService.insert(inform1);
					}
				}
			}
			json.put("user",getCurrentUser());
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 个人中心二级页面 TA
	 * 11-14
	 */
	public String selectContent(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(Integer.parseInt(form.getId()),UserInfo.class);
			json.put("user", user);
			
			//TA的发言一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			//TA的发言
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			for(CircleDetailInfo circledetail : circleDetailInfos){
				circledetail.setUserinfo(null);
				circledetail.setGztopics(null);
				circledetail.setCircleCommentInfo(null);
			}
			json.put("circleDetailInfos", circleDetailInfos);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 个人中心二级页面ME
	 * 11-14
	 */
	public String selectContentme(){
		try {
			Map<String, Object> json1 = new HashMap<String, Object>();
			UserInfo user = getCurrentUser();
			
			if(user==null){
				json1.put("user", null);
				form.setJsonMsg("success", true, json1);
				return JSON_PAGE;
			}else{
			json1.put("user", user);}
			
			//我的发言一共有多少
//			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
//			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
//			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
//			json1.put("count", count);
			//我创建的圈子数
//			CircleInfoExample circleinfoCount = new CircleInfoExample();
//			circleinfoCount.createCriteria().andUseridEqualTo(user.getUserid());
//			int circleCount = circleInfoService.countByExample(circleinfoCount);
//			json1.put("circleCount", circleCount);
			//我创建的投票数
//			MyVoteInfoExample voteCount = new MyVoteInfoExample();
//			voteCount.createCriteria().andUseridEqualTo(user.getUserid());
//			int myvoteCount = myVoteInfoService.countByExample(voteCount);
//			json1.put("myvoteCount", myvoteCount);
			//我加入的圈子数
//			UserCircleRlsExample addcircle = new UserCircleRlsExample();
//			addcircle.createCriteria().andUseridEqualTo(user.getUserid());
//			int addcircleCount = userCircleRlsService.countByExample(addcircle);
//			json1.put("addcircleCount", addcircleCount);
			//我加入的圈子
//			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
//			userCircleRlsExample.setOrderByClause("jtype desc");
//			userCircleRlsExample.createCriteria().andUseridEqualTo(user.getUserid());
//			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
//			userCircleRlsExample.setRowsPerPage(form.getPageSize());
//			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
//			for(UserCircleRls usercircle : add_circles){
//				usercircle.setUserinfo(null);
//				if(usercircle.getCircleInfo() != null){
//					usercircle.getCircleInfo().setUserinfo(null);
//				}else{
//					userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
//				}
//			}
//			json1.put("add_circles", add_circles);
			//我创建的圈子
//			CircleInfoExample circleInfoExample = new CircleInfoExample();
//			circleInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
//			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
//			circleInfoExample.setRowsPerPage(form.getPageSize());
//			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
//			for (CircleInfo circleInfo : my_create_circle) {
//				circleInfo.setUserinfo(null);
//			}
//			json1.put("createCircle", my_create_circle);
			//我的发言
//			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
//			circleDetailInfoExample.createCriteria().andUseridEqualTo(user.getUserid());
//			circleDetailInfoExample.setOrderByClause("createdatetime desc");
//			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
//			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
//			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
//			for(CircleDetailInfo circledetail : circleDetailInfos){
//				circledetail.setUserinfo(null);
//				circledetail.setGztopics(null);
//				circledetail.setCircleCommentInfo(null);
//			}
//			json1.put("circleDetailInfos", circleDetailInfos);
			//我创建的投票
//			MyVoteInfoExample myvoteinfo = new MyVoteInfoExample();
//			myvoteinfo.createCriteria().andUseridEqualTo(user.getUserid());
//			myvoteinfo.setOrderByClause(" createDate desc");
//			myvoteinfo.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
//			myvoteinfo.setRowsPerPage(form.getPageSize());
//			List<MyVoteInfo> voteList = myVoteInfoService.selectByExample(myvoteinfo);
//			for (MyVoteInfo vote : voteList) {
//				vote.setUserinfo(null);
//			}
//			json1.put("voteList", voteList);
			
			//访客
			VisitorExample viExample = new VisitorExample();
			viExample.createCriteria().andUseridEqualTo(user.getUserid());
			int vCount = visitorService.countByExample(viExample);
			json1.put("vCount", vCount);
			viExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()-3));
			viExample.setRowsPerPage(form.getPageSize()-3);
			viExample.setOrderByClause(" visitTime desc");
			List<Visitor> viList = visitorService.selectByExample(viExample);
			json1.put("viList", viList);
			//我的最新动态
			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(user.getUserid());
			int informCount = informService.countByExample(informExample);
			json1.put("informCount", informCount);
			
			informExample.setOrderByClause(" isRead ,createTime desc");
			informExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			informExample.setRowsPerPage(form.getPageSize());
			List<Inform> informList = informService.selectByExample(informExample);
			json1.put("informList", informList);
//			Inform in = new Inform();
//			in.setIsread(1);
//			informService.updateByExampleSelective(in, informExample);

			form.setJsonMsg("success", true, json1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 发言分页查询 TA
	 * 11-15
	 */
	public String gettopicnext(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA的发言一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			//TA的发言
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			for(CircleDetailInfo circledetail : circleDetailInfos){
				circledetail.setUserinfo(null);
				circledetail.setGztopics(null);
				circledetail.setCircleCommentInfo(null);
			}
			json.put("circleDetailInfos", circleDetailInfos);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
		
	}
	/**
	 * 发言分页查询ME
	 * 11-15
	 */
	public String gettopicnextme(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA的发言一共有多少
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			int count = circleDetailInfoService.countByExample(countCircleDetailInfoExample);
			json.put("count", count);
			//TA的发言
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			circleDetailInfoExample.setOrderByClause("createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos =  circleDetailInfoService.selectByExample(circleDetailInfoExample);
			for(CircleDetailInfo circledetail : circleDetailInfos){
				circledetail.setUserinfo(null);
				circledetail.setGztopics(null);
				circledetail.setCircleCommentInfo(null);
			}
			json.put("circleDetailInfos", circleDetailInfos);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 我的访客分页
	 * 2013-3-4
	 */
	public String getvisitornextme(){
		try{
			Map<String, Object> json = new HashMap<String, Object>();
			VisitorExample viExample = new VisitorExample();
			viExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			int vCount = visitorService.countByExample(viExample);
			json.put("vCount", vCount);
			
			viExample.setOrderByClause(" visitTime desc");
			viExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			viExample.setRowsPerPage(form.getPageSize());
			List<Visitor> viList = visitorService.selectByExample(viExample);
			json.put("viList", viList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他的投票分页TA
	 * 11-15
	 */
	public String getvotenext(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA创建的投票数
			MyVoteInfoExample voteCount = new MyVoteInfoExample();
			voteCount.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
			int myvoteCount = myVoteInfoService.countByExample(voteCount);
			json.put("myvoteCount", myvoteCount);
			// TA 创建的投票
			MyVoteInfoExample myvoteinfo = new MyVoteInfoExample();
			myvoteinfo.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
			myvoteinfo.setOrderByClause(" createDate desc");
			myvoteinfo.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			myvoteinfo.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> voteList = myVoteInfoService.selectByExample(myvoteinfo);
			for (MyVoteInfo vote : voteList) {
				vote.setUserinfo(null);
			}
			json.put("voteList", voteList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 投票分页ME
	 * 11-15
	 */
	public String getvotenextme(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA创建的投票数
			MyVoteInfoExample voteCount = new MyVoteInfoExample();
			voteCount.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			int myvoteCount = myVoteInfoService.countByExample(voteCount);
			json.put("myvoteCount", myvoteCount);
			// TA 创建的投票
			MyVoteInfoExample myvoteinfo = new MyVoteInfoExample();
			myvoteinfo.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			myvoteinfo.setOrderByClause(" createDate desc");
			myvoteinfo.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			myvoteinfo.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> voteList = myVoteInfoService.selectByExample(myvoteinfo);
			for (MyVoteInfo vote : voteList) {
				vote.setUserinfo(null);
			}
			json.put("voteList", voteList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他的加入的圈子分页 TA
	 * 11-15
	 */
	public String getcirclenext(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();	
			//ta加入的圈子数
			UserCircleRlsExample addcircle = new UserCircleRlsExample();
			addcircle.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int addcircleCount = userCircleRlsService.countByExample(addcircle);
			json.put("addcircleCount", addcircleCount);
			//ta加入的圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause("jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			for(UserCircleRls usercircle : add_circles){
				usercircle.setUserinfo(null);
				if(usercircle.getCircleInfo() != null){
					usercircle.getCircleInfo().setUserinfo(null);
				}else{
					userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
				}
			}
			json.put("add_circles", add_circles);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 加入的圈子分页 ME
	 * 11-15
	 */
	public String getcirclenextme(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();	
			//ta加入的圈子数
			UserCircleRlsExample addcircle = new UserCircleRlsExample();
			addcircle.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			int addcircleCount = userCircleRlsService.countByExample(addcircle);
			json.put("addcircleCount", addcircleCount);
			//ta加入的圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.setOrderByClause("jtype desc");
			userCircleRlsExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			userCircleRlsExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> add_circles =userCircleRlsService.selectByExample(userCircleRlsExample);
			for(UserCircleRls usercircle : add_circles){
				usercircle.setUserinfo(null);
				if(usercircle.getCircleInfo() != null){
					usercircle.getCircleInfo().setUserinfo(null);
				}else{
					userCircleRlsService.deleteByPrimaryKey(usercircle.getUcid(),UserCircleRls.class);
				}
			}
			json.put("add_circles", add_circles);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 他的创建的圈子分页TA
	 * 11-15
	 */
	public String getcreatenext(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA创建的圈子数
			CircleInfoExample circleinfoCount = new CircleInfoExample();
			circleinfoCount.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int circleCount = circleInfoService.countByExample(circleinfoCount);
			json.put("circleCount", circleCount);
			//TA创建的圈子
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
			for (CircleInfo circleInfo : my_create_circle) {
				circleInfo.setUserinfo(null);
			}
			json.put("createCircle", my_create_circle);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;	
	}
	/**
	 * 创建的圈子分页ME
	 * 11-15
	 */
	public String getcreatenextme(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			//TA创建的圈子数
			CircleInfoExample circleinfoCount = new CircleInfoExample();
			circleinfoCount.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			int circleCount = circleInfoService.countByExample(circleinfoCount);
			json.put("circleCount", circleCount);
			//TA创建的圈子
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.createCriteria().andUseridEqualTo(getCurrentUser().getUserid());
			circleInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			circleInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleInfo> my_create_circle = circleInfoService.selectByExample(circleInfoExample);
			for (CircleInfo circleInfo : my_create_circle) {
				circleInfo.setUserinfo(null);
			}
			json.put("createCircle", my_create_circle);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;	
	}
	/**
	 * 通知分页ME
	 * 11-26
	 */
	public String getnoticenextme(){
		try{
			Map<String, Object> json = new HashMap<String, Object>();
			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid());
			int informCount = informService.countByExample(informExample);
			json.put("informCount", informCount);
			
			informExample.setOrderByClause(" isRead ,createTime desc");
			informExample.setLimitStart((form.getPageNum()-1)*(form.getPageSize()));
			informExample.setRowsPerPage(form.getPageSize());
			List<Inform> informList = informService.selectByExample(informExample);
			json.put("informList", informList);
//			for (Inform inform : informList) {
//				
//			}
			Inform in = new Inform();
			in.setIsread(1);
			informService.updateByExampleSelective(in, informExample);
			form.setJsonMsg("success", true, json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;	
	}
	public String uploadphoto()
	{
		try {
			if(getCurrentUser()==null)
			{
				return "index";
			}
			
			if(getCurrentUser().getImage()!=null && getCurrentUser().getImage().length()>1)
			{
				return "index";
			}
			UserInfo user = getCurrentUser();
			if(form.getId()!=null && form.getId().equals("uploadimg"))
			{
				
			}else{
				user.setUserimage("/uploadPath/user/photo_big.jpg");
				setCurrentUser(user);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "uploadphoto";
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}
}