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
<link href="css/user_me.css" type="text/css"  rel="stylesheet"/>
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<link href="<%=basePath%>css/topic.css" type="text/css" rel="stylesheet" />
<script src="js/user_me.js" type="text/javascript"></script>
<%--<link href="css/style.css" type="text/css" rel="stylesheet" />--%>
<script type="text/javascript"> var basePath = "<%=basePath%>";</script>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="center_bj">
	<div class="usename_bj" >
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
				</div>
				<div class="personal_middle1_right">
					<div class="personal_middle1_right_top">
						<div class="personal_middle1_right_top_bj">
							<img id="user_bj"/>
						</div>
						<div class="personal_middle1_right_top_photo">
							<dl>
								<dt>
									<div class="test1_mask" ></div>
	                            	<div class="test1_cont"><img  id="userimg"/></div>
								</dt>
								<dd><span id="nick"></span></dd>
							</dl>
						</div>
					</div>
					<div class="personal_middle1_right_bottom">
						<ul>
							<li>
								<a href="javascript:void(0)" onclick="$('#hesaid').show(); window.location.hash='hesaid'" id="huatiCount"></a><br /><span>发表的言论</span>
							</li>
							<li>
								<a id="circleCount" href="javascript:getMoreCreate()"></a><br /><span>创建的圈子</span>
							</li>
<%--							<li style="border-right: none;">--%>
<%--								<a id="myvoteCount"></a><br /><span>参与的投票</span>--%>
<%--							</li>--%>
						</ul>
					</div>
				</div>
			</div>
			
			<div class="join_circle" id="join">
				<div class="join_circle_top"><h2>加入的圈子</h2><h3><a id="morecircle_join" style="display:none;">查看更多</a></h3></div>
					<div class="join_circle_middle" id="jiaruli">
					</div>
					<div class="join_circle_bottom" id="pagediv_join" style="display:none;"></div>
				</div>
        	</div>
        	
        	<div class="join_circle" style="display:none;" id="chuangjian">
				<div class="join_circle_top"><h2>创建的圈子</h2><h3><a id="morecircle_chuangjian" style="display:none;">查看更多</a></h3></div>
					<div class="join_circle_middle" id="chuangjianli">
					</div>
					<div class="join_circle_bottom" id="pagediv_chuangjian" style="display:none;"></div>
				</div>
        	</div>
        	
<%--        	<div class="user_lunbo">--%>
<%--	           <div class="u_name">--%>
<%--	               <h2><a>TA的圈子</a></h2>--%>
<%--	           </div>            --%>
<%--	           <div class="user_lun_x">--%>
<%--	               <div class="us_left">--%>
<%--	                   <ul>--%>
<%--	                      <li onclick="changeLi(1)" class="li" id="jiaru">加入</li>--%>
<%--	                      <li class="active" onclick="changeLi(2)" id="chuangjian">创建</li>--%>
<%--	                      --%>
<%--	                   </ul>--%>
<%--	               </div>--%>
<%--	               --%>
<%--	               <div class="ue_conx"  id="ue_jiaru"  style="display: block" >--%>
<%--	                    <div class="u_left" id="u_left_jiaru"><img src="images/u_left.gif" /></div>--%>
<%--	                    <div class="ue_conx_li" >--%>
<%--	                    <ul id="jiaruli">--%>
<%--	                    </ul>--%>
<%--	                    </div>--%>
<%--	                    <div class="u_right" id="u_right_jiaru"><a onclick="userJoinCircle('2');" href='javascript:void(0);'><img src="images/u_right.gif" /></a></div>--%>
<%--	               </div>--%>
<%--	               <div class="ue_conx" id="ue_chuangjian" style="display: none;">--%>
<%--	                    <div class="u_left" id="u_left_chuangjian"><img src="images/u_left.gif" /></div>--%>
<%--	                    <div  class="ue_conx_li" >--%>
<%--	                    <ul id="chuangjianli">--%>
<%--	                    </ul>--%>
<%--	                    </div>--%>
<%--	                    <div class="u_right" id="u_right_chuangjian"><a onclick="userCreateCircle('2');" href='javascript:void(0);'><img src="images/u_right.gif" /></a></div>--%>
<%--	               </div>--%>
<%--	               --%>
<%--	           </div>    --%>
<%--	        </div>--%>
<%--			<a name="maodian"></a>--%>
        	<div class="personal_middle2" id="hesaid" ></div>
        	<div class="u_more" id="u_more_topic" style=" text-align: center; display:none;"></div>
        	
        	
        	
	        
<%--	        <div class="unlist">--%>
<%--	            <div class="unlist_left">--%>
<%--	               <div class="u_text">--%>
<%--	                   <div class="u_name">--%>
<%--	                       <h2><a href="javascript:void(0);">TA的发言</a></h2>--%>
<%--	                       <div class="u_nametiao" id="hesay"></div>--%>
<%--	                   </div>--%>
<%--	                   <ul id="hesaycontent">--%>
<%--	                   </ul>--%>
	                   
<%--	               </div>--%>
<%--	            </div>--%>
<%--	            <div class="unlist_right">--%>
<%--	              <div class="u_contst">--%>
<%--	                   <div class="u_name">--%>
<%--	                       <h2><a href="javascript:void(0);">TA创建的投票</a></h2>--%>
<%--	                       <div class="u_nametiao" id="hisvote"></div>--%>
<%--	                   </div>--%>
<%--	                   <ul id="hisvotelist">--%>
<%--	                   </ul>--%>
<%--	                   <div class="u_more" id="u_more"><a href="javascript:void(0);">更多</a></div>--%>
<%--	               </div>--%>
<%--	            </div>--%>
<%--	        </div>--%>
	        
	        
	        
	        
	        
    	</div>
	</div>
<%--</div>--%>
</body>
</html>