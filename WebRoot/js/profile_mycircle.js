myCreateCircle(18,1,1);
function myCreateCircle(rows,pageNumber,ttp)
{
	var my_circleURL= "centerAction_myCreateCircle.do";
	$.ajax({
		url:my_circleURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#my_create_circle").html("");
			var cir = data.data.my_create_circle;
			if(cir.length<=0)
			{	
				$("#my_create_circle").append("<div><h1>你还没有创建圈子！</h1></div>");
			}else{
				
				for(var i=0;i<cir.length;i++){
					var strcirclename = cir[i].circlename;
					if(strcirclename.length>6)
					{
						strcirclename = strcirclename.substring(0,6);
						strcirclename = strcirclename+"...";
					}
					var cid = cir[i].circleid;
					var result="<div id="+"btn"+cid+" class=\"prof_mine_t\"  >" +
					"<a href=\"javascript:void(0);\" id=\"proAon\" onmouseover=\"onProMinMouseOver(this,'"+cid+"','"+cid+"y')\" onmouseout=\"oProMinMouseOut(this,'"+cid+"','"+cid+"y')\" >" +
						"<img src=\""+cir[i].circlemiddleimg+"\"/><div id=\""+cid+"\" class=\"pro_yr\"><p>"+strcirclename+"</p></div><div id=\""+cid+"y\" style=\"display: none;\" class=\"pro_dlelt\" >" +
						"<div class=\"pro_dlelt_left\"  >" +
							"<input id=\"btn"+cid+"\" type=\"button\" onclick=\"deleteById('"+cid+"','"+cir[i].circlename+"')\" value=\"删除\"/></div>" +
							"<div class=\"pro_dlelt_right\"><input id=\"btn"+cid+"\" type=\"button\" onclick=\"openEdit('"+cid+"');\" value=\"编辑\"/></div>" +
						"</div>" +
					"</a>" +
					"</div>";
					$("#my_create_circle").append(result);
				}
			}
			var count = data.data.count;
 			if(count >18){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',1,ttp);
			}else{
				$("#pl_page").html("");
			}
		},
		error:function(data)
		{
			$("#my_create_circle").html("<div><h1>数据加载失败，请重新刷新页面试试...</h1></div>");
		}
	});
}
function openEdit(cid)
{
	var editCircleURL = "circleInfoAction_update.do?id="+cid;
	window.open(basePath + editCircleURL); 
//	location.href=basePath+"circleInfoAction_update.do?id="+cid;
}
function deleteById(circleId,circleName)
{
	var flage=confirm("您确定要删除圈子?\n"+circleName);
	if(flage)
	{
		$.ajax({
			url:"centerAction_deleteById.do",
			data:{id : circleId},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
				var result = data.data.result;
				var islogin = data.data.islogin;
				if(!islogin)
				{
					alert(result);
					$("#btn"+circleId+"").remove();
					return false;
				}else{
					alert(result);
					$("#btn"+circleId+"").remove();
					return false;
				}
			},
			error:function(data)
			{
				$("#btn"+circleId+"").remove();
				alert("删除失败！");
			}
		});
	}
}
function showdd()
{
	document.getElementById('commentsuccess').style.display='block';
	setTimeout("document.getElementById('commentsuccess').style.display = 'none';", 3000);
}
function flipPage(rows,pageNumber,id,ttp) {
	myCreateCircle(rows,pageNumber,id,ttp);
}
