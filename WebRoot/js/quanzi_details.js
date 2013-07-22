	var cird,index,islogin,ids;
	function getDetailList(){
    	$.ajax({                 
			url: websiteUrl.quanzidetail_all_list_link,  
			data : {id : $("#id").val()},
			type: "POST",     
			dataType : "json",
			async:false,
			success: function(data) {
				var json = data.data.circledetailinfo_ls;
				cird = eval(json);
				var did = $("#did").val() == '' ? 0 : $("#did").val();
				for(var i=0;i<cird.length;i++){
					if(did == cird[i].circledetailid){
						index = i;
						showHtml();
						break;
					}
				}
			}             
		});   
    }
	
	
	
	function showHtml(){
		var d = cird[index];
		var dimg = d.circleDetailImg;
		$("#d_img").html("");
		for(var j=0;j<dimg.length;j++){
			$("#d_img").append("<img src=\""+dimg[j].bigimg+"\" />");
		}
		$("#d_title").html("【"+d.title+"】");
		$("#d_txt").html(d.circlecontent);
		$("#comm_count").html(d.comcount);
//		$("#join").attr("onClick","join_or_quit_quanzi("+$("#id").val()+",1)");
		
		if(cird.length > 1){
			//内容大于1篇
			if(index == 0){
				//第一篇
				$("#prev").css("display","none");
				$("#h_prev").css("display","block");
				
				$("#next").css("display","block");
				$("#h_next").css("display","none");
				
				$("#next").attr("onClick","index++,showHtml()");
			}else if(index==(cird.length-1)){
				//最后一篇
				$("#prev").css("display","block");
				$("#h_prev").css("display","none");
				
				$("#prev").attr("onClick","index--,showHtml()");
				
				$("#next").css("display","none");
				$("#h_next").css("display","block");
			}else{
				$("#prev").css("display","block");
				$("#h_prev").css("display","none");
				
				$("#prev").attr("onClick","index--,showHtml()");
				
				$("#next").css("display","block");
				$("#h_next").css("display","none");
				$("#next").attr("onClick","index++,showHtml()");
			}
		}else{
			//内容小于等于1篇
			$("#prev").css("display","none");
			$("#h_prev").css("display","block");
			
			$("#next").css("display","none");
			$("#h_next").css("display","block");
		}
		ids = cird[index].circledetailid;
		getCommentList(4,1,1);
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
	
	function getCommentList(rows,pageNumber,ttp){
		$("#com_ls").html("<div class=\"pl_txt\" align=\"center\" >");
		$("#com_ls").append("<div align=\"center\"><img src=\"images/jz.gif\" /></div>");
		$("#com_ls").append("</div>");
		$.ajax({                 
				url: websiteUrl.comment_link,  
				data : {pageNum : pageNumber,pageSize : rows,id : ids == '' ? 0 : ids,type : ttp},
				type: "POST",     
				dataType : "json",
				async:true,
				success: function(data) {
					var json = data.data.comment_ls;
					var count = data.data.comment_count;
					var com_ls = eval(json);
					$("#com_ls").html("");
					for(var i=0;i<com_ls.length;i++){
						var com = com_ls[i];
						$("#com_ls").append("<div class=\"pl_txt\">");
						$("#com_ls").append("<div class=\"u_ico\"><img src=\""+com.userinfo.otheraccountuserimage+"\" /></div>");
						$("#com_ls").append("<div class=\"u_name1\"><p>"+com.userinfo.nick+"</p><span>"+com.createdate+"</span></div>");
						$("#com_ls").append("<div class=\"u_txt\"><p>"+com.commentinfo+"</p><span>回复</span></div>");
						$("#com_ls").append("</div>");
					}
					if(count > 0){
						var paginate = new pagination();
						paginate.printPagination(pageNumber, count, rows, 'pl_page',ids,ttp);
					}else{
						$("#pl_page").html("");
					}
				}             
		});   
	}
	
	//添加评论
	function addComment(){
		if(islogin){
			if($("#comm_text").val() == ''){
				alert("请填写评论信息！");return;
			}
			$.ajax({                 
				url: websiteUrl.addcomment_link,  
				data : {id : ids,content : $("#comm_text").val()},
				type: "POST",     
				dataType : "json",
				async:true,
				success: function(data) {
					var flag = data.flag;
					if(flag){
						getCommentList(4,1,1);
						alert("评论成功！");
						$("#comm_text").val("");
						$("#comm_count").html(parseInt($("#comm_count").html())+1);
						
					}else{
						alert("评论失败！");
					}
				}             
			}); 
		}else{
			alert("您还没有登录，登录后才可以评论！");
		}
	}
	
	function flipPage(rows,pageNumber,id,ttp) {
		getCommentList(rows,pageNumber,id,ttp);
	}
            