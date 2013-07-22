<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>"/>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>热门用户</title>
	<link href="css/topic.css" type="text/css" rel="stylesheet" />
	<link href="css/style.css" type="text/css" rel="stylesheet" />
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/topicHotUser.js" type="text/javascript"></script>
	<script src="js/topicall.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/imgChange.js" type="text/javascript"></script>
	<script>var basePath = "<%=basePath%>";</script>
</head>

<body style="display: none;" id="body">
<!--头部代码 -->
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="topic_center">
    <div class="topic1_center" >
        <div class="topic_pk_left">
            <div class="topic_subnav" style="margin-top:0;">
                <div class="topic_desp">
                    
                    <div class="topic_desp1">
                        <h4>热门用户：</h4>
                        <div class="topic_desp2">
                          <a href="javascript:void(0);"><img src="images/pro_navco.gif" onclick="isdisplay(1);" /></a>
                          <a href="javascript:void(0);"><img src="images/pro_br.gif" /></a>
                          <a href="javascript:void(0);"><img src="images/nao_su.gif" onclick="isdisplay(2);" /></a>
                            
                        </div>
                    </div>
                    
                    <div class="subtop_lun" id="subtop_lun">
	                    <ul id="hotUser">
	                    </ul>
	                    <div class="topic_more" id="topic_more" style="text-align: center;display: none;"><a target="_blank" onclick="moreHotUser();" >查看更多</a></div>
                    </div>
                    
                    
                    <div class="subtop_mav" id="subtop_mav" style="display: none;">
                         <div class="sub_mav0">
                             <ul id="hostUser1">
                             </ul>
                         </div>
                         <div class="sub_mav2">
                             <ul id="hostUser2">
                             </ul>
                         </div>
                          <div class="topic_more" id="topic_more1" style="text-align: center;display: none;"><a target="_blank" onclick="moreHotUser();" >查看更多</a></div>
                    </div>
                </div>
             </div>
         </div>
        <div class="topic_pk_right">
           <!--热门话题 -->
            <div class="pk_about">
                    <h3>热门话题:</h3>
                    <!--热门话题7个-->
                    <ul id="hotTopics">
                    </ul>
                    <!--热门话题7个-->
                    <div class="topic_more" id="topic_moresss" style="display: none;"><a id="moreid" href="<%=basePath%>quanzi/newTopics.jsp ">查看更多</a></div>
                    
                </div>
           
        </div>
    </div>
</div>
</body>
</html>
