package com.yizu.proj.sys.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import simpleWebFrame.util.DateTimeUtil;
import simpleWebFrame.util.MD5Util;
import t4j.http.AccessTokens;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.PostParameter;
import weibo4j.model.User;

import com.opensymphony.xwork2.ModelDriven;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.RenrenApiConfig;
import com.renren.api.client.utils.HttpURLUtils;
import com.sun.jndi.toolkit.url.Uri;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.UserInfoForm;
import com.yizu.proj.sys.beans.InformExample;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.beans.UserInfoExample;
import com.yizu.proj.sys.beans.Userloginlog;
import com.yizu.proj.sys.service.InformService;
import com.yizu.proj.sys.service.UserInfoService;
import com.yizu.proj.sys.service.UserloginlogService;
import com.yizu.proj.utils.DateUtil;
import com.yizu.proj.utils.QQUtil;
import com.yizu.proj.utils.RenrenUtil;
import com.yizu.proj.utils.TaobaoUtil;
import com.yizu.proj.utils.WangYiUtil;
import com.yizu.proj.utils.WeiBoUtil;

@Scope("prototype")
@Controller("authorizeAction")
public class AuthorizeAction extends BaseAction implements ModelDriven {

	private static final long serialVersionUID = -1517391340327305523L;
    private String authorizeUrl;
    private UserInfoForm form = new UserInfoForm();
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private InformService informService;
    @Autowired
	private UserloginlogService userloginlogService;
    private String getLoginedUserInfo() {
        // TODO 需要补充
        return null;
    }

    /**
     * 无效访问
     */
    private void unvalidAccess() {
        // TODO 需要补充
    }

    private String getFormData(String obj) {
        return getRequest().getParameter(obj);
    }

    private void setFormData(String key, String value) {
        getRequest().setAttribute(key, value);
    }

