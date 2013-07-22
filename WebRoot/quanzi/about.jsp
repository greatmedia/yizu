<%@ page contentType="text/html;charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>关于逸族</title>
<link href="<%=basePath%>css/style.css" type="text/css" rel="stylesheet" />
<link href="<%=basePath%>css/sharejs.css" type="text/css" rel="stylesheet" />
<script src="<%=basePath%>js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="<%=basePath%>js/jquery.form.js" type="text/javascript"></script>
<script src="<%=basePath%>js/alljs.js" type="text/javascript"></script>
<script src="<%=basePath%>js/lrscroll.js" type="text/javascript"></script>
</head>   

<body>
<jsp:include flush="false" page="../inc/header.jsp"></jsp:include>
<script type="text/javascript">
	cssdropdown.startchrome("chromemenu");
</script>
<script type="text/javascript">
$(function(){
	var tag=getQueryStringRegExp("tag");
	if(tag==1){
		yizu();
	}
	else if(tag==2){
		yizus();
	}else if(tag==3){
		vote();
	}else if(tag==4){
		banquan();
	}else if(tag==5){
		service();
	}else if(tag==6){
		contactUs();
	}else if(tag==7){
		link();
	}
});
function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
	
function yizu(){
	document.title="关于逸族";
	$("#aboutYizu").css("display","block");
	$("#aboutYizus").css("display","none");
	$("#aboutVote").css("display","none");
	$("#aboutBan").css("display","none");
	$("#aboutSer").css("display","none");
	$("#contactUs").css("display","none");
	$("#link").css("display","none");
}
function yizus(){
	document.title="关于逸族网";
	$("#aboutYizu").css("display","none");
	$("#aboutYizus").css("display","block");
	$("#aboutVote").css("display","none");
	$("#aboutBan").css("display","none");
	$("#aboutSer").css("display","none");
	$("#contactUs").css("display","none");
	$("#link").css("display","none");
}
function vote(){
	document.title="关于投票";
	$("#aboutYizu").css("display","none");
	$("#aboutYizus").css("display","none");
	$("#aboutVote").css("display","block");
	$("#aboutBan").css("display","none");
	$("#aboutSer").css("display","none");
	$("#contactUs").css("display","none");
	$("#link").css("display","none");
}
function banquan(){
	document.title="版权声明";
	$("#aboutYizu").css("display","none");
	$("#aboutYizus").css("display","none");
	$("#aboutVote").css("display","none");
	$("#aboutBan").css("display","block");
	$("#aboutSer").css("display","none");
	$("#contactUs").css("display","none");
	$("#link").css("display","none");
}
function service(){
	document.title="服务协议";
	$("#aboutYizu").css("display","none");
	$("#aboutYizus").css("display","none");
	$("#aboutVote").css("display","none");
	$("#aboutBan").css("display","none");
	$("#aboutSer").css("display","block");
	$("#contactUs").css("display","none");
	$("#link").css("display","none");
}
function contactUs(){
	document.title="联系我们";
	$("#aboutYizu").css("display","none");
	$("#aboutYizus").css("display","none");
	$("#aboutVote").css("display","none");
	$("#aboutBan").css("display","none"); 
	$("#aboutSer").css("display","none");
	$("#contactUs").css("display","block");
	$("#link").css("display","none");
}
function link(){
	document.title="友情连接";
	$("#aboutYizu").css("display","none");
	$("#aboutYizus").css("display","none");
	$("#aboutVote").css("display","none");
	$("#aboutBan").css("display","none"); 
	$("#aboutSer").css("display","none");
	$("#contactUs").css("display","none");
	$("#link").css("display","block");
	
}
</script>
<div class="main_cont">
	<div class="main" style="padding-top:42px;">
		<!--     <div class="user_name"> -->
		<!--         <ul> -->
		<!--             <li class="name1">画眉深浅<img src="images/ico_02.gif" width="15" height="15" /></li> -->
		<!--             <li class="tcqz"><img src="images/btn_02.gif" /></li> -->
		<!--             <li class="tcqz"><img src="images/btn_01.gif" /></li> -->
		<!--         </ul> -->
		<!--     </div> -->
		<!-- 	<div class="line1"></div> -->
		<!--     <div class="line2"></div> -->

		<div class="feedwall">
			<div class="l_about">
				<ul>
					<li class="a_hover">
						<a href="javascript:void(0)" onclick="yizu()">关于逸族</a>
					</li>
					<li class="a_hover">
						<a href="javascript:void(0)" onclick="yizus()">关于逸族网</a>
					</li>
					<li class="a_hover" onclick="vote()">
						<a href="javascript:void(0)" onclick="vote()">关于投票</a>
					</li>
					<li class="a_hover">
						<a href="javascript:void(0)" onclick="banquan()">版权声明</a>
					</li>
					<li class="a_hover">
						<a href="javascript:void(0)" onclick="service()">服务协议</a>
					</li>
					<li class="a_hover">
						<a href="javascript:void(0)" onclick="contactUs()">联系我们</a>
					</li>
					<li class="a_hover" style="display: none;">
						<a href="javascript:void(0)" onclick="link()" style="display: none;">友情链接</a>
					</li>
				</ul>
			</div>
			
			<div style="float:right; width: 757px;" id="aboutYizu">
				<div class="details_cont">
					<div class="r_about">
						<div class="a_title">
							关于逸族
						</div>
						<p>
							一朵花从破种到绽放，至少需要三个月，有人从旁走过，匆匆一瞥，仅用了一秒钟；
							<br />
							一杯茶从采摘到入口，需要八道工序，有人漫不经心，一饮而尽，仅用了一分钟；
							<br />
							一本好书从构思到上架，需要呕心沥血，几番增删，有人泛泛浏览，束之高阁，仅用了一个小时；
							<br />
							时光如水般流逝，越想要珍惜，就越是匆忙；
							<br />
							但逸族人生命的乐趣，却不在于经历多少，而在于用心享受多少；
							<br />
							他们愿意从头到尾欣赏花的美，细品茶之甘，慢享书之韵；
							<br />
							这不仅是爱好，也是能力；
							<br />
							逸族，慢得有趣，闲得有品。
							<br />
						</p><br/>
					</div>
					<div class="a_title">
							逸族释义
					</div>
					<br /><br />
					<div class="r_about">
					<p>
					逸族，指生活忙碌但内心淡定、外表温和而自我意识强烈、追求格调与享受、崇尚 自由和健康的一类人。<br />
					逸族人拥有品位高雅的闲情逸致、中等偏上的社会地位、身居闹市的隐遁之心、放任自由的心灵所向。
					<p>
					</div>
					<br /><br />
					<div class="clearfloat"></div>
					<div id="featureContainer">
						<div id="feature">
							<div id="block">
								<div id="botton-scroll">
									<ul class="featureUL">
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"> <img width="132"
														height="132" src="images/img_05.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_06.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_07.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_08.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_05.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_06.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_07.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 144px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_08.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 150px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_05.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 150px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_06.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 150px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"><img width="132"
														height="132" src="images/img_07.jpg" /> </a>
											</div>
										</li>
										<li class="featureBox"
											style="overflow: hidden; float: left; width: 150px; height: 144px;">
											<div class="box">
												<a target="_blank" href="#"> <img width="132"
														height="132" src="images/img_08.jpg" /> </a>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<a class="prev" href="javascript:void();">Previous</a>
							<a class="next" href="javascript:void();">Next</a>
						</div>
					</div>

				</div>
				<div class="details_cont">
					<div class="r_about">
						<div class="a_title">
							"逸族"一词的来源
						</div>
						<p>
							资深媒体人李勇，于2006年下海创办中国卓越传媒集团，至今5年有余。从白手起家到规模壮大，他承受了很多身心压力，但源自内心的兴趣指引，使他坚持下来并从中获得了收获的乐趣。同时他发现，周围有很多像自己一样的人。他们具备殷实的经济基础和中上等社会地位，多属于企业中高层管理者，被外界称为白领甚至金领精英。在很多人看来，他们整日为俗务所累，但在他们内心，永远有着一块绿洲，爱着的某个人或事物，会让他们随时浸润其中，净化身心，怡然自得。那也许是一个私密的小空间，但就是这点让他们看起来与众不同——忙碌而充满喜悦，享受每一秒的当下，在飞逝的时间面前，欢喜着一颗悠然闲暇的心，他们是逸族。
							<br />
							<br />
							<br />
							<br />
							<br />
						</p>
					</div>
				</div>
			</div>
			<div style="float:right; width: 757px; display: none;" id="aboutYizus">
				<div class="details_cont">
					<div class="r_about">
						<div class="a_title">
							关于逸族网
						</div>
						<p>逸族网（1mily.com）隶属于北京卓越盈动网络科技有限公司，首推社区化电子商务模式。网站风格简约唯美，内容清新时尚，其中，“第二人生”社区根据喜好聚集逸族人群，会员在不同圈子中深度讨论发掘自身好恶；同时，围绕讨论话题，逸族商城集结国内一线品牌之精华， 销售符合逸族人欣赏水平的家居、工作、健康、美丽、文化、休闲、逸组合七大类商品。</p>
						<p>逸族网，交友、购物、参与、分享，一站式拓展。从这里，正式开启你的第二人生！</p><br/>
						<div class="a_title">
							网站构成
						</div>
						<p>逸族网首创社区化电子商务模式，专为注重品质、追求安逸的逸族人打造，倡导自然健康的生活方式，由逸族商城和第二人生社区两大部分组成。</p>
						<p>逸族人在第二人生社区中阐释生活理念，分享兴趣爱好，以同好交友的方式扩展社交网络，并可一起参加逸族网主办或参与主办的线上及线下文化沙龙、时尚派对。</p>
						<p>逸族商城将充分吸纳第二人生社区中的会员讨论，通过投票等方式民主决定未来上线销售的商品结构、数量、价格等等，打造24小时完美购物体验，建立高品质购物环境。</p><br/>
						<div class="a_title">
							网站会员
						</div>
						<p>
						逸族网会员有着相近的社会背景、价值取向、生活理念、兴趣爱好，其权利如下：
						<br/>1.可以在第二人生社区中扩展人脉、参与讨论、发起圈子、参加线上线下活动；
						<br/>2.可以在逸族网及其他微博、论坛中分享逸族网购物体验；
						<br/>3.可以推荐适合逸族人使用的品质商品，经由会员投票通过在逸族商城中销售，获得“买手”荣誉；
						<br/>4.可以根据自己在逸族网的人气和活跃程度，升级会员等级，获取购物折扣。<br/>
						</p><br/>
						<div class="a_title">
							逸族网特点
						</div>
						<p>
						1.模式新颖，全国首推社交化电子商务的商业模式；
						<br/>2.话题导向，发起“逸族”定义，高强度客户黏性；
						<br/>3.消费者第一，打造24小时完美购物体验；
						<br/>4.一线品牌之精华，品质保障，专属渠道。<br/>
						</p><br/>
						<div class="a_title">
							逸族网产品特点
						</div>
						<p>
						1.一线品牌之精华，令人想起国王身边最小的王子或公主；
						<br/>2.产品具有令人眼前一亮的独到之处，有可“晒”的亮点；
						<br/>3.产品具有强烈的设计感，风格简约明朗，与逸族风格接近；
						<br/>4.使用健康、环保材料制成，品质高端有保障。</p>
					</div>
				</div>
			</div>
			<div style="float:right; width: 757px; display: none;" id="aboutVote">
				<div class="details_cont">
					<div class="r_about">
						<div class="a_title">
							关于投票
						</div>
						<p>
							当您发现新奇、有创意的商品时，有没有想过与更多的朋友分享你的欣喜，晒出您的发现？逸族网提供给了这样一个投票平台，您随手拍下的商品照片上传后，将有海量的会员参与您发布的投票，当支持者超过一定比率时，我们会将您提供的产品通过与正规供应渠道洽谈，最终投放到逸族商城，成为上架商品进行销售，您也将会的得到逸族网送出的丰厚奖励。
						</p>
						<br/>
						<div class="a_title">
							投票流程
						</div>
						<p>
							1、创建投票
							<br />
							点击首页"投票"，进入投票页面；点击"创建投票"，进入填写信息页面，如下图，按照信息提示填写完整后，点击"创建"即可。
						</p>
						<div class="about_1">
							<img src="images/about_1.png" />
						</div>
						<p>
							2、待审核的投票
							<br />
							投票上传后，进入待审核状态，由网站后台负责审核，审核通过后会直接进入"即将开始的投票"。
							<br />
							3、即将开始的投票
							<br />
							通过审核而还未到"开始时间"的投票将会在这里呈现。
							<br />
							4、进行中的投票
							<br />
							依据"开始时间"的设定，会在当日零时正式开始计时投票，会员可以根据自己的意愿进行投票,还可以写下对产品意见和建议的评论。
							<br />
						</p>
						<div class="about_1">
							<img src="images/about_2.png" />
						</div>
						<p>
							5、结束的投票
							<br />
							"结束时间"为实际日期午夜零时，结束后的投票将会在这里呈现。
							<br />
							6、有效投票
							<br />
							根据实际投票结果，后台将会自动筛选出投"想买"票数占参与投票会员总数比率在80%（含80%）以上的投票为有效投票，此类投票将会被产品运营部接收，成为商城备选产品。
							<br />
							7、投票产品转化为商城上架产品
							<br />
							产品运营部根据"有效投票"的产品，通过商城产品渠道，依据"价格范围"促成渠道合作，把该产品转化为商城的上架产品，实现线上销售。
							<br />
							8、奖励办法
							<br />
							"有效投票"的发起者将会得到逸族商城送出的会员积分1000分。
							<br />
						</p>
					</div>
				</div>
			</div>

			<div style="float:right; width: 757px; display: none;" id="aboutBan">
				<div class="details_cont">
					<div class="r_about">
						<div class="a_title">
							版权声明
						</div>
						<p>
