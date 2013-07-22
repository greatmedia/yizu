package com.yizu.proj.sys.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.renren.api.client.utils.Md5Utils;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.config.AppParametersConfig;
import com.yizu.proj.convert.DateConverter;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.sys.action.form.PhoneFeiwenForm;
import com.yizu.proj.sys.beans.CircleDetailInfo;
import com.yizu.proj.sys.beans.CircleDetailInfoExample;
import com.yizu.proj.sys.beans.FeiWen;
import com.yizu.proj.sys.beans.FeiWenExample;
import com.yizu.proj.sys.beans.FeiWenType;
import com.yizu.proj.sys.beans.FeiWenTypeExample;
import com.yizu.proj.sys.beans.Gztopics;
import com.yizu.proj.sys.beans.GztopicsExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.Userloginlog;
import com.yizu.proj.sys.beans.gen.UserInfoExampleBase;
import com.yizu.proj.sys.service.FeiWenService;
import com.yizu.proj.sys.service.FeiWenTypeService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.UserloginlogService;
import com.yizu.proj.utils.DateUtil;
import com.yizu.proj.utils.FeiwenResultCode;

@Scope("prototype")
@Controller("phoneFeiwenAction")
public class PhoneFeiwenAction extends BaseAction implements ModelDriven{
	private PhoneFeiwenForm form = new PhoneFeiwenForm();
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserloginlogService userloginlogService;
	@Autowired
	private FeiWenService feiWenService;
	@Autowired
	private FeiWenTypeService feiWenTypeService;
	public String userLogin()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!StringUtils.equals(Md5Utils.md5(StringUtils.trimToEmpty(form.getPwd())), StringUtils.trimToEmpty(currUserInfo.getPassword()))) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_SUCCESS);
			currUserInfo.setOtheraccountuserimage(currUserInfo.getImage());
			setCurrentUser(currUserInfo);
			
			/**插入用户登录日志*/
    		UserInfo user = getCurrentUser();
    		Userloginlog userloginlog = new Userloginlog();
			userloginlog.setLogid(String.valueOf(new Date().getTime()));
			userloginlog.setUserid(user.getUserid());
			userloginlog.setLogdatetime(new Date());
			userloginlog.setIp(form.getIp());
			userloginlog.setDef1(form.getLogtype());
			userloginlog.setDef2("0");
			userloginlog.setDef3("0");
			userloginlog.setDef4("0");
			int reuserlog = userloginlogService.insert(userloginlog);
			json.put(FeiwenResultCode.USER, user);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String userMD5Login()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getUser().getEmail()).andPasswordEqualTo(form.getUser().getPassword());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_SUCCESS);
			currUserInfo.setOtheraccountuserimage(currUserInfo.getImage());
			setCurrentUser(currUserInfo);
			
			/**插入用户登录日志*/
    		UserInfo user = getCurrentUser();
    		Userloginlog userloginlog = new Userloginlog();
			userloginlog.setLogid(String.valueOf(new Date().getTime()));
			userloginlog.setUserid(user.getUserid());
			userloginlog.setLogdatetime(new Date());
			userloginlog.setIp(form.getIp());
			userloginlog.setDef1(form.getLogtype());
			userloginlog.setDef2("0");
			userloginlog.setDef3("0");
			userloginlog.setDef4("0");
			int reuserlog = userloginlogService.insert(userloginlog);
			json.put(FeiwenResultCode.USER, user);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String feiwenType()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenTypeExample feiWenTypeExample = new FeiWenTypeExample();
			feiWenTypeExample.setOrderByClause(" tid asc ");
			List<FeiWenType> feiWenTypes = feiWenTypeService.selectByExample(feiWenTypeExample);
			json.put("types", feiWenTypes);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String feiwen()
	{
		
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			feiWenExample.setRowsPerPage(form.getPageSize());
			feiWenExample.setOrderByClause(" createdatetime desc ");
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
		
	}
	/**
	 * 首页
	 * @return
	 */
	public String indexFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenTypeExample feiWenTypeExample = new FeiWenTypeExample();
			feiWenTypeExample.setOrderByClause(" createdatetime desc ");
			List<FeiWenType> feiWenTypes = feiWenTypeService.selectByExample(feiWenTypeExample);
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
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENTYPES, feiWenTypes);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String newFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");            
			feiWenExample.createCriteria().andCreatedatetimeBetween(fmt.parse(form.getId()), new Date());
			feiWenExample.setOrderByClause(" createdatetime desc ");
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(50);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeGreaterFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andDef1GreaterThan(form.getId()).andTidEqualTo(form.getTid());
			feiWenExample.setOrderByClause(" createdatetime asc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(1);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeLessFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andDef1LessThan(form.getId()).andTidEqualTo(form.getTid());
			feiWenExample.setOrderByClause(" createdatetime desc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(1);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeGreaterDateFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			//feiWenExample.createCriteria().andDef1GreaterThan("").andTidEqualTo(form.getTid());
			feiWenExample.createCriteria().andTidEqualTo(form.getTid()).andCreatedatetimeGreaterThan(tempDate.parse(form.getId()));
			feiWenExample.setOrderByClause(" createdatetime desc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart((form.getPageNum()-1));
			feiWenExample.setRowsPerPage(form.getPageSize());
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeLessDateFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			//feiWenExample.createCriteria().andDef1LessThan(form.getId()).andTidEqualTo(form.getTid());
			feiWenExample.createCriteria().andTidEqualTo(form.getTid()).andCreatedatetimeLessThan(tempDate.parse(form.getId()));
			feiWenExample.setOrderByClause(" createdatetime desc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart((form.getPageNum()-1));
			feiWenExample.setRowsPerPage(form.getPageSize());
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeGreaterSdfFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			//feiWenExample.createCriteria().andDef1GreaterThan("").andTidEqualTo(form.getTid());
			long t=Long.valueOf(form.getId());
			Date date =	new Date();
			date.setTime(t);
			feiWenExample.createCriteria().andTidEqualTo(form.getTid()).andCreatedatetimeGreaterThan(date);
			feiWenExample.setOrderByClause(" createdatetime asc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(1);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeLessSdfFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			long t=Long.valueOf(form.getId());
			Date date =	new Date();
			date.setTime(t);
			//feiWenExample.createCriteria().andDef1LessThan(form.getId()).andTidEqualTo(form.getTid());
			feiWenExample.createCriteria().andTidEqualTo(form.getTid()).andCreatedatetimeLessThan(date);
			feiWenExample.setOrderByClause(" createdatetime desc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(1);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String typeIndexFeiwen()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andTidEqualTo(form.getTid());
			feiWenExample.setOrderByClause(" createdatetime desc ");
			int count = feiWenService.countByExample(feiWenExample);
			json.put("count", count);
			feiWenExample.setLimitStart(0);
			feiWenExample.setRowsPerPage(1);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			System.out.println(feiWens.size());
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**根据标题搜索*/
	public String sfeiwenByTitle()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**根据标题搜索*/
			FeiWenExample feiWenExample = new FeiWenExample();
			System.out.println(form.getContent());
			feiWenExample.createCriteria().andTitleLike("%"+form.getContent()+"%");
			
			/**得到总数量*/
			int resultcount = feiWenService.countByExample(feiWenExample);
			if(resultcount<1)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SEARCH_NULL);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			/**用分页查询数据、得到搜索内容*/
			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			feiWenExample.setRowsPerPage(form.getPageSize());
			feiWenExample.setDistinct(true);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			if(feiWens.size()<1)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ROW_NULL);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**根据分类标题搜索*/
	public String sfeiwenByTidandTitle()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**根据标题搜索*/
			FeiWenExample feiWenExample = new FeiWenExample();
			System.out.println(form.getContent());
			feiWenExample.createCriteria().andTitleLike("%"+form.getContent()+"%").andTidEqualTo(form.getId());
			
			/**得到总数量*/
			int resultcount = feiWenService.countByExample(feiWenExample);
			if(resultcount<1)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SEARCH_NULL);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put("resultcount", resultcount);
			
			/**用分页查询数据、得到搜索内容*/
			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			feiWenExample.setRowsPerPage(form.getPageSize());
			feiWenExample.setDistinct(true);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			if(feiWens.size()<1)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ROW_NULL);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**
	 * 根据飞闻类型查询数据
	 * @return
	 */
	public String seiwenBytype()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			/**根据标题搜索*/
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andTidEqualTo(form.getTid());
			/**得到总数量*/
			int resultcount = feiWenService.countByExample(feiWenExample);
			if(resultcount<1)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.TYPE_NULL);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put(FeiwenResultCode.COUNT, resultcount);
			/**用分页查询数据、得到搜索内容*/
			System.out.println(form.getPageSize());
			System.out.println(form.getPageNum());
			System.out.println(form.getTid());
			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			feiWenExample.setRowsPerPage(form.getPageSize());
			feiWenExample.setDistinct(true);
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			if(feiWens.size()<1)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ROW_NULL);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**
	 * 检查授权ID是否存在
	 * @return
	 */
	public String checkOauthID()
	{
		//http://localhost:8070/yizu/phoneFeiwenAction_checkOauthID.do?oaid=3100DA1C348BAB5C174573C62582DE19&oatype
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample userInfoExample = new UserInfoExample();
			userInfoExample.createCriteria().andOtheraccountEqualTo(form.getOaid());
			List<UserInfo> userInfos = userInfoService.selectByExample(userInfoExample);
			if(userInfos.size()>0)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHE);
				json.put(FeiwenResultCode.USER, userInfos.get(0));
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}else{
				UserInfo userInfo  = new UserInfo();
				userInfo.setEmail("");
        		userInfo.setPassword("");
                userInfo.setOtheraccount(form.getOaid());
                userInfo.setOtheraccountflag(form.getOatype());
                userInfo.setNick("");
                userInfo.setOtheraccountuserimage("");
        		userInfo.setRegistertime(DateUtil.getCurrentTime());
        		userInfo.setRegisterip(form.getIp());
        		userInfo.setLogintime(DateUtil.getCurrentTime());
        		userInfo.setLoginip(form.getIp());
        		userInfo.setSex("");
        		userInfo.setWhat("");
        		userInfo.setPhone("");
        		userInfo.setMobile("");
        		userInfo.setTownid(0);
        		userInfo.setPostalcode("");
        		userInfo.setDeliverytypeid(0);
        		userInfo.setDeliverytimeid(0);
        		userInfo.setPaytypeid(0);
        		userInfo.setUsermoney(0f);
        		userInfo.setBuyscore(0);
        		userInfo.setActivationcode("");
        		userInfo.setActivationdate("");
        		userInfo.setFirstname("");
        		userInfo.setLastname("");
        		userInfo.setHobby("");
        		userInfo.setDeclaration("");
        		userInfo.setBirthday("");
        		userInfo.setDegreeid(0);
        		userInfo.setIncometypeid(0);
        		userInfo.setJoinstatus(0);
        		userInfo.setOnlineflag(true);
        		userInfo.setTopicnumber(0);
        		userInfo.setVotenumber(0);
        		userInfo.setFansnumber(0);
        		userInfo.setFollownumber(0);
        		userInfo.setUserlevel(0);
        		userInfo.setMembersflag(true);
        		userInfo.setActivationcodejoincommunity("");
        		userInfo.setWebsite("");
        		userInfo.setFood("");
        		userInfo.setBooks("");
        		userInfo.setPlot("");
        		userInfo.setPrograms("");
        		userInfo.setEntertainment("");
        		userInfo.setTourist("");
        		userInfo.setDigital("");
        		userInfo.setSports("");
        		userInfo.setNewspapers("");
        		userInfo.setBgid("");
        		userInfo.setCommunitytownid(0);
        		userInfo.setPersonality("");
        		userInfo.setFigure("");
        		userInfo.setDressstyle("");
        		userInfo.setInterests("");
        		userInfo.setTraveldestination("");
        		userInfo.setLovetoreadmagazinesandbooks("");
        		userInfo.setOftengotowebsite("");
        		userInfo.setLikestar("");
        		userInfo.setGoodatsports("");
        		userInfo.setLikemovie("");
        		userInfo.setLikefood("");
        		userInfo.setLikemakingfriends("");
        		userInfo.setLastactiontime("");
        		userInfo.setName("");
        		userInfo.setImage("");
        		userInfo.setAddress("");
        		int re = userInfoService.insert(userInfo);
        		json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
        		if(re>0)
        		{
        			//json.put(FeiwenResultCode.USER, userInfo);
//        			UserInfoExample userExample = new UserInfoExample();
        			//userInfoExample.createCriteria().andOtheraccountEqualTo(form.getOaid());
        			List<UserInfo> users = userInfoService.selectByExample(userInfoExample);
        			setCurrentUser(users.get(0));
        			UserInfo u = getCurrentUser();
        			String cid = u.getOtheraccount();
        			int uid = u.getUserid();
        			json.put(FeiwenResultCode.USER, u);
        		}
        		form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	//http://localhost:8070/yizu/phoneFeiwenAction_updateOauthNick.do?oaid=123&user.nick=longliuping
	public String updateOauthNick()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setNick(form.getUser().getNick());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthSex()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setSex(form.getUser().getSex());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthEmailPwd()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				int el  = user.getEmail().length();
				int pl = user.getPassword().length();
				System.out.println("");
				
				if(user.getPassword().length()<=0&&user.getEmail().length()<=0)
				{
					UserInfoExample exampleis = new UserInfoExample();
					exampleis.createCriteria().andEmailEqualTo(form.getUser().getEmail());
					List<UserInfo> listis = userInfoService.selectByExample(exampleis);
					if (listis == null || listis.isEmpty() || listis.size()<1) 
					{
						user.setEmail(form.getUser().getEmail());
						user.setPassword(Md5Utils.md5(form.getUser().getPassword()));
						int res = userInfoService.updateByPrimaryKey(user);
						if(res>0)
						{
							json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
							json.put(FeiwenResultCode.USER, user);
						}else{
							json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
						}
					}else{
						json.put(FeiwenResultCode.RESULT, FeiwenResultCode.EMAILEX);
					}
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
				}
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthEmail()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setEmail(form.getUser().getEmail());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthImage()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setImage(form.getUser().getImage());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
				form.setJsonMsg("success", true, json);
			}
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthImageOauth()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setOtheraccountuserimage(form.getUser().getOtheraccountuserimage());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthName()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setName(form.getUser().getName());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateOauthWhat()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andOtheraccountEqualTo(form.getOaid()).andUseridEqualTo(form.getUser().getUserid());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if(list.size()>0)
			{
				UserInfo user = list.get(0);
				user.setWhat(form.getUser().getWhat());
				int res = userInfoService.updateByPrimaryKey(user);
				if(res>0)
				{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
					json.put(FeiwenResultCode.USER, user);
				}else{
					json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
				}
				setCurrentUser(user);
				setCurrentUser(user);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.OAUTHN);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String register()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		String email = form.getEmail();
		if(email.length()<1)
		{
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.EMAIL_ERROR);
			form.setJsonMsg("success", true, json);
			return JSON_PAGE;
		}
		String pwd = form.getPwd();
		if(pwd.length()<1)
		{
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.PWD_ERROR);
			form.setJsonMsg("success", true, json);
			return JSON_PAGE;
		}
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty() || list.size()<1) {
				UserInfo userInfo  = new UserInfo();
				userInfo.setEmail(form.getEmail());
        		userInfo.setPassword(Md5Utils.md5(form.getPwd()));
                userInfo.setOtheraccount(form.getOaid());
                userInfo.setOtheraccountflag(form.getOatype());
                userInfo.setNick("");
                userInfo.setOtheraccountuserimage("");
        		userInfo.setRegistertime(DateUtil.getCurrentTime());
        		userInfo.setRegisterip(form.getIp());
        		userInfo.setLogintime(DateUtil.getCurrentTime());
        		userInfo.setLoginip(form.getIp());
        		userInfo.setSex("");
        		userInfo.setWhat("");
        		userInfo.setPhone("");
        		userInfo.setMobile("");
        		userInfo.setTownid(0);
        		userInfo.setPostalcode("");
        		userInfo.setDeliverytypeid(0);
        		userInfo.setDeliverytimeid(0);
        		userInfo.setPaytypeid(0);
        		userInfo.setUsermoney(0f);
        		userInfo.setBuyscore(0);
        		userInfo.setActivationcode("");
        		userInfo.setActivationdate("");
        		userInfo.setFirstname("");
        		userInfo.setLastname("");
        		userInfo.setHobby("");
        		userInfo.setDeclaration("");
        		userInfo.setBirthday("");
        		userInfo.setDegreeid(0);
        		userInfo.setIncometypeid(0);
        		userInfo.setJoinstatus(0);
        		userInfo.setOnlineflag(true);
        		userInfo.setTopicnumber(0);
        		userInfo.setVotenumber(0);
        		userInfo.setFansnumber(0);
        		userInfo.setFollownumber(0);
        		userInfo.setUserlevel(0);
        		userInfo.setMembersflag(true);
        		userInfo.setActivationcodejoincommunity("");
        		userInfo.setWebsite("");
        		userInfo.setFood("");
        		userInfo.setBooks("");
        		userInfo.setPlot("");
        		userInfo.setPrograms("");
        		userInfo.setEntertainment("");
        		userInfo.setTourist("");
        		userInfo.setDigital("");
        		userInfo.setSports("");
        		userInfo.setNewspapers("");
        		userInfo.setBgid("");
        		userInfo.setCommunitytownid(0);
        		userInfo.setPersonality("");
        		userInfo.setFigure("");
        		userInfo.setDressstyle("");
        		userInfo.setInterests("");
        		userInfo.setTraveldestination("");
        		userInfo.setLovetoreadmagazinesandbooks("");
        		userInfo.setOftengotowebsite("");
        		userInfo.setLikestar("");
        		userInfo.setGoodatsports("");
        		userInfo.setLikemovie("");
        		userInfo.setLikefood("");
        		userInfo.setLikemakingfriends("");
        		userInfo.setLastactiontime("");
        		userInfo.setName("");
        		userInfo.setImage("");
        		userInfo.setAddress("");
        		int re = userInfoService.insert(userInfo);
        		if(re>0)
        		{
        			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.REGISTER_SUCCESS);
        			List<UserInfo> registAfterUserInfoList = userInfoService.selectByExample(example);
        			if(registAfterUserInfoList.size()>0)
        			{
        				setCurrentUser(registAfterUserInfoList.get(0));
            			try {
            				/**插入用户登录日志*/
            	    		UserInfo user = getCurrentUser();
            	    		Userloginlog userloginlog = new Userloginlog();
            				userloginlog.setLogid(String.valueOf(new Date().getTime()));
            				userloginlog.setUserid(user.getUserid());
            				userloginlog.setLogdatetime(new Date());
            				userloginlog.setIp(form.getIp());
            				userloginlog.setDef1("0");
            				userloginlog.setDef2("0");
            				userloginlog.setDef3("0");
            				userloginlog.setDef4("0");
            				int reuserlog = userloginlogService.insert(userloginlog);
    					} catch (Exception e) {
    						// TODO: handle exception
    						e.printStackTrace();
    					}
            			json.put(FeiwenResultCode.USER, getCurrentUser());
        			}
        		}else{
        			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.REGISTER_FAIL);
        		}
				form.setJsonMsg("success", true, json);
			} else {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.EMAILEX);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**
	 * 获取飞闻内容
	 * @return
	 */
	public String content()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWen feiwen = (FeiWen) feiWenService.selectByPrimaryKey(form.getId(), FeiWen.class);
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWEN, feiwen);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**
	 * 上传图片
	 * @return
	 */
	public String uploadImage()
	{
		Map<String, Object> json = new HashMap<String, Object>();
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
				//form.setJsonMsg("上传成功！", true, "uploadPath/tmp/" + newName.toString());
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
				json.put(FeiwenResultCode.IMAGE_PATH, "uploadPath/tmp/" + newName.toString());
				form.setJsonMsg("success", true, json);
			} else {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
				form.setJsonMsg("success", true, json);
			}

		} catch (Exception e) {
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateNick()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getUser().getEmail()).andPasswordEqualTo(form.getUser().getPassword());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!(currUserInfo.getPassword().equals(form.getUser().getPassword()))) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			currUserInfo.setNick(form.getUser().getNick());
			int res = userInfoService.updateByPrimaryKey(currUserInfo);
			if(res>0)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
				json.put(FeiwenResultCode.USER, currUserInfo);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
			}
			
			setCurrentUser(currUserInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateName()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getUser().getEmail()).andPasswordEqualTo(form.getUser().getPassword());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!(currUserInfo.getPassword().equals(form.getUser().getPassword()))) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			currUserInfo.setName(form.getUser().getName());
			int res = userInfoService.updateByPrimaryKey(currUserInfo);
			if(res>0)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
				json.put(FeiwenResultCode.USER, currUserInfo);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
			}
			
			setCurrentUser(currUserInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateWhat()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getUser().getEmail()).andPasswordEqualTo(form.getUser().getPassword());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!(currUserInfo.getPassword().equals(form.getUser().getPassword()))) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			currUserInfo.setWhat(form.getUser().getWhat());
			int res = userInfoService.updateByPrimaryKey(currUserInfo);
			if(res>0)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
				json.put(FeiwenResultCode.USER, currUserInfo);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
			}
			
			setCurrentUser(currUserInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateSex()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getUser().getEmail()).andPasswordEqualTo(form.getUser().getPassword());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!(currUserInfo.getPassword().equals(form.getUser().getPassword()))) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			String sex = form.getUser().getSex().toString();
			currUserInfo.setSex(form.getUser().getSex());
			int res = userInfoService.updateByPrimaryKey(currUserInfo);
			if(res>0)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
				json.put(FeiwenResultCode.USER, currUserInfo);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
			}
			
			setCurrentUser(currUserInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String updateImage()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getUser().getEmail()).andPasswordEqualTo(form.getUser().getPassword());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);
			setCurrentUser(currUserInfo);
			if (!(currUserInfo.getPassword().equals(form.getUser().getPassword()))) {
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.LOGIN_EMAIL_PWD_ERROR);
				form.setJsonMsg("success", true, json);
				return JSON_PAGE;
			}
			String imageName = form.getUser().getImage();
            if (imageName.indexOf("/") != -1) {
             	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
			}
        	String currDate = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
    	   	File userImagePath = new File(AppParametersConfig.getParameter("file.userInfo.imagePath")+currDate+"/");
    	   	if(!userImagePath.exists())
  	   		{
    	   		userImagePath.mkdirs();
  	   		}
    	   	File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
            FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.userInfo.imagePath")+currDate+"/"+imageName));
            getCurrentUser().setImage("uploadPath/user/imagePath/" +currDate+"/"+ imageName);
			int res = userInfoService.updateByPrimaryKey(currUserInfo);
			if(res>0)
			{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_SUCCESS);
				json.put(FeiwenResultCode.USER, currUserInfo);
			}else{
				json.put(FeiwenResultCode.RESULT, FeiwenResultCode.UPDATE_FAIL);
			}
			setCurrentUser(currUserInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	/**
	 * 验证邮箱是否已经注册
	 * @return
	 */
	private boolean checkEmailNotExists() {
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty() || list.size()<1) {
				return true;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return false;
		}
		return false;
	}
	public String feiwenHtml()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			feiWenExample.setRowsPerPage(form.getPageSize());
			feiWenExample.setOrderByClause(" createdatetime desc ");
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String feiwenTypePageGreater(){
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			feiWenExample.setOrderByClause(" createdatetime desc ");
			feiWenExample.createCriteria().andCreatedatetimeGreaterThan(fmt.parse(form.getId())).andTidEqualTo(form.getTid());;
//			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
//			feiWenExample.setRowsPerPage(form.getPageSize());
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public String feiwenTypePageLess()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			FeiWenExample feiWenExample = new FeiWenExample();
			feiWenExample.createCriteria().andTidEqualTo(form.getTid());
			feiWenExample.setOrderByClause(" createdatetime desc ");
			feiWenExample.setLimitStart((form.getPageNum()-1)*form.getPageSize());
			feiWenExample.setRowsPerPage(form.getPageSize());
			List<FeiWen> feiWens = feiWenService.selectByExample(feiWenExample);
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.SUCCESS);
			json.put(FeiwenResultCode.FEIWENS, feiWens);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
			json.put(FeiwenResultCode.RESULT, FeiwenResultCode.ERROR);
			form.setJsonMsg("success", true, json);
		}
		return JSON_PAGE;
	}
	public Object getModel() {
		// TODO Auto-generated method stub
		return form;
	}
}
