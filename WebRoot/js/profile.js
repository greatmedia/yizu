function oProMinMouseOut(obj , oPro_y ,oPro_Dlelt)
{
    var oPro_y1 = document.getElementById(oPro_y);
	var oPro_Dlelt1 = document.getElementById(oPro_Dlelt);
	
    oPro_y1.style.display = "none";
	oPro_Dlelt1.style.display = "none";
}
function onProMinMouseOver(obj, oPro_y, oPro_Dlelt)
{
	var oPro_y1 = document.getElementById(oPro_y);
	var oPro_Dlelt1 = document.getElementById(oPro_Dlelt);
	oPro_y1.style.display = "block";
	oPro_Dlelt1.style.display = "block";
}
showData(10,1,1);
var uid = 1;
var cird,index;
function showData(rows,pageNumber,ttp)
{
	var centerAction_topic="centerAction_topic.do";
	$.ajax({
	url:centerAction_topic,
	data:{pageNum : pageNumber,pageSize : rows,type : ttp,id : uid},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		myScroll();//滚动条
		$("#topic").html("");
		var cir = data.data.user_topics;
		cird = eval(cir);
		for(var i=0;i<cird.length;i++){
			index = i;
			var content = cird[i].circlecontent;
			if(content.length>100)
			{
				content = content.substring(0,100);
				content = content+".......";
			}
			var title = cird[i].title;
			
			if(title.length>18)
			{
				title = title.substring(0,18);
				title = title+".......";
			}
			var def1 = cird[i].def1;
			var def2 = cird[i].def2;
			if(def1==1)
			{
				if(def2==1)
				{
					def1 = "topic_pk";
				}else{
					def1 = "topic_tu";
				}
			}else{
				def1 = "details";
			}
			var date = cird[i].createdatetime;
			if(date.length>11)
			{
				date = date.substring(0,11);
			}
			var did = cird[i].circledetailid;
			var cid = cird[i].circleid;
			
			var circle_center_p_fillet = document.createElement("div");
			circle_center_p_fillet.className="circle_center fillet";
			
			var circle_c_right = document.createElement("div");
			circle_c_right.className="circle_c_right";
			circle_center_p_fillet.appendChild(circle_c_right);
			
			var circle_right_top = document.createElement("div");
			circle_right_top.id="topic"+did;
			circle_right_top.className="circle_right_top";
			circle_c_right.appendChild(circle_right_top);
			
			var circle_right_top_h4_a = document.createElement("a");
			circle_right_top_h4_a.target="_blank";
			circle_right_top_h4_a.href=""+def1+"/"+did;
			circle_right_top.appendChild(circle_right_top_h4_a);
			
			var circle_right_top_h4 = document.createElement("h4");
			circle_right_top_h4.innerHTML = title;
			circle_right_top_h4_a.appendChild(circle_right_top_h4);
			
			var circle_right_top_span = document.createElement("span");
			circle_right_top_span.innerHTML = date;
			circle_right_top_span.id = "close"+did;
			circle_right_top.appendChild(circle_right_top_span);
			
			/**
			 * 编辑
			 */
			var edit = document.createElement("a");
			edit.target="_blank";
			edit.href=basePath+"circleInfoAction_updateCircleDetail.do?id="+did;
			edit.id="update"+did;
			edit.innerHTML="&nbsp;编辑";
			circle_right_top_span.appendChild(edit);
			
			/**
			 * 删除
			 */
			var remove = document.createElement("a");
			remove.href="javascript:void(0)";
			remove.id="delete"+did;
			remove.innerHTML="&nbsp;删除";
			remove.setAttribute("onclick","deleteDid('"+cid+"','"+did+"',"+i+")");
			circle_right_top_span.appendChild(remove);
			
			
			var circle_right_top_p = document.createElement("div");
			circle_right_top_p.innerHTML = content;
			circle_c_right.appendChild(circle_right_top_p);
			
			var circle_right_top_p_div = document.createElement("div");
			circle_right_top_p_div.id = "dialog"+did;
			circle_right_top_p.appendChild(circle_right_top_p_div);
			
			var circle_ult = document.createElement("div");
			circle_ult.className="circle_ult";
			circle_c_right.appendChild(circle_ult);
			
			var circle_ult_ul = document.createElement("ul");
			circle_ult_ul.id = "img"+did;
			circle_ult.appendChild(circle_ult_ul);
			
			
			var circle_tite = document.createElement("div");
			circle_tite.className="circle_tite";
			circle_c_right.appendChild(circle_tite);
			
			var circle_tite_a = document.createElement("a");
			circle_tite_a.innerHTML="展开全文>>";
			circle_tite_a.target="_blank";
			circle_tite_a.href=""+def1+"/"+did;
			circle_tite.appendChild(circle_tite_a);
			
			$("#topic").append(circle_center_p_fillet);
			
			//显示图片
			showImg(i,did);
			$("#topic"+did).html("<h4 onmouseover=\"overs('"+did+"')\" onmouseout=\"out('"+did+"')\" style=\"display : inline;\"><a target=\"_blank\" href=\""+def1+"/"+did+"\">"+title+"</a></h4><span style=\"float:left\" onmouseover=\"overs('"+did+"')\" onmouseout=\"out('"+did+"')\">"+date+"<a style=\"float:right\" id=\"update"+did+"\" style=\"display : none;\" href=\""+basePath+"circleInfoAction_updateCircleDetail.do?id="+did+"\" target=\"_blank\" >&nbsp;编辑</a><a style=\"float:right\" id=\"delete"+did+"\" style=\"display : none;\" href='javascript:void(0);' onclick=\"deleteDid('"+cid+"','"+did+"',"+i+")\" >删除</a></span>");
			$("#update"+did).css("display","none");
			$("#delete"+did).css("display","none");
		}
		var count = data.data.count;
		if(count > 10){
			var paginate = new pagination();
			paginate.printPagination(pageNumber, count, rows, 'pl_page',uid,ttp);
		}else{
			$("#pl_page").html("");
		}
		if(cir.length<=0)
		{
			$("#topic").html("<div><h1>没有你的发言！</h1></div>");
		}
		
	},error:function(data)
	{
		$("#topic").html("<h1>数据加载失败，请重新刷新页面试试</h1>");
	}
});
}
function overs(obj)
{
	$("#update"+obj).css("display","block");
	$("#delete"+obj).css("display","block");
}
function out(obj)
{
	$("#update"+obj).css("display","none");
	$("#delete"+obj).css("display","none");
}
function flipPage(rows,pageNumber,id,ttp) {
	showData(rows,pageNumber,id,ttp);
}
//删除话题
function deleteDid(cid,did,i)
{
	if(window.confirm('你确定要删除该记录')){
		var deleteDidURL = "circleDetail_deleteDid.do";
		$.ajax({
			url:deleteDidURL,
			data:{id : did, ids : cid},
			type:"POST",
			dataType:"json",
			success:function(data)
			{
				var islogin = data.data.islogin; 
				var result = data.data.result;
				if(!islogin)
				{
					alert(result);
					$("#window_dl").css("display","block");
					return false;
				}else{
					alert(result);
					showData(10,1,1);
				}
				
//				if(result>0)
//				{
//					alert("删除成功");
//					showData(10,1,1);
//				}
//				else
//				{
//					alert("删除失败...");
//				}
			},
			error:function(data)
			{
				alert("删除失败...");
			}
		});
     }else{
        return false;
    }
}
function updateDetail(did)
{
	var updateDidURL = "circleInfoAction_updateCircleDetail.do?id="+did;
	window.open(basePath + updateDidURL); 
}
function showImg(i,id)
{
	var d = cird[index];
	var dimg = d.circleDetailImg;
	for(var j=0;j<dimg.length;j++){
		if(j>=3)
		{
			break;
		}
		var result = "<a href='javascript:void(0);'  onclick=\"showZom('"+dimg[j].bigimg+"','"+id+"',"+j+")\" ><li><img src=\""+dimg[j].middleimg+"\" style=\"margin-top:5px; width:120px;\"/><div id=\"box"+j+"\"></div></li></a>";
		$("#img"+id+"").append(result);
	}
	var countImg = dimg.length;
	if(countImg>0)
	{
		$("#img"+id+"").append("<span id=\"imgCount"+j+"\">["+countImg+"张图片]</span>");
	}
}
//点击放大图片并显示遮罩层，单击消失
function showZom(imageurl,i,j){
	var img=document.createElement('img');//创建一个img元素
	img.src=""+imageurl+"";//指定src
	img.style.position="absolute";//防止正常的内容变形
	img.style.visibility='hidden';//藏起来
	var inj=document.getElementById("box"+j+"").appendChild(img);//插入到box中。当然插入到document.body也可以
	//然后就可以通过 offset 取得宽和高了
	if(inj.offsetWidth>590){
		$("#dialog"+i+"").css({"z-index":"110","overflow":"hidden", "background-color":"#ffffff"}).html("<a href=\"javascript:void(0);\" id=\"div_pic\"><img src=\"" + imageurl + "\" width=\"590px\" /></a>").fadeIn(500).show();
	}else{
		$("#dialog"+i+"").css({"z-index":"110","overflow":"hidden", "background-color":"#ffffff"}).html("<a href=\"javascript:void(0);\" id=\"div_pic\"><img src=\"" + imageurl + "\" /></a>").fadeIn(500).show();
	}
	
	$("#div_pic").click(function(){
		$("#dialog"+i+"").css("display","none");
		$("#zhaoDiv"+i+"").hide();
		$("#dialog"+i+"").html("");
		$("#zhaoDiv"+i+"").html("");
		});
}