1、逸族网所有内容，凡注明"来源：逸族网"的所有文字、图片和音视频资料，版权均属逸族网所有，任何媒体、网站或个人未经本网协议授权不得转载、链接、转贴或以其他方式复制发布/发表。已经逸族网协议授权的媒体、网站，在下载使用时必须注明"稿件来源：逸族网"，违者逸族网将依法追究责任。 
<br /><br />凡逸族网注明"来源：XXX "的文/图等稿件逸族网转载出于传递更多信息之目的，并不意味着赞同其观点或证实其内容的真实性。
<br /><br />2、除注明"来源：逸族网"的内容外，逸族网以下内容亦不可任意转载：　　
<br /> a. 逸族网所指向的非逸族网内容的相关链接内容； 　　 
<br />b.已作出不得转载或未经许可不得转载声明的内容； 　　 
<br />c.未由逸族网署名或逸族网引用、转载的他人作品等非逸族网版权内容； 　　
<br /> d. 逸族网中特有的图形、标志、页面风格、编排方式、程序等； 　　
 <br />e逸族网中必须具有特别授权或具有注册用户资格方可知晓的内容； 　　
 <br />f.其他法律不允许或逸族网认为不适合转载的内容。 
<br /><br />3、转载或引用逸族网内容必须是以新闻性或资料性公共免费信息为使用目的的合理、善意引用，不得对逸族网内容原意进行曲解、修改，同时必须保留逸族网注明的"稿件来源"，并自负版权等法律责任。
<br /><br />4、转载或引用逸族网内容不得进行如下活动：　　
<br /> a. 损害逸族网或他人利益；　　
<br /> b. 任何违法行为； 　　
<br /> c. 任何可能破坏公秩良俗的行为； 　　
 <br />d. 擅自同意他人继续转载、引用逸族网内容； 
