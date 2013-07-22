package com.yizu.proj.sys.action;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.renren.api.client.utils.Md5Utils;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.config.AppParametersConfig;
import com.yizu.proj.exception.BusinessException;
import com.yizu.proj.sys.action.form.UserInfoForm;
import com.yizu.proj.sys.beans.City;
import com.yizu.proj.sys.beans.CityExample;
import com.yizu.proj.sys.beans.InformExample;
import com.yizu.proj.sys.beans.Province;
import com.yizu.proj.sys.beans.ProvinceExample;
import com.yizu.proj.sys.beans.TfindPwdLog;
import com.yizu.proj.sys.beans.TfindPwdLogExample;
import com.yizu.proj.sys.beans.Town;
import com.yizu.proj.sys.beans.TownExample;
import com.yizu.proj.sys.beans.UserEmaillog;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.Userloginlog;
import com.yizu.proj.sys.service.CityService;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.ProvinceService;
import com.yizu.proj.sys.service.TfindPwdLogService;
import com.yizu.proj.sys.service.TownService;
import com.yizu.proj.sys.service.UserEmaillogService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.UserloginlogService;
import com.yizu.proj.util.mail.MailSenderInfo;
import com.yizu.proj.util.mail.SimpleMailSender;
import com.yizu.proj.util.mail.UserEail;
import com.yizu.proj.utils.DateUtil;
import com.yizu.proj.utils.ImageUtils;
import com.yizu.proj.utils.OperateImage;

@Scope("prototype")
@Controller("userInfoAction")
public class UserInfoAction extends BaseAction implements ModelDriven {

	private static final long serialVersionUID = -1517391340327305523L;
	private UserInfoForm form = new UserInfoForm();
	private OperateImage image = new OperateImage();
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private TfindPwdLogService tfindPwdLogService;
	@Autowired
	private UserloginlogService userloginlogService;
	@Autowired
	private UserEmaillogService userEmaillogService;
	@Autowired
	private InformService informService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private TownService townService;
	
	/**
	 * 检查用户是否在线
	 * @return
	 */
	public String checkOnUserLine()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			UserInfo userInfo = findUser(Integer.parseInt(form.getId()));
			if(userInfo==null)
			{
				json.put("userline", null);
				form.setJsonMsg("success", false, json);
				return JSON_PAGE;
			}
			boolean islogin = OnLineUserListener.findUserOnLine(Integer.parseInt(form.getId()));
			json.put("userline", islogin);
			json.put("user", userInfo);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 得到所有的在线用户
	 * @return
	 */
	public String allOnUserLinePage()
	{
		List<UserInfo> userLines = new ArrayList<UserInfo>();
		try {
			System.out.println(form.getPageSize());
			System.out.println(form.getPageNum());
			Map<String, Object> json = new HashMap<String, Object>();
			List<Integer> line = OnLineUserListener.onLineUserList;
			for (int i = 0; i < line.size(); i++) {
				UserInfo user = findUser(line.get(i));
				userLines.add(user);
			}
			json.put("userLineCount", userLines.size());
			json.put("userLines", userLines);
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
		
//		List<UserInfo> userLines = new ArrayList<UserInfo>();
//		Map<String, Object> json = new HashMap<String, Object>();
//		try {
//			System.out.println(form.getPageSize());
//			System.out.println(form.getPageNum());
//			List<Integer> line = OnLineUserListener.onLineUserList;
//			if(line.size()>0)
//			{
//				for (int i = (form.getPageNum()-1); i < form.getPageSize(); i++) {
//					UserInfo user = findUser(line.get(i));
//					userLines.add(user);
//				}
//			}
//			json.put("userLineCount", userLines.size());
//			json.put("userLines", userLines);
//			form.setJsonMsg("success", true, json);
//		} catch (Exception e) {
//			e.printStackTrace();
//			json.put("userLineCount", userLines.size());
//			json.put("userLines", userLines);
//			form.setJsonMsg("success", true, json);
//			return JSON_PAGE;
//		}
//		return JSON_PAGE;
	}
	/**
	 * 用户登录
	 * @return
	 */
	public String loginLine()
	{
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			System.out.println(form.getId());
			System.out.println(form.getId());
			UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(Integer.parseInt(form.getId()), UserInfo.class);
			if(user!=null)
			{
				json.put("result", true);
				json.put("user", user);
				setCurrentUser(user);
			}else{
				json.put("result", false);
			}
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 获取用户资料
	 * @param uid 用户ID
	 * @return
	 */
	public UserInfo findUser(int uid)
	{
		UserInfo user = new UserInfo();
		try {
			user = (UserInfo) userInfoService.selectByPrimaryKey(uid, UserInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
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
	 * 
	 * @return
	 */
	public String regLogin(){
		try{
			if (StringUtils.isBlank(form.getEmail())) {
				form.setJsonMsg("请输入邮箱！", false, null, 4);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getPwd())) {
				form.setJsonMsg("请输入密码！", false, null, 5);
				return JSON_PAGE;
			}
			if(form.getPwd().length()<6){
				getSession().setAttribute("rname", form.getName());
				getSession().setAttribute("remail", form.getEmail());
				form.setJsonMsg("密码不能少于6位", false, null, 2);
				return JSON_PAGE;
			}
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list.size()>0) {
				form.setJsonMsg("邮箱已注册", false, null, 1);
				return JSON_PAGE;
			}
			 UserInfo userInfo  = new UserInfo();
			 userInfo.setNick(form.getName());
			 userInfo.setName(form.getName());
			 userInfo.setEmail(form.getEmail());
			 userInfo.setPassword(Md5Utils.md5(form.getPwd()));
			 userInfo.setRegistertime(DateUtil.getCurrentTime());
             userInfo.setRegisterip(form.getIp());
             userInfo.setLogintime(DateUtil.getCurrentTime());
             userInfo.setLoginip(form.getIp());
             
             userInfo.setUsermoney(Float.valueOf("0.00"));
             userInfo.setBuyscore(0);
             userInfo.setTopicnumber(0);
             userInfo.setVotenumber(0);
             userInfo.setFollownumber(0);
             userInfo.setOnlineflag(true);
			
			
           
//    		userInfo.setRegistertime(DateUtil.getCurrentTime());
//    		userInfo.setRegisterip("");
//    		userInfo.setLogintime(DateUtil.getCurrentTime());
//    		userInfo.setLoginip("");
//    		userInfo.setSex(false);
//    		userInfo.setWhat("");
//    		userInfo.setPhone("");
//    		userInfo.setMobile("");
             
//    		userInfo.setTownid(0);
//    		userInfo.setPostalcode("");
//    		userInfo.setDeliverytypeid(0);
//    		userInfo.setDeliverytimeid(0);
//    		userInfo.setPaytypeid(0);
//    		userInfo.setUsermoney(0f);
//    		userInfo.setBuyscore(0);
             
//    		userInfo.setActivationcode("");
//    		userInfo.setActivationdate("");
//    		userInfo.setFirstname("");
//    		userInfo.setLastname("");
//    		userInfo.setHobby("");
//    		userInfo.setDeclaration("");
//    		userInfo.setBirthday("");
//    		userInfo.setDegreeid(0);
//    		userInfo.setIncometypeid(0);
//    		userInfo.setJoinstatus(0);
//    		userInfo.setOnlineflag(true);
//    		userInfo.setTopicnumber(0);
//    		userInfo.setVotenumber(0);
//    		userInfo.setFansnumber(0);
//    		userInfo.setFollownumber(0);
//    		userInfo.setUserlevel(0);
//    		userInfo.setMembersflag(true);
//    		userInfo.setActivationcodejoincommunity("");
//    		userInfo.setWebsite("");
//    		userInfo.setFood("");
//    		userInfo.setBooks("");
//    		userInfo.setPlot("");
//    		userInfo.setPrograms("");
//    		userInfo.setEntertainment("");
//    		userInfo.setTourist("");
//    		userInfo.setDigital("");
//    		userInfo.setSports("");
//    		userInfo.setNewspapers("");
//    		userInfo.setBgid("");
//    		userInfo.setCommunitytownid(0);
//    		userInfo.setPersonality("");
//    		userInfo.setFigure("");
//    		userInfo.setDressstyle("");
//    		userInfo.setInterests("");
//    		userInfo.setTraveldestination("");
//    		userInfo.setLovetoreadmagazinesandbooks("");
//    		userInfo.setOftengotowebsite("");
//    		userInfo.setLikestar("");
//    		userInfo.setGoodatsports("");
//    		userInfo.setLikemovie("");
//    		userInfo.setLikefood("");
//    		userInfo.setLikemakingfriends("");
//    		userInfo.setLastactiontime("");
//    		userInfo.setName("");
//    		userInfo.setImage("");
//    		userInfo.setAddress("");
    		
    		int result = userInfoService.insert(userInfo);
    		if(result==1){
    			Map<String, Object> json = new HashMap<String, Object>();
    			List<UserInfo> userlist = userInfoService.selectByExample(example);
    			UserInfo user = userlist.get(0);
    			setCurrentUser(user);
    			json.put("user", user);
    			form.setJsonMsg("注册成功！", true, json, 3);
				return JSON_PAGE;
    		}
    		
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("", false, null);
		}
		return JSON_PAGE;
	}
	public String ajaxLoginForPhone(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			if (StringUtils.isBlank(form.getEmail())) {
				form.setJsonMsg("请输入邮箱！", false, null, 4);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getPwd())) {
				form.setJsonMsg("请输入密码！", false, null, 5);
				return JSON_PAGE;
			}

			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				form.setJsonMsg("您输入的邮箱还未注册，再检查一下吧", false, null, 1);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!StringUtils.equals(Md5Utils.md5(StringUtils.trimToEmpty(form.getPwd())), StringUtils.trimToEmpty(currUserInfo.getPassword()))) {
				form.setJsonMsg("密码错误，请重新输入", false, null, 2);
				return JSON_PAGE;
			}
			currUserInfo.setOtheraccountuserimage(currUserInfo.getImage());
			json.put("user", currUserInfo);
			form.setJsonMsg("登录成功,欢迎你：" + currUserInfo.getNick(), true, json, 3);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("密码错误，请重新输入", false, null);
		}
		return JSON_PAGE;
	}
	
	
	
