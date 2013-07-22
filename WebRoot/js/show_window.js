/*页面弹出层*/
/*xiaoyuan.bjtu.edu.cn JS Document */
/*Desgin By Steven*/
/*Test by IE6/IE8/Fire5*/
/*其它非主流浏览器不在测试范围内*/
/*First design time:2011-7-28 17:00*/
function BOX_show(e)//显示
{
    if(document.getElementById(e)==null)
    {
        return ;
    }

    window.reload;

    var selects = document.getElementsByTagName('select');
    for(i = 0; i < selects.length; i++)
    {
        selects[i].style.visibility = "hidden";
    } 

    BOX_layout(e);
    window.onresize = function(){BOX_layout(e);} //改变窗体重新调整位置
    window.onscroll = function(){BOX_layout(e);} //滚动窗体重新调整位置
    document.onkeyup = function(event)
    {
        var evt = window.event || event;
        var code = evt.keyCode?evt.keyCode : evt.which;
        //alert(code);

        if(code == 27)
        {
            BOX_remove(e);
        }
    }

}

function BOX_remove(e)//移除
{
    window.onscroll = function () {
        var t = document.documentElement.scrollTop || document.body.scrollTop;
        var top_div = document.getElementById("top_down_out");
        if (t >= 100) {
            top_div.className = "top_down_out0 fillet";
        }
        if (t == 0) {
            top_div.className = "top_down_out fillet";
        }
    }

    window.onresize = null;
    //document.getElementById('BOX_overlay').style.display="none";
    document.getElementById(e).style.display="none";

    var selects = document.getElementsByTagName('select');
    for(i = 0; i < selects.length; i++)
    {
        selects[i].style.visibility = "visible";
    }
}

function BOX_layout(e)//调整位置
{
    var a = document.getElementById(e);

//    if (document.getElementById('BOX_overlay')==null)//判断是否新建遮掩层
//    {

//        var overlay = document.createElement("div");
//        overlay.setAttribute('id','BOX_overlay');

//        //overlay.onclick=function(){BOX_remove(e);};
//        //a.parentNode.appendChild(overlay);
//        document.body.appendChild(overlay);
//    }

//    document.getElementById('BOX_overlay').ondblclick=function(){BOX_remove(e);};
//    //取客户端左上坐标，宽，高
//	
    var scrollLeft = (document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft);
    var scrollTop = (document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop);

    //alert(scrollTop);

    var clientWidth;
    if (window.innerWidth)
    {
        clientWidth = window.innerWidth;
       // clientWidth = ((Sys.Browser.agent === Sys.Browser.Safari) ? window.innerWidth : Math.min(window.innerWidth, document.documentElement.clientWidth));
    }
    else
    {
        clientWidth = document.documentElement.clientWidth;
    }

    var clientHeight;
    if (window.innerHeight)
    {
        clientHeight = window.innerHeight;
        //clientHeight = ((Sys.Browser.agent === Sys.Browser.Safari) ? window.innerHeight : Math.min(window.innerHeight, document.documentElement.clientHeight));
    }
    else
    {
        clientHeight = document.documentElement.clientHeight;
    }

//    var bo = document.getElementById('BOX_overlay');
//    bo.style.left = scrollLeft+'px';
//    bo.style.top = scrollTop + 'px';
//    bo.style.position = 'absolute';
//    bo.style.width = clientWidth+'px';
//    bo.style.height = '1300px';
//    bo.style.background = "#000";
//    bo.style.display = "";
    //a.style.background-color="red";
    //Popup窗口定位
/*    a.style.position = 'absolute';
    a.style.zIndex=999;*/
    a.style.display="";
    //a.style.left = scrollLeft+((clientWidth-a.offsetWidth)/2)+'px';
    //a.style.top = scrollTop+((clientHeight-a.offsetHeight)/2)+'px';
}

function HiddenButton(e)
{
    e.style.visibility='hidden';
    e.coolcodeviousSibling.style.visibility='visible'
}

function openShutManager(oSourceObj,oTargetObj){
	
	var sourceObj = typeof oSourceObj == "string" ? document.getElementById(oSourceObj) : oSourceObj;
	var targetObj = typeof oTargetObj == "string" ? document.getElementById(oTargetObj) : oTargetObj;
//	alert(sourceObj+"  "+targetObj);
//	var openTip = oOpenTip || "";
//	var shutTip = oShutTip || "";
	if(targetObj.style.display!="none"){
//	   if(shutAble) return;
	   targetObj.style.display="none";
//	   if(openTip  &&  shutTip){
//	    sourceObj.innerHTML = shutTip; 
//	   }
	} else {
		getInformInfoNotRead();
		targetObj.style.display="block";
//	   if(openTip  &&  shutTip){
//	    sourceObj.innerHTML = openTip; 
//	   }
	}
}

