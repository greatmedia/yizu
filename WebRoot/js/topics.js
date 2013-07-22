var hotTopics;
$(function(){
	getHotTopic();
	allTopics(12,1,1);
	setInterval("showt()", 3000);
})
var hird,index;
//获取最热话题
function getHotTopic(){
	var hotTopicURl="topicAction_getHotTopic.do";
	$.ajax({
		url:hotTopicURl,
		type:"POST",
		dataType:"json",
		success:function(data)
		{
		$("#topImgs").html("");
			hotTopics = data.data.hotTopics;//最热话题
			hird = eval(hotTopics);
			for(var i=0;i<hird.length;i++){
				index = i;
				//话题标题
				var title=hird[i].title;
				//话题内容
				var content=hird[i].circlecontent;
				//话题图片
				var tid=hird[i].circledetailid;
				getHotTopicImg(tid,i);
			}
			if(hotTopics.length<=0)
			{
				$("#topImgs").html("");
			}
		},
		error:function(data)
		{
			$("#topImgs").html("");
		}
	});
}

//获取最热话题图片
function getHotTopicImg(tid,i)
{
	var d = hird[index];
	var dimg = d.circleDetailImg;
	for(var j =0;j<dimg.length;j++)
	{
		if(j>=1)
		{
			break;
		}
		var imgurl = dimg[j].bigimg;
		var result;
		if(i==0)
		{
			result = "<li id=\""+i+"\" style=\"display:block;\" ><a><img src=\""+imgurl+"\" width='960px'/></a></li>";
			$("#topImgs").append(result);
		}else{
			result = "<li id=\""+i+"\" style=\"display:none;\"><a><img src=\""+imgurl+"\" width='960px'/></a></li>";
			$("#topImgs").append(result);
		}
		var results = "<li onclick=\"topImg("+i+")\"><a href='javascript:void();'><img src=\""+imgurl+"\" width='100px'/></a></li>";
		$("#smile_pic_list").append(results);
	}
}

var s=0;
function showt()
{
	if(s==5)
		s = 0;
	topImg(s);
	s++;
}
function topImg(i)
{
	for(var j=0;j<hotTopics.length;j++)
	{
		if(i==j)
		{
			var title = hotTopics[i].title
			var tid=hotTopics[i].circledetailid;
			if(title.length>=9)
			{
				title = title.substring(0,9);
				title = title+"...";
			}
			var content = hotTopics[i].circlecontent;
			if(content.length>234)
			{
				content = content.substring(0,234);
				content = content+"...";
			}
			$("#"+j).css("display","block");
			$("#d_banner").html("<a target=\"_blank\" href=\"topic/"+tid+"\"><h3>"+title+"</h3><p>"+content+"</p></a>");
		}
		else{
			$("#"+j).css("display","none");
		}
	}
	if(hotTopics.length<=0){
		$("#d_banner").css("display","none");
	}
}
var indexs,cird;
//获取所有话题
function allTopics(rows,pageNumber,ttp)
{
	allTopicURL = "topicAction_getAllTopics.do";
	$.ajax({
		url:allTopicURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#showTopics").html("");
			var allTopics = data.data.allTopics;
			cird = eval(allTopics);
			var count = data.data.count;
			if(count > 12){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',1,ttp);
			}else{
				$("#pl_page").html("");
			}
			for(var i =0;i<cird.length;i++)
			{
				indexs = i;
				var tid = cird[i].circledetailid;
				var title = cird[i].title;
				if((i+1)%3==0){
					var result = " <div class=\"tab_min\" style=\"margin-right:0px;\">"
		                 +"<h2><a target=\"_blank\" href=\"topic/"+tid+"\">"+title+"</a></h2>"
		                 +"<div class=\"tab_img\" id=\"s"+tid+"\"></div>"
		              +"</div>";
					$("#showTopics").append(result);
					getTopicImg(tid);
				}else{
					var result = " <div class=\"tab_min\">"
	                 +"<h2><a target=\"_blank\" href=\"topic/"+tid+"\">"+title+"</a></h2>"
	                 +"<div class=\"tab_img\" id=\"s"+tid+"\"></div>"
	              +"</div>";
					$("#showTopics").append(result);
					getTopicImg(tid);
				}
			}
			if(allTopics<=0){
				$("#showTopics").html("<h2>暂时还没有话题！</h2>").css({"text-align":"center",width:"100%"});
			}
		},
		error:function(data)
		{
		}
	});
}
//获取话题图片
function getTopicImg(tid)
{
	var d = cird[indexs];
	var dimg = d.circleDetailImg;
	for(var i =0;i<dimg.length;i++)
	{
		if(i>=1){
			break; 
		}
		var imgurl = dimg[0].middleimg;
		$("#s"+tid).append("<a target=\"_blank\" href=\"topic/"+tid+"\"><img src=\""+imgurl+"\" width=\"290px\"/></a>");
	}
}
function flipPage(rows,pageNumber,id,ttp) {
	allTopics(rows,pageNumber,id,ttp);
}