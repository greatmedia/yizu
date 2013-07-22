function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
var gz;
var love;
var notlove;
var buy;
var voteid,index,islogin,ids;
//voteid = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
voteid = realurl.substring(idindexof + 1,realurl.search(".html"));
ids = voteid;
getvote();
var now=new Date();
var gz,love,notLove,buy;//类型
var days,hours,minutes;
var date1 = "";
var date2 = "";
var nowToStart;
var startToEnd;
var d1;
var d2;
function getvote() {
	var voteURL = "myVoteInfoAction_getVote.do";
	$.ajax({
		url : voteURL,
		data : {id : voteid },
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			islogin = data.data.islogin;
			var vote = data.data.myVoteInfo;
			voteid = vote.voteId;
			$("#title").html(vote.title);
			$("#summarys").html(vote.summary);
			var price=""+vote.price+"-"+vote.pricess+"";
			$("#price").html(price);
			$("#time").html(vote.starttime);
			$("#tag").html(vote.voteTag);
			gz =vote.gzcount;
			love = vote.love;
			notlove = vote.notlove;
			buy = vote.buy;
			$("#one").html("" + gz + "");
			$("#two").html("" + love + "");
			$("#three").html("" + notlove + "");
			$("#four").html("" + buy + "");
			$("#userNick").html("<a target=\"_blank\" href='user/"+vote.userinfo.userid+".html'>"+vote.userinfo.nick+"</a>");
			var vote_user_head = vote.userinfo.image;
			if(vote_user_head.length<2)
			{
				vote_user_head = vote.userinfo.otheraccountuserimage;
			}
			$("#userHead").html("<a target=\"_blank\" href='user/"+vote.userinfo.userid+".html'><img src=\""+vote_user_head+"\" width=\"73px\" height=\"73px\"/></a>");
			
			document.title = vote.title;
//			getImg(vote.voteId);
			$("#banner_list").html("");
			$("#imgNum").html("");
			var voteimgs = data.data.myVoteInfoImages;

			for ( var i = 0; i < voteimgs.length; i++) {
				$("#banner_list").append("<a href=\"javascript:void(0);\"><img src=\""+ voteimgs[i].imgAddress+ "\" /></a>");
			}
			for ( var j = 1; j <= voteimgs.length; j++) {
				$("#imgNum").append("<li>"+ j + "</li>");
			}
			show();
			
			date2 = vote.endtime;
			date1 = vote.starttime;
//			countTopic(vote.userinfo.userid);
			var userid = vote.userinfo.userid;
			var countTopic = data.data.countTopic;
			var countVote = data.data.countVote;
			$("#countTopic").html("<a target=\"_blank\" href='user/"+userid+".html'>"+countTopic+"</a>");
			$("#countVote").html("<a target=\"_blank\" href='user/"+userid+".html'>"+countVote+"</a>");
			
			getDate();
			getCommentList(5,1,1);
		},
		error : function(data) {
		}
	});
}
//获取相差的时间
function getDate()
{
	date2 = date2.replace("年","/").replace("月","/").replace("日","");//转换成时间类型
	date1 = date1.replace("年","/").replace("月","/").replace("日","");   //结束时间
	d1=Date.parse(date1.replace(/-/g, "/"));//开始时间
	d2=Date.parse(date2.replace(/-/g, "/"));//结束时间
	
	nowToStart=d1-now;
	startToEnd=d2-d1;
	if(nowToStart>=0){
		var scha=new Date(nowToStart);//投票时间与当前时间的差值
		//计算出相差天数
		days=Math.floor(scha/(24*3600*1000));
		//计算出小时数
		var leave1=scha%(24*3600*1000);    //计算天数后剩余的毫秒数
		hours=Math.floor(leave1/(3600*1000));
		//计算相差分钟数
		var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
		minutes=Math.floor(leave2/(60*1000));
		if(days>=0 && hours>=0 && minutes>=0){
			$("#show").html("<h2>投票将于"+date1+"开始\n敬请关注！</h2>").show();
			$("#votetime").css("text-align","center").html("<h3>投票尚未开始</h3>");
		}
	}else{
		//投票开始
		d1=new Date();
		startToEnd=d2-d1;
		$("#show").css("display","none");
		if(startToEnd>0){
			getTimeImg(startToEnd);
		}else{
			$("#votetime").css("text-align","center").html("<h3>投票已结束</h3>");
		}
	}
}
function getTimeImg(startToEnd){
	
	d1=new Date();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ;
	startToEnd=d2-d1;
	if(startToEnd<0){
		$("#votetime").css("text-align","center").html("<h3>投票已结束</h3>");
		return;
	}
	var date3=new Date(startToEnd);
	//计算出相差天数
	days=Math.floor(date3/(24*3600*1000));
	//计算出小时数
	var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
	hours=Math.floor(leave1/(3600*1000));
	//计算相差分钟数
	var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
	minutes=Math.floor(leave2/(60*1000));
	//计算相差秒数
	//	var leave3=leave2%(60*1000);      //计算分钟数后剩余的毫秒数
	//	var seconds=Math.round(leave3/1000);
	//确定天数的对应图片
	if(days>=100){$("#day").html("<img src='images/09.png'/><img src='images/09.png'/>");}
	for(var n=0;n<100;n++){
		if(days==n){
			if(days<10){
				$("#day").html("<img src='images/"+days+".png'/>");
			}else{
				var s=Math.floor(days/10);
				var g=days%10;
				$("#day").html("<img src='images/"+s+".png'/><img src='images/"+g+".png'/>");
			}
		}
	}
	
//	hours=20;确定时间对应的图片
	for(var i=1;i<24;i++){
		if(hours==0 || hours==00)
		{
			$("#hour").html("<img src='images/00.png'/><img src='images/00.png'/>");
		}
		if(hours==i){
			if(hours<10)
			{
				$("#hour").html("<img src='images/00.png'/><img src='images/0"+hours+".png'/>");
			}else{
				var s=Math.floor(hours/10);
				var g=hours%10;
				$("#hour").html("<img src='images/0"+s+".png'/><img src='images/0"+g+".png'/>");
			}
		}
	}
	
	//确定分钟所对应的图片
	for(var j=0;j<60;j++){
		if(minutes==j){
			if(minutes<10){
				$("#minute").html("<img src='images/00.png'/><img src='images/0"+minutes+".png'/>");
			}else{
				var s=Math.floor(minutes/10);
				var g=minutes%10;
				$("#minute").html("<img src='images/0"+s+".png'/><img src='images/0"+g+".png'/>");
			}
		}
	}
	setTimeout("getTimeImg(startToEnd)",5000);
}
// 得到图片信息
function getImg(voids) {
	var voteImgURL = "myVoteInfoAction_getVoteImg.do";
	$.ajax({
		url : voteImgURL,
		data : {id : voids},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$("#banner_list").html("");
			$("#imgNum").html("");
			var voteimgs = data.data.myVoteInfoImages;

			for ( var i = 0; i < voteimgs.length; i++) {
				$("#banner_list").append("<a href=\"javascript:void(0);\"><img src=\""+ voteimgs[i].imgAddress+ "\" /></a>");
			}
			for ( var j = 1; j <= voteimgs.length; j++) {
				$("#imgNum").append("<li>"+ j + "</li>");
			}
			
			show();
		},
		error : function(data) {
			$("#banner_list").html("");
			$("#imgNum").html("");
		}
	});
}


