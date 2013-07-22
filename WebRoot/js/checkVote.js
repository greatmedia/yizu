$(function(){
	noThoughCheck(8,1,1);	
});
var voteType;
var voteid,index,islogin,ids;
var user;

function users(){
	var getUser="myVoteInfoAction_getUser.do";
	$.ajax({
		url : getUser,
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var result = data.data.result;
			if(result==1)
			{
				$("#login").css("display","none");
				$("#showall").css("display","block");
			}else{
				$("#login").css("display","block");
				$("#showall").css("display","none");
			}
		},
		error : function(data) {
			alert(data);
		}
	});
}

function loginSub(){
	var pwd=$("#pwd").val();
	if(pwd=="yizuadmin"){
		$("#login").css("display","none");
		$("#showall").css("display","block");
	}else{
		alert("您没有权限！");
	}
}
//所有投票
function getAllVote(rows,pageNumber,ttp){
	voteType = 1;
	$("#allDiv").removeClass();
	$("#allDiv").addClass("center_tag");
	$("#nocheck").removeClass();
	$("#nocheck").addClass("center_1");
	$("#checkThr").removeClass();
	$("#checkThr").addClass("center_1");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
 			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
 			 +"</div>");
	var AllVoteURL="myVoteInfoAction_getAllVoteCheck.do";
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
				$("#left").css({"text-align":"center",width:"100%",height:"100%" ,"padding-top":"200px","min-height":"800px"}).html("<br \/><a href='javascript:void()' onclick='isLogin()'><h2>还没有投票，赶快点此创建吧!</h2></a>");
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
								"<p>喜欢<span>"+love+"</span>想买<span>"+buy+"</span>评论<span>"+commentcount+"</span></p>" +
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
				$("#showDiv").append(show2);
				var imgList = allVoteList[i].myVoteInfoImage;
				//获取该投票的图片
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress=imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+"\"><img src=\""+ imgAddress + "\" width='306px'/></a>");
					}
				}
			}
		},
		error : function(data) {
		}
	});
}
//用来查询未通过审核的投票
function noThoughCheck(rows,pageNumber,ttp){
	voteType = 2;
	$("#allDiv").removeClass();
	$("#allDiv").addClass("center_1");
	$("#nocheck").removeClass();
	$("#nocheck").addClass("center_tag");
	$("#checkThr").removeClass();
	$("#checkThr").addClass("center_1");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
	           			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
	           			 +"</div>");
	var AllVoteURL="myVoteInfoAction_noThoughCheck.do";
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
				$("#showDiv").html("<br /><h2>没有未通过审核的投票</h2>");
			}
			for(var i=0;i<allVoteList.length;i++){
				//该投票的id
				var voteId=allVoteList[i].voteId;
				//该投票商品的价钱
				var price=allVoteList[i].price;
				var pricess= allVoteList[i].pricess;
				//该投票商品的描述
				var title=allVoteList[i].title;
				var show2 = 
					"<div class=\"min_middle\">" +
						"<div class=\"s_pic\">" +
							"<div class=\"s_pic_left\">" +
								"<p><h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1></p>" +
								"<p><strong>¥"+price+"-"+pricess+"</strong></p>" +
								"<p><a target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\"../images/vote101.jpg\" /></a></p>" +
								"<p id=\"comment"+voteId+"\"></p>" +
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
                
				$("#showDiv").append(show2);
//				$("#showDiv").append(show);
				//获取该投票的图片
				var imgList =  allVoteList[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){ break;}
						var imgAddress = imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+"\"><img src=\""+ imgAddress+ "\" width='306px'/></a>");
					}
				}
				var check="<a href=\"javascript:void(0);\"><input type=\"button\" value=\"通过审核\"  onclick=\"check('"+voteId+"')\"/></a>";
				$("#comment"+voteId+"").append(check);//.css("text-align","center");
			}
		},
		error : function(data) {
		}
	});
}
//审核投票
function check(voids){
	var checkVoteURL="myVoteInfoAction_checkVote.do";
	$.ajax({
		url : checkVoteURL,
		data : {id:voids},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var res = data.data.res;
			if(res==1){
				noThoughCheck(8,1,1);
			}else{
				alert(data.msg);
			}
		},
		error : function(data) {
			alert(data);
		}
	});
}
//用来得到通过审核的投票
function throughCheckVote(rows,pageNumber,ttp){
	voteType = 3;
	$("#allDiv").removeClass();
	$("#allDiv").addClass("center_1");
	$("#nocheck").removeClass();
	$("#nocheck").addClass("center_1");
	$("#checkThr").removeClass();
	$("#checkThr").addClass("center_tag");
	$("#showDiv").html(" <div align=\"center\" style=\"margin-left: 300px\">"
  			 +"<img src=\"images/loading.gif\" alt=\"数据加载中请稍等...\" />数据加载中请稍等...</div>"
  			 +"</div>");
	var beforeVotingURL="myVoteInfoAction_getAllVote.do";
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
				$("#showDiv").html("<br /><h2>没有通过审核的投票</h2>");
			}
			for(var i=0;i<beforeVotingList.length;i++){
				//该投票的id
				var voteId=beforeVotingList[i].voteId;
				//该投票商品的价钱
				var price=beforeVotingList[i].price;
				var pricess=beforeVotingList[i].pricess;
				//该投票商品的描述
				var title=beforeVotingList[i].title;
				
				var show=" <div class=\"min-txt fillet\"> <div class=\"min_img\" id=\"showImage"+i+"\"></div>"
					+"<h1><a target=\"_blank\" href=\"votes/"+voteId+"\">"+title+"</a></h1>"
                    +"<div class=\"min_l\">" 
                    +"<div class=\"min_yen\"><a><span>&yen;</span><em>"+price+"-"+pricess+"</em></a></div></div>"
                    +"<div class=\"min_r\">"
                    +"<div class=\"min_xiang\"><a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+"\">了解详细</a></div> </div>"
                    +"<div class=\"min_bottom\" id=\"comment"+voteId+"\"></div></div>";
				var show2 = 
					"<div class=\"min_middle\">" +
						"<div class=\"s_pic\">" +
							"<div class=\"s_pic_left\">" +
								"<p><h1><a target=\"_blank\" href=\"votes/"+voteId+".html\">"+title+"</a></h1></p>" +
								"<p><strong>¥"+price+"-"+pricess+"</strong></p>" +
								"<p><a target=\"_blank\" href=\"votes/"+voteId+".html\"><img src=\"../images/vote101.jpg\" /></a></p>" +
								"<p id=\"comment"+voteId+"\"></p>" +
							"</div>" +
							"<div class=\"s_pic_right\" id=\"showImage"+i+"\"></div>" +
						"</div>" +
					"</div>";
				$("#showDiv").append(show2);
				//获取该投票的图片
				var imgList = beforeVotingList[i].myVoteInfoImage;
				if(imgList!=null){
					for(var j=0;j<imgList.length;j++){
						if(j>=1){break;}
						var imgAddress = imgList[0].imgAddress;
						$("#showImage"+i+"").append("<a target=\"_blank\" target=\"_blank\" href=\"votes/"+voteId+"\"><img src=\""+ imgAddress + "\" width='306px'/></a>");
					}
				}
				var check="<a href=\"javascript:void(0);\"><input type=\"button\" value=\"取消通过\"  onclick=\"checkNoThrough('"+voteId+"')\"/></a>";
				$("#comment"+voteId+"").append(check);
			}
		},
		error : function(data) {
		}
	});
}
//把通过审核的投票转成不通过
function checkNoThrough(voids){
	var checkVoteURL="myVoteInfoAction_checkNoThrough.do";
	$.ajax({
		url : checkVoteURL,
		data : {id:voids},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			var res = data.data.res;
			if(res==1){
				throughCheckVote(8,1,1);
			}else{
				alert(data.msg);
			}
		},
		error : function(data) {
			alert(data);
		}
	});
}
//所有投票的分页
function flipPage(rows,pageNumber,id,ttp) {
	if(voteType == 1){getAllVote(rows,pageNumber,id,ttp);}
	if(voteType == 2){noThoughCheck(rows,pageNumber,id,ttp);}
	if(voteType == 3){throughCheckVote(rows,pageNumber,id,ttp);}
}
