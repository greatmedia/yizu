<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>买SWATCH白蛇传奇就送港澳游！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="<%=basePath %>css/style.css" type="text/css" rel="stylesheet" />
	<script src="js/show_window.js" type="text/javascript"></script>
	<script src="js/jquery.js" type="text/javascript"></script>
  </head>
  
  <body>
    <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
    <div class="main_cont" style="text-align: center;">
    	<a target="_blank" href="http://mall.1mily.com/product/3223.html"><img src="<%=basePath%>act/img/activity0415.jpg"/></a>
    </div>
  </body>
</html>
