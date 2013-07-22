package com.yizu.proj.sys.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.opensymphony.xwork2.ModelDriven;
import com.yizu.proj.base.action.BaseAction;
import com.yizu.proj.sys.action.form.UserInfoForm;
import com.yizu.proj.sys.beans.UserInfo;
import com.yizu.proj.sys.service.UserInfoService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Scope;
import simpleWebFrame.util.DateTimeUtil;
import weibo4j.Weibo;
import weibo4j.http.AccessToken;
import weibo4j.model.PostParameter;
import weibo4j.model.User;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.utils.HttpURLUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.UserGetRequest;
import com.taobao.api.response.UserGetResponse;
import com.yizu.proj.utils.QQUtil;
import com.yizu.proj.utils.RenrenUtil;
import com.yizu.proj.utils.TaobaoUtil;
import com.yizu.proj.utils.WeiBoUtil;

/**
 * 授权
 * 
 * @author congml
 * @date 2012-5-31 上午11:05:06
 * @version V1.0
 */
@Scope("prototype")
public class AuthorizeActinnon extends BaseAction  implements ModelDriven {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(AuthorizeActinnon.class);
    private String authorizeUrl;
    private UserInfoForm form = new UserInfoForm();

    private UserInfoService userInfoService;

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
			authorizeUrl = QQUtil.login_URL;

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

	public String taoBaoLoginResult() throws Exception {
		try {
			if (getLoginedUserInfo() != null) {
				unvalidAccess();
				return "loginResult"; // 需要处理
			}
			boolean res = false;

			String notify_result = getFormData("notify_result");
			String taobao_user_nick = getFormData("taobao_user_nick");
			String app_key = getFormData("app_key");
			String notify_type = getFormData("notify_type");
			// 获得头像
			String taobao_user_nick_encode = java.net.URLEncoder.encode(taobao_user_nick, "UTF-8");
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
			String otherAccountUserImage = taoBaoUserInfo.get("avatar").toString();
			if (notify_result.equals("success") && app_key.equals(TaobaoUtil.APP_KEY) && notify_type.equals("identify")) {
				otherAccountLogin(taobao_user_nick, "1", "", otherAccountUserImage + "_30x30.jpg");
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
				return "loginResult";
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
				
				if(LOG.isDebugEnabled()){
					LOG.debug("client_id:"+  RenrenUtil.APP_KEY);
					LOG.debug("client_secret:"+ RenrenUtil.APP_SERCET);
					LOG.debug("redirect_uri:"+ RenrenUtil.redirectUri);
					LOG.debug("grant_type:"+ "authorization_code");
					LOG.debug("code:"+ code);
				}
				
				String tokenResult = HttpURLUtils.doPost(rrOAuthTokenEndpoint, parameters);
				JSONObject tokenJson = (JSONObject) JSONValue.parse(tokenResult);

				String accessToken = (String) tokenJson.get("access_token");

				RenrenApiClient apiClient = new RenrenApiClient(accessToken, true);
				int rrUid = apiClient.getUserService().getLoggedInUser();
				JSONArray userInfo = apiClient.getUserService().getInfo(String.valueOf(rrUid), "uid,name,tinyurl");
				JSONObject user = (JSONObject) userInfo.get(0);
				String uid = ((Long) user.get("uid")) + "";
				String name = (String) user.get("name");
				String otherAccountUserImage = user.get("tinyurl").toString();
				otherAccountLogin(uid, "4", name, otherAccountUserImage);

				res = true;
			}

			if (res) {
				setFormData("result", "success");
			} else {
				setFormData("result", "failed");
			}
		} catch (Exception e) {
			LOG.error("tWeiboLoginResult 执行失败！", e);
			setFormData("result", "failed");
		}
		return "loginResult";
	}

	public String weiBoLoginResult() throws Exception {
		try {
			if (getLoginedUserInfo() != null) {
				unvalidAccess();
				return "loginResult";
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
				otherAccountLogin(user.getId(), "2", user.getName(), otherAccountUserImage);

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

	public String tWeiboLoginResult() throws Exception {
		try {
			if (getLoginedUserInfo() != null) {
				unvalidAccess();
				return "loginResult";
			}
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
				// json = getMethod.getResponseBodyAsString();
				InputStream in = getMethod.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
				String line = "";
				StringBuffer buffer = new StringBuffer();
				while ((line = br.readLine()) != null) {
					buffer.append(line);
				}
				json = buffer.toString();
				jsonObj = new weibo4j.org.json.JSONObject(json);

				String nick = jsonObj.getString("nickname");
				String otherAccountUserImage = jsonObj.getString("figureurl");

				otherAccountLogin(openID, "3", nick, otherAccountUserImage);

				res = true;
			}

			if (res) {
				setFormData("result", "success");
			} else {
				setFormData("result", "failed");
			}
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

            UserInfo userInfo  = new UserInfo();
            userInfo.setOtheraccount(account);
            userInfo.setOtheraccountflag(accountTypeID);
            userInfo.setNick(nick);
            userInfo.setOtheraccountuserimage(otherAccountUserImage);
            setCurrentUser(userInfo);
		} catch (Exception e) {
			LOG.error("发送邮件失败！", e);
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
