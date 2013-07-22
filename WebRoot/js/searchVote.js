var urlinfo = window.location.href;//獲取url
var url = decodeURI(urlinfo)
var idindexof = url.lastIndexOf("=");
var str = url.substring(idindexof + 1);
ajaxSearchVote();
function ajaxSearchVote(){
	var earchVoteURL="myVoteInfoAction_getSearchVote.do";
	$.ajax({
		url : earchVoteURL,
		data : {pageNum : 0 , pageSize : 50,content:str},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var islogin = data.data.islogin;
//			$("#hotVote").html("");
			var hostVotes = data.data.hotMyvoteInfo;
			$("#keywords").val(str); 
//			for(var i =0;i<hostVotes.length;i++)
//			{
//				var title = hostVotes[i].title;
//				var voteId=hostVotes[i].voteId;
//				if(title.length>15)
//				{
//					title = title.substring(0,15);
//					title = title+"...";
//				}
//				var demo = "<div class=\"right_txt\"><div class=\"minr_img\"><label id='hotImg"+i+"'></label></div><p><a target=\"_blank\" target=\"_blank\" href=\"votes/"+hostVotes[i].voteId+"\">"+title+"</a></p></div>";
//				$("#hotVote").append(demo);
//				var imgList = hostVotes[i].myVoteInfoImage;
//				if(imgList!=null){
//					for(var j=0;j<imgList.length;j++){
//						if(j>=1){break;}
//						var imgAddress = imgList[0].imgAddress;
//						$("#hotImg"+i+"").html("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+"\"><img src=\""+ imgAddress + "\" width='240px'/></a>");
//					}
//				}
//			}
			
			var allVoteList = data.data.searchtitlelist;
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
				if(title.length>15)
				{
					title = title.substring(0,15);
					title = title+"...";
				}
				var show=" <div class=\"min-txt fillet\"> <div class=\"min_img\" id=\"showImage"+i+"\"></div>"
					+"<h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1>"
		            +"<div class=\"min_l\">" 
		            +"<div class=\"min_yen\"><a><span>&yen;</span><em>"+price+"-"+pricess+"</em></a></div></div>"
		            +"<div class=\"min_r\">"
		            +"<div class=\"min_xiang\"><a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\">了解详细</a></div> </div>"
		            +"<div class=\"min_bottom\" id=\"comment\">" 
		            +"<a>喜欢:&nbsp;<span style=\"color:#f78d00;\">"+love+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a>想买:&nbsp;<span style=\"color:#f78d00;\">"+buy+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a>评论:&nbsp;<span style=\"color:#f78d00;\">"+commentcount+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		            +"</div></div>";
				$("#showDiv").append(show);
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
			
			var searchsummarylist = data.data.searchsummarylist;
			if(searchsummarylist.length<=0 && allVoteList.length<=0)
			{
				$("#showDiv").append("没有你搜索的投票信息！");
			}
			for(var i=0;i<searchsummarylist.length;i++){
				//该投票的id
				var voteId=searchsummarylist[i].voteId;
				//该投票商品的价钱
				var price=searchsummarylist[i].price;
				var pricess=searchsummarylist[i].pricess;
				//该投票商品的描述
				var title=searchsummarylist[i].title;
				var love=searchsummarylist[i].love;
				var buy=searchsummarylist[i].buy;
				var commentcount=searchsummarylist[i].commentcount;
				if(title.length>15)
				{
					title = title.substring(0,15);
					title = title+"...";
				}
				var show=" <div class=\"min-txt fillet\"> <div class=\"min_img\" id=\"showImage"+i+"\"></div>"
					+"<h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1>"
		            +"<div class=\"min_l\">" 
		            +"<div class=\"min_yen\"><a><span>&yen;</span><em>"+price+"-"+pricess+"</em></a></div></div>"
		            +"<div class=\"min_r\">"
		            +"<div class=\"min_xiang\"><a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+".html\">了解详细</a></div> </div>"
		            +"<div class=\"min_bottom\" id=\"comment\">" 
		            +"<a>喜欢:&nbsp;<span style=\"color:#f78d00;\">"+love+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a>想买:&nbsp;<span style=\"color:#f78d00;\">"+buy+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
					+"<a>评论:&nbsp;<span style=\"color:#f78d00;\">"+commentcount+"</span></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
		            +"</div></div>";
				$("#showDiv").append(show);
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
$("#keywords").val(str);