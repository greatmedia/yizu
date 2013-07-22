//function getQueryStringRegExp(name)
//{
//	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
//	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
//}
//var userid = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var userid = realurl.substring(idindexof + 1);
userVote(18,1,1);
var vote,index,islogin,ids;
function userVote(rows,pageNumber,ttp)
{
	var user_voteURL= "centerAction_userVote.do";
	$.ajax({
		url:user_voteURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp,id : userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#loading").html("");
			$("#my_vote").html("");
			var votes = data.data.myVoteInfos;
			vote = eval(votes);
			if(vote.length<=0)
			{	
				$("#my_vote").append("<div><h1>你还没有创建投票...</h1></div>");
			}else{
			for(var i=0;i<vote.length;i++){
				index = i;
				var votetitle = vote[i].title;
				if(votetitle.length>6)
				{
					votetitle = votetitle.substring(0,6);
					votetitle = votetitle+"...";
				}
				var result="" +
				"<div id="+"btn"+vote[i].voteId+" class=\"prof_mine_t\"  >" +
				"<a target=\"_blank\" href=\"votes/"+vote[i].voteId+"\" id=\"proAon\" onmouseover=\"onProMinMouseOver(this,'"+vote[i].voteId+"','"+vote[i].voteId+"y')\" onmouseout=\"oProMinMouseOut(this,'"+vote[i].voteId+"','"+vote[i].voteId+"y')\" >" +
					"<img id=\"vote"+vote[i].voteId+"\" /><div id=\""+vote[i].voteId+"\" class=\"pro_yr\"><p>"+votetitle+"</p></div><div id=\""+vote[i].voteId+"y\" style=\"display: none;\" class=\"pro_dlelt\" >" +
					"<div class=\"pro_dlelt_left\"  >" +
						"<input id=\"btn"+vote[i].voteId+"\" type=\"button\" onclick=\"deleteById('"+vote[i].voteId+"','"+vote[i].title+"')\" value=\"删除\"/></div>" +
						"<div class=\"pro_dlelt_right\"></div>" +
					"</div>" +
				"</a>" +
				"</div>";
				$("#my_vote").append(result);
				var vid =  vote[i].voteId;
				showVoteImg(vid,i);
			}}
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
			$("#loading").html("");
			$("#my_vote").html("<h1>数据加载失败，请重新刷新页面试试...</h1>");
		}
	});
}
function showVoteImg(vid,i)
{
	var v = vote[index];
	var vimg = v.myVoteInfoImage;
	for(var j=0;j<vimg.length;j++)
	{
		$("#vote"+vid).attr("src",vimg[0].imgAddress);
		$("#vote"+vid).attr("width","125");
	}
}
function flipPage(rows,pageNumber,id,ttp) {
	userVote(rows,pageNumber,id,ttp);
}