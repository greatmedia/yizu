package com.yizu.proj.dto;

import com.taobao.api.domain.User;

public class UserDto extends User {
	private static final long serialVersionUID = 1L;

	private String clientIp;

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

}
