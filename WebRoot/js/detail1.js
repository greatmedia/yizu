//function getQueryStringRegExp(name)
//{
//	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
//	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
//}
//var did = getQueryStringRegExp("pid");
//var id = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var ccID = realurl.substring(realurl.search("det/")+4,idindexof);
var did = realurl.substring(idindexof + 1,realurl.search(".html"));
var islogin ;
var userId;

var detail;
//某一个话题的信息
function details(did)
{
	var detailURL = "circleDetails_getDetail.do";
	$.ajax({
		url:detailURL,
		data:{id : did},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#details_cont").html("");
			var details = data.data.circleDetailInfo;
			detail = eval(details);
			document.title=detail.title;
			userId = detail.userid;
//			$("#join_d").html("<img src=\"images/jr_btna.gif\" onclick=\"join_or_quit_quanzi('"+ccID+"',1)\" id=\"join\" style=\"display: block;\" />");
//			$("#fa").html("<img src=\"images/btn_02.gif\" id=\"fabu\" onclick=\"window.open('"+basePath+"circleInfoAction_circleInfoPublish.do?id="+ccID+".html')\" style=\"display: none;\"/>");
//			$("#qui").html("<img src=\"images/btn_01.gif\" id=\"quit\" onclick=\"join_or_quit_quanzi('"+ccID+"',2)\" style=\"display: none;\"/>");
			var details_cont = "" +
			"<div class=\"d_title\">"+detail.title+"</div>" +//标题
			"<div class=\"d_chuchu\"></div>" +
			"<div class=\"clearfloat\"></div>" +
			"<div class=\"d_txt\">"+detail.circlecontent+"</div>" +//内容
			"<div class=\"d_img\" id='detailImgs'></div>" +//所有的图片
				"<div class=\"clearfloat\"><label style=\"display:none\" id=\"commentsuccess\"></label></div>" +
				"<div class=\"enjoy\"><a href=\"javascript:void(0)\">" +
					"<font color=\"#e67300\" size=\"+1\" id='commentCount'></font>条评论</a>"+"<div class=\"d_fenxiang\">" +
				"<div id=\"ckepop\">" +
				"<span id=\"broadreceiver\" class=\"jiathis_txt\">分享到：</span>" +
				"<div id=\"ckepop\">" +
				"<a class=\"jiathis_button_qzone\"></a>" +
				"<a class=\"jiathis_button_tsina\"></a>" +
				"<a class=\"jiathis_button_tqq\"></a>" +
				"<a class=\"jiathis_button_renren\"></a>" +
				"<a class=\"jiathis_button_kaixin001\"></a>" +
				"<a class=\"jiathis_button_t163\"></a>" +
				"<a href=\"http://www.jiathis.com/share?uid=1639347\" class=\"jiathis jiathis_txt jtico jtico_jiathis\" target=\"_blank\"></a>" +
//				"<a class=\"jiathis_counter_style\"></a>" +
				
				"</div>" +
				"<script type=\"text/javascript\" src=\"http://v3.jiathis.com/code/jia.js?uid=1338348908887337\" charset=\"utf-8\"></script>"+
				"</div>"+
				"</div>" +
			"<div class=\"d_pl_cont\">" +
				"<div class=\"pl_top\"></div>" +
					"<div class=\"pl_cont\">" +
					"<div>" +
					"<input type=\"text\" class=\"pl_input\" id=\"commentValue\" value=\"\"/></div>" +
					"<ul class=\"d_pl\">" +
					"<li class=\"lp_bq\"></li>" +
					"<li class=\"pl_btn\"><a href='javascript:void(0);'><img src=\"images/sub_pingl.gif\" onclick=\"subjectContent('"+detail.circledetailid+"','"+userId+"');\" /></a></li>" +
					"<li id=\"msg\"></li></ul>" +
					"<label id='detailComment'></label>" +
				"</div>" +
			"</div>" +
			"</div>";
			$("#details_cont").html(details_cont);
			
			
			
			$("#detailImgs").html("");
			//一个话题的所有的图片信息
			var detailImgs = detail.circleDetailImg; 
			var picPath="";
			for(var i =0;i<detailImgs.length;i++)
			{
//				picPath += detailImgs[i].bigimg+"\":\"";
				picPath = detailImgs[0].bigimg;
				$("#detailImgs").append("<div id=\"box"+i+"\"></div>");
				var img=document.createElement('img');//创建一个img元素
				img.src=""+detailImgs[i].bigimg+"";//指定src
				img.style.position="absolute";//防止正常的内容变形
				img.style.visibility='hidden';//藏起来
				var inj=document.getElementById("box"+i+"").appendChild(img);//插入到box中。当然插入到document.body也可以
				//然后就可以通过 offset 取得宽和高了
				if(inj.offsetWidth>590){
					$("#detailImgs").append("<a style=\"margin-bottom:5px;\" href='javascript:void(0)'><img src=\""+detailImgs[i].bigimg+"\"  width=\"790px\" /></a><br>");
				}else{
					$("#detailImgs").append("<a style=\"margin-bottom:5px;\" href='javascript:void(0)'><img src=\""+detailImgs[i].bigimg+"\" /></a><br>");
				}
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
			$("#detailImgs").append(str);
			$("#details_cont").append("<div class=\"pl_page\" id=\"pl_page\"></div>");
			showComment(10,1,1);
		},
		error:function(data){
		}
	});
}
//一个话题的所有的评论
function showComment(rows,pageNumber,ttp)
{
	var commentsURL = "circleDetails_getTopicComment.do";
	$.ajax({
		url:commentsURL,
		data:{pageNum : pageNumber, pageSize : rows, type : ttp,id : did },
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#detailComment").html("");
			var countComment = data.data.commentCountById;
//			alert(countComment);
			$("#commentCount").html(countComment);
			var comments = data.data.circleCommentInfos;
			for(var k=0;k<comments.length;k++)
			{
				var userHead = comments[k].userinfo.image;
				if(userHead.length<2)
				{
					userHead = comments[k].userinfo.otheraccountuserimage;
				}
				var str = "<div class=\"pl_txt\" id=\"txt"+k+"\">" +
						"<div class=\"u_ico\" id=\"user_a"+k+"\"><label id=\"user_head"+k+"\"></label></div>" +
						"<div class=\"u_name1\" id=\"u_name"+k+"\"><p>"+comments[k].userinfo.nick+"</p><span>"+comments[k].createdate+"</span></div>" +
						"<div class=\"u_txt\"><p>"+comments[k].commentinfo+"</p></div>" +
						"</div>";
				$("#detailComment").append(str);
//				$("#detailComment").append("<div class=\"pl_txt\" id=\"txt"+k+"\">");
//				$("#detailComment").append("<div class=\"u_ico\" id=\"user_a"+k+"\"><label id=\"user_head"+k+"\"></label></div>");
//				$("#detailComment").append("<div class=\"u_name1\" id=\"u_name"+k+"\"><p>"+comments[k].userinfo.nick+"</p><span>"+comments[k].createdate+"</span></div>");
//				$("#detailComment").append("<div class=\"u_txt\"><p>"+comments[k].commentinfo+"</p></div>");
//				$("#detailComment").append("</div>");
				$("#user_head"+k+"").html("<a target=\"_blank\" href=\"user/"+comments[k].userinfo.userid+".html\"><img src=\""+userHead+"\" width=\"30px\" height=\"30px\" /></a>");
			}
			var count = data.data.commentCountById;
			if(count > 10){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',1,ttp);
			}else{
				$("#pl_page").html("");
			}
		},
		error:function(data)
		{
			$("#detailComment").html("<h4>数据加载失败，请重新刷新页面试试...</h4>");
		}
	});
}
function flipPage(rows,pageNumber,id,ttp) {
	showComment(rows,pageNumber,id,ttp);
}
//统计发言和成员
function count(id)
{
	var spackCountURL = "circleDetails_count.do?id="+id;
	$.ajax({
		url:spackCountURL,
		data:{id : $("#id").val()},
		type:"POST",
		dataType:"json",
		success:function(data)
		{ 
			var count_detail = data.data.count_details;
			$("#visitcount").html(count_detail);
			var count_pro  = data.data.count_per;
			$("#joincount").html(count_pro);
		},
		error:function(data)
		{
		}
	});
}