// 投票
function vote(num) {
	if (islogin == 'not') {
		alert("您还没有登录，登录之后才能投票..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	if(nowToStart>=0)
	{
		alert("投票尚未开始..");
		return false;
	}
	if((d2-now)<=0)
	{
		alert("投票时间已经结束..");
		return false;
	}
	votes(num);
}
function votes(num)
{
	var voteURL = "myVoteInfoAction_vote.do";
	$.ajax({
		url : voteURL,
		data : {vid : voteid,resultType : num},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			var isVote = data.data.isVote;
			if(isVote>=1)
			{
				var reStr = data.data.reStr;
				alert(reStr);
			}else{
				switch (num) {
				case 1:
					gz = gz+1;
					$("#one").html("" + gz + "");
					break;
				case 2:
					love = love+1;
					$("#two").html("" + love + "");
					break;
				case 3:
					notlove = notlove+1;
					$("#three").html("" + notlove + "");
					break;
				case 4:
					buy = buy +1;
					$("#four").html("" + buy + "");
					break;
				}
			}
		},
		error : function(data) {
		}
	});
}

//评论投票信息
function commentVote() {
	if (islogin == 'not') {
//		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>您还没有登录，登录之后才能评论..</font></b></h3>");
		alert("您还没有登录，登录之后才能评论..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	if((d2-now)<=0)
	{
		alert("投票时间已经结束..");
		return false;
	}
	var contents = $("#contentText").val();
	if (contents.length <= 0) {
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>&nbsp;&nbsp;评论内容不能为空..</font></b></h3>");
		return false;
	}
	if (contents.length >= 50) {
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>&nbsp;&nbsp;评论内容过长..</font></b></h3>");
		return false;
	}
	$("#msg").html("");
	var commentVoteURL = "myVoteInfoAction_comment.do";
	$("#msg").html("<div class=\"profile_page\" align=\"center\"><img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" /></div>");
	$.ajax({
		url : commentVoteURL,
		data : {id : voteid,content : contents},
		type : "POST",
		dataType : "json",
		success : function(data) {
			$("#msg").html("");
			var result = data.data.result;
			if (result >= 1) {
				$("#contentText").val("");
				getCommentList(5,1,1);
			}
			if (result < 1) {
				alert("评论失败！");
			}
		},
		error : function(data) {
		}
	});
}
var t = n = 0, counts; 
function show()
{
	$(document).ready(function(){ 
		counts=$("#banner_list a").length; 
	$("#banner_list a:not(:first-child)").hide(); 
	$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt')); 
	$("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")}); 
	$("#banner li").click(function() { 
	var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4 
	n = i; 
	if (i >= counts) return; 
	$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt')); 
	$("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")}) 
	$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000); 
	$(this).css({"background":"#be2424",'color':'#000'}).siblings().css({"background":"#6f4f67",'color':'#fff'}); 
	}); 
	t = setInterval("showAuto();", 7000); 
	$("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 7000);}); 
	}) 
}
function showAuto() 
{ 
	n = n >=(counts - 1) ? 0 : ++n; 
	$("#banner li").eq(n).trigger('click'); 
}

