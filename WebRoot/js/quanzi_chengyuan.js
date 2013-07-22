	var pageNum;
			var divCount;
			var res;
			var end;
			pageNum = 1;
            var showTopButtonHeight = 500;//回到顶部按钮的距离
            var div1;
            var div2;
            var div3;
            var div4;
            var div5;
            window.onload = function () {
            	var toTop = $aq("toTop");
                //初始化
                read();
                //主会场
                window.onscroll = fun_scroll;
                //滚动方法
                function fun_scroll() {
                    //卷上去的高度
                    var top_top = document.body.scrollTop || document.documentElement.scrollTop;
                    //回到顶部按钮操作
                    if (top_top > showTopButtonHeight)
                        toTop.style.display = "block";
                    else
                        toTop.style.display = "none";
                }
                //回到顶部
                toTop.onclick = function () {
                	goTop(0.1,24);
                };
            };

          //加载图片
            function loadImg() {
                var num =0;
                for(var i=0;i<res.length;i++){
                		num = getNum();
                    	if(num == 0){$aq("one").appendChild(addDiv(res[i]));}
                    	else if(num == 1){$aq("two").appendChild(addDiv(res[i]));}
                    	else if(num == 2){$aq("three").appendChild(addDiv(res[i]));}
                    	else if(num == 3){$aq("four").appendChild(addDiv(res[i]));}
                    	else if(num == 4){$aq("five").appendChild(addDiv(res[i]));}
                }
            }

            //声明一个包含img和title的div
            function addDiv(cir) {
                //数组下标的随机值
                var box = document.createElement("div");
                box.className = "box";
               
                var div1 = document.createElement("div");
                div1.setAttribute("style","z-index:100; position:absolute; left:15px; top:15px;"); 
                
                var img1 = document.createElement("img");
                img1.src = "images/gz_btn1.gif";
                div1.appendChild(img1);
                
                box.appendChild(div1);
                
                //=================================
                
                var items = document.createElement("div");
                items.className="items";
                
                var feed_container = document.createElement("div");
                feed_container.className="feed_container";
                
                var img2 = document.createElement("img");
                img2.src = cir.userinfo.otheraccountuserimage;
                img2.setAttribute("width","198");
                
                feed_container.appendChild(img2);
                items.appendChild(feed_container);
                box.appendChild(items);
                
                //=================================
                var shenfen = document.createElement("div");
                shenfen.className="shenfen";
                
                var ul = document.createElement("ul");
                
                var li1 = document.createElement("li");
                li1.className="shenfen_name";
                li1.innerHTML=cir.userinfo.nick;
                
                var li2 = document.createElement("li");
                li2.className="shenfen_btn";
                
                var img3 = document.createElement("img");
                if(cir.ucRole == 1){
                	img3.src="images/btn_05.gif";
                }else if(cir.ucRole == 2){
                	img3.src="images/btn_06.gif";
                }else if(cir.ucRole == 3){
                	img3.src="images/btn_07.gif";
                }
                li2.appendChild(img3);
                
                ul.appendChild(li1);
                ul.appendChild(li2);
                shenfen.appendChild(ul);
                box.appendChild(shenfen);
                
                return box;
            }
            
            function read(){
            	$.ajax({                 
					url: websiteUrl.membars_link,  
					data : {pageNum : pageNum,pageSize : member_rows,id : $("#id").val()},
					type: "POST",     
					dataType : "json",
					async:true,
					success: function(data) {
						var json = data.data.ucr_ls;
						var count = data.data.count;
						res = eval(json);
						$("#one").html("");
	                	$("#two").html("");
	                	$("#three").html("");
//	                	$("#four").html("");
	                	$("#five").html("");
						loadImg();
						var paginate = new pagination();
						paginate.printPagination(pageNum, count, member_rows, 'pl_page',$("#id").val(),1);
					}             
				});   
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
        	
          //通过id得到对象
            function $aq(id) {
                return document.getElementById(id);
            }

	//左侧圈子信息
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
				islogin = data.data.islogin;
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
				$("#a_img").attr("href","quanzi/quanzi_list.jsp?id="+cir.circleid);
				$("#fabu").attr("window.location.href","quanzi/quanzi_fabu.jsp?id="+cir.circleid);

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
					}
				}else{
					//未登录
					$("#join").css("display","none");
					$("#join").css("display","none");
					$("#quit").css("display","none");
					$("#fabu").css("display","none");
				}
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
	
	function flipPage(rows,pageNumber,id,ttp) {
		pageNum = pageNumber;
		read();
		goTop(0.1,24);
	}