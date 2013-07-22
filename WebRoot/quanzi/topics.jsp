<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>话题列表</title>
<link href="css/index.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/topics.js" type="text/javascript"></script>
<script>
<%--var arr_text=['<h3>标题1</h3><p>内容文字1内容文字1内容文字1内容文字1内容文字1内容文字1</p>',--%>
<%--'<h3>标题2</h3><p>内容文2</p>',--%>
<%--'<h3>标题3</h3><p>内容文3</p>',--%>
<%--'<h3>标题4</h3><p>内容文4</p>',--%>
<%--'<h3>标题5</h3><p>内容文5</p>']--%>
window.onload=function()
{
	var oCbanner=document.getElementById('Cbanner');
	var oUl=oCbanner.getElementsByTagName('ul')[0];
	var aLi=oUl.getElementsByTagName('li');
	var oOl=document.getElementById('smile_pic_list');
	var aLi1=oOl.getElementsByTagName('li');
	var oD_banner=document.getElementById('d_banner');
	var num=0;
	
	for(var i=0;i<aLi1.length;i++)
	{
		aLi1[i].index=i;
		aLi1[i].onclick=function()
		{
			num=this.index;
			tab();
		};
	}
	
	function tab()
	{
		for(var i=0;i<aLi1.length;i++)
		{
			aLi1[i].className='';
			aLi[i].style.display='none';
		}
		aLi[num].style.display='block';
		oD_banner.innerHTML=arr_text[num]
	}

};
</script>

  </head>
  <body>
  <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  <!--中间部分 -->
<div class="center_bj"> 
    <div class="center" style="padding-top:5px;padding-bottom:0px;">
       <div class="c_banner" id="Cbanner">
         <ul id="topImgs">
<%--            <li style="display:block;"><a href="###"><img src="images/200851910567179_2.jpg" /></a></li>--%>
<%--            <li><a href="###"><img src="images/2B4F1E86AB3FD6653F42C29F94A8C0B4_500_353.jpg" /></a></li>--%>
<%--            <li><a href="###"><img src="images/200851910567179_2.jpg" /></a></li>--%>
<%--            <li><a href="###"><img src="images/200851910567179_2.jpg" /></a></li>--%>
<%--            <li><a href="###"><img src="images/200851910567179_2.jpg" /></a></li>--%>
							<div id="banner_list"> 
	                		 <div align="center" style="margin-top: 100px;" >
	           			 		<img src="images/loading.gif" />  数据加载中请稍等...
	            			</div>
         </ul>
       
         <div class="d_banner" id="d_banner">
     			<div id="banner_list"> 
             		 <div align="center" style="margin-top: 100px;" >
        			 		<img src="images/loading.gif" />  数据加载中请稍等...</div>
         			</div>
         </div>
         
         <ol class="t_banner" id="smile_pic_list">
<%--           <li><a href="javascript:void();"><img src="images/2B4F1E86AB3FD6653F42C29F94A8C0B4_500_353.jpg" /></a></li>--%>
<%--           <li><a href="javascript:void();"><img src="images/2B4F1E86AB3FD6653F42C29F94A8C0B4_500_353.jpg" /></a></li>--%>
<%--           <li><a href="javascript:void();"><img src="images/200851910567179_2.jpg" /></a></li>--%>
<%--           <li><a href="javascript:void();"><img src="images/200851910567179_2.jpg" /></a></li>--%>
<%--           <li><a href="javascript:void();"><img src="images/200851910567179_2.jpg" /></a></li>--%>
         </ol>
       
       </div>
       
    </div>
</div>
<!--内容部分 -->
<div class="center_bj">
	<div class="center" style="padding-top: 0px;">
		<div class="center_mian" id="showTopics">
			<div style="text-align: center" align="center">
				<img src="images/loading.gif" />
				数据加载中请稍等...
			</div>
		</div>
		<div class="pl_page" id="pl_page"></div>
	</div>
</div>

	</body>
</html>
