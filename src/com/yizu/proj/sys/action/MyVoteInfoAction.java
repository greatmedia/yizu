package com.yizu.proj.sys.action;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.renren.api.client.utils.Md5Utils;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.config.AppParametersConfig;
import com.yizu.proj.convert.DateConverter;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.mail.Mailer;
import com.yizu.proj.sys.action.form.JsonInfoForm;
import com.yizu.proj.sys.action.form.MyVoteInfoForm;
import com.yizu.proj.sys.action.form.UserInfoForm;
import com.yizu.proj.sys.beans.CiecleDetailImg;
import com.yizu.proj.sys.beans.CiecleDetailImgExample;
import com.yizu.proj.sys.beans.CircleCommentInfo;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.CircleInfoExample;
import com.yizu.proj.sys.beans.JoinVote;
import com.yizu.proj.sys.beans.JoinVoteExample;
import com.yizu.proj.sys.beans.MyVoteInfo;
import com.yizu.proj.sys.beans.MyVoteInfoExample;
import com.yizu.proj.sys.beans.MyVoteInfoImage;
import com.yizu.proj.sys.beans.MyVoteInfoImageExample;
import com.yizu.proj.sys.beans.TfindPwdLog;
import com.yizu.proj.sys.beans.UserCircleRlsExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.VoteCommentInfo;
import com.yizu.proj.sys.beans.VoteCommentInfoExample;
import com.yizu.proj.sys.service.CiecleDetailImgService;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.CircleInfoService;
import com.yizu.proj.sys.service.JoinVoteService;
import com.yizu.proj.sys.service.MyVoteInfoImageService;
import com.yizu.proj.sys.service.MyVoteInfoService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.VoteCommentInfoService;
import com.yizu.proj.utils.DateUtil;
import com.yizu.proj.utils.IpUtils;

@Scope("prototype")
@Controller("myVoteInfoAction")
public class MyVoteInfoAction extends BaseAction implements ModelDriven {

	private static final long serialVersionUID = -1517391340327305523L;
	private MyVoteInfoForm form = new MyVoteInfoForm();
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private MyVoteInfoService myVoteInfoService;
	@Autowired
	private MyVoteInfoImageService myVoteInfoImageService;
	@Autowired
	private VoteCommentInfoService voteCommentInfoService;
	@Autowired
	private CircleDetailInfoService circleDetailInfoService;
	@Autowired
	private JoinVoteService joinVoteService;
	public String getSearchVote()
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
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andTitleLike("%"+form.getContent()+"%");
			myVoteInfoExample.createCriteria().andIsthroughEqualTo(1);
			System.out.println(form.getContent());
			myVoteInfoExample.setOrderByClause(" starttime desc ");
			myVoteInfoExample.setDistinct(true);
			
			int counttitle = myVoteInfoService.countByExample(myVoteInfoExample);
			json.put("counttitle", counttitle);
			System.out.println(counttitle);
			
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> searchtitlelist = myVoteInfoService.selectByExample(myVoteInfoExample);
			System.out.println(searchtitlelist.size());
			json.put("searchtitlelist", searchtitlelist);
			
			MyVoteInfoExample suExample = new MyVoteInfoExample();
			suExample.createCriteria().andIsthroughEqualTo(1).andSummaryLike("%"+form.getContent()+"%");
			suExample.setOrderByClause(" starttime desc ");
			suExample.setDistinct(true);
			int countsumaary = myVoteInfoService.countByExample(suExample);
			System.out.println(countsumaary);
			json.put("countsumaary", countsumaary);
			
			suExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			suExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> searchsummarylist = myVoteInfoService.selectByExample(suExample);
			System.out.println(searchsummarylist.size());
			json.put("searchsummarylist", searchsummarylist);
			
			MyVoteInfoExample example = new MyVoteInfoExample();
			example.createCriteria().andStarttimeLessThan(new Date()).andEndtimeGreaterThan(new Date()).andIsthroughEqualTo(1);
			example.setOrderByClause(" joincount desc ");
			example.setLimitStart(0);
			example.setRowsPerPage(5);
			example.createCriteria();
			List<MyVoteInfo> hotMyvoteInfo = myVoteInfoService.selectByExample(example);
			json.put("hotMyvoteInfo", hotMyvoteInfo);
			
			form.setJsonMsg("success", true, json);
			
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//获取所有已通过审核的投票
	public String getAllVote(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andIsthroughEqualTo(1);
			myVoteInfoExample.setOrderByClause(" starttime desc ");
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> voteList=myVoteInfoService.selectByExample(myVoteInfoExample);

			MyVoteInfoExample countMyVoteInfo = new MyVoteInfoExample();
			countMyVoteInfo.createCriteria().andIsthroughEqualTo(1);
			int count = myVoteInfoImageService.countByExample(countMyVoteInfo);
			
			System.out.println("共有多少投票信息"+count);
			json.put("user", user);
			json.put("count", count);
			json.put("voteList", voteList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//得到正在进行的投票
	public String getVoting(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andStarttimeLessThan(new Date()).andEndtimeGreaterThan(new Date()).andIsthroughEqualTo(1);
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> votingList=myVoteInfoService.selectByExample(myVoteInfoExample);
			
			MyVoteInfoExample countMyVoteInfo = new MyVoteInfoExample();
			countMyVoteInfo.createCriteria().andStarttimeLessThan(new Date()).andEndtimeGreaterThan(new Date()).andIsthroughEqualTo(1);
			int count = myVoteInfoImageService.countByExample(countMyVoteInfo);
			
			json.put("voteList", votingList);
			json.put("count", count);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//得到即将开始的投票
	public String getBeforeVoting(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andStarttimeGreaterThan(new Date()).andIsthroughEqualTo(1);
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> beforeVotingList=myVoteInfoService.selectByExample(myVoteInfoExample);
			
			MyVoteInfoExample countMyVoteInfo = new MyVoteInfoExample();
			countMyVoteInfo.createCriteria().andStarttimeGreaterThan(new Date()).andIsthroughEqualTo(1);
			int count = myVoteInfoImageService.countByExample(countMyVoteInfo);
			
			json.put("count", count);
			json.put("voteList", beforeVotingList);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//已经结束的投票
	public String getEndVote(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andEndtimeLessThan(new Date()).andIsthroughEqualTo(1);
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> beforeVotingList=myVoteInfoService.selectByExample(myVoteInfoExample);
			
			MyVoteInfoExample countMyVoteInfo = new MyVoteInfoExample();
			countMyVoteInfo.createCriteria().andEndtimeLessThan(new Date()).andIsthroughEqualTo(1);
			int count = myVoteInfoImageService.countByExample(countMyVoteInfo);
			
			json.put("count", count);
			json.put("voteList", beforeVotingList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String getUser(){
		Map<String, Object> json = new HashMap<String, Object>();
		UserInfo user = getCurrentUser();
		json.put("user",user);
		form.setJsonMsg("success", true, json);
		return JSON_PAGE;
	}
	
	//查询所有投票 包含未通过审核的
	public String getAllVoteCheck(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.setOrderByClause(" starttime desc ");
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> voteList=myVoteInfoService.selectByExample(myVoteInfoExample);
			System.out.println(voteList.size());
			
			MyVoteInfoExample countMyVoteInfo = new MyVoteInfoExample();
			int count = myVoteInfoImageService.countByExample(countMyVoteInfo);
			System.out.println("共有多少投票信息"+count);
			
			json.put("user", user);
			json.put("count", count);
			json.put("voteList", voteList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	//查询所有未通过审核的通票
	public String noThoughCheck(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andIsthroughEqualTo(0);
			myVoteInfoExample.setOrderByClause(" starttime desc ");
			myVoteInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			myVoteInfoExample.setRowsPerPage(form.getPageSize());
			List<MyVoteInfo> voteList=myVoteInfoService.selectByExample(myVoteInfoExample);

			System.out.println(voteList.size());
			MyVoteInfoExample countMyVoteInfo = new MyVoteInfoExample();
			countMyVoteInfo.createCriteria().andIsthroughEqualTo(0);
			int count = myVoteInfoImageService.countByExample(countMyVoteInfo);
			System.out.println("共有多少未通过审核的投票信息"+count);
			
			json.put("user", user);
			json.put("count", count);
			json.put("voteList", voteList);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	//check使未通过审核的投票通过审核
	public String checkVote(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoImageService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
			myVoteInfo.setIsthrough(1);
			int res = myVoteInfoImageService.updateByPrimaryKey(myVoteInfo);
			json.put("res", res);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	//checkNoThrough 是通过审核的投票转成不通过
	public String checkNoThrough(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoImageService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
			myVoteInfo.setIsthrough(0);
			int res = myVoteInfoImageService.updateByPrimaryKey(myVoteInfo);
			json.put("res", res);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	
	// 得到一个投票的信息
	public String getVote() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {

			UserInfo user = getCurrentUser();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoService
					.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
			json.put("myVoteInfo", myVoteInfo);
			if (user == null) {
				System.out.println("没有登录");
				json.put("islogin", "not");
			}
			if (user != null) {
				json.put("islogin", "have");
				json.put("user", user);
			}
			//一个投票的所有的图片
			MyVoteInfoImageExample myVoteInfoImageExample = new MyVoteInfoImageExample();
			System.out.println(form.getId());
			myVoteInfoImageExample.createCriteria().andVoteIdEqualTo(form.getId());
			List<MyVoteInfoImage> myVoteInfoImages = myVoteInfoImageService.selectByExample(myVoteInfoImageExample);
			json.put("myVoteInfoImages", myVoteInfoImages);
			
			//用户创建的话题总数
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(myVoteInfo.getUserid());
			int countTopic =circleDetailInfoService.countByExample(circleDetailInfoExample);
			//用户创建投票的总数
			MyVoteInfoExample countMyVoteInfoExample = new MyVoteInfoExample();
			countMyVoteInfoExample.createCriteria().andUseridEqualTo(myVoteInfo.getUserid());
			int countVote = myVoteInfoImageService.countByExample(countMyVoteInfoExample);
			
			json.put("countTopic", countTopic);
			json.put("countVote", countVote);
			//增加投票访问量
			MyVoteInfo visitmyVoteInfo = (MyVoteInfo) myVoteInfoImageService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
			System.out.println(visitmyVoteInfo.getVisitcount());
			int temp = visitmyVoteInfo.getVisitcount()+1;
			visitmyVoteInfo.setVisitcount(temp);
			System.out.println(temp);
			int res = myVoteInfoImageService.updateByPrimaryKey(visitmyVoteInfo);
			System.out.println(res);
			System.out.println(visitmyVoteInfo.getVisitcount());
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 得到一个投票的所有的图片
	public String getVoteImg() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfoImageExample myVoteInfoImageExample = new MyVoteInfoImageExample();
			System.out.println(form.getId());
			myVoteInfoImageExample.createCriteria().andVoteIdEqualTo(form.getId());
			List<MyVoteInfoImage> myVoteInfoImages = myVoteInfoImageService.selectByExample(myVoteInfoImageExample);
			json.put("myVoteInfoImages", myVoteInfoImages);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 得到一个投票的所有的评论
	public String getVoteComment() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			VoteCommentInfoExample voteCommentInfoExample = new VoteCommentInfoExample();
			voteCommentInfoExample.createCriteria().andVoteIdEqualTo(form.getId());
			voteCommentInfoExample.setOrderByClause(" commentdate desc");
			voteCommentInfoExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			voteCommentInfoExample.setRowsPerPage(form.getPageSize());
			List<VoteCommentInfo> commentInfos = voteCommentInfoService.selectByExample(voteCommentInfoExample);
			
			VoteCommentInfoExample countExample = new VoteCommentInfoExample();
			countExample.createCriteria().andVoteIdEqualTo(form.getId());
			int count = voteCommentInfoService.countByExample(countExample);
			json.put("count", count);
			json.put("commentInfos", commentInfos);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 提交评论
	public String comment() {
//		Map<String, Object> json = new HashMap<String, Object>();
//		try {
//			UserInfo user = getCurrentUser();
//			VoteCommentInfo voteCommentInfo = new VoteCommentInfo();
//			System.out.println("id...." + form.getId() + "...conetnt"
//					+ form.getContent());
//			voteCommentInfo.setCommentId(String.valueOf(UUID.randomUUID()));
//			voteCommentInfo.setCommentinfo(form.getContent());
//			voteCommentInfo.setCommentdate(new Date());
//			voteCommentInfo.setUserid(user.getUserid());
//			voteCommentInfo.setVoteId(form.getId());
//			int result = voteCommentInfoService.insert(voteCommentInfo);
//			System.out.println("评论信息" + result);
//			if (result > 0) {
//				System.err.println("评论成功" + result);
//				json.put("result", result);
//				form.setJsonMsg("success", true, json);
//			} else {
//				System.err.println("评论失败" + result);
//				json.put("result", result);
//				form.setJsonMsg("error", true, json);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return JSON_PAGE;
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			VoteCommentInfo voteCommentInfo = new VoteCommentInfo();
			System.out.println("id...." + form.getId() + "...conetnt"
					+ form.getContent());
			voteCommentInfo.setCommentId(String.valueOf(UUID.randomUUID()));
			voteCommentInfo.setCommentinfo(form.getContent());
			voteCommentInfo.setCommentdate(new Date());
			voteCommentInfo.setUserid(user.getUserid());
			voteCommentInfo.setVoteId(form.getId());
			int result = voteCommentInfoService.insert(voteCommentInfo);
			System.out.println("评论信息" + result);
			if (result > 0) {
				System.err.println("评论成功" + result);
				
				MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
				MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
				if(myVoteInfo.getCommentcount()==null)
				{
					myVoteInfo.setCommentcount(0);
				}
				myVoteInfo.setCommentcount(myVoteInfo.getCommentcount()+1);
				int icom = myVoteInfoService.updateByPrimaryKey(myVoteInfo);
				json.put("result", result);
				form.setJsonMsg("success", true, json);
			} else {
				System.err.println("评论失败" + result);
				json.put("result", result);
				form.setJsonMsg("error", true, json);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 获取投票类型
	public String getJoinVote() {
//		Map<String, Object> json = new HashMap<String, Object>();
//		try {
//			UserInfo user = getCurrentUser();
//			JoinVoteExample joinVoteExample = new JoinVoteExample();
//			joinVoteExample.createCriteria().andVoteIdEqualTo(form.getId());
// 			List<JoinVote> joinVotes = joinVoteService
//					.selectByExample(joinVoteExample);
//			int one = 0, two = 0, three = 0, four = 0;
//			String gz = "not",love= "not",notLove = "not",buy = "not";
//			for (int i = 0; i < joinVotes.size(); i++) {
//				JoinVote joinVote = joinVotes.get(i);
//				if (user != null) {
//					System.out.println("已经登录");
//					System.out.println("joinVote.getUserid()...."+joinVote.getUserid()+"....user.getUserid()"+user.getUserid()+"....joinVote。getVoteId()"+joinVote.getVoteId()+"...form.getId()"+form.getId());
//					if(joinVote.getUserid().equals(user.getUserid()) && joinVote.getVoteId().equals(form.getId()))
//					{
//						if(joinVote.getResulttype()==1)
//						{
//							gz = "have";
//						}
//						if(joinVote.getResulttype()==2)
//						{
//							love = "have";
//						}
//						if(joinVote.getResulttype()==3)
//						{
//							notLove = "have";
//						}
//						if(joinVote.getResulttype()==4)
//						{
//							buy = "have";
//						}
//					}
//				}
//				
//				if (joinVote.getResulttype() == 1) {
//					one = one +1;
//				}
//				if (joinVote.getResulttype() == 2) {
//					two = two +1;
//				}
//				if (joinVote.getResulttype() == 3) {
//					three = three +1;
//				}
//				if (joinVote.getResulttype() == 4) {
//					four = four +1;
//				}
//			}
//			//获取评论总数
//			VoteCommentInfoExample countExample = new VoteCommentInfoExample();
//			countExample.createCriteria().andVoteIdEqualTo(form.getId());
//			int count = voteCommentInfoService.countByExample(countExample);
//			json.put("count", count); 
//			json.put("gz", gz);
//			json.put("love", love);
//			json.put("notLove", notLove);
//			json.put("buy", buy);
//			json.put("joinVotes", joinVotes);
//			json.put("one", one);
//			json.put("two", two);
//			json.put("three", three);
//			json.put("four", four);
//			form.setJsonMsg("success", true, json);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return JSON_PAGE;
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfo user = getCurrentUser();
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
			
			//获取评论总数
			VoteCommentInfoExample countExample = new VoteCommentInfoExample();
			countExample.createCriteria().andVoteIdEqualTo(form.getId());
			int commentCount = voteCommentInfoService.countByExample(countExample);
			json.put("commentCount", commentCount);
			json.put("myVoteInfo", myVoteInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 投票
	public String vote() {
		Map<String, Object> json = new HashMap<String, Object>();
//		try {
//			UserInfo user = getCurrentUser();
//			JoinVote joinVote = new JoinVote();
//			joinVote.setJoinvoteId(String.valueOf(UUID.randomUUID()));
//			joinVote.setPartketime(new Date());
//			joinVote.setUserid(user.getUserid());
//			joinVote.setVoteId(form.getId());
//			joinVote.setResulttype(Integer.parseInt(form.getIds()));
//			int result = joinVoteService.insert(joinVote);
//			if(result>0)
//			{
//				//增加投票数量
//				MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoImageService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
//				System.out.println(myVoteInfo.getJoincount());
//				myVoteInfo.setJoincount((myVoteInfo.getJoincount())+1);
//				int res = myVoteInfoImageService.updateByPrimaryKey(myVoteInfo);
//			}
//			json.put("result", result);
//			form.setJsonMsg("success", true, json);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return JSON_PAGE;
		
		try {
			int isVote = 1;
			UserInfo user = getCurrentUser();
			JoinVoteExample joinVoteExample = new JoinVoteExample();
			joinVoteExample.createCriteria().andVoteIdEqualTo(form.getVid()).andUseridEqualTo(user.getUserid()).andResulttypeEqualTo(form.getResultType());
			System.out.println("vid=="+form.getVid()+"----ResultType"+form.getResultType());
 			List<JoinVote> joinVotes = joinVoteService.selectByExample(joinVoteExample);
 			System.out.println(joinVotes.size());
 			if(joinVotes.size()>=1)
 			{
				int rid = form.getResultType();
				String reStr="";
				switch (rid) {
				case 1:
					reStr="您已经关注过该商品了..";
					break;
				case 2:
					reStr="您已经投过票了..";
					break;
				case 3:
					reStr="您已经投过票了..";
					break;
				case 4:
					reStr="我们尽快与商家联系..";
					break;
				}
				System.out.println(reStr);
				isVote = 1;
				json.put("result", rid);
				json.put("reStr", reStr);
				json.put("isVote", isVote);
				form.setJsonMsg("success", true, json);
 			}else{
 				
 				JoinVote joinVote = new JoinVote();
 				joinVote.setJoinvoteId(String.valueOf(UUID.randomUUID()));
 				joinVote.setPartketime(new Date());
 				joinVote.setUserid(user.getUserid());
 				joinVote.setVoteId(form.getVid());
 				joinVote.setResulttype(form.getResultType());
 				int result = joinVoteService.insert(joinVote);
 				
 				if(result>0)
 				{
 					//增加投票参与数量
 					MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoImageService.selectByPrimaryKey(form.getVid(), MyVoteInfo.class);
 					System.out.println(myVoteInfo.getJoincount());
 					int id = form.getResultType();
 					switch (id) {
 					case 1:
 						if(myVoteInfo.getGzcount()==null){
 							myVoteInfo.setGzcount(0);
 						}
 						myVoteInfo.setGzcount((myVoteInfo.getGzcount())+1);
 						break;
 					case 2:
 						if(myVoteInfo.getLove()==null){
 							myVoteInfo.setLove(0);
 						}
 						myVoteInfo.setLove((myVoteInfo.getLove())+1);		
 						break;
 					case 3:
 						if(myVoteInfo.getNotlove()==null){
 							myVoteInfo.setNotlove(0);
 						}
 						myVoteInfo.setNotlove((myVoteInfo.getNotlove())+1);
 						break;
 					case 4:
 						if(myVoteInfo.getJoincount()==null){
 							myVoteInfo.setJoincount(0);
 						}
 						myVoteInfo.setBuy((myVoteInfo.getBuy())+1);
 						break;
 					}
 					myVoteInfo.setJoincount((myVoteInfo.getJoincount())+1);
 					
 					int res = myVoteInfoImageService.updateByPrimaryKey(myVoteInfo);
 					isVote = 0;
 				}
 				json.put("result", result);
 				json.put("isVote", isVote);
 				form.setJsonMsg("success", true, json);
 			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}

	// 用户话题的总数
	public String count() {
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			CircleDetailInfoExample circleDetailInfoExample = new CircleDetailInfoExample();
			circleDetailInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int countTopic =circleDetailInfoService.countByExample(circleDetailInfoExample);
			
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andUseridEqualTo(Integer.parseInt(form.getId()));
			int countVote = myVoteInfoImageService.countByExample(myVoteInfoExample);
			
			json.put("countTopic", countTopic);
			json.put("countVote", countVote);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	//热门投票
	public String hotVote()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			MyVoteInfoExample example = new MyVoteInfoExample();
			example.createCriteria().andStarttimeLessThan(new Date()).andEndtimeGreaterThan(new Date()).andIsthroughEqualTo(1);
			example.setOrderByClause(" joincount desc ");
			example.setLimitStart(0);
			example.setRowsPerPage(5);
			example.createCriteria();
			List<MyVoteInfo> hotMyvoteInfo = myVoteInfoService.selectByExample(example);
			System.out.println("共有热门投票  "+hotMyvoteInfo.size());
			json.put("hotMyvoteInfo", hotMyvoteInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String swfUpload(){
	    	byte[] imgBufTemp = new byte[102401];
	    	StringBuilder json = new StringBuilder();
			InputStream stream = null;
			BufferedOutputStream bos = null;
			String fileUrl = "";
			try {
				if (ServletFileUpload.isMultipartContent(getRequest())) {
					if(form.getFiledata() != null){
						stream = new FileInputStream(form.getFiledata());
						String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + RandomUtils.nextInt();
						// 得到文件的扩展名(无扩展名时将得到全名)
						String ext = form.getFilename().substring(form.getFilename().lastIndexOf(".") + 1);
						String fileName = prefix + "." + ext;
						String savePath = AppParametersConfig.getParameter("file.tmp.path") + fileName;
						fileUrl = "uploadPath/tmp/" + fileName;
						bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
						int length;
						while ((length = stream.read(imgBufTemp)) != -1) {
							bos.write(imgBufTemp, 0, length);
						}
						String cURL = "E:/uploadPath/tmp/" + fileName;
						 File imgfile = new File(cURL);
				         try {
				        	 System.out.println(cURL);
				        	 FileInputStream fis = new FileInputStream(imgfile);
				             BufferedImage buff = ImageIO.read(fis); 
//				             long w = buff.getWidth() * 1L;
//				             long h = buff.getHeight() * 1L;
//				             System.out.println(w);
//				             System.out.println(h);
//				             if(w<630)
//			                 {
//			                	 form.setJsonMsg("w", false, null);
//							     return JSON_PAGE;
//			                 }
//				             if(h<240)
//			                 {
//			                	 form.setJsonMsg("h", false, null);
//							     return JSON_PAGE;
//			                 }
				             fis.close();
				         }catch(Exception e)
				         {
				        	 form.setJsonMsg("ex", false, null);
				        	 return JSON_PAGE;
				         }
					}
					json.append("{");
					json.append("'");
					json.append("fileUrl");
					json.append("':'");
					json.append(fileUrl.toString());
					json.append("'");
					Enumeration<String> pNames = getRequest().getParameterNames();
					String pName;
					while (pNames.hasMoreElements()) {
						json.append(",");
						pName = (String) pNames.nextElement();
						json.append("'");
						json.append(pName);
						json.append("':'");
						json.append(getRequest().getParameter(pName));
						json.append("'");
					}
					json.append("}");
					
					form.setJsonText(json.toString());
				}
			} catch (IOException e) {
				  LOG.error(e.getMessage(), e);
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (Exception e) {
					}
				}
				if (bos != null) {
					try {
						bos.close();
					} catch (Exception e) {
					}
				}
			}
	    	return JSON_PAGE;
	    }

	public String ajaxCreateVote(){
    	try {
    		UserInfo user = getCurrentUser();
			if (getCurrentUser() == null) {
			    form.setJsonMsg("您还未登录，请先登录吧！", false, null);
			    return JSON_PAGE;
			}
			
			if (StringUtils.isBlank(form.getImages())) {
				 form.setJsonMsg("亲，您还没上传图片！", false, null);
			     return JSON_PAGE;
			}
			Double lowPrice=form.getMyVoteInfo().getPrice();
			Double highPrice=form.getMyVoteInfo().getPricess();
			if(lowPrice>highPrice){
				form.setJsonMsg("最低价不能高于最高价！", false, null);
			     return JSON_PAGE;
			}
			MyVoteInfo voteInfo = new MyVoteInfo();
			voteInfo.setUserid(user.getUserid());
			voteInfo.setJoincount(0);
			voteInfo.setPrice(lowPrice);
			voteInfo.setPricess(highPrice);
			voteInfo.setSummary(form.getMyVoteInfo().getSummary());
			voteInfo.setTitle(form.getMyVoteInfo().getTitle());
			UUID voteid = UUID.randomUUID();
			voteInfo.setVoteId(String.valueOf(voteid));
			voteInfo.setVoteTag(form.getMyVoteInfo().getVoteTag());
			voteInfo.setVisitcount(0);
			voteInfo.setGzcount(0);
			voteInfo.setLove(0);
			voteInfo.setNotlove(0);
			voteInfo.setBuy(0);
			voteInfo.setIsrecommend(0);
			voteInfo.setCreatedate(new Date());
			voteInfo.setCommentcount(0);
			//isThrough  0:审核  1：通过
			voteInfo.setIsthrough(0);
			//开始时间
			String start=form.getStartTime();
			
			Date now=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String s_now = sdf.format(now);
			Date startTime=sdf.parse(start);
			String s_time = sdf.format(startTime);
			if(s_time.equals(s_now)){
				startTime=now;
			}else{
				startTime=sdf.parse(start);
			}
			long startCha=startTime.getTime()-now.getTime();
			long sday=startCha/(24*60*60*1000);
			long shour=(startCha/(60*60*1000)-sday*24);
			long smin=((startCha/(60*1000))-sday*24*60-shour*60);
			if(sday<=0 && shour<=0 && smin<0){
				form.setJsonMsg("开始时间不能小于当前时间", false, null);
				  return JSON_PAGE;
			}
			voteInfo.setStarttime(startTime);
			//结束时间
			String joinDate=form.getEndTime();
			Date endTime=sdf.parse(joinDate);
			long endcha=endTime.getTime()-startTime.getTime();
			
			long cha=endTime.getTime()-now.getTime();
			long day=cha/(24*60*60*1000);
			long hour=(cha/(60*60*1000)-day*24);
			long min=((cha/(60*1000))-day*24*60-hour*60);
//			long s=(cha/1000-day*24*60*60-hour*60*60-min*60);
			if(day<=0 && hour<=0 && min<=0){
				form.setJsonMsg("结束时间不能小于当前时间", false, null);
				  return JSON_PAGE;
			}
			if(endcha<=0){
				form.setJsonMsg("结束时间不能小于开始时间", false, null);
					return JSON_PAGE;
			}
			if(day>=100){
				form.setJsonMsg("结束时间不能多于100天", false, null);
				 return JSON_PAGE;
			}
			voteInfo.setEndtime(endTime);
			int result = myVoteInfoService.insert(voteInfo);
			if (result < 1) {
				  form.setJsonMsg("添加失败，请稍候重试！", false, null);
				  return JSON_PAGE;
			}
			
			if (LOG.isDebugEnabled()) {
        		LOG.debug("创建投票成功..");
			}
			
			for (String strImage : form.getImages().split(",")) {
				try {
					String imageName = strImage;
                    if (imageName.indexOf("/") != -1) {
                    	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
					}
                    System.out.println(imageName);
                    if(!imageName.equals("close_ico.gif"))
                    {
	                    String currDate = checkDirExists()+"/";
	                    
	                    File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
	                    if (LOG.isDebugEnabled()) {
	                		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
	        			}
	                    FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.vote.imagePath")+currDate+imageName));
	                   
	                    FileUtils.deleteQuietly(temFile);
	                    //保存图片信息
	                    System.out.println(imageName);
	                    
                    	MyVoteInfoImage mi = new MyVoteInfoImage();
                        mi.setImageId(String.valueOf(UUID.randomUUID()));
                        mi.setVoteId(voteid.toString());
                        mi.setImgUpdatetime(new Date());
                    	mi.setImgAddress("uploadPath/vote/" +currDate+ imageName);
                    	 myVoteInfoImageService.insert(mi);
                    }
                   
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
			form.setJsonMsg("创建投票成功.正在审核中.\n请关注其他投票！", true, null);
			
    	} catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("添加失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
    }
	private String checkDirExists(){
	    	String currDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
	    	
	    	File imagePath = new File(AppParametersConfig.getParameter("file.vote.imagePath")+currDate+"/");
	        if(!imagePath.exists()) 
	        	imagePath.mkdirs();
	        return currDate;
	}
	public String updateVote()
	{
		try {
    		if (StringUtils.isNotBlank(form.getId())) {
    			MyVoteInfo myVoteInfo = (MyVoteInfo) myVoteInfoService.selectByPrimaryKey(form.getId(), MyVoteInfo.class);
    			if(myVoteInfo!=null)
    			{
    				form.setMyVoteInfo(myVoteInfo);
    				return SUCCESS;
    			}
    		}
		} catch (Exception e) {
			 LOG.error(e.getMessage(), e);
		}
		return "index";
	}
	public String ajaxUpdateVote()
	{
		try {
			if (getCurrentUser() == null) {
			    form.setJsonMsg("您还未登录，请先登录吧！", false, null);
			    return JSON_PAGE;
			}
			
			
			MyVoteInfo myVoteInfo  = (MyVoteInfo) myVoteInfoService.selectByPrimaryKey(form.getMyVoteInfo().getVoteId(), MyVoteInfo.class); 
			if (myVoteInfo == null) {
				 form.setJsonMsg("亲，这个投票已经被删除了哦,", false, null);
			     return JSON_PAGE;
			}
			
//			if (circleDetailInfo.getUserid() != getCurrentUser().getUserid()) {
//        		form.setJsonMsg("亲，这个圈子详不是你创建的不能修改哦！", false, null);
//				return JSON_PAGE;
//			}
			myVoteInfo.setTitle(form.getMyVoteInfo().getTitle());
			myVoteInfo.setSummary(form.getMyVoteInfo().getSummary());
			myVoteInfo.setStarttime(form.getMyVoteInfo().getStarttime());
			myVoteInfo.setEndtime(form.getMyVoteInfo().getEndtime());
			myVoteInfo.setPrice(form.getMyVoteInfo().getPrice());
			myVoteInfo.setPricess(form.getMyVoteInfo().getPricess());
			
			baseService.updateByPrimaryKeySelective(myVoteInfo);
			
			if (LOG.isDebugEnabled()) {
        		LOG.debug("修改投票详细完成！");
			}
			
			for (String strImage : form.getImages().split(",")) {
				try {
					String imageName = strImage;
                    if (imageName.indexOf("/") != -1) {
                    	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
					}
                    
                    File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
                    
                    String currDate = checkDirExists() + "/";
                    
                    if (LOG.isDebugEnabled()) {
                		LOG.debug("temFile.getAbsolutePath():" + temFile.getAbsolutePath());
        			}
                    
                    FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.vote.imagePath")+currDate+imageName));
//                    thumnail(imageName, temFile, currDate, AppParametersConfig.getParameter("file.circleDetail.middleImagePath"), AppParametersConfig.getParameter("file.circleDetail.smallImagePath"));
                    FileUtils.deleteQuietly(temFile);
                    
					MyVoteInfoImage mi = new MyVoteInfoImage();
					mi.setImageId(String.valueOf(UUID.randomUUID()));
					mi.setImgAddress("uploadPath/vote/" + currDate + imageName);
					mi.setImgUpdatetime(new Date());
					mi.setVoteId(form.getMyVoteInfo().getVoteId());
					baseService.insert(mi);
					
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
			
//			circleInfo.setComcount(circleInfo.getComcount() + 1);
//			baseService.updateByPrimaryKey(circleInfo);
			
			form.setJsonMsg("修改投票成功，赶紧去看看吧！", true, null);
			
    	} catch (Exception e) {
            LOG.error(e.getMessage(), e);
            form.setJsonMsg("修改投票失败，请稍候重试！", false, null);
        }
        return JSON_PAGE;
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}

}
