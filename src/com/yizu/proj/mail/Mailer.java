package com.yizu.proj.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.yizu.proj.config.AppParametersConfig;

public class Mailer {
	public static boolean sendSimpleMail(String paramString1, String paramString2, String paramString3, String fromName) throws UnsupportedEncodingException, MessagingException {
		String str1 = AppParametersConfig.getParameter("mailServer.smtpHost");
		String str2 = AppParametersConfig.getParameter("mailServer.userName");
		String str3 = AppParametersConfig.getParameter("mailServer.password");
		return sendSimpleMail(str1, str2, str3, fromName, paramString1, paramString2, paramString3);
	}

	public static boolean sendSimpleMail(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6,
			String paramString7) throws UnsupportedEncodingException, MessagingException {
		Session localSession = getMailSession(paramString1, paramString2, paramString3);
		MimeMessage localMimeMessage = new MimeMessage(localSession);
		InternetAddress localInternetAddress = new InternetAddress(paramString2);
		if ((paramString4 != null) && (!paramString4.equals("")))
			localInternetAddress.setPersonal(paramString4);
		localMimeMessage.setFrom(localInternetAddress);
		InternetAddress[] arrayOfInternetAddress = { new InternetAddress(paramString5) };
		localMimeMessage.setRecipients(Message.RecipientType.TO, arrayOfInternetAddress);
		localMimeMessage.setSubject(paramString6, "GB2312");
		localMimeMessage.setContent(paramString7, "text/plain");
		localMimeMessage.setText(paramString7, "GB2312");
		Transport.send(localMimeMessage);
		return true;
	}

	public static boolean sendHtmlMail(String paramString1, String paramString2, String paramString3, String fromName) throws Exception {
		String str1 = AppParametersConfig.getParameter("mailServer.smtpHost");
		String str2 = AppParametersConfig.getParameter("mailServer.userName");
		String str3 = AppParametersConfig.getParameter("mailServer.password");
		return sendHtmlMail(str1, str2, str3, fromName, paramString1, paramString2, paramString3);
	}

	public static boolean sendHtmlMail(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6,
			String paramString7) throws Exception {
		Session localSession = getMailSession(paramString1, paramString2, paramString3);
		MimeMessage localMimeMessage = new MimeMessage(localSession);
		InternetAddress localInternetAddress = new InternetAddress(paramString2);
		if ((paramString4 != null) && (!paramString4.equals("")))
			localInternetAddress.setPersonal(paramString4);
		localMimeMessage.setFrom(localInternetAddress);
		InternetAddress[] arrayOfInternetAddress = { new InternetAddress(paramString5) };
		localMimeMessage.setRecipients(Message.RecipientType.TO, arrayOfInternetAddress);
		localMimeMessage.setSubject(paramString6, "utf-8");
		MimeMultipart localMimeMultipart = new MimeMultipart();
		MimeBodyPart localMimeBodyPart = new MimeBodyPart();
		localMimeBodyPart.setContent(paramString7, "text/html; charset=utf-8");
		localMimeMultipart.addBodyPart(localMimeBodyPart);
		localMimeMessage.setContent(localMimeMultipart);
		Transport.send(localMimeMessage);
		return true;
	}

	private static Session getMailSession(String paramString1, String paramString2, String paramString3) {
		Properties localProperties = System.getProperties();
		localProperties.put("mail.smtp.host", "mail.atb2c.com");
		localProperties.put("mail.smtp.auth", "true");
		localProperties.put("mail.mime.charset", "GB2312");
		SMTPAuthenticator localSMTPAuthenticator = new SMTPAuthenticator(paramString2, paramString3);
		return Session.getInstance(localProperties, localSMTPAuthenticator);
	}
	
	public static void main(String[] args) {
		try {
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
					"<p><a class='link' href='http://login.tudou.com/lost.do?act=reset&amp;c=a96a7a9b14d57ef3d2dd3e956dca1a4c' target='_blank'>http://login.tudou.com/lost.do?act=reset&amp;c=a96a7a9b14d57ef3d2dd3e956dca1a4c</a></p>"+
					"<p>如果以上链接不能点击，你可以复制网址URL，然后粘贴到浏览器地址栏打开，完成更新。</p>"+
					"<p>- 逸族网</p>"+
					"<p class='info'>（这是一封自动发送的邮件，请不要直接回复）</p>"+
					"<p class='info'>－如果你没有使用过“找回密码”功能，请忽略本邮件，你的逸族网账户依然是安全的。</p>"+
					"</p></body>"+
					"</html> ";
			
			boolean flag = sendHtmlMail("625342225@qq.com", "【逸族网】-密码找回", content, "逸族网");
			System.out.println("发送状态：" + flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
