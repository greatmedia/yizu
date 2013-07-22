<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>" />
<%--		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />--%>
		<title>账号设置</title>
		<script src="js/alljs.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery.js" type="text/javascript"></script>
		<script src="<%=basePath %>js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=basePath%>js/profile_setting.js" type="text/javascript"></script>
		<script type="text/javascript">
			function sc(){
				$("#form1").ajaxSubmit({
			         url:"<%=basePath %>centerAction_uploadFile.do",
			         type: 'post',
			         dataType: 'json',
			         beforeSend:function(){
			         	 $("#showPhoto").attr("src","images/loading_32.gif");
			         },
			         success:function(data){
			             if(data.flag){
			                 $("#showPhoto").attr("src", data.data);
			                 $("#image").val(data.data);
			             }else{
			            	 	alert(data.msg);
			             }
			         },
			         error:function(){
			             alert("保存失败，请稍后重试，如果错误依然存在请联系管理员");
			         }
			     });
			}
		</script>
</head>

<body >

<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<div class="main_cont">
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu")
</script>

    <div class="user_name">
        <ul>
        </ul>
    </div>

<div class="main">
    
    <div class="line1"></div>
    <div class="line2"></div>

	<div class="feedwall">
    <!---第一列数据----->
   	  <jsp:include flush="false" page="profile_left.jsp"></jsp:include>
   <!---右边部分-----> 
        
     <div class="profile_cont">
            
        <div class="profile_center">
           
           <div class="profile_nav">
               <ul>
                   <li><a  href="profit/profile.jsp">我的发言</a></li>
                   <li><a style="border-left:none;" href="profit/profile_circle.jsp">我加入的圈子</a></li>
                   <li><a style="border-left:none;" href="profit/profile_mycircle.jsp">我创建的圈子</a></li>
                   <li><a style="border-left:none;" href="profit/profile_vote.jsp">我创建的投票</a></li>
                   <li><a style="border-left:none; border-right:none;"href="profit/profile_gz.jsp">我关注的商品</a></li>
                   <li><a id="active" href="profit/profile_setting.jsp">账号设置</a></li>
               </ul>
           </div>
           
            <div class="profile_titer">
               <div class="prof_mine">
                      <div class="prof_shezhi">
	                       <ul>
		                       <li class="s_name" >邮箱地址</li>
		                       <li>
		                          <input type="text" id="user_email" class="pro_input" style="width:273px; line-height:31px;" value="${user.email}" />
		                          <div id="email_msg"></div>
		                       </li>
<!-- 		                       <li><img src="images/btn_14.jpg" /></li> -->
	                       </ul>
                       
	                       <ul>
		                       <li class="s_name">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</li>
		                       <li>
		                         <input type="text" class="pro_input"  id="user_nick" value="${user.nick}"  />
		                       </li>
	                       </ul>
                       <ul>
                       <li class="s_name">头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像</li>
                       <li class="pro_name">
                       <c:choose>
  							<c:when test="${user.image != null && user.image != ''}">
   							<img style="width:120px;height:120px;" id="showPhoto" src="<%=basePath%>${user.image}" />
  							</c:when>
  							<c:otherwise>
   							<img style="width:120px;height:120px;" id="showPhoto" src="" />
   						</c:otherwise>
  						</c:choose>
                       </li>
                       <li style=" clear:both; margin-top:10px; margin-left:80px;">
	                       <div class="creat-img">
                     	   	   <form action="userInfoAction_uploadFile.do" id="form1" name="form1" method="post" enctype="multipart/form-data"  >
	                          	   <input id="upFile" name="upFile" class="ifile" type="file" size="20" />
		                           <input type="button" style="background-image:url(images/btn_15.jpg); width:79px; height:30px; border:none;" onclick="document.getElementById('upFile').click()" class="bot_2words"/>
		                           <input id="image" name="instance.image" value="${user.image}" type="text" style="display:none;" autocomplete= "off"/>
	                           </form>
	                       </div>
                       </li>
                      
                       </ul>
                       <ul style=" background:none;"><li class="s_name">关于自己</li>
                       <li><textarea rows="7" cols="50" style="border:1px #f78f00 solid; line-height:31px; "  id='user_about'>${user.declaration}</textarea></li></ul>
                       <div class="clearfloat"></div>
                       <div class="pro_btn"><a id="saveSettingButton" href='javascript:void(0);' onclick="setData();"><img src="images/btn_17.gif" /></a><label style="display: none;" id="saveSetting">保存设置中...</label></div>
                       <div id="showSS"></div>
                   </div>
                     
               </div> 
            </div>
           
        </div>
      
    </div>
</div>
</div>

</div>
<!-----页尾部分---->
<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>
</body>
</html>