<br /><br />5、转载或引用逸族网版权所有之内容须注明“转自（或引自）逸族网”字样，并标明逸族网网址www.1mily.com。
<br /><br />6、转载或引用逸族网中的署名文章，请按规定向作者支付稿酬。
<br /><br />7、对于不当转载或引用逸族网内容而引起的民事纷争、行政处理或其他损失，逸族网不承担责任。
<br /><br />8、对不遵守本声明或其他违法、恶意使用本网内容者，逸族网保留追究其法律责任的权利。
</p>
					</div>
				</div>
			</div>

			<div style="float:right; width: 757px; display: none;" id="aboutSer">
				<div class="details_cont">
					<div class="r_about">
						<div class="a_title">
							服务协议
						</div>
						<p>
							1.	接受条款<br />

逸族网根据以下服务条款为您提供服务。这些条款可由逸族网随时更新，且毋须另行通知。逸族网使用协议（以下简称“使用协议”）一旦发生变动，逸族网将在网页上公布修改内容。修改后的使用协议一旦在网页上公布即有效代替原来的使用协议。此外，当您使用逸族网特殊服务时，您和逸族网应遵守逸族网随时公布的与该服务相关的指引和规则。前述所有的指引和规则，均构成本使用协议的一部分。

<br /><br />您在使用逸族网提供的各项服务之前，应仔细阅读本使用协议。如您不同意本使用协议及/或随时对其的修改，请您立即停止使用逸族网所提供的全部服务；您一旦使用逸族网服务，即视为您已了解并完全同意本使用协议各项内容，包括逸族网对使用协议随时所做的任何修改，并成为逸族网用户（以下简称“用户”）。
	<br /><br />
