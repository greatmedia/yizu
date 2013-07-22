<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>更改个人资料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="">
	<link href="css/css/zhucestyle.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/changeuserinfo.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/wanshan.js" type="text/javascript"></script>
	<script src="<%=basePath%>js/timetime.js" type="text/javascript"></script>
	<script>var basePath = "<%=basePath%>";</script>

  </head>
  
  <body>
    	<c:if test="${user == null}">
			<script type="text/javascript">
    			location.href="<%=basePath%>";
    		</script>
		</c:if>
		<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
		<div class="box_zhuce">
			<div class="inner_box">
				<div class="tittle">
					<p>更改你的个人资料</p>
				</div>
				<div class="middle">
					<div class="middle_left">
						<p>你的昵称：</p>
					</div>
					<div class="middle_right">
						<input class="input1" style=" width:180px; height:28px; margin:0 auto; padding:0px; border:#cccccc 1px solid; overflow:hidden; font-size:12px; color:#666; border-radius:0px; overflow:hidden; padding-left:10px;" type="text" id="usernick" value="${user.nick }"/>
					</div>
				</div>	
				<div class="middle">
					<div class="middle_left">
						<p>你的身份：</p>
					</div>
					<div class="middle_right">
						<dl>
							<dt>
								<img src="<%=basePath %>images/register/04.jpg" />
							</dt>
							<dd>
								<c:choose>
									<c:when test="${user.sex == '1' || user.sex == null || user.sex == '0'}">
										<input name="sex" checked="checked" type="radio" value="1"/>
									</c:when>
									<c:otherwise>
										<input name="sex" type="radio" value="1"/>
									</c:otherwise>
								</c:choose>
								<span class="checkbox">逸骑士</span>
							</dd>
						</dl>
						<dl>
							<dt>
								<img src="<%=basePath %>images/register/03.jpg" />
							</dt>
							<dd>
								<c:choose>
									<c:when test="${user.sex == 2}">
										<input name="sex" checked="checked" type="radio" value="2"/>
									</c:when>
									<c:otherwise>
										<input name="sex" type="radio" value="2"/>
									</c:otherwise>
								</c:choose>
								<span class="checkbox">逸千金</span>
							</dd>
						</dl>
					</div>
				</div>
				<div class="clear"></div>
				<div class="middle">
					<div class="middle_left">
						<p>
							你的生日：
						</p>
					</div>
					<form name="theForm">
					<div class="middle_right2">
						<select id="year" class="skin-text-willwhite_zhuce" name="YYYY" onchange="YYYYMM(this.value)"
							style="height: 30px; overflow: auto; width: 85px;">
						</select>
						<select id="month" class="skin-text-willwhite_zhuce" name="MM" onchange="MMDD(this.value)"
							style="height: 30px; overflow: auto; width: 85px;">
						</select>
						<select id="day" class="skin-text-willwhite_zhuce" name="DD"
							style="height: 30px; overflow: auto; width: 85px;">
						</select>
						<p>
							* 请填写生日信息，我们将会在生日送出逸族网的意外惊喜！
						</p>
					</div>
					</form>
				</div>
				<div class="middle">
					<div class="middle_left">
						<p>
							你的兴趣：
						</p>
					</div>
					<div class="middle_right2">
						<div class="interest">
							<script type="text/javascript">
								function showinterest(imgid,divid)
								{
									
									var obj = document.getElementById(divid);
									var obj_img = document.getElementById(imgid);
									if (obj.className == "yes_d") {
										obj.className = "yes_d_no";
										obj_img.style.opacity = 0.7;
									} else {
										obj.className = "yes_d";
										obj_img.style.opacity = 1.0;
									}
									
								}
							</script>
							<ul id="habby">
								<li>
									<a id="cell_interest1" href="javascript:void(0);"
										class="interest01"
										onclick="showinterest('cell_interest1', 'check_interest1');"></a>
									<div id="check_interest1" class="yes_d">
										<input type="hidden" id="text1" value="读书"/>
									</div>
								</li>
								<li>
									<a id="cell_interest2" href="javascript:void(0);"
										class="interest02"
										onclick="showinterest('cell_interest2', 'check_interest2');"></a>
									<div id="check_interest2" class="yes_d">
										<input type="hidden" id="text2" value="饮茶"/>
									</div>
								</li>
								<li>
									<a id="cell_interest3" href="javascript:void(0);"
										class="interest03"
										onclick="showinterest('cell_interest3', 'check_interest3');"></a>
									<div id="check_interest3" class="yes_d">
										<input type="hidden" id="text3" value="电影"/>
									</div>
								</li>
								<li>
									<a id="cell_interest4" href="javascript:void(0);"
										class="interest04"
										onclick="showinterest('cell_interest4', 'check_interest4');"></a>
									<div id="check_interest4" class="yes_d">
										<input type="hidden" id="text4" value="健身"/>
									</div>
								</li>
								<li>
									<a id="cell_interest5" href="javascript:void(0);"
										class="interest05"
										onclick="showinterest('cell_interest5', 'check_interest5');"></a>
									<div id="check_interest5" class="yes_d">
										<input type="hidden" id="text5" value="音乐"/>
									</div>
								</li>
								<li>
									<a id="cell_interest6" href="javascript:void(0);"
										class="interest06"
										onclick="showinterest('cell_interest6', 'check_interest6');"></a>
									<div id="check_interest6" class="yes_d">
										<input type="hidden" id="text6" value="游戏"/>
									</div>
								</li>
								<li>
									<a id="cell_interest7" href="javascript:void(0);"
										class="interest07"
										onclick="showinterest('cell_interest7', 'check_interest7');"></a>
									<div id="check_interest7" class="yes_d">
										<input type="hidden" id="text7" value="书法"/>
									</div>
								</li>
								<li>
									<a id="cell_interest8" href="javascript:void(0);"
										class="interest08"
										onclick="showinterest('cell_interest8', 'check_interest8');"></a>
									<div id="check_interest8" class="yes_d">
										<input type="hidden" id="text8" value="美食"/>
									</div>
								</li>
								<li>
									<a id="cell_interest9" href="javascript:void(0);"
										class="interest09"
										onclick="showinterest('cell_interest9', 'check_interest9');"></a>
									<div id="check_interest9" class="yes_d">
										<input type="hidden" id="text9" value="摄影"/>
									</div>
								</li>
								<li>
									<a id="cell_interest10" href="javascript:void(0);"
										class="interest10"
										onclick="showinterest('cell_interest10', 'check_interest10');"></a>
									<div id="check_interest10" class="yes_d">
										<input type="hidden" id="text10" value="舞蹈"/>
									</div>
								</li>
								<li>
									<a id="cell_interest11" href="javascript:void(0);"
										class="interest11"
										onclick="showinterest('cell_interest11', 'check_interest11');"></a>
									<div id="check_interest11" class="yes_d">
										<input type="hidden" id="text11" value="钓鱼"/>
									</div>
								</li>
								<li>
									<a id="cell_interest12" href="javascript:void(0);"
										class="interest12"
										onclick="showinterest('cell_interest12', 'check_interest12');"></a>
									<div id="check_interest12" class="yes_d">
										<input type="hidden" id="text12" value="画画"/>
									</div>
								</li>
								<li>
									<a id="cell_interest13" href="javascript:void(0);"
										class="interest13"
										onclick="showinterest('cell_interest13', 'check_interest13');"></a>
									<div id="check_interest13" class="yes_d">
										<input type="hidden" id="text13" value="红酒"/>
									</div>
								</li>
								<li>
									<a id="cell_interest14" href="javascript:void(0);"
										class="interest14"
										onclick="showinterest('cell_interest14', 'check_interest14');"></a>
									<div id="check_interest14" class="yes_d">
										<input type="hidden" id="text14" value="财经"/>
									</div>
								</li>
								<li>
									<a id="cell_interest15" href="javascript:void(0);"
										class="interest15"
										onclick="showinterest('cell_interest15', 'check_interest15');"></a>
									<div id="check_interest15" class="yes_d">
										<input type="hidden" id="text15" value="集邮"/>
									</div>
								</li>
								<li>
									<a id="cell_interest16" href="javascript:void(0);"
										class="interest16"
										onclick="showinterest('cell_interest16', 'check_interest16')"></a>
									<div id="check_interest16" class="yes_d">
										<input type="hidden" id="text16" value="棋类"/>
									</div>
								</li>
					            <li>
					            	<a id="cell_interest17"  href="javascript:void(0);" 
					            		class="interest17" 
					            		onclick="showinterest('cell_interest17', 'check_interest17');"></a>
					            		<div id="check_interest17" class="yes_d">
					            			<input type="hidden" id="text17" value="高尔夫"/>
					            		</div>
					            </li>
					            <li>
					            	<a id="cell_interest18"  href="javascript:void(0);" 
					            		class="interest18" 
					            		onclick="showinterest('cell_interest18', 'check_interest18');"></a>
					            	<div id="check_interest18" class="yes_d">
					            		<input type="hidden" id="text18" value="桥牌"/>
					            	</div>
					            </li>
					            <li>
					            	<a id="cell_interest19"  href="javascript:void(0);" 
					            	class="interest19" 
					            	onclick="showinterest('cell_interest19', 'check_interest19');"></a>
					            	<div id="check_interest19" class="yes_d">
					            		<input type="hidden" id="text19" value="桌球"/>
					            	</div>
					            </li>
					            <li>
					            	<a id="cell_interest20"  href="javascript:void(0);" 
					            	class="interest20" 
					            	onclick="showinterest('cell_interest20', 'check_interest20');"></a>
					            	<div id="check_interest20" class="yes_d">
					            		<input type="hidden" id="text20" value="泡吧"/>
					            	</div>
					            </li>
					            <li>
					            	<a id="cell_interest21"  href="javascript:void(0);" 
					            	class="interest21" 
					            	onclick="showinterest('cell_interest21', 'check_interest21');"></a>
					            	<div id="check_interest21" class="yes_d">
					            		<input type="hidden" id="text21" value="骑车"/>
					            	</div>
					            </li>
								<li>
									<a id="cell_interest22" href="javascript:void(0);"
										class="interest22"
										onclick="showinterest('cell_interest22', 'check_interest22');"></a>
									<div id="check_interest22" class="yes_d">
										<input type="hidden" id="text22" value="瑜伽"/>
									</div>
								</li>
								<li>
									<a id="cell_interest23" href="javascript:void(0);"
										class="interest23"
										onclick="showinterest('cell_interest23', 'check_interest23');"></a>
									<div id="check_interest23" class="yes_d">
										<input type="hidden" id="text23" value="美容"/>
									</div>
								</li>
								<li>
									<a id="cell_interest24" href="javascript:void(0);"
										class="interest24"
										onclick="showinterest('cell_interest24', 'check_interest24');"></a>
									<div id="check_interest24" class="yes_d">
										<input type="hidden" id="text24" value="SOSPORT"/>
									</div>
								</li>
								<li>
									<a id="cell_interest25" href="javascript:void(0);"
										class="interest25"
										onclick="showinterest('cell_interest25', 'check_interest25');"></a>
									<div id="check_interest25" class="yes_d">
										<input type="hidden" id="text25" value="网球"/>
									</div>
								</li>
								<li>
									<a id="cell_interest26" href="javascript:void(0);"
										class="interest26"
										onclick="showinterest('cell_interest26', 'check_interest26');"></a>
									<div id="check_interest26" class="yes_d">
										<input type="hidden" id="text26" value="网球"/>								
									</div>
								</li>
								<li>
									<a id="cell_interest27" href="javascript:void(0);"
										class="interest27"
										onclick="showinterest('cell_interest27', 'check_interest27');"></a>
									<div id="check_interest27" class="yes_d">
										<input type="hidden" id="text27" value="购物"/>
									</div>
								</li>
								<li>
									<a id="cell_interest28" href="javascript:void(0);"
										class="interest28"
										onclick="showinterest('cell_interest28', 'check_interest28');"></a>
									<div id="check_interest28" class="yes_d">
										<input type="hidden" id="text28" value="收藏"/>
									</div>
								</li>
							</ul>
						</div>
						<div class="interest_info_right">
							<span>你的兴趣真广泛，你可以在这里补充你的兴趣，打造你的黄金人脉圈。</span>
							<div class="clear"></div>
							<p>
								我还喜欢：
							</p>
							<div class="clear"></div>
							<textarea id="habbymore"></textarea>
							<p>
								<a href="javascript:void(0);" onclick="insertmorehabby()">添加</a>
							</p>
						</div>
					</div>
				</div>
				<div class="middle">
					<div class="middle_left">
						<p>
							你来自：
						</p>
					</div>
					<script>initProvince();</script>
					<div class="middle_right2">
						<select id="province" class="skin-text-willwhite_zhuce" onchange="getCityFromProvince()"
							style="height: 30px; overflow: auto; width: 85px;">
						</select>
						<select id="city" class="skin-text-willwhite_zhuce" onchange="getTownFromCity()"
							style="height: 30px; overflow: auto; width: 85px;">
						</select>
						<select id="town" class="skin-text-willwhite_zhuce"
							style="height: 30px; overflow: auto; width: 85px;">
						</select>
<%--						<p>--%>
<%--							* --%>
<%--						</p>--%>
					</div>
				</div>
				<div class="middle">
					<div class="middle_left" style="margin-top:50px;">
						<p>
							个人素描：
						</p>
					</div>
					<div class="middle_right2">
						<textarea cols="60" rows="5" id="userwhat">${user.what }</textarea>
						<p style="float:none;">
							* 请简短的描述自己，140字以内！
						</p>
					</div>
				</div>
				<div class="middle" style="border-bottom: none;">
					<div class="cj">
						<a href="javascript:void(0);" onclick="updateuserinfo();"></a>
					</div>
				</div>
			</div>
		</div>
  </body>
</html>
