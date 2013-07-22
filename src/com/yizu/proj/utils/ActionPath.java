package com.yizu.proj.utils;

import com.yizu.proj.annocation.ControlMethod;

public class ActionPath {
	private String path;
	private ControlMethod controlMethod;
	public ActionPath(String path,ControlMethod controlMethod){
		this.path = path;
		this.controlMethod=controlMethod;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ControlMethod getControlMethod() {
		return controlMethod;
	}
	public void setControlMethod(ControlMethod controlMethod) {
		this.controlMethod = controlMethod;
	}
}
