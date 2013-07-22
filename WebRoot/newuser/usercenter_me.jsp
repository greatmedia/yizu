<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
<script src="js/usercenter_me.js" type="text/javascript"></script>
<script src="<%=basePath %>js/jquery.form.js" type="text/javascript"></script>
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
				<div class="personal_middle1_left_bottom"><a href="userInfoAction_updatedata.do">更改个人信息</a></div>
<!--				<div class="personal_middle1_left_bottom"><a href="changuserinfo/${user.userid }.html">更改个人信息</a></div>-->
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
							<a href="javascript:void(0)" onclick="$('#mesaid').show();window.location.hash='mesaid'" id="huatiCount"></a><br /><span>发表的言论</span>
						</li>
						<li>
							<a id="circleCount" href="javascript:getMoreCreate()"></a><br /><span>创建的圈子</span>
						</li>
<%--						<li style="border-right: none;">--%>
<%--							<a id="myvoteCount"></a><br /><span>参与的投票</span>--%>
<%--						</li>--%>
					</ul>
				</div>
				<div class="personal_middle1_right_fengmian"><a href="javascript:void(0);" onClick="BOX_show('usercover');">更改封面</a></div>
				<div id="usercover" style="position:absolute; display:none; top:10%; left:19%;z-index:999; font-size:12px;border: 8px solid rgba(0,0,0,0.2);border-radius: 8px;background-color: #fff;position: fixed;z-index: 106;width:445px;left: 433px;top:150px;">
     				<div class="photo_box"> <span class="close"><a href="javascript:BOX_remove('usercover')" ></a></span>
		       			<div class="zhc_t"><h3>个性化设置</h3></div>
	        			<div class="photo_box1">
	        				<ul id="coverimg">
	        				</ul>
	          				<div class="clear"></div>
	          				<div class="photo_box2">
          						<ul id="coverbottom">
          							<li><a href="javascript:changeUserBj(1);" id="but1">1</a></li>
          							<li><a href="javascript:changeUserBj(2);" id="but2">2</a></li>
          							<li><a href="javascript:changeUserBj(3);" id="but3">3</a></li>
          						</ul>
	          					<p><a href="javascript:BOX_remove('usercover')">取消</a><a href="javascript:changeViewImg()">确定</a></p>
							</div>	
							<script type="text/javascript">changeUserBj(1);</script>	        			
	        			</div>
      				</div>
    			</div>
			</div>
		</div>
        
         <div class="user_list" id="selectcircle" style="display: none;">
           <div class="user_list_t">请选择你要加入的圈子</div>
           <ul id="showcircle">
              
           </ul>
           <div class="user_too">
           <a href="javascript:void(0);" onclick="nextStep();">完成</a>
           <a href="javascript:void(0);" onclick="moreinsert();" id="nextpage">下一批</a></div>
        </div>
        
        <div class="join_circle" id="joincircle">
			<div class="join_circle_top"><h2>我加入的圈子</h2><h3><a href="javascript:void(0);" onclick="moreinsert();">批量添加</a><a id="morecircle_join" style="display:none;">|&nbsp;&nbsp;查看更多</a></h3></div>
				<div class="join_circle_middle" id="jiaruli">
				</div>
				<div class="join_circle_bottom" id="pagediv_join" style="display:none;"></div>
			</div>
        </div>
        
        <div class="join_circle" id="chuangjiancircle" style="display:none;">
			<div class="join_circle_top"><h2>我创建的圈子</h2><h3><a id="morecircle_chuangjian" style="display:none;">查看更多</a></h3></div>
				<div class="join_circle_middle" id="chuangjianli">
				</div>
				<div class="join_circle_bottom" id="pagediv_chuangjian" style="display:none;"></div>
			</div>
        </div>
        
