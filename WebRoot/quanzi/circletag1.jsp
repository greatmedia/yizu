<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${circleleft.circlename}</title>
<meta name="description" content="${circleleft.circlename} -圈子"/>
<meta name="Keywords" content="${circleleft.circletag}"/>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<script src="js/alljs.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/public_min.js" type="text/javascript"></script>
<script src="js/scroll.js" type="text/javascript"></script>
<script src="js/circle.js" type="text/javascript"></script>
<script type="text/javascript">
    var basePath = "<%=basePath%>";
</script> 
</head>

<body  onkeydown="if(event.keyCode==13){return false;}">
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<div class="main_cont" >
	<div class="main" id="min" >
    	<div class="user_name" style="display:none;">
        	<ul>
            	<li class="name1" id="circleName">${circleleft.circlename}</li>
        		<li class="name1"><span id="quanzi_title"></span></li>
        		<c:if test="${user != null}">
				<c:choose>
					<c:when test="${isAddCircle>0}">
						<li class="tcqz"><img src="images/btn_02.gif" id="fabu" onclick="window.open('<%=basePath%>circleInfoAction_circleInfoPublish.do?id=<%=request.getParameter("id") %>')" style="display: block;"/></li>
            			<li class="tcqz"><img src="images/btn_01.gif" id="quit" onclick="join_or_quit_quanzi('<%=request.getParameter("id") %>',2)" style="display: block;"/></li>
					</c:when>
        		</c:choose>
        		</c:if>
        	</ul>
    	</div>
		<input type="hidden" id="circleid" value="${circleleft.circleid}"/>
		<c:choose>
			<c:when test="${user != null}">
				<input type="hidden" id="islongin" value="${user.userid }"/>
				<input type="hidden" id="userid" value="${user.userid }"/>
				<input type="hidden" id="quanzhuid" value="${circleleft.userid }"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="islongin"/>
			</c:otherwise>
		</c:choose>
		<div class="quanzi">
     	<jsp:include flush="false" page="circle_left.jsp"></jsp:include>
     	<!---中间的数据-----> 
     	<div class="quanzi_right">
			<div class="quanzi_right1_0">
				<div class="quanzi_right1">
					<div class="quanzi_biaoqian1">
						<div class="quanzi_biaoqian1_left">
							<p><strong>标签</strong></p>
							<ul>
								<li><a href="<%=basePath %>cirtag/1/${circleleft.circleid }/0.html" >全部</a></li>
							<c:forEach var="relate" items="${circletagrelate}" varStatus="c">
<%--								<li><a href="javascript:void(0);" onclick="getmoredetail(1,'${circleleft.circleid }','${relate.cirtag.ctid }')">${relate.cirtag.tagname }</a></li>--%>
								<li><a href="<%=basePath %>cirtag/1/${circleleft.circleid }/${relate.cirtag.ctid }.html" >${relate.cirtag.tagname }</a>
								</li>
							</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
			
			<div id="circle_center_detail">
				<c:forEach var="detail" items="${circleDetailInfos}" varStatus="in" >
					<div class="quanzi_right3">
						<dl>
							<c:forEach var="img" items="${detail.circleDetailImg}" varStatus="jn">
								<c:if test="${jn.index == 0}">
									<c:choose>
										<c:when test="${img.middleimg !=null && img.middleimg != ''}">
				                    	<dt><a target="_blank" href="det/${detail.circleid }/${detail.circledetailid }.html"><img src="${img.middleimg }"/></a></dt>
				                    	</c:when>
				                    	<c:otherwise>
				                    	<dt><a target="_blank" href="det/${detail.circleid }/${detail.circledetailid }.html"><img src="../images/003.jpg"/></a></dt>
				                    	</c:otherwise>
									</c:choose>
				                </c:if>
							</c:forEach>
							<dd>
							<div class="quanzi_right3_top">
								<p><a target="_blank" href="det/${circleleft.circleid }/${detail.circledetailid }.html">${detail.title }</a>
								<c:if test="${user.userid == circleleft.userid}">
									<select id="changetag" onchange="changetag('${detail.circledetailid}',$(this).val())">
										<c:forEach var="relate" items="${circletagrelate}" varStatus="c">
											<c:choose>
											<c:when test="${relate.ctid == detail.def4 }">
												<option value="${relate.ctid}" id="ctid" selected="selected">${relate.cirtag.tagname}</option>
											</c:when>
											<c:otherwise>
												<option value="${relate.ctid}">${relate.cirtag.tagname}</option>
											</c:otherwise>
										</c:choose>
										</c:forEach>
									</select> 
								</c:if>
								</p>
								<p><span><fmt:formatDate value="${detail.createdatetime}"  type="date" dateStyle="default"/></span></p>
							</div>
							<div class="clear"></div>
							<div class="quanzi_right3_middle">
								<p>
									<c:choose>
		                    		<c:when test="${fn:startsWith(detail.circlecontent,'<embed')}">
		                    			${detail.circlecontent}
		                    		</c:when>
		                    		<c:otherwise>
		                    			<c:choose>
		                    				<c:when test="${fn:length(detail.circlecontent) > 300}">
		                    					${fn:substring(detail.circlecontent, 0, 300)}......
		                    				</c:when>
		                    				<c:otherwise> 
		     									${detail.circlecontent}
		    								 </c:otherwise>
		                    			</c:choose>
		                    		</c:otherwise>
		                    		</c:choose>
								</p>
							</div>
							<div class="quanzi_right3_middle">
								<span><a href="det/${circleleft.circleid }/${detail.circledetailid }.html">继续阅读>></a></span>
							</div>
							</dd>
						</dl>
					</div>
			</c:forEach>
		</div>
		</div>
 		</div>
 		<div class="themore" id="next_page">
<%-- 			<a href="javascript:void(0);" onclick="getmoredetail(${pageNum+1},'${circleleft.circleid }',document.getElementById('ctid').value);">查看更多</a>--%>
			<a href="javascript:void(0);" onclick="getmoredetail(${pageNum+1},'${circleleft.circleid }','${pid}')">查看更多</a>
 		</div>
	</div>
</div>
<%--<script type="text/javascript">--%>
<%--	showtalk(1);--%>
<%--</script>--%>
<%--<script type="text/javascript" src="http://v1.ujian.cc/code/ujian.js?type=slide"></script>--%>
</body>
</html>

