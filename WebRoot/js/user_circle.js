function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
//var userid = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var userid = realurl.substring(idindexof + 1);
userCircle(18,1,1);
function userCircle(rows,pageNumber,ttp)
{
	var circleURL = "centerAction_userAddCircle.do";
	$("#loading").html("<div class=\"profile_page\" align=\"center\"><img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" /></div>");
	$.ajax({
		url:circleURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp,id : userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#loading").html("");
			$("#my_create_circle").html("");
			var cir = data.data.add_circles;
			if(cir.length<=0)
			{
				$("#my_create_circle").html("<div><h1>该用户还没有加入圈子..</h1></div>");
			}
			for(var i =0;i<cir.length;i++)
			{
				var strcirclename = cir[i].circleInfo.circlename;
				var id = cir[i].circleInfo.circleid;
				if(strcirclename.length>6)
				{
					strcirclename = strcirclename.substring(0,6);
					strcirclename = strcirclename+"...";
				}
				var cid = cir[i].circleid;
				var ucid = cir[i].ucid;
				
				var result="" +
				"<div id="+"btn"+ucid+" class=\"prof_mine_t\"  >" +
				"<a id=\"proAon\" target=\"_blank\" href=\"circle/"+cid+"\" onmouseover=\"onProMinMouseOver(this,'xs"+ucid+"','"+ucid+"y')\" onmouseout=\"oProMinMouseOut(this,'xs"+ucid+"','"+ucid+"y')\" >" +
					"<img src=\""+cir[i].circleInfo.circlesmallimg+"\"/><div id=\"xs"+ucid+"\" class=\"pro_yr\"><p>"+strcirclename+"</p></div><div id=\""+ucid+"y\" style=\"display: none;\" class=\"pro_dlelt\" >" +
					"</div>" +
				"</a>" +
				"</div>";
				$("#my_create_circle").append(result);
			}
			var count = data.data.count;
			if(count > 18){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',1,ttp);
			}else{
				$("#pl_page").html("");
			}
		},
		error:function(data)
		{
			$("#my_create_circle").html("<h1>数据加载失败，请重新刷新页面试试...</h1>");
			$("#loading").html("");
		}
	});
}
function flipPage(rows,pageNumber,id,ttp) {
	userCircle(rows,pageNumber,id,ttp);
}

//circle(userid);
//function circle(userid)
//{
//	var circleURL = "centerAction_userAddCircle.do";
//	$("#loading").html("<div class=\"profile_page\" align=\"center\"><img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" /></div>");
//	$.ajax({
//		url:circleURL,
//		data:{id : userid},
//		type:"POST",
//		dataType:"json",
//		success:function(data)
//		{
//			$("#loading").html("");
//			var cir = data.data.add_circles;
//			if(cir.length<=0)
//			{
//				$("#my_create_circle").html("<div><h1>该用户还没有加入圈子..</h1></div>");
//			}
//			for(var i =0;i<cir.length;i++)
//			{
//				user_circle(cir[i].circleid);
//			}
//		},
//		error:function(data)
//		{
//			$("#my_create_circle").html("");
//			$("#loading").html("圈子加载失败...");
//		}
//	});
//}
//
//function user_circle(id)
//{
//	var circleURL = "circleDetail_getCircle.do";
//	$("#loading").html("<div class=\"profile_page\" align=\"center\"><img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" /></div>");
//	$.ajax({
//		url:circleURL,
//		data:{id : id},
//		type:"POST",
//		dataType:"json",
//		success:function(data)
//		{
//			$("#loading").html("");
//			var cir = data.data.center_circleInfo;
//			var strcirclename = cir.circlename;
//			if(strcirclename.length>6)
//			{
//				strcirclename = strcirclename.substring(0,6);
//				strcirclename = strcirclename+"...";
//			}
//			$("#my_create_circle").append("" +
//			"<div class=\"prof_mine_t\" id=\"div"+id+"\">" +
//				"<a target=\"_blank\" id=\"proAon\" href=\"circle/"+id+"\"  onmouseover=\"onProMinMouseOver(this, '"+id+"')\" onmouseout=\"oProMinMouseOut(this, '"+id+"')\">" +
//					"<img src=\""+cir.circlemiddleimg+"\"/>" +
//					"<div id=\""+id+"\" class=\"pro_yr\">" +
//					"<p>"+strcirclename+"</p>" +
//					"</div>" +
//				"</a>" +
//			"</div>");
//		},
//		error:function(data)
//		{
//			$("#my_create_circle").html("");
//			$("#loading").html("圈子加载失败...");
//		}
//	});
//}

