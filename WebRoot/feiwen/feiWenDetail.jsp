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
	<meta http-equiv="keywords" content="飞闻 ，逸族飞闻 - ${feiWen.tag }">
	<meta http-equiv="description" content="逸族飞闻 - ${feiWen.title }">
	
	<LINK REL="STYLESHEET" TYPE="TEXT/CSS" HREF="css/fei_tu.css"/>
	<script src="js/jquery.js" type="text/javascript"></script>
  </head>
  
  <body>
  	<div class="top_0"><div class="top"><p>当前位置：<a href="<%=basePath%>">首页</a>&gt;<a href="<%=basePath%>feiwenshowone/${tid}/1/1.html">${type.title }飞闻</a>&gt;<span>${feiWen.title }</span></p></div></div>
  	<div class="box">
  		<div class="title">
			<h1>${feiWen.title }</h1>
		</div>
		<div class="time">
			<input type="hidden" value="${feiWen.createdatetime }" id="createdatetime"/>
			<p id="createdatetimeString"></p>
			<script type="text/javascript">
					$(function(){
						var result;
						var createdatetime = $("#createdatetime").val();
					    createdatetime = calcTime(createdatetime, '+0');
						var nowd = calcTime(new Date(),'+8');
						var now = nowd.getTime();
						var time = Date.parse(createdatetime)-6*1000*60*60;
						var nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
						var nh = 1000 * 60 * 60;// 一小时的毫秒数
						var nm = 1000 * 60;// 一分钟的毫秒数
						var ns = 1000;// 一秒钟的毫秒数
						var diff = now - time;
						if (diff > ns) {
							var nss = diff / ns;
							nss = Math.round(nss);
							result = "发表于" + nss + "秒钟前";
						}
						if (diff > nm) {
							var nms = diff / nm;
							nms = Math.round(nms);
							result = "发表于" + nms + "分钟前";
						}
						if (diff > nh) {
							var nhs = diff / nh;
							nhs = Math.round(nhs);
							result = "发表于" + nhs + "小时前";
						}
						if (diff > nd) {
							var nds = diff / nd;
							nds = Math.round(nds);
							result = "发表于" + nds + "天前";
						}
						$("#createdatetimeString").html(result);
					});
					function calcTime(time, offset) {
						time = new Date(time);
						utc =  time.getTime() + (time.getTimezoneOffset() * 60000);
						nd = new Date(utc + (3600000*offset));
						return nd;
					}
				</script>
		</div> 
		<div class="middle">
			<p>${feiWen.content }</p>
		</div>
		<div class="link">
			<ul>
				<li class="float_left">
				<c:choose>
					<c:when test="${pageUPfid == null}">
						<a href="javascript:void(0);" title="没有上一页">
						<input id="btnLoging" type="button"  class="loginBtn_hover" disabled="disabled" />
						</a>
					</c:when>
					<c:otherwise>
						<a href="<%=basePath%>feiwenshowone/${tid}/${pageNum-1}/${pageUPfid}.html" >
						<input id="btnLoging" type="button"  class="loginBtn_hover" onmouseover="this.className='loginBtn'" onmouseout="this.className='loginBtn_hover'"  />
						</a>
					</c:otherwise>
				</c:choose>
				</li>
				<li class="float_right">
					<a href="<%=basePath%>feiwenshowone/${tid}/${pageNum+1}/${pageDOWNfid}.html">
						<input id="btnLoging_right" type="button"  class="loginBtn_right_hover" onmouseover="this.className='loginBtn_right'" onmouseout="this.className='loginBtn_right_hover'" />
					</a>
				</li>
			</ul>
		</div>
		<div id="uyan_frame"></div>
		<script type="text/javascript" id="UYScript" src="http://v1.uyan.cc/js/iframe.js?UYUserId=1639347" async=""></script>
		<div class="news">
			<ul>
			<li><span>话题推荐</span></li>
			<c:forEach var="newTopics" items="${newTopics}" varStatus="i">
				<c:choose>
                   <c:when test="${fn:length(newTopics.title) > 30}">
                   		<li><A href="<%=basePath%>topic_tu/${newTopics.circledetailid}.html" >${fn:substring(newTopics.title,0,30)}...... </A></li>
                   </c:when>
                <c:otherwise>
                    <li><A href="<%=basePath%>topic_tu/${newTopics.circledetailid}.html">${newTopics.title} </A></li>
                 </c:otherwise>
               </c:choose>
			</c:forEach>
			</ul>
			<ul>
			<li><span>其他文章</span></li>
			<c:forEach var="otherDetail" items="${otherDetail}" varStatus="i">
				<c:choose>
                   <c:when test="${fn:length(otherDetail.title) > 15}">
                   		<li><A href="<%=basePath%>det/${otherDetail.circleid}/${otherDetail.circledetailid}.html" >${fn:substring(otherDetail.title,0,15)}...... </A></li>
                   </c:when>
                <c:otherwise>
                    <li><A href="<%=basePath%>det/${otherDetail.circleid}/${otherDetail.circledetailid}.html">${otherDetail.title}</A></li>
                 </c:otherwise>
               </c:choose>
			</c:forEach>
			</ul>
		</div>	
  	</div>
  	<div class="bottom" style="display:none;">
	<p>Copyright © 2011 - 2012 yeeyan.org
	中国卓越传媒
	京ICP证10000013号 京公网安备11010802009636号</p>
	</div>
  	  <script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0&amp;btn=r5.gif&amp;uid=1338348908887337" charset="utf-8"></script>
	  <script type="text/javascript" >
			var jiathis_config={
				data_track_clickback:false,
				appkey:{
					"tsina":"2614308697",
					"tqq":"100255656",
					"t163":"mBjMmCCPvbKOvvQI",
					"tpeople":"184905"
				}	
			}
	  </script>
    </body>
</html>