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
    
    <title>更多热议话题</title>
    
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
    <jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
  	<jsp:include flush="false" page="circletop.jsp"></jsp:include>
	<div class="circle_middle2">
  		<div class="circle_middle2_left_huati">
    		<div class="circle_middle2_huati">
      			<div class="circle_middle2_top_huati" id="moretalk">
			        
      			</div>
      		</div>
      		<div class="circle3_middle2_left3" id="moretalkabout" style="display:none;">
					
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
		showmoretalkabout(5,1,1);
	</script>
</body>
</html>
