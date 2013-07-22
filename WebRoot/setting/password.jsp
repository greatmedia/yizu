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
		<title>修改密码</title>
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
    	
    	<c:if test="${user == null}">
    		<script type="text/javascript">
    			location.href="<%=basePath%>";
    		</script>
    	</c:if>
		<script type="text/javascript">
    
	        $(function() {
	        	$("#oldPassword").val("");
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
	                    "oldPassword" : {
	                    	required : true,
	                    	minlength : 6,
	                        maxlength : 18
	                    },
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
	               		"oldPassword" : {
	                    	required : "请输入密码",
	                        minlength : "密码长度必须为6-18位数字或字母组合！",
	                        maxlength : "密码长度必须为6-18位数字或字母组合！"
	                    },
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
			                url:"<%=basePath%>userInfoAction_ajaxUpdatePassword.do",
			                type: 'post',
			                dataType: 'json',
			                beforeSend:function(){
			                	$("#creat_submit").hide();
            					$("#loadding").show();
			                },
			                success:function(data){
			                	alert(data.msg);
			                	$("#loadding").hide();
			                	
			                    if(data.flag){
			                    	$(this)[0].currentForm.reset();
			                    }else{
				                    $("#creat_submit").show();
				                }
			                },
			                error:function(){
				                $("#loadding").hide();
				                $("#creat_submit").show();
			                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
			                }
			            });
	                    return false;
	                }
	            });
	            
	            $("#formSubmit").attr("disabled", false);
	        });
	    </script>
	</head>

	<body>
		<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
		<script type="text/javascript">
			cssdropdown.startchrome("chromemenu");
		</script>
		<div class="main_cont" style="height: 750px;">
			<div class="main">

				<div class="line1"></div>
				<div class="line2"></div>

				<div class="feedwall"  style="margin-top:53px;">
					<div class="shezhi">
						<form action="#" id="reg" name="reg" method="post">
							<div class="shezhi_t">
								确认密码
							</div>
							<ul>
								<li class="s_name">
									原始密码
								</li>
								<li>
									<input id="oldPassword" name="oldPassword" type="password" class="s_input" value=""/>
								</li>
							</ul>
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
									<input id="confirmPassword" name="confirmPassword" type="password" class="s_input"/>
								</li>
							</ul>
							<div class="clearfloat"></div>
							
							<div id="creat_submit" class="s_btn">
		                         <input id="formSubmit" type="submit" value="保存设置"  disabled="disabled"/>
<!--		                         src="images/btn_16.gif"-->
		                    </div>
		                    <div id="loadding" class="s_btn" style="display: none;width: 200px;">
		                        <img src='<%=basePath%>images/loading_32.gif'/>保存中，请稍候......
		                    </div>
						</form>
					</div>

				</div>
			</div>
		</div>

	</body>
</html>