function getInformInfoNotRead(){
	var infromInfoNotReadURL = "jsonInfoAction_getInformInfoNotRead.do";
	$.ajax({
    	url:infromInfoNotReadURL,
		type:"POST",
		dataType:"json",
		beforeSend:function() {
		},
		success:function(data)
		{
			$("#con").html("");
			$("#tongzhi").html("");
			var informList = data.data.informList;
			var joinCircleList = data.data.joinCirclelist;
			var str = "<ul id=\"tags\">" +
						"<li style=\" border-right:#dddddc solid 1px;\" class='selectTag'>" +
							"<a onClick=\"selectTag2('tagContent0',this)\"  href=\"javascript:void(0);\">最新动态</a>" +
						"</li>" +
						"<li class=\"\">" +
							"<a onClick=\"selectTag2('tagContent1',this)\"  href=\"javascript:void(0);\">新成员</a>" +
						"</li>" +
					  "</ul>" +
					  "<div class=\"xx_close\"><a href=\"javascript:void(0);\"onClick=\"BOX_remove('window_xx')\" ></a></div>" +
					  "<div id=tagContent>" +
					  		"<div class=\"tagContent selectTag\" id=tagContent0></div>" +
					  		"<div class=\"tagContent\" id=tagContent1></div>" +
					  "</div>";
			$("#con").html(str);
//			if(joinCircleCount <= 0){$("#tagContent1").html("<dl><dd>加入圈子的成员均为已读状态</dd></dl>");}
			for(var i = 0; i < informList.length; i++){
				 var informtype = informList[i].informtype;//通知类型
				 if(informtype == 2){//为2 圈子内容被评论
					 var info2 ="" +
					 		"<p>" +
					 			"<a href=\""+basePath+"user/"+informList[i].userid+".html\">"+informList[i].userinfo.nick+"</a>评论了您发布的内容" +
					 			"<a href=\""+basePath+"det/"+informList[i].circleid+"/"+informList[i].circledetailid+".html\">"+informList[i].detail.title+"</a>" +
					 		"</p>";
					 $("#tagContent0").append(info2);
				 }else if(informtype == 3 ){//为3 话题被评论
					 var info3 ="" +
				 		"<p>" +
				 			"<a href=\""+basePath+"user/"+informList[i].userid+".html\">"+informList[i].userinfo.nick+"</a>评论了您发布的话题" +
				 			"<a href=\""+basePath+"topic_tu/"+informList[i].circledetailid+".html\">"+informList[i].detail.title+"</a>" +
				 		"</p>";
					 $("#tagContent0").append(info3);
				 }else if(informtype == 4){//话题被关注
					 var info4 ="" +
				 		"<p>" +
				 			"<a href=\""+basePath+"user/"+informList[i].userid+".html\">"+informList[i].userinfo.nick+"</a>关注了您发布的话题" +
				 			"<a href=\""+basePath+"topic_tu/"+informList[i].circledetailid+".html\">"+informList[i].detail.title+"</a>" +
				 		"</p>";
					 $("#tagContent0").append(info4);
				 }
			}
			for(var c = 0; c < joinCircleList.length ; c++){
				//为 1 圈子有新加入的成员
				 if(joinCircleList[c].circleinfo == null){ continue;}
				 var info1 = "" +
			 		"<dl>" +
			 			"<dt><a href=\""+basePath+"user/"+joinCircleList[c].userid+".html\"><img src=\""+joinCircleList[c].userinfo.image+"\"/></a></dt>" +
			 			"<dd><a href=\""+basePath+"user/"+joinCircleList[c].userid+".html\">"+joinCircleList[c].userinfo.nick+"</a>加入了<a target='_blank' href=\""+basePath+"cir2/1/"+joinCircleList[c].circleinfo.circleid+".html\">"+joinCircleList[c].circleinfo.circlename+"</a>圈子</dd>" +
			 		"</dl>";
				 $("#tagContent1").append(info1);
			}
		},
		error:function(data){
//			alert("ee"+data);
		}
		
	});
}