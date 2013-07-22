<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>用户中心</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

	</head>

	<body>
		<a href="<%=basePath %>userInfoAction_register.action">新增</a>
		<table border="1">
			<tr>
				<td>userid</td>
				<td>nick</td>
				<td>name</td>
			</tr>
			
			<s:iterator value="%{userInfoList}" var="userInfo" status="u">
				<tr>
					<td>${userInfo.userid }</td>
					<td>${userInfo.nick }</td>
					<td>${userInfo.name }</td>
					<td>${userInfo.email }</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>
