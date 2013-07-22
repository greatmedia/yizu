package com.yizu.email;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	public String host;
	
	public String mail_head_name;
	
	public String mail_head_value;
	
	public String mail_to;
	
	public String mail_from;
	
	public String mail_subject;
	
	public String mail_body;
	
	public String personalName;
	
	public String getHost() {
		return "smtp.163.com";
	}

	public void setHost(String host) {
		this.host = "smtp.163.com";
	}

	public String getMail_head_name() {
		return "this is head of this mail";
	}

	public void setMail_head_name(String mailHeadName) {
		mail_head_name = "this is head of this mail";
	}

	public String getMail_head_value() {
		return "";
	}

	public void setMail_head_value(String mailHeadValue) {
		mail_head_value = "this is head of this mail";
	}

	public String getMail_to() {
		return mail_to;
	}

	public void setMail_to(String mailTo) {
		mail_to = mailTo;
	}

	public String getMail_from() {
		return "m18602090927@163.com";
	}

	public void setMail_from(String mailFrom) {
		mail_from = "m18602090927@163.com";
	}

	public String getMail_subject() {
		return mail_subject;
	}

	public void setMail_subject(String mailSubject) {
		mail_subject = mailSubject;
	}

	public String getMail_body() {
		return mail_body;
	}

	public void setMail_body(String mailBody) {
		mail_body = mailBody;
	}

	public String getPersonalName() {
//		return personalName;
		return "逸族网";
	}

	public void setPersonalName(String personalName) {
		this.personalName = "逸族网";
	}

	public void sendMail() throws SendFailedException{
		try {
			String content = "<!DOCTYPE HTML PUBLIC 'null'><html><head><title>逸族网 - 开启你的第二人生</title></head><body><style>body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{margin:0;padding:0}body{padding:20px;width:500px;color:#222;background:#fff;font-size:12px;line-height:1.5;font-family:Tahoma,sans-serif}a{color:#14647d;text-decoration:none;_font-family:Arial,Simsun,sans-serif}a:hover{text-decoration:underline}img{display:block;border:0}.logo{display:block;margin-bottom:10px;padding-bottom:10px;border-bottom:2px solid #ebebeb;}.title{margin-bottom:10px;font-family:'微软雅黑',Tahoma,sans-serif;font-size:32px;font-weight:200;}p{margin-bottom:10px;}p strong,p span, p .link{font-size:14px;}.link{color:#f60;text-decoration:underline;}.info{color:#808080;}</style><a href='http://www.1mily.com' target='_blank' class='logo' title='逸族网'><img src='http://www.1mily.com:80/images/logo.png'></a><h3 class='title'>[逸族网]找回密码</h3><p><span><strong>你使用“找回密码”功能。点击以下链接，设置你的新密码：</strong></span></p><p><a class='link' href='http://login.tudou.com/lost.do?act=reset&amp;c=a96a7a9b14d57ef3d2dd3e956dca1a4c' target='_blank'>http://login.tudou.com/lost.do?act=reset&amp;c=a96a7a9b14d57ef3d2dd3e956dca1a4c</a></p><p>如果以上链接不能点击，你可以复制网址URL，然后粘贴到浏览器地址栏打开，完成更新。</p><p>- 逸族网</p><p class='info'>（这是一封自动发送的邮件，请不要直接回复）</p><p class='info'>－如果你没有使用过“找回密码”功能，请忽略本邮件，你的逸族网账户依然是安全的。</p></p></body></html>";
			Properties props = new Properties();
			Authenticator auth = new Email_Autherticator();
			props.put("mail.smtp.host", this.getHost());
			props.put("mail.smtp.auth", "true");
			System.out.println(props);
			Session session = Session.getDefaultInstance(props,auth);
			MimeMessage message = new MimeMessage(session);
			message.setContent("Hello","text/plain");
			message.setSubject(this.getMail_subject());//mail_subject
			message.setText(content);//mail_body
			message.setHeader(this.getMail_head_name(), this.getMail_head_value());//mail_head_name,mail_head_value
			message.setSentDate(new Date());
			Address address = new InternetAddress(this.getMail_from(),this.getPersonalName());//mail_from,personalName
			message.setFrom(address);
			Address toaddress = new InternetAddress(this.getMail_to());//mail_to
			message.addRecipient(Message.RecipientType.TO,toaddress);
			System.out.println(message);
			Transport.send(message);
			System.out.println("Send Mail Ok!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return flag;
	}
}