//获取评论信息getCommentList  getVoteComment
function getCommentList(rows,pageNumber,ttp){
	var voteCommentURL = "myVoteInfoAction_getVoteComment.do";
	$.ajax({
		url : voteCommentURL,//voteid
		data : {pageNum : pageNumber , pageSize : rows,id : voteid,type : ttp},
		type : 'POST',
		dataType : 'json',
		async:true,
		success : function(data) {
			$("#voteComment").html("");
			var comments = data.data.commentInfos;
			for ( var i = 0; i < comments.length; i++) {
				var head = comments[i].userinfo.image;
				if(head.length<2)
				{
					head = comments[i].userinfo.otheraccountuserimage;
				}
				$("#voteComment").append("<div class=\"pl_txt\"><div class=\"u_ico\"><img src=\""+head+"\" width = '31px' height= '31px' /></div><div class=\"u_name1\"><p>"+ comments[i].userinfo.nick+ "</p><span>"+comments[i].commentdate+"</span></div><div class=\"u_txt\"><p>" + comments[i].commentinfo+ "</p><span></span></div></div>");
			}
			var count = data.data.count;
			if(count >= 10){
				var paginate = new pagination();
				//paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
				paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
			}else{
				$("#pl_page").html("");
			}
		},
		error : function(data) {
		}
	});
}

