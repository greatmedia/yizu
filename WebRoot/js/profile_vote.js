myCreateVote(18,1,1);
var index,islogin,ids,vote;
function myCreateVote(rows,pageNumber,ttp)
{
	var my_voteURL= "centerAction_myCreateVote.do";
	$.ajax({
		url:my_voteURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#loading").html("");
			$("#my_vote").html("");
			var votes = data.data.myVoteInfos;
			if(votes.length<=0)
			{	
				$("#my_vote").append("<div><h1>你还没有创建投票...</h1></div>");
			}else{
			vote = eval(votes);
			for(var i=0;i<vote.length;i++){
				index = i;
				var votetitle = vote[i].title;
				if(votetitle.length>6)
				{
					votetitle = votetitle.substring(0,6);
					votetitle = votetitle+"...";
				}
				var vid =  vote[i].voteId;
				$("#my_vote").append("" +
				"<div class=\"prof_mine_t\" id=\"div"+vid+"\">" +
					"<a target=\"_blank\" id=\"proAon\" href=\"votes/"+vid+"\"  onmouseover=\"onProMinMouseOver(this, '"+vid+"')\" onmouseout=\"oProMinMouseOut(this, '"+vid+"')\">" +
						"<img id=\"vote"+vid+"\"/>" +
						"<div id=\""+vid+"\" class=\"pro_yr\">" +
						"<p>"+votetitle+"</p>" +
						"</div>" +
					"</a>" +
				"</div>");
				showVoteImg(vid,i);
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
			$("#loading").html("");
			$("#my_vote").html("<h1>数据加载失败，请重新刷新页面试试...</h1>");
		}
	});
}
function showVoteImg(vid,i)
{
	var v = vote[index];
	var vimg = v.myVoteInfoImage;
	for(var j =0;j<vimg.length;j++)
	{
		$("#vote"+vid).attr("src",vimg[0].imgAddress);
		$("#vote"+vid).attr("width","125");
	}
}
function flipPage(rows,pageNumber,id,ttp) {
	myCreateVote(rows,pageNumber,id,ttp);
}