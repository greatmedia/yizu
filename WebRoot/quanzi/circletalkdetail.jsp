<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>话题详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="${circleleft.circletag}">
	<meta http-equiv="description" content="${circleleft.circlename} -圈子">
	<link href="css/style.css" type="text/css" rel="stylesheet" />
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/circle.js" type="text/javascript"></script>
	
  </head>
  
 <body  style="background: url('../images/bg.jpg') repeat scroll 0 0 transparent;color: #626262;font-family: '宋体';font-size: 12px;">
    h<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  	<jsp:include flush="false" page="circletop.jsp"></jsp:include>
	<div class="circle_middle2">
  		<div class="circle_middle2_left_huati">
    		<div class="circle_middle2_huati">
      			<div class="circle_middle2_top_huati">
       				<ul>
          				<li>
            				<h1>热门话题讨论：</h1>
            				<input type="hidden" id="tdid" value="${talkabout.id }"/>
            				<h2 style="font-size:16px; font-weight:bold;">${talkabout.talktitle }</h2>
          				</li>
          				<div class="clear"></div>
          				<li style="margin-top:20px;"><span><a href="user/${circleleft.userinfo.userid }.html">${circleleft.userinfo.nick }</a>发表于：<fmt:formatDate value="${talkabout.updatetime }"  type="both" /></span></li>
        			</ul>
        			<div id="talkdetail">
        				
        			</div>
      			</div>
	      		<div class="circle3_middle2_left3" id="moretalkdetail" style="display:none;" >
<!--	      			<a>查看更多</a>-->
				</div>
    		</div>
		</div>
		
  		<div class="circle_middle2_right">
  			<div class="circle_middle2_right1">
      			<p>最新加入</p>
      				<c:forEach var="usercir" items="${userCircleRlss}" varStatus="u" >
						<a target="_blank" href="user/${usercir.userinfo.userid }.html">
							<c:choose>
								<c:when test="${usercir.userinfo.image == null || usercir.userinfo.image =='' }">
									<img src="../images/nobody.gif" title="${usercir.userinfo.nick }" />
								</c:when>
								<c:otherwise>
									<img src="${usercir.userinfo.image }" title="${usercir.userinfo.nick }" />
								</c:otherwise>
							</c:choose>
						</a>
					</c:forEach>
      		</div>
			<div class="circle_middle2_right3">
			     <p style="float:left;">圈子成员（${count}）</p><p style="float:right; margin-right:10px;  font-size:12px;">
			      	<c:if test="${count > 12}">
			      		<a href="cir_user/${circleleft.circleid }.html" style="color:#626262;">更多</a>
			      	</c:if>
			      </p> 
			</div>
   			<div class="circle_middle2_right4">
   				<c:forEach var="cUser" items="${circleAlluser}">
  						<dl>
		        		<dt><a href="user/${cUser.userid}.html">
		        			<c:choose>
		        				<c:when test="${cUser.userinfo.image == null || cUser.userinfo.image == ''}">
		        					<img src="../images/nobody.gif" />
		        				</c:when>
		        				<c:otherwise>
		        					<img src="${cUser.userinfo.image}" />
		        				</c:otherwise>
		        			</c:choose>
		        			</a></dt>
		        		<dd><a href="user/${cUser.userid}.html">${cUser.userinfo.nick }</a></dd>
	      			</dl>
   				</c:forEach>
   			</div>
  		</div>
	</div>
	<script type="text/javascript">
		showtalkdetail(20,1,1);
	</script>
</body>
</html>
