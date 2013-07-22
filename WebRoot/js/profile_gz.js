myGz(18,1,1);
var img;
function myGz(rows,pageNumber,ttp)
{
	var my_gzURL= "centerAction_myGz.do";
	$.ajax({
		url:my_gzURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			img = data.data.myVoteInfoImages;
			$("#loading").html("");
			$("#my_gz").html("");
			var gzs = data.data.joinVotes;
			if(gzs.length<=0)
			{	
				$("#my_gz").append("<div><h1>你还没有关注任何商品...</h1></div>");
			}else{
			for(var i=0;i<gzs.length;i++){
				var title = gzs[i].myVoteInfo.title;
				if(title.length>6)
				{
					title = title.substring(0,6);
					title = title+"...";
				}
				var gzid = gzs[i].voteId;
				$("#my_gz").append("" +
						"<div class=\"prof_mine_t\" id=\"div"+gzid+"\">" +
							"<a target=\"_blank\" id=\"proAon\" href=\"votes/"+gzid+"\"  onmouseover=\"onProMinMouseOver(this, '"+gzid+"')\" onmouseout=\"oProMinMouseOut(this, '"+gzid+"')\">" +
								"<img id=\"gz"+gzid+"\"/>" +
								"<div id=\""+gzid+"\" class=\"pro_yr\">" +
								"<p>"+title+"</p>" +
								"</div>" +
							"</a>" +
						"</div>");
				
				showVoteImg(gzid,i);
			}}
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
			$("#loading").html("");
			$("#my_gz").html("<h1>数据加载失败，请重新刷新页面试试...</h1>");
		}
	});
}
function showVoteImg(vid,i)
{
	for(var j=0;j<img.length;j++)
	{
		if(i==j)
		{	
			if(img[i][0]==null)
			{
				break;
			}else{
			$("#gz"+vid).attr("src",img[i][0].imgAddress);
			$("#gz"+vid).attr("width","125");
			}
		}
	}

}
function flipPage(rows,pageNumber,id,ttp) {
	myGz(rows,pageNumber,id,ttp);
}