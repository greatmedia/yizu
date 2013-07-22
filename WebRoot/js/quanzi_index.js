			var cnt = 0;
			var pageNum;
			var divCount;
			var res;
			var end;
			var islogin;
            window.onload = function () {
            	getTagTopic();
            	pageNum = 1;
            	divCount = 5;
                //初始参数
                var reset = 0; //某些滚动条会触发三次scroll事件 用这个解决
                var surplusHeight = 500; //差值
                var imgWidth = "198px"; //img的宽度
                var showTopButtonHeight = 500;//回到顶部按钮的距离
                var div1 = $aq("one");
                var div2 = $aq("two");
                var div3 = $aq("three");
//                var div4 = $aq("four");
                var div5 = $aq("five");
                var loading = $aq("loading");
                var load = $aq("load");
                end = $aq("end");
                var toTop = $aq("toTop");
                //得到浏览器的名称
                var browser = getBrowser();
                $("#load").bind("click",function(){
                	read();
                });
                $("#nav_bar_latest").bind("click",function(){
                	$("#box_news").css("display","none");
                	sethotIniVal("1");
                	read();
                });
                $("#nav_bar_hottest").bind("click",function(){
                	$("#box_news").css("display","none");
                	sethotIniVal("2");
                	read();
                });
                $(".btn01").bind("click",function(){
                	$("#box_news").css("display","none");
                	settagIniVal($(this).attr("name"));
                	window.open("quanzi/type.jsp?hot="+$("#hotvals").val()+"&type="+$("#tagvals").val());
                });
                //初始化
                read();
                //主会场
                window.onscroll = fun_scroll;
                window.onload = imgLoadPre();
                window.onresize = imgLoadPre();
                //滚动方法
                function fun_scroll() {
                    //body的高度
                    var topAll = (browser == "Firefox") ? document.documentElement.scrollHeight : document.body.scrollHeight;
                    //卷上去的高度
                    var top_top = document.body.scrollTop || document.documentElement.scrollTop;
                    if(top_top>=2250){
                    	$("#head").css("position","relative");
                    	$("#hot_nav").css({"position":"fixed","margin-top":"0px","left":"0px","width":"100%"});
                    }else{
                    	$("#hot_nav").css({"position":"relative","margin-top":"20px"});
                    	$("#head").css("position","fixed");
                    }
                    
                    //回到顶部按钮操作
                    if (top_top > showTopButtonHeight)
                        toTop.style.display = "block";
                    else
                        toTop.style.display = "none";
                    //控制滚动条次数以及判断是否到达底部
                    if (reset == 0) {
                    	var position = getMinHeight();
                        var top_top = document.body.scrollTop || document.documentElement.scrollTop; //卷上去的高度
                        if((top_top+surplusHeight) > position || top_top == position){
                        	setTimeout(read, 1);
                            reset = 1;
                        }
                    } else {
                        setTimeout(function () { reset = 0; }, 1);
                    }
                    imgLoadPre();
                }
                //加载图片
                function loadImg() {
                    loading.style.display = "none";
                    load.style.display = "none";
                    var num =0;
                    for(var i=0;i<res.length;i++){
                    		num = getNum();
                        	if(num == 0){div1.appendChild(addDiv(res[i]));}
                        	else if(num == 1){div2.appendChild(addDiv(res[i]));}
                        	else if(num == 2){div3.appendChild(addDiv(res[i]));}
//                        	else if(num == 3){div4.appendChild(addDiv(res[i]));}
                        	else if(num == 3){div5.appendChild(addDiv(res[i]));}
                    }
                    setTimeout(function () {
                        var hh = (browser == "Firefox") ? document.documentElement.scrollHeight : document.body.scrollHeight;
                        loading.style.top = (hh-130) + "px";
                        if(pageNum < (autoloadrows+1)){
                        	if(res.length == (indexshowrows*divCount)){
                        		loading.style.display = "block";
                        	}
                        }else{
                        	if(res.length == (indexshowrows*divCount)){
                        		load.style.display = "block";
                        	}
                        }
                    }, 1);
                }

                //声明一个包含img和title的div
                function addDiv(cir) {
                    //数组下标的随机值
                    var box = document.createElement("div");
                    box.className = "box";
                    box.setAttribute("onmouseover","dis('"+cir.circleid+"', 'show')");
                    box.setAttribute("onmouseout","dis('"+cir.circleid+"', 'hide')");
                    //=================  fenxiang  ==========================
                    //漂浮层
                    var fenxiang = document.createElement("div");
                    fenxiang.className="fenxiang";
                    fenxiang.setAttribute("id",cir.circleid);
                    fenxiang.setAttribute("name","abc");
                    fenxiang.setAttribute("onmouseover","dis('"+cir.circleid+"', 'show')");
                    fenxiang.setAttribute("onmouseout","dis('"+cir.circleid+"', 'hide')");
                    //内侧ul
                    var ul = document.createElement("ul");
                    //内侧li1
                    var li1 = document.createElement("li");
                    if(islogin){
                    	var img1 = document.createElement("img");
                    	img1.setAttribute("id","jimg_"+cir.circleid);
                    	if(cir.isjoin ==1){
                    		img1.src="images/s-btn_01_h.gif";     
                    		img1.setAttribute("_src","images/s-btn_01_h.gif");
                    	}else{
                    		img1.src="images/s-btn_01.gif";
                    		img1.setAttribute("_src","images/s-btn_01.gif");
                    		img1.setAttribute("onclick","join_or_quit_quanzi('"+cir.circleid+"',1,"+cir.userinfo.userid+")");
                    	}
                    	li1.appendChild(img1);
                    	$("#hot_ht2").css("display","block");
                    }else{
                    	$("#hot_ht2").css("display","none");
                    }
                    ul.appendChild(li1);
                    fenxiang.appendChild(ul);
                    box.appendChild(fenxiang);
                    
                    //===============  fenxiang end ==========================
                    
                    //==================   items   ==========================
                    //图片层
                    var items = document.createElement("div");
                    items.className = "items";
                    
                	//内侧的div 1 图片
                    var container = document.createElement("div");
                    container.className = "feed_container";
                    //图片外部的链接
                	var container_hrf = document.createElement("a");
//                	container_hrf.href="quanzi/circle.jsp?id="+cir.circleid;
                	container_hrf.href="circle/"+cir.circleid+".html";
                	container_hrf.target = "_blank";
                    var con_img = document.createElement("img");
                    con_img.alt = "";
                    con_img.id = cir.circlemiddleimg;
                    if(cir.circlemiddleimg==null){
                    	con_img.src = "images/preimg.gif";
                    	con_img.setAttribute("_src","images/preimg.gif");
                    }else{
                    	con_img.src = "images/preimg.gif";
                    	con_img.setAttribute("_src",cir.circlemiddleimg);
                    }
                    con_img.removeAttribute("height"); 
                    con_img.style.width = imgWidth;
                    
                    container_hrf.appendChild(con_img);
                    container.appendChild(container_hrf);
                    items.appendChild(container);
                    
                    //----------------------------圈子名称
                    //内侧p标签 2
                    var con_p = document.createElement("p");
                    con_p.className = "c_grey";
                    
                    var con_p_hrf = document.createElement("a");
                    con_p_hrf.href="circle/"+cir.circleid+".html";
                    con_p_hrf.title=cir.circlename;
                    con_p_hrf.innerHTML = sub(cir.circlename,12);
                    con_p_hrf.target="_blank"
                    
                    con_p.appendChild(con_p_hrf);
                    items.appendChild(con_p);
                    box.appendChild(items);
                    //==================   items   end  ==========================
                    
                    //==================   sbmint_txt   ==========================
                    
                    var sbmint_txt = document.createElement("div");
                    sbmint_txt.className="sbmint_txt";
                    var sbm_ul = document.createElement("ul");
                    var sbm_ul_li = document.createElement("li");
                    sbm_ul_li.className="sbmint_txt_1";
                    sbm_ul_li.innerHTML="成员&nbsp;&nbsp;";
                    var sbm_ul_li_span = document.createElement("span");
                    sbm_ul_li_span.id="join_"+cir.circleid;
                    sbm_ul_li_span.innerHTML="<a id=\"cu"+cir.circleid+"\" target=\"_blank\" href=\"circle_user/"+cir.circleid+"\">"+cir.joincount+"</a>";
                    var sbm_ul_li2 = document.createElement("li");
                    sbm_ul_li2.innerHTML="发言&nbsp;&nbsp;";
                    var sbm_ul_li2_span = document.createElement("span");
                    sbm_ul_li2_span.id="comm_"+cir.circleid;
                    sbm_ul_li2_span.innerHTML="<a target=\"_blank\" href=\"circle/"+cir.circleid+".html\">"+cir.comcount+"</a>";
                    var sbm_aft_p = document.createElement("p");
                    
                    sbm_ul_li.appendChild(sbm_ul_li_span);
                    sbm_ul_li2.appendChild(sbm_ul_li2_span);
                    sbm_ul.appendChild(sbm_ul_li);
                    sbm_ul.appendChild(sbm_ul_li2);
                    sbmint_txt.appendChild(sbm_ul);
                    
                    var p_aft_span = document.createElement("span");
                    p_aft_span.innerHTML="标签";
                    var p_aft_a = document.createElement("a");
                   // p_aft_a.href="javascript:void();";
                    p_aft_a.innerHTML=cir.circletag;
                    
                    sbm_aft_p.appendChild(p_aft_span);
                    sbm_aft_p.appendChild(p_aft_a);
                    sbmint_txt.appendChild(sbm_aft_p);
                    
                    
                    box.appendChild(sbmint_txt);
                    
                    //================== sbmint_txt end  ==========================
                    
                    //==================     fabuzhe    ==========================
                    var con_sum = document.createElement("div");
                	con_sum.className = "fabuzhe";
                	
                	var con_mbs = document.createElement("div");
                	con_mbs.className = "mbs";
                	
                	var con_mbs_hrf = document.createElement("a");
                	con_mbs_hrf.target="_blank";
                	con_mbs_hrf.href="user/"+cir.userinfo.userid;
                	
                	var con_mbs_hrf_img = document.createElement("img");
                	con_mbs_hrf_img.src = cir.userinfo.otheraccountuserimage;
                	con_mbs_hrf_img.setAttribute("_src",cir.userinfo.otheraccountuserimage);
                	
                	con_mbs_hrf.appendChild(con_mbs_hrf_img);
                	
                	con_mbs.appendChild(con_mbs_hrf);
                	//----------------------------------------------
                	
                	var con_text = document.createElement("p");
                	con_text.className = "fabuzhe_text";
                	
                	var con_text_hrf = document.createElement("a");
                	con_text_hrf.target="_blank";
                	//con_text_hrf.href="quanzi/center.jsp?id=";
                	con_text_hrf.alt="";
                	con_text_hrf.innerHTML = "<font color=\"#f78d00\">圈主</font>：";
                	//----------------------------------------------
                	
                	var con_text_hrf2 = document.createElement("a");
                	con_text_hrf2.href="user/"+cir.userinfo.userid;
                	con_text_hrf2.alt="";
                	con_text_hrf2.target="_blank";
                	con_text_hrf2.innerHTML = cir.userinfo.nick;

                	//----------------------------------------------
                	
                	con_text.appendChild(con_text_hrf);
                	con_text.appendChild(con_text_hrf2);
                	
                	con_sum.appendChild(con_mbs);
                	con_sum.appendChild(con_text);
                	
                	box.appendChild(con_sum);
                	
                	//========================  fabuzhe  end  =========================

                    return box;
                }
                
                function addsum(box,ls){
                	if(ls != '' && ls.length >0){
	                	for(var i=0;i<ls.length;i++){
	                		var con_sum = document.createElement("div");
	                    	con_sum.className = "summary";
	                    	
	                    	var con_mbs = document.createElement("div");
	                    	con_mbs.className = "mbs";
	                    	
	                    	var con_mbs_hrf = document.createElement("a");
	                    	con_mbs_hrf.href="#";
	                    	
	                    	var con_mbs_hrf_img = document.createElement("img");
	                    	con_mbs_hrf_img.src = "images/user_02.gif";
	                    	
	                    	con_mbs_hrf.appendChild(con_mbs_hrf_img);
	                    	
	                    	con_mbs.appendChild(con_mbs_hrf);
	                    	//------------------------------------------
	                    	
	                    	
	                    	var con_text = document.createElement("p");
	                    	con_text.className = "feed_text";
	                    	
	                    	var con_text_hrf = document.createElement("a");
	                    	con_text_hrf.href="http://www.baidu.com";
	                    	con_text_hrf.alt= "";
	                    	con_text_hrf.innerHTML = "<font color=\"#3fbdec\">XOXOSunnyXOXO:</font>啊啊啊啊啊，喜欢他干净的笑容。";
	                    	
	                    	var con_text_br = document.createElement("br");
	                    	
	                    	var con_text_hrf2 = document.createElement("a");
	                    	con_text_hrf2.href="#";
	                    	con_text_hrf2.alt="";
	                    	con_text_hrf2.innerHTML = "2012-05-09";
	                    	
	                    	con_text.appendChild(con_text_hrf);
	                    	con_text.appendChild(con_text_br);
	                    	con_text.appendChild(con_text_hrf2);
	                    	
	                    	con_sum.appendChild(con_mbs);
	                    	con_sum.appendChild(con_text);
	                    	
	                    	box.appendChild(con_sum);
	                	}
                	}
                }
                
                //通过id得到对象
                function $aq(id) {
                    return document.getElementById(id);
                }

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

                //回到顶部
                toTop.onclick = function () {
                	goTop(0.5,24);
                };
                
                //设置热门话题初始化信息
                function sethotIniVal(num){
                	pageNum=1;
                	$("#one").html("");
                	$("#two").html("");
                	$("#three").html("");
                	$("#four").html("");
                	$("#five").html("");
                	$("#hotvals").val(num);
                }
                
                //设置标签初始化信息
                function settagIniVal(id){
                	pageNum=1;
//                	$("#one").html("");
//                	$("#two").html("");
//                	$("#three").html("");
//                	$("#four").html("");
//                	$("#five").html("");
                	$("#tagvals").val(id);
                }
                
                function read(){
                	cnt++;
                	$.ajax({                 
    					url: websiteUrl.indexinfo_link,  
    					data : {pageNum : pageNum,pageSize : indexshowrows*divCount,hot : $("#hotvals").val(),tag : $("#tagval").val()},
    					type: "POST",     
    					dataType : "json",
    					async:true,
    					success: function(data) {
    						var json = data.data.circle_ls;
    						islogin = data.data.islogin;
    						res = eval(json);
    						if(res.length < (indexshowrows*divCount)){
    							$("#end").css('display','block'); 
    						}
    						//给热门话题 加底色

    						$("#tag"+$("#tagval").val()).css("color","#f78d00");
    						loadImg();
    						if(pageNum == 2 && cnt < 2){
    							$("#box_news").css("display","block");
    						}
    						imgLoadPre();
    					}
    				});   
                	pageNum++;
                }
                function getMinHeight(){
                	var one = document.getElementById("ones").offsetHeight;
                	var two = document.getElementById("twos").offsetHeight;
                	var thr = document.getElementById("threes").offsetHeight;
//                	var fou = document.getElementById("fours").offsetHeight;
                	var fiv = document.getElementById("fives").offsetHeight;
                	var darray = [one,two,thr,fiv];
                	var nn = min(darray);
                	return nn;
                }
                
                function getNum(){
                	var one = document.getElementById("ones").offsetHeight;
                	var two = document.getElementById("twos").offsetHeight;
                	var thr = document.getElementById("threes").offsetHeight;
//                	var fou = document.getElementById("fours").offsetHeight;
                	var fiv = document.getElementById("fives").offsetHeight;
                	var darray = [one,two,thr,fiv];
                	var nn = min(darray);
                	if(nn == one){return 0;}
                	else if(nn == two){return 1;}
                	else if(nn == thr){return 2;}
//                	else if(nn == fou){return 3;}
                	else if(nn == fiv){return 3;}
                }
                
                function min(darray) {
            		var min = darray[0];  
            		var len = darray.length;
            		for(var j=0;j<5;j++){
            			for (var i = 1; i < len; i++){  
            				if (darray[i] < min){  
            					min = darray[i];
            				}  
            			}  
            		}
            		return min;
            	};
            };
            
        	
        	function getTagTopic(){
        		$.ajax({                 
					url: websiteUrl.tagtopic_link,  
					data : {type : 1},
					type: "POST",     
					dataType : "json",
					async:false,
					success: function(data) {
						var tagjson = data.data.tag_ls;
						for ( var i = 0; i < tagjson.length; i++) {
							var tagname = tagjson[i].tagname;
							var ttid = tagjson[i].ttid;
							if(ttid!=1)
							{
								var name = "typecircleid";
								if(i>=1 && i<=6)
								{
									bindclick(tagname,ttid,'tagtype',i)
									bindvalue(tagname,ttid,'tagtype',i)
								}else{
									
									if(i>6 && i<=10)
									{
										if(i==10)
										{
											moreTypeHTML(i,name,"row last");
										}else{
											moreTypeHTML(i,name,"row");
										}
									}
									if(i>6 && i<=13)
									{
										moreTypeTag(7,i,ttid,tagname,name);
									}
									if(i>13 && i<=20)
									{
										moreTypeTag(8,i,ttid,tagname,name);
									}
									if(i>20 && i<=27)
									{
										moreTypeTag(9,i,ttid,tagname,name);
									}
									if(i>27 && i<=35)
									{
										moreTypeTag(10,i,ttid,tagname,name);
									}
								}
							}
						}
					}             
				});   
            }
        	/**
        	 * 绑定点击事件
        	 * @param tagname
        	 * @param tid
        	 * @param name
        	 * @param i
        	 * @return
        	 */
        	function bindclick(tagname,tid,name,i)
        	{
        		$("#"+name+i).bind("click",function(){
        			opencircle(tid);
				});
        	}
        	/**
        	 * 写入value
        	 * @param tagname
        	 * @param tid
        	 * @param name
        	 * @param i
        	 * @return
        	 */
        	function bindvalue(tagname,tid,name,i)
        	{
//        		alert(tagname+"  "+tid+"   "+name+" "+i);
        		$("#"+name+i).html(tagname);
        	}
        	/**
        	 * 追加标签div
        	 * @param i
        	 * @param css
        	 * @return
        	 */
        	function moreTypeHTML(i,name,css)
        	{
        		var strHTMl = "<div class=\""+css+"\"><ul id=\""+name+i+"\"></ul></div>";
        		$("#nav_bar_more_categories").append(strHTMl);
        	}
        	function moreTypeTag(i,index,id,tagname,name)
        	{
        		$("#"+name+i).append("<li><a id=\"circleid"+index+"\" onclick = \"opencircle('"+id+"')\" href=\"javascript:void(0);\">"+tagname+"</a></li>");
        	}
        	function opencircle(id)
        	{
        		window.open(basePath+"quanzi/type.jsp?hot=2&type="+id);
        	}
        	//加入圈子
        	function join_or_quit_quanzi(id,ttyp,userid){
        		$.ajax({                 
        			url: websiteUrl.joinquanzi_link,  
        			data : {id : id ,type : ttyp,userid:userid},
        			type: "POST",     
        			dataType : "json",
        			async:false,
        			success: function(data) {
        				var flag = data.flag;
        				if(ttyp == 1){
        					//加入圈子
        					if(flag){
        						alert("加入圈子成功！");
        						$("#jimg_"+id).attr("src","images/s-btn_01_h.gif");
        						$("#jimg_"+id).attr("_src","images/s-btn_01_h.gif");
        						$("#cu"+id).html(parseInt($("#cu"+id).html())+1);
        					}else{
        						alert("加入圈子失败！");
        					}
        				}else if(ttyp == 2){
        					//退出圈子
        					if(flag){
        						alert("退出圈子成功！");
        						$("#jimg_"+id).attr("src","images/s-btn_01.gif");
        						$("#jimg_"+id).attr("_src","images/s-btn_01.gif");
        						$("#join_"+id).html(parseInt($("#join_"+id).html())-1);
        					}else{
        						alert("退出圈子失败！");
        					}
        				}
        			}             
        		});
        	}
        	function createquanzi()
        	{
        		if(islogin)
        		{
        			location.href = basePath+"circleInfoAction_create.do";
        		}else{
        			alert("你还没有登录");
        			myScroll();
        			$("#window_dl").css("display","block");
        		}		
        	}
