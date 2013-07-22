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
<title>逸族网 - 开启你的第二人生</title>
<meta name="description" content="来吧，从此刻开始，开辟或加入专属自己的逸族圈子吧。哪怕你的爱好再小众，在这里也可以找到你的知己。让我们的身边簇拥着知己，一起分享，一起品位生活的点点滴滴。"/>
<meta name="Keywords" content="逸族,逸族网,逸族社区,逸族商城,第二人生,圈子,交友社区,社区购物,图片分享,话题,热门话题,今日话题，热点话题"/>
<link rel="shortcut icon" href="favicon.gif" type="image/x-icon">
<link rel="Bookmark" href="favicon.gif" type="image/x-icon">
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/show_window.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jsUtil.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/quanzi_index.js" type="text/javascript"></script>
<script src="js/jquery.form.js" type="text/javascript"></script>
<%--<script src="js/delay.js" type="text/javascript"></script>--%>
<script src="js/1.js" type="text/javascript"></script>
<script>
var basePath = "<%=basePath%>";
 	//鼠标滑过弹出层	
	function dis(obj, sType) { 
		var oDiv = document.getElementById(obj); 
		if (sType == 'show') { oDiv.style.display = 'block';} 
		if (sType == 'hide') { oDiv.style.display = 'none';} 
	} 
	 function moreOverType(obj)
     {
	     $("#"+obj).css("display","block");
	 }
     function moustOutType(obj)
     {
         $("#"+obj).css("display","none");
     }
</script>
</head>
<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<input type="hidden" name="tagval" id="tagval" value="1" />
<input type="hidden" name="hotval" id="hotval" value="2" />
<input type="hidden" name="tagvals" id="tagvals" value="1" />
<input type="hidden" name="hotvals" id="hotvals" value="2" />
		<div class="main_cont" id="main_cont">
			<div class="main">
				<div class="hot_nav" id="hot_nav">
					<div style="width: 960px; margin: auto;">
						<div class="cases">
							<a id="nav_bar_latest" href="javascript:void(0);"> <em></em>
								最新圈子 </a>
							<a id="nav_bar_hottest" href="javascript:void(0);"> <em></em>
								热门圈子 </a>
						</div>
						<div class="categories">
							<a class="recent" id="tagtype1"></a>
							<a class="recent" id="tagtype2"></a>
							<a class="recent" id="tagtype3"></a>
							<a class="recent" id="tagtype4"></a>
							<a class="recent" id="tagtype5"></a>
							<a class="recent" id="tagtype6"></a>
							<a onmouseover="moreOverType('nav_bar_more_categories');"
								class="recent" href="javascript:void(0);">更多<em></em>
							</a>
							<div id="nav_bar_more_categories" style="display: none;"
								onmouseover="moreOverType('nav_bar_more_categories');"
								onmouseout="moustOutType('nav_bar_more_categories');"></div>
						</div>

						<div class="switcher">
							<a href="javascript:void(0);" onclick="createquanzi();"><em></em>创建圈子</a>
						</div>
					</div>
				</div>

				<div class="feedwall" id="box">
					<!---第一列数据----->
					<div class="col" id="ones">
						<div id="one"></div>
					</div>

					<!---第二列数据----->
					<div class="col" id="twos">
						<div id="two"></div>

					</div>

					<!---第三列数据----->
					<div class="col" id="threes">
						<div id="three"></div>

					</div>
					<!---第si列数据----->
					<div class="col_last" id="fives">
						<div id="five"></div>
					</div>
				</div>
			</div>
		</div>

		<div id="loading" class="jiazai" >
    	<ul>
        	<li><a href="javascript:void(0);"><img src="images/jz.gif" /></a></li>
        	<li>正在加载...</li>
        </ul>
    </div>
    
    
    <div id="load" class="jiazai" style="display: none;">
    	<ul>
            <li><a href="javascript:void(0);">继续加载...</a></li>
        </ul>
    </div>
    
    <div id="end" class="jiazai" style="display: none; text-align: center; background: url('images/bg.jpg') repeat scroll 0 0 transparent;">
           <a onclick="createquanzi();" href='javascript:void(0);'><img src="images/dibu.GIF" /></a>
    </div>

    <div class="footer">
		<div class="footer_txt">
			<!-----页尾部分---->
	<div class="link">
      <ul>
      	<li><a href="quanzi/about.jsp" target="_blank">关于逸族</a></li>
        <li><a href="quanzi/about.jsp?tag=2" target="_blank">关于逸族网</a></li>
        <li><a href="quanzi/about.jsp?tag=7" target="_blank">友情链接</a></li>
        <li><a href="quanzi/about.jsp?tag=6" target="_blank">联系我们</a></li>
        <li><a href="quanzi/about.jsp?tag=4" target="_blank">版权声明</a></li>
        <li><a href="quanzi/about.jsp?tag=5" target="_blank">服务协议</a></li>
      </ul>
    </div>
    	<div class="type">©Copyright 2010-2011 逸族网（1mily.com) 京ICP备11029264号-2(增值电信业务经营许可证：京ICP证110990号)</div>
        <div class="fixed-bottom" id="toTop" style="display: none;"></div>
    </div>
    	
	</div>
<div style="display:none"><script src="http://s21.cnzz.com/stat.php?id=3921665&web_id=3921665" language="JavaScript"></script></div>
</body>
</html>
