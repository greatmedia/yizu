package com.yizu.proj.sys.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.JsonInfoForm;
import com.yizu.proj.sys.beans.CiecleDetailImg;
import com.yizu.proj.sys.beans.CiecleDetailImgExample;
import com.yizu.proj.sys.beans.CircleCommentInfo;
import com.yizu.proj.sys.beans.CircleCommentInfoExample;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.CircleInfoExample;
import com.yizu.proj.sys.beans.Circlerecommend;
import com.yizu.proj.sys.beans.CirclerecommendExample;
import com.yizu.proj.sys.beans.Circletagrelate;
import com.yizu.proj.sys.beans.CircletagrelateExample;
import com.yizu.proj.sys.beans.Circletalkabout;
import com.yizu.proj.sys.beans.CircletalkaboutExample;
import com.yizu.proj.sys.beans.Inform;
import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserCircleRlsExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.Usertalk;
import com.yizu.proj.sys.beans.UsertalkExample;
import com.yizu.proj.sys.service.CiecleDetailImgService;
import com.yizu.proj.sys.service.CircleCommentInfoService;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.CircleInfoService;
import com.yizu.proj.sys.service.CirclerecommendService;
import com.yizu.proj.sys.service.CircletagrelateService;
import com.yizu.proj.sys.service.CircletalkaboutService;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.UserCircleRlsService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.UsertalkService;
import com.yizu.proj.utils.ReplaceContent;

@Scope("prototype")
@Controller("circleDetailInfoAction")
public class CircleDetailInfoAction extends BaseAction implements ModelDriven {
	private JsonInfoForm form = new JsonInfoForm();
	@Autowired
	private CircleDetailInfoService circleDetailInfoService;
	@Autowired
	private CircleInfoService circleInfoService;
	@Autowired
	private UserCircleRlsService userCircleRlsService;
	@Autowired
	private CiecleDetailImgService ciecleDetailImgService;
	@Autowired
	private CircleCommentInfoService circleCommentInfoService;
	@Autowired 
	private CircletalkaboutService circletalkaboutService;
	@Autowired 
	private InformService informService;
	@Autowired 
	private UserInfoService userInfoService;
	@Autowired
	private UsertalkService usertalkService;
	@Autowired
	private CircletagrelateService circletagrelateService;
    @Autowired
    private CirclerecommendService circlerecommendService;
	// 根据圈子ID查询所有的详细信息
	public String circleDetail() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();

			System.out.println("圈子详细");
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
					.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();

			// 所有的话题
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			if(!form.getPid().equals("0")){
				circleDetailInfoExample.createCriteria().andCircleidEqualTo(form.getId()).andDef1IsNull().andDef4EqualTo(form.getPid());
			}else{
				circleDetailInfoExample.createCriteria().andCircleidEqualTo(form.getId()).andDef1IsNull();
			}
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum() - 1)
					* form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos = circleDetailInfoService
					.selectByExample(circleDetailInfoExample);
			for (CircleDetailInfo circleDetailInfo : circleDetailInfos) {
				if(circleDetailInfo.getCirclecontent().indexOf("embed")<0){
					String s = ReplaceContent.replaceHtmlFromContent(circleDetailInfo.getCirclecontent());
					circleDetailInfo.setCirclecontent(s);
				}
			}
			// 话题总数
			CircleDetailInfoExample countCircleDetailInfoExample = new CircleDetailInfoExample();
			countCircleDetailInfoExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andDef1IsNull();
			int count = circleDetailInfoService
					.countByExample(countCircleDetailInfoExample);
			//该圈子所属标签
			CircletagrelateExample tagrelateExample = new CircletagrelateExample();
			tagrelateExample.createCriteria().andCircleidEqualTo(form.getId());
			tagrelateExample.setOrderByClause(" ctid ");
			List<Circletagrelate> tagrelate = circletagrelateService.selectByExample(tagrelateExample);

			json.put("tagrelate",tagrelate);
			json.put("countDetail", count);
			json.put("circleDetailInfos", circleDetailInfos);
			json.put("center_circleInfo", center_circleInfo);
			form.setPageNum(form.getPageNum());