//        	indexFeiwen();
        	function indexFeiwen()
        	{
        		var indexFeiwenURL="feiWenAction_indexFeiWen.do";
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
    			                            "<div class=\"fei_r\"><p><a href=\"feiwenshow/"+feiwen[f].fwid+"?tid="+tid+"\" target=\"_blank\" title="+titleStr+">"+title+"</a></p></div>"+                             
    		                            "</li>";
    	        					$("#feiwen").append(feiwenHTML);
    							}
        					}
						}
//        				getCircleTotop();
        			},
        			error : function(data) {
        				location.href=basePath;
        			}
        		});
        	}
    function getCircleTotop(){
    	var getCircleTotopURL="jsonInfoAction_getCircleTotop.do";
		$.ajax({
			url : getCircleTotopURL,
			type : 'POST',
			dataType : 'json',
			success : function(data){
				var circleList = data.data.circleList;
				for(var i = 0; i<circleList.length; i++){
					var circleName = circleList[i].circlename;
					var tagname = circleList[i].circletag;
					if(tagname.length >=20){
						tagname = tagname.substring(0,20);
					}
					var str;
					if((i+1)%4 == 0){
						 str = "<li class=\"re_list\">" +
							"<a target=\"_blank\" href=\"circle/"+circleList[i].circleid+".html\"><img _src=\""+circleList[i].circlebigimg+"\" src=\"images/preimg.gif\" width=\"220px\" height=\"354px\"/></a>" +
							"<div class=\"re_yin\"></div>" +
							"<p><a target=\"_blank\" href=\"circle/"+circleList[i].circleid+".html\">"+circleName+"</a><span>"+tagname+"</span></p>" +
							"</li>";
					}else{
						 str = "<li>" +
							"<a target=\"_blank\" href=\"circle/"+circleList[i].circleid+".html\"><img _src=\""+circleList[i].circlebigimg+"\" src=\"images/preimg.gif\" width=\"220px\" height=\"354px\"/></a>" +
							"<div class=\"re_yin\"></div>" +
							"<p><a target=\"_blank\" href=\"circle/"+circleList[i].circleid+".html\">"+circleName+"</a><span>"+tagname+"</span></p>" +
							"</li>";
					}
					$("#Recommend").append(str);
				}
				/////
				var zcCircleinfo = data.data.zcCircleinfo;
				var yundongCircleinfo = data.data.yundongCircleinfo;
				var wenhuaCircleinfo = data.data.wenhuaCircleinfo;
				var jujiaCircleinfo = data.data.jujiaCircleinfo;
				var qitaCircleinfo = data.data.qitaCircleinfo;
				createTagHTML(zcCircleinfo,1);
				createTagHTML(yundongCircleinfo,2);
				createTagHTML(wenhuaCircleinfo,3);
				createTagHTML(jujiaCircleinfo,4);
				createTagHTML(qitaCircleinfo,5);
			},
			error : function(data){
				
			}
		});
    }
    function createTagHTML(circleinfos,i)
    {
    	for ( var j = 0; j < circleinfos.length; j++) {
    		var circleName = circleinfos[j].circlename;
    		var html = document.createElement("li");
    		var aimg = document.createElement("a");
    		if((j+1)%6==0){html.className = "le_list";}
    		aimg.href="circle/"+circleinfos[j].circleid+".html";
    		html.appendChild(aimg);
    		var img = document.createElement("img");
//    		img.src=circleinfos[j].circlebigimg;
    		img.src="images/preimg.gif";
    		img.setAttribute("_src",circleinfos[j].circlebigimg);
    		img.style.width = "146px";
    		img.style.height = "195px";
    		aimg.appendChild(img);
    		var divs = document.createElement("div");
    		divs.className="le_yin";
    		html.appendChild(divs);
    		var p = document.createElement("p");
    		html.appendChild(p);
    		var a = document.createElement("a");
    		a.href="circle/"+circleinfos[j].circleid+".html";
    		a.innerHTML = circleName;
    		p.appendChild(a)
			if(i==1)
			{
				$("#zcDIV").append(html);
			}
			if(i==2)
			{
				$("#yundongDIV").append(html);
			}
			if(i==3)
			{
				$("#wenhuaDIV").append(html);
			}
			if(i==4)
			{
				$("#jujiaDIV").append(html);
			}
			if(i==5)
			{
//				alert(circleinfos[j].circlename);
				$("#qitaDIV").append(html);
			}
		}
    }
