var islogin,user,type,t_end_li,u_end_li,c_end_li;//热门话题结束标签、热门用户结束标签、当前用
var ispk = 0;

//JavaScript Document
window.onload=function ()
{
	tanChu()
	touPiao()
//	var oDiv=document.getElementById('div1');
//	var aBtn=document.getElementById('btn');
//	var aL=aBtn.getElementsByTagName('ul')[0].getElementsByTagName('li');
//	var aA=aBtn.getElementsByTagName('a');
//	var aDiv=getByClass(oDiv,'topic_subqie');
//	for(var i=0; i<aA.length; i++){
//		aA[i].strive=i;
//		aA[i].onmouseover=function(){
//			for(var i=0; i<aA.length; i++){
//				aA[i].className='';
//				aDiv[i].style.display='none';
//			}
//			this.className='topic_active';
//			aDiv[this.strive].style.display='block';
//			
//		}
//	}
	
	
//	var oPicnav=document.getElementById('pic_nav');
//	var oUl=oPicnav.getElementsByTagName('ul')[0];
//	var aLi=oUl.getElementsByTagName('li');
//	var oOl=document.getElementById('pic_roav');
//	var aLi1=oOl.getElementsByTagName('li');
//	
//    var num=0;
	
//	for(var i=0;i<aLi1.length;i++)
//	{
//		aLi1[i].index=i;
//		aLi1[i].onmouseover=function()
//		{
//			num=this.index;
//			for(var i=0;i<aLi1.length;i++)
//			{
//				aLi1[i].className='';
//				aLi[i].style.display='none';
//			}
//			aLi[num].style.display='block';
//			
//		};
//	}
	
	
	
	
	
}
function getByClass(oParent,sClass){
	var aResult=[];
	var aEle=oParent.getElementsByTagName('*');
	for(var i=0; i<aEle.length; i++){
		if(aEle[i].className==sClass){
			aResult.push(aEle[i]);
		}	
	}
	return aResult;
}


function tab()
	{
		for(var i=0;i<aLi1.length;i++)
		{
			aLi1[i].className='';
			aLi[i].style.display='none';
		}
		aLi[num].style.display='block';
		
	}
	
	
	
	
	
	
function tanChu()   //弹出层
{
	if(ispk==1)
	{
		$("#box_B").css("display","none");
		$("#ropic_lent").css("display","none");
		return false;
	}
	
	var oDivTan =document.getElementById('ropic_lent');
	var oDivA =document.getElementById('box_B');
	
	var oDivL =document.getElementById('pro_pk_red');
	var oDivR =document.getElementById('pro_pk_blue');
	oDivA.style.width =viewWidth()+'px';
	oDivA.style.height =documentHeight()+'px';
	
	
	
	function tanKuan()
	{
			$("#redimg").html("<img src=\"images/pk-1.PNG\" />");$("#blueimg").html("<img src=\"images/pk_2.PNG\" />");
			if(type==1)
			{
				$("#ropic_lent_p").html("红方");
				$("#ropic_tit").html("红方");
			}
			if(type==2)
			{
				$("#ropic_lent_p").html("蓝方");
				$("#ropic_tit").html("蓝方");
			}
			oDivTan.style.left =(viewWidth()-oDivTan.offsetWidth)/2+'px';
			oDivTan.style.top =(viewHeight()-oDivTan.offsetHeight)/2+'px';
	}
	
	
	oDivL.onclick =function()
	{
		type = 1;
		if(!islogin)
		{
			alert("你还没有登录！");
			$("#window_dl").css("display","block");
			return false;
		}
		oDivA.style.display ='block';
		oDivTan.style.display ='block';
		tanKuan()	
		
	}
	oDivR.onclick =function()
	{
		type = 2;
		if(!islogin)
		{
			alert("你还没有登录！");
			$("#window_dl").css("display","block");
			return false;
		}
		oDivA.style.display ='block';
		oDivA.style.display ='block';
		oDivTan.style.display ='block';
		tanKuan()
	}
	
	
	
	window.onscroll = window.onresize = function()
	{
		var oDivTan =document.getElementById('ropic_lent');
		if(oDivTan)
		{
			oDivTan.style.left =(viewWidth()-oDivTan.offsetWidth)/2+'px';
			oDivTan.style.top =(viewHeight()-oDivTan.offsetHeight)/2+'px';
		}
		
	}
	//弹出结束
}



