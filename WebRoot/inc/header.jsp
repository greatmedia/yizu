<%@ page contentType="text/html;charset=utf-8"%>
<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="Wed, 26 Feb 1997 08:21:57GMT">
<script src="<%=basePath %>js/jquery.cookies.2.2.0.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/show_window.js" type="text/javascript"></script>
<%--<script src="http://tjs.sjs.sinajs.cn/open/api/js/wb.js" type="text/javascript" charset="utf-8"></script>--%>

<!--个人设置-->
  <script src="js/jquery.min.js" type="text/javascript"></script>
  <script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
  <script type="text/javascript" src="<%=basePath%>js/om-core.js"></script>
  <script type="text/javascript" src="<%=basePath%>js/om-validate.js"></script>
  <script type="text/javascript">
	var timeout         = 0;
	var closetimer		= 0;
	var ddmenuitem      = 0;

	function jsddm_open(){	
		jsddm_canceltimer();
		jsddm_close();
		ddmenuitem = $(this).find('ul').eq(0).css('visibility', 'visible');
<%--		$(".jsddm_top").css({"background-color":"#FFF", "border":"solid 0px #d6d6d4"});--%>
<%--		$(".jsddm_top").css("background-color","#FFF");--%>
	}

	function jsddm_close()
	{	
<%--		$(".jsddm_top").css({"background-color":"#F3F3F2", "border":"solid 0px #F3F3F2"});--%>
<%--		$(".jsddm_top").css("background-color","#F3F3F2");--%>
		if(ddmenuitem) ddmenuitem.css('visibility', 'hidden');}
	
	function jsddm_timer()
	{	closetimer = window.setTimeout(jsddm_close, timeout);}
	
	function jsddm_canceltimer()
	{	if(closetimer)
		{	window.clearTimeout(closetimer);
			closetimer = null;}}
	
	$(document).ready(function()
	{	$('#jsddm > li').bind('mouseover', jsddm_open);
		$('#jsddm > li').bind('mouseout',  jsddm_timer);});
	
	document.onclick = jsddm_close;
  </script>
<!--个人设置-->


<link href="css/style2.css" type="text/css" rel="stylesheet" />