	public String ajaxLogin() {
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			if (StringUtils.isBlank(form.getEmail())) {
				form.setJsonMsg("请输入邮箱！", false, null, 4);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getPwd())) {
				form.setJsonMsg("请输入密码！", false, null, 5);
				return JSON_PAGE;
			}

			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty()) {
				form.setJsonMsg("您输入的邮箱还未注册，在检查一下吧", false, null, 1);
				return JSON_PAGE;
			}
			UserInfo currUserInfo = list.get(0);

			if (!StringUtils.equals(Md5Utils.md5(StringUtils.trimToEmpty(form.getPwd())), StringUtils.trimToEmpty(currUserInfo.getPassword()))) {
				form.setJsonMsg("密码错误，请重新输入", false, null, 2);
				return JSON_PAGE;
			}
			currUserInfo.setOtheraccountuserimage(currUserInfo.getImage());
			setCurrentUser(currUserInfo);
			
			int userid = currUserInfo.getUserid();
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpServletResponse response = ServletActionContext.getResponse();
			Cookie[] cookie = request.getCookies();
			boolean e = false;
			boolean u = false;
			Cookie ecookie = null;
			Cookie ucookie = null;
			if (cookie != null ) {
				for (int i = 0; i < cookie.length; i++) {
					if (cookie[i].getName().equals("email")) {
						e = true;
						ecookie = cookie[i];
					}
					if (cookie[i].getName().equals("userid")) {
						u = true;
						ucookie = cookie[i];
					}
				}
			}
			if(e){
				ecookie.setDomain(".1mily.com");
				ecookie.setPath("/");
				ecookie.setMaxAge(60*60*24*30);
				response.addCookie(ecookie);
			}else{
				ecookie = new Cookie("email",currUserInfo.getEmail());
				ecookie.setDomain(".1mily.com");
				ecookie.setPath("/");
				ecookie.setMaxAge(60*60*24*30);
				response.addCookie(ecookie);
			}
			
			if(u){
				ucookie.setDomain(".1mily.com");
				ucookie.setPath("/");
				ucookie.setMaxAge(60*60*24*30);
				response.addCookie(ucookie);
			}else{
				ucookie = new Cookie("userid", String.valueOf(userid));
				ucookie.setDomain(".1mily.com");
				ucookie.setPath("/");
				ucookie.setMaxAge(60*60*24*30);
				response.addCookie(ucookie);
			}

			InformExample informExample = new InformExample();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(1);
			informExample.setOrderByClause(" createTime desc");
			int joinCircleCount = informService.countByExample(informExample);//加入圈子记数
			setJoinCircleCount(joinCircleCount+"");
			
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(2);
			informExample.setOrderByClause(" createTime desc");
			int pinglunCircleCount = informService.countByExample(informExample);//圈子内容评论记数
			setPinglunCircleCount(pinglunCircleCount+"");
			
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(3);
			informExample.setOrderByClause(" createTime desc");
			int topicPinglunCount = informService.countByExample(informExample);//话题内容评论记数
			setTopicPinglunCount(topicPinglunCount+"");
			
			informExample.clear();
			informExample.createCriteria().andTouseridEqualTo(getCurrentUser().getUserid()).andIsreadIsNull().andInformtypeEqualTo(4);
			informExample.setOrderByClause(" createTime desc");
			int gzTopicCount = informService.countByExample(informExample);//话题被关注记数
			setGzTopicCount(gzTopicCount+"");
			
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
//			form.setJsonMsg("登录成功,欢迎你：" + currUserInfo.getNick(), true, currUserInfo);
			json.put("user", user);
			form.setJsonMsg("登录成功,欢迎你：" + currUserInfo.getNick(), true, json, 3);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("密码错误，请重新输入", false, null);
		}
		return JSON_PAGE;
	}

	public String ajaxSettingAccount() {
		try {
			UserInfo user = getCurrentUser();
//			Map<String, Object> json = new HashMap<String, Object>();
//			if(user.getEmail().length()>3 || user.getEmail()!=null || user.getEmail()!="")
//			{
//				form.setJsonMsg("", true, json);
//				return JSON_PAGE;
//			}
			if (StringUtils.isBlank(form.getInstance().getEmail())) {
				form.setJsonMsg("请输入邮箱！", false, null);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getInstance().getPassword())) {
				form.setJsonMsg("请输入密码！", false, null);
				return JSON_PAGE;
			}

			if (!checkEmailNotExists()) {
				form.setJsonMsg("您输入的邮箱已经被注册了，换一个吧！", false, null);
				return JSON_PAGE;
			}

//			if (StringUtils.isNotBlank(getCurrentUser().getEmail())
//					&& !StringUtils.equals(Md5Utils.md5(StringUtils.trimToEmpty(form.getInstance().getPassword())), StringUtils.trimToEmpty(getCurrentUser().getPassword()))) {
//				form.setJsonMsg("密码错误，请重新输入", false, null);
//				return JSON_PAGE;
//			}
			if(!StringUtils.isBlank(form.getInstance().getImage())){
				String imageName = form.getInstance().getImage();
	            if (imageName.indexOf("/") != -1) {
	             	imageName = StringUtils.substring(imageName, imageName.lastIndexOf("/") +1);
				}
		           try {
		        	   File temFile = new File(AppParametersConfig.getParameter("file.tmp.path")+imageName);
			            FileUtils.copyFile(temFile, new File(AppParametersConfig.getParameter("file.userInfo.imagePath")+imageName));
			            FileUtils.deleteQuietly(temFile);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
	            getCurrentUser().setImage("uploadPath/user/imagePath/" + imageName);
//	            getCurrentUser().setOtheraccountuserimage("uploadPath/user/imagePath/" + imageName);
			}
			getCurrentUser().setEmail(form.getInstance().getEmail());
			getCurrentUser().setPassword(Md5Utils.md5(form.getInstance().getPassword()));
			getCurrentUser().setNick(form.getInstance().getNick());
			
			
			getCurrentUser().setWhat(form.getInstance().getWhat());
			
			userInfoService.updateByPrimaryKeySelective(getCurrentUser());
			UserInfo usernow = (UserInfo) userInfoService.selectByPrimaryKey(getCurrentUser().getUserid(), UserInfo.class);
			setCurrentUser(usernow);
			/**邮件日志*/
			String eid = String.valueOf(UUID.randomUUID());
			UserEmaillog userEmaillog = new UserEmaillog();
			userEmaillog.setEid(eid);
			userEmaillog.setLogdatetime(new Date());
			userEmaillog.setUserid(user.getUserid());
			userEmaillog.setEmail(form.getInstance().getEmail());
			
			userEmaillog.setIp(form.getIp());
			userEmaillog.setDisplay(1);
			userEmaillog.setModifytype(0);
			userEmaillog.setDef1("0");
			userEmaillog.setDef2("0");
			userEmaillog.setDef3("0");
			userEmaillog.setDef4("0");
			userEmaillogService.insert(userEmaillog);
			
			//设置 邮件
//		    MailSenderInfo mailInfo = new MailSenderInfo();    
//		    mailInfo.setMailServerHost(UserEail.EmailServerHost);    
//		    mailInfo.setMailServerPort(UserEail.EailServerPort);    
//		    mailInfo.setValidate(UserEail.EmailValidate);    
//		    mailInfo.setUserName(UserEail.EmailUserName);    
//		    mailInfo.setPassword(UserEail.EmailUserPwd);
//		    mailInfo.setFromAddress(UserEail.EmailFromAddress); 
//		    mailInfo.setToAddress(form.getInstance().getEmail());    
//		    mailInfo.setSubject("逸族网 帐号- 账号激活");    
//		    String content = "<!DOCTYPE HTML PUBLIC 'null'><html><head><title>逸族网 - 开启你的第二人生</title></head><body><style>body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{margin:0;padding:0}body{padding:20px;width:500px;color:#222;background:#fff;font-size:12px;line-height:1.5;font-family:Tahoma,sans-serif}a{color:#14647d;text-decoration:none;_font-family:Arial,Simsun,sans-serif}a:hover{text-decoration:underline}img{display:block;border:0}.logo{display:block;margin-bottom:10px;padding-bottom:10px;border-bottom:2px solid #ebebeb;}.title{margin-bottom:10px;font-family:'微软雅黑',Tahoma,sans-serif;font-size:32px;font-weight:200;}p{margin-bottom:10px;}p strong,p span, p .link{font-size:14px;}.link{color:#f60;text-decoration:underline;}.info{color:#808080;}</style><a href='http://www.1mily.com' target='_blank' class='logo' title='逸族网'><img src='http://www.1mily.com:80/images/logo.png'></a><h3 class='title'>[逸族网]</h3><p><span><strong>点击以下链接、复制下面的地址到浏览器中打开</strong></span></p><p><a class='link' href='http://1mily.com/setting/activationEmail.jsp?id="+eid+"&uid="+user.getUserid()+"' target='_blank'>http://1mily.com/setting/activationEmail.jsp?eid="+eid+"&uid="+user.getUserid()+"</a></p><p class='info'></p></p></body></html>";
//		    mailInfo.setContent(content);
//		    SimpleMailSender sms = new SimpleMailSender();   
//		    boolean istu =  sms.sendHtmlMail(mailInfo);
			
//			form.setJsonMsg("设置成功,邮件已经发送你邮箱【"+form.getInstance().getEmail()+"】,根据提示，进行账号激活！", true, getCurrentUser());
			form.setJsonMsg("设置成功！", true, getCurrentUser());
			return JSON_PAGE;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("请上传你的头像！", false, null);
		}
		return JSON_PAGE;
	}

	public String ajaxCheckEmailNotExists() {
		try {
			if (checkEmailNotExists()) {
				form.setJsonMsg("邮箱可以使用", true, null);
			} else {
				form.setJsonMsg("您输入的邮箱已经被注册了，换一个把！", false, null);
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("验证错误，请稍候重试！", false, null);
		}
		return JSON_PAGE;
	}

	private boolean checkEmailNotExists() {
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getInstance().getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			if (list == null || list.isEmpty() || (list.size() == 1 && list.get(0).getUserid().equals(getCurrentUser().getUserid()))) {
				return true;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return false;
	}
	public String aa()
	{
		String email = "";
		UserInfoExample example = new UserInfoExample();
//		String email = form.getEmail().replace("$", "@");
		example.createCriteria().andUseridEqualTo(Integer.valueOf(form.getId()));
		try {
			List<UserInfo> list = userInfoService.selectByExample(example);
			UserInfo user = list.get(0);
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			if (cookies != null || "".equals(cookies)) {
				System.out.println(cookies.length);
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("loginUserName")) {
						 email = cookies[i].getValue();
						 email = email.replace("$", "@");
					}
					if (cookies[i].getName().equals("email")) {
						 email = cookies[i].getValue();
					}
				}
			}
			
			if(user.getEmail().equals(email)){
				setCurrentUser(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SSOlogin";//SSOlogin
	}
	public String ajaxUpdatePassword() {
		try {

			if (StringUtils.isBlank(form.getOldPassword())) {
				form.setJsonMsg("请输入原始密码！", false, null);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getNewPassword())) {
				form.setJsonMsg("请输入新密码！", false, null);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getConfirmPassword())) {
				form.setJsonMsg("请输入确认密码！", false, null);
				return JSON_PAGE;
			}
			System.out.println(StringUtils.trimToEmpty(form.getOldPassword()));
			System.out.println(StringUtils.trimToEmpty(getCurrentUser().getPassword()));
			
			if (!StringUtils.equals(Md5Utils.md5(StringUtils.trimToEmpty(form.getOldPassword())), StringUtils.trimToEmpty(getCurrentUser().getPassword()))) {
				form.setJsonMsg("原始密码错误，请重新输入", false, null);
				return JSON_PAGE;
			}

			getCurrentUser().setPassword(Md5Utils.md5(form.getNewPassword()));
			userInfoService.updateByPrimaryKeySelective(getCurrentUser());
			form.setJsonMsg("修改成功！", true, getCurrentUser());
			return JSON_PAGE;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("密码错误，请重新输入", false, null);
		}
		return JSON_PAGE;
	}
	
	public String ajaxForgetSendEmail(){
		try {
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andEmailEqualTo(form.getInstance().getEmail());
			List<UserInfo> list = userInfoService.selectByExample(example);
			
			if (list == null || list.isEmpty() || list.size() < 1) {
				form.setJsonMsg("当前邮箱未注册，请确认邮箱是否正确！", false, null, 0);
				return JSON_PAGE;
			}
			
			TfindPwdLog findPwdLog = new TfindPwdLog();
			findPwdLog.setId(UUID.randomUUID().toString());
			findPwdLog.setCreateTime(new Date());
			findPwdLog.setIp(getRequest().getRemoteAddr());
			findPwdLog.setUsedTime(null);
			findPwdLog.setUserId(form.getInstance().getEmail());
			findPwdLog.setCode(UUID.randomUUID().toString());
			
			tfindPwdLogService.insert(findPwdLog);
			
			String url = getContextPath() + "userInfoAction_forgetPassword.do?code=" + findPwdLog.getCode();
			
			
			String content = "<!DOCTYPE HTML PUBLIC 'null'><html>"+
				"<head>"+
				""+
				"<title>逸族网 - 开启你的第二人生</title>"+
				"</head>"+
				"<body>"+
				"<style>"+
				"body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{margin:0;padding:0}"+
				"body{padding:20px;width:500px;color:#222;background:#fff;font-size:12px;line-height:1.5;font-family:Tahoma,sans-serif}"+
				"a{color:#14647d;text-decoration:none;_font-family:Arial,Simsun,sans-serif}"+
				"a:hover{text-decoration:underline}"+
				"img{display:block;border:0}"+
				".logo{display:block;margin-bottom:10px;padding-bottom:10px;border-bottom:2px solid #ebebeb;}"+
				".title{margin-bottom:10px;font-family:'微软雅黑',Tahoma,sans-serif;font-size:32px;font-weight:200;}"+
				"p{margin-bottom:10px;}"+
				"p strong,p span, p .link{font-size:14px;}"+
				".link{color:#f60;text-decoration:underline;}"+
				".info{color:#808080;}"+
				"</style>"+
				"<a href='http://www.1mily.com' target='_blank' class='logo' title='逸族网'><img src='http://www.1mily.com:80/images/logo.png'></a>"+
				"<h3 class='title'>[逸族网]找回密码</h3>"+
				"<p><span><strong>你使用“找回密码”功能。点击以下链接，设置你的新密码：</strong></span></p>"+
				"<p><a class='link' href='" +url+ "' target='_blank'>" +url+ "</a></p>"+
				"<p>如果以上链接不能点击，你可以复制网址URL，然后粘贴到浏览器地址栏打开，完成更新。</p>"+
				"<p>- 逸族网</p>"+
				"<p class='info'>（这是一封自动发送的邮件，请不要直接回复）</p>"+
				"<p class='info'>－如果你没有使用过“找回密码”功能，请忽略本邮件，你的逸族网账户依然是安全的。</p>"+
				"</p></body>"+
				"</html> ";
		
			//设置 邮件
		    MailSenderInfo mailInfo = new MailSenderInfo();    
		    mailInfo.setMailServerHost(UserEail.EmailServerHost);    
		    mailInfo.setMailServerPort(UserEail.EailServerPort);    
		    mailInfo.setValidate(UserEail.EmailValidate);    
		    mailInfo.setUserName(UserEail.EmailUserName);    
		    mailInfo.setPassword(UserEail.EmailUserPwd);
		    mailInfo.setFromAddress(UserEail.EmailFromAddress); 
		    mailInfo.setToAddress(form.getInstance().getEmail());    
		    mailInfo.setSubject("【逸族网】-找回密码");    
		    mailInfo.setContent(content);
		    SimpleMailSender sms = new SimpleMailSender();   
		    boolean flag =  sms.sendHtmlMail(mailInfo);
//			boolean flag = Mailer.sendHtmlMail(form.getInstance().getEmail(), "【逸族网】-找回密码", content, "逸族网");
			if (flag) {
				form.setJsonMsg("发送成功，请去您的邮箱按照提示进行修改密码！", true, null, 1);
			}else {
				form.setJsonMsg("发送失败，请稍后重试！", true, null, 0);
			}
			
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("发送失败，请稍后重试！", false, null, 0);
		}
		return JSON_PAGE;
	}
	
	public String forgetPassword(){
		ArrayList<String> error = new ArrayList<String>();
		
		try {
			
			if (StringUtils.isBlank(form.getCode())) {
				error.add("你访问的地址无效，请检查浏览器地址输入是否正确！");
			}else {
				TfindPwdLogExample tfindPwdLogExample = new TfindPwdLogExample();
				tfindPwdLogExample.createCriteria().andCodeEqualTo(StringUtils.trimToEmpty(form.getCode()));
				List<TfindPwdLog> list = baseService.selectByExample(tfindPwdLogExample);
				if (list == null || list.size() < 1) {
					error.add("你访问的地址无效，请检查浏览器地址输入是否正确！");
				}else {
					
					TfindPwdLog tfindPwdLog = list.get(0);
					if (tfindPwdLog.getUsedTime() == null) {
						form.setTfindPwdLog(tfindPwdLog);
					}else {
						error.add("您访问的地址已过期，请重新找回密码！");
					}
				}
			}
		} catch (Exception e) {
			error.add("你访问的地址无效，请检查浏览器地址输入是否正确！");
			LOG.error(e.getMessage(), e);
		}
		setActionErrors(error);
		return SUCCESS;
	}
	
	
	public String ajaxForgetUpdatePassword(){
		try {
			
			TfindPwdLogExample tfindPwdLogExample = new TfindPwdLogExample();
			tfindPwdLogExample.createCriteria().andCodeEqualTo(StringUtils.trimToEmpty(form.getCode()));
			List<TfindPwdLog> list = baseService.selectByExample(tfindPwdLogExample);
			if (list == null || list.size() < 1) {
				form.setJsonMsg("你访问的地址无效，请检查浏览器地址输入是否正确！");
				return JSON_PAGE;
			}
			
			TfindPwdLog tfindPwdLog = list.get(0);
			
			if (StringUtils.isBlank(form.getNewPassword())) {
				form.setJsonMsg("请输入新密码！", false, null);
				return JSON_PAGE;
			}
			if (StringUtils.isBlank(form.getConfirmPassword())) {
				form.setJsonMsg("请输入确认密码！", false, null);
				return JSON_PAGE;
			}
			
			if (tfindPwdLog.getUsedTime() != null) {
				form.setJsonMsg("您访问的地址已过期，请重新找回密码！", false, null);
				return JSON_PAGE;
			}
			
			UserInfoExample userInfoExample = new UserInfoExample();
			userInfoExample.createCriteria().andEmailEqualTo(StringUtils.trimToEmpty(tfindPwdLog.getUserId()));
			List<UserInfo> userList = baseService.selectByExample(userInfoExample);
			if (userList == null || userList.size() < 1) {
				form.setJsonMsg("用户不存在或者被管理员禁用，请刷新后重试！");
				return JSON_PAGE;
			}
			
			UserInfo userInfo = userList.get(0);

			if (StringUtils.equals(Md5Utils.md5(StringUtils.trimToEmpty(form.getNewPassword())), StringUtils.trimToEmpty(userInfo.getPassword()))) {
				form.setJsonMsg("新密码与原始密码相同，请重新设置密码！", false, null);
				return JSON_PAGE;
			}

			userInfo.setPassword(Md5Utils.md5(form.getNewPassword()));
			userInfoService.updateByPrimaryKeySelective(userInfo);
			setCurrentUser(userInfo);
			
			tfindPwdLog.setUsedTime(new Date());
			tfindPwdLogService.updateByExample(tfindPwdLog, tfindPwdLogExample);
			form.setJsonMsg("修改密码成功,系统将自动为您登录！", true, getCurrentUser());
			return JSON_PAGE;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			form.setJsonMsg("修改密码失败，请稍后重试！", false, null);
		}
		return JSON_PAGE;
	}

	public String logout() {
		try {
			Cookie[] cookies = ServletActionContext.getRequest().getCookies();
			for(int i=0;i<cookies.length;i++) {
				if(cookies[i].getName().equals("email")||cookies[i].getName().equals("userid")||cookies[i].getName().equals("loginUserName")
						||cookies[i].getName().equals("cookieUserId")){
					Cookie cookie = new Cookie(cookies[i].getName(), null);
					cookie.setMaxAge(0);
					cookie.setDomain(".1mily.com");
					cookie.setPath("/");
					ServletActionContext.getResponse().addCookie(cookie);
				}
			}
			getSession().removeAttribute("user");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return "loginResult";
	}

	public String index() {
		try {

			if (LOG.isDebugEnabled()) {
				LOG.debug("请求地址及参数--:" + getRequest().getServletPath());
				LOG.debug("请求地址及参数--:" + getRequest().getRequestURL());
				LOG.debug("请求地址及参数--:" + getRequest().getUserPrincipal());
				LOG.debug("请求地址及参数--:" + getRequest().getContextPath());
				LOG.debug("请求地址及参数--:" + getRequest().getContextPath());
				LOG.debug("请求地址及参数--:" + getRequest().getLocalAddr());
				LOG.debug("请求地址及参数--:" + this.getLocale().getDisplayCountry());
				LOG.debug("请求地址及参数--:" + getRequest().getQueryString());
			}

			UserInfoExample example = new UserInfoExample();
			List<UserInfo> list = userInfoService.selectByExample(example);

			if (list != null && !list.isEmpty()) {
				for (UserInfo userInfo : list) {
					System.out.println(userInfo.getAddress());
				}
			}

			form.setUserInfoList(list);
			LOG.debug("执行index");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return SUCCESS;
	}

	public String register() {
		try {
			if (form != null && form.getInstance() != null) {
				userInfoService.insert(form.getInstance());
			} else {
				
				if (LOG.isDebugEnabled()) {
					LOG.debug("未传入数据，跳转到注册页面！");
				}
			}
			LOG.debug("执行show");
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return SUCCESS;
	}
	/**
	 * 检查用户是否已经登录
	 * @return
	 */
	public String checkUserLogin()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			boolean userLogin = false;
			UserInfo user = getCurrentUser();
			if(user!=null)
			{
				userLogin = true;
			}else{
				userLogin = false;
			}
			json.put("userLogin", userLogin);
			System.out.println(userLogin+"-----------------------------------------------------");
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	/**
	 * 个人中心
	 */
	public String getProvinceAndCity(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			ProvinceExample provinceExample = new ProvinceExample();
			List<Province> provinceList = provinceService.selectByExample(provinceExample);
			json.put("provinceList", provinceList);
			
			CityExample cityExample = new CityExample();
			cityExample.createCriteria().andProvinceidEqualTo(1);
			List<City> cityList = cityService.selectByExample(cityExample);
			json.put("cityList", cityList);
			
			TownExample townExample = new TownExample();
			townExample.createCriteria().andCityidEqualTo(1);
			List<Town> townList = townService.selectByExample(townExample);
			json.put("townList", townList);
			 
			form.setJsonMsg("success", true, json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String getCityFromProvince(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			CityExample cityExample = new CityExample();
			cityExample.createCriteria().andProvinceidEqualTo(Integer.parseInt(form.getId()));
			List<City> cityList = cityService.selectByExample(cityExample);
			json.put("cityList",cityList);
			TownExample townExample = new TownExample();
			townExample.createCriteria().andCityidEqualTo(cityList.get(0).getCityid());
			List<Town> townList = townService.selectByExample(townExample);
			json.put("townList",townList);
			form.setJsonMsg("success", true, json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String getTownFromCity(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			TownExample townExample = new TownExample();
			townExample.createCriteria().andCityidEqualTo(Integer.parseInt(form.getId()));
			List<Town> townList = townService.selectByExample(townExample);
			json.put("townList",townList);
			form.setJsonMsg("success", true, json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String getProvince(){
		try {
			Map<String, Object> json = new HashMap<String, Object>();
			ProvinceExample provinceExample = new ProvinceExample();
			List<Province> provinceList = provinceService.selectByExample(provinceExample);
			json.put("provinceList", provinceList);
			//json.put("townList",townList);
			form.setJsonMsg("success", true, json);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String saveUserInfo(){
		try{
			UserInfo user = getCurrentUser();
			user.setSex(form.getSex());
			user.setBirthday(form.getBirthday());
			user.setHobby(form.getHabby());
			user.setAddress(form.getAddress());
			user.setImage(user.getOtheraccountuserimage());
			user.setWhat(form.getWhat());
			int i = userInfoService.updateByPrimaryKeySelective(user);
			if(i == 1){
				form.setJsonMsg("success", true, null);
			}else{
				form.setJsonMsg("fail", false, null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String saveUserImg(){
		try{
			UserInfo user = getCurrentUser();
//			user.setNick(user.getName());
			if(form.getRef() == null || form.getRef().equals("")){
				user.setImage(user.getOtheraccountuserimage());
			}else{
				user.setImage(form.getRef());
			}
			int i = userInfoService.updateByPrimaryKeySelective(user);
			if(i == 1){
				form.setJsonMsg("success", true, null);
			}else{
				form.setJsonMsg("fail", false, null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	public String bangdingTenAndSinaUser(){
		try{
			UserInfo user = getCurrentUser();
			user.setNick(form.getName());
			user.setName(form.getName());
			user.setEmail(form.getEmail());
			user.setPassword(Md5Utils.md5(form.getPwd()));
			
			int i = userInfoService.updateByPrimaryKeySelective(user);
			if(i == 1){
				form.setJsonMsg("success", true, null);
			}else{
				form.setJsonMsg("fail", false, null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String changuserinfo (){
		try{
			UserInfo user = getCurrentUser();
			form.setBirthday(user.getBirthday());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "changuserinfo";
	}
	
	public String updateuserinfo(){
		try{
			UserInfo user = getCurrentUser();
			user.setNick(form.getName());
			user.setName(form.getName());
			user.setSex(form.getSex());
			user.setBirthday(form.getBirthday());
			user.setHobby(form.getHabby());
			user.setAddress(form.getAddress());
			user.setImage(user.getOtheraccountuserimage());
			user.setWhat(form.getWhat());
			int i = userInfoService.updateByPrimaryKeySelective(user);
			if(i == 1){
				form.setJsonMsg("success", true, null);
			}else{
				form.setJsonMsg("fail", false, null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGE;
	}
	
	public String getJsonPage() {
		// 第一个参数，提示用户，第二个参数：标识操作是否成功true/false 第三个参数为obj对象，不用转换为json格式
		form.setJsonMsg("第一个参数，提示用户，第二个参数：标识操作是否成功true/false  第三个参数为obj对象，不用转换为json格式", true, UserInfoExample.class);
		return JSON_PAGE;
	}
	/**
	 * 生成SessionUID
	 * @return
	 */
	public String createUID() {
		Date d = new Date();
		long l = d.getTime();
		String s = String.valueOf(l);
		return "cgmg" + s;

	}
	public String updatedata()
	{
		if(getCurrentUser()==null)
		{
			return "index";
		}
		String province= "";
		int provinceid=1;
		String city = "";
		int cityid=1;
		String town = "";
		int townid=1;
		String[] add = null;
		UserInfo user = getCurrentUser();
		try {
			user = (UserInfo) userInfoService.selectByPrimaryKey(user.getUserid(), UserInfo.class);
			setCurrentUser(user);
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(user.getAddress() != null && user.getAddress().length()>1)
		{
			String address = getCurrentUser().getAddress();
			add = address.split("-");
			for (int i = 0; i < add.length; i++) {
				if(i==0)
				{
					province = add[i];
				}
				if(i==1)
				{
					city = add[i];
				}
				if(i==2)
				{
					town = add[i];
				}
			}
			try {
				
				if(add.length>0)
				{
					ProvinceExample provinceExample = new ProvinceExample();
					List<Province> provinceList = provinceService.selectByExample(provinceExample);
					form.setListProvinces(provinceList);
					for (int i = 0; i < provinceList.size(); i++) {
						Province province2 = provinceList.get(i);
						if(province2.getName().equals(province))
						{
							provinceid=province2.getProvinceid();
							form.setProvince(province2);
						}else{
							form.setProvince(provinceList.get(0));
						}
					}
					if(add.length==2)
					{
						
						TownExample townExample = new TownExample();
						townExample.createCriteria().andNameLike("%"+city+"%");
						List<Town> townList = townService.selectByExample(townExample);
						for (int i = 0; i < townList.size(); i++) {
							Town town2 = townList.get(i);
							if(town2.getName().equals(city))
							{
								townid = town2.getTownid();
								cityid = town2.getCityid();
							}
						}
						CityExample cityExample = new CityExample();
						cityExample.createCriteria().andProvinceidEqualTo(provinceid);
						List<City> cityList = cityService.selectByExample(cityExample);
						form.setListCitys(cityList);
						for (int i = 0; i < cityList.size(); i++) {
							City city2 = cityList.get(i);
							if(city2.getCityid()==cityid)
							{
								cityid = city2.getCityid();
								form.setCity(city2);
							}
						}
						townExample.clear();
						townExample.createCriteria().andCityidEqualTo(cityid);
						townList = townService.selectByExample(townExample);
						form.setListTowns(townList);
						for (int j = 0; j < townList.size(); j++) {
							Town town2 = townList.get(j);
							if(town2.getName().equals(city)){
								form.setTown(town2);
							}
						}
					}
					if(add.length==3){
						CityExample cityExample = new CityExample();
						cityExample.createCriteria().andProvinceidEqualTo(provinceid);
						List<City> cityList = cityService.selectByExample(cityExample);
						form.setListCitys(cityList);
						for (int i = 0; i < cityList.size(); i++) {
							City city2 = cityList.get(i);
							if(city2.getName().equals(city))
							{
								cityid = city2.getCityid();
								form.setCity(city2);
							}
						}
						TownExample townExample = new TownExample();
						townExample.createCriteria().andCityidEqualTo(cityid);
						List<Town> towList = townService.selectByExample(townExample);
						form.setListTowns(towList);
						for (int j = 0; j < towList.size(); j++) {
							Town town2 = towList.get(j);
							if(town2.getName().equals(town)){
								form.setTown(town2);
							}
						}
						
					}
				}
				
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				ProvinceExample provinceExample = new ProvinceExample();
				List<Province> provinceList = provinceService.selectByExample(provinceExample);
				form.setListProvinces(provinceList);
				CityExample cityExample = new CityExample();
				cityExample.createCriteria().andProvinceidEqualTo(1);
				List<City> cityList = cityService.selectByExample(cityExample);
				form.setListCitys(cityList);
				TownExample townExample = new TownExample();
				townExample.createCriteria().andCityidEqualTo(1);
				List<Town> townList = townService.selectByExample(townExample);
				form.setListTowns(townList);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return "updatedata";
	}
	public String updatedatasave()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if(getCurrentUser()==null)
			{
				json.put("islogin", false);
			}else{
				json.put("islogin", true);
				UserInfo user = getCurrentUser();
				user.setNick(form.getNick());
				user.setSex(form.getSex());
				user.setBirthday(form.getBirthday());
				user.setAddress(form.getAddress());
				user.setWhat(form.getWhat());
				int status =userInfoService.updateByPrimaryKey(user);
				json.put("status", status);
				form.setJsonMsg("", true, json);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return JSON_PAGE;
	}
	public String updatehobby()
	{
		if(getCurrentUser()==null)
		{
			return "index";
		}
		return "updatehobby";
	}
	public String updatehobbysave()
	{
		Map<String, Object> json = new HashMap<String, Object>();
		try {
			if(getCurrentUser()==null)
			{
				json.put("islogin", false);
			}else{
				json.put("islogin", true);
				UserInfo user = getCurrentUser();
				user.setHobby(form.getHabby());
				int status = userInfoService.updateByPrimaryKey(user);
				json.put("status", status);
			}
			form.setJsonMsg("", true, json);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json.put("status", 0);
			form.setJsonMsg("", true, json);
		}
		return JSON_PAGE;
	}
	public String updatehead()
	{
		if(getCurrentUser()==null)
		{
			return "index";
		}
		UserInfo user = getCurrentUser();
		String img = user.getImage();
		if(user.getImage()==null)
		{     
			user.setUserimage("/uploadPath/user/photo_big.jpg");
			setCurrentUser(user);
		}else{
			if(form.getId()!=null && form.getId().equals("uploadimg"))
			{
				
			}else{
				user.setUserimage(img);
				setCurrentUser(user);
			}
		}
		return "updatehead";
	}
	/**
	 * 对火狐浏览器处理
	 * @return
	 */
	public String firefoxUserimage()
	{
		try {
			UserInfo userInfo = getCurrentUser();
			if(userInfo!=null)
			{
				userInfo.setUserimage(form.getId());
				setCurrentUser(userInfo);
			}
			form.setJsonMsg("", true, null);
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    		String ymd = sdf.format(new Date());
    		String path = ymd + "/";
    		//String img = "";
			if (ServletFileUpload.isMultipartContent(getRequest())) {
				if(form.getFiledata() != null){
					stream = new FileInputStream(form.getFiledata());
					String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + RandomUtils.nextInt();
					String ext = form.getFilename().substring(form.getFilename().lastIndexOf(".") + 1);
					String fileName = prefix + "." + ext;
					String savePath = AppParametersConfig.getParameter("file.userInfo.imagePath")+path + fileName;
					String filedir = AppParametersConfig.getParameter("file.userInfo.imagePath")+path;
					File file = new File(filedir);
					if(!file.isDirectory())
					{
						file.mkdirs();
					}
					fileUrl = "/uploadPath/user/imagePath/"+path+fileName;
					bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
					int length;
					while ((length = stream.read(imgBufTemp)) != -1) {
						bos.write(imgBufTemp, 0, length);
					}
					//img = "/uploadPath/user/imagePath/"+path+"1"+fileName;
//					try {
						//ImageUtils.getFixedBoundIcon("E:"+fileUrl,"E:"+img, 230, 230);
						UserInfo user = getCurrentUser();
			        	if(user!=null)
			        	{
			        		user.setUserimage(fileUrl);
					        setCurrentUser(user);
			        	}
//				        
//					} catch (Exception e) {
//						// TODO: handle exception
//						e.printStackTrace();
//					}
				}
				json.append("{");
				json.append("'");
				json.append("fileUrl");
				json.append("':'");
				json.append(fileUrl);
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
    	return "jsonPage";
    }
	public String updatecuthead()
	{
		if(getCurrentUser()==null)
		{
			return "index";
		}
		Map<String, Object> json = new HashMap<String, Object>();
    	String name = AppParametersConfig.getParameter("file.base.path");
    	String names = AppParametersConfig.getParameter("file.userInfo.imagePath");
    	name = name+form.getImgpath();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String path = ymd + "/";
		String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + RandomUtils.nextInt();
		String filepath = names+path+prefix+".jpg";
		String filedir = AppParametersConfig.getParameter("file.userInfo.imagePath")+path;
		File file = new File(filedir);
		if(!file.isDirectory())
		{
			file.mkdirs();
		}
        image.setSrcpath(name);  
        image.setSubpath(filepath); 
        image.setX(paseint(form.getImgx().trim()));
        image.setY(paseint(form.getImgy().trim()));
        image.setHeight(paseint(form.getImgheight().trim()));
        image.setWidth(paseint(form.getImgwidth().trim()));
        boolean su = false;
        try {  
        	su = image.cut(); //执行裁剪操作  执行完后即可生成目标图在对应文件夹内。</span>  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        String saveUrl  = AppParametersConfig.getParameter("file.userInfo.saveUrl");
        if(su)
        {
        	saveUrl = saveUrl+path+prefix+".jpg";
            UserInfo user = getCurrentUser();
            user.setImage(saveUrl);
    		try {
    			userInfoService.updateByPrimaryKeySelective(user);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            json.put("saveUrl",saveUrl );
            getCurrentUser().setUserimage(saveUrl);
        }else{
        	json.put("saveUrl",getCurrentUser().getUserimage());
        }
        form.setJsonMsg("", true, json);
		return JSON_PAGE;
	}
	 public int paseint(String str)
	    {
	    	int i = str.indexOf(".");
	    	if(i>0)
	    	{
	    		String res = str.substring(0, str.indexOf("."));
	    		return Integer.parseInt(res.trim());
	    	}else{
	    		return Integer.parseInt(str);
	    	}
	    	
	    }
	 /**
	  * 修改用户昵称和素描
	  * 2013-07-17
	  */
	 public String updateUserNickAndWhat(){
			try {
				String nick = form.getNick();
				String what = form.getWhat();
				int userId = form.getUserId();
				UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(userId, UserInfo.class);
				if(user != null){
					user.setNick(nick);
					user.setWhat(what);
					int i = userInfoService.updateByPrimaryKeySelective(user);
					if(i == 1){
						form.setJsonMsg("更改成功", true, null,1);
					}
				}else{
					form.setJsonMsg("更改失败,用户不存在", false, null,0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				form.setJsonMsg("更改失败", true, null);
			}
			return JSON_PAGE;
	 }
	 /**
	  * 更改个人中心背景图片
	  * 2013-07-17
	  */
	 public String updateUserWebsite(){
		 try {
				int userId = form.getUserId();
				UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(userId, UserInfo.class);
				if(user != null){
					user.setWebsite(form.getCoverImgId()+"");
					int i = userInfoService.updateByPrimaryKeySelective(user);
					if(i == 1){
						form.setJsonMsg("更改成功", true, null,1);
					}
				}else{
					form.setJsonMsg("更改失败,用户不存在", false, null,0);
				}
			} catch (Exception e) {
				e.printStackTrace();
				form.setJsonMsg("更改失败", false, null,0);
			}
			return JSON_PAGE;
	 }
	 /**
	  * 更改用户头像
	  * 2013-07-17
	  */
	 public String updateUserImage(){
		
		 Map<String, Object> json = new HashMap<String, Object>();
			byte[] imgBufTemp = new byte[102401];
			InputStream stream = null;
			BufferedOutputStream bos = null;
			String fileUrl = "";
			try {
				UserInfo user = (UserInfo) userInfoService.selectByPrimaryKey(form.getUserId(), UserInfo.class);
				if(user!=null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			    	String ymd = sdf.format(new Date());
			    	String path = ymd + "/";
					if(form.getFiledata() != null){
						stream = new FileInputStream(form.getFiledata());
						String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-" + RandomUtils.nextInt();
						String ext = form.getFilename().substring(form.getFilename().lastIndexOf(".") + 1);
						String fileName = prefix + "." + ext;
						String savePath = AppParametersConfig.getParameter("file.userInfo.imagePath")+path + fileName;
						String filedir = AppParametersConfig.getParameter("file.userInfo.imagePath")+path;
						File file = new File(filedir);
						if(!file.isDirectory())
						{
							file.mkdirs();
						}
						fileUrl = "/uploadPath/user/imagePath/"+path+fileName;
						bos = new BufferedOutputStream(new FileOutputStream(new File(savePath)));
						int length;
						while ((length = stream.read(imgBufTemp)) != -1) {
							bos.write(imgBufTemp, 0, length);
						}
						user.setImage(fileUrl);
						int i = userloginlogService.updateByPrimaryKeySelective(user);
						json.put("user", user);
						if(i == 1){
							form.setJsonMsg("更改成功", true, json,1);
						}
					}else{
						form.setJsonMsg("更改失败,上传文件不正确", false, null,0);
					}
				}else{
					form.setJsonMsg("更改失败", false, null,0);
				}
			} catch (Exception e) {
				  LOG.error(e.getMessage(), e);
				  form.setJsonMsg("更改失败", false, null,0);
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
	public UserInfoForm getModel() {
		return form;
	}

}
