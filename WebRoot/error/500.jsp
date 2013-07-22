<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
</head>

<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div style=" position:absolute; left:50%; top:50%; margin-left:-; margin-top:-;">服务器已经累坏了..请浏览其它页面....</div>
<script>var basePath = "<%=basePath%>";setTimeout("window.location=\""+basePath+"\"",5000);</script>
</body>
</html>
