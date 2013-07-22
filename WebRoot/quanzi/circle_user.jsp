<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>圈子成员列表</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/circle.js" type="text/javascript"></script>
<script type="text/javascript">var basePath = "<%=basePath%>";</script>
<script src="js/circle_user.js" type="text/javascript"></script>

</head>

<body style="background: url('../images/bg.jpg') repeat scroll 0 0 transparent;color: #626262;font-family: '宋体';font-size: 12px;">
	<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
	<jsp:include flush="false" page="circletop.jsp"></jsp:include>
		<div class="circle_middle2">
  			<div class="circle_middle2_left_chengyuan">
  				<h1 style=" font-size:20px;">圈子所有成员</h1>
  				<div class="clear"></div>
  				<div id="allcircleuser">
  				</div>
  				<div class="circle3_middle2_left3" id="allcircleuser_more" style="display:none;">
					<a href="javascript:showUser(49,2,2)">加载更多</a>
				</div>
  			</div>
  			<div class="circle_middle2_right">
  				<img src="../images/guanggao1.jpg"/>
    		</div>
  		</div>
	</body>
</html>

