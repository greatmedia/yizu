package com.yizu.email;

public class Test {
	public static void main(String[] args) {
		Mail m = new Mail();
		try {
			m.setMail_to("1115955757@qq.com");
			m.setMail_subject("邮箱修改提示！");
			m.setMail_body("你的邮箱已经修改了！");
			m.sendMail();
		} catch (Exception e) {
			
		}
	}
}
