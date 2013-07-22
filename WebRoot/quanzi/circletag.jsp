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

<body style="background: url('../images/bg.jpg') repeat scroll 0 0 transparent;color: #626262;font-family: '宋体';font-size: 12px;">
	<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
	<jsp:include flush="false" page="circletop.jsp"></jsp:include>	
	<div class="personal_middle2">
		<c:forEach var="detail" items="${circleDetailInfos}" varStatus="in">
	  		<div class="personal_middle2_wenzi" >
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
				        <div class="personal_middle2_wenzi_top">
				          <p><a href="det/${circleleft.circleid }/${detail.circledetailid }.html">${detail.title} </a>
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
				          <p><span><fmt:formatDate value="${detail.createdatetime}"  type="date" dateStyle="default"/> • ${detail.comcount }条评论</span></p>
				        </div>
	        			<div class="clear"></div>
	        			<div class="personal_middle2_wenzi_middle">
	          				<p>
	          					<c:choose>
		                    		<c:when test="${fn:startsWith(detail.circlecontent,'<embed')}">
		                    			${detail.circlecontent}
		                    		</c:when>
		                    		<c:otherwise>
		                    			<c:choose>
		                    				<c:when test="${fn:length(detail.circlecontent) >200}">
		                    					${fn:substring(detail.circlecontent, 0, 200)}......
		                    				</c:when>
		                    				<c:otherwise> 
		     									${detail.circlecontent}
		    								 </c:otherwise>
		                    			</c:choose>
		                    		</c:otherwise>
		                    	</c:choose>
	          				</p>
	        			</div>
	        			<div class="personal_middle2_wenzi_middle"><span><a href="det/${detail.circleid }/${detail.circledetailid }.html">查看更多>></a></span> </div>
	      			</dd>
	    		</dl>
	  		</div>
  		</c:forEach>
  		<div id="circle_center_detail"></div>
  		<c:if test="${hot > 10}">
  			<div class="themore" id="next_page">
			<a href="javascript:getmoredetail(${pageNum+1},'${circleleft.circleid }','${pid}')">查看更多</a>
 		</div>
  		</c:if>
  	</div>
</body>
</html>

