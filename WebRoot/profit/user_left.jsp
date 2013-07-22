<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style.css" type="text/css" rel="stylesheet" />
	<script src="js/user_left.js" type="text/javascript"></script>
	<script src="js/jquery.js" type="text/javascript"></script>
   <div class="col">
       	<div class="box"> 
            <div class="items">
                <div class="feed_container" id="userImg"></div>
                <div id='userMsg'></div>
                <p class="username" id="userName"></p>
            </div>            
            <div class="summary3">
            <p class="c_grey3">
                 关于自己：<br>  <label id="userSummary"></label>
            </p>
            	
            </div>
            
        </div>
         
   	  </div>
