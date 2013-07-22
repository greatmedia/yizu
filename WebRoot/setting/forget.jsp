<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>逸族网-找回密码</title>
<%--		<link href="css/style.css" type="text/css" rel="stylesheet" />--%>
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
            .resultright{
            	background: #fff6bf url(images/success.gif) center no-repeat;
				background-position: 5px 50%;
				text-align: left;
				padding: 8px 20px 8px 30px;
				border: 1px solid #ffd324;
				width: 400px;
				margin-left: 10px;
				font-size: 14px;
            }
            .resulterror{
            	background: #fff6bf url(images/alert.png) center no-repeat;
				background-position: 5px 50%;
				text-align: left;
				padding: 8px 20px 8px 30px;
				border: 1px solid #ffd324;
				width: 400px;
				margin-left: 10px;
				font-size: 14px;
            }
            .resultload{
            	background: #fff6bf url(images/loading.gif) center no-repeat;
				background-position: 5px 50%;
				text-align: left;
				padding: 8px 20px 8px 50px;
				border: 1px solid #ffd324;
				width: 400px;
				margin-left: 10px;
				font-size: 14px;
            }
    	</style>
		<script type="text/javascript">
    
	        $(function() {
	        	$("#email").val("");
	        
	            $("#reg").validate({
	                rules : {
	                    "instance.email" : {
	                    	required : true,
	                    	email:true
	                    }
	                },
	                messages : {
	               		"instance.email" : {
	                    	required : "请输入注册邮箱",
	                        email : "邮箱格式错误"
	                    }
	                },
	                submitHandler : function(){
	                	$("#msg").hide();
	                    $("#reg").ajaxSubmit({
			                url:"<%=basePath%>userInfoAction_ajaxForgetSendEmail.do",
			                type: 'post',
			                dataType: 'json',
			                beforeSend:function(){
			                	$("#msg").show();
			                	$("#msg").addClass("resultload");
			                	$("#msg").removeClass("resulterror");
		                    	$("#msg").removeClass("resultright");
			                	$("#msg").html("正在发送邮件，请稍候...");
			                	$("#submitButton").attr("disabled", true);
			                },
			                success:function(data){
			                 	$("#submitButton").attr("disabled", false);
			                	$("#msg").removeClass("resultload");
			                	$("#msg").html(data.msg);
			                    if(data.flag){
			                    	$("#msg").removeClass("resulterror");
			                    	$("#msg").addClass("resultright");
			                    }else{
			                    	$("#msg").removeClass("resultright");
			                    	$("#msg").addClass("resulterror");
			                    }
			                },
			                error:function(){
			                	$("#msg").removeClass("resultload");
			                	$("#msg").removeClass("resultright");
		                    	$("#msg").addClass("resulterror");
			                    $("#msg").html("发送失败，请稍后重试。");
			                     $("#submitButton").attr("disabled", false);
			                }
			            });
	                    return false;
	                }
	            });
	            
	            $("#submitButton").attr("disabled", false);
	        });
	    </script>
	</head>

	<body>
		<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
		<script type="text/javascript">
			cssdropdown.startchrome("chromemenu");
		</script>
		<div class="main_cont">
			<div class="main">

				<div class="line2"></div>

				<div class="feedwall">
					<div class="shezhi">
						<form action="#" id="reg" name="reg" method="post">
							<div class="shezhi_t">
								找回密码
							</div>
							<ul>
								<li class="s_name">
									注册邮箱：
								</li>
								<li>
									<input id="registerEmail" name="instance.email" type="text" class="s_input" value="" />
									<input id="submitButton" disabled="disabled" type="button" value="发送" onclick="$('#reg').submit();"/>
								</li>
								<li class="s_name" style="width: 400px;">
									<span style="display: none;" class="resultright" id="msg"></span>
								</li>
							</ul>
							<div class="clearfloat"></div>
							<div class="s_btn">
								
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
		<!-----页尾部分---->
		<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>

	</body>
</html>
