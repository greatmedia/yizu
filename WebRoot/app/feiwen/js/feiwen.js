isshow()
function isshow()
{
	var indexFeiwenURL="phoneFeiwenAction_seiwenBytype.do";
	$.ajax({
		url : indexFeiwenURL,
		data:{tid : tid,pageNum : pageNum,pageSize : pageSize},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#loading").css("display","none");
			var result = data.data.result;
			if(result==ERROR)
			{
				$("#indexfeiwen").append("网络出现 问题了！");
				return false;
			}
			if(result == SEARCH_NULL)
			{
				$("#indexfeiwen").append("搜索内容不存在！");
				return false;
			}
			if(result == ROW_NULL)
			{
				$("#indexfeiwen").append("没有更多了！");
			}
			var feiwen = data.data.feiWens;
			if(feiwen.length>0)
			{
				for ( var f = 0; f < feiwen.length; f++) 
				{
					var fwid = feiwen[f].fwid;
					var title = feiwen[f].title;
					var titleStr = feiwen[f].title;
					var comcount =  feiwen[f].comcount;
					var content = feiwen[f].content;
					var contentStr = feiwen[f].content;
					if(content.length>100){
						content = content.substr(0,100);
						content = content+"..";
					}
					var divHTML = document.createElement("div");
					$("#indexfeiwen").append(divHTML);
					
					var title_div = document.createElement("div");
					title_div.className="title";
					divHTML.innerHTML=title;
					
					var content_div = document.createElement("div");
					content_div.className="content";
					content_div.id="fwid"+fwid;
					content_div.innerHTML=content;
					divHTML.appendChild(content_div);
					
					var content_qw_div = document.createElement("div");
					content_qw_div.className="content_qw";
					content_qw_div.id="fwid_qw"+fwid;
					content_qw_div.innerHTML=contentStr;
					divHTML.appendChild(content_qw_div);
					
					var qu_a = document.createElement("a");
					qu_a.href="javascript:void(0)";
					qu_a.id="quanwen"+fwid;
					qu_a.innerHTML="查看全文";
					qu_a.setAttribute("onclick","qw('"+fwid+"','fwid','fwid_qw','quanwen')");
					divHTML.appendChild(qu_a);
					
					var label = document.createElement("label");
					label.innerHTML="</br></br>";
					divHTML.appendChild(label);
				}
			}else{
				$("#indexfeiwen").html("没有数据！");
			}
		},
		error : function(data) {
			$("#loading").css("display","none");
			$("#indexfeiwen").html("数据加载失败，请检查网络是否连接正确！");
		}
	});
}
var i = 0;
function qw(fwid,fwidStr,fwid_qu,quanwen)
{
	if(i%2==0)
	{
		$("#"+fwidStr+fwid).css("display","none");
		$("#"+fwid_qu+fwid).css("display","block");
		$("#"+quanwen+fwid).css("display","none");
	}
	i++
}