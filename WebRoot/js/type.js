			var cnt = 0;
			var pageNum;
			var divCount;
			var res;
			var end;
			var islogin;
            window.onload = function () {
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
                var div4 = $aq("four");
//                var div5 = $aq("five");
                var loading = $aq("loading");
                var load = $aq("load");
                end = $aq("end");
                var toTop = $aq("toTop");
                //得到浏览器的名称
                var browser = getBrowser();
                $("#load").bind("click",function(){
                	read();
                });
                //初始化
                read();
                //主会场
                window.onscroll = fun_scroll;
                //滚动方法
                function fun_scroll() {
                    //body的高度
                    var topAll = (browser == "Firefox") ? document.documentElement.scrollHeight : document.body.scrollHeight;
                    //卷上去的高度
//                    var top_top = document.body.scrollTop || document.documentElement.scrollTop;
//                    var top_div = document.getElementById("top_down_out");
//                    if (top_top >= 1) {
//                        top_div.className = "top_down_out0 fillet";
//                    }
//                    if (top_top <= 10) {
//                        top_div.className = "top_down_out fillet";
//                    }
                    //回到顶部按钮操作
//                    if (top_top > showTopButtonHeight)
//                        toTop.style.display = "block";
//                    else
//                        toTop.style.display = "none";
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
                    //控制滚动条次数以及判断是否到达底部
                    if (reset == 0) {
                    	var position = getMinHeight();
                        var top_top = document.body.scrollTop || document.documentElement.scrollTop; //卷上去的高度
                        if((top_top+surplusHeight) > position+400 || top_top == position){
                        	setTimeout(read, 1);
                            reset = 1;
                        }
                    } else {
                        setTimeout(function () { reset = 0; }, 1);
                    }
                }
                //加载图片
                function loadImg() {
                    loading.style.display = "none";
                    load.style.display = "none";
                    var num =0;
                    for(var i=0;i<res.length;i++){
//                    		num = getNum();
                    		if(num>=4){num = 0;}
                        	if(num == 0){div1.appendChild(addDiv(res[i]));}
                        	else if(num == 1){div2.appendChild(addDiv(res[i]));}
                        	else if(num == 2){div3.appendChild(addDiv(res[i]));}
                        	else if(num == 3){div4.appendChild(addDiv(res[i]));}
//                        	else if(num == 4){div5.appendChild(addDiv(res[i]));}
                        	num++;
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
                    	}else{
                    		img1.src="images/s-btn_01.gif";
                    		img1.setAttribute("onclick","join_or_quit_quanzi('"+cir.circleid+"',1)");
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
                	container_hrf.href="cir2/1/"+cir.circleid+".html";
                	container_hrf.target = "_blank";
                    var con_img = document.createElement("img");
                    con_img.alt = "";
                    con_img.src = cir.circlemiddleimg;
                    con_img.style.width = imgWidth;
                    
                    container_hrf.appendChild(con_img);
                    container.appendChild(container_hrf);
                    items.appendChild(container);
                        
                    //----------------------------圈子名称
                    //内侧p标签 2
                    var con_p = document.createElement("p");
                    con_p.className = "c_grey";
                    
                    var con_p_hrf = document.createElement("a");
                    con_p_hrf.href="cir2/1/"+cir.circleid+".html";
                    con_p_hrf.target="_blank";
                    con_p_hrf.title=cir.circlename;
                    con_p_hrf.innerHTML = sub(cir.circlename,12);
                    
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
                    sbm_ul_li_span.innerHTML="<a target=\"_blank\" id=\"cu"+cir.circleid+"\" href=\"cir_user/"+cir.circleid+".html\">"+cir.joincount+"</a>";
                    var sbm_ul_li2 = document.createElement("li");
                    sbm_ul_li2.innerHTML="发言&nbsp;&nbsp;";
                    var sbm_ul_li2_span = document.createElement("span");
                    sbm_ul_li2_span.id="comm_"+cir.circleid;
                    sbm_ul_li2_span.innerHTML="<a target=\"_blank\" href=\"cir2/1/"+cir.circleid+".html\">"+cir.comcount+"</a>";
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
                	con_mbs_hrf.href="user/"+cir.userinfo.userid+".html";
                	
                	var con_mbs_hrf_img = document.createElement("img");
                	con_mbs_hrf_img.src = cir.userinfo.otheraccountuserimage;
                	
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
                	con_text_hrf2.href="user/"+cir.userinfo.userid+".html";
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

//                //回到顶部
//                toTop.onclick = function () {
//                	goTop(0.5,24);
//                };
                function read(){
                	cnt++;
                	var realurl = location.href;
                	var idindexof = realurl.lastIndexOf("/");
                	var hot = realurl.substring(realurl.search("type/")+5,idindexof);
                	var tag = realurl.substring(idindexof + 1,realurl.search(".html"));
                	$.ajax({                 
    					url: websiteUrl.indexinfo_link,  
    					data : {pageNum : pageNum,pageSize : indexshowrows*divCount,hot :hot,tag : tag},
    					type: "POST",     
    					dataType : "json",
    					async:true,
    					success: function(data) {
    						var json = data.data.circle_ls;
    						islogin = data.data.islogin;
    						var tagname = data.data.tagname;
    						res = eval(json);
    						if(res.length < (indexshowrows*divCount)){
    							$("#end").css('display','block'); 
    						}
    						$("#tagname").html(tagname);
    						loadImg();
    					}             
    				});   
                	pageNum++;
                }
                
                function getMinHeight(){
                	var one = document.getElementById("ones").offsetHeight;
                	var two = document.getElementById("twos").offsetHeight;
                	var thr = document.getElementById("threes").offsetHeight;
                	var fou = document.getElementById("fours").offsetHeight;
//                	var fiv = document.getElementById("fives").offsetHeight;
                	var darray = [one,two,thr,fou];
                	var nn = min(darray);
                	return nn;
                }
                
                function getNum(){
                	var one = document.getElementById("ones").offsetHeight;
                	var two = document.getElementById("twos").offsetHeight;
                	var thr = document.getElementById("threes").offsetHeight;
                	var fou = document.getElementById("fours").offsetHeight;
//                	var fiv = document.getElementById("fives").offsetHeight;
                	var darray = [one,two,thr,fou];
                	var nn = min(darray);
                	if(nn == one){return 0;}
                	else if(nn == two){return 1;}
                	else if(nn == thr){return 2;}
                	else if(nn == fou){return 3;}
//                	else if(nn == fiv){return 4;}
                }
                
                function min(darray) {
            		var min = darray[0];  
            		var len = darray.length;
            		for(var j=0;j<4;j++){
            			for (var i = 1; i < len; i++){  
            				if (darray[i] < min){ 
            					min = darray[i];
            				}  
            			}  
            		}
            		return min;
            	};
            };
            
        	//加入圈子
        	function join_or_quit_quanzi(id,ttyp){
        		$.ajax({                 
        			url: websiteUrl.joinquanzi_link,  
        			data : {id : id ,type : ttyp},
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
        						$("#cu"+id).html(parseInt($("#cu"+id).html())+1);
        					}else{
        						alert("加入圈子失败！");
        					}
        				}else if(ttyp == 2){
        					//退出圈子
        					if(flag){
        						alert("退出圈子成功！");
        						$("#jimg_"+id).attr("src","images/s-btn_01.gif");
        						$("#join_"+id).html(parseInt($("#join_"+id).html())-1);
        					}else{
        						alert("退出圈子失败！");
        					}
        				}
        			}             
        		});
        	}