//投票开始
function touPiao()
{
//	var oDiv =document.getElementById('pro_pk_onu');
//	var oDivA =document.getElementById('pro_span_1')
//	var oDivB =document.getElementById('pro_span_2')
//	var numA =parseInt(oDivA.innerHTML);
//	var numB =parseInt(oDivB.innerHTML);
//	var nubA =0;
//	var nubB =0;
//	var numM =numA+numB
//	var scale =oDiv.offsetWidth/numM
//	oDivA.style.width =oDivA.offsetWidth+'px';
//	oDivB.style.width =oDivB.offsetWidth+'px';
//	
//	
//	oDivA.onclick =function()
//	{
//		numA++
//		numM =numA+numB
//		scale =oDiv.offsetWidth/numM
//		if(oDivA.offsetWidth>=oDiv.offsetWidth)
//		{
//			alert('亲，满票耶')
//			oDivA.innerHTML=numA;	
//		}
//		
//		else{
//				oDivA.style.width =numA*scale+'px';
//				oDivB.style.width =numB*scale+'px';
//				oDivA.innerHTML=numA;
//			}
//	}
//	oDivB.onclick =function()
//	{
//		numB++
//		numM =numA+numB
//		scale =oDiv.offsetWidth/numM
//		if(oDivB.offsetWidth>=oDiv.offsetWidth)
//		{
//			alert('亲，满票耶')
//			oDivB.innerHTML=numB;
//		}
//		else{
//			oDivA.style.width =numA*scale+'px';
//			oDivB.style.width =numB*scale+'px';
//			oDivB.innerHTML=numB;
//			
//			}
//	}
		
}
//投票结束



function viewWidth(){
	return document.documentElement.clientWidth;
}
function viewHeight(){
	return document.documentElement.clientHeight;
}

function documentHeight(){
	return Math.max(document.documentElement.scrollHeight || document.body.scrollHeight,document.documentElement.clientHeight);
}

//$(function(){
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	var topicPkId = realurl.substring(idindexof+1,realurl.search(".html"));
	var tid = topicPkId;
	getTopicPk(topicPkId,1,7);
	$("#plContent").val("");
	$("#pro_span_1").css("width","0px");
	$("#pro_span_2").css("width","0px");