2.	服务说明<br />

逸族网目前向用户提供如下服务：发布并分享兴趣、爱好、购物体验；参与发布和评论产品投票；在逸族网中创建圈子、发布消息、话题、上传图片；在逸族网商城网购。除非本使用协议另有其它明示规定，增加或强化目前本服务的任何新功能，包括所推出的新产品，均受到本使用协议之规范。您了解并同意，本服务仅依其当前所呈现的状况提供，对于任何用户信息或个人化设定之时效、删除、传递错误、未予储存或其它任何问题，逸族网均不承担任何责任。逸族网保留不经事先通知为维修保养、升级或其它目的暂停本服务任何部分的权利。
	<br /><br />
3.	遵守法律<br />

您同意遵守中华人民共和国相关法律法规的所有规定，并对以任何方式使用您的密码和您的帐号使用本服务的任何行为及其结果承担全部责任。如您的行为违反国家法律和法规的任何规定，有可能构成犯罪的，将被追究刑事责任，并由您承担全部法律责任。

<br />同时如果逸族网有理由认为您的任何行为，包括但不限于您的任何言论和其它行为违反或可能违反国家法律和法规的任何规定，逸族网可在任何时候不经任何事先通知终止向您提供服务。
	<br /><br />
4.	您的注册义务
<br />为了能使用本服务，您同意以下事项：依本服务注册提示请您填写正确的注册邮箱、密码和昵称，并确保今后更新的登录邮箱、昵称、头像等资料的有效性和合法性。若您提供任何违法、不道德或逸族网认为不适合在逸族网上展示的资料；或者逸族网有理由怀疑你的资料属于程序或恶意操作，逸族网有权暂停或终止您的帐号，并拒绝您于现在和未来使用本服务之全部或任何部分。

