//var  userid =   location.search;
//userid   =   userid.substr(1,userid.length-1);
//userid = userid.substr(3,userid.length);
function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
//var userid = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var userid = realurl.substring(idindexof + 1);
userCreateCircle(18,1,1);
function userCreateCircle(rows,pageNumber,ttp)
{
	var my_circleURL= "centerAction_userCreateCircle.do";
	$.ajax({
		url:my_circleURL,
		data:{pageNum : pageNumber,pageSize : rows,type : ttp,id : userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			$("#my_create_circle").html("");
			var cir = data.data.my_create_circle;
			if(cir.length<=0)
			{	
				$("#my_create_circle").append("<div><h1>该用户还没有创建圈子..</h1></div>");
			}else{
				
			for(var i=0;i<cir.length;i++){
				var strcirclename = cir[i].circlename;
				if(strcirclename.length>6)
				{
					strcirclename = strcirclename.substring(0,6);
					strcirclename = strcirclename+"...";
				}
				var cid = cir[i].circleid;
				// 
				var result="" +
				"<div id="+"btn"+cid+" class=\"prof_mine_t\"  >" +
				"<a id=\"proAon\" target=\"_blank\" href=\"circle/"+cid+"\" onmouseover=\"onProMinMouseOver(this,'x"+cid+"','"+cid+"y')\" onmouseout=\"oProMinMouseOut(this,'x"+cid+"','"+cid+"y')\" >" +
					"<img src=\""+cir[i].circlemiddleimg+"\"/><div id=\"x"+cid+"\" class=\"pro_yr\"><p>"+strcirclename+"</p></div><div id=\""+cid+"y\" style=\"display: none;\" class=\"pro_dlelt\" >" +
					"</div>" +
				"</a>" +
				"</div>";
				$("#my_create_circle").append(result);
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
			$("#my_create_circle").html("<h1>数据加载失败，请重新刷新页面试试...</h1>");
		}
	});
}
function flipPage(rows,pageNumber,id,ttp) {
	userCreateCircle(rows,pageNumber,id,ttp);
}