//提交评论
function subjectContent(detailid,userid)
{
	islogin = document.getElementById("islogin").value;
	if(islogin=='not')
	{
		alert("您还没有登录，登录之后才能评论..");
		myScroll();
		$("#window_dl").css("display","block");
		return false;
	}
	var contents = $("#commentValue").val();//评论的内容
	if(contents.length <= 0)
	{
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>评论内容不能为空..</font></b></h3>");
		return false;
	}
	if(contents.length >= 200)
	{
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>评论内容过长..</font></b></h3>");
		return false;
	}
	$("#msg").html("");
	var commentURL =  "circleDetails_insertComment.do?id="+detailid;
	$.ajax({  
		url:commentURL,
		data:{content : contents,userid:userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#commentValue").val("");
			$("#msg").html("");
			var result = data.data.result;
			if(result>=1)
			{
				showComment(10,1,1);
				showdd();
				$("#commentValue").val("");
			}
			if(result<1)
			{
				showComment(10,1,1);
				showdd();
			}
		},
		error:function(){
			
		}
	}); 
}
function showjiaru_tuichu()
{
	document.getElementById('jr_tuichu').style.display='block';
	setTimeout("document.getElementById('jr_tuichu').style.display = 'none';", 2222);
}
function showdd()
{
	document.getElementById('commentsuccess').style.display='block';
	setTimeout("document.getElementById('commentsuccess').style.display = 'none';", 2222);
}
function showTopic()
{
	var imgURL = "circleDetails_topicDetais.do?id="+did;
	$.ajax({
		url:imgURL,
		data:{id : $("#id").val()},
		type:"POST",
		dataType:"json",
		success:function(data)
		{ 
			var topicDetail = data.data.topicDetail;
		},
		error:function(data)
		{
		}
	});
}
