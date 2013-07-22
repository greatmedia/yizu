$(function(){
	getAll(8,1,1);
});

var voteType;
var voteid,index,islogin,ids;
var user;
//var allVoteList;

//所有投票
function getAll(rows,pageNumber,ttp){
	voteType = 1;
	$("#allVote").removeClass();
	$("#allVote").addClass("center_tag");
	$("#voting").removeClass();
	$("#voting").addClass("center_1");
//	$("#beforevote").removeClass();
//	$("#beforevote").addClass("center_1");
//	$("#endvote").removeClass();
//	$("#endvote").addClass("center_1");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
 			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
 			 +"</div>");
	var AllVoteURL="myVoteInfoAction_getAllVote.do";
	$.ajax({
		url : AllVoteURL,
		data : {pageNum : pageNumber , pageSize : rows,type : ttp},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#showDiv").html("");
			var allVoteList=data.data.voteList;
			var count = data.data.count;
			user=data.data.user;
			if(count > 8){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
			}else{
				$("#pl_page").html("");
			}
			if(count<=0)
			{
				$("#main").css("display","none");
				$("#left").css("display","block").css({"text-align":"center",width:"100%",height:"100%" ,"padding-top":"200px","min-height":"800px"}).html("<br \/><a href='javascript:void()' onclick='isLogin()'><h2>还没有投票，赶快点此创建吧!</h2></a>");
			}else{
				$("#left").html("");
			}
			for(var i=0;i<allVoteList.length;i=i+1){
				//该投票的id
				var voteId=allVoteList[i].voteId;
				//该投票商品的价钱
				var price=allVoteList[i].price;
				var pricess=allVoteList[i].pricess;
				//该投票商品的描述
				var title=allVoteList[i].title;
				var love=allVoteList[i].love;
				var buy=allVoteList[i].buy;
				var commentcount=allVoteList[i].commentcount;
				var show2 = 
					"<div class=\"min_middle\">" +
						"<div class=\"s_pic\">" +
							"<div class=\"s_pic_left\">" +
								"<p><h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1></p>" +
								"<p><strong>¥"+price+"-"+pricess+"</strong></p>" +
								"<p><a target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\"../images/vote101.jpg\" /></a></p>" +
								"<p>喜欢<span>"+love+"</span>想买<span>"+buy+"</span></p>" +
//								评论<span>"+commentcount+"</span>
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
				$("#showDiv").append(show2);
				//获取该投票的图片
				var imgList=allVoteList[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress=imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\""+ imgAddress + "\" /></a>");
					}
				}
			}
			hotVote();
		},
		error : function(data) {
		}
	});
}
//用来得到进行中的投票
function getVoting(rows,pageNumber,ttp){
	voteType = 2;
	$("#allVote").removeClass();
	$("#allVote").addClass("center_1");
	$("#voting").removeClass();
	$("#voting").addClass("center_tag");
//	$("#beforevote").removeClass();
//	$("#beforevote").addClass("center_1");
//	$("#endvote").removeClass();
//	$("#endvote").addClass("center_1");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
	           			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
	           			 +"</div>");
	var AllVoteURL="myVoteInfoAction_getVoting.do";
	$.ajax({
		url : AllVoteURL,
		data : {pageNum : pageNumber , pageSize : rows,type : ttp},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#showDiv").html("");
			var allVoteList=data.data.voteList;
			user=data.data.user;
			var count = data.data.count;
			if(count > 8){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
			}else{
				$("#pl_page").html("");
			}
			if(count<=0)
			{
				$("#showDiv").html("<br /><h2>没有正在进行的投票</h2>");
			}
			for(var i=0;i<allVoteList.length;i++){
				//该投票的id
				var voteId=allVoteList[i].voteId;
				//该投票商品的价钱
				var price=allVoteList[i].price;
				var pricess=allVoteList[i].pricess;
				//该投票商品的描述
				var title=allVoteList[i].title;
				
				var love=allVoteList[i].love;
				var buy=allVoteList[i].buy;
				var commentcount=allVoteList[i].commentcount;
				var show2 = 
					"<div class=\"min_middle\">" +
						"<div class=\"s_pic\">" +
							"<div class=\"s_pic_left\">" +
								"<p><h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1></p>" +
								"<p><strong>¥"+price+"-"+pricess+"</strong></p>" +
								"<p><a target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\"../images/vote101.jpg\" /></a></p>" +
								"<p>喜欢<span>"+love+"</span>想买<span>"+buy+"</span>评论<span>"+commentcount+"</span></p>" +
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
				$("#showDiv").append(show2);
				//获取该投票的图片
				var imgList=allVoteList[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress=imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\""+ imgAddress + "\" width='306px'/></a>");
					}
				}
			}
		},
		error : function(data) {
		}
	});
}

