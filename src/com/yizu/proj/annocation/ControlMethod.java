package com.yizu.proj.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yizu.proj.utils.SecurityLevel;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ControlMethod {
	/**
	 * 功能点名称
	 * @return
	 */
	String name();
	/**
	 * 功能点描述
	 * @return
	 */
	String desc();
	/**
	 * 是否为功能点入口方法，在构建菜单时，应列表entrance为true的功能点供选择
	 * @return
	 */
	boolean entrance() default false;
	/**
	 * 安全级别，数字约大，安全级别约高，none即为任意访问
	 * @return
	 */
	SecurityLevel securityLevel() default SecurityLevel.five;
}
