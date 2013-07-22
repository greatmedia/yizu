<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<input type="hidden" id="circleid" value="${circleleft.circleid }"/>
			<input type="hidden" id="islogin" value="${islogin}"/>
			<input type="hidden" id="quanzhuid" value="${circleleft.userid }"/>
    		<div class="circle_middle1">
  				<div class="circle_middle1_neirong">
  					<div class="circle_middle1_photo_out">
    					<div class="circle_middle1_photo"><img src="${circleleft.circlebigimg }" /></div>
    					<div class="circle_middle1_photo_xu"><img src="${circleleft.circlebigimg }" /></div>
  					</div>
    				<div class="circle_middle1_neirong_left">
      					<h1>${circleleft.circlename }</h1>
      					<c:choose>
							<c:when test="${user != null && isAddCircle > 0}">
								<h2><a href="<%=basePath%>circleInfoAction_circleInfoPublish.do?id=${circleleft.circleid }">发布内容</a></h2>
								<span>
								<c:choose>
									<c:when test="${user.userid == circleleft.userid }">
										<a href="<%=basePath%>circleInfoAction_update.do?id=${circleleft.circleid }">编辑圈子</a>
									</c:when>
									<c:otherwise>
<%--										<a href="<%=basePath%>circleInfoAction_update.do?id=${circleleft.circleid }">编辑圈子</a>--%>
										<a href="javascript:join_or_quit_quanzi('${circleleft.circleid }',2)">退出</a>
									</c:otherwise>
								</c:choose>
								</span>
							</c:when>
							<c:otherwise>
								<h2><a href="javascript:join_or_quit_quanzi('${circleleft.circleid }',1)">加入圈子</a></h2>
							</c:otherwise>
						</c:choose>
      					<div class="clear"></div>
      					<p>
      					<c:choose>
		                    <c:when test="${fn:length(circleleft.summary) > 70}">
		                    	${fn:substring(circleleft.summary, 0, 70)}...
		                    </c:when>
		                    <c:otherwise> 
		     					${circleleft.summary}
		    				</c:otherwise>
		                </c:choose>
      					</p>
      					<c:forEach var="relate" items="${circletagrelate}" varStatus="c">
      						<c:choose>
      							<c:when test="${c.index == 3}">
      								<dl style="margin-right:0px;">
      							</c:when>
      							<c:otherwise>
      								<dl>
      							</c:otherwise>
      						</c:choose>
      							<a href="<%=basePath %>cirtag/1/${circleleft.circleid }/${relate.cirtag.ctid }.html"><dt><h3 style="color:#626262;">${c.index+1 }</h3></dt></a>
<!--						     	<dt><a href="<%=basePath %>cirtag/1/${circleleft.circleid }/${relate.cirtag.ctid }.html"><img src="images/liangcang.jpg" /></a></dt>-->
						     	<dd><a href="<%=basePath %>cirtag/1/${circleleft.circleid }/${relate.cirtag.ctid }.html">${relate.cirtag.tagname }</a></dd>
					    	</dl>
						</c:forEach>
    				</div>
    				<div class="circle_middle1_neirong_right">
				      	<dl>
				        	<dt>
				        		<div class="test1_mask" ></div>
                            	<div class="test1_cont"><a href="user/${circleleft.userinfo.userid }.html"><img src="${circleleft.userinfo.image }" style="width:80px;"/></a></div>
				        	</dt>
				        	<dd>圈主：<a href="user/${circleleft.userinfo.userid }.html" style="color:#626262;">${circleleft.userinfo.nick }</a></dd>
				      	</dl>
				    </div>
  				</div>
  			</div>