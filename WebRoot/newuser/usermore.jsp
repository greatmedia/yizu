<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>个人中心</title>
    <link href="css/user_me.css" type="text/css" rel="stylesheet" />
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="js/usermore.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/public_min.js" type="text/javascript"></script> 
	<script type="text/javascript"> var basePath = "<%=basePath%>";</script>
</head>

<body >
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="center_bj ">
     <div class="personal_middle1">
		<div class="personal_middle1_left">
			<div class="personal_middle1_left_top">
				<ul>
					<li>
						<a><span>逸族身份：</span><label id="usersex"></label></a>
					</li>
					<li>
						<a><span>个人素描：</span><label id="aboutme"></label></a>
					</li>
					<li>
						<a><span>所在城市：</span><label id="useraddress"></label></a>
					</li>
					<li>
						<a><span>兴趣爱好：</span><label id="userhobby"></label></a>
					</li>
				</ul>
			</div>
		</div>
		<div class="personal_middle1_right">
			<div class="personal_middle1_right_top">
				<div class="personal_middle1_right_top_bj">
					<img id="user_bj"/>
				</div>
				<div class="personal_middle1_right_top_photo">
					<dl>
						<dt><img id="userimg"/></dt>
						<dd><span id="nick"></span></dd>
					</dl>
				</div>
			</div>
			<div class="personal_middle1_right_bottom">
				<ul>
					<li>
						<a id="huatiCount"></a><br /><span>发表的言论</span>
					</li>
					<li style="border-right: none;">
						<a id="circleCount"></a><br /><span>创建的圈子</span>
					</li>
					<li style="border-right: none;">
						<a id="myvoteCount"></a><br /><span>参与的投票</span>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="personal_middle2" id="hesaid">
	</div>
	<div class="pl_page" id="topic_pl_page"></div>
</div>

</body>
</html>