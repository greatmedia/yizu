package com.yizu.proj.utils;

import java.io.Serializable;
import java.util.Hashtable;

import com.yizu.proj.dto.UserDto;

public class UserOnlineUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private static UserOnlineUtils instance = new UserOnlineUtils();

	
	private Integer userOnlineSize;
	private Hashtable<String, UserDto> onlineUser = new Hashtable<String, UserDto>(); 
	

	public static UserOnlineUtils getInstance() {
		return instance;
	}

	public Hashtable<String, UserDto> getOnlineUser() {
		return onlineUser == null ? new Hashtable<String, UserDto>() : onlineUser;
	}

	public void setOnlineUser(Hashtable<String, UserDto> onlineUser) {
		this.onlineUser = onlineUser;
	}

	public Integer getUserOnlineSize() {
		return userOnlineSize == null ? 0 : userOnlineSize;
	}

	public void setUserOnlineSize(Integer userOnlineSize) {
		this.userOnlineSize = userOnlineSize;
	}

}
