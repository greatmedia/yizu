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
	<link href="css/user_me.css" type="text/css" rel="stylesheet"/>
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<link href="<%=basePath%>css/topic.css" type="text/css" rel="stylesheet" />
	<script src="js/usercenter.js" type="text/javascript"></script>
	<script type="text/javascript"> var basePath = "<%=basePath%>";</script>
	
</head>
<body>

<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="center_bj">
<div style="width:960px; margin:auto; height:1000px; background:url(images/bg.jpg) repeat scroll">
    <div class="user_left">
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
<%--				<div class="personal_middle1_left_bottom"><a href="usercontent_me/${user.userid }.html?tag=5">更改个人信息</a></div>--%>
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
<%--				 <div class="personal_middle1_right_fengmian"><a href="javascript:void(0);" onClick="BOX_show('window_zhc');">更改封面</a></div>--%>
			</div>
		</div>
    	
    	
<%--        <div class="user_center">--%>
<%--            <div class="user_bj"><img src="images/user_bj.gif" /></div>--%>
<%--            <div class="user_img" ><img id="userimg"/></div>--%>
<%--<!--            <div class="user_btn"><img src="images/user_btn.png" /></div>-->--%>
<%--            <div class="user_title">--%>
<%--                <h2><a id="nick"></a></h2>--%>
<%--                <p><span id="aboutme"></span></p>--%>
<%--            </div>--%>
<%--            <div class="user_context">--%>
<%--               <ul>--%>
<%--                     <li><a href="javascript:void(0);" id="huatiCount"></a><span>发言</span></li>--%>
<%--                     <li><a href="javascript:void(0);" id="circleCount"></a><span>圈子</span></li>--%>
<%--                     <li><a href="javascript:void(0);" id="myvoteCount"></a></em><span>投票</span></li>--%>
<%--               </ul>--%>
<%--            </div>--%>
<%--        </div>--%>
        
        
        
        
        <div class="user_list" id="selectcircle" style="display: none;">
           <div class="user_list_t">点击你所感兴趣的圈子</div>
           <ul id="showcircle">
              
           </ul>
           <div class="user_too"><a href="javascript:void(0);" onclick="nextStep();">下一步</a></div>
        </div>
        
    </div>
<!--    <div class="user_right">-->
<!--        <ul>-->
<!--            <li><span class="u-dian"></span><a href="javascript:void(0);">首页</a></li>-->
<!--            <li><span></span><a href="javascript:void(0);">个人资料</a></li>-->
<!--            <li><span></span><a href="javascript:void(0);">发言</a></li>-->
<!--            <li><span></span><a href="javascript:void(0);">圈子</a></li>-->
<!--            <li><span></span><a href="javascript:void(0);">投票</a></li>-->
<!--        </ul>-->
<!--    </div>-->
</div>
</div>
</body>
</html>