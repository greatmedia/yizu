<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>话题列表</title>
<link href="<%=basePath %>css/index.css" type="text/css" rel="stylesheet" />
<link href="css/style.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>css/topic.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/newTopics.js" type="text/javascript"></script>
<script src="<%=basePath%>js/topicall.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath%>js/om-core.js" type="text/javascript"></script>
<script src="<%=basePath%>js/om-fileupload.js" type="text/javascript"></script>
<link href="css/om-fileupload.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.css" />
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/kindeditor.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/lang/zh_CN.js"></script>
<script charset="utf-8" src="<%=basePath %>kindeditor-4.1.1/plugins/code/prettify.js"></script>
<script type="text/javascript" src="js/om-validate.js"></script>
<script type="text/javascript">var basePath = "<%=basePath%>";</script>
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
</head>

<body>
<!--头部代码 -->
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>

<div class="topic_center">
      <div class="topic1_center" >
            <div class="pic_nav" id="pic_nav_new" style="display:block;">
              <ul id="b_new">
              </ul>
              
              <div class="pic_roav" >
                 <ol id="pic_roav_new">
                 </ol>
              </div>
         	</div>
         	<div class="pic_nav" id="pic_nav_hot" style="display:none;">
              <ul id="b_hot">
              </ul>
              
              <div class="pic_roav" >
                 <ol id="pic_roav_hot">
                 </ol>
              </div>
         	</div>
         <div class="topic_new">
        <!--话题页面左边的代码 -->
             <div class="topic_left" id="div1">
                <div class="topic_lunbo" id="btn">
                   <ul>
                       <li><a class="topic_active" href="javascript:void(0)" onclick="topicClick(1)" id="new">最新话题</a></li>
                       <li><a href="javascript:void(0)" onclick="topicClick(2)" id="hot">热门话题</a></li>
                       <li><a href="javascript:void(0)" onclick="topicClick(3)" id="pk">PK话题</a></li>
<%--                       <li><a href="javascript:void(0)" onclick="topicClick(4)" id="fabu">发布话题</a></li>--%>
                   </ul>  
                </div>                 
                <div class="topic_subqie" id="newTopics"  style="display:block;" >
                </div> 
                <div class="topic_subqie" id="hotTopics">
                </div>
                <div class="topic_subqie" id="pkTopics">
                </div>
                <div class="topic_subqie" id="fabuTopic">

							
							
							
							<div class="details_cont_n">
            <div class="details-left">
          <!-- 标题部分 -->
          <form id="reg" name="reg" action="" method="post">
                   <div class="create_value">
                       <h3>标题</h3>
                       <div class="creat-con" style="height: 30px;">
						<input name="circleDetail.circleid" id="circleid" type="hidden" class="m_input" value="${id }"/>
						<input name="circleDetail.title" id="title" type="text" class="cterat-value1" />
						<input type="checkbox" style="display: none;" id="def1" name="circleDetail.def1"  onclick="checkedsT()" value="1"/>
						<input type="checkbox" id="def2" name="circleDetail.def2" onclick="checkeds()" value="1"/>PK话题
					</div>
					<div id="pkdiv" style="display: none;">
						<br/><br/>
						蓝方意见：<input name="circleDetail.title" id="title" type="text" class="cterat-value1" />
						<br/><br/>
						红方意见：<input name="circleDetail.title" id="title" type="text" class="cterat-value1" />
					</div>
					
					
					<!-- 描述部分 -->
					<br />
					<div class="create_value">
						<h3>内容</h3>
					</div>
					<div class="creat-con">
						<textarea name="circleDetail.circlecontent" id="circlecontent" cols="79" style="width:650px;height: 250px;visibility:hidden;"></textarea>
					</div>
					<div class="creat-photo" style="height: 50px;">
						
					</div>

					<!--图片部分 -->

					<div class="creat-photo">
						<div class="creat-img">
							<input type="file" id="file_upload" name="file_upload"/>
							<input type="hidden" id="images" name="images"/>
						</div>
						<div class="img_right">
							<font color="red">*</font>JPG、GIF、PNG或BMP，不超过20MB，一次可上传多张。
						</div>
					</div>
					
					 <div class="cteat-post-yin" id="yin">
                        <ul id="uploadImages">
						
						</ul>
                     </div> 
					
					<div  id="creat_submit" class="creat_submit">
						<img src="images/btn_11.jpg" onclick="history.back();"/>
						<input id="formSubmit" type="image" src="images/btn_09.jpg" />
					</div>
                    <div id="loadding" class="creat_submit" style="display: none;">
                        <img src='<%=basePath%>images/loading_32.gif'/>正在创建圈子内容，请稍候......
                    </div>
	       	 </div>
		</form>
   		 </div>
    </div>
							
							
							
							
							
									
					
					
					
                </div>
                
              </div>
             <!--话题页面右边的代码 -->
             <div class="topic_right">
                <div class="topic_r_center">
                    <h3>热门用户:</h3>
                    <!--热门用户7个-->
                    <ul id="hotUser">
                    </ul>
<%--                    <div class="topic_more" id="topic_more"><a href="javascript:void(0);" onclick="moreUser()" >查看更多</a></div>--%>
						<div class="topic_more" id="topic_more"><a target="_blank" href="quanzi/topicHotUserBig.jsp" >查看更多</a></div>
                    
                </div>
             </div>
         </div>     
      </div>
</div>

<!-----页尾部分---->
<%--<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>--%>
<script>
function checkeds()
{
		if(document.getElementById("def2").checked == true)
		{
			$("#pkdiv").css("display","block");
		}else{
			$("#pkdiv").css("display","none");
		}
<%--	if(document.getElementById("def2").checked == true)--%>
<%--	{--%>
<%--		document.getElementById("def1").checked = true;--%>
<%--	}--%>
<%--	if(document.getElementById("def2").checked == false)--%>
<%--	{--%>
<%--		document.getElementById("def1").checked = false;--%>
<%--	}--%>

}
function checkedsT()
{
	if(document.getElementById("def1").checked == false)
	{
		document.getElementById("def2").checked = false;
	}
}
</script>
</body>
</html>
