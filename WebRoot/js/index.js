			var islogin;
            window.onload = function () {
            	indexFeiwen();
            	var showTopButtonHeight = 800;//回到顶部按钮的距离
                var toTop = $aq("toTop");
                var xuanfu = $aq("xuanfu");
                //得到浏览器的名称
                var browser = getBrowser();
//                var h = document.getElementById("top_down_out").offsetTop;
//                var e = document.getElementById("top_down_out0").offsetTop;
//                alert(e+h);
//                if(h == 61){document.getElementById("top_down_out").className = "top_down_out0 fillet";}
//                imgLoadPre();
                //主会场
                window.onscroll = fun_scroll;
                //滚动方法
                function fun_scroll() {
                    //body的高度
                    var topAll = (browser == "Firefox") ? document.documentElement.scrollHeight : document.body.scrollHeight;
                    //卷上去的高度
                    var top_top = document.body.scrollTop || document.documentElement.scrollTop;
                    var top_div = document.getElementById("top_down_out");
                    if (top_top >= 61) {
                        top_div.className = "top_down_out0 fillet";
                        $(".main_cont").css("padding-top","56px");
                    }
                    if (top_top <= 61) {
                        top_div.className = "top_down_out fillet";
                        $(".main_cont").css("padding-top","0px");
                    }
                    
                    //回到顶部按钮操作
                    if (top_top > showTopButtonHeight)
                    	xuanfu.style.display = "block";
                    else
                    	xuanfu.style.display = "none";
//                    imgLoadPre();
                    window.onscroll = fun_scroll;
                }
                
                //通过id得到对象
                function $aq(id) {
                    return document.getElementById(id);
                }
                
                //回到顶部
                toTop.onclick = function () {
                	goTop(0.5,24);
                };
            };
          //得到浏览器的名称
            function getBrowser() {
                if (navigator.userAgent.indexOf("MSIE") > 0) {
                    return "MSIE";
                }
                if (isFirefox = navigator.userAgent.indexOf("Firefox") > 0) {
                    return "Firefox";
                }
                if (isSafari = navigator.userAgent.indexOf("Safari") > 0) {
                    return "Safari";
                }
                if (isCamino = navigator.userAgent.indexOf("Camino") > 0) {
                    return "Camino";
                }
                if (isMozilla = navigator.userAgent.indexOf("Gecko/") > 0) {
                    return "Gecko";
                }
            }
        	function createquanzi()
        	{
        		if(islogin)
        		{
        			location.href = basePath+"circleInfoAction_create.do";
        		}else{
        			$("#window_dl").css("display","block");
        			$("#zhezhaoceng").show(300).delay(2000).hide(300);//.hide(300)
        		}		
        	}
        	
        	function indexFeiwen()
        	{
        		var indexFeiwenURL=basePath+"feiWenAction_indexFeiWen.do";
        		$.ajax({
        			url : indexFeiwenURL,
        			type : 'POST',
        			dataType : 'json',
        			success : function(data){
        				var feiWenTypes = data.data.feiWenTypes;
        				for ( var i = 0; i < feiWenTypes.length; i++) {
        					var tid = feiWenTypes[i].tid;
        					var type = feiWenTypes[i].title;
        					var feiwen = feiWenTypes[i].feiWen;
        					if(feiwen.length>0){
        						for ( var f = 0; f < feiwen.length; f++) {
    								var title = feiwen[f].title;
    								var titleStr = feiwen[f].title;
    								if(title.length>10){
    									title = title.substr(0,10);
    									title = title+"..";
    								}
    								var feiwenHTML = 
    									"<li>"+
    			                            "<div class=\"fei_l\"><a href=\"javascript:void(0);\"><img src=\"images/type"+tid+".gif\" _src=\"images/type"+tid+".gif\" /></a></div>"+
    			                            "<div class=\"fei_t\">"+type+"</div>"+
    			                            "<div class=\"fei_r\"><p><a href=\"feiwenshow/"+feiwen[f].fwid+".html?tid="+tid+"\" target=\"_blank\" title="+titleStr+">"+title+"</a></p></div>"+                             
    		                            "</li>";
    								var feiwenHTML2 = 
    									"<li>"+
    			                            "<div class=\"fei_l\"><a href=\"javascript:void(0);\"><img src=\"images/type"+tid+".gif\" _src=\"images/type"+tid+".gif\" /></a></div>"+
    			                            "<div class=\"fei_t\">"+type+"</div>"+
    			                            "<div class=\"fei_r\"><p><a href=\"feiwenshowone/"+tid+"/1/"+feiwen[f].fwid+".html\" target=\"_blank\" title="+titleStr+">"+title+"</a></p></div>"+                             
    		                            "</li>";
    	        					$("#feiwen").append(feiwenHTML2);
    							}
        					}
						}
        				getCircleTotop();
        			},
        			error : function(data) {
//        				alert("error");
        				location.href=basePath;
        			}
        		});
        	}
    function getCircleTotop(){
    	var getCircleTotopURL=basePath+"jsonInfoAction_getCircleTotop.do";
		$.ajax({
			url : getCircleTotopURL,
			type : 'POST',
			dataType : 'json',
			success : function(data){
				islogin = data.data.islogin;
				var mrCircleinfo = data.data.mrCircleinfo;
				createTagHTML(mrCircleinfo,1);
				
				var ydCircleinfo = data.data.ydCircleinfo;
				createTagHTML(ydCircleinfo,2);
				
				var xxCircleinfo = data.data.xxCircleinfo;
				createTagHTML(xxCircleinfo,3);
				
				var whCircleinfo = data.data.whCircleinfo;
				createTagHTML(whCircleinfo,4);
				
				var zcCircleinfo = data.data.zcCircleinfo;
				createTagHTML(zcCircleinfo,5);
				
				var nxCircleinfo = data.data.nxCircleinfo;
				createTagHTML(nxCircleinfo,6);
				
				var shCircleinfo = data.data.shCircleinfo;
				createTagHTML(shCircleinfo,7);
				
				var bfCircleinfo = data.data.bfCircleinfo;
				createTagHTML(bfCircleinfo,8);
				
//				$("#Recommend").html("");
//				var circleList = data.data.circleList;
//				
//				for(var i = 0; i<circleList.length; i++){
//					var circleName = circleList[i].circlename;
//					var tagname = circleList[i].circletag;
//					if(tagname.length >=20){
//						tagname = tagname.substring(0,20);
//					}
//					var imgsrc = circleList[i].circlebigimg;
////					imgsrc = "http://1mily.com/"+imgsrc;
//					var str;
//					if((i+1)%4 == 0){
//						 str = "<li class=\"re_list\">" +
//							"<a target=\"_blank\" href=\"cir2/1/"+circleList[i].circleid+".html\"><img _src=\""+imgsrc+"\" src=\""+imgsrc+"\" width=\"220px\" height=\"354px\" onmouseover=\"mover(this)\" onmouseout=\"mout(this)\"/></a>" +
//							"<div class=\"re_yin\"></div>" +
//							"<p><a target=\"_blank\" href=\"cir2/1/"+circleList[i].circleid+".html\">"+circleName+"</a><span>"+tagname+"</span></p>" +
//							"</li>";
//					}else{
//						 str = "<li>" +
//							"<a target=\"_blank\" href=\"cir2/1/"+circleList[i].circleid+".html\"><img _src=\""+imgsrc+"\" src=\""+imgsrc+"\" width=\"220px\" height=\"354px\" onmouseover=\"mover(this)\" onmouseout=\"mout(this)\"/></a>" +
//							"<div class=\"re_yin\"></div>" +
//							"<p><a target=\"_blank\" href=\"cir2/1/"+circleList[i].circleid+".html\">"+circleName+"</a><span>"+tagname+"</span></p>" +
//							"</li>";
//					}
//					$("#Recommend").append(str);
//				}
//				/////
//				var zcCircleinfo = data.data.zcCircleinfo;
//				var yundongCircleinfo = data.data.yundongCircleinfo;
//				var wenhuaCircleinfo = data.data.wenhuaCircleinfo;
//				var jujiaCircleinfo = data.data.jujiaCircleinfo;
//				var qitaCircleinfo = data.data.qitaCircleinfo;
//				var nvxingCircleinfo = data.data.nvxingCircleinfo;
//				createTagHTML(zcCircleinfo,1);
//				createTagHTML(yundongCircleinfo,2);
//				createTagHTML(wenhuaCircleinfo,3);
//				createTagHTML(jujiaCircleinfo,4);
//				createTagHTML(qitaCircleinfo,5);
//				createTagHTML(nvxingCircleinfo,6);
			},
			error : function(data){
				
			}
		});
    }
    function createTagHTML(circleinfos,i)
    {
    	for ( var j = 0; j < circleinfos.length; j++) {
    		var circleName = circleinfos[j].circlename;
    		var d = (j+1)+(i-1)*4;
//    		alert(d);
    		var str = "" +
    			"<li>" +
    				"<a href=\"cir2/1/"+circleinfos[j].circleid+".html\">"+
    				"<div class=\"ch-item ch-img-"+d+"\" style=\"background-image: url("+circleinfos[j].circlebigimg+");\">" +
    					"<span style=\"position:absolute; width:220px; font-weight:bold; text-align:center;color: #000;text-shadow: #fff 0 1px 2px;line-height:220px;font-size: 16px;\">"+circleName+"</span>" +
    					"<div class=\"ch-info\">" +
    						"<h3><a href=\"cir2/1/"+circleinfos[j].circleid+".html\" style=\"color: #fff;\"\>"+circleName+"</a></h3>" +
    						"<h2>" +
    							"<a href=\"javascript:join_quanzi('"+circleinfos[j].circleid+"');\"><img src=\"images/shouye/jia.png\"/></a>" +
    							"<a href=\"javascript:addFavorite('"+circleinfos[j].circleid+"');\"><img src=\"images/shouye/xin.png\"/></a>" +
    							"<a href=\"cir2/1/"+circleinfos[j].circleid+".html\"><img src=\"images/shouye/hua.png\"/></a>" +
    						"</h2>" +
    						"<p>圈主：<a href=\"user/"+circleinfos[j].userinfo.userid+".html\" style=\"color: #fff;\">"+circleinfos[j].userinfo.nick+"</a></p>" +
    						"<h1><a href=\"user/"+circleinfos[j].userinfo.userid+".html\"><img src=\""+circleinfos[j].userinfo.image+"\" style=\"width:80px;\"/></a></h1>" +
    					"</div>" +
    				"</div>" +
    				"</a>"+
    			"</li>";
    		
//    		var circleName = circleinfos[j].circlename;
//    		var html = document.createElement("li");
//    		var div = document.createElement("div");
//    		div.className = "ch-item ch-img-"+j;
//    		
//    		var span = document.createElement("span");
//    		span.style.positon = "absolute";
//    		span.style.width = "220px";
//    		span.style.fontWeight ="bold";
//    		span.style.textAlign = "center";
//    		span.style.color = "#000";
//    		span.style
//    		
//    		if((j+1)%6==0){html.className = "le_list";}
//    		aimg.href="cir2/1/"+circleinfos[j].circleid+".html";
//    		html.appendChild(aimg);
//    		var img = document.createElement("img");
//    		img.src="images/preimg.gif";
//    		var imgPath = circleinfos[j].circlebigimg;
//    		img.setAttribute("_src",imgPath);//circleinfos[j].circlebigimg
//    		img.style.width = "146px";
//    		img.style.height = "195px";
//    		img.setAttribute("onmouseover","mover(this)");
//    		img.setAttribute("onmouseout","mout(this)");
//    		aimg.appendChild(img);
//    		var divs = document.createElement("div");
//    		divs.className="le_yin";
//    		html.appendChild(divs);
//    		var p = document.createElement("p");
//    		html.appendChild(p);
//    		var a = document.createElement("a");
//    		a.href="cir2/1/"+circleinfos[j].circleid+".html";
//    		a.innerHTML = circleName;
//    		p.appendChild(a)
			if(i==1)
			{
				$("#mrtop_div").show();
				$("#mrCircleinfo").append(str);
			}
			if(i==2)
			{
				$("#ydtop_div").show();
				$("#ydCircleinfo").append(str);
			}
			if(i==3)
			{
				$("#xxtop_div").show();
				$("#xxCircleinfo").append(str);
			}
			if(i==4)
			{
				$("#whtop_div").show();
				$("#whCircleinfo").append(str);
			}
			if(i==5)
			{
				$("#zctop_div").show();
				$("#zcCircleinfo").append(str);
			}
			if(i==6)
			{
				$("#nxtop_div").show();
				$("#nxCircleinfo").append(str);
			}
			if(i==7)
			{
				$("#shtop_div").show();
				$("#shCircleinfo").append(str);
			}
			if(i==8)
			{
				$("#bftop_div").show();
				$("#bfCircleinfo").append(str);
			}
		}
    }
    function join_quanzi(did){
    	if(!islogin)
    	{
    		alert("您还没有登录，登录之后才能加入圈子..");
    		$("#window_dl").css("display","block");
    		return;
    	}
    	var type = 1;
    	var joinquanziURL = "jsonInfoAction_joinOrQuitQuanzi.do";
    	$.ajax({                 
    		url: joinquanziURL,  
    		data : {id : did ,type : 1},
    		type: "POST",     
    		dataType : "json",
    		async:false,
    		success: function(data) {
    			var flag = data.flag;
    			if(type == 1){
    				//加入圈子
    				if(flag){
    					alert("加入圈子成功！");
    				}else{
    					alert("你已经加入该圈子了！");
    				}
    			}
    		},
    		error:function(data){
//    			$("#wait").css("display","none");
    		}
    	});
    }
    function addFavorite(cid){
    	if(!islogin)
    	{
    		alert("您还没有登录，登录之后才能收藏圈子..");
    		$("#window_dl").css("display","block");
    		return;
    	}
    	var addFavoriteURL = "jsonInfoAction_addFavorite.do";
    	$.ajax({                 
    		url: addFavoriteURL,  
    		data : {id : cid},
    		type: "POST",     
    		dataType : "json",
    		success: function(data) {
    			var flag = data.flag;
    			if(flag){
    				alert("收藏成功！");
    			}else{
    				alert("已经收藏过了");
    			}
    		},
    		error:function(data){
    			
    		}
    	});
    }