<script type="text/javascript"> 
	$(function(){
		var t;
		<%if(session.getAttribute("user") != null){%>
			getInformInfo();
			<%}else{%>
				clearTimeout(t);
			<%}%>
			window.onscroll = function () {
		        var t = document.documentElement.scrollTop || document.body.scrollTop;
		        var top_div = document.getElementById("top_down_out");
		        if (t >= 61) {
		            top_div.className = "top_down_out0 fillet";
		            $(".circle_middle1").css("padding-top","56px");
		            $(".center_bj").css("padding-top","56px");
		            $(".topic_center").css("padding-top","56px");
		            $(".box_zhuce").css("padding-top","56px");
		        }
		        if (t < 61) {
		            top_div.className = "top_down_out fillet";
		            $(".circle_middle1").css("padding-top","0px");
		            $(".center_bj").css("padding-top","0px");
		            $(".topic_center").css("padding-top","0px");
		            $(".box_zhuce").css("padding-top","0px");
		        }
		    }	
	});
	function getInformInfo(){
		$.ajax({
    		url:"jsonInfoAction_getInformInfo.do",
    		type:"POST",
    		dataType:"json",
    		success:function(data){
        		var joinCircleCount = data.data.joinCircleCount;
        		var pinglunCircleCount = data.data.pinglunCircleCount;
        		var topicPinglunCount = data.data.topicPinglunCount;
        		var gzTopicCount = data.data.gzTopicCount;

        		if(joinCircleCount+pinglunCircleCount+topicPinglunCount+gzTopicCount > 0){
            		var total = joinCircleCount+pinglunCircleCount+topicPinglunCount+gzTopicCount;
            		$("#tongzhi").html("");
            		$("#tongzhi").html("<strong>["+total+"]</strong>");
            	}
        	},
        	error : function(data){
            }
    	});
		t = setTimeout("getInformInfo()",120000);
	}
    
    var keyinfo = "搜索你感兴趣的圈子";
    function focusandblur(tp){
    	var keywords = document.getElementById("keywords");
    	if(tp == 1){
    		if(keywords.value == keyinfo){
    			keywords.value="";
    		}
    	}else{
    		if(keywords.value.length == 0){
    			keywords.value = keyinfo;
    		}
    	}
    }
    
    function subSearch(){
    	var keywords = document.getElementById("keywords");
    	if(keywords.value.length == 0 || keywords.value == keyinfo){
    		alert("请输入搜索内容");
    		keywords.focus();
    	}else{
    		if($("#search_tag").val() == "1"){
				window.location.href = "<%=basePath %>quanzi/search.jsp?tag=1&keyword="+keywords.value;    			
    		}else{
	    		window.open("<%=basePath %>quanzi/search.jsp?tag=1&keyword="+keywords.value);
    		}
    	}
    }
    
    function subkeydown(e){
    	var key = window.event ? e.keyCode : e.which;
		if(key==13){
			subSearch();
		 }
    }
    function changeInform(){
    	$.ajax({
    		url:"jsonInfoAction_changeInform.do",
    		type:"POST",
    		dataType:"json",
    		success:function(data){
    			var id = $("#userid").val();
    			$("#tongzhi").html("");
<%--   				location.href = "<%=basePath%>usercontent_me/id?tag=7";--%>
        	},
        	error : function(data){
            }
    	});    
    }
    function ajaxLogins(){
        if($.trim($("#email").val()) == "" || $.trim($("#email").val()) == "请输入邮箱"){
            alert("请输入邮箱地址！");
            $("#email").focus();
            return;
        }
        if($("#email").val().search("@") < 0 ){
            alert("邮箱格式不正确，请重新输入");
            $("#regemail").focus();
            return;
        }
        if($.trim($("#password").val()) == "" || $.trim($("#password").val()) == "请输入密码"){
            alert("请输入密码！");
            $("#password").focus();
            return;
        }
        var ajaxLoginsURL = "userInfoAction_ajaxLogin.do";
        var email = $("#email").val();
        var password = $("#password").val();
        var ref = location.href;
        if(ref.search("#") > 0){
			ref = ref.substring(0,ref.search("#"));
        }
          $.ajax({
        	url:ajaxLoginsURL,
    		data:{email : email,pwd : password,ref : ref},
    		type:"POST",
    		dataType:"json",
    		beforeSend:function() {
			 	$("#creat_submit").hide();
            	$("#loadding").show();
			},
    		success:function(data)
    		{
				$("#loadding").hide();
        		if(data.flag){
					location.href=ref;
        		}else{
        			$("#creat_submit").show();
        			alert(data.msg);
        		}
    		},
    		error:function(data){
    			alert("ee"+data);
    		}
    		
    	});
    }
    function regLoginUser(){
    	if($.trim($("#regemail").val()) == "" || $.trim($("#regemail").val()) == "请输入邮箱"){
            alert("请输入邮箱地址！");
            $("#regemail").focus();
            return;
        }
    	if($("#regemail").val().search("@") < 0 ){
            alert("邮箱格式不正确，请重新输入");
            $("#regemail").focus();
            return;
        }
    	var pwd = $("#regpwd").val();
        if($.trim($("#regpwd").val()) == "" || $.trim($("#regpwd").val()) == "请输入密码"){
            alert("请输入密码！");
            $("#regpwd").focus();
            return;
        }
        var repwd = $("#regrepwd").val();
        if($.trim($("#regrepwd").val()) == "" || $.trim($("#regrepwd").val()) == "重复确认密码"){
            alert("请输入确认密码！");
            $("#regrepwd").focus();
            return;
        }
        if(repwd != pwd){
        	 alert("两次密码输入不一致");
             $("#regrepwd").focus();
             return;
        }
        var ajaxLoginsURL = "userInfoAction_regLogin.do";
        var email = $("#regemail").val();
        var ref = location.href;
          $.ajax({
        	url:ajaxLoginsURL,
    		data:{email : email,pwd : pwd,ref : ref},
    		type:"POST",
    		dataType:"json",
    		beforeSend:function() {
			 	$("#reg_submit").hide();
            	$("#loadding").show();
			},
    		success:function(data)
    		{
				$("#loadding").hide();
        		if(data.flag){
					alert("注册成功，请登录吧！");
					$("#window_zhuce").hide();
					$("#window_dl").css("display","block");
        		}else{
        			$("#reg_submit").show();
        			alert(data.msg);
        		}
    		},
    		error:function(data){
    			alert("ee"+data);
    		}
    		
    	});
    }
    function onkeydownLogin(e)
    {
    	var key = window.event ? e.keyCode : e.which;
    	if(key==13){
    		ajaxLogins();
    	 }
    }
