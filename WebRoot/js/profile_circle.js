myAddCircles(18,1,1);
function myAddCircles(rows,pageNumber,ttp)
{
	var circleURL = "centerAction_myAddCircle.do";
	$.ajax({
		url:circleURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#loading").html("");
			$("#my_create_circle").html("");
			var cir = data.data.add_circles;
			for(var i =0;i<cir.length;i++)
			{
				var cname = cir[i].circleInfo.circlename;
				var cid = cir[i].circleInfo.circleid;
				if(cname.length>6)
				{
					cname = cname.substring(0,6);
					cname = cname+"...";
				}
				var cid = cir[i].circleid;
				var ucid = cir[i].ucid;
				var result="" +
				"<div id="+"btn"+ucid+" class=\"prof_mine_t\"  >" +
				"<a id=\"proAon\" target=\"_blank\" href=\"circle/"+cid+"\" onmouseover=\"onProMinMouseOver(this,'xs"+ucid+"','"+ucid+"y')\" onmouseout=\"oProMinMouseOut(this,'xs"+ucid+"','"+ucid+"y')\" >" +
					"<img src=\""+cir[i].circleInfo.circlesmallimg+"\"/><div id=\"xs"+ucid+"\" class=\"pro_yr\"><p>"+cname+"</p></div><div id=\""+ucid+"y\" style=\"display: none;\" class=\"pro_dlelt\" >" +
					"</div>" +
				"</a>" +
				"</div>";
				$("#my_create_circle").append(result);
			}
			var count = data.data.count;
			if(count<1)
			{
				$("#my_create_circle").html("<div><h1>你还没有加入圈子..</h1></div>");
			}
			if(count >18){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',1,ttp);
			}else{
				$("#pl_page").html("");
			}
			
		},
		error:function(data)
		{
		}
	});
}
function flipPage(rows,pageNumber,id,ttp) {
	myAddCircles(rows,pageNumber,id,ttp);
}