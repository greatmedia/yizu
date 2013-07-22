// 			var urlinfo = window.location.href;//獲取url
//        	var url = decodeURI(urlinfo)
//        	var idindexof = url.lastIndexOf("=");
//        	var strsssss = url.substring(idindexof + 1);
//        	var s = $("#keywords").val(strsssss);
            var cnt = 0;
			var pageNum;
			var divCount;
			var res;
			var end;
			var islogin;
            window.onload = function () {
            	var urlinfo = window.location.href;//獲取url
            	var url = decodeURI(urlinfo)
            	var idindexof = url.lastIndexOf("=");
            	var strsssss = url.substring(idindexof + 1);
            	var s = $("#keywords").val(strsssss);
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
                $("#search_circle").bind("click",function(){
                	setSearchInit(1);
                	read();
                });
                $("#search_circledetail").bind("click",function(){
                	setSearchInit(2);
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
                    var top_top = document.body.scrollTop || document.documentElement.scrollTop;
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
                }
                //加载图片
                function loadImg() {
                    loading.style.display = "none";
                    load.style.display = "none";
                    var num =0;
                    for(var i=0;i<res.length;i++){
                    		num = getNum();
                    		var addDivMethod = $("#s_type").val() == "1" ? addDiv(res[i]) : addDiv2(res[i]);
                        	if(num == 0){div1.appendChild(addDivMethod);}
                        	else if(num == 1){div2.appendChild(addDivMethod);}
                        	else if(num == 2){div3.appendChild(addDivMethod);}
                        	else if(num == 3){div4.appendChild(addDivMethod);}
                        	else if(num == 4){div5.appendChild(addDivMethod);}
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
                    sbm_ul_li_span.innerHTML="<a target=\"_blank\" href=\"cir_user/"+cir.circleid+".html\">"+cir.joincount+"</a>";
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
                	con_mbs_hrf.href="user/"+cir.userinfo.userid+".html"
                	
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
                
                
                function addDiv2(cir) {
                    //数组下标的随机值
                    var box = document.createElement("div");
                    box.className = "box";
                    box.setAttribute("onmouseover","dis('"+cir.circledetailid+"', 'show')");
                    box.setAttribute("onmouseout","dis('"+cir.circledetailid+"', 'hide')");
                    //=================  fenxiang  ==========================
                    //漂浮层
                    var fenxiang = document.createElement("div");
                    fenxiang.className="fenxiang";
                    fenxiang.setAttribute("id",cir.circledetailid);
                    fenxiang.setAttribute("name","abc");
                    fenxiang.setAttribute("onmouseover","dis('"+cir.circledetailid+"', 'show')");
                    fenxiang.setAttribute("onmouseout","dis('"+cir.circledetailid+"', 'hide')");
                    
                    //内侧ul
                    var ul = document.createElement("ul");
                    
                    //内侧li1
                    var li1 = document.createElement("li");
                    var img1 = document.createElement("img");
                    img1.src="images/sc_btn.gif";
                    li1.appendChild(img1);
                    //内侧li2
                    var li2 = document.createElement("li");
                    var img2 = document.createElement("img");
                    img2.src="images/xh_btn.gif";
                    li2.appendChild(img2);
                    //内侧li3
                    var li3 = document.createElement("li");
                    var img3 = document.createElement("img");
                    img3.src="images/pl_btn.gif";
                    li3.appendChild(img3);
                    
                    ul.appendChild(li1);
                    ul.appendChild(li2);
                    ul.appendChild(li3);
                    
                    fenxiang.appendChild(ul);
                    
                    box.appendChild(fenxiang);
                    
                    //===============  fenxiang end ==========================
                    
                    //==================   items   ==========================
                    //图片层
                    var items = document.createElement("div");
                    items.className = "items";
                    
                    var imgls = cir.circleDetailImg;
                    for(var i=0;i<imgls.length;i++){
                    	//内侧的div 1 图片
                        var container = document.createElement("div");
                        container.className = "feed_container";
                        //图片外部的链接
                    	var container_hrf = document.createElement("a");
                    	container_hrf.href="details/"+cir.circledetailid+".html";
                        var con_img = document.createElement("img");
                        con_img.alt = "";
                        con_img.src = imgls[i].middleimg;
                        
                        container_hrf.appendChild(con_img);
                        container.appendChild(container_hrf);
                        items.appendChild(container);
                    }
                        
                    //----------------------------圈子名称
                    //内侧p标签 2
                    var con_p = document.createElement("p");
                    con_p.className = "c_grey";
                    
                    var con_p_hrf = document.createElement("a");
                    con_p_hrf.href="details/"+cir.circledetailid+".html";
                    //con_p_hrf.innerHTML = cir.summary;
                    con_p_hrf.innerHTML = cir.circlecontent;
                    
                    con_p.appendChild(con_p_hrf);
                    items.appendChild(con_p);
                    box.appendChild(items);
                    //==================   items   end  ==========================
                    
                    //==================     fabuzhe    ==========================
                    var con_sum = document.createElement("div");
                	con_sum.className = "fabuzhe";
                	
                	var con_mbs = document.createElement("div");
                	con_mbs.className = "mbs";
                	
                	var con_mbs_hrf = document.createElement("a");
                	con_mbs_hrf.href="#";
                	
                	var con_mbs_hrf_img = document.createElement("img");
                	con_mbs_hrf_img.src = cir.userinfo.otheraccountuserimage;
                	
                	con_mbs_hrf.appendChild(con_mbs_hrf_img);
                	
                	con_mbs.appendChild(con_mbs_hrf);
                	//----------------------------------------------
                	
                	var con_text = document.createElement("p");
                	con_text.className = "feed_text";
                	
                	var con_text_hrf = document.createElement("a");
                	con_text_hrf.href="javascript:void(0);";
                	con_text_hrf.innerHTML = "<font color=\"#f78d00\">"+cir.userinfo.nick+": </font>发布";
                	//----------------------------------------------
                	
                	var con_text_br = document.createElement("br");
                	
                	var con_text_hrf2 = document.createElement("a");
                	con_text_hrf2.href="javascript:void(0);";
                	var smpl = [];
                	smpl.push("<font color=\"#666666\" id=\"did_count_"+cir.circledetailid+"\">"+cir.comcount+"</font>&nbsp;评论&nbsp;&nbsp;&nbsp;");
                	smpl.push("<font color=\"#666666\" id=\"did_visit_"+cir.circledetailid+"\">"+cir.visitcount+"</font>&nbsp;访问");
                	con_text_hrf2.innerHTML = smpl.join("");
                	
                	//----------------------------------------------
                	
                	con_text.appendChild(con_text_hrf);
                	con_text.appendChild(con_text_br);
                	con_text.appendChild(con_text_hrf2);
                	
                	con_sum.appendChild(con_mbs);
                	con_sum.appendChild(con_text);
                	
                	box.appendChild(con_sum);

                    
                    //==================summary1==========================
                     var ls = cir.circleCommentInfo;
                    //添加回复信息列表
                     addsum2(box,ls,cir.circledetailid);
                     if(islogin){
                    	//==================summary2==========================
                         var con_sum2 = document.createElement("div");
                         con_sum2.className = "summary1";
                         con_sum2.setAttribute("id","sumry"+cir.circledetailid);
                         //---------------------------------------------------
                         
                         var sumimg = document.createElement("div");
                         sumimg.className="sumimg";;
                         sumimg.setAttribute("id","sumimg"+cir.circledetailid);
                         
                         var sum_img = document.createElement("img");
                         sum_img.src=$("#other_user_img").attr("src");
                         
                         sumimg.appendChild(sum_img);
                         con_sum2.appendChild(sumimg);
                         
                         var form_input = document.createElement("input");
                         form_input.name="textD";
                         form_input.id="textD"+cir.circledetailid;
                         form_input.value="评论...";
                         form_input.title="按回车键发表评论";
                         form_input.setAttribute("onblur","_inputOnBlur(this, 'sumry"+cir.circledetailid+"', 'sumimg"+cir.circledetailid+"')");
                         form_input.setAttribute("onkeydown","_inputOnKeyDown(this, 'sumry"+cir.circledetailid+"', 'sumimg"+cir.circledetailid+"','"+cir.circledetailid+"',event)");
                         form_input.setAttribute("onfocus","_inputOnFocus(this, 'sumry"+cir.circledetailid+"', 'sumimg"+cir.circledetailid+"')");
                         
                         con_sum2.appendChild(form_input);
          
                         box.appendChild(con_sum2);
                     }
                    return box;
                }
                
                function addsum2(box,ls,did){
                	var comment = document.createElement("div");
                	comment.id="c_"+did;
                	box.appendChild(comment);
                	if(ls != '' && ls.length >0){
	                	for(var i=0;i<ls.length;i++){
	                		var con_sum = document.createElement("div");
	                    	con_sum.className = "summary";
	                    	
	                    	var con_mbs = document.createElement("div");
	                    	con_mbs.className = "mbs";
	                    	
	                    	var con_mbs_hrf = document.createElement("a");
	                    	con_mbs_hrf.href="#";
	                    	
	                    	var con_mbs_hrf_img = document.createElement("img");
	                    	con_mbs_hrf_img.src = ls[i].userinfo.otheraccountuserimage;
	                    	
	                    	con_mbs_hrf.appendChild(con_mbs_hrf_img);
	                    	
	                    	con_mbs.appendChild(con_mbs_hrf);
	                    	//------------------------------------------
	                    	
	                    	
	                    	var con_text = document.createElement("p");
	                    	con_text.className = "feed_text";
	                    	
	                    	var con_text_hrf = document.createElement("a");
	                    	con_text_hrf.href="javascript:void(0);";
	                    	con_text_hrf.alt= "";
	                    	con_text_hrf.innerHTML = "<font color=\"#3fbdec\">"+ls[i].userinfo.nick+":</font>"+ls[i].commentinfo;
	                    	
	                    	var con_text_br = document.createElement("br");
	                    	
	                    	var con_text_hrf2 = document.createElement("a");
	                    	con_text_hrf2.href="#";
	                    	con_text_hrf2.alt="";
	                    	con_text_hrf2.innerHTML = ls[i].createdate;
	                    	
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
                
              //设置搜索类型
                function setSearchInit(num){
                	pageNum=1;
                	$("#one").html("");
                	$("#two").html("");
                	$("#three").html("");
                	$("#four").html("");
                	$("#five").html("");
                	$("#s_type").val(num);
                }
                function read(){
                	var urlinfo = window.location.href;//獲取url
                	var url = decodeURI(urlinfo)
                	var idindexof = url.lastIndexOf("=");
                	var str = url.substring(idindexof + 1);
                	cnt++;
                	$.ajax({                 
    					url: websiteUrl.getSearchJsonInfo_link,  
    					data : {pageNum : pageNum,pageSize : indexshowrows*divCount, type : $("#s_type").val(),content : str},
    					type: "POST",     
    					dataType : "json",
    					async:true,
    					success: function(data) {
    						var json = data.data.ls;
    						islogin = data.data.islogin;
    						res = eval(json);
    						if(pageNum == 2){
    							var ccount = data.data.circle_count;
    							var cdcount = data.data.circledetail_count;
    							$("#search_circle").html(ccount+"个圈子");
    							//$("#search_circledetail").html(cdcount+"个回复");
    							var urlinfo = window.location.href;//獲取url
    				        	var url = decodeURI(urlinfo)
    				        	var idindexof = url.lastIndexOf("=");
    				        	var strsssss = url.substring(idindexof + 1);
    							$("#search_key").html("<b>--搜索结果："+strsssss+"</b>");
    						}
    						loadImg();
    						if(res.length < (indexshowrows*divCount)){
    							$("#end").css('display','block'); 
    						}
    					}             
    				});   
                	pageNum++;
                }
                
                function getMinHeight(){
                	var one = document.getElementById("ones").offsetHeight;
                	var two = document.getElementById("twos").offsetHeight;
                	var thr = document.getElementById("threes").offsetHeight;
                	var fou = document.getElementById("fours").offsetHeight;
                	var fiv = document.getElementById("fives").offsetHeight;
                	var darray = [one,two,thr,fou,fiv];
                	var nn = min(darray);
                	return nn;
                }
                
                function getNum(){
                	var one = document.getElementById("ones").offsetHeight;
                	var two = document.getElementById("twos").offsetHeight;
                	var thr = document.getElementById("threes").offsetHeight;
                	var fou = document.getElementById("fours").offsetHeight;
                	var fiv = document.getElementById("fives").offsetHeight;
                	var darray = [one,two,thr,fou,fiv];
                	var nn = min(darray);
                	if(nn == one){return 0;}
                	else if(nn == two){return 1;}
                	else if(nn == thr){return 2;}
                	else if(nn == fou){return 3;}
                	else if(nn == fiv){return 4;}
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
        						$("#join_"+id).html(parseInt($("#join_"+id).html())+1);
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
        	
        	//添加评论
        	function addComment(ids){
        		if(islogin){
        			if($("#cmt_"+ids).val() == ''){
        				alert("请填写评论信息！");return;
        			}
        			$.ajax({                 
        				url: websiteUrl.addcomment_link,  
        				data : {id : ids,content : $("#textD"+ids).val()},
        				type: "POST",     
        				dataType : "json",
        				async:true,
        				success: function(data) {
        					var flag = data.flag;
        					var comm = data.data.circleCommetnInfo;
        					if(flag){
        						alert("评论成功！");
        						 $("#cmt_"+ids).val("");
        						 var comtinfo = [];
        						 comtinfo.push("<div class=\"summary\">");
        						 comtinfo.push("<div class=\"mbs\">");
        						 comtinfo.push("<a href=\"#\"> <img src=\""+comm.userinfo.otheraccountuserimage+"\"></a>");
        						 comtinfo.push("</div>");
        						 comtinfo.push("<p class=\"feed_text\">");
        						 comtinfo.push("<a href=\"#\"><font color=\"#3fbdec\">"+comm.userinfo.nick+":</font>"+comm.commentinfo+"</a><br />");
        						 comtinfo.push("<a>"+comm.createdate+"</a>");
        						 comtinfo.push("</p>");
        						 comtinfo.push("</div>");
        						 $("#c_"+ids).after(comtinfo.join(""));
        						 $("#did_count_"+ids).html(parseInt($("#did_count_"+ids).html())+1);
        					}else{
        						alert("评论失败！");
        					}
        				}             
        			}); 
        		}else{
        			alert("您还没有登录，登录后才可以评论！");
        		}
        	}
        	function createquanzi()
        	{
        		if(islogin)
        		{
        			window.location.href = basePath+"circleInfoAction_create.do";
        		}else{
        			alert("你还没有登录");
        			$("#window_dl").css("display","block");
        		}		
        	}