//			form.setCircleDetailInfos(circleDetailInfos);
			if (user == null) {
				System.out.println("没有登录");
				json.put("islogin", "not");
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 圈子下的内容更换标签
	 * 2013-04-22
	 * @return
	 */
	public String changecircletag(){
		try{
			CircleDetailInfo circleDetailInfo = (CircleDetailInfo)baseService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
			if (circleDetailInfo == null) {
				form.setJsonMsg("亲，这个圈子详细已经被删除不能修改，请刷新后重试！", false, null);
			    return JSON_PAGE;
			}
			circleDetailInfo.setDef4(form.getPid());
			baseService.updateByPrimaryKeySelective(circleDetailInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String showRightDetail(){
		try{
			System.out.println(form.getId()+"  "+form.getPageNum()+"  "+form.getPageSize());
			if(form.getPageNum()<=0){
				return JSON_PAGE;
			}
			UserInfo user = getCurrentUser();
//			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(100014, UserInfo.class); 
//			setCurrentUser(user);
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
				.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();
			form.setCircleleft(center_circleInfo);
			if (user == null) {
				System.out.println("没有登录");
				form.setIslogin("not");
			}
			if (user != null) {
				// 是否已经加入该圈子
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
					.andUseridEqualTo(user.getUserid());
				int isAddCircle = 0;
				isAddCircle = userCircleRlsService.countByExample(ucrexample);
				form.setIslogin("have");
				form.setIsAddCircle(isAddCircle);
			}
			form.setUser(user);
			// 最新加入圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.createCriteria()
				.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(0);
			userCircleRlsExample.setRowsPerPage(12);
			userCircleRlsExample.setOrderByClause("joindatetime desc");

			List<UserCircleRls> userCircleRlss = userCircleRlsService
				.selectByExample(userCircleRlsExample);
			form.setUserCircleRlss(userCircleRlss);
			
			 //全职成员
			userCircleRlsExample.clear();
			userCircleRlsExample.createCriteria().andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(12);
			userCircleRlsExample.setRowsPerPage(10);
			userCircleRlsExample.setOrderByClause(" joindatetime desc");
			List<UserCircleRls> circleAlluser = userCircleRlsService.selectByExample(userCircleRlsExample);
			form.setCircleAlluser(circleAlluser);
			
			UserCircleRlsExample countCircleRlsExample = new UserCircleRlsExample();
			countCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			int count = userCircleRlsService.countByExample(countCircleRlsExample);
			form.setCount(count);
			
			//该圈子所属标签
			CircletagrelateExample tagrelateExample = new CircletagrelateExample();
			tagrelateExample.createCriteria().andCircleidEqualTo(form.getId());
			tagrelateExample.setOrderByClause(" def1 ");
			List<Circletagrelate> tagrelate = circletagrelateService.selectByExample(tagrelateExample);
			form.setCircletagrelate(tagrelate);
			
			//获得右边该圈子下的所有话题 分页 1  10
			// 所有的话题
//			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
//			circleDetailInfoExample.createCriteria()
//					.andCircleidEqualTo(form.getId()).andDef1IsNull();
//			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
//			circleDetailInfoExample.setLimitStart((form.getPageNum() - 1)
//					* form.getPageSize());
//			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
//			List<CircleDetailInfo> circleDetailInfos = circleDetailInfoService
//					.selectByExample(circleDetailInfoExample);
//			for (CircleDetailInfo circleDetailInfo : circleDetailInfos) {
//				if(circleDetailInfo.getCirclecontent().indexOf("embed")<0){
//					String s = ReplaceContent.replaceHtmlFromContent(circleDetailInfo.getCirclecontent());
//					circleDetailInfo.setCirclecontent(s);
//				}
//			}
//			form.setCircleDetailInfos(circleDetailInfos);
			//热点话题。
			CircletalkaboutExample talkabout = new CircletalkaboutExample();
			talkabout.createCriteria().andCircleidEqualTo(form.getId());
			talkabout.setOrderByClause("updatetime desc");
			List<Circletalkabout> circletalkabout = circletalkaboutService.selectByExample(talkabout);
			if(circletalkabout.size()>0){
				form.setTalkabout(circletalkabout.get(0));
			}else{
				form.setTalkabout(null);
			}
			//推荐圈子
			CirclerecommendExample circlerecommendExample = new CirclerecommendExample();
            circlerecommendExample.createCriteria().andCircleidEqualTo(form.getId());
            List<Circlerecommend> circlerecommend = circlerecommendService.selectByExample(circlerecommendExample);
            form.setCirclerecommend(circlerecommend);
            
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	//圈子即时聊天查询
	public String showtalk(){
		try{
			System.out.println(form.getId()+"  "+form.getUid()+"  "+form.getPid());
			Map<String, Object> json = new HashMap<String, Object>();
			UsertalkExample talkExample = new UsertalkExample();
			talkExample.createCriteria().andCircleidEqualTo(form.getId()).andIdGreaterThan(form.getUid()).andDef1EqualTo(form.getPid());
			talkExample.setOrderByClause(" talktime desc");
//			talkExample.setLimitStart((form.getPageNum() - 1)
//					* form.getPageSize());
//			talkExample.setRowsPerPage(form.getPageSize());
			talkExample.setLimitStart(0);
			talkExample.setRowsPerPage(20);
			List<Usertalk> usertalk = usertalkService.selectByExample(talkExample);
			json.put("usertalk", usertalk);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//圈子即时聊天功能保存对话
	public String savetalk(){
		try{
			System.out.println(form.getId()+"  "+form.getContent()+"  "+form.getPid());
			Map<String, Object> json = new HashMap<String, Object>();
			Usertalk usertalk = new Usertalk();
			usertalk.setCircleid(form.getId());
			usertalk.setUserid(getCurrentUser().getUserid());
			usertalk.setTalktime(new Date());
			usertalk.setContent(form.getContent());
			usertalk.setDef1(form.getPid());
			int result = usertalkService.insert(usertalk);
			UserInfo user = getCurrentUser();
			json.put("user", user);
			if(result == 1){
				form.setJsonMsg("success", true, json);
			}else{
				form.setJsonMsg("fail", false, 0);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
		
	}
	public String changetalkabout(){
		try{
			Map<String, Object> json = new HashMap<String, Object>();
			Circletalkabout circleTalkAbout = new Circletalkabout();
			circleTalkAbout.setId(String.valueOf(UUID.randomUUID()));
			circleTalkAbout.setCircleid(form.getId());
			circleTalkAbout.setUpdatetime(new Date());
			circleTalkAbout.setTalktitle(form.getContent());
			circletalkaboutService.insert(circleTalkAbout);
			Circletalkabout talkabout = (Circletalkabout) circletalkaboutService.selectByPrimaryKey(circleTalkAbout.getId(), Circletalkabout.class);
			json.put("talkabout", talkabout);
			
//			CircletalkaboutExample talk = new CircletalkaboutExample();
//			talk.createCriteria().andCircleidEqualTo(form.getId());
//			List<Circletalkabout> talkabout = circletalkaboutService.selectByExample(talk);
//			if(talkabout.size()>0){
//				Circletalkabout circleTalkAbout = talkabout.get(0);
//				circleTalkAbout.setUpdatetime(new Date());
//				circleTalkAbout.setTalktitle(form.getContent());
//				circletalkaboutService.updateByExample(circleTalkAbout, talk);
//			}else{
				
//			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String circleVisition() {
		// 圈子访问数量
		try {
			CircleInfo circleInfo = (CircleInfo) circleInfoService
					.selectByPrimaryKey(form.getId(), CircleInfo.class);
			System.out.println(circleInfo.getVisitcount());
			circleInfo.setVisitcount((circleInfo.getVisitcount()) + 1);
			circleDetailInfoService.updateByPrimaryKey(circleInfo);
			Map<String, Object> json = new HashMap<String, Object>();
			System.out.println(circleInfo.getVisitcount());
			json.put("res", circleInfo.getVisitcount());
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	public String getTopicImg() {
		// 某个话题的所有的图片信息
		System.out.println("某个话题的所有的图片");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			String idString = form.getId();
			System.out.println("话题ID===图片===" + idString);
			CiecleDetailImgExample ciecleDetailImgExample = new CiecleDetailImgExample();
			ciecleDetailImgExample.createCriteria().andCircledetailidEqualTo(
					form.getId());
			List<CiecleDetailImg> ciecleDetailImgs = ciecleDetailImgService
					.selectByExample(ciecleDetailImgExample);
			json.put("ciecleDetailImgs", ciecleDetailImgs);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 某个话题的所有的评论信息
	public String getTopicComment() {
		System.out.println("某个话题的所有的评论信息");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			String idString = form.getId();
			System.out.println("话题ID===评论===" + idString);
			System.out.println("getPageNum=" + form.getPageNum()
					+ ";getPageSize=" + form.getPageSize());
			CircleCommentInfoExample circleCommentInfoExample = new CircleCommentInfoExample();
			circleCommentInfoExample.createCriteria().andCircledetailidEqualTo(
					form.getId());
			circleCommentInfoExample.setOrderByClause("createdate desc");
			circleCommentInfoExample.setLimitStart((form.getPageNum() - 1)
					* form.getPageSize());
			circleCommentInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleCommentInfo> circleCommentInfos = circleCommentInfoService
					.selectByExample(circleCommentInfoExample);

			CircleCommentInfoExample countEsxample = new CircleCommentInfoExample();
			countEsxample.setLimitStart((form.getPageNum() - 1)* form.getPageSize());
			countEsxample.setRowsPerPage(form.getPageSize());
			countEsxample.createCriteria().andCircledetailidEqualTo(
					form.getId());
			int commentCountById = circleCommentInfoService
					.countByExample(countEsxample);

			json.put("commentCountById", commentCountById);
			json.put("circleCommentInfos", circleCommentInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 一个圈子的所有的话题数量
	public int detailCount() {
		int detailCount = 0;
		try {
			CircleDetailInfoExample detailExampleCount = new CircleDetailInfoExample();
			detailExampleCount.createCriteria()
					.andCircleidEqualTo(form.getId());
			detailCount = circleDetailInfoService
					.countByExample(detailExampleCount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return detailCount;
	}

	// 统计某一个话题的所有的评论数量
	public int commentCount() {
		Map<String, Object> json = new HashMap<String, Object>();
		int commentCount = 0;
		try {
			CircleCommentInfoExample commentExampleCount = new CircleCommentInfoExample();
			commentExampleCount.createCriteria().andCcidEqualTo(form.getId());
			circleCommentInfoService.countByExample(commentExampleCount);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return commentCount;
	}

	// 评论话题
	public String insertComment() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			CircleCommentInfo circleCommentInfo = new CircleCommentInfo();
			circleCommentInfo.setCommentinfo(form.getContent());
			circleCommentInfo.setCircledetailid(form.getId());
			circleCommentInfo.setCcid(String.valueOf(UUID.randomUUID()));
			circleCommentInfo.setUserid(user.getUserid());
			circleCommentInfo.setCreatedate(new Date());
			int result = circleCommentInfoService.insert(circleCommentInfo);
//			CircleDetailInfoExample detailExample = new CircleDetailInfoExample();
//			detailExample.createCriteria().andCircledetailidEqualTo(form.getId());
			
			CircleDetailInfo circledetail = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
			circledetail.setComcount(circledetail.getComcount()+1);
			circleDetailInfoService.updateByPrimaryKeySelective(circledetail);
			//推送给发布者通知
			Inform inform =new Inform();
			inform.setIfid(String.valueOf(UUID.randomUUID()));
			inform.setUserid(user.getUserid());
			inform.setTouserid(form.getUserid());
			inform.setCircleid(circledetail.getCircleid());
			inform.setCircledetailid(form.getId());
			inform.setCircledetailtype(1);
			inform.setCreatetime(new Date());
			inform.setInformcontent(form.getContent());
			inform.setInformtype(2);
			inform.setIsread(null);
			informService.insert(inform);
			if (result > 0) {
				System.err.println("评论成功" + result);
				json.put("result", result);
				form.setJsonMsg("success", true, json);
			} else {
				System.err.println("评论失败" + result);
				json.put("result", result);
				form.setJsonMsg("error", true, json);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 热门3个圈子
	public String hotCircleTop3() {
		System.out.println("热门3个圈子");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			circleInfoExample.setOrderByClause("comcount desc");
			circleInfoExample.setLimitStart(0);
			circleInfoExample.setRowsPerPage(3);
			circleInfoExample.createCriteria();
			List<CircleInfo> hotcCircleInfos = circleDetailInfoService
					.selectByExample(circleInfoExample);
			json.put("hotcCircleInfos", hotcCircleInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 最新加入圈子的成员
	public String newAddMember() {
		System.out.println("最新加入的成员");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(0);
			userCircleRlsExample.setRowsPerPage(5);
			userCircleRlsExample.setOrderByClause("joindatetime desc");

			List<UserCircleRls> userCircleRlss = userCircleRlsService
					.selectByExample(userCircleRlsExample);
			json.put("userCircleRlss", userCircleRlss);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 某一个圈子的所有的话题
	public String getDetails() {
		System.out.println("某一个圈子的所有的话题");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andCircleidEqualTo(
					form.getId());
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum() - 1)
					* form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos = circleDetailInfoService
					.selectByExample(circleDetailInfoExample);
			json.put("circleDetailInfos", circleDetailInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 某一个圈子的单个话题信息
	public String getDetail() {
//		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
					.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();
			form.setCircleleft(center_circleInfo);
			if (user == null) {
				System.out.println("没有登录");
				form.setIslogin("not");
			}
			if (user != null) {
				// 是否已经加入该圈子
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
						.andUseridEqualTo(user.getUserid());
				int isAddCircle = 0;
				isAddCircle = userCircleRlsService.countByExample(ucrexample);
				System.out.println("已经登录===是否已经加入该圈子" + isAddCircle);
				form.setIslogin("have");
				form.setIsAddCircle(isAddCircle);
			}
			form.setUser(user);
			// 最新加入圈子
//			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
//			userCircleRlsExample.createCriteria()
//					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
//			userCircleRlsExample.setLimitStart(0);
//			userCircleRlsExample.setRowsPerPage(5);
//			userCircleRlsExample.setOrderByClause("joindatetime desc");
//			List<UserCircleRls> userCircleRlss = userCircleRlsService
//					.selectByExample(userCircleRlsExample);
//			form.setUserCircleRlss(userCircleRlss);
			
//			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
//			circleDetailInfoExample.createCriteria().andCircledetailidEqualTo(
//					form.getPid());
//			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService
					.selectByPrimaryKey(form.getPid(), CircleDetailInfo.class);
			form.setCircleDetailInfo(circleDetailInfo);
			// 推荐阅读
			CircleDetailInfoExample recommendDetail = new CircleDetailInfoExample();
			recommendDetail.createCriteria().andCircleidEqualTo(form.getId()).andDef1IsNull();
			recommendDetail.setLimitStart(0);
			recommendDetail.setRowsPerPage(8);
			recommendDetail.setOrderByClause(" createdatetime desc");
			List<CircleDetailInfo> recommend = circleDetailInfoService.selectByExample(recommendDetail);
			form.setCircleDetailInfos(recommend);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "circledetail";
	}
	//圈子标签下的内容
	public String circletag(){
		try{
			UserInfo user = getCurrentUser();
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
					.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();
			form.setCircleleft(center_circleInfo);
			if (user == null) {
				System.out.println("没有登录");
				form.setIslogin("not");
			}
			if (user != null) {
				// 是否已经加入该圈子
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
						.andUseridEqualTo(user.getUserid());
				int isAddCircle = 0;
				isAddCircle = userCircleRlsService.countByExample(ucrexample);
				System.out.println("已经登录===是否已经加入该圈子" + isAddCircle);
				form.setIslogin("have");
				form.setIsAddCircle(isAddCircle);
			}
			form.setUser(user);
			// 最新加入圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(0);
			userCircleRlsExample.setRowsPerPage(5);
			userCircleRlsExample.setOrderByClause("joindatetime desc");
			List<UserCircleRls> userCircleRlss = userCircleRlsService
					.selectByExample(userCircleRlsExample);
			form.setUserCircleRlss(userCircleRlss);
			
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			if(form.getPid().equals("0")){
				circleDetailInfoExample.createCriteria().andCircleidEqualTo(form.getId()).andDef1IsNull();
			}else{
				circleDetailInfoExample.createCriteria().andCircleidEqualTo(form.getId()).andDef1IsNull().andDef4EqualTo(form.getPid());
			}
			int count =  circleDetailInfoService.countByExample(circleDetailInfoExample);
			form.setHot(count);
			circleDetailInfoExample.setOrderByClause(" createdatetime desc");
			circleDetailInfoExample.setLimitStart((form.getPageNum() - 1)
					* form.getPageSize());
			circleDetailInfoExample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> circleDetailInfos = circleDetailInfoService
					.selectByExample(circleDetailInfoExample);
			for (CircleDetailInfo circleDetailInfo : circleDetailInfos) {
				if(circleDetailInfo.getCirclecontent().indexOf("embed")<0){
					String s = ReplaceContent.replaceHtmlFromContent(circleDetailInfo.getCirclecontent());
					circleDetailInfo.setCirclecontent(s);
				}
			}
			//该圈子所属标签
			CircletagrelateExample tagrelateExample = new CircletagrelateExample();
			tagrelateExample.createCriteria().andCircleidEqualTo(form.getId());
			tagrelateExample.setOrderByClause(" def1 ");
			List<Circletagrelate> tagrelate = circletagrelateService.selectByExample(tagrelateExample);

			form.setCircletagrelate(tagrelate);
			form.setCircleDetailInfos(circleDetailInfos);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "circletag";
	}

	// 某一个圈子的信息
	public String selectCircle() {
		try {
			UserInfo user = getCurrentUser();
			System.out.println("圈子详细");
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
					.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();
			form.setCircleleft(center_circleInfo);
//			json.put("center_circleInfo", center_circleInfo);
			if (user == null) {
				System.out.println("没有登录");
				form.setIslogin("not");
//				json.put("islogin", "not");
			}
			if (user != null) {
				// 是否已经加入该圈子
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
						.andUseridEqualTo(user.getUserid());
				int isAddCircle = 0;
				isAddCircle = userCircleRlsService.countByExample(ucrexample);
				System.out.println("已经登录===是否已经加入该圈子" + isAddCircle);
				form.setIslogin("have");
				form.setIsAddCircle(isAddCircle);
				
//				json.put("islogin", "have");
//				json.put("isAddCircle", isAddCircle);
//				json.put("user", user);
			}
			form.setUser(user);
			// 最新加入圈子
			CircletagrelateExample tagrelateExample = new CircletagrelateExample();
			tagrelateExample.createCriteria().andCircleidEqualTo(form.getId());
			tagrelateExample.setOrderByClause(" def1 ");
			List<Circletagrelate> tagrelate = circletagrelateService.selectByExample(tagrelateExample);
			form.setCircletagrelate(tagrelate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "circledetail";
	}

	// 话题详细
	public String topicDetais() {
		System.out.println("detais");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample detailE = new CircleDetailInfoExample();
			System.out.println("话题ID：" + form.getId());
			detailE.createCriteria().andCircleidEqualTo(form.getId());
			CircleDetailInfo topicDetail = (CircleDetailInfo) circleDetailInfoService
					.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
			json.put("topicDetail", topicDetail);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 统计发言 和成员
	public String count() {
		System.out.println("detais  统计发言  评论加话题");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample detailE = new CircleDetailInfoExample();
			detailE.createCriteria().andCircleidEqualTo(form.getId());
			System.out.println("圈子ID====" + form.getId());
			int count_details = circleCommentInfoService
					.countByExample(detailE);
			System.out.println("发言数量：=====" + count_details);

			UserCircleRlsExample userRlsExample = new UserCircleRlsExample();
			userRlsExample.createCriteria().andCircleidEqualTo(form.getId());
			int count_per = userCircleRlsService.countByExample(userRlsExample);
			System.out.println("成员数量：=====" + count_per);
			json.put("count_per", count_per);
			json.put("count_details", count_details);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 统计 评论
	public String countComment() {
		System.out.println("detais  统计发言  评论加话题");
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleCommentInfoExample circleCommentInfoExample = new CircleCommentInfoExample();
			circleCommentInfoExample.createCriteria().andCircledetailidEqualTo(
					form.getId());
			int count_comment = circleCommentInfoService
					.countByExample(circleCommentInfoExample);
			json.put("count_comment", count_comment);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 得到加入圈子的所有的用户
	public String getCircleAllUser() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();

			userCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart((form.getPageNum() - 1)
					* form.getPageSize());
			userCircleRlsExample.setRowsPerPage(form.getPageSize());
			userCircleRlsExample.setOrderByClause(" joindatetime desc");
			List<UserCircleRls> circleAlluser = userCircleRlsService
					.selectByExample(userCircleRlsExample);
			System.out.println(circleAlluser.size());

			UserCircleRlsExample countCircleRlsExample = new UserCircleRlsExample();
			countCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			int count = userCircleRlsService
					.countByExample(countCircleRlsExample);
			json.put("count", count);
			json.put("circleAlluser", circleAlluser);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 删除话题
	public String deleteDid() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			String did = form.getId();
			boolean islogin = false;
			System.out.println("要删除的话题ID" + did);
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
			json.put("islogin", islogin);
			/**判断话题是否存在*/
			CircleDetailInfo circleDetailInfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(did, CircleDetailInfo.class);
			if (circleDetailInfo == null) 
			{
				result ="该话题已经不存在了！";
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			/**判断话题是否是本人创建的*/
			if (!circleDetailInfo.getUserid().equals(user.getUserid())) 
			{
				result ="该话题不是你创建的，不能删除！"+circleDetailInfo.getUserid()+"__equals__"+user.getUserid();
				json.put("result", result);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			// 删除话题
			CircleDetailInfoExample circcleCircleDetailInfoExample = new CircleDetailInfoExample();
			circcleCircleDetailInfoExample.createCriteria();
			int resultDetail = circleDetailInfoService.deleteByPrimaryKey(did, CircleDetailInfo.class);
			if (resultDetail > 0) {
				// 删除话题的图片
				CiecleDetailImgExample cirCiecleDetailImgExample = new CiecleDetailImgExample();
				cirCiecleDetailImgExample.createCriteria().andCircledetailidEqualTo(did).andCdidEqualTo(form.getIds());
				ciecleDetailImgService.deleteByExample(cirCiecleDetailImgExample);
				// 删除话题的评论
				CircleCommentInfoExample circleCommentInfoExample = new CircleCommentInfoExample();
				circleCommentInfoExample.createCriteria().andCircledetailidEqualTo(did);
				circleCommentInfoService.deleteByExample(circleCommentInfoExample);

				// 更新圈子发言数量
				System.out.println("要更新的圈子ID" + form.getIds());
				CircleInfo circleInfo = (CircleInfo) circleInfoService.selectByPrimaryKey(form.getIds(),CircleInfo.class);
				int count = circleInfo.getComcount() - 1;
				System.out.println(count);
				circleInfo.setComcount(count);
				System.out.println(circleInfo.getComcount());
			    circleDetailInfoService.updateByPrimaryKey(circleInfo);
			} 
			if(resultDetail>0)
			{
				json.put("result", resultDetail);
			}else{
				json.put("result", 0);
			}
			form.setJsonMsg("success", true, json);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String circletalkmore(){
		try {
			UserInfo user = getCurrentUser();
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
				.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();
			form.setCircleleft(center_circleInfo);
			if (user == null) {
				System.out.println("没有登录");
				form.setIslogin("not");
			}
			if (user != null) {
				// 是否已经加入该圈子
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
					.andUseridEqualTo(user.getUserid());
				int isAddCircle = 0;
				isAddCircle = userCircleRlsService.countByExample(ucrexample);
				form.setIslogin("have");
				form.setIsAddCircle(isAddCircle);
			}
			form.setUser(user);
			// 最新加入圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.createCriteria()
				.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(0);
			userCircleRlsExample.setRowsPerPage(12);
			userCircleRlsExample.setOrderByClause("joindatetime desc");

			List<UserCircleRls> userCircleRlss = userCircleRlsService
				.selectByExample(userCircleRlsExample);
			form.setUserCircleRlss(userCircleRlss);
			
			 //全职成员
			userCircleRlsExample.clear();
			userCircleRlsExample.createCriteria().andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(12);
			userCircleRlsExample.setRowsPerPage(10);
			userCircleRlsExample.setOrderByClause(" joindatetime desc");
			List<UserCircleRls> circleAlluser = userCircleRlsService.selectByExample(userCircleRlsExample);
			form.setCircleAlluser(circleAlluser);
			
			UserCircleRlsExample countCircleRlsExample = new UserCircleRlsExample();
			countCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			int count = userCircleRlsService.countByExample(countCircleRlsExample);
			form.setCount(count);
			
			//该圈子所属标签
			CircletagrelateExample tagrelateExample = new CircletagrelateExample();
			tagrelateExample.createCriteria().andCircleidEqualTo(form.getId());
			tagrelateExample.setOrderByClause(" def1 ");
			List<Circletagrelate> tagrelate = circletagrelateService.selectByExample(tagrelateExample);
			form.setCircletagrelate(tagrelate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "circletalk";
	}
	
	public String showmoretalkabout(){
		try {
			System.out.println(form.getId()+" ===="+form.getPageSize()+"==="+form.getPageNum());
			Map<String, Object> json = new HashMap<String, Object>();
			CircletalkaboutExample talkabout = new CircletalkaboutExample();
			talkabout.createCriteria().andCircleidEqualTo(form.getId());
			talkabout.setOrderByClause("updatetime desc");
			if(form.getPageNum() == 1){
				talkabout.setLimitStart(0);
			}else{
				talkabout.setLimitStart((form.getPageNum() - 1)* form.getPageSize());
			}
			talkabout.setRowsPerPage(form.getPageSize());
			List<Circletalkabout> circletalkabout = circletalkaboutService.selectByExample(talkabout);
			for (Circletalkabout circletalkabout2 : circletalkabout) {
				UsertalkExample usertalk = new UsertalkExample();
				usertalk.createCriteria().andDef1EqualTo(circletalkabout2.getId());
				usertalk.setOrderByClause(" talktime desc");
				usertalk.setLimitStart(0);
				usertalk.setRowsPerPage(1);
				List<Usertalk> talk = usertalkService.selectByExample(usertalk);
				if(talk.size() > 0){
					circletalkabout2.setTalk(talk.get(0));
				}else{
					circletalkabout2.setTalk(null);
				}
			}
			json.put("moretalkabout", circletalkabout);
			talkabout.clear();
			talkabout.createCriteria().andCircleidEqualTo(form.getId());
			int talkcount = circletalkaboutService.countByExample(talkabout);
			json.put("talkcount", talkcount);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String talkdetail(){
		try {
			UserInfo user = getCurrentUser();
			// 单个圈子的信息
			CircleInfoExample circleInfoExample = new CircleInfoExample();
			CircleInfo center_circleInfo = (CircleInfo) circleInfoService
				.selectByPrimaryKey(form.getId(), CircleInfo.class);
			circleInfoExample.createCriteria();
			form.setCircleleft(center_circleInfo);
			if (user == null) {
				System.out.println("没有登录");
				form.setIslogin("not");
			}
			if (user != null) {
				// 是否已经加入该圈子
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andCircleidEqualTo(form.getId())
					.andUseridEqualTo(user.getUserid());
				int isAddCircle = 0;
				isAddCircle = userCircleRlsService.countByExample(ucrexample);
				form.setIslogin("have");
				form.setIsAddCircle(isAddCircle);
			}
			form.setUser(user);
			// 最新加入圈子
			UserCircleRlsExample userCircleRlsExample = new UserCircleRlsExample();
			userCircleRlsExample.createCriteria()
				.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(0);
			userCircleRlsExample.setRowsPerPage(12);
			userCircleRlsExample.setOrderByClause("joindatetime desc");

			List<UserCircleRls> userCircleRlss = userCircleRlsService
				.selectByExample(userCircleRlsExample);
			form.setUserCircleRlss(userCircleRlss);
			
			 //全职成员
			userCircleRlsExample.clear();
			userCircleRlsExample.createCriteria().andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			userCircleRlsExample.setLimitStart(12);
			userCircleRlsExample.setRowsPerPage(10);
			userCircleRlsExample.setOrderByClause(" joindatetime desc");
			List<UserCircleRls> circleAlluser = userCircleRlsService.selectByExample(userCircleRlsExample);
			form.setCircleAlluser(circleAlluser);
			
			UserCircleRlsExample countCircleRlsExample = new UserCircleRlsExample();
			countCircleRlsExample.createCriteria()
					.andCircleidEqualTo(form.getId()).andUcRoleEqualTo(3);
			int count = userCircleRlsService.countByExample(countCircleRlsExample);
			form.setCount(count);
			
			//该圈子所属标签
			CircletagrelateExample tagrelateExample = new CircletagrelateExample();
			tagrelateExample.createCriteria().andCircleidEqualTo(form.getId());
			tagrelateExample.setOrderByClause(" def1 ");
			List<Circletagrelate> tagrelate = circletagrelateService.selectByExample(tagrelateExample);
			form.setCircletagrelate(tagrelate);
			
			CircletalkaboutExample talkabout = new CircletalkaboutExample();
			talkabout.createCriteria().andCircleidEqualTo(form.getId()).andIdEqualTo(form.getPid());
			talkabout.setOrderByClause("updatetime desc");
			List<Circletalkabout> circletalkabout = circletalkaboutService.selectByExample(talkabout);
			if(circletalkabout.size()>0){
				form.setTalkabout(circletalkabout.get(0));
			}else{
				form.setTalkabout(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "talkdetail";
	}

	public String showtalkdetail(){
		try {
			System.out.println(form.getId()+" ===="+form.getPageSize()+"==="+form.getPageNum()+"==="+form.getPid());
			Map<String, Object> json = new HashMap<String, Object>();
			UsertalkExample usertalk = new UsertalkExample();
			usertalk.createCriteria().andCircleidEqualTo(form.getId()).andDef1EqualTo(form.getPid());
			usertalk.setOrderByClause(" talktime desc");
			usertalk.setLimitStart((form.getPageNum() - 1)* form.getPageSize());
			usertalk.setRowsPerPage(form.getPageSize());
			List<Usertalk> talkList = usertalkService.selectByExample(usertalk);
			json.put("talklist", talkList);
			usertalk.clear();
			usertalk.createCriteria().andCircleidEqualTo(form.getId()).andDef1EqualTo(form.getPid());
			int talkcount = circletalkaboutService.countByExample(usertalk);
			json.put("talkcount", talkcount);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	
	
	public Object getModel() {
		return form;
	}
}
