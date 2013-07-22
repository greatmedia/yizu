package com.yizu.proj.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class SMTPAuthenticator extends Authenticator {
	private String username;

	private String password;

	SMTPAuthenticator(String paramString1, String paramString2) {
		this.username = paramString1;
		this.password = paramString2;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.username, this.password);
	}
}