</script>
<style>
.top_down_out0{width:100%; height:55px; background-color:#f3f3f2; border-bottom: 1px solid #d6d6d4; position: fixed; z-index:1000; top:0px;}
.item{padding:0px;}
</style>

<div class="top_up_out">
	<div class="top_up">
		<div class="top_up_left"><a href="<%=basePath %>"><img src="images/top01.jpg" /></a></div>
		<div class="top_up_right"><p><a href="javascript:void(0);"><wb:follow-button uid="2673608173" type="red_2" width="115"  height="24" ></wb:follow-button></a></p></div>
	</div>
</div>
<div class="top_down_out fillet" id="top_down_out">
	<div class="top_down">
		<div class="top_down_left">
			<!--导航-->
      		<div class="nav">
        		<ul>
        			<li style="margin-left:0px;"><a href="<%=basePath%>">首页</a></li>
					<li><a href="<%=basePath%>topic.html">话题</a></li>
                    <li><a href="<%=basePath%>vote.html">投票</a></li> 
                    <li><a href="http://mall.1mily.com" target="blank">商城</a></li>
        		</ul>
      		</div>
      		<!--导航-->
		</div>
		<div class="top_down_middle">
			<div class="header_r">
      			<div class="search" id="searchtype" style="display:none;">
        			<input type="text" class="input" name="keywords" id="keywords" 
        				value="<%=request.getParameter("keyword") == null ? "搜索你感兴趣的圈子" : request.getParameter("keyword") %>" 
        				onkeydown="subkeydown(event);"
        				onfocus="focusandblur(1)" onblur="focusandblur(2)" />
        			<input type="button"  onclick="subSearch()" class="input_1" />
      			</div>
      			<!--用户登录-->
      			<c:choose>
      				<c:when test="${user != null}">
      					<div id="chromemenu" class="dengluhou">
      						<input type="hidden" id="userid" value="${user.userid }" />
      						<input type="hidden" id="useremail" value="${user.email }" />
							<input type="hidden" id="usernick" value="${user.nick }" />
							<ul>
								<li><a href="javascript:void(0);" onclick="openShutManager(this,'window_xx')" >消息<span id="tongzhi"><c:if test="${joinCircleCount+pinglunCircleCount+topicPinglunCount+gzTopicCount > 0 }"><strong>[${joinCircleCount+pinglunCircleCount+topicPinglunCount+gzTopicCount}]</strong></c:if></span></a></li>
								<li>
              						<ul id="jsddm">
										<li class="jsddm_top">
											<a href="<%=basePath%>usercenter_me/${user.userid }.html">
											<c:choose>
				             					<c:when test="${user.image != null && user.image != ''}">
				             						<img title="${user.nick }" src="${user.image }" width="25" height="25"/>
				             					</c:when>
				             					<c:otherwise>
				             						<c:choose>
				             							<c:when test="${user.otheraccountuserimage != null && user.otheraccountuserimage != ''}">
				             								<img title="${user.nick }" src="${user.otheraccountuserimage }" width="25" height="25"/>
				             							</c:when>
				             							<c:otherwise>
				             								<img title="${user.nick }" src="../images/nobody.gif" width="25" height="25"/>
				             							</c:otherwise>
				             						</c:choose>
				             					</c:otherwise>
		             						</c:choose>
		             						<p>${user.nick}</p>
		             						</a>
											<ul>
												<li><a href="<%=basePath%>usercenter_me/${user.userid }.html"><h1>主页</h1></span><span><img src="images/zhuye.png" /></span></a></li>
<%--												<%=basePath%>usercontent_me/${user.userid}?tag=6--%>
												<li><a href="userInfoAction_updatedata.do"><h1>设置</h1><span><img src="images/shezhi.png" /></span></a></li>
												<li><a href="<%=basePath%>userInfoAction_logout.do"><h1>退出</h1><span><img src="images/tuichu.png" /></span></a></li>
											</ul>
										</li>
    								</ul>
              					</li>
<%--								<li class="mainlevel"id="mainlevel_02"  onmouseover="$('#sub_02').show();"  onmouseout="$('#sub_02').hide();">--%>
<%--									<a href="javascript:void(0);" style="margin-right:1px;"><span>${user.nick }</span>--%>
<%--										<c:choose>--%>
<%--			             					<c:when test="${user.image != null && user.image != ''}">--%>
<%--			             						<img title="${user.nick }" src="${user.image }" width="25" height="25"/>--%>
<%--			             					</c:when>--%>
<%--			             					<c:otherwise>--%>
<%--			             						<img title="${user.nick }" src="${user.otheraccountuserimage }" width="25" height="25"/>--%>
<%--			             					</c:otherwise>--%>
<%--		             					</c:choose>--%>
<%--									</a>--%>
<%--									<ul id="sub_02" >--%>
<%--										<li><a href="<%=basePath%>usercenter_me/${user.userid }.html" target="_parent" style="background:url(images/zhuye.png) no-repeat 10px center;">主页</a></li>--%>
<%--										<li class="shezhi"><a href="<%=basePath%>usercontent_me/${user.userid}?tag=5" target="_parent" style="background:url(images/shezhi.png) no-repeat 10px center;">设置</a></li>--%>
<%--										<li class="tuichu"><a href="<%=basePath%>userInfoAction_logout.do" target="_parent" style="background:url(images/tuichu.png) no-repeat 10px center;">退出</a></li>--%>
<%--									</ul>--%>
<%--								</li>--%>
        					</ul>
        				</div>
        				<!---通知---->
						<div id="window_xx" style=" margin-top: 45px; position:absolute; display:none;z-index:1; font-size:12px;width:250px; margin-left:300px; border-radius: 5px;border: solid 1px #ddd;background-color: #fff;">
							<div class="jiantou">&nbsp;</div>
						  	<div class="xiaoxi_box">
						   		<div id=con>
									<img src="images/loading.gif" style="width:25px; margin-left: 150px; margin-top:15px;"/>
								</div>
							  	<SCRIPT type=text/javascript>
									function selectTag2(showContent,selfObj){
										// 操作标签
										var tag = document.getElementById("tags").getElementsByTagName("li");
										var taglength = tag.length;
										for(i=0; i<taglength; i++){
											tag[i].className = "";
										}
										selfObj.parentNode.className = "selectTag";
										// 操作内容
										for(i=0; j=document.getElementById("tagContent"+i); i++){
											j.style.display = "none";
										}
									document.getElementById(showContent).style.display = "block";
								}
								</SCRIPT>
							</div>
<%--							<div class="xiaoxi_bottom"><a href="javascript:void(0);" onclick="changeInform()">查看全部>></a></div>--%>
							<div class="xiaoxi_bottom"><a href="<%=basePath%>usercontent_me/id?tag=7">查看全部>></a></div>
						</div>
      				</c:when>
      				<c:otherwise>
      					<div id="chromemenu" class="chro" >
      						<input type="hidden" id="userid" />
               			 	<ul class="dropdown">
						        <li><a href="javascript:void(0);" onclick="javascript:location.href='<%=basePath%>authorizeAction_connectWeibo.do?ref='+window.location.href"><img src="images/top_01.jpg" /></a></li>
						        <li><a href="javascript:void(0);" onclick="javascript:location.href='<%=basePath%>authorizeAction_connectQQ.do?ref='+window.location.href"><img src="images/top_02.jpg" /></a></li>
						        <li><a href="javascript:void(0);" onClick="BOX_remove('window_zhuce');BOX_show('window_dl');">登陆</a>|</li>
<%--						        <li><a href="javascript:void(0);" onClick="BOX_remove('window_dl');BOX_show('window_zhuce');">注册</a></li>--%>
								<li><a href="<%=basePath %>register.html">注册</a></li>
				    		</ul>
      					</div>
      				</c:otherwise>
      			</c:choose>
    		</div>
		</div>
	</div>
</div>

<!-----登录窗口---->
<div class="zhezhaoceng" style="filter:alpha(opacity=80); opacity:0.93;  display:none; text-align: center; " id="zhezhaoceng">
		<span style=" color: #F78D00; font-size: 25px; line-height: 60px; margin-top:200px;">您还没有登录网站哦</span>
	</div>
<div id="window_dl" style="position:absolute; display:none; top:10%; left:19%;z-index:999; font-size:12px;border: 8px solid rgba(0,0,0,0.2);border-radius: 8px;background-color: #fff;position: fixed;z-index: 106;width: 540px;left: 433px;top: 226px;">
  	
  	<div class="zhc_box">
  		<span class="close"></span>
    	<div class="zhc_t">
    		<h3>登录逸族</h3>
    		<div class="dl_close" style="cursor:hand;">
    			<img src="images/panel-spr.png"  onClick="BOX_remove('window_dl');"  />
    		</div>
    	</div>
   		<div class="zhc_l">
    		<div class="t_txt">使用合作网站账号直接登录。</div>
      		<div class="else_dl">
        		<ul>
          			<li><a href="javascript:void(0);" onclick="javascript:location.href='<%=basePath%>authorizeAction_connectWeibo.do?ref='+window.location.href"><img src="images/weibo_ico.jpg" /></a></li>
        			<li><a href="javascript:void(0);" onclick="javascript:location.href='<%=basePath%>authorizeAction_connectQQ.do?ref='+window.location.href"><img src="images/qq_ico.jpg" /></a></li>
        		</ul>
      		</div>
      		<div class="clearfloat"></div>
			<div class="t_txt">还没有账号？</div>
      		<div class="else_dl">
        		<ul>
          			<li><a href="/register.html" ><img src="images/zhuce_ico.jpg" /></a></li>
        		</ul>
      		</div>
    	</div>
    	<div class="zhc_r">
      		<div class="t_txt">使用注册邮箱登录。</div>
      			<ul>
      				<li>
						<div class="item">
							<input type="text" class="e-mail" id="email" name="instance.email" style="overflow: auto;" />
							<script>
								var el = document.getElementById("email");
								if (el.value == "")
  									{el.value = "请输入邮箱";}
   								el.onfocus = function() {
  									if (this.value == "请输入邮箱")
    								this.value = "";
								};
								el.onblur = function() {
								  	if (this.value == "")
								    this.value = "请输入邮箱";
								}
							</script>
						</div>
					</li>
					<li>
						<div class="item">
							<input type="text" id="password" class="e_password" onkeydown="onkeydownLogin(event)" name="instance.password" style="overflow: auto;" />
							<script>
								var el = document.getElementById("password");
								if (el.value == "")
							  		el.value = "请输入密码";
								el.onfocus = function() {
								  	if (this.value == "请输入密码")
								    this.value = "";
								  	this.type="password";
								};
								el.onblur = function() {
								  if (this.value == ""){
									  this.value = "请输入密码";
									  this.type="text";
								   }
								}
							</script>
						</div>
					</li>
      			</ul>
      		<div class="zhc_dl" id="creat_submit">
        		<input type="image" onclick="ajaxLogins()" src="images/denglu01.jpg" />
      		</div>
      		<div id="loadding"  style="display: none;">
                <img src='<%=basePath%>images/loading_32.gif' style="width: 12px;height: 12px;"/>登录中...
            </div>
      		<div class="zhc_wm"><a  href="<%=basePath %>setting/forget.jsp" style="color:#F88F00;">忘记密码了吗？</a></div>
    	</div>
  	</div>
</div>

<!-----注册窗口---->
<div id="window_zhuce" style="position:absolute; display:none; top:10%; left:19%;z-index:999; font-size:12px;border: 8px solid rgba(0,0,0,0.2);border-radius: 8px;background-color: #fff;position: fixed;z-index: 106;width: 540px;left: 433px;top: 226px;">
  	<div class="zhc_box" style="height:350px;">
    	<div class="zhc_t">
    		<h3>注册逸族</h3>
    		<div class="dl_close" style="cursor:hand;">
    			<img src="images/panel-spr.png"  onClick="BOX_remove('window_zhuce');"   />
    		</div>
    	</div>
     	<div class="zhc_l">
    		<div class="t_txt">使用合作网站账号直接注册。</div>
   			<div class="else_dl">
     			<ul>
       				<li><a href="javascript:void(0)" onclick="javascript:location.href='<%=basePath%>authorizeAction_connectWeibo.do?ref='+window.location.href"><img src="images/weibo_ico.jpg" /></a></li>
    				<li><a href="javascript:void(0)" onclick="javascript:location.href='<%=basePath%>authorizeAction_connectQQ.do?ref='+window.location.href"><img src="images/qq_ico.jpg" /></a></li>
     			</ul>
   			</div>
   			<div class="clearfloat"></div>
		</div>
    	<div class="zhc_r">
      		<div class="t_txt">免费注册逸族网账号</div>
      		<ul>
     	 		<li>
					<div class="item">
						<input type="text" id="regemail" class="e-mail" style="overflow: auto;" />
						<script>
							var el = document.getElementById("regemail");
							if (el.value == "")
							  el.value = "请输入邮箱";
							   
							el.onfocus = function() {
							  if (this.value == "请输入邮箱")
							    this.value = "";
							};
							el.onblur = function() {
							  if (this.value == "")
							    this.value = "请输入邮箱";
							}
						</script>
					</div>
				</li>
				<li>
					<div class="item">
						<input type="text" id="regpwd" class="e_password" style="overflow: auto;" />
						<script>
						var el = document.getElementById("regpwd");
						if (el.value == "")
						  el.value = "请输入密码";
						   
						el.onfocus = function() {
						  	if (this.value == "请输入密码")
						    this.value = "";
						  	this.type="password";
						};
						el.onblur = function() {
						  	if (this.value == ""){
						  		this.type="text";
						  		this.value = "请输入密码";
							}
						}
						</script>
					</div>
				</li>
				<li>
					<div class="item">
						<input type="text" id="regrepwd" class="e_password" style="overflow: auto;" />
						<script>
							var el = document.getElementById("regrepwd");
							if (el.value == "")
							  el.value = "重复确认密码";
							   
							el.onfocus = function() {
							  	if (this.value == "重复确认密码")
							    this.value = "";
							  	this.type="password";
							};
							el.onblur = function() {
							  	if (this.value == ""){
							  		 this.value = "重复确认密码";
							  		this.type="text";
								 }
							}
						</script>
					</div>
				</li>
			</ul>
      		<div class="zhc_dl" id="reg_submit">
        		<input type="image" onclick="regLoginUser()" src="images/zhuce01.jpg" />
      		</div>
      		<div id="loadding"  style="display: none;">
                <img src='<%=basePath%>images/loading_32.gif' style="width: 12px;height: 12px;"/>注册中...
            </div>
    	</div>
  	</div>
</div>

<script>

<%--var userid = $("#userid").val();--%>
<%--if(userid.length>0 && userid != null)--%>
<%--{ --%>
<%--	var useremail = $("#useremail").val();--%>
<%--	var usernick = $("#usernick").val();--%>
	//var s = "${user.email }";
<%--	if(useremail.length < 2 || useremail == null || useremail == "")--%>
<%--	{--%>
<%--		localhost("请设置你的电子邮件");--%>
<%--	} else--%>
<%--	 if(usernick.length<2 ||usernick == "")--%>
<%--	{--%>
<%--		localhost("请完善您的个人资料");--%>
<%--	}--%>
<%--}--%>
<%--function localhost(msg)--%>
<%--{--%>
<%--	--%>
<%--	var realurl = location.href;--%>
<%--	var idindexof = realurl.lastIndexOf("/");--%>
<%--	var localhost = realurl.substring(idindexof + 1);--%>
<%--	if(localhost != "registerinfo.html" && localhost !="uploadphoto.html")--%>
<%--	{--%>
<%--		alert(msg);--%>
<%--		location.href="<%=basePath%>registerinfo.html";--%>
<%--	}--%>
<%--}--%>
</script>