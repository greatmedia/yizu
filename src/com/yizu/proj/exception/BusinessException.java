package com.yizu.proj.exception;

public class BusinessException extends RuntimeException{
	public BusinessException(){}
	public BusinessException(String msg){
		super(msg);
	}
	public BusinessException(Throwable throwable){
		super(throwable);
	}
	public BusinessException(String msg,Throwable throwable){
		super(msg, throwable);
	}
}
