<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="来吧，从此刻开始，开辟或加入专属自己的逸族圈子吧。哪怕你的爱好再小众，在这里也可以找到你的知己。让我们的身边簇拥着知己，一起分享，一起品位生活的点点滴滴。">
	<meta http-equiv="description" content="逸族,逸族网,逸族社区,逸族商城,第二人生,圈子,交友社区,社区购物,图片分享,话题,热门话题,今日话题，热点话题">
	<link href="<%=basePath%>css/css/index.css" rel="stylesheet" type="text/css" />
	
	<script>var basePath = "<%=basePath%>";</script>
	
  </head>
  
  <body>
		<div class="box">
			<div class="box_top">
				<div class="box_top_left">
					<a href="http://www.1mily.com/">
						<p class="font1">欢迎来到<font color="#F0951F">逸族网</font></p><br />
						<p class="font3">邂逅你的同好知己，拥有你的专属“轻奢品”<br />———从这里,开启你的第二人生!</p>
					</a>
				</div>
				<div class="box_top_right">
					<div class="zhuce">
						<div class="zhuce_top">
							注册
						</div>
						<div class="zhuce_middle">
							<ul>
								<li>
									<input class="input1"
										style="width: 274px; height: 28px; margin: 0 auto; padding: 0px; border: #cccccc 1px solid; overflow: hidden; font-size: 12px; color: #666; border-radius: 0px; overflow: hidden; padding-left: 10px;"
										type="text" id="regname" />
									<script>
										var el = document.getElementById("regname");
										if (el.value == "")
											el.value = "姓名";
									
										el.onfocus = function() {
											if (this.value == "姓名")
												this.value = "";
										};
										el.onblur = function() {
											if (this.value == "")
												this.value = "姓名";
										}
									</script>
								</li>
								<li>
									<input class="input1"
										style="width: 274px; height: 28px; margin: 0 auto; padding: 0px; border: #cccccc 1px solid; overflow: hidden; font-size: 12px; color: #666; border-radius: 0px; overflow: hidden; padding-left: 10px;"
										type="text" id="regemail" />
									<script>
										var el = document.getElementById("regemail");
										if (el.value == "")
											el.value = "邮箱地址";
									
										el.onfocus = function() {
											if (this.value == "邮箱地址")
												this.value = "";
										};
										el.onblur = function() {
											if (this.value == "")
												this.value = "邮箱地址";
										}
									</script>
								</li>
								<li>
									<input class="input1"
										style="width: 274px; height: 28px; margin: 0 auto; padding: 0px; border: #cccccc 1px solid; overflow: hidden; font-size: 12px; color: #666; border-radius: 0px; overflow: hidden; padding-left: 10px;"
										type="text" id="tx" />
										<input class="input1"
										style="display:none;width: 274px; height: 28px; margin: 0 auto; padding: 0px; border: #cccccc 1px solid; overflow: hidden; font-size: 12px; color: #666; border-radius: 0px; overflow: hidden; padding-left: 10px;"
										type="password" id="regpwd" />
									<script>
										var el = document.getElementById("tx");
										var pwd = document.getElementById("regpwd");
										if (el.value == "")
											el.value = "请输入密码";
									
										el.onfocus = function() {
										  	if (this.value == "请输入密码"){
												this.style.display = 'none';
												pwd.style.display = "block";
												pwd.value = "";
												pwd.focus();
											}
<%--										    this.value = "";--%>
<%--										  	this.type="password";--%>
											
										};
										pwd.onblur = function() {
										  	if (this.value != ""){return;}
											this.style.display = 'none';	
											el.style.display = "block";
											el.value = "请输入密码";										  	
<%--										  		this.type="text";--%>
<%--										  		this.value = "请输入密码";--%>
										}
									</script>
								</li>
								<li>
									<a id="reg_submit" href="javascript:void(0);" onclick="registerUser()"></a>
									<span id="loadding" style="display:none;">注册中···</span>
								</li>
							</ul>
						</div>
					</div>
				</div>

			</div>

			<div class="box_bottom">
				<p>
					<a href="http://www.1mily.com/quanzi/about.jsp">关于逸族</a>|
					<a href="http://www.1mily.com/quanzi/about.jsp?tag=2">关于逸族网</a>|
					<a href="http://www.1mily.com/quanzi/about.jsp?tag=6">联系我们</a>|
					<a href="http://www.1mily.com/quanzi/about.jsp?tag=4">版权声明</a>|
					<a href="http://www.1mily.com/quanzi/about.jsp?tag=5">服务协议</a>
				</p>
			</div>

		</div>
	
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/register.js" type="text/javascript"></script>
		<script language="javascript">

	window.onload = function () {
		var d = new Date();
		switch(d.getDay()) {
			case 0:
				document.body.style.backgroundImage = "url(../images/register/a.jpg)";
				break;
			case 1:
				document.body.style.backgroundImage = "url(../images/register/b.jpg)";
				break;
			case 2:
				document.body.style.backgroundImage = "url(../images/register/c.jpg)";
				break;
			case 3:
				document.body.style.backgroundImage = "url(../images/register/d.jpg)";
				break;
			case 4:
				document.body.style.backgroundImage = "url(../images/register/e.jpg)";
				break;
			case 5:
				document.body.style.backgroundImage = "url(../images/register/f.jpg)";
				break;
			case 6:
				document.body.style.backgroundImage = "url(../images/register/g.jpg)";
				break;
		}
	
	}
</script>
	</body>
</html>