//用来得到即将开始的投票
function getBeforeVoting(rows,pageNumber,ttp){
	voteType = 3;
	$("#allVote").removeClass();
	$("#allVote").addClass("center_1");
	$("#voting").removeClass();
	$("#voting").addClass("center_1");
//	$("#beforevote").removeClass();
//	$("#beforevote").addClass("center_tag");
//	$("#endvote").removeClass();
//	$("#endvote").addClass("center_1");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
  			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
  			 +"</div>");
	var beforeVotingURL="myVoteInfoAction_getBeforeVoting.do";
	$.ajax({
		url : beforeVotingURL,
		data : {pageNum : pageNumber , pageSize : rows,type : ttp},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#showDiv").html("");
			var beforeVotingList=data.data.voteList;
			var count = data.data.count;
			if(count > 8){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
			}else{
				$("#pl_page").html("");
			}
			if(count<=0)
			{
				$("#showDiv").html("<br /><h2>没有即将开始的投票</h2>");
			}
			for(var i=0;i<beforeVotingList.length;i++){
				//该投票的id
				var voteId=beforeVotingList[i].voteId;
				//该投票商品的价钱
				var price=beforeVotingList[i].price;
				var pricess=beforeVotingList[i].pricess;
				//该投票商品的描述
				var title=beforeVotingList[i].title;
				
				var love1=beforeVotingList[i].love;
				var buy1=beforeVotingList[i].buy;
				var commentcount1=beforeVotingList[i].commentcount;
				
				var show2 = 
					"<div class=\"min_middle\">" +
						"<div class=\"s_pic\">" +
							"<div class=\"s_pic_left\">" +
								"<p><h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1></p>" +
								"<p><strong>¥"+price+"-"+pricess+"</strong></p>" +
								"<p><a target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\"../images/vote101.jpg\" /></a></p>" +
								"<p>喜欢<span>"+love+"</span>想买<span>"+buy+"</span>评论<span>"+commentcount+"</span></p>" +
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
				$("#showDiv").append(show2);
				//获取该投票的图片
				var imgList=beforeVotingList[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress=imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\""+ imgAddress + "\" width='306px'/></a>");
					}
				}
			}
		},
		error : function(data) {
		}
	});
}
//得到已经结束的投票
function getEndVote(rows,pageNumber,ttp){
	voteType = 4;
	$("#allVote").removeClass();
	$("#allVote").addClass("center_1");
	$("#voting").removeClass();
	$("#voting").addClass("center_1");
//	$("#beforevote").removeClass();
//	$("#beforevote").addClass("center_1");
//	$("#endvote").removeClass();
//	$("#endvote").addClass("center_tag");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
			 +"</div>");
	var endVoteURL="myVoteInfoAction_getEndVote.do";
	$.ajax({
		url : endVoteURL,
		data : {pageNum : pageNumber , pageSize : rows,type : ttp},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#showDiv").html("");
			var allVoteList=data.data.voteList;
			var count = data.data.count;
			if(count > 8){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
			}else{
				$("#pl_page").html("");
			}
			if(count<=0)
			{
				$("#showDiv").html("<br /><h2>没有已经结束的投票</h2>");
			}
			for(var i=0;i<allVoteList.length;i=i+1){
				//该投票的id
				var voteId=allVoteList[i].voteId;
				//该投票商品的价钱
				var price=allVoteList[i].price;
				var pricess=allVoteList[i].pricess;
				//该投票商品的描述
				var title=allVoteList[i].title;
				var love=allVoteList[i].love;
				var buy=allVoteList[i].buy;
				var commentcount=allVoteList[i].commentcount;
				var show2 = 
					"<div class=\"min_middle\">" +
						"<div class=\"s_pic\">" +
							"<div class=\"s_pic_left\">" +
								"<p><h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1></p>" +
								"<p><strong>¥"+price+"-"+pricess+"</strong></p>" +
								"<p><a target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\"../images/vote101.jpg\" /></a></p>" +
								"<p>喜欢<span>"+love+"</span>想买<span>"+buy+"</span>评论<span>"+commentcount+"</span></p>" +
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
				$("#showDiv").append(show2);
				//获取该投票的图片
				var imgList=allVoteList[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress=imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\""+ imgAddress + "\" width='306px'/></a>");
					}
				}
			}
		},
		error : function(data) {
		}
	});
}
//所有投票的分页
function flipPage(rows,pageNumber,id,ttp) {
	if(voteType == 1){getAll(rows,pageNumber,id,ttp);}
	if(voteType == 2){getVoting(rows,pageNumber,id,ttp);}
	if(voteType == 3){getBeforeVoting(rows,pageNumber,id,ttp);}
	if(voteType == 4){getEndVote(rows,pageNumber,id,ttp);}
}
//热门投票
function hotVote()
{
	var voteURL = "myVoteInfoAction_hotVote.do";
	$.ajax({
		url : voteURL,
		type : 'POST',
		dataType : 'json',
		success : function(data) {
			$("#hotVote").html("");
			var hostVotes = data.data.hotMyvoteInfo;
			for(var i =0;i<hostVotes.length;i++)
			{
				var title = hostVotes[i].title;
				var voteId=hostVotes[i].voteId;
				if(title.length>15)
				{
					title = title.substring(0,15);
					title = title+"...";
				}
				var demo = "<div class=\"right_txt\"><div class=\"minr_img\"><label id='hotImg"+i+"'></label></div><p><a target=\"_blank\" target=\"_blank\" href=\"votes/"+hostVotes[i].voteId+".html\">"+title+"</a></p></div>";
				$("#hotVote").append(demo);
				var imgList = hostVotes[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress = imgList[0].imgAddress;
						$("#hotImg"+i+"").html("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\""+ imgAddress + "\" width='240px'/></a>");
					}
				}
			}
		},
		error : function(data) {
			$("#hotVote").html("");
		}
	});
}
