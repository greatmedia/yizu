<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${feiWen.title }</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="飞闻 ，逸族飞闻 - ${feiWen.title }">
	<meta http-equiv="description" content="逸族飞闻 - ${feiWen.title }">
	
	<LINK REL="STYLESHEET" TYPE="TEXT/CSS" HREF="css/fei_tu.css"/>
<%--	<script src="js/jquery.js" type="text/javascript"></script>--%>

<%--	<script src="js/feiwenshow.js" type="text/javascript"></script>--%>
  </head>
  
  <body>
  	<div class="top_0"><div class="top"><p>当前位置：<a href="<%=basePath%>">首页</a>&gt;<a href="<%=basePath%>user/${feiWen.userinfo.userid}.html">${feiWen.userinfo.nick}</a>&gt;<a href="javascript:void(0);">新闻中心</a>&gt;<span>${feiWen.title }</span></p></div></div>
  	<div class="box">
  		<div class="title">
			<h1>${feiWen.title }</h1><br />
		</div>
		<div class="title_0">
			<div class="tittle_0_left">
				<p><a href="<%=basePath%>user/${feiWen.userinfo.userid}.html">${feiWen.userinfo.nick }</a> 发表于
				<fmt:formatDate  value="${feiWen.createdatetime }" type="both" dateStyle="long" timeStyle="long"/>
				</p>
			</div>
			<div class="tittle_0_right">
				<script type="text/javascript">
					var jiathis_config = {
						data_track_clickback:'true',
						appkey:{
							"jtico jtico_tsina":"2614308697",
							"tqq":"100255656",
							"t163":"mBjMmCCPvbKOvvQI",
							"tpeople":"184905"
						}
					};
				</script>
				<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?btn=r5.gif&amp;uid=1338348908887337" charset="utf-8"></script>
				<script type="text/javascript" src="http://v1.ujian.cc/code/ujian.js?type=slide"></script>
			</div>
  		</div>
  		<div class="clear"></div>
  		<div class="middle">
			<p>${feiWen.content }</p>
		</div>	
	</div>
  	<div class="bottom">
	<p>Copyright © 2011 - 2012 yeeyan.org
	中国卓越传媒
	京ICP证10000013号 京公网安备11010802009636号</p>
	</div>
  
  
  
  	
<%--    <div style="margin-left: 400px; position: relative; z-index: 997;">--%>
<%--        	<div class="jiathis_style">--%>
<%--			<span class="jiathis_txt" style="font-size: 12px;">&nbsp;&nbsp;&nbsp;分享到：</span>--%>
<%--			<a class="jiathis_button_qzone"></a>--%>
<%--			<a class="jiathis_button_tsina"></a>--%>
<%--			<a class="jiathis_button_tqq"></a>--%>
<%--			<a class="jiathis_button_renren"></a>--%>
<%--			<a class="jiathis_button_kaixin001"></a>--%>
<%--			<a class="jiathis_button_t163"></a>--%>
<%--			<a href="http://www.jiathis.com/share?uid=1639347" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>--%>
<%--			</div>--%>
			<!-- JiaThis Button BEGIN -->
<%--				<div class="jiathis_style_32x32">--%>
<%--					<a class="jiathis_button_tsina"></a>--%>
<%--					<a class="jiathis_button_qzone"></a>--%>
<%--					<a class="jiathis_button_renren"></a>--%>
<%--					<a class="jiathis_button_t163"></a>--%>
<%--					<a class="jiathis_button_tqq"></a>--%>
<%--					<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>--%>
<%--				</div>--%>
<%--				<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=" charset="utf-8"></script>--%>
			<!-- JiaThis Button END -->
			<!-- JiaThis Button BEGIN -->
<%--			<script type="text/javascript" >--%>
<%--				var jiathis_config={--%>
<%--					data_track_clickback:false,--%>
<%--					appkey:{--%>
<%--						"tsina":"2614308697",--%>
<%--						"tqq":"100255656",--%>
<%--						"t163":"mBjMmCCPvbKOvvQI",--%>
<%--						"tpeople":"184905"--%>
<%--					}	--%>
<%--				}--%>
<%--			</script>--%>
			<!-- JiaThis Button END -->
<%--			<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1338348908887337" charset="utf-8"></script>--%>
<%--       </div>--%>
    </body>
</html>