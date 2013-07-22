package com.yizu.proj.sys.action.form;

import com.yizu.proj.base.action.BaseForm;
import com.yizu.proj.sys.beans.FeiWen;
import com.yizu.proj.sys.beans.UserInfo;

/**
 * @author 龙流平(LongLiuPing)  微博：http://t.qq.com/longliuping
 * @version 创建时间：2013-2-17 下午04:01:03
 */
public class WeixinForm extends BaseForm<UserInfo>{
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	private String token;
	private FeiWen feiwen;
	
	public FeiWen getFeiwen() {
		return feiwen;
	}
	public void setFeiwen(FeiWen feiwen) {
		this.feiwen = feiwen;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
