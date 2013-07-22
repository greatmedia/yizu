$(function(){
//	alert("ssssssssssss");
	getHotUser(16,1);
});
var t_end_li;
function getHotUser(pageSize,pageNum){
	var moreUserURl="topicAction_moreUser.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : pageSize,pageNum : pageNum},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#body").css("display","block");
			var hotUser = data.data.userTopics;
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
				writeUserHTML(userid,userimg,nick,count);
				writeUserHTM2L(userid,userimg,nick,count,u);
			}
			var count = data.data.count;
			if(count<=pageSize)
			{
				$("#topic_more").css("display","none");
				$("#topic_more1").css("display","none");
			}else{
				$("#topic_more").css("display","block");
				$("#topic_more1").css("display","block");
			}
			getHotTopics();
		},
		error:function(data)
		{
		}
	});
}

var i = 1;
//热门话题
function getHotTopics(){
	var moreUserURl="topicDetailAction_moreTopics.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 7,pageNum : i},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#li"+t_end_li).removeAttr("style");
			$("#topic_moresss").css("display","block");
			var hotTopics = data.data.hotTopics;
			for ( var h = 0; h < hotTopics.length; h++) {
				var uimg = hotTopics[h].userinfo.image;
				var userid = hotTopics[h].userinfo.userid;
				if(uimg.length<5)
				{
					uimg = hotTopics[h].userinfo.otheraccountuserimage;
				}
				var cuser = hotTopics[h].gztopics;
				var gzuserid = "";
				var gzusernick = "";
				if(cuser!=null)
				{
					gzuserid = cuser.userinfo.userid;
					gzusernick = cuser.userinfo.nick;
				}
				var title = hotTopics[h].title;
				var isgz = hotTopics[h].isgz;
				var tid = hotTopics[h].circledetailid;
				var def3 = hotTopics[h].def3;
				t_end_li = tid;
				hoteHTML(tid,isgz,uimg,title,def3,userid,gzuserid,gzusernick);
			}
			if(hotTopics.length<=0)
			{
				$("#moreid").css("display","none");
			}
			$("#li"+t_end_li).css("border-bottom","none");
		},
		error:function(data)
		{
		}
	});
	i++;
}
var pageNum = 2;
/**
 * 更多热门用户
 */
function moreHotUser(){
	var moreUserURl="topicAction_moreUser.do";
	$.ajax({
		url:moreUserURl,
		data:{pageSize : 16,pageNum : pageNum},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var hotUser = data.data.userTopics;
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
				writeUserHTML(userid,userimg,nick,count);
				writeUserHTM2L(userid,userimg,nick,count,u);
			}
			if(hotUser.length<16)
			{
				$("#topic_more").css("display","none");
				$("#topic_more1").css("display","none");
			}else{
				$("#topic_more").css("display","block");
				$("#topic_more1").css("display","block");
			}
		},
		error:function(data)
		{
		}
	});
	pageNum ++;
}
/**
 * 热门话题
 * @param tid
 * @param isgz
 * @param uimg
 * @param title
 * @param def3
 * @param userid
 * @param gzuserid
 * @param gzusernick
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
	var qtitle = title;
	if(title.length>7)
	{
		title = title.substring(0,7);
		title = title+"..";
	}
	var unick = gzusernick;
	if(gzusernick.length>4)
	{
		gzusernick = gzusernick.substring(0,4);
		gzusernick = gzusernick+".";
	}
	var hotuHTML = "<li id=\"li"+tid+"\">"+
               "<div class=\"pk_about_l\"><a href=\"user/"+userid+".html\" target=\"_blank\"><img onclick=\"gz('"+tid+"')\" src=\""+uimg+"\" width='25px' /></a></div>"+
               "<div class=\"pk_about_r\">"+
                   "<h4><a title="+qtitle+" href=\"topic_tu/"+tid+".html\" target=\"_blank\">"+title+"</a></h4>"+
                   "<span><a target=\"_blank\" href=\"user/"+gzuserid+".html\">"+def3+"人</a>&nbsp;关注</span>"+
//                   "<p><a title="+unick+" href=\"javascript:void()\">"+gzusernick+"</a> &nbsp;关注了 &nbsp;该话题</p>"+
               "</div>"+
               "<div class=\"pk_guanzhu1\"><a href=\"javascript:void(0)\" id=\"gzid_a"+tid+"\"><img src=\""+isgzImg+"\"/></a></div>"+
            "</li>";
	$("#hotTopics").append(hotuHTML);
	var gzImg = document.createElement("img");
	gzImg.id="gzid"+tid;
	if(isgz==1)
	{
		gzImg.src = "images/pro_importan_1t.gif";
	}else{
		gzImg.setAttribute("onclick","gzTopic('"+tid+"')");
		gzImg.src = "images/pro_important.gif";
	}
	$("#gzid_a"+tid).html(gzImg);
}

/**
 * 关注话题
 * @param tid 话题ID
 */
function gzTopic(tid)
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
				$("#gzid"+tid).attr("src","images/pro_importan_1t.gif");
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
 * 用户列表页面
 * @param userid 用户ID
 * @param userimg 用户头像
 * @param nick 用户昵称
 * @param count 参与话题数量
 */
function writeUserHTML(userid,userimg,nick,count)
{
	var li = document.createElement("li");
	$("#hotUser").append(li);
	
	var li_div = document.createElement("div");
	li.appendChild(li_div);
	
	var li_div_a = document.createElement("a");
	li_div_a.id="user"+userid;
	li_div_a.target="_blank";
	li_div_a.href= "user/"+userid+".html";
	li_div.appendChild(li_div_a);
	
//	var li_div_a_img = document.createElement("img");
//	li_div_a_img.src = userimg;
//	li_div_a_img.setAttribute("width","145px");
//	li_div_a_img.setAttribute("height","199px");
//	li_div_a_img.setAttribute("onload","sImage(this,145,199)");
//	li_div_a.appendChild(li_div_a_img);
	$("#user"+userid).html("<img src=\""+userimg+"\"/>");
	
	var li_p = document.createElement("p");
	li.appendChild(li_p);
	
	var li_p_a = document.createElement("a");
	li_p_a.target="_blank";
	li_p_a.href= "user/"+userid+".html";
	li_p_a.innerHTML = nick;
	li_p.appendChild(li_p_a);
	
	var li_span = document.createElement("span");
	li_span.innerHTML = "参与了"+count+"个话题讨论";
	li.appendChild(li_span);
}
/**
 * 
 * @param userid 用户ID
 * @param userimg 用户头像
 * @param nick 用户昵称
 * @param count 数量
 * @param num 类型
 */
function writeUserHTM2L(userid,userimg,nick,count,num)
{
	var html = "<li>"+
                 "<a target=\"_blank\" href=\"user/"+userid+".html\"><img width='50px' height='50px' src=\""+userimg+"\"  /></a>"+
                 "<div class=\"sub_mav1\">"+
                     "<p><a target=\"_blank\" href=\"user/"+userid+".html\">"+nick+"</a></p>"+
                     "<span>参与了"+count+"个话题讨论</span>"+
                 "</div>"+
             "</li>";
	if(num%2==0)
	{
		$("#hostUser1").append(html);
	}
	if(num%2!=0)
	{
		$("#hostUser2").append(html);
	}
}
/**
 * 切换视图显示
 * @param num
 */
function isdisplay(num)
{
	if(num==1)
	{
		$("#subtop_lun").css("display","block");
		$("#subtop_mav").css("display","none");
	}
	if(num==2)
	{
		$("#subtop_lun").css("display","none");
		$("#subtop_mav").css("display","block");
	}
	
}