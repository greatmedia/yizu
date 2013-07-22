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
                        	if(num == 0){div1.appendChild(addDiv(res[i]));}
                        	else if(num == 1){div2.appendChild(addDiv(res[i]));}
                        	else if(num == 2){div3.appendChild(addDiv(res[i]));}
                        	else if(num == 3){div4.appendChild(addDiv(res[i]));}
                        	else if(num == 4){div5.appendChild(addDiv(res[i]));}
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
                    	container_hrf.href="quanzi/quanzi_details.jsp?id="+$("#id").val()+"&did="+cir.circledetailid;
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
                    con_p_hrf.href="quanzi/quanzi_details.jsp?id="+$("#id").val()+"&did="+cir.circledetailid;
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
                     addsum(box,ls,cir.circledetailid);
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
                         sum_img.src="images/user_02.gif";
                         
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
                
                function addsum(box,ls,did){
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
                	goTop(0.1,24);
                };
                function abc(){
                	alert("dd");
                }
                
                function read(){
                	$.ajax({                 
    					url: websiteUrl.quanzidetail_list_link,  
    					data : {pageNum : pageNum,pageSize : indexshowrows*divCount,id : $("#id").val()},
    					type: "POST",     
    					dataType : "json",
    					async:false,
    					success: function(data) {
    						var json = data.data.circledetailinfo_ls;
    						islogin = data.data.islogin;
    						res = eval(json);
    						if(res.length < (indexshowrows*divCount)){
    							$("#end").css('display','block'); 
    						}
    						loadImg();
    					}             
    				});   
                	pageNum++;
                }
                
                function getMinHeight(){
                	var one = document.getElementById("one").offsetHeight;
                	var two = document.getElementById("two").offsetHeight;
                	var thr = document.getElementById("three").offsetHeight;
                	var fou = document.getElementById("four").offsetHeight;
                	var fiv = document.getElementById("five").offsetHeight;
                	var darray = [one,two,thr,fou,fiv];
                	var nn = min(darray);
                	return nn;
                }
                
                function getNum(){
                	var one = document.getElementById("one").offsetHeight;
                	var two = document.getElementById("two").offsetHeight;
                	var thr = document.getElementById("three").offsetHeight;
                	var fou = document.getElementById("four").offsetHeight;
                	var fiv = document.getElementById("five").offsetHeight;
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
            

        	function getQuanzi(){
            	$.ajax({                 
					url: websiteUrl.quanzi_link,  
					data : {id : $("#id").val()},
					type: "POST",     
					dataType : "json",
					async:false,
					success: function(data) {
						var cir = data.data.circle;
						var jcount = data.data.joincount;
						var islogin = data.data.islogin;
						var ttag = cir.circletag;
						var tags = ttag.replace("，",",").split(",");
						$("#tags").html("标签：");
						for(var i=0;i<tags.length;i++){
							$("#tags").append("<a href=\"#\">"+tags[i]+"</a>");
						}
						$("#quanzi_title").html(cir.circlename);
						$("#q_title").html(cir.circlename);
						$("#joincount").html(cir.joincount);
						$("#visitcount").html(cir.visitcount);
						$("#summary").html(cir.summary);
						$("#u_name").html(cir.userinfo.nick);
						$("#u_name2").html("#"+cir.userinfo.nick+"#:&nbsp;");
						$("#u_img").attr("src",cir.userinfo.otheraccountuserimage);
						$("#q_img").attr("src",cir.circlemiddleimg);
						$("#member").attr("href","quanzi/members.jsp?id="+cir.circleid);
						
						if(islogin){
							//已经登录
							$("#fabu").css("display","block");
							if(jcount > 0){
								//已经加入
								$("#join").css("display","none");
								$("#a_join").css("display","block");
								$("#quit").css("display","block");
							}else{
								//没有加入
								$("#join").css("display","block");
								$("#a_join").css("display","none");
								$("#quit").css("display","none");
							};
						}else{
							//未登录
							$("#join").css("display","none");
							$("#join").css("display","none");
							$("#quit").css("display","none");
							$("#fabu").css("display","none");
						};
					}        
				});   
            }
        	
        	//加入圈子
        	function join_or_quit_quanzi(did,ttyp){
        		$.ajax({                 
        			url: websiteUrl.joinquanzi_link,  
        			data : {id : did ,type : ttyp},
        			type: "POST",     
        			dataType : "json",
        			async:false,
        			success: function(data) {
        				var flag = data.flag;
        				if(ttyp == 1){
        					//加入圈子
        					if(flag){
        						alert("加入圈子成功！");
        						$("#join").css("display","none");
        						$("#a_join").css("display","block");
        						$("#quit").css("display","block");
        					}else{
        						alert("加入圈子失败！");
        					}
        				}else if(ttyp == 2){
        					//退出圈子
        					if(flag){
        						alert("退出圈子成功！");
        						$("#join").css("display","block");
        						$("#a_join").css("display","none");
        						$("#quit").css("display","none");
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
            