function flipPage(rows,pageNumber,id,ttp) {
	getCommentList(rows,pageNumber,id,ttp);
}
function countTopic(userid)
{
	var countTopicURL = "myVoteInfoAction_count.do";
	$.ajax({
		url : countTopicURL,
		data : {id : userid},
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			var countTopic = data.data.countTopic;
			var countVote = data.data.countVote;
			$("#countTopic").html("<a target=\"_blank\" href='user/"+userid+".html'>"+countTopic+"</a>");
			$("#countVote").html("<a target=\"_blank\" href='user_vote/"+userid+"'>"+countVote+"</a>");
		},
		error : function(data) {
		}
	});
}




//function getQueryStringRegExp(name)
//{
//	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
//	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
//}
//
//var voteid,index,islogin,ids;
////voteid = getQueryStringRegExp("id");
//var realurl = location.href;
//var idindexof = realurl.lastIndexOf("/");
//voteid = realurl.substring(idindexof + 1);
//ids = voteid;
//getvote(voteid);
//var now=new Date();
//var gz,love,notLove,buy;//类型
//var days,hours,minutes;
//var date1 = "";
//var date2 = "";
//var nowToStart;
//var startToEnd;
//var d1;
//var d2;
//function getvote() {
//	var voteURL = "myVoteInfoAction_getVote.do";
//	$.ajax({
//		url : voteURL,
//		data : {id : voteid },
//		type : 'POST',
//		dataType : 'json',
//		success : function(data) {
//			islogin = data.data.islogin;
//			var vote = data.data.myVoteInfo;
//			voteid = vote.voteId;
//			$("#title").html(vote.title);
//			$("#summarys").html(vote.summary);
//			var price=""+vote.price+"-"+vote.pricess+"";
//			$("#price").html(price);
//			$("#time").html(vote.starttime);
//			$("#tag").html(vote.voteTag);
//			$("#userNick").html("<a target=\"_blank\" href='user/"+vote.userinfo.userid+"'>"+vote.userinfo.nick+"</a>");
//			var vote_user_head = vote.userinfo.image;
//			if(vote_user_head.length<2)
//			{
//				vote_user_head = vote.userinfo.otheraccountuserimage;
//			}
//			$("#userHead").html("<a target=\"_blank\" href='user/"+vote.userinfo.userid+"'><img src=\""+vote_user_head+"\" width=\"73px\" height=\"73px\"/></a>");
//			
//			document.title = vote.title;
//			getImg(vote.voteId);
//			getJoinVote(voteid);
//			date2 = vote.endtime;
//			date1 = vote.starttime;
//			countTopic(vote.userinfo.userid);
//			getDate();
//			getCommentList(5,1,1);
//		},
//		error : function(data) {
//		}
//	});
//}
////获取相差的时间
//function getDate()
//{
//	date2 = date2.replace("年","/").replace("月","/").replace("日","");//转换成时间类型
//	date1 = date1.replace("年","/").replace("月","/").replace("日","");   //结束时间
//	d1=Date.parse(date1.replace(/-/g, "/"));//开始时间
//	d2=Date.parse(date2.replace(/-/g, "/"));//结束时间
//	
//	nowToStart=d1-now;
//	startToEnd=d2-d1;
//	if(nowToStart>=0){
//		var scha=new Date(nowToStart);//投票时间与当前时间的差值
//		//计算出相差天数
//		days=Math.floor(scha/(24*3600*1000));
//		//计算出小时数
//		var leave1=scha%(24*3600*1000);    //计算天数后剩余的毫秒数
//		hours=Math.floor(leave1/(3600*1000));
//		//计算相差分钟数
//		var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
//		minutes=Math.floor(leave2/(60*1000));
//		if(days>=0 && hours>=0 && minutes>=0){
//			$("#show").html("<h2>投票将于"+date1+"开始\n敬请关注！</h2>").show();
//			$("#votetime").css("text-align","center").html("<h3>投票尚未开始</h3>");
//		}
//	}else{
//		//投票开始
//		d1=new Date();
//		startToEnd=d2-d1;
//		$("#show").css("display","none");
//		if(startToEnd>0){
//			getTimeImg(startToEnd);
//		}else{
//			$("#votetime").css("text-align","center").html("<h3>投票已结束</h3>");
//		}
//	}
//}
//function getTimeImg(startToEnd){
//	
//	d1=new Date();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          ;
//	startToEnd=d2-d1;
//	if(startToEnd<0){
//		$("#votetime").css("text-align","center").html("<h3>投票已结束</h3>");
//		return;
//	}
//	var date3=new Date(startToEnd);
//	//计算出相差天数
//	days=Math.floor(date3/(24*3600*1000));
//	//计算出小时数
//	var leave1=date3%(24*3600*1000);    //计算天数后剩余的毫秒数
//	hours=Math.floor(leave1/(3600*1000));
//	//计算相差分钟数
//	var leave2=leave1%(3600*1000);        //计算小时数后剩余的毫秒数
//	minutes=Math.floor(leave2/(60*1000));
//	//计算相差秒数
//	//	var leave3=leave2%(60*1000);      //计算分钟数后剩余的毫秒数
//	//	var seconds=Math.round(leave3/1000);
//	//确定天数的对应图片
//	for(var n=0;n<100;n++){
//		if(days==n){
//			if(days<10){
//				$("#day").html("<img src='images/"+days+".png'/>");
//			}else{
//				var s=Math.floor(days/10);
//				var g=days%10;
//				$("#day").html("<img src='images/"+s+".png'/><img src='images/"+g+".png'/>");
//			}
//		}
//	}
//	
////	hours=20;确定时间对应的图片
//	for(var i=1;i<24;i++){
//		if(hours==0 || hours==00)
//		{
//			$("#hour").html("<img src='images/00.png'/><img src='images/00.png'/>");
//		}
//		if(hours==i){
//			if(hours<10)
//			{
//				$("#hour").html("<img src='images/00.png'/><img src='images/0"+hours+".png'/>");
//			}else{
//				var s=Math.floor(hours/10);
//				var g=hours%10;
//				$("#hour").html("<img src='images/0"+s+".png'/><img src='images/0"+g+".png'/>");
//			}
//		}
//	}
//	
//	//确定分钟所对应的图片
//	for(var j=0;j<60;j++){
//		if(minutes==j){
//			if(minutes<10){
//				$("#minute").html("<img src='images/00.png'/><img src='images/0"+minutes+".png'/>");
//			}else{
//				var s=Math.floor(minutes/10);
//				var g=minutes%10;
//				$("#minute").html("<img src='images/0"+s+".png'/><img src='images/0"+g+".png'/>");
//			}
//		}
//	}
//	setTimeout("getTimeImg(startToEnd)",5000);
//}
//// 得到图片信息
//function getImg(voids) {
//	var voteImgURL = "myVoteInfoAction_getVoteImg.do";
//	$.ajax({
//		url : voteImgURL,
//		data : {id : voids},
//		type : 'POST',
//		dataType : 'json',
//		success : function(data) {
//			$("#banner_list").html("");
//			$("#imgNum").html("");
//			var voteimgs = data.data.myVoteInfoImages;
//
//			for ( var i = 0; i < voteimgs.length; i++) {
//				$("#banner_list").append("<a href=\"javascript:void(0);\"><img src=\""+ voteimgs[i].imgAddress+ "\" /></a>");
//			}
//			for ( var j = 1; j <= voteimgs.length; j++) {
//				$("#imgNum").append("<li>"+ j + "</li>");
//			}
//			
//			show();
//		},
//		error : function(data) {
//			$("#banner_list").html("");
//			$("#imgNum").html("");
//		}
//	});
//}
//// 获取 用户投票类型
//function getJoinVote(voids) {
//	var joinvoteURL = "myVoteInfoAction_getJoinVote.do";
//	$.ajax({
//		url : joinvoteURL,
//		data : {
//			id : voids
//		},
//		type : 'POST',
//		dataType : 'json',
//		success : function(data) {
//			$("#one").html("");
//			$("#two").html("");
//			$("#three").html("");
//			$("#four").html("");
//			var one = data.data.one;
//			var two = data.data.two;
//			var three = data.data.three;
//			var four = data.data.four;
//			gz = data.data.gz;
//			love = data.data.love;
//			notLove = data.data.notLove;
//			buy = data.data.buy;
//			isVote = data.data.isVote;
//			$("#one").html("" + one + "");
//			$("#two").html("" + two + "");
//			$("#three").html("" + three + "");
//			$("#four").html("" + four + "");
//		},
//		error : function(data) {
//		}
//	});
//}
//
//// 投票
//function vote(num) {
//	if (islogin == 'not') {
//		alert("您还没有登录，登录之后才能投票..");
//		myScroll();
//		$("#window_dl").css("display","block");
//		return false;
//	}
//	if(nowToStart>=0)
//	{
//		alert("投票尚未开始..");
//		return false;
//	}
//	if((d2-now)<=0)
//	{
//		alert("投票时间已经结束..");
//		return false;
//	}
//	if(num==1)
//	{
//		if(gz == "not")
//		{
//			gz = "have";
//			votes(num);
//		}else{
//			alert("您已经关注过该商品了..");
//			return false;
//		}
//	}
//	if(num==2)
//	{
//		if(love =="not")
//		{
//			love = "have";
//			votes(num);
//		}else{
//			alert("您已经投过票了..");
//			return false;
//		}
//	}
//	if(num==3)
//	{
//		if(notLove=="not")
//		{
//			notLove = "have";
//			votes(num);
//		}else{
//			alert("您已经投过票了..");
//			return false;
//		}
//	}
//	if(num==4)
//	{
//		if(buy=="not")
//		{
//			buy = "have";
//			votes(num);
//		}else{
//			alert("我们尽快与商家联系..");
//			return false;
//		}
//	}
//}
//function votes(num)
//{
//	var voteURL = "myVoteInfoAction_vote.do";
//	$.ajax({
//		url : voteURL,
//		data : {
//			id : voteid,
//			ids : num
//		},
//		type : 'POST',
//		dataType : 'json',
//		success : function(data) {
//			var result = data.data.result;
//			if (result >= 1) {
//				getJoinVote(voteid);
//			} else {
//				alert("投票失败..");
//			}
//		},
//		error : function(data) {
//		}
//	});
//}
//
////评论投票信息
//function commentVote() {
//	if (islogin == 'not') {
////		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>您还没有登录，登录之后才能评论..</font></b></h3>");
//		alert("您还没有登录，登录之后才能评论..");
//		myScroll();
//		$("#window_dl").css("display","block");
//		return false;
//	}
//	if((d2-now)<=0)
//	{
//		alert("投票时间已经结束..");
//		return false;
//	}
//	var contents = $("#contentText").val();
//	if (contents.length <= 0) {
//		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>&nbsp;&nbsp;评论内容不能为空..</font></b></h3>");
//		return false;
//	}
//	if (contents.length >= 50) {
//		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>&nbsp;&nbsp;评论内容过长..</font></b></h3>");
//		return false;
//	}
//	$("#msg").html("");
//	var commentVoteURL = "myVoteInfoAction_comment.do";
//	$("#msg").html("<div class=\"profile_page\" align=\"center\"><img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" /></div>");
//	$.ajax({
//		url : commentVoteURL,
//		data : {id : voteid,content : contents},
//		type : "POST",
//		dataType : "json",
//		success : function(data) {
//			$("#msg").html("");
//			var result = data.data.result;
//			if (result >= 1) {
//				$("#contentText").val("");
//				getCommentList(5,1,1);
//			}
//			if (result < 1) {
//				alert("评论失败！");
//			}
//		},
//		error : function(data) {
//		}
//	});
//}
//var t = n = 0, counts; 
//function show()
//{
//	$(document).ready(function(){ 
//		counts=$("#banner_list a").length; 
//	$("#banner_list a:not(:first-child)").hide(); 
//	$("#banner_info").html($("#banner_list a:first-child").find("img").attr('alt')); 
//	$("#banner_info").click(function(){window.open($("#banner_list a:first-child").attr('href'), "_blank")}); 
//	$("#banner li").click(function() { 
//	var i = $(this).text() - 1;//获取Li元素内的值，即1，2，3，4 
//	n = i; 
//	if (i >= counts) return; 
//	$("#banner_info").html($("#banner_list a").eq(i).find("img").attr('alt')); 
//	$("#banner_info").unbind().click(function(){window.open($("#banner_list a").eq(i).attr('href'), "_blank")}) 
//	$("#banner_list a").filter(":visible").fadeOut(500).parent().children().eq(i).fadeIn(1000); 
//	$(this).css({"background":"#be2424",'color':'#000'}).siblings().css({"background":"#6f4f67",'color':'#fff'}); 
//	}); 
//	t = setInterval("showAuto();", 7000); 
//	$("#banner").hover(function(){clearInterval(t)}, function(){t = setInterval("showAuto()", 7000);}); 
//	}) 
//}
//function showAuto() 
//{ 
//	n = n >=(counts - 1) ? 0 : ++n; 
//	$("#banner li").eq(n).trigger('click'); 
//}
//
////获取评论信息getCommentList  getVoteComment
//function getCommentList(rows,pageNumber,ttp){
//	var voteCommentURL = "myVoteInfoAction_getVoteComment.do";
//	$.ajax({
//		url : voteCommentURL,//voteid
//		data : {pageNum : pageNumber , pageSize : rows,id : voteid,type : ttp},
//		type : 'POST',
//		dataType : 'json',
//		async:true,
//		success : function(data) {
//			$("#voteComment").html("");
//			var comments = data.data.commentInfos;
//			for ( var i = 0; i < comments.length; i++) {
//				var head = comments[i].userinfo.image;
//				if(head.length<2)
//				{
//					head = comments[i].userinfo.otheraccountuserimage;
//				}
//				$("#voteComment").append("<div class=\"pl_txt\"><div class=\"u_ico\"><img src=\""+head+"\" width = '31px' height= '31px' /></div><div class=\"u_name1\"><p>"+ comments[i].userinfo.nick+ "</p><span>"+comments[i].commentdate+"</span></div><div class=\"u_txt\"><p>" + comments[i].commentinfo+ "</p><span></span></div></div>");
//			}
//			var count = data.data.count;
//			if(count >= 10){
//				var paginate = new pagination();
//				//paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
//				paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
//			}else{
//				$("#pl_page").html("");
//			}
//		},
//		error : function(data) {
//		}
//	});
//}
//
//function flipPage(rows,pageNumber,id,ttp) {
//	getCommentList(rows,pageNumber,id,ttp);
//}
//function countTopic(userid)
//{
//	var countTopicURL = "myVoteInfoAction_count.do";
//	$.ajax({
//		url : countTopicURL,
//		data : {id : userid},
//		type : 'POST',
//		dataType : 'json',
//		success : function(data) {
//			var countTopic = data.data.countTopic;
//			var countVote = data.data.countVote;
//			$("#countTopic").html("<a target=\"_blank\" href='user/"+userid+"'>"+countTopic+"</a>");
//			$("#countVote").html("<a target=\"_blank\" href='user_vote/"+userid+"'>"+countVote+"</a>");
//		},
//		error : function(data) {
//		}
//	});
//}
//

