<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>个人中心首页</title>
    <link href="css/user_me.css" type="text/css" rel="stylesheet" />
    <script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="js/usermore_me.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/public_min.js" type="text/javascript"></script> 
	<script type="text/javascript"> var basePath = "<%=basePath%>";</script>
</head>

<body >
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="center_bj ">
    <div class="usename_bj">
        <div class="me_center">
            <div class="me_left">
                <ul>
<%--                    <li id="1"><a href="javascript:void(0);" onclick="showleft(1)">我的发言</a></li>--%>
<%--                    <li id="3"><a href="javascript:void(0);" onclick="showleft(3)">我加入的圈子</a></li>--%>
<%--                    <li id="4"><a href="javascript:void(0);" onclick="showleft(4)">我创建的圈子</a></li>--%>
                    <li id="7"><a href="javascript:void(0);" onclick="showleft(7)">我的最新动态</a></li>
<%--                    <li id="2"><a href="javascript:void(0);" onclick="showleft(2)">我创建的投票</a></li>--%>
                    <li id="8"><a href="javascript:void(0);" onclick="showleft(8)">我的访客</a></li>
                </ul>
            </div>
			<!-- 发言-->
<%--            <div class="me_right" id="TAtopic" style="display:none;">--%>
<%--                <ul class="spak_me" id="hesaid"> --%>
<%--                </ul>--%>
<%--                <div class="pl_page" id="topic_pl_page"></div>--%>
<%--            </div>--%>
            <!-- 投票-->
<%--            <div class="me_right" id="TAvote" style="display:none;">--%>
<%--            	<ul class="spak_me" id="hevote"> --%>
<%--                </ul>--%>
<%--                <div class="pl_page" id="vote_pl_page"></div>--%>
<%--            </div>--%>
            <!--加入的圈子-->
<%--            <div class="me_right" id="TAcircle" style="display:none;">--%>
<%--                  <ul class="join_me" id="hecircle">--%>
<%--                  </ul>--%>
<%--                  <div class="pl_page" id="circle_pl_page"></div>--%>
<%--        	</div>--%>
        	<!--创建的圈子-->
<%--        	<div class="me_right" id="TAcreate" style="display:none;">--%>
<%--        		 <ul class="join_me" id="hecreate">--%>
<%--                  </ul>--%>
<%--                  <div class="pl_page" id="create_pl_page"></div>--%>
<%--            </div>--%>
			<%-- 动态--%>
            <div class="me_right" id="Menotice" style="display:none;">
                <ul class="spak_me" id="notice"> 
                </ul>
                <div class="pl_page" id="notice_page"></div>
            </div>
            <%-- 访客--%>
            <div class="me_right" id="myVisitor" style="display:none;">
                <ul class="spak_me" id="visitor"> 
                </ul>
                <div class="pl_page" id="visitor_page"></div>
            </div>
             <!--修改密码  -->
<%--             <div class="me_right" id="userpassword" style="display:none;">--%>
<%--                <div class="num_h4">修改密码</div>--%>
<%--                <div class="num_center">--%>
<%--                	<form action="#" id="reg" name="reg" method="post">--%>
<%--                   <table border="0" cellpadding="0" cellspacing="0" width="700px;" >--%>
<%--                       <tbody>--%>
<%--                           <tr height="35">--%>
<%--                              <td width="14%">原始密码:</td>--%>
<%--                              <td width="86%">--%>
<%--                              <input id="oldPassword" name="oldPassword" type="password" class="num_ibput1" value=""/>--%>
<%--                              </td>--%>
<%--                           </tr>--%>
<%--                           <tr height="20"></tr>--%>
<%--                           <tr height="35">--%>
<%--                              <td>新密码：</td>--%>
<%--                              <td>--%>
<%--                              <input id="newPassword" name="newPassword" type="password" class="num_ibput1" />--%>
<%--                              </td>--%>
<%--                           </tr> --%>
<%--                           <tr height="20"></tr>--%>
<%--                           <tr height="35">--%>
<%--                              <td>确认密码：</td>--%>
<%--                              <td>--%>
<%--                              	<input id="confirmPassword" name="confirmPassword" type="password" class="num_ibput1"/>--%>
<%--                              </td>--%>
<%--                           </tr>--%>
<%--                           <tr height="20"></tr>--%>
<%--                           <tr height="30">--%>
<%--                              <td></td>--%>
<%--                              <td>--%>
<%--                              	<div id="creat_submit"><a href="javascript:void(0);" onclick="$('#reg').submit();"><img src="images/num_butn.gif" /></a></div>--%>
<%--                              </td>--%>
<%--                           </tr>   --%>
<%--                                                --%>
<%--                       </tbody>--%>
<%--                   </table>--%>
<%--                   </form>--%>
<%--                </div>--%>
<%--            </div>--%>

        </div>
    </div>
</div>
<script type="text/javascript">
        $(function() {
        	$("#oldPassword").val("");
        	$("#newPassword").val("");
        	$("#confirmPassword").val("");
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
		                },
		                success:function(data){
		                	alert(data.msg);
		                    if(data.flag){
		                    	location.href = location.href;
		                    }else{
		                    	$("#newPassword").val("");
		                    	$("#confirmPassword").val("");
			                }
		                },
		                error:function(){
		                    alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
		                }
		            });
                    return false;
                }
            });
            
            $("#formSubmit").attr("disabled", false);
        });
	    </script>
</body>
</html>