<br />逸族网无须对任何用户的任何登记资料承担任何责任，包括但不限于鉴别、核实任何登记资料的真实性、正确性、完整性、适用性及/或是否为最新资料的责任。
<br /><br />
5.  用户帐号、密码及安全
<br />完成本服务的注册程序并成功注册之后，您可使用您的Email和密码，登录到您在逸族网的帐号（下称“帐号”）。保护您的帐号安全，是您的责任。

<br />您应对所有使用您的密码及帐号的活动负完全的责任。您同意：
<br />
1).	您的逸族网帐号遭到未获授权的使用，或者发生其它任何安全问题时，您将立即通知逸族网；
<br />
2).	如果您未保管好自己的帐号和密码，因此而产生的任何损失或损害，逸族网无法也不承担任何责任；
<br />
3).	每个用户都要对其帐号中的所有行为和事件负全责。如果您未保管好自己的帐号和密码而对您、逸族网或第三方造成的损害，您将负全部责任。
	<br />
	
6.	提供者的责任
<br />根据有关法律法规，逸族网在此郑重提请您注意，任何经由本服务而发布、上传的文字、资讯、资料、音乐、照片、图形、视讯、信息或其它资料（以下简称“内容”），无论系公开还是私下传送，均由内容提供者承担责任。逸族网仅为用户提供内容存储空间，无法控制经由本服务传送之内容，因此不保证内容的正确性、完整性或品质。您已预知使用本服务时，可能会接触到令人不快、不适当或令人厌恶之内容。在任何情况下，逸族网均不为任何内容负责，但逸族网有权依法停止传输任何前述内容并采取相应行动，包括但不限于暂停用户使用本服务的全部或部分，保存有关记录，并向有关机关报告。
	<br /><br />
7.	用户行为
<br />
用户同意将不会利用本服务进行任何违法或不正当的活动，包括但不限于下列行为∶
<br />
1).	发布或以其它方式传送含有下列内容之一的信息：<br />
　　　* 反对宪法所确定的基本原则的；<br />
　　　* 危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；<br />
　　　* 损害国家荣誉和利益的；<br />
　　　* 煽动民族仇恨、民族歧视、破坏民族团结的；<br />
　　　* 破坏国家宗教政策，宣扬邪教和封建迷信的；<br />
　　　* 散布谣言，扰乱社会秩序，破坏社会稳定的；<br />
　　　* 散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；<br />
　　　* 侮辱或者诽谤他人，侵害他人合法权利的；<br />
　　　* 含有虚假、诈骗、有害、胁迫、侵害他人隐私、骚扰、侵害、中伤、粗俗、猥亵、或其它道德上令人反感的内容；<br />
　　　* 含有中国法律、法规、规章、条例以及任何具有法律效力之规范所限制或禁止的其它内容的；<br />
　　　* 含有逸族网认为不适合在逸族网展示的内容；<br />
<br />
2).	以任何方式危害他人的合法权益；<br />
<br />
3).	冒充其他任何人或机构，或以虚伪不实的方式陈述或谎称与任何人或机构有关；<br />
<br />
4).	依据任何法律或合约或法定关系（例如由于雇佣关系和依据保密合约所得知或揭露之内部资料、专属及机密资料）知悉但无权传送之任何内容加以发布、发送电子邮件或以其它方式传送；
<br /><br />
5).	将侵害他人著作权、专利权、商标权、商业秘密、或其它专属权利（以下简称“专属权利”）之内容加以发布或以其它方式传送；
<br /><br />
6).	将任何广告信函、促销资料、“垃圾邮件”、““滥发信件”、“连锁信件”、“直销”或其它任何形式的劝诱资料加以发布、发送或以其它方式传送；
<br /><br />
7).	将设计目的在于干扰、破坏或限制任何计算机软件、硬件或通讯设备功能之计算机病毒（包括但不限于木马程序（trojan horses）、蠕虫（worms）、定时炸弹、删除蝇（cancelbots）（以下简称“病毒”）或其它计算机代码、档案和程序之任何资料，加以发布、发送或以其它方式传送；
<br /><br />
8).	干扰或破坏本服务或与本服务相连线之服务器和网络，或违反任何关于本服务连线网络之规定、程序、政策或规范；
<br /><br />
9).	跟踪、人肉搜索或以其它方式骚扰他人；
<br /><br />
10).	故意或非故意地违反任何适用的当地、国家法律，以及任何具有法律效力的规则；
<br /><br />
11).	未经合法授权而截获、篡改、收集、储存或删除他人个人信息、站内邮件或其它数据资料，或将获知的此类资料用于任何非法或不正当目的。
<br /><br />
您已认可逸族网未对用户的使用行为进行全面控制，您使用任何内容时，包括依赖前述内容之正确性、完整性或实用性时，您同意将自行加以判断并承担所有风险，而不依赖于逸族网。但逸族网依其自行之考虑，拒绝和删除可经由本服务提供之违反本条款的或其它引起逸族网反感的任何内容。
<br /><br />
您了解并同意，逸族网依据法律法规的要求，或基于诚信为了以下目的或在合理必要范围内，认定必须将内容加以保存或揭露时，得加以保存或揭露：
<br /><br />
a）遵守法律程序；<br />
b）执行本使用协议；<br />
c）回应任何第三人提出的权利主张；<br />
d）保护逸族网、其用户及公众之权利、财产或个人安全；<br />
e）其它逸族网认为有必要的情况。<br />
	<br />
9.	国际使用的特别警告<br />
您已了解国际互联网的无国界性，同意遵守当地所有关于网上行为及内容之法律法规。您特别同意遵守有关从中国或您所在国家或地区输出信息之传输的所有适用法律法规。
	<br /><br />
10.	在逸族网发布的公开信息<br />
1).	在本使用协议中，“本服务公开使用区域”系指一般公众可以使用的区域；
<br /><br />
2).	用户在逸族网上传或发布的内容，用户应保证其为著作权人或已取得合法授权，并且该内容不会侵犯任何第三方的合法权益，用户同意授予逸族网所有上述内容在全球范围内的免费、不可撤销的、永久的、可再许可或转让的独家使用权许可，据该许可逸族网将有权以展示、推广及其他不为我法律所禁止的方式使用前述内容。
<br /><br />
11.	免责声明
<br />
1).	用户明确同意其将文字、资讯、资料、音乐、照片、图形、视讯、信息或其它个人资料上传到互联网上，有可能被其他组织或个人复制、转载、或做其它用途的风险完全由其自己承担；因其使用逸族网服务而产生的一切后果也由其自己承担，我们对用户不承担任何责任。
<br /><br />
2).	微博、人人网等官方帐号对用户的相关文字、资讯、资料、音乐、照片、图形、视讯、信息或其它个人资料进行推广发布的，不属于对用户相关权益的侵权行为、逸族网不承担任何责任。
<br /><br />
3).	网站上针对用户的恶意评论，经用户的举报我们会采取删除处理，但不保证服务一定能满足用户的要求，也不对该恶意评论负任何法律责任。
	<br /><br />
12.	赔偿
<br />
由于您通过本服务提供、发布或传送之内容、您与本服务连线、您违反本使用协议、或您侵害他人任何权利因而衍生或导致任何第三人提出任何索赔或请求，包括合理的律师费，您同意赔偿逸族网及其子公司、关联企业、高级职员、代理人、品牌共有人或其它合作伙伴及员工，并使其免受损害，并承担由此引发的全部法律责任。
	<br /><br />
13.	禁止商业行为
<br />
您同意不对本服务任何部分或本服务之使用或获得，进行复制、拷贝、出售、转售或用于任何其它商业目的。
	<br /><br />
14.	关于使用及储存之一般措施
<br />
您承认关于本服务的使用逸族网有权制订一般措施及限制，包含但不限于本服务将保留所发布内容或其它发布内容之最长期间，以及一定期间内您使用本服务之次数上限（及每次使用时间之上限）。通过本服务发布或传送之任何信息、通讯资料和其它内容，如被删除或未予储存，您同意蘑菇街毋须承担任何责任。您也同意，逸族网有权依其自行之考虑，不论通知与否，随时变更这些一般措施及限制。
	<br /><br />
15.	服务的修改
<br />
逸族网有权于任何时间暂时或永久修改或终止本服务（或其任何部分），且无论通知与否。您同意对于本服务所作的任何修改、暂停或终止，蘑菇街对您和任何第三人均无需承担任何责任。
	<br /><br />
16.	终止服务
<br />
您同意逸族网基于其自行之考虑，因任何理由，包含但不限于缺乏使用，或逸族网认为您已经违反本使用协议的文字及精神，终止您的帐号或本服务之使用（或服务之任何部分），并将您在本服务内任何内容加以移除并删除。您同意依本使用协议任何规定提供之本服务，无需进行事先通知即可中断或终止，您承认并同意，逸族网可立即关闭或删除您的帐号及您帐号中所有相关信息及文件，及/或禁止继续使用前述文件或本服务。此外，您同意若本服务之使用被中断或终止或您的帐号及相关信息和文件被关闭或删除，逸族网对您或任何第三人均不承担任何责任。
	<br /><br />
17.	与广告商及其他第三方进行交易
<br />
您通过本网站与广告商及其他第三方进行任何形式的通讯或商业往来，或参与促销活动，包含相关商品或服务之付款及交付，以及达成的其它任何相关条款、条件、保证或声明，完全为您与广告商及其他第三方之间之行为。您因前述任何交易或前述广告商及其他第三方而遭受的任何性质的损失或损害，逸族网对此不负任何法律责任。
	<br /><br />