//});
function getTopicPk(id,pageNum,pageSize){
	var getTopicPkURL= "topicAction_getTopicPk.do";
	$.ajax({
		url:getTopicPkURL,
		data:{id:id,pageNum : pageNum, pageSize : pageSize},
		type:"POST",
		dataType:"json",
		success: function(data){
			user = data.data.user;
			islogin = data.data.islogin;
			var pkTopic = data.data.pkTopic;
			if(pkTopic==null)
			{
				alert("该PK话题不存在，点击确定返回话题首页！");
				var url = "quanzi/newTopics.jsp";
				location.href = basePath+url;
				return false;
			}
			var def1 = pkTopic.def1;
			var def2 = pkTopic.def2;
			if(def2!=1 || def1 != def2)
			{
				alert("该PK话题不存在，点击确定返回话题首页！");
				var url = "quanzi/newTopics.jsp";
				location.href = basePath+url;
				return false;
			}
			document.title = pkTopic.title;
			
			if(islogin)
			{
				ispk = pkTopic.ispk;
				if(ispk==1)
				{
					var cc = data.data.ccid;
					for ( var c = 0; c < cc.length; c++) {
						var ispktype = cc[c].ispktype;
						if(ispktype==1)
						{
							$("#redimg").html("<img src=\"images/pk-1.PNG\" />");$("#blueimg").html("<img src=\"images/pk_2.PNG\" />");
						}
						if(ispktype==2)
						{
							$("#redimg").html("<img src=\"images/pk-1.PNG\" />");$("#blueimg").html("<img src=\"images/pk_2.PNG\" />");
						}
					}
					
				}
			}
			
			var pk_userId = pkTopic.userinfo.userid;
			var topicuser = "<a href=\"user/"+pk_userId+".html\" target=\"_blank\"><img src=\""+pkTopic.userinfo.image+"\" alt=\""+pkTopic.userinfo.nick+"\" width='48px' height='48px'/></a>";
			$("#topic_user").append(topicuser);
			
			var nick = pkTopic.userinfo.nick;
			var createdatetime = pkTopic.createdatetime;
			var isGz = pkTopic.isgz;
			var gzimg = "";
			var imghtml = "";
			if(isGz==1)
			{
				gzimg = "images/pro_importan_1t.gif";
				imghtml = "<img src=\""+gzimg+"\" /></a>";
			}else{
				isgz = 0;
				gzimg = "images/pro_important.gif";
//				$("#isimg"+1+tid).attr("src","images/pro_importan_1t.gif");
				imghtml = "<img id=\"isimg"+id+"\" onclick=\"gzTopic('"+id+"',1)\" src=\""+gzimg+"\" /></a>";
			}
			var topicTitle = "<div class=\"pro_tiilt\" >" +
				"<h2>"+pkTopic.title+"</h2>"+
				"<div class=\"top_impo\"><a href=\"javascript:void(0);\" id=\"gzid_a1"+tid+"\">"+imghtml+"</div>"+
				"<span><strong><a href=\"user/"+pk_userId+".html\" target=\"_blank\">"+nick+"</a></strong>&nbsp&nbsp发表于&nbsp"+createdatetime+"</span>"+
				"</div>"  ;
			$("#topicTitle").append(topicTitle);
			$("#content").html(pkTopic.circlecontent);
			var imgAddress = pkTopic.circleDetailImg;
			var picPath = "";
			for(var i=0; i<imgAddress.length;i++){
				if(i>=1){break;}
				var big = imgAddress[i].bigimg;
				$("#topicimg").append("<img src=\""+big+"\" onload=\"scaleImage(this,646,10000)\" />");
				picPath = imgAddress[0].bigimg;
			}
			picPath = "1mily.com/"+picPath;
			var str =
			"<script type=\"text/javascript\" >"+
			"var jiathis_config={"+
			"pic:\""+picPath+"\","+
			"data_track_clickback:false,"+
			"appkey:{"+
			"\"tsina\":\"2614308697\",\"renren\":\"184905\",\"t163\":\"mBjMmCCPvbKOvvQI\",\"tqq\":\"100255656\" "+
			"}"+
			"}"+
			"</script>";
			$("#fenxiang").append(str);
			
			if(user==null||user==""){
				var str = "<a href=\"###\"><img src=\"images/nobody.gif\" /></a>";
				$("#userimg").append(str);
			}
			var hotCount = data.data.hotCount;
			if(hotCount>7){
				$("#hottopic").css("display","block");
			}
			
			/**
			 * 获取评论
			 */
			var topicPk = data.data.topicPk;
			var redCount = data.data.redCount;
			var blueCount = data.data.blueCount;
			var blueCircleCommentInfos = data.data.blueCircleCommentInfos;
			var redCircleCommentInfos = data.data.redCircleCommentInfos;
			var objblue = "redCommentHTML";
			pkCommentHTML(redCircleCommentInfos,objblue,redCount,blueCount);
			var objred = "blueCommentHTML";
			pkCommentHTML(blueCircleCommentInfos,objred,redCount,blueCount);
			writeS(redCount,blueCount);
			if(redCount>10 || blueCount>10)
			{
				$("#moreComment").css("display","block");
			}
			
			var hotTopics = data.data.hotTopics;
			for(var j=0;j<hotTopics.length;j++){
				var hottopicid = hotTopics[j].circledetailid;
				var title = hotTopics[j].title;
				if(title.length>10)
				{
					title = title.substring(0,10);
					title = title+"..";
				}
				var userimg = hotTopics[j].userinfo.image;
				var userid = hotTopics[j].userinfo.userid;
				var t_isgz = hotTopics[j].isgz;
				var gzcount = hotTopics[j].def3;
				
				var cuser = hotTopics[j].gztopics;
				var gzuserid = "";
				var gzusernick = "";
				if(cuser.userinfo != null)
				{
					gzuserid = cuser.userinfo.userid;
					gzusernick = cuser.userinfo.nick;
				}
				if(gzcount==""){
					gzcount=0;
				}
				t_end_li = hottopicid;
				hoteHTML(hottopicid,t_isgz,userimg,title,gzcount,userid,gzuserid,gzusernick);
			}
			$("#li"+t_end_li).css("border-bottom","none");
			var count = data.data.count;
			if(count>7){
				$("#moreuser").css("display","block");
			}
			
			var hotUser = data.data.userTopics;

			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var uid = hotUser[u].userinfo.userid;
				u_end_li = uid;
				var hotuserHTML="<li id=\"li"+uid+"\">"+
                "<div class=\"topic_r_img\"><a href=\"user/"+hotUser[u].userinfo.userid+".html\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
                "<p>"+
                    "<span><a href=\"user/"+hotUser[u].userinfo.userid+".html\" target=\"_blank\">"+hotUser[u].userinfo.nick+"</a></span>"+
                    "<strong>参与了"+hotUser[u].count+"个话题讨论</strong>"+
                "</p>"+
            "</li>";
				$("#hotUser").append(hotuserHTML);
			}
			$("#li"+u_end_li).css("border-bottom","none");
			var countHotTopic = data.data.countHotTopic;
			var countHotUser = data.data.countHotUser;
			if(countHotTopic<=7)
			{
				$("#topic_more_a").html("");
			}
			if(countHotUser<=7)
			{
				$("#user_more_a").html("");
			}
			
		},
		error: function(data){
		}
	});
}
function writeS(red,blue)
{
	var sum = red+blue;
	if(red>0)
	{
		$("#pro_span_1").html(red);
	}
	if(blue>0)
	{
		$("#pro_span_2").html(blue);
	}
	red = red/sum*100;
	blue = blue/sum*100;
	$("#pro_span_1").css("width",red+"%");
	$("#pro_span_2").css("width",blue+"%");
}
function getComment(pageNum,pageSize)
{

	var getCommentURL = "topicDetailAction_getComment.do";
	$.ajax({
		url:getCommentURL,
		data:{id:tid,pageNum : pageNum, pageSize : pageSize},
		type:"POST",
		dataType:"json",
		success: function(data){
			var redCount = data.data.redCount;
			var blueCount = data.data.blueCount;
			var blueCircleCommentInfos = data.data.blueCircleCommentInfos;
			var redCircleCommentInfos = data.data.redCircleCommentInfos;
			var objblue = "redCommentHTML";
			writeS(redCount,blueCount);
			pkCommentHTML(redCircleCommentInfos,objblue,redCount,blueCount);
			var objred = "blueCommentHTML";
			pkCommentHTML(blueCircleCommentInfos,objred,redCount,blueCount);
			if(blueCircleCommentInfos.length<10 && redCircleCommentInfos.length<10)
			{
				$("#moreComment").css("display","none");
			}
		},
		error: function(data){
		}
	});
}
/**
 * 评论ul
 * @param CommentInfos 
 * @param obj 对象
 * @param redCount 红方评论数量
 * @param blueCount 蓝方评论数量
 */
