<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<div class="quanzi_left_top" >
<div class="quanzi_left0">
	<input type="hidden" id="circleid" value="${circleleft.circleid }"/>
	<input type="hidden" id="islogin" value="${islogin}"/>
	<div class="quanzi_left">
		<div class="quanzi_left1"><p>${circleleft.circlename }</p></div>
		<div class="quanzi_left2">
			<ul>
				<li><img src="${circleleft.circlemiddleimg }" /></li>
				<li><a href="cir_user/${circleleft.circleid }.html">${circleleft.joincount }</a><span>成员</span><strong>|</strong><a href="cir2/1/${circleleft.circleid }.html">${circleleft.comcount }</a><span>发言</span></li>
				<c:choose>
					<c:when test="${user != null && isAddCircle > 0}">
						<li><a href="<%=basePath%>circleInfoAction_circleInfoPublish.do?id=${circleleft.circleid }"><img src="../images/tianjianeirong.jpg" /></a></li>
						<c:if test="${user.userid == circleleft.userid }">
							<li><a href="<%=basePath%>circleInfoAction_update.do?id=${circleleft.circleid }">编辑</a></li>
						</c:if>
					</c:when>
					<c:otherwise>
						<li>
							<a href="javascript:void(0);" onclick="join_or_quit_quanzi('<%=request.getParameter("id")%>',1)"><img src="../images/quanzi_02.jpg" /></a>
						</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		
		<div class="quanzi_left3" style="display:none;">
			<div class="quanzi_left3_title"><p>标签：</p></div>
			<ul>
				<li><a href="javascript:void(0);">${circleleft.circletag}</a></li>
			</ul>
		</div>
		<div class="quanzi_left4">
			<p><span>${circleleft.summary }</span></p>
		</div>
		<div class="quanzi_left5">
			<ul>
				<li>圈主：</li>
				<li><a href="user/${circleleft.userinfo.userid }.html" style="color:#CCCCCC;"><img src="${circleleft.userinfo.image }" />${circleleft.userinfo.nick }</a></li>
<%--				<li>最新加入成员</li>--%>
<%--				<li>--%>
<%--				<c:forEach var="usercir" items="${userCircleRlss}" varStatus="u" >--%>
<%--					<a target="_blank" href="user/${usercir.userinfo.userid }.html"><img src="${usercir.userinfo.image }" title="${usercir.userinfo.nick }" /></a>--%>
<%--				</c:forEach>--%>
<%--				</li>--%>
			</ul>
		</div>
	</div>
</div> 
<div class="clear"></div>
<div id="ggcirclediv" class="quanzi_left6" style="display:none;">
	<a id="ggcirclediv_a" target="_blank"><img id="ggcirclediv_img" width="240" /></a>
</div>
</div>
<script>