18.	逸族网的专属权利
<br />
您了解并同意，本服务及本服务所使用之相关软件（以下简称“软件”）含有受到相关知识产权及其它法律保护之专有保密资料。您也了解并同意，经由本服务或广告商向您呈现之赞助广告或信息所包含之内容，亦受到著作权、商标权、服务商标、专利权或其它专属权利之法律保护。未经逸族网或广告商明示授权，您不得修改、出租、出借、出售、散布本服务或软件之任何部份或全部，或据以制作衍生著作，或使用擅自修改后的软件，包括但不限于为了未经授权而使用本服务之目的。蘑菇街仅授予您个人、不可移转及非专属之使用权，使您得于单机计算机使用其软件之目的码，但您不得（并不得允许任何第三人）复制、修改、创作衍生著作、进行还原工程、反向组译，或以其它方式发现原始码，或出售、转让、再授权或提供软件设定担保，或以其它方式移转软件之任何权利。您同意将通过由逸族网所提供的界面而非任何其它途径使用本服务。
	<br /><br />
19.	担保与保证
<br />
您明确了解并同意∶
<br /><br />
1).	本使用协议的任何规定不会免除逸族网对造成您人身伤害的、或因故意或重大过失造成您财产损失的任何责任；
<br /><br />
2).	您使用本服务之风险由您个人负担。本服务系依“现状”及“现有”基础提供。逸族网对本服务不提供任何明示或默示的担保或保证，包含但不限于商业适售性、特定目的之适用性及未侵害他人权利等担保或保证；
<br /><br />
3).	逸族网不保证以下事项∶<br />
　　　* 本服务将符合您的要求；<br />
　　　* 本服务将不受干扰、及时提供、安全可靠或不会出错；<br />
　　　* 使用本服务取得之结果正确可靠；<br />
　　　* 您经由本服务购买或取得之任何产品、服务、资讯或其它信息将符合您的期望；<br />
<br /><br />
4).	是否使用本服务下载或取得任何资料应由您自行考虑且自负风险，因任何资料之下载而导致的您电脑系统之任何损坏或数据流失等后果，由您自行承担；
<br /><br />
5).	您自逸族网或经由本服务取得的任何建议或信息，无论是书面或口头形式，除非本使用协议有明确规定，将不构成本使用协议以外之任何保证。
<br /><br />
6).	如果逸族网和/或合作单位使用您提供的肖像、姓名或其他合法权益，您同意将您的肖像、姓名和/或其他合法权益一并授权给逸族网和/或合作单位使用。
	<br /><br />
20.	责任限制<br /><br />
<br />
您明确了解并同意，基于以下原因而造成的，包括但不限于利润、信誉、应用、数据损失或其它无形损失，逸族网不承担任何直接、间接、附带、特别、衍生性或惩罚性赔偿责任：
<br /><br />
1).	本服务之使用或无法使用；<br />
2).	为替换从或通过本服务购买或取得之任何商品、数据、信息、服务，收到的讯息，或缔结之交易而发生的成本；<br />
3).	您的传输或数据遭到未获授权的存取或变造；<br />
4).	任何第三方在本服务中所作之声明或行为；<br />
5).	与本服务相关的其它事宜，但本使用协议有明确规定的除外；<br />
6).	第三方以任何方式发布或投递欺诈性信息，或诱导用户受到经济损失，逸族网不承担任何责任。
	<br /><br />
21.	逸族网商标信息<br />
逸族网、逸族以及其它逸族网注册商标、标志及产品、服务名称，均为逸族网公司之商标（以下简称“逸族网标记”）。未经逸族网事先书面同意，您同意不将逸族网标记以任何方式展示或使用或作其它处理，或表示您有权展示、使用或另行处理逸族网标记。
	<br /><br />
22.	用户专属权利
<br />
逸族网尊重他人知识产权，呼吁用户也要同样尊重知识产权。
<br /><br />
逸族网之服务与资料是基于“现状”提供，而且逸族网明确地表示拒绝对于“服务”、“资料”或“产品”给予任何明示或暗示之保证，包括但不限于，得为商业使用或适合于特定目的之保证。逸族网对于因“服务”、“资料”或“产品”所生之任何直接、间接、附带的或因此而导致之衍生性损失概不负责。
<br /><br />
如果您对他人的知识产权造成了侵害，逸族网将依国家法律法规的规定，或在适当的情形下，将依其服务条款或其相关规范性规定，删除特定内容或以至终止您对账户的使用。
<br /><br />
逸族网尊重他人的任何权利（包括知识产权），同时也要求我们的使用者也尊重他人之权利。逸族网在适当情况下，得自行决定终止侵害或违反他人权利之使用者的帐号。
<br /><br />
若您认为您的作品的著作权遭到侵害或您的知识产权被侵犯，根据《信息网络传播权保护条例》的规定，您需及时向逸族网联系并提供详实的举证材料。或请到中华人民共和国国家版权局下载《要求删除或断开链接侵权网络内容的通知》（下称“删除通知”）的示范格式，如果您不明白“删除通知”的内容，请登录中华人民共和国国家版权局查看《要求删除或断开链接侵权网络内容的通知》填写说明。
	<br /><br />
23.	一般条款
<br />
1).	本使用协议、社区指导原则和免责声明构成您与逸族网之全部协议，并规范您对于本服务之使用行为。在您使用相关服务、使用第三方提供的内容或软件时，亦应遵从所适用之附加条款及条件；
<br /><br />
2).	本使用协议及您与逸族网之关系，均受到中华人民共和国法律管辖。您与逸族网就本服务、本使用协议或其它有关事项发生的争议，应首先友好协商解决，协商不成时应提请中国国际经济贸易仲裁委员会仲裁，仲裁裁决是终局性的，对双方均有约束力；
<br /><br />
3).	逸族网未行使或执行本使用协议任何权利或规定，不构成对前述权利或权利之放弃；
<br /><br />
4).	倘本使用协议之任何规定因与中华人民共和国法律抵触而无效，您依然同意应依照法律，努力使该规定所反映之当事人意向具备效力，且本使用协议其它规定仍应具有完整的效力及效果；
<br /><br />
5).	本使用协议之标题仅供方便而设，不具任何法律或契约效果；
	<br /><br />
6).	只要您登录了逸族网，就代表您认可以上所有协议。
<br /><br />
7).	逸族网对本使用协议享有最终解释权。
						</p>
					</div>
				</div>
			</div>
			
			<div style=" float:right; width: 757px;  display: none;" id="contactUs">
      			<div class="details_cont">
            		<div class="r_about">
               			<p style="font-size:16px; line-height:25px;" >
               				<b> 北京卓越盈动网络科技有限公司</b><br /><br />
						  	<b> 地址：</b>北京市海淀区知春路108号豪景大厦A座401<br /><br />
							<b> 邮编：</b>100080<br /><br />
							<b>电话：</b>010-62106012<br /><br />
							<b>传真：</b>010-62106012<br /><br />
							<b>Email：</b>zhuoycm@126.com<br /><br /><br /><br />
            			</p>
              			<p><img src="images/about_map.jpg" /><br /><br /><br /><br /><br /><br /><br /><br /></p>
           			</div>
     		 	</div>
			</div>
			<div style=" float:right; width: 757px; display: none;" id="link">
				<div class="details_cont">
            		<div class="r_about">
            			<div class="r_lianjie">友情连接:</div>
             				<ul class="r_youqing">
								<li><a href="http://www.baidu.com" target="_blank" >百度</a></li>
        						<li><a href="http://www.sina.com.cn" target="_blank">新浪</a></li>
        						<li><a href="http://www.sohu.com" target="_blank">搜狐</a></li>
        						<li><a href="http://www.qq.com" target="_blank">腾讯</a></li>
        						<li><a href="http://www.hexun.com" target="_blank">和讯</a></li>
        						<li><a href="http://www.163.com">网易</a></li>
        						<li><a target="_blank" href="http://www.nvc-lighting.com.cn">雷士照明</a></li>
       							<li><a target="_blank" href="http://www.joyoung.com">九阳</a></li>
        						<li><a target="_blank" href="http://www.xiansiniao.com.cn" >纤丝鸟 </a></li>
  								<li><a target="_blank" href="http://www.jomoo.com.cn" >九牧厨卫 </a></li>
        						<li><a target="_blank" href="http://www.vatsliquor.com" >华致酒行 </a></li>
        						<li><a target="_blank" href="http://www.china-xiuzheng.com">修正药业</a></li>
        						<li><a href="http://www.eastmoney.com" target="_blank" >东方财富网 </a></li>
       							<li><a href="http://www.people.com.cn" target="_blank">人民网</a></li>
        						<li><a href="http://www.xinhuanet.com/fortune" target="_blank">新华财经频道</a></li>
        						<li><a href="http://www.4a6.net" target="_blank">爱壳网</a></li>
        						<li><a href="http://www.beijingcx.com" target="_blank">北京城乡论坛</a></li>
        						<li><a href="http://www.loveabc.net/" target="_blank">恋爱学院</a></li>
        						<li><a href="http://www.xincity.com.cn/" target="_blank">新城在线</a></li>
        						<li><a href="http://bbs.m51.org/" target="_blank">北京驴友论坛</a></li>
        						<li><a href="http://www.tjhjyg.com/" target="_blank">梭哈游戏</a></li>
        						<li><a href="http://changpai.fsjoy.com/" target="_blank">南通长牌游戏下载</a></li>
        						<li><a href="http://dzpk.fsjoy.com" target="_blank">德州扑克</a></li>
        						<li><a href="http://www.uuavdyw.com/index.html" target="_blank">优优</a></li>
        						<li><a href="http://www.jiuyinj.com/" target="_blank">久亿集群网</a></li>
        						<li><a href="http://www.xoyin.com/" target="_blank">旅行定制平台</a></li>
                			</ul>
               			</div>
     		 		</div>
				</div>
			</div>
	</div>
</div>

</body>
</html>