function pkCommentHTML(CommentInfos,obj,redCount,blueCount)
{
	for ( var c = 0; c < CommentInfos.length; c++) {
		var userid = CommentInfos[c].userinfo.userid;
		var userimg = CommentInfos[c].userinfo.image;
		if(userimg.length<5)
		{
			userimg = CommentInfos[c].userinfo.otheraccountuserimage;
		}
		var usernick = CommentInfos[c].userinfo.nick;
		var comment = CommentInfos[c].commentinfo;
		var createdate = CommentInfos[c].createdate;
		$("#redCountHTML").html(redCount);
		$("#blueCountHTML").html(blueCount);
		c_end_li = userid;
		var html = "<li id=\"li"+userid+"\">"+
	    "<div class=\"pk_txt_l-img\"><a target=\"_blank\" href=\"user/"+userid+".html\"><img width='50px' height='50px' src=\""+userimg+"\" /></a></div>"+
	    "<div class=\"pk_txt_l-text\">"+
	        "<h3><a target=\"_blank\" href=\"user/"+userid+".html\">"+usernick+"</a></h3>"+
	        "<p>"+comment+"</p>"+
	        "<span>"+createdate+"</span>"+
	    "</div>"+
	"</li>";
		$("#"+obj).append(html);
	}
	$("#li"+c_end_li+"").css("border-bottom","none");
}


/**
 * 更多的热门用户
 */
var mu = 2;
var moreUserEND = false;
function moreUser()
{
	if(moreUserEND == false)
	{
	var moreUserURl="topicAction_moreUser.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 7,pageNum : mu},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var hotUser = data.data.userTopics;
			if(hotUser.length<7)
			{
				$("#user_more_a").html("没有更多了");
				moreUserEND = true;
			}
			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var count = hotUser[u].count;
				var nick = hotUser[u].userinfo.nick;
				var userid = hotUser[u].userinfo.userid;
				moreUserHTML(userimg,nick,count,userid);
			}
		},
		error:function(data)
		{
		}
	});
	mu++;
	}
}
function getMoreUser(pageNum,pageSize){
	var moreuserURL = "topicAction_moreUser.do";
	$.ajax({
		url:moreuserURL,
		data:{pageNum : pageNum, pageSize : pageSize},
		type:"POST",
		dataType:"json",
		success: function(data){
			var count = data.data.count;
			if(count>7){
				$("#moreuser").css("display","block");
			}
			var hotUser = data.data.userTopics;
			if(hotUser.length<7)
			{
				$("#topic_more").html("没有更多了");
			}
			for(var u = 0;u<hotUser.length;u++)
			{
				var userimg = hotUser[u].userinfo.image;
				if(userimg.length<5)
				{
					userimg = hotUser[u].userinfo.otheraccountuserimage;
				}
				var hotuserHTML="<li>"+
                "<div class=\"topic_r_img\"><a href=\"user/"+hotUser[u].userinfo.userid+".html\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
                "<p>"+
                    "<span><a href=\"user/"+hotUser[u].userinfo.userid+".html\" target=\"_blank\">"+hotUser[u].userinfo.nick+"</a></span>"+
                    "<strong>参与了"+hotUser[u].count+"个话题讨论</strong>"+
                "</p>"+
            "</li>";
				$("#hotUser").append(hotuserHTML);
			}
		},
		error:function(data){
			
		}
	});
}
/**
 * 关注话题
 * @param tid
 */
function gzTopic(tid,type)
{
	var gzTopicURl="gztopicsAction_gzTopic.do";
	$.ajax({
		url:gzTopicURl,
		data:{tid : tid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			islogin = data.data.islogin;
			var result = data.data.result;
			if(islogin)
			{
//				$("#isimg"+tid).attr("src","images/pro_importan_1t.gif");
				if(type==1)
				{
					//当前话题
					$("#gzid_a1"+tid).html("<img src='images/pro_importan_1t.gif'/>");
				}
				if(type==2)
				{
					//热门
					$("#gzid_a"+tid).html("<img src='images/pro_importan_1t.gif'/>");
				}
				alert(data.data.result);
			}else{
				alert(data.data.result);
				$("#window_dl").css("display","block");
			}
		},
		error:function(data)
		{
			alert("关注失败！");
		}
	});
}

/**
 * 更多的热门话题
 */
