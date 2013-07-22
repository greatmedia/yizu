package com.yizu.proj.util.mail;

public class Test {
	public static void main(String[] args){   
     //设置 邮件
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.163.com");    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("10086@163.com");    
     mailInfo.setPassword("10086");//您的邮箱密码    
     mailInfo.setFromAddress("10086@163.com");    
     mailInfo.setToAddress("1115955757@qq.com");
     mailInfo.setSubject("逸族网 - 开启你的第二人生");    
     String content = "<!DOCTYPE HTML PUBLIC 'null'><html><head><title>逸族网 - 开启你的第二人生</title></head><body><style>body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td{margin:0;padding:0}body{padding:20px;width:500px;color:#222;background:#fff;font-size:12px;line-height:1.5;font-family:Tahoma,sans-serif}a{color:#14647d;text-decoration:none;_font-family:Arial,Simsun,sans-serif}a:hover{text-decoration:underline}img{display:block;border:0}.logo{display:block;margin-bottom:10px;padding-bottom:10px;border-bottom:2px solid #ebebeb;}.title{margin-bottom:10px;font-family:'微软雅黑',Tahoma,sans-serif;font-size:32px;font-weight:200;}p{margin-bottom:10px;}p strong,p span, p .link{font-size:14px;}.link{color:#f60;text-decoration:underline;}.info{color:#808080;}</style><a href='http://www.1mily.com' target='_blank' class='logo' title='逸族网'><img src='http://www.1mily.com:80/images/logo.png'></a><h3 class='title'>[逸族网]</h3><p><span><strong>点击以下链接</strong></span></p><p><a class='link' href='http://login.tudou.com/lost.do?act=reset&amp;c=a96a7a9b14d57ef3d2dd3e956dca1a4c' target='_blank'>http://1mily.com/</a></p><p>如果以上链接不能点击，你可以复制网址URL，然后粘贴到浏览器地址栏打开，完成更新。</p><p>- 逸族网</p><p class='info'>（这是一封自动发送的邮件，请不要直接回复）</p><p class='info'></p></p></body></html>";
     mailInfo.setContent(content);
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
//   sms.sendTextMail(mailInfo);//发送文体格式    
     sms.sendHtmlMail(mailInfo);//发送html格式   
   }
}
