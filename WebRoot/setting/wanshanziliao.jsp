<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>完善个人资料</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="来吧，从此刻开始，开辟或加入专属自己的逸族圈子吧。哪怕你的爱好再小众，在这里也可以找到你的知己。让我们的身边簇拥着知己，一起分享，一起品位生活的点点滴滴。">
	<meta http-equiv="description" content="逸族,逸族网,逸族社区,逸族商城,第二人生,圈子,交友社区,社区购物,图片分享,话题,热门话题,今日话题，热点话题">
	<link href="css/css/zhucestyle.css" rel="stylesheet" type="text/css" />
	<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
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
		<c:if test="${user.image!=null }">
			<script type="text/javascript">
    			location.href="<%=basePath%>";
    		</script>
		</c:if>
    	<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
		<div class="box_zhuce">
			<div class="inner_box">
				<div class="nav_zhuce">
					<ul>
						<li>
							<a href="/registerinfo.html" style="background-color: #ff8a00; color: #fff;">1 完善个人资料 </a>
						</li>
						<li>
<!--							<a href="/uploadphoto.html" style="margin-left:10px;">2 上传个人头像</a>-->
								<a href="centerAction_uploadphoto.do" style="margin-left:10px;">2 上传个人头像</a>-->
						</li>
					</ul>
				</div>
				<div class="tittle">
					<p>
						丰富你的个人资料
					</p>
				</div>
				<div class="middle">
					<div class="middle_left">
						<p>
							你的身份：
						</p>
					</div>
					<div class="middle_right">
						<dl>
							<dt>
								<img src="<%=basePath %>images/register/04.jpg" />
							</dt>
							<dd>
								<input id="girl" name="sex" checked="checked" type="radio" value="1"/>
								<span class="checkbox">逸骑士</span>
							</dd>
						</dl>
						<dl>
							<dt>
								<img src="<%=basePath %>images/register/03.jpg" />
							</dt>
							<dd>
								<input id="boy" name="sex" type="radio" value="2" />
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
										<input type="hidden" id="text1" value="品牌" />
									</div>
								</li>
								<li>
									<a id="cell_interest2" href="javascript:void(0);"
										class="interest02"
										onclick="showinterest('cell_interest2', 'check_interest2');"></a>
									<div id="check_interest2" class="yes_d">
										<input type="hidden" id="text2" value="旅游" />
									</div>
								</li>
								<li>
									<a id="cell_interest3" href="javascript:void(0);"
										class="interest03"
										onclick="showinterest('cell_interest3', 'check_interest3');"></a>
									<div id="check_interest3" class="yes_d">
										<input type="hidden" id="text3" value="营销" />
									</div>
								</li>
								<li>
									<a id="cell_interest4" href="javascript:void(0);"
										class="interest04"
										onclick="showinterest('cell_interest4', 'check_interest4');"></a>
									<div id="check_interest4" class="yes_d">
										<input type="hidden" id="text4" value="电商" />
									</div>
								</li>
								<li>
									<a id="cell_interest5" href="javascript:void(0);"
										class="interest05"
										onclick="showinterest('cell_interest5', 'check_interest5');"></a>
									<div id="check_interest5" class="yes_d">
										<input type="hidden" id="text5" value="财经" />
									</div>
								</li>
								<li>
									<a id="cell_interest6" href="javascript:void(0);"
										class="interest06"
										onclick="showinterest('cell_interest6', 'check_interest6');"></a>
									<div id="check_interest6" class="yes_d">
										<input type="hidden" id="text6" value="文学" />
									</div>
								</li>
								<li>
									<a id="cell_interest7" href="javascript:void(0);"
										class="interest07"
										onclick="showinterest('cell_interest7', 'check_interest7');"></a>
									<div id="check_interest7" class="yes_d">
										<input type="hidden" id="text7" value="高尔夫" />
									</div>
								</li>
								<li>
									<a id="cell_interest8" href="javascript:void(0);"
										class="interest08"
										onclick="showinterest('cell_interest8', 'check_interest8');"></a>
									<div id="check_interest8" class="yes_d">
										<input type="hidden" id="text8" value="桥牌" />
									</div>
								</li>
								<li>
									<a id="cell_interest9" href="javascript:void(0);"
										class="interest09"
										onclick="showinterest('cell_interest9', 'check_interest9');"></a>
									<div id="check_interest9" class="yes_d">
										<input type="hidden" id="text9" value="桌球" />
									</div>
								</li>
								<li>
									<a id="cell_interest10" href="javascript:void(0);"
										class="interest10"
										onclick="showinterest('cell_interest10', 'check_interest10');"></a>
									<div id="check_interest10" class="yes_d">
										<input type="hidden" id="text10" value="骑马" />
									</div>
								</li>
								<li>
									<a id="cell_interest11" href="javascript:void(0);"
										class="interest11"
										onclick="showinterest('cell_interest11', 'check_interest11');"></a>
									<div id="check_interest11" class="yes_d">
										<input type="hidden" id="text11" value="钓鱼" />
									</div>
								</li>
								<li>
									<a id="cell_interest12" href="javascript:void(0);"
										class="interest12"
										onclick="showinterest('cell_interest12', 'check_interest12');"></a>
									<div id="check_interest12" class="yes_d">
										<input type="hidden" id="text12" value="棋类" />
									</div>
								</li>
								<li>
									<a id="cell_interest13" href="javascript:void(0);"
										class="interest13"
										onclick="showinterest('cell_interest13', 'check_interest13');"></a>
									<div id="check_interest13" class="yes_d">
										<input type="hidden" id="text13" value="摄影" />
									</div>
								</li>
								<li>
									<a id="cell_interest14" href="javascript:void(0);"
										class="interest14"
										onclick="showinterest('cell_interest14', 'check_interest14');"></a>
									<div id="check_interest14" class="yes_d">
										<input type="hidden" id="text14" value="绘画" />
									</div>
								</li>
								<li>
									<a id="cell_interest15" href="javascript:void(0);"
										class="interest15"
										onclick="showinterest('cell_interest15', 'check_interest15');"></a>
									<div id="check_interest15" class="yes_d">
										<input type="hidden" id="text15" value="舞蹈" />
									</div>
								</li>
								<li>
									<a id="cell_interest16" href="javascript:void(0);"
										class="interest16"
										onclick="showinterest('cell_interest16', 'check_interest16');"></a>
									<div id="check_interest16" class="yes_d">
										<input type="hidden" id="text16" value="音乐" />
									</div>
								</li>
								<li>
									<a id="cell_interest17" href="javascript:void(0);"
										class="interest17"
										onclick="showinterest('cell_interest17', 'check_interest17');"></a>
									<div id="check_interest17" class="yes_d">
										<input type="hidden" id="text17" value="电影" />
									</div>
								</li>
								<li>
									<a id="cell_interest18" href="javascript:void(0);"
										class="interest18"
										onclick="showinterest('cell_interest18', 'check_interest18');"></a>
									<div id="check_interest18" class="yes_d">
										<input type="hidden" id="text18" value="收藏" />
									</div>
								</li>
								<li>
									<a id="cell_interest19" href="javascript:void(0);"
										class="interest19"
										onclick="showinterest('cell_interest19', 'check_interest19');"></a>
									<div id="check_interest19" class="yes_d">
										<input type="hidden" id="text19" value="读书" />
									</div>
								</li>
								<li>
									<a id="cell_interest20" href="javascript:void(0);"
										class="interest20"
										onclick="showinterest('cell_interest20', 'check_interest20');"></a>
									<div id="check_interest20" class="yes_d">
										<input type="hidden" id="text20" value="话剧" />
									</div>
								</li>
								<li>
									<a id="cell_interest21" href="javascript:void(0);"
										class="interest21"
										onclick="showinterest('cell_interest21', 'check_interest21');"></a>
									<div id="check_interest21" class="yes_d">
										<input type="hidden" id="text21" value="手绘" />
									</div>
								</li>
								<li>
									<a id="cell_interest22" href="javascript:void(0);"
										class="interest22"
										onclick="showinterest('cell_interest22', 'check_interest22');"></a>
									<div id="check_interest22" class="yes_d">
										<input type="hidden" id="text22" value="瑜伽" />
									</div>
								</li>
								<li>
									<a id="cell_interest23" href="javascript:void(0);"
										class="interest23"
										onclick="showinterest('cell_interest23', 'check_interest23');"></a>
									<div id="check_interest23" class="yes_d">
										<input type="hidden" id="text23" value="泡吧" />
									</div>
								</li>
								<li>
									<a id="cell_interest24" href="javascript:void(0);"
										class="interest24"
										onclick="showinterest('cell_interest24', 'check_interest24');"></a>
									<div id="check_interest24" class="yes_d">
										<input type="hidden" id="text24" value="集邮" />
									</div>
								</li>
								<li>
									<a id="cell_interest25" href="javascript:void(0);"
										class="interest25"
										onclick="showinterest('cell_interest25', 'check_interest25');"></a>
									<div id="check_interest25" class="yes_d">
										<input type="hidden" id="text25" value="红酒" />
									</div>
								</li>
								<li>
									<a id="cell_interest26" href="javascript:void(0);"
										class="interest26"
										onclick="showinterest('cell_interest26', 'check_interest26');"></a>
									<div id="check_interest26" class="yes_d">
										<input type="hidden" id="text26" value="咖啡" />
									</div>
								</li>
								<li>
									<a id="cell_interest27" href="javascript:void(0);"
										class="interest27"
										onclick="showinterest('cell_interest27', 'check_interest27');"></a>
									<div id="check_interest27" class="yes_d">
										<input type="hidden" id="text27" value="美食" />
									</div>
								</li>
								<li>
									<a id="cell_interest28" href="javascript:void(0);"
										class="interest28"
										onclick="showinterest('cell_interest28', 'check_interest28');"></a>
									<div id="check_interest28" class="yes_d">
										<input type="hidden" id="text28" value="美容" />
									</div>
								</li>
								<li>
									<a id="cell_interest29" href="javascript:void(0);"
										class="interest29"
										onclick="showinterest('cell_interest29', 'check_interest29');"></a>
									<div id="check_interest29" class="yes_d">
										<input type="hidden" id="text29" value="游戏" />
									</div>
								</li>
								<li>
									<a id="cell_interest30" href="javascript:void(0);"
										class="interest30"
										onclick="showinterest('cell_interest30', 'check_interest30');"></a>
									<div id="check_interest30" class="yes_d">
										<input type="hidden" id="text30" value="时尚" />
									</div>
								</li>
								<li>
									<a id="cell_interest31" href="javascript:void(0);"
										class="interest31"
										onclick="showinterest('cell_interest31', 'check_interest31');"></a>
									<div id="check_interest31" class="yes_d">
										<input type="hidden" id="text31" value="探索" />
									</div>
								</li>
								<li>
									<a id="cell_interest32" href="javascript:void(0);"
										class="interest32"
										onclick="showinterest('cell_interest32', 'check_interest32');"></a>
									<div id="check_interest32" class="yes_d">
										<input type="hidden" id="text32" value="冒险" />
									</div>
								</li>
								<li>
									<a id="cell_interest33" href="javascript:void(0);"
										class="interest33"
										onclick="showinterest('cell_interest33', 'check_interest33');"></a>
									<div id="check_interest33" class="yes_d">
										<input type="hidden" id="text33" value="赚钱" />
									</div>
								</li>
								<li>
									<a id="cell_interest34" href="javascript:void(0);"
										class="interest34"
										onclick="showinterest('cell_interest34', 'check_interest34');"></a>
									<div id="check_interest34" class="yes_d">
										<input type="hidden" id="text34" value="购物" />
									</div>
								</li>
								<li>
									<a id="cell_interest35" href="javascript:void(0);"
										class="interest35"
										onclick="showinterest('cell_interest35', 'check_interest35');"></a>
									<div id="check_interest35" class="yes_d">
										<input type="hidden" id="text35" value="宅" />
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
						<textarea cols="60" rows="5" id="userwhat"></textarea>
						<p style="float:none;">
							* 请简短的描述自己，140字以内！
						</p>
					</div>
				</div>
				<div class="middle" style="border-bottom: none;">
					<div class="cj">
						<a href="javascript:void(0);" onclick="saveUserInfo();"></a>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