var mt = 1;
var morehotTopicsEND = false;
function morehotTopics()
{
	if(morehotTopicsEND==false)
	{
	mt++;
	var moreUserURl="topicDetailAction_moreTopics.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 7,pageNum : mt},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var hotTopics = data.data.hotTopics;
			
			for ( var h = 0; h < hotTopics.length; h++) {
				var uimg = hotTopics[h].userinfo.image;
				var userid = hotTopics[h].userinfo.userid;
				if(uimg.length<5)
				{
					uimg = hotTopics[h].userinfo.otheraccountuserimage;
				}
				var title = hotTopics[h].title;
				if(title.length>7)
				{
					title = title.substring(0,7);
					title = title+"..";
				}
				var isgz = hotTopics[h].isgz;
				var tid = hotTopics[h].circledetailid;
				var def3 = hotTopics[h].def3;
				var gzcount = hotTopics[h].def3;
				var cuser = hotTopics[h].gztopics;
				var gzuserid = "";
				var gzusernick = "";
				if(cuser)
				{
					gzuserid = cuser.userinfo.userid;
					gzusernick = cuser.userinfo.nick;
				}
				hoteHTML(tid,isgz,uimg,title,def3,userid,gzuserid,gzusernick);
			}
			if(hotTopics.length<7)
			{
				$("#topic_more_a").html("没有更多了");
				morehotTopicsEND = true;
			}
				
		},
		error:function(data)
		{
		}
	});
	}
}
function pkComment()
{
	var plContent = $("#idpk").val();
	if(plContent.length <= 0)
	{
		alert("评论内容不能为空！");
		return false;
	}
	if(plContent.length >= 200)
	{
		alert("评论内容太长了！");
		return false;
	}
	var pkCommentURl="topicDetailAction_pkComment.do";
	$.ajax({
		url:pkCommentURl,
		data:{id : tid,comType : type,content : plContent},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var result = data.data.result;
			var islogin = data.data.islogin;
			$("#redimg").html("<img src=\"images/pro_PK_red.png\" />");$("#blueimg").html("<img src=\"images/pro_PK_blue.png\" />");
			if(!islogin)
			{
				alert(result);
				$("#box_B").css("display","none");
				$("#pkContent").val("");
				$("#window_dl").css("display","block");
				
				$("#box_B").css("display","none");$("#ropic_lent").css("display","none");
			}else{
				$("#box_B").css("display","none");
				alert(result);
				if(type==1)
				{
					$("#pro_pk_blue").html("<a href='javascript:void();'><img src=\"images/pk_2.PNG\" /></a>");
					
				}
				if(type==2)
				{
					$("#pro_pk_red").html("<a href='javascript:void();'><img  src=\"images/pk-1.PNG\" /></a>");
				}
				$("#redCommentHTML").html("");
				$("#blueCommentHTML").html("");
				$("#box_B").css("display","none");$("#ropic_lent").css("display","none");
				getComment(1,10);
			}
			
			
		},
		error:function(data)
		{
			alert("评论失败！");
		}
	});
}
/**
 * 热门话题
 * @param tid 话题ID
 * @param uimg 用户头像
 * @param title 标题
 * @param def3 关注数量
 * @param isgzImg 是否已经关注图片
 * @param userid 用户ID
 */
function hoteHTML(tid,isgz,uimg,title,def3,userid,gzuserid,gzusernick)
{
	var isgzImg = "";
	if(isgz==1)
	{
		isgzImg = "images/pro_importan_1t.gif";
	}else{
		isgzImg = "images/pro_important.gif";
	}
	var hotuHTML = "<li id=\"li"+tid+"\">"+
//               "<div class=\"pk_about_l\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img onclick=\"gz('"+tid+"')\" src=\""+uimg+"\" width='25px' /></a></div>"+
               "<div class=\"pk_about_r\">"+
                   "<h4><a href=\"topic_tu/"+tid+".html\" target=\"_blank\">"+title+"</a></h4>"+
                   "<span><a href=\"javascript:void(0);\">"+def3+"人</a>&nbsp;关注</span>"+
//                   "<p><a target=\"_blank\" title=\""+gzusernick+"\" href=\"user/"+gzuserid+"\">"+gzusernick+"</a> &nbsp;关注了 &nbsp;该话题</p>"+
               "</div>"+
               "<div class=\"pk_guanzhu1\"><a href=\"javascript:void(0)\" id=\"gzid_a"+tid+"\"><img src=\""+isgzImg+"\"/></a></div>"+
            "</li>";
	$("#hotTopics").append(hotuHTML);
	if(isgz==1)
	{
		$("#gzid_a"+tid).html("<img id=\"gzid"+tid+"\" src='images/pro_importan_1t.gif'>");
	}else{
		$("#gzid_a"+tid).html("<img id=\"gzid"+tid+"\" onclick=\"gzTopic('"+tid+"',2)\" src='images/pro_important.gif'>");
	}
}
/**
 * 热门用户
 * @param userimg 用户头像
 * @param nick 用户昵称
 * @param count 关注数量
 * @param count 用户ID
 */
function moreUserHTML(userimg,nick,count,userid)
{
	var hotuserHTML="<li>"+
    "<div class=\"topic_r_img\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img width=\"30px\" height=\"30px;\" src=\""+userimg+"\" /></a></div>"+
    "<p>"+
        "<span><a href=\"user/"+userid+".html\" target=\"_blank\">"+nick+"</a></span>"+
        "<strong>参与了"+count+"个话题讨论</strong>"+
    "</p>"+
"</li>";
	$("#hotUser").append(hotuserHTML);
}
var i =2;
function pageComment()
{
	getComment(i,10);
	i++;
}
