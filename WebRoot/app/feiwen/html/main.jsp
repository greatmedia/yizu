<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    <script src="<%=basePath%>app/feiwen/js/jquery.js" type="text/javascript"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <script type="text/javascript">
  var basePath = "<%=basePath%>";
  function openssss()
  {
   var content = $("#content").val();
	  if(content.length<1)
	  {
	  	alert("请输入搜索内容");
	 	 return false;
	  }
	  var str = "app/feiwen/html/searchfeiwen.jsp?tid=1&pageNum=1&pageSize=10&keyword="+content;
  	window.open(basePath + str);
  }
  </script>
  <body>
  <a href="javascript:void(0);" onclick="openssss()">打开网页</a>
  <input type="text"  id="content" >
  </body>
</html>
