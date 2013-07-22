//一个话题的所有的评论
function showComment(rows,pageNumber,ttp,did)
{
	var commentsURL = "circleDetails_getTopicComment.do";
	$.ajax({
		url:commentsURL,
		data:{pageNum : pageNumber, pageSize : rows, type : ttp,id : did },
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			if(ttp == 1){
				$("#detailComment").html("");
			}
			$("#morecomment").hide();
			var comments = data.data.circleCommentInfos;
			for(var k=0;k<comments.length;k++)
			{
				var userHead = comments[k].userinfo.image;
				if(userHead.length<2)
				{
					userHead = comments[k].userinfo.otheraccountuserimage;
				}
				var str = "" +
					"<dl>" +
						"<dt>" +
							"<a href=\"user/"+comments[k].userinfo.userid+".html\"><img src=\""+userHead+"\"  width=\"50px\" height=\"50px\"/></a>" +
						"</dt>" +
						"<dd>" +
							"<a href=\"user/"+comments[k].userinfo.userid+".html\">"+comments[k].userinfo.nick+"</a>发表于  "+comments[k].createdate+"" +
							"<br />" +
							""+comments[k].commentinfo+"" +
						"</dd>" +
					"</dl>";
				$("#detailComment").append(str);
			}
			var count = data.data.commentCountById;
			if(ttp == 2){
//				alert(comments.length);
				if(comments.length >= 10){
					var n = pageNumber+1;
					$("#morecomment").html("<a href=\"javascript:showComment("+rows+","+n+","+2+",'"+did+"');\">加载更多</a>");
					$("#morecomment").show();
				}
			}else{
				if(count > 10){
					var n = pageNumber+1;
					$("#morecomment").html("<a href=\"javascript:showComment("+rows+","+n+","+2+",'"+did+"');\">加载更多</a>");
					$("#morecomment").show();
				}
			}
		},
		error:function(data)
		{
			$("#detailComment").html("<h4>数据加载失败，请重新刷新页面试试...</h4>");
		}
	});
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
		$("#window_dl").css("display","block");
		return;
	}
	var contents = $("#commentValue").val();//评论的内容
	
	if(contents.length <= 0)
	{
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>评论内容不能为空..</font></b></h3>");
		return;
	}
	if(contents.length >= 200)
	{
		$("#msg").html("&nbsp;&nbsp;<h3><b><font color='#FF0000'>评论内容过长..</font></b></h3>");
		return;
	}
	$("#msg").html("");
//	alert(contents);
//	return;
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
				showComment(10,1,1,detailid);
//				showdd();
				$("#commentValue").val("");
			}
			if(result<1)
			{
				showComment(10,1,1,detailid);
//				showdd();
			}
		},
		error:function(){
			
		}
	}); 
}
//function showjiaru_tuichu()
//{
//	document.getElementById('jr_tuichu').style.display='block';
//	setTimeout("document.getElementById('jr_tuichu').style.display = 'none';", 2222);
//}
//function showdd()
//{
//	document.getElementById('commentsuccess').style.display='block';
//	setTimeout("document.getElementById('commentsuccess').style.display = 'none';", 2222);
//}
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
