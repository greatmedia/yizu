<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>推荐朋友</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="">
	<link href="css/css/zhucestyle.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/invitation.js" type="text/javascript"></script>
	<script>var basePath = "<%=basePath%>";</script>

  </head>
  
  <body>
  		<jsp:include page="../inc/header.jsp"></jsp:include>
		<div class="box_zhuce">
			<div class="inner_box">
				<div class="nav_zhuce">
					<ul>
						<li>
							<a href="/registerinfo.html">1 完善个人资料</a>
						</li>
						<li style="margin: 0px 3px;">
							<a href="/invitation.html"  style="background-color: #ff8a00; color: #fff;">2 推荐你的朋友</a>
						</li>
						<li>
							<a href="/uploadphoto.html">3 上传个人头像</a>
						</li>
					</ul>
				</div>
				<div class="tittle">
					<p>
						搜索你的电邮帐号是搜索你逸族网朋友的最佳途径
					</p>
				</div>
				<div class="middle_invitation">
					<ul>
						<li>
							<div class="middle_invitation_left">
								<a href="#">网易邮箱</a>
							</div>
						</li>
						<li>
							<div class="middle_invitation_right">
								<a href="#" style="display: none;">搜索好友</a><span
									class="yes_invitation"></span>
							</div>
						</li>
					</ul>
					<ul>
						<li>
							<div class="middle_invitation_left0">
								<a href="#">QQ邮箱</a>
							</div>
						</li>
						<li>
							<div class="middle_invitation_right">
								<a href="#">搜索好友</a>
							</div>
						</li>
					</ul>
				</div>
				<div class="middle1_invitation">
					<ul>
						<li>
							<h1>
								电子邮箱：
							</h1>
							<input type="text" class="text" />
						</li>
						<li>
							<h1>
								邮箱密码：
							</h1>
							<input type="text" class="text" />
						</li>
						<li>
							<a href="#">确定</a><span>逸族网不会储存您的密码，请放心！</span>
						</li>
					</ul>
				</div>
				<div class="clear"></div>
				<div class="tittle_invitation">
					<p>
						搜索你的电邮帐号是搜索你逸族网朋友的最佳途径
					</p>
				</div>
				<div class="middle2_invitation">
					<ul>
						<li>
							<input type=checkbox name=mm value=a
								onclick="checkItem(this, 'mmAll')">
							<img src="../../images/register/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
							</label>
						</li>
						<li style="border-right: none;">
							<input type=checkbox name=mm value=b
								onclick="checkItem(this, 'mmAll')">
							<img src="../../images/register/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
						</li>
					</ul>
					<ul>
						<li>
							<input type=checkbox name=mm value=c
								onclick="checkItem(this, 'mmAll')">
							<img src="../../images/register/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
						</li>
						<li style="border-right: none;">
							<input type=checkbox name=mm value=d
								onclick="checkItem(this, 'mmAll')">
							<img src="../../images/register/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
						</li>
					</ul>
					<ul>
						<li>
							<input type=checkbox name=mm value=a
								onclick="checkItem(this, 'mmAll')">
							<img src="img/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
							</label>
						</li>
						<li style="border-right: none;">
							<input type=checkbox name=mm value=b
								onclick="checkItem(this, 'mmAll')">
							<img src="img/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
						</li>
					</ul>
					<ul>
						<li>
							<input type=checkbox name=mm value=c
								onclick=
	checkItem(this, 'mmAll');
>
							<img src="img/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
							</label>
						</li>
						<li style="border-right: none;">
							<input type=checkbox name=mm value=d
								onclick="checkItem(this, 'mmAll')">
							<img src="img/photo_big.jpg" />
							<p>
								<span>张三三</span>
								<br />
								878739672@qq.com
							</p>
						</li>
					</ul>
					<div class="middle2_invitation_bottom">
						<p>
							<input type=checkbox name=mmAll onclick="checkAll(this, 'mm')">
							全选
						</p>
					</div>

					<script language=Javascript>
function checkAll(e, itemName)
{
  var aa = document.getElementsByName(itemName);
  for (var i=0; i<aa.length; i++)
   aa[i].checked = e.checked;
}
function checkItem(e, allName)
{
  var all = document.getElementsByName(allName)[0];
  if(!e.checked) all.checked = false;
  else
  {
    var aa = document.getElementsByName(e.name);
    for (var i=0; i<aa.length; i++)
     if(!aa[i].checked) return;
    all.checked = true;
  }
}
</script>
				</div>
				<div class="middle2_invitation_bottom_icon">
					<span><a href="javascript:void(0);">确定</a>
					</span>
					<h1>
						<a href="/uploadphoto.html">跳过此步>></a>
					</h1>
				</div>
				</div>
				</div>
	</body>
</html>