    // QQ授权
    public String connectQQ() {
        try {
        	
        	if (StringUtils.isNotBlank(form.getRef())) {
				getSession().setAttribute("login_before_url", form.getRef());	
			}
        	
            authorizeUrl = QQUtil.login_URL;

            if (LOG.isDebugEnabled()) {
                LOG.debug("authorizeUrl:" + authorizeUrl);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "authorize";
    }
    // 网易授权
    public String connectWangYi() {
    	
        try {
        	if (StringUtils.isNotBlank(form.getRef())) {
				getSession().setAttribute("login_before_url", form.getRef());	
			}
            authorizeUrl = WangYiUtil.getURL();
            System.out.println("URL地址:"+authorizeUrl);
            if (LOG.isDebugEnabled()) {
                LOG.debug("authorizeUrl:" + authorizeUrl);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "authorize";
    }
    // 人人授权
    public String connectRenRen() {
        try {
        	if (StringUtils.isNotBlank(form.getRef())) {
				getSession().setAttribute("login_before_url", form.getRef());	
			}
        	
            authorizeUrl = RenrenUtil.login_URL;

            if (LOG.isDebugEnabled()) {
                LOG.debug("authorizeUrl:" + authorizeUrl);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "authorize";
    }

    // 淘宝授权
    public String connectTaobao() {
        try {
        	if (StringUtils.isNotBlank(form.getRef())) {
				getSession().setAttribute("login_before_url", form.getRef());	
			}
        	
            authorizeUrl = TaobaoUtil.login_URL + taobaoIdentifyParams();
            if (LOG.isDebugEnabled()) {
                LOG.debug("authorizeUrl:" + authorizeUrl);
            }
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "authorize";
    }

    /**
     * 使用淘宝帐号登录
     *
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    private String taobaoIdentifyParams() throws UnsupportedEncodingException {
        TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        // 组装协议参数。
        apiparamsMap.put("sign_method", "md5");
        apiparamsMap.put("app_key", TaobaoUtil.APP_KEY);
        apiparamsMap.put("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        String sign = TaobaoUtil.sign(apiparamsMap, TaobaoUtil.APP_SERCET);
        // 组装协议参数sign
        apiparamsMap.put("sign", sign);
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> e = it.next();
            param.append("&").append(e.getKey()).append("=").append(e.getValue());
        }
        return param.toString().substring(1);
    }

    // 微博授权
    public String connectWeibo() {
        try {
        	if (StringUtils.isNotBlank(form.getRef())) {
				getSession().setAttribute("login_before_url", form.getRef());	
			}
        	
            authorizeUrl = WeiBoUtil.authorizeURL + "?client_id=" + WeiBoUtil.APP_ID + "&redirect_uri=" + WeiBoUtil.redirect_URI
                    + "&response_type=code";
            if (LOG.isDebugEnabled()) {
                LOG.debug("authorizeUrl:" + authorizeUrl);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return "authorize";
    }

    /**
     * 淘宝 退出TOP登陆（清除top cookie）
     *
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    private String logoutOpenId() throws UnsupportedEncodingException {
        TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        // 组装协议参数。
        apiparamsMap.put("sign_method", "md5");
        apiparamsMap.put("app_key", TaobaoUtil.APP_KEY);
        apiparamsMap.put("timestamp", DateTimeUtil.getCurrentDateTime());
        String sign = TaobaoUtil.sign(apiparamsMap, TaobaoUtil.APP_SERCET);
        // 组装协议参数signs
        apiparamsMap.put("sign", sign);
        StringBuilder param = new StringBuilder();
        for (Iterator<Map.Entry<String, String>> it = apiparamsMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<String, String> e = it.next();
            param.append("&").append(e.getKey()).append("=").append(e.getValue());
        }
        return param.toString().substring(1);
    }
    //网易登录
	public String wangyiLoginResult()
	{
		try {
			AccessTokens accessToken =WangYiUtil.tblog.getOAuthAccessToken(WangYiUtil.requestToken);
//			 以后使用的时候，授权过的用户，只需要读取已经保存的 access token，就可以重复使用
			WangYiUtil.tblog.setToken(accessToken.getToken(), accessToken.getTokenSecret());
//			 检查登录的用户
			t4j.data.User user = WangYiUtil.tblog.verifyCredentials();
			otherAccountLogin(user.getId()+"", "wangyi", user.getName(), user.getProfileImageURL());
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "loginResult";
	}
    public String taoBaoLoginResult() throws Exception {
        try {
            if (getLoginedUserInfo() != null) {
                unvalidAccess();
                return "index";
            }
            boolean res = false;

            String notify_result = getFormData("notify_result");
            String taobao_user_nick = getFormData("taobao_user_nick");
            String app_key = getFormData("app_key");
            String notify_type = getFormData("notify_type");
            
            String taobao_user_nick_encode = taobao_user_nick;
            TaobaoClient client = new DefaultTaobaoClient(TaobaoUtil.APP_URL, TaobaoUtil.APP_KEY, TaobaoUtil.APP_SERCET);
            com.taobao.api.request.UserGetRequest req = new UserGetRequest();
            req.setFields("user_id,nick,seller_credit,avatar");
            req.setNick(taobao_user_nick_encode);
            UserGetResponse response = client.execute(req);
            String json = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> requestObj = objectMapper.readValue(json, Map.class);

            requestObj = (Map<String, Object>) requestObj.get("user_get_response");
            Map<String, Object> taoBaoUserInfo = (Map<String, Object>) requestObj.get("user");
            System.out.println(taoBaoUserInfo);
            System.out.println(taoBaoUserInfo.get("avatar"));
            
            Object otherAccountUserImage = taoBaoUserInfo.get("avatar");
            if (notify_result.equals("success") && app_key.equals(TaobaoUtil.APP_KEY) && notify_type.equals("identify")) {
                otherAccountLogin(taobao_user_nick, "taobao", "", taoBaoUserInfo.get("avatar") != null ? taoBaoUserInfo.get("avatar").toString() + "_30x30.jpg" : getContextPath()+"images/ico_01.jpg");
                res = true;
            }

            if (res) {
                setFormData("result", "success");
            } else {
                setFormData("result", "failed");
            }
        } catch (Exception e) {
            LOG.error("taoBaoLoginResult 执行失败！", e);
            setFormData("result", "failed");
        }
        return "loginResult";
    }

    public String renRenLoginResult() throws Exception {
        try {
            if (getLoginedUserInfo() != null) {
                unvalidAccess();
                return "index";
            }
            boolean res = false;
            String code = getFormData("code");
            LOG.debug("code:"+code);
            if (code != null && !code.equals("")) {
                String rrOAuthTokenEndpoint = "https://graph.renren.com/oauth/token";
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("client_id", RenrenUtil.APP_KEY);
                parameters.put("client_secret", RenrenUtil.APP_SERCET);
                parameters.put("redirect_uri", RenrenUtil.redirectUri);
                parameters.put("grant_type", "authorization_code");
                parameters.put("code", code);
                
                System.out.println("APP_KEY:"+RenrenUtil.APP_KEY);
                System.out.println("APP_SERCET:"+RenrenUtil.APP_SERCET);
                System.out.println("redirectUri:"+RenrenUtil.redirectUri);
                System.out.println("code:"+code);
                
                
                
                
                String tokenResult = HttpURLUtils.doPost(rrOAuthTokenEndpoint, parameters);
                JSONObject tokenJson = (JSONObject) JSONValue.parse(tokenResult);
                
                String accessToken = (String) tokenJson.get("access_token");
                RenrenApiConfig.renrenApiKey=RenrenUtil.APP_KEY;
                RenrenApiConfig.renrenApiSecret=RenrenUtil.APP_SERCET;
                RenrenApiClient apiClient = new RenrenApiClient(accessToken, true);
                int rrUid = apiClient.getUserService().getLoggedInUser();
                
                
                JSONArray userInfo = apiClient.getUserService().getInfo(String.valueOf(rrUid), "uid,name,tinyurl");
                JSONObject user = (JSONObject) userInfo.get(0);
                String uid = ((Long) user.get("uid")) + "";
                String name = (String) user.get("name");
                String otherAccountUserImage = user.get("tinyurl").toString();
                otherAccountLogin(uid, "renren", name, otherAccountUserImage);

                res = true;
            }

            if (res) {
                setFormData("result", "success");
            } else {
                setFormData("result", "failed");
            }
        } catch (Exception e) {
            LOG.error("renRenLoginResult 执行失败！", e);
            setFormData("result", "failed");
        }
        return "loginResult";
    }

    public String weiBoLoginResult() throws Exception {
        try {
            if (getLoginedUserInfo() != null) {
                unvalidAccess();
                return "index";
            }
            boolean res = false;
            String code = getFormData("code");

            if (code != null && !code.equals("")) {
                PostParameter[] postParameters = new PostParameter[] { new PostParameter("client_id", WeiBoUtil.APP_ID),
                        new PostParameter("client_secret", WeiBoUtil.APP_Sercet), new PostParameter("grant_type", "authorization_code"),
                        new PostParameter("code", code), new PostParameter("redirect_uri", WeiBoUtil.redirect_URI) };

                AccessToken accessToken = new AccessToken(Weibo.client.post(WeiBoUtil.accessTokenURL, postParameters, false));
                String access_token = accessToken.getAccessToken();
                Weibo weibo = new Weibo();
                weibo.setToken(access_token);
                weibo4j.org.json.JSONObject uid = Weibo.client.get(WeiBoUtil.baseURL + "account/get_uid.json").asJSONObject();
                User user = new User(Weibo.client.get(WeiBoUtil.baseURL + "users/show.json",
                        new PostParameter[] { new PostParameter("uid", uid.getString("uid")) }).asJSONObject());
                String otherAccountUserImage = user.getProfileImageUrl();
                otherAccountLogin(user.getId(), "sina", user.getName(), otherAccountUserImage);

                res = true;
            }

            if (res) {
                setFormData("result", "success");
            } else {
                setFormData("result", "failed");
            }
        } catch (Exception e) {
            LOG.error("weiBoLoginResult 执行失败！", e);
            setFormData("result", "failed");
        }
        return "loginResult";
    }
    
    public String tencentLoginResult(){
    	try{
	    	boolean res = false;
	        String access_token = getFormData("access_token");
	        
	        LOG.info("access_token:" + access_token);
	        if (access_token != null && !access_token.equals("")) {
	            HttpClient client = new HttpClient();
	            GetMethod getMethod = new GetMethod("https://graph.qq.com/oauth2.0/me?access_token=" + access_token);
	            client.executeMethod(getMethod);
	            String json = getMethod.getResponseBodyAsString();
	            json = json.replace("callback( ", "").replace(" );", "");
	            weibo4j.org.json.JSONObject jsonObj = new weibo4j.org.json.JSONObject(json);
	            String openID = jsonObj.get("openid").toString();
	            getMethod = new GetMethod("https://graph.qq.com/user/get_user_info?access_token=" + access_token + "&oauth_consumer_key="
	                    + QQUtil.APP_ID + "&openid=" + openID);
	            client.executeMethod(getMethod);
	             json = getMethod.getResponseBodyAsString();
	            jsonObj = new weibo4j.org.json.JSONObject(json);
	            String nick = jsonObj.getString("nickname");
	            String otherAccountUserImage = jsonObj.getString("figureurl");
//	            form.setJsonMsg("", true, nick+"  "+otherAccountUserImage+"  "+openID);
	            otherAccountLogin(openID, "tencent", nick, otherAccountUserImage);
	            res = true;
	        }
	        if (res) {
	            setFormData("result", "success");
	        } else {
	            setFormData("result", "failed");
	        }
	       
	    } catch (Exception e) {
	        LOG.error("cententLoginResult 执行失败！", e);
	        setFormData("result", "failed");
	    }
    	return "loginResult";
	   
//	    return JSON_PAGE;
    }

    public String tWeiboLoginResult() throws Exception {
        try {
            if (getLoginedUserInfo() != null) {
                unvalidAccess();
                return "index";
            }
            setFormData("accountTypeID", "tencent");
        } catch (Exception e) {
            LOG.error("tWeiboLoginResult 执行失败！", e);
            setFormData("result", "failed");
        }
        return "loginResult";
    }

    private void otherAccountLogin(String account, String accountTypeID, String nick, String otherAccountUserImage) throws Exception {
        try {
            setFormData("account", account);
            setFormData("accountTypeID", accountTypeID);
            setFormData("nick", nick);
            setFormData("otherAccountUserImage", otherAccountUserImage);

            //验证用户是否存在
            UserInfoExample example = new UserInfoExample();
    		example.createCriteria().andOtheraccountEqualTo(StringUtils.trimToEmpty(account));
    		List<UserInfo> list = userInfoService.selectByExample(example);
    		if (list == null || list.isEmpty()) {
    			UserInfo userInfo  = new UserInfo();
                userInfo.setOtheraccount(account);
                userInfo.setOtheraccountflag(accountTypeID);
                userInfo.setNick(nick);
                userInfo.setOtheraccountuserimage(otherAccountUserImage);
                
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
                
                
//        		setFormData("topicNumber", "0");
//        		setFormData("voteNumber", "0");
//        		setFormData("fansNumber", "0");
//        		setFormData("followNumber", "0");
//        		setFormData("membersFlag", "0");
//        		setFormData("onlineFlag", "1");
                
                
                
//                userInfo.setEmail("");
//        		
//        		userInfo.setPassword("");
//        		
//        		
//        		
//        		
//        		userInfo.setSex(false);
//        		userInfo.setWhat("");
//        		userInfo.setPhone("");
//        		userInfo.setMobile("");
//        		userInfo.setTownid(0);
//        		userInfo.setPostalcode("");
//        		userInfo.setDeliverytypeid(0);
//        		userInfo.setDeliverytimeid(0);
//        		userInfo.setPaytypeid(0);
//        		userInfo.setUsermoney(0f);
//        		userInfo.setBuyscore(0);
//        		userInfo.setActivationcode("");
//        		userInfo.setActivationdate("");
//        		userInfo.setFirstname("");
//        		userInfo.setLastname("");
//        		userInfo.setHobby("");
//        		userInfo.setDeclaration("");
//        		userInfo.setBirthday("");
//        		userInfo.setDegreeid(0);
//        		userInfo.setIncometypeid(0);
//        		userInfo.setJoinstatus(0);
//        		userInfo.setOnlineflag(true);
//        		userInfo.setTopicnumber(0);
//        		userInfo.setVotenumber(0);
//        		userInfo.setFansnumber(0);
//        		userInfo.setFollownumber(0);
//        		userInfo.setUserlevel(0);
//        		userInfo.setMembersflag(true);
//        		userInfo.setActivationcodejoincommunity("");
//        		userInfo.setWebsite("");
//        		userInfo.setFood("");
//        		userInfo.setBooks("");
//        		userInfo.setPlot("");
//        		userInfo.setPrograms("");
//        		userInfo.setEntertainment("");
//        		userInfo.setTourist("");
//        		userInfo.setDigital("");
//        		userInfo.setSports("");
//        		userInfo.setNewspapers("");
//        		userInfo.setBgid("");
//        		userInfo.setCommunitytownid(0);
//        		userInfo.setPersonality("");
//        		userInfo.setFigure("");
//        		userInfo.setDressstyle("");
//        		userInfo.setInterests("");
//        		userInfo.setTraveldestination("");
//        		userInfo.setLovetoreadmagazinesandbooks("");
//        		userInfo.setOftengotowebsite("");
//        		userInfo.setLikestar("");
//        		userInfo.setGoodatsports("");
//        		userInfo.setLikemovie("");
//        		userInfo.setLikefood("");
//        		userInfo.setLikemakingfriends("");
//        		userInfo.setLastactiontime("");
//        		userInfo.setName("");
//        		userInfo.setImage("");
//        		userInfo.setAddress("");
        		
        		int result = userInfoService.insert(userInfo);
        		
        		
        		
        		
        		if (result != 0) {
        			List<UserInfo> registAfterUserInfoList = userInfoService.selectByExample(example);
        			setCurrentUser(registAfterUserInfoList.get(0));
				}else{
					setCurrentUser(userInfo);
				}
    		}else {
    			UserInfo currUserInfo =list.get(0); 
//    			currUserInfo.setOtheraccountuserimage(currUserInfo.getImage());
    			setCurrentUser(currUserInfo);
			}
    		UserInfo curUser = getCurrentUser();
			int userid = curUser.getUserid();
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
				ecookie = new Cookie("email",curUser.getEmail());
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
			
        } catch (Exception e) {
            LOG.error("第三方登录异常", e);
        }
        /**
         * Hashtable<String, String> userTypeKey = new Hashtable<String,
         * String>(); userTypeKey.put("otherAccounTypeID", accountTypeID);
         * userTypeKey.put("otherAccountFlag", "1");
         * userTypeKey.put("otherAccount", account); Vector<Hashtable<String,
         * String>> userDatas = DBProxy.query(getConnection(), "user",
         * userTypeKey); if (userDatas.size() > 0) { Hashtable<String, String>
         * key = new Hashtable<String, String>(); key.put("otherAccount",
         * account); key.put("otherAccounTypeID", accountTypeID);
         * key.put("otherAccountFlag", "1");
         *
         * Hashtable<String, String> value = new Hashtable<String, String>();
         * value.put("loginTime", DateTimeUtil.getCurrentDateTime());
         * value.put("loginIP", getRequestIPInfo()); value.put("onlineFlag",
         * "1"); value.put("otherAccountUserImage", otherAccountUserImage);
         * DBProxy.update(getConnection(), "user", key, value);
         *
         * Vector<Hashtable<String, String>> usersXin =
         * DBProxy.query(getConnection(), "user_V", key);
         *
         * setLoginedUserInfo(usersXin.get(0));
         *
         * DataCache.getInstance().getOnlineUser().put(usersXin.get(0).get("userID"),
         * System.currentTimeMillis() + ""); } else { String userID =
         * IndexGenerater.getTableIndex("user", getConnection());
         * setFormData("userID", userID); setFormData("nick", nick.equals("") ?
         * StringUtil.limitStringLength(account, 10) :
         * StringUtil.limitStringLength(nick, 10)); setFormData("registerTime",
         * DateTimeUtil.getCurrentDateTime()); setFormData("registerIP",
         * getRequestIPInfo()); setFormData("loginTime",
         * DateTimeUtil.getCurrentDateTime()); setFormData("loginIP",
         * getRequestIPInfo()); setFormData("userMoney", "0.00");
         * setFormData("buyScore", "0"); setFormData("topicNumber", "0");
         * setFormData("voteNumber", "0"); setFormData("fansNumber", "0");
         * setFormData("followNumber", "0"); setFormData("membersFlag", "0");
         * setFormData("onlineFlag", "1"); setFormData("otherAccounTypeID",
         * accountTypeID); setFormData("otherAccountFlag", "1");
         * setFormData("otherAccount", account);
         * setFormData("otherAccountUserImage", otherAccountUserImage);
         * DBProxy.insert(getConnection(), "user", getFormDatas());
         *
         * Hashtable<String, String> key = new Hashtable<String, String>();
         * key.put("userID", userID); Vector<Hashtable<String, String>> users =
         * DBProxy.query(getConnection(), "user_V", key);
         *
         * Hashtable<String, String> value = new Hashtable<String, String>();
         * value.put("loginTime", DateTimeUtil.getCurrentDateTime());
         * value.put("loginIP", getRequestIPInfo()); value.put("onlineFlag",
         * "1"); DBProxy.update(getConnection(), "user", key, value);
         *
         * setLoginedUserInfo(users.get(0));
         *
         * DataCache.getInstance().getOnlineUser().put(users.get(0).get("userID"),
         * System.currentTimeMillis() + ""); }
         */
    }

    public String getAuthorizeUrl() {
        return authorizeUrl;
    }

    public void setAuthorizeUrl(String authorizeUrl) {
        this.authorizeUrl = authorizeUrl;
    }

    public Object getModel() {
        return form;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
