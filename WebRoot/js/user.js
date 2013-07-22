function getQueryStringRegExp(name)
{
	var reg = new RegExp("(^|\\?|&)"+ name +"=([^&]*)(\\s|&|$)", "i");
	if (reg.test(location.href)) return unescape(RegExp.$2.replace(/\+/g, " ")); return "";
}
//var uid = getQueryStringRegExp("id");
var realurl = location.href;
var idindexof = realurl.lastIndexOf("/");
var uid = realurl.substring(idindexof + 1);
showData(10,1,1);
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
var cird,index;
function showData(rows,pageNumber,ttp)
{
	var getUserTopicURL="centerAction_getUserTopic.do";
	$.ajax({
	url:getUserTopicURL,
	data:{pageNum : pageNumber,pageSize : rows,type : ttp,id : uid},
	type:"POST",
	dataType:"json",
	success:function(data)
	{
		myScroll();//滚动条
		$("#topic").html("");
		var cir = data.data.user_topics;
		cird = eval(cir);
		if(cir.length<=0)
		{
			$("#topic").html("<h1>该用户还没有发表话题..</h1>");
			$("#pl_page").removeClass();
			
		}else{
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
				var def1 = cir[i].def1;
				var def2 = cir[i].def2;
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
				var did = cird[i].circledetailid;
				var date = cird[i].createdatetime;
//				var result="<div id=\"zhaoDiv\""+i+"></div><div class=\"circle_center_p fillet\"><div class=\"circle_c_right\">" +
//						"<div class=\"circle_right_top\">" +
//						"<h4><a target=\"_blank\" href='"+def1+"/"+cird[i].circledetailid+"'>"+title+"</a></h4><span>"+cird[i].createdatetime+"</span></div>" +
//							"<p>"+content+"</p><div id=\"dialog"+i+"\"></div>" +
//									"<div class=\"circle_ult\">" +
//							"<ul id=\"img"+i+"\"></ul></div>" +
//								"<div class=\"circle_tite\"><a href=\"javascript:void(0);\" onclick=\"window.open('"+def1+"/"+cird[i].circledetailid+"')\" ;return false; \">展开全文>></a>" +
//						"</div>" +
//					"</div>" +
//				"</div>"
				
				var circle_center_p_fillet = document.createElement("div");
				circle_center_p_fillet.className="circle_center fillet";
				
				var circle_c_right = document.createElement("div");
				circle_c_right.className="circle_c_right";
				circle_center_p_fillet.appendChild(circle_c_right);
				
				var circle_right_top = document.createElement("div");
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
				var circleids = cird[i].circledetailid;
				//显示图片
				showImg(i,circleids);
			}
			var count = data.data.count;
			if(count > 10){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, count, rows, 'pl_page',uid,ttp);
			}else{
				$("#pl_page").html("");
			}
		}
	},error:function(data)
	{
		$("#topic").html("你要查找的用户信息不存在...");
	}
});
}
function flipPage(rows,pageNumber,id,ttp) {
	showData(rows,pageNumber,id,ttp);
}
function showImg(i,id)
{
	var d = cird[index];
	var dimg = d.circleDetailImg;
	$("#img"+i+"").html("");
	var countImg = dimg.length;
	for(var j=0;j<dimg.length;j++)
	{
		if(j>3)
		{
			break;
		}
		var result = "<li><a href=\"javascript:void(0);\" onclick=\"showZom('"+dimg[j].bigimg+"','"+id+"',"+j+")\"><img src=\"" + dimg[j].middleimg + "\" id=\"showImg"+j+"\" style=\"margin-top:5px; width:120px;\" /><div id=\"box"+j+"\"></div></a></li>";
		$("#img"+id+"").append(result);
		
	}
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