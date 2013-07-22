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

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.sys.action.form.JsonInfoForm;
import com.yizu.proj.sys.beans.CircleCommentInfo;
import com.yizu.proj.sys.beans.CircleCommentInfoExample;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.CircleInfo;
import com.yizu.proj.sys.beans.CircleInfoExample;
import com.yizu.proj.sys.beans.Favorite;
import com.yizu.proj.sys.beans.FavoriteExample;
import com.yizu.proj.sys.beans.Inform;
import com.yizu.proj.sys.beans.InformExample;
import com.yizu.proj.sys.beans.MyVoteInfoExample;
import com.yizu.proj.sys.beans.TagTopic;
import com.yizu.proj.sys.beans.TagTopicExample;
import com.yizu.proj.sys.beans.UserCircleRls;
import com.yizu.proj.sys.beans.UserCircleRlsExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.service.CircleCommentInfoService;
import com.yizu.proj.sys.service.CircleDetailInfoService;
import com.yizu.proj.sys.service.CircleInfoService;
import com.yizu.proj.sys.service.FavoriteService;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.TagTopicService;
import com.yizu.proj.sys.service.UserCircleRlsService;

@Scope("prototype")
@Controller("jsonInfoAction")
public class JsonInfoAction extends BaseAction implements ModelDriven {

	private static final long serialVersionUID = 569982359897816794L;
	private JsonInfoForm form = new JsonInfoForm();
	
	@Autowired
	private CircleInfoService circleInfoService;
	@Autowired
	private CircleDetailInfoService circleDetailInfoService;
	@Autowired
	private TagTopicService tagTopicService;
	@Autowired
	private CircleCommentInfoService circleCommentInfoService;
	@Autowired
	private UserCircleRlsService userCircleRlsService;
	@Autowired
	private InformService informService;
	@Autowired
	private FavoriteService favoriteService;
	
	private static final String TAG_ALL = "全部";
	/**
	 * 首页瀑布流获取json信息
	 * @return
	 */
	public String getIndexJsonInfo(){
		try {	
			System.out.println(form.getTag());
			System.out.println(form.getTag());
			System.out.println(form.getTag());
			String tag = "";
			boolean islogin = false;
			TagTopic tt = (TagTopic) tagTopicService.selectByPrimaryKey(form.getTag(), TagTopic.class);
			tag = tt.getTagname();
			//查询圈子信息
			CircleInfoExample cexample = new CircleInfoExample();
			System.out.println(form.getHot());
			if(form.getHot() ==1){
				//最新话题
				if(!tag.contains(TAG_ALL))
				cexample.createCriteria().andDef1Like("%"+tag+"%");//.andCircletagLike("%"+tag+"%");
				cexample.setOrderByClause(" createDateTime desc");
			}else if(form.getHot() ==2){
				//最热话题
				if(!tag.contains(TAG_ALL))
				cexample.createCriteria().andDef1Like("%"+tag+"%");
				cexample.setOrderByClause(" def2 desc, comCount desc");
			}else if(form.getHot() ==3){
				//推荐活体
				if(!tag.contains(TAG_ALL))
				cexample.createCriteria().andDef1Like("%"+tag+"%");
				cexample.createCriteria().andIstopEqualTo(1);
				cexample.setOrderByClause(" csort desc");
			}else if(form.getHot() ==5){
				//推荐活体
				if(!tag.contains(TAG_ALL))
				cexample.createCriteria().andDef3EqualTo(form.getTag());
				cexample.createCriteria().andIstopEqualTo(1);
				cexample.setOrderByClause(" csort desc");
			}
			cexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			cexample.setRowsPerPage(form.getPageSize());
			List<CircleInfo> clist = circleInfoService.selectByExample(cexample);
			
			Map<String,String> map = new HashMap<String,String>();
			UserInfo userinfo = getCurrentUser();
			if(userinfo != null){
				islogin = true;
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
				ucrexample.createCriteria().andUseridEqualTo(userinfo.getUserid());
				List<UserCircleRls> rclist = userCircleRlsService.selectByExample(ucrexample);
				for (UserCircleRls userCircleRls : rclist) {
					map.put(userCircleRls.getCircleid(), "1");
				}
				for (CircleInfo info : clist) {
					String val = map.get(info.getCircleid());
					if(!StringUtils.isEmpty(val)){
						info.setIsjoin("1");
					}else{
						info.setIsjoin("0");
					}
				}
			}
			
			
			//封装信息
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("circle_ls", clist);
			json.put("islogin", islogin);
			json.put("tagname", tag);
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		}
		return JSON_PAGE;
	}
	/**
	 * 获取导航圈子
	 * 11-30
	 */
	public String getCircleTotop(){
		try{
			Map<String,Object> json = new HashMap<String,Object>();
			boolean islogin = false;
			if(getCurrentUser() != null){
				islogin = true;
			}
			json.put("islogin",islogin);
			System.out.println("测试登录"+islogin);
			/**
			 * 名人逸族
			 */
			CircleInfoExample mrcircleinfo = new CircleInfoExample();
			mrcircleinfo.createCriteria().andDef3EqualTo("1");
			mrcircleinfo.setOrderByClause(" csort desc");
			mrcircleinfo.setLimitStart(0);
			mrcircleinfo.setRowsPerPage(4);
			List<CircleInfo> circleList = circleInfoService.selectByExample(mrcircleinfo);
			System.out.println("名人逸族的长度"+circleList.size());
			json.put("mrCircleinfo", circleList);
			
			/**
			 * 运动逸族
			 */
			CircleInfoExample ydExample = new CircleInfoExample();
			ydExample.createCriteria().andDef3EqualTo("2");
			ydExample.setOrderByClause(" csort desc ");
			ydExample.setLimitStart(0);
			ydExample.setRowsPerPage(4);
			List<CircleInfo> ydCircleinfo = circleInfoService.selectByExample(ydExample);
			System.out.println("运动逸族的长度"+ydCircleinfo.size());
			json.put("ydCircleinfo", ydCircleinfo);
			/**
			 * 休闲逸族
			 */
			CircleInfoExample xxExample = new CircleInfoExample();
			xxExample.createCriteria().andDef3EqualTo("3");
			xxExample.setOrderByClause(" csort desc ");
			xxExample.setLimitStart(0);
			xxExample.setRowsPerPage(4);
			List<CircleInfo> xxCircleinfo = circleInfoService.selectByExample(xxExample);
			System.out.println("休闲逸族长度"+xxCircleinfo.size());
			json.put("xxCircleinfo", xxCircleinfo);
			/**
			 * 文化逸族
			 */
			CircleInfoExample whExample = new CircleInfoExample();
			whExample.createCriteria().andDef3EqualTo("4");
			whExample.setOrderByClause(" csort desc ");
			whExample.setLimitStart(0);
			whExample.setRowsPerPage(4);
			List<CircleInfo> whCircleinfo = circleInfoService.selectByExample(whExample);
			System.out.println("文化逸族长度"+whCircleinfo.size());
			json.put("whCircleinfo", whCircleinfo);
			/**
			 * 职场逸族
			 */
			CircleInfoExample zcExample = new CircleInfoExample();
			zcExample.createCriteria().andDef3EqualTo("5");
			zcExample.setOrderByClause(" csort desc ");
			zcExample.setLimitStart(0);
			zcExample.setRowsPerPage(4);
			List<CircleInfo> zcCircleinfo = circleInfoService.selectByExample(zcExample);
			System.out.println("职场逸族长度"+zcCircleinfo.size());
			json.put("zcCircleinfo", zcCircleinfo);
			
			/**
			 * 女性逸族 
			 */
			CircleInfoExample nxExample = new CircleInfoExample();
			nxExample.createCriteria().andDef3EqualTo("6");
			nxExample.setOrderByClause(" csort desc ");
			nxExample.setLimitStart(0);
			nxExample.setRowsPerPage(4);
			List<CircleInfo> nxCircleinfo = circleInfoService.selectByExample(nxExample);
			System.out.println("女性逸族长度"+nxCircleinfo.size());
			json.put("nxCircleinfo", nxCircleinfo);
			
			/**
			 * 生活逸族
			 */
			CircleInfoExample shExample = new CircleInfoExample();
			shExample.createCriteria().andDef3EqualTo("7");
			shExample.setOrderByClause(" csort desc ");
			shExample.setLimitStart(0);
			shExample.setRowsPerPage(4);
			List<CircleInfo> shCircleinfo = circleInfoService.selectByExample(shExample);
			System.out.println("生活逸族长度"+shCircleinfo.size());
			json.put("shCircleinfo", shCircleinfo);
			
			/**
			 * 缤纷逸族
			 */
			CircleInfoExample bfExample = new CircleInfoExample();
			bfExample.createCriteria().andDef3EqualTo("8");
			bfExample.setOrderByClause(" csort desc ");
			bfExample.setLimitStart(0);
			bfExample.setRowsPerPage(8);
			List<CircleInfo> bfCircleinfo = circleInfoService.selectByExample(bfExample);
			System.out.println("其他"+bfCircleinfo.size());
			json.put("bfCircleinfo", bfCircleinfo);
			
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		}
		return JSON_PAGE;
	}
	/**
	 * 获取通知信息及时刷新
	 * 11-27
	 * @return
	 */
	public String getInformInfo(){
		try{
			if(getCurrentUser() == null){
				form.setJsonMsg("fail", false, null);
				return JSON_PAGE;
			}
			Map<String,Object> json = new HashMap<String,Object>();
			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(1);
			informExample.setOrderByClause(" createTime desc");
			int joinCircleCount = informService.countByExample(informExample);//加入圈子记数
			setJoinCircleCount(joinCircleCount+"");
			json.put("joinCircleCount", joinCircleCount);
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(2);
			informExample.setOrderByClause(" createTime desc");
			int pinglunCircleCount = informService.countByExample(informExample);//圈子内容评论记数
			setPinglunCircleCount(pinglunCircleCount+"");
			json.put("pinglunCircleCount", pinglunCircleCount);
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(3);
			informExample.setOrderByClause(" createTime desc");
			int topicPinglunCount = informService.countByExample(informExample);//话题内容评论记数
			setTopicPinglunCount(topicPinglunCount+"");
			json.put("topicPinglunCount", topicPinglunCount);
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(4);
			informExample.setOrderByClause(" createTime desc");
			int gzTopicCount = informService.countByExample(informExample);//话题被关注记数
			setGzTopicCount(gzTopicCount+"");
			json.put("gzTopicCount", gzTopicCount);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 获取通知信息
	 */
	public String getInformInfoNotRead(){
		try{
			Map<String,Object> json = new HashMap<String,Object>();
			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull();
			informExample.setOrderByClause(" createTime desc ");
			Inform in = new Inform();
			in.setIsread(1);
			informService.updateByExampleSelective(in, informExample);
			
			getSession().removeAttribute("joinCircleCount");
			getSession().removeAttribute("pinglunCircleCount");
			getSession().removeAttribute("topicPinglunCount");
			getSession().removeAttribute("gzTopicCount");
			
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andInformtypeNotEqualTo(1);
			informExample.setOrderByClause(" createTime desc");
			informExample.setLimitStart(0);
			informExample.setRowsPerPage(20);
			List<Inform> informList = informService.selectByExample(informExample);
			json.put("informList", informList);
			
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andInformtypeEqualTo(1);
			informExample.setOrderByClause(" createTime desc");
			informExample.setLimitStart(0);
			informExample.setRowsPerPage(20);
			List<Inform> joinCircleList = informService.selectByExample(informExample);//加入圈子记数
			json.put("joinCirclelist", joinCircleList);
			form.setJsonMsg("success", true, json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 获取搜索json信息
	 * @return
	 */
	public String getSearchJsonInfo(){
		try {
			List list = null;
			int circlecount = 0;
			int circledetailcount = 0;
			if(form.getType() == 1){
				//搜索圈子
				//查询圈子信息
				CircleInfoExample cexample = new CircleInfoExample();
				cexample.createCriteria().andCirclenameLike("%"+form.getContent()+"%");
				cexample.createCriteria().andSummaryLike("%"+form.getContent()+"%");
				cexample.setOrderByClause("createDateTime desc");
				cexample.setDistinct(true);
				
				circlecount = circleInfoService.countByExample(cexample);
				
				cexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				cexample.setRowsPerPage(form.getPageSize());
				list = circleInfoService.selectByExample(cexample);
				
				if(form.getPageNum() == 1){
					//查询圈子详细信息
					CircleDetailInfoExample cdexample = new CircleDetailInfoExample();
					cdexample.createCriteria().andTitleLike("%"+form.getContent()+"%");
					cdexample.createCriteria().andCirclecontentLike("%"+form.getContent()+"%");
					cexample.setDistinct(true);
					
					circledetailcount = circleDetailInfoService.countByExample(cdexample);
				}
			}else{
				//搜索圈子详细
				//查询圈子详细信息
				CircleDetailInfoExample cdexample = new CircleDetailInfoExample();
				cdexample.createCriteria().andTitleLike("%"+form.getContent()+"%");
				cdexample.createCriteria().andCirclecontentLike("%"+form.getContent()+"%");
				cdexample.setDistinct(true);
				
				circledetailcount = circleDetailInfoService.countByExample(cdexample);
				
				cdexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
				cdexample.setRowsPerPage(form.getPageSize());
				list = circleDetailInfoService.selectByExample(cdexample);
				
				if(form.getPageNum() == 1){
					//查询圈子信息
					CircleInfoExample cexample = new CircleInfoExample();
					cexample.createCriteria().andCirclenameLike("%"+form.getContent()+"%");
					cexample.createCriteria().andSummaryLike("%"+form.getContent()+"%");
					cexample.setOrderByClause("createDateTime desc");
					cexample.setDistinct(true);
					
					circlecount = circleInfoService.countByExample(cexample);
				}
			}
			
			//检查用户是否登录
			boolean islogin = false;
			UserInfo userinfo = getCurrentUser();
			if(userinfo != null){
				islogin = true;
			}
			
			//封装信息
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("ls", list);
			json.put("circle_count", circlecount);
			json.put("circledetail_count", circledetailcount);
			json.put("islogin", islogin);
			
			System.out.println("=================================================================");
			System.out.println("content:"+form.getContent());
			System.out.println("circle_count:"+circlecount);
			System.out.println("circledetail_count:"+circledetailcount);
			System.out.println("=================================================================");
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 查询热门话题列表 接送信息
	 * @return
	 */
	public String getHotCircleListJsonInfo(){
		try {
			boolean islogin = false;
//			String tag = "";
//			TagTopic tt = (TagTopic) tagTopicService.selectByPrimaryKey(form.getTag(), TagTopic.class);
//			tag = tt.getTagname();

			CircleDetailInfoExample cdexample = new CircleDetailInfoExample();
//			if(!tag.contains(TAG_ALL))
//			cdexample.createCriteria().andCtagLike("%"+tag+"%");
			cdexample.setOrderByClause("updateDateTime desc");
			cdexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			cdexample.setRowsPerPage(form.getPageSize());
			List<CircleDetailInfo> cdlist = circleDetailInfoService.selectByExample(cdexample);
			UserInfo userinfo = getCurrentUser();
			if(userinfo != null){
				islogin = true;
			}
			//封装信息
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("circledetailinfo_ls", cdlist);
			json.put("islogin", islogin);
			form.setJsonMsg("circledetailinfo_ls", true, json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 获取标签列表json信息
	 * @return
	 */
	public String getTagTopicListJsonInfo(){
		try {
			//查询标签信息
			TagTopicExample texample = new TagTopicExample();
			texample.createCriteria().andTagtypeEqualTo(form.getType()).andDisplayEqualTo(1);
			texample.setOrderByClause("tsort ");
			List<TagTopic> taglist = tagTopicService.selectByExample(texample);
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("tag_ls", taglist);
			form.setJsonMsg("success", true, json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail", false, null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 圈子详细列表json信息
	 * @return
	 */
	public String getQuanziDetailsListJsonInfo(){
		try {
			boolean islogin = false;
			//查询圈子详细
			CircleDetailInfoExample cdexample = new CircleDetailInfoExample();
			cdexample.createCriteria().andCircleidEqualTo(form.getId());
			cdexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			cdexample.setRowsPerPage(form.getPageSize());
			UserInfo userinfo = getCurrentUser();
			if(userinfo != null){
				islogin = true;
			}
			List<CircleDetailInfo> cdlist = circleDetailInfoService.selectByExample(cdexample);
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("circledetailinfo_ls", cdlist);
			json.put("islogin", islogin);
			form.setJsonMsg("success",true,json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("success",true,null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 根据id查询圈子信息
	 * @return
	 */
	public String getQuanziJsonInfo(){
		try {
			int count = 0;
			boolean islogin = false;
			Map<String,Object> json = new HashMap<String,Object>();
			//查询圈子信息
			CircleInfo circle = (CircleInfo) circleInfoService.selectByPrimaryKey(form.getId(), CircleInfo.class);
			UserInfo userinfo = getCurrentUser();
			if(userinfo != null){
				UserCircleRlsExample ucrexample = new UserCircleRlsExample();
     			ucrexample.createCriteria().andCircleidEqualTo(form.getId()).andUseridEqualTo(userinfo.getUserid());
				count = userCircleRlsService.countByExample(ucrexample);
				islogin = true;
			}
			json.put("circle", circle);
			json.put("joincount", count);
			json.put("islogin", islogin);
			form.setJsonMsg("success",true,json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("success",true,null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 圈子详细json信息
	 * @return
	 */
	public String getQuanziDetailsJsonInfo(){
		try {
			CircleDetailInfoExample cdexample = new CircleDetailInfoExample();
			cdexample.createCriteria().andCircleidEqualTo(form.getId()).andDisplayEqualTo(1);
			List<CircleDetailInfo> cdlist = circleDetailInfoService.selectByExample(cdexample);
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("circledetailinfo_ls", cdlist);
			form.setJsonMsg("success",true,json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 根据圈子详细id  分页查询圈子详细回复json信息
	 * @return
	 */
	public String getCommentListJsonInfo(){
		try {
			System.out.println("==========+++++++id="+form.getId());
			
			CircleCommentInfoExample ccexample = new CircleCommentInfoExample();
			ccexample.createCriteria().andCircledetailidEqualTo(form.getId());
			ccexample.setOrderByClause("createDate desc ");
			ccexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			ccexample.setRowsPerPage(form.getPageSize());
			List<CircleCommentInfo> cclist = circleCommentInfoService.selectByExample(ccexample);
			
			CircleCommentInfoExample ccexample2 = new CircleCommentInfoExample();
			ccexample2.createCriteria().andCircledetailidEqualTo(form.getId());
			int count = circleCommentInfoService.countByExample(ccexample2);
			
			System.out.println("=========================size="+cclist.size());
			System.out.println("=========================count="+count);
			
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("comment_ls", cclist);
			json.put("comment_count", count);
			form.setJsonMsg("success",true,json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 添加圈子详细的  回复信息
	 * @return
	 */
	public String addComment(){
		try {
			//添加圈子详细 评论
			UserInfo userinfo = getCurrentUser();
			CircleCommentInfo cc = new CircleCommentInfo();
			cc.setCcid(UUID.randomUUID()+"");
			cc.setCircledetailid(form.getId());
			cc.setCommentinfo(form.getContent());
			cc.setCreatedate(new Date());
			cc.setUserid(userinfo.getUserid());
			int res = circleCommentInfoService.insert(cc);
			System.out.println("res="+res+"==============ids="+form.getIds());
			if(res > 0){
				CircleDetailInfo circledetailinfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
				circledetailinfo.setComcount(circledetailinfo.getComcount()+1);
				circleDetailInfoService.updateByPrimaryKey(circledetailinfo);
			}
			cc.setUserinfo(userinfo);
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("res", res);
			json.put("circleCommetnInfo", cc);
			form.setJsonMsg("success",true,json);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 加入圈子
	 * @return
	 */
	public String joinOrQuitQuanzi(){
		//UserInfo userinfo = getCurrentUser();
		ActionContext ac = ActionContext.getContext(); 
		Map session = ac.getSession();
		UserInfo userinfo = (UserInfo) session.get("user");
		try {
			if(userinfo != null){
				CircleInfo ci = (CircleInfo) circleInfoService.selectByPrimaryKey(form.getId(), CircleInfo.class);
				if(form.getType() == 1){
					UserCircleRlsExample checkRlsExample = new UserCircleRlsExample();
					checkRlsExample.createCriteria().andCircleidEqualTo(form.getId()).andUseridEqualTo(userinfo.getUserid());
					int checkRlsCount = userCircleRlsService.countByExample(checkRlsExample);
					if(checkRlsCount>0)
					{
						form.setJsonMsg("你已经加入该圈子了",false,null);
					}else{
						//加入
						UserCircleRls ucr = new UserCircleRls();
						ucr.setCircleid(form.getId());
						ucr.setJoindatetime(new Date());
						ucr.setJtype("1");
						ucr.setUcid(UUID.randomUUID()+"");
						ucr.setUcRole(3);
						ucr.setUserid(userinfo.getUserid());
						int res = userCircleRlsService.insert(ucr);
						ci.setJoincount(ci.getJoincount()+1);
						form.setJsonMsg("success",true,res);
						
						Inform inform = new Inform();
						inform.setIfid(UUID.randomUUID()+"");
						inform.setCircleid(form.getId());
						inform.setUserid(userinfo.getUserid());
						inform.setTouserid(ci.getUserid());
						inform.setInformtype(1);
						inform.setCreatetime(new Date());
						inform.setIsread(null);
						informService.insert(inform);
						
						UserCircleRlsExample userRlsExample = new UserCircleRlsExample();
						userRlsExample.createCriteria().andCircleidEqualTo(form.getId());
						List<UserCircleRls> userRls = userCircleRlsService.selectByExample(userRlsExample);
						for (UserCircleRls userCircleRls : userRls) {
							if(userCircleRls.getUserid().equals(userinfo.getUserid())){continue;}
							Inform inform1 = new Inform();
							inform1.setIfid(UUID.randomUUID()+"");
							inform1.setCircleid(form.getId());
							inform1.setUserid(userinfo.getUserid());
							inform1.setInformtype(1);
							inform1.setCreatetime(new Date());
							inform1.setIsread(null);
							inform1.setTouserid(userCircleRls.getUserid());
							informService.insert(inform1);
						}
					}
					
				}else if(form.getType() == 2){
					//退出
					UserCircleRlsExample ucrexample = new UserCircleRlsExample();
					ucrexample.createCriteria().andCircleidEqualTo(form.getId()).andUseridEqualTo(userinfo.getUserid());
					int res = userCircleRlsService.deleteByExample(ucrexample);
					if(res>0)
					{
						ci.setJoincount(ci.getJoincount()-1);
					}
					form.setJsonMsg("success",true,res);
				}else{
					form.setJsonMsg("type is noll",false,null);
				}
				circleInfoService.updateByPrimaryKey(ci);
			}else{
				//未登录 登录
				form.setJsonMsg("notlogin",false,null);
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		}
		
		return JSON_PAGE;
	}
	
	/**
	 * 根据圈子id查询该圈子成员
	 * @return
	 */
	public String getMemberByCid(){
		try {
			UserCircleRlsExample ucrexample = new UserCircleRlsExample();
			ucrexample.createCriteria().andCircleidEqualTo(form.getId());
			int count = userCircleRlsService.countByExample(ucrexample);
			ucrexample.setOrderByClause("uc_role asc");
			ucrexample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			ucrexample.setRowsPerPage(form.getPageSize());
			List<UserCircleRls> ucrls = userCircleRlsService.selectByExample(ucrexample);
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("ucr_ls", ucrls);
			json.put("count", count);
			form.setJsonMsg("success",true,json);
		} catch (BusinessException e) {
			form.setJsonMsg("fail",false,null);
			e.printStackTrace();
		} catch (Exception e) {
			form.setJsonMsg("fail",false,null);
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	/**
	 * 添加圈子详细访问数量
	 * @return
	 */
	public String addVisitCount(){
		try {
			CircleDetailInfo circledetailinfo = (CircleDetailInfo) circleDetailInfoService.selectByPrimaryKey(form.getId(), CircleDetailInfo.class);
			circledetailinfo.setVisitcount(circledetailinfo.getVisitcount()+1);
			int res = circleDetailInfoService.updateByPrimaryKey(circledetailinfo);
			Map<String,Object> json = new HashMap<String,Object>();
			json.put("res", res);
			form.setJsonMsg("success",true,json);
		} catch (BusinessException e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		}
		return JSON_PAGE;
	}
	public String searchTopic()
	{
		try {
//			CircleInfoExample cexample = new CircleInfoExample();
//			cexample.createCriteria().andCirclenameLike("%"+form.getContent()+"%");
//			cexample.createCriteria().andSummaryLike("%"+form.getContent()+"%");
//			cexample.setOrderByClause("createDateTime desc");
//			cexample.setDistinct(true);
			MyVoteInfoExample myVoteInfoExample = new MyVoteInfoExample();
			myVoteInfoExample.createCriteria().andSummaryLike("%"+form.getContent()+"%");
			myVoteInfoExample.createCriteria().andTitleLike("%"+form.getImage()+"%");
			
		} catch (Exception e) {
			e.printStackTrace();
			form.setJsonMsg("fail",false,null);
		}
		return JSON_PAGE;
	}
	
	/**
	 * 读取通知信息 
	 */
	public String getInform(){
		try{
			Map<String,Object> json = new HashMap<String,Object>();
			
			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull();
			informExample.setOrderByClause(" createTime desc");
			List<Inform> inform = informService.selectByExample(informExample);
			json.put("inform", inform);
			int count = informService.countByExample(informExample);
			json.put("count", count);
			
			form.setJsonMsg("success",true,json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 更改inform表中的isread字段 并清楚session中的informCount属性
	 * 11-23
	 */
	public String changeInform(){
		try{
			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull();
			informExample.setOrderByClause(" createTime desc ");
			Inform in = new Inform();
			in.setIsread(1);
			informService.updateByExampleSelective(in, informExample);
			
			getSession().removeAttribute("joinCircleCount");
			getSession().removeAttribute("pinglunCircleCount");
			getSession().removeAttribute("topicPinglunCount");
			getSession().removeAttribute("gzTopicCount");
			form.setJsonMsg("success",true,null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON_PAGE;
	}
	
	public String addFavorite(){
		try{
			UserInfo user = getCurrentUser();
			FavoriteExample favoriteExample = new FavoriteExample();
			favoriteExample.createCriteria().andUseridEqualTo(user.getUserid()).andCircleidEqualTo(form.getId());
		    List<Favorite> favoriteList = favoriteService.selectByExample(favoriteExample);
		    if(favoriteList.size() > 0){
		    	form.setJsonMsg("已经收藏过了", false, null);
		    }else{
		    	Favorite favorite = new Favorite();
		    	favorite.setCircleid(form.getId());
		    	favorite.setFdatetime(new Date());
		    	favorite.setFid(UUID.randomUUID()+"");
		    	favorite.setUserid(user.getUserid());
		    	int r = favoriteService.insert(favorite);
		    	if(r==1){
		    		form.setJsonMsg("收藏成功", true, null);
		    	}
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON_PAGE;
	}
	
	public String moreCircleFormTag(){
		try{
			Map<String,Object> json = new HashMap<String,Object>();
			boolean islogin = false;
			if(getCurrentUser() != null){
				islogin = true;
			}
			json.put("islogin",islogin);
			
			CircleInfoExample cexample = new CircleInfoExample();
			cexample.createCriteria().andDef3EqualTo(form.getId());
			cexample.setOrderByClause(" csort desc");
			List<CircleInfo> clist = circleInfoService.selectByExample(cexample);
			json.put("morecircleList",clist);
			form.setJsonMsg("success", true, json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public Object getModel() {
		return form;
	}

}
