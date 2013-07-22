<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>逸族网 | 都市精英兴趣社交第一平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="来吧，从此刻开始，开辟或加入专属自己的逸族圈子吧。哪怕你的爱好再小众，在这里也可以找到你的知己。让我们的身边簇拥着知己，一起分享，一起品位生活的点点滴滴。">
	<meta http-equiv="description" content="逸族,逸族网,逸族社区,逸族商城,第二人生,圈子,交友社区,社区购物,图片分享,话题,热门话题,今日话题，热点话题">
	<link href="<%=basePath%>css/newindex.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=basePath%>js/modernizr.custom.79639.js"></script>
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/jsUtil.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/public_min.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/morecircle.js" type="text/javascript"></script>
  </head>
  
  <body style=" background: url('../images/bg.jpg') repeat scroll 0 0 transparent;">
    	<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
    	<div class="main">
<!--			<div class="banner" id="banner">-->
<!--				<img id="bannerimg"/>-->
<!--			</div>-->
	    	<div class="newindex_box">
				<div class="newindex_box1">
					<div class="newindex_box1_top">
						<div class="newindex_box1_top_left"></div>
						<div class="newindex_box1_top_middle">
							<p id="cirlcetop"></p>
						</div>
						<div class="newindex_box1_top_right"></div>
					</div>
					<div class="newindex_middle1">
						<ul class="ch-grid" id="morecircleinfo">
						
						</ul>
					</div>
				</div>
			</div>
		</div>	
		<div id="end" class="" style="display: none;  background: url(../images/bg.jpg) repeat scroll 0 0 transparent;">
	    	<ul style="margin: auto auto auto 500px; width:50%;">
	            <li><h2><a href="<%=basePath%>circleInfoAction_create.do" style="color:black;">没有找到心仪的圈子？请<span style="color:#F78D00;">创建一个</span>吧！</a></h2></li>
	        </ul>
   		</div>
	<!-----页尾部分---->
	<jsp:include flush="false" page="../inc/footer.jsp"></jsp:include>
  </body>
</html>