//加入圈子
function join_or_quit_quanzi(did,ttyp){
	var islogin = document.getElementById("islogin").value;
	if(islogin == 'not'){
		alert("您还没有登录，登录之后才能加入圈子..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	$("#wait").css("display","block");
	$("#join").css("display","none");
	$("#a_join").css("display","none");
	var joinquanziURL = "jsonInfoAction_joinOrQuitQuanzi.do";
	$.ajax({                 
		url: joinquanziURL,  
		data : {id : did ,type : ttyp},
		type: "POST",     
		dataType : "json",
		async:false,
		success: function(data) {
			//json.put("cadd", "add");
			$("#wait").css("display","none");
			var flag = data.flag;
			if(ttyp == 1){
				//加入圈子
				if(flag){
					alert("加入圈子成功！");
					window.location.href = location.href;
				}else{
					alert("你已经加入该圈子了！");
					$("#wait").css("display","none");
				}
			}else if(ttyp == 2){
				//退出圈子
				if(flag){
					alert("退出圈子成功！");
					location.href = location.href;
				}else{
					alert("退出圈子失败！");
					$("#wait").css("display","none");
				}
			}
		},
		error:function(data){
			$("#wait").css("display","none");
		}
	});
}
var tid = document.getElementById("circleid").value;
getggdiv(tid);
function getggdiv(tid)
{
	if(tid=='3a607484-620d-438d-9b9c-cac9f0d50f36')
	{
		ggdiv("http://www.7979la.com/","7979la.jpg");
	}
	else if(tid=='f5e49d3e-49c2-461a-bf4e-afb41c06ffe9')
	{
		ggdiv("http://www.sogocn.cn/","sogocn.jpg");
	}
	else if(tid=="810c2627-7268-428f-961f-72db44f3f3b6")
	{
		ggdiv("http://99.com.cn/","99.gif");
	}else if(tid=="3e45bb2e-d377-4cfa-a522-3a4324d60294")
	{
		ggdiv("http://www.pifa186.com/","pifa186.jpg");
	}else if(tid=="24bc4d92-ef0a-437b-9583-61dc4f5e6828")
	{
		ggdiv("http://www.kingdeeit.com/","kingdeeit.jpg");
	}
	else if(tid=="3f2f2344-e68b-407a-939e-a6d464a8439d")
	{
		ggdiv("http://xiandieli.tmall.com/","xiandieli.jpg");
	}
	else if(tid=="1a551520-23b6-4434-aef1-7d2e2f58ae8f")
	{
		ggdiv("http://www.w3c-net.com/","w3c_net.gif");
	}
	else if(tid=="e35455d2-8738-4068-96ed-e6eff3b6c030")
	{
		ggdiv("http://item.taobao.com/item.htm?id=17741639993","taobao-226-270.jpg");
	}
	else if(tid=="1339406727475")
	{
		ggdiv("http://reg.2177s.com/?u=1084&l=6","2177s.jpg");
	}
	else if(tid=="e66aea87-d678-4a88-9262-566f0ef00b21")
	{
		ggdiv("http://www.qiandu6.com/","qiandu6.jpg");
	}
	else if(tid=="4fa49761-d812-46ae-a6b4-71bbfa8fa883")
	{
		ggdiv("http://www.310win.com/","310win.jpg");
	}
	else if(tid=="1339493969319")
	{
		ggdiv("http://www.0261.com/reg/index.html?gid=312&st=1314","0261.gif");
	}
	else if(tid=="84ac5f7e-d217-4157-9a16-33caa4b79aff")
	{
		ggdiv("http://china.findlaw.cn/","findlaw.jpg");
	}
	else if(tid=="26677a5b-7743-442a-bd4a-4b03fddbdc49")
	{
		ggdiv("http://stdsw.1414.cn/","1414.jpg");
	}
	else if(tid=="1339489615662")
	{
		ggdiv("http://esf.sh.soufun.com/?utm_source=shhezuo&utm_medium=click&utm_term=wum_sh&utm_content=sh1mily&utm_campaign=201301061mily","esf.sh.soufun.jpg");
	}
	else if(tid=="78814533-fe58-4a7a-a30f-5122ec21af50")
	{
		ggdiv("http://www.chinainv.org/","chinainv.jpg");
	}
	else if(tid=="9ac2d13d-9f86-4f6e-a9b5-3a87abcf5b46")
	{
		ggdiv("http://www.sogoulm.com/","sogoulm.jpg");
	}
	else if(tid=="8c0b3aae-359a-4553-a2ca-92ce477fb159"){
		ggdiv("http://www.papayacn.com/","papayacn.jpg");
	}
	else if(tid=="7246010d-9509-4983-9e78-afc9274e34c3"){
		ggdiv("http://www.sogoulm.com/","sogoulm.jpg");
	}
	else{
		$("#ggcirclediv").css("display","none");
	}
}
function ggdiv(href,img)
{var str="<a href=\""+href+"\" target=\"_blank\"><img width=\"240\" src=\"gg/"+img+"\"></a>";$("#ggcirclediv").html(str);}
</script>