<%--        <div class="user_lunbo" id="user_lunbo">--%>
<%--           <div class="u_name">--%>
<%--               <h2><a>我的圈子</a></h2>--%>
<%--               <h3 id="pilianginsert"><a href="javascript:void(0);" onclick="moreinsert();">批量添加</a></h3>--%>
<%--           </div>            --%>
<%--           <div class="user_lun_x">--%>
<%--               <div class="us_left">--%>
<%--                   <ul>--%>
<%--                      <li class="li" onclick="changeLi(1)" id="jiaru" >加入</li>--%>
<%--                      <li class="active" onclick="changeLi(2)" id="chuangjian">创建</li>--%>
<%--                      --%>
<%--                   </ul>--%>
<%--               </div>--%>
<%--               --%>
<%--               <div class="ue_conx"  id="ue_jiaru"  style="display: block" >--%>
<%--                    <div class="u_left" id="u_left_jiaru" ><img src="images/u_left.gif" /></div>--%>
<%--                    <div class="ue_conx_li" >--%>
<%--                    <ul id="jiaruli">--%>
<%--                    </ul>--%>
<%--                    </div>--%>
<%--                    <div class="u_right" id="u_right_jiaru"><a onclick="userJoinCircle('2');" href='javascript:void(0);'><img src="images/u_right.gif" /></a></div>--%>
<%--               </div>--%>
<%--               <div class="ue_conx" id="ue_chuangjian" style="display: none;">--%>
<%--                    <div class="u_left" id="u_left_chuangjian"><img src="images/u_left.gif" /></div>--%>
<%--                    <div  class="ue_conx_li" >--%>
<%--                    <ul id="chuangjianli">--%>
<%--                    </ul>--%>
<%--                    </div>--%>
<%--                    <div class="u_right" id="u_right_chuangjian"><a onclick="userCreateCircle('2');" href='javascript:void(0);'><img src="images/u_right.gif" /></a></div>--%>
<%--               </div>--%>
<%--               --%>
<%--           </div>    --%>
<%--        </div>--%>
        
        <div class="personal_middle2" id="mesaid"></div>
        <div class="u_more" id="u_more_topic" style=" text-align: center; display:none;"></div>
        
        
<%--        <div class="unlist" id="unlist">--%>
<%--            <div class="unlist_left">--%>
<%--               <div class="u_text">--%>
<%--                   <div class="u_name">--%>
<%--                       <h2><a href="javascript:void(0);">我的发言</a></h2>--%>
<%--                       <div class="u_nametiao" id="hesay"></div>--%>
<%--                   </div>--%>
<%--                   <ul id="hesaycontent">--%>
<%--                   </ul>--%>
<%--                   <div class="u_more" id="u_more_topic"><a href="javascript:void(0);">更多</a></div>--%>
<%--               </div>--%>
<%--            </div>--%>
<%--            <div class="unlist_right">--%>
<%--              <div class="u_contst">--%>
<%--                   <div class="u_name">--%>
<%--                       <h2><a href="javascript:void(0);">我创建的投票</a></h2>--%>
<%--                       <div class="u_nametiao" id="hisvote"></div>--%>
<%--                   </div>--%>
<%--                   <ul id="hisvotelist">--%>
<%--                   </ul>--%>
<%--                   <div class="u_more" id="u_more"><a href="javascript:void(0);">更多</a></div>--%>
<%--               </div>--%>
<%--            </div>--%>
<%--            <div class="unlist_right" id="visitdiv">--%>
<%--              <div class="u_contst">--%>
<%--                   <div class="u_name">--%>
<%--                       <h2><a href="javascript:void(0);">我的访客</a></h2>--%>
<%--                       <div class="u_nametiao" id="myVisitor"></div>--%>
<%--                   </div>--%>
<%--                   <ul id="myVisitlist">--%>
<%--                   </ul>--%>
<%--                   <div class="u_more" id="v_more"><a href="javascript:void(0);">更多</a></div>--%>
<%--               </div>--%>
<%--            </div>--%>
<%--        </div>--%>
        
        
        
        
        
        
        
        
<%--    </div>--%>
</div>
</div>
</body>
</html>