<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>确认密码</title>
		<link href="css/style.css" type="text/css" rel="stylesheet" />
		<script src="js/alljs.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery.form.js" type="text/javascript"></script>
		<script type="text/javascript" src="<%=basePath%>js/om-core.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/om-validate.js"></script>
		<style type="text/css">
	        label.error{
		        background: #fff6bf url(images/alert.png) center no-repeat;
				background-position: 5px 50%;
				text-align: left;
				padding: 2px 20px 2px 25px;
				border: 1px solid #ffd324;
				display: none;
				width: 200px;
				margin-left: 10px;
				font-size: 14px;
            }
    	</style>
		<script type="text/javascript">
    
	        $(function() {
	        	$("#newPassword").val("");
	        	$("#confirmPassword").val("");
	        
	        	//在输入的时候展示输入密码强弱
	           $('#newPassword').bind('keyup',function(key){
	               var len = $.trim($('#newPassword').val()).length;
	               var target = $('#newPassword').next();
	               target.show();
	               if(len >0 && len <= 5){
	                   target.children().eq(1).find('span').css('width','25%');
	                   target.children().eq(2).text('弱');
	               }else if(5 < len && len <= 10){
	                   target.children().eq(1).find('span').css('width','65%');
	                   target.children().eq(2).text('中');
	               }else if(len > 10){
	                   target.children().eq(1).find('span').css('width','85%');
	                   target.children().eq(2).text('强');
	               }
	           });
	           
		        	
	            $("#reg").validate({
	                rules : {
	                    "newPassword" : {
	                        required : true,
	                        minlength : 6,
	                        maxlength : 18
	                    },
	                    "confirmPassword" : {
	                        required : true,
	                        minlength : 6,
	                        maxlength : 18,
	                        equalTo : "#newPassword",
	                    }
	                },
	                messages : {
	                    "newPassword" : {
	                        required : "请输入新密码",
	                        minlength : "新密码长度必须为6-18位数字或字母组合！",
	                        maxlength : "新密码长度必须为6-18位数字或字母组合！"
	                    },
	                    "confirmPassword" : {
	                        required : "请输入确认密码",
	                        minlength : "确认密码长度必须为6-18位数字或字母组合！",
	                        maxlength : "确认密码长度必须为6-18位数字或字母组合！",
	                        equalTo:"新密码与确认密码不一致！"
	                    }
	                },
	                submitHandler : function(){
	                    $("#reg").ajaxSubmit({
			                url:"<%=basePath%>userInfoAction_ajaxForgetUpdatePassword.do",
			                type: 'post',
			                dataType: 'json',
			                beforeSend:function(){
				            	$("#creat_submit").hide();
				            	$("#loadding").show();
			                },
			                success:function(data){
			                	$("#loadding").hide();
			                	alert(data.msg);
			                    if(data.flag){
			                    	location.href="<%=basePath%>";
			                    }else{
			                    	$("#creat_submit").show();
			                    }
			                },
			                error:function(){
			                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
			                    $("#creat_submit").show();
           						$("#loadding").hide();
			                }
			            });
	                    return false;
	                }
	            });
	        });
	    </script>
	</head>

	<body>
		<jsp:include flush="false" page="../../../inc/header.jsp"></jsp:include>
		<script type="text/javascript">
			cssdropdown.startchrome("chromemenu");
		</script>
		<div class="main_cont">
			<div class="main">

				<div class="line1"></div>
				<div class="line2"></div>

				<div class="feedwall">
					<div class="shezhi">
						<form action="#" id="reg" name="reg" method="post">
							<c:if test="${actionErrors == null || actionErrors == '[]'}">
								<input type="hidden" name="code" value="${code }"/>
								<div class="shezhi_t">
									重置密码
								</div>
								<ul>
									<li class="s_name">
										新&nbsp;密&nbsp;码
									</li>
									<li>
										<input id="newPassword" name="newPassword" type="password" class="s_input" />
										<div style="display: none;">
											<span style="font-size: 12px;color: blue;">密码强度</span>
				                            <span style=" background-color: white;border: 1px solid #42BF26;display: inline-block;font-size: 0;height: 5px;padding: 1px;vertical-align: middle;width: 80px;">
				                            	<span style="background-color: #42BF26;display: inline-block;height: 5px;">&nbsp;</span>
				                            </span>
				                            <span class="status-result"></span>
										</div>
									</li>
								</ul>
								<ul>
									<li class="s_name">
										确认密码
									</li>
									<li>
										<input id="confirmPassword" name="confirmPassword" type="password" class="s_input" />
									</li>
								</ul>
								<div class="clearfloat"></div>
								<div  id="creat_submit" class="s_btn">
									<input type="image" src="images/btn_16.gif" />
								</div>
			                    <div id="loadding" class="s_btn" style="display: none;">
			                        <img src='<%=basePath%>images/loading_32.gif'/>正在保存，请稍候
			                    </div>
							</c:if>
							
							<c:if test="${actionErrors != null && actionErrors != '[]'}">
								<div style="text-align: center;">
									<br/><br/><br/><br/><br/><br/>
									<p style="color: red;font-size: 14px;">${actionErrors}</p>
									<br/>
									<p>
										<a style="color: blue;font: 14px;" href="<%=basePath %>setting/forget.jsp">重新找回密码</a>
										<a style="color: blue;font: 14px;" href="<%=basePath %>">首页</a>
									</p>
								</div>
							</c:if>
							
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-----页尾部分---->
		<jsp:include flush="false" page="../../../inc/footer.jsp"></jsp:include>

	</body>
</html>
