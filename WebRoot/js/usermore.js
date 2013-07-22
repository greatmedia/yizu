var userid ;
$(function(){
	var realurl = location.href;
	var idindexof = realurl.lastIndexOf("/");
	var idendIndexof = realurl.lastIndexOf("?")
	userid= realurl.substring(idindexof + 1,idendIndexof);
	var tag = realurl.substring(realurl.lastIndexOf("=")+1)
	selectContent(userid);
});
var pageNumber = 1;
function selectContent(userid){
	var selectContent="centerAction_selectContent.do";
	$.ajax({
		url:selectContent,
		data:{pageNum : pageNumber,pageSize : 12,id : userid},
		type:"POST",
		dataType:"json",
		success:function(data)
		{
			var user = data.data.user;
			document.title = user.nick+"的个人中心"
			$("#userimg").attr({"src":user.image,"width":"80","height":"80"});//头像
			$("#nick").html(user.nick)//名字
			if(user.website != null && user.website != ""){
				$("#user_bj").attr({"src": user.website,"width":"720","height":"295"});
			}else{
				$("#user_bj").attr("src", "images/gerenzhongxin_bj.jpg");
			}
			if(user.sex !=null && user.sex != "" ){
				if(user.sex == "2"){$("#usersex").html("逸千金");}else{$("#usersex").html("逸骑士");}
			}else{
				$("#usersex").html("逸骑士");
			}
			if(user.address !=null && user.address != "" ){$("#useraddress").html(""+user.address+"")}
			if(user.hobby !=null && user.hobby != "" ){$("#userhobby").html(""+user.hobby+"")}
			$("#aboutme").html(user.what);//关于自己
			$("#huatiCount").html(data.data.count);	//发言数
			$("#circleCount").html(data.data.circleCount);//我创建的圈子数
			$("#myvoteCount").html(data.data.myvoteCount);//我创建的投票数
			
			
			//ta的发言
			var hishuati = data.data.circleDetailInfos;
			if(data.data.count<=0){
				$("#hesaid").html("<li><div class='u_cont'><h3>TA还没有发言哦！</h3></div></li>");
			}
			if(data.data.count>12){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, data.data.count, 12, 'topic_pl_page',userid,1);
			}
			for(var k = 0;k<hishuati.length;k++){
				var def1 = hishuati[k].def1;//话题
				var def2 = hishuati[k].def2;//PK话题
				var cid = hishuati[k].circleid;
				var img;
				if(hishuati[k].circleDetailImg[0] ==null){
					img = "images/003.jpg";
				}else{
					img = hishuati[k].circleDetailImg[0].middleimg;
					if(img == ""){img = "images/003.jpg";}
				}
				var url ;
				if(def1==1){
					if(def2==1){
						url = basePath +"topic_pk/"+hishuati[k].circledetailid+".html";
					}else{
						url = basePath +"topic_tu/"+hishuati[k].circledetailid+".html";
					}
					
				}else{
					url = basePath + "det/" +cid+ "/" +hishuati[k].circledetailid+ ".html";
				}
				var content = hishuati[k].circlecontent;
				if(content.length > 120){
					content = content.substring(0,120);
					content = content+"";
				}
				var fayan = "" +
					"<div class=\"personal_middle2_wenzi\">" +
						"<dl>" +
							"<dt><a href=\""+url+"\"><img src=\""+img+"\" /></a></dt>" +
							"<dd>" +
								"<div class=\"personal_middle2_wenzi_top\">" +
									"<p><a href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
									"<p><span>"+hishuati[k].createdatetime+" • "+hishuati[k].comcount+"条评论</span></p>" +
								"</div>" +
								"<div class=\"clear\"></div>" +
								"<div class=\"personal_middle2_wenzi_middle\">" +
									"<p>"+content+"</p>" +
								"</div>" +
								"<div class=\"personal_middle2_wenzi_middle\">" +
									"<span><a href=\""+url+"\">查看更多>></a></span>" +
								"</div>" +
							"</dd>" +
						"</dl>" +
					"</div>";
				$("#hesaid").append(fayan);;
			}
		},error : function(data){
			
		}
	});
}
//分页
function flipPage(rows,pageNumber,id,ttp) {
	if(ttp == 1){gettopicnext(rows,pageNumber,id,ttp);}
}
//发言分页
function gettopicnext(rows,pageNumber,id,ttp){
	var gettopicnextURL = "centerAction_gettopicnext.do";
	$.ajax({
		url : gettopicnextURL,
		data : {pageNum : pageNumber , pageSize : rows,id : userid},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			$("#hesaid").html("");
			var hishuati = data.data.circleDetailInfos;
			if(data.data.count<=0){
				$("#hesaid").html("<li><div class='u_cont'><h3>TA还没有发言哦！</h3></div></li>");
			}
			if(data.data.count>12){
				var paginate = new pagination();
				paginate.printPagination(pageNumber, data.data.count, 12, 'topic_pl_page',userid,1);
			}
			for(var k = 0;k<hishuati.length;k++){
				var def1 = hishuati[k].def1;//话题
				var def2 = hishuati[k].def2;//PK话题
				var cid = hishuati[k].circleid;
				var img;
				if(hishuati[k].circleDetailImg[0] ==null){
					img = "images/003.jpg";
				}else{
					img = hishuati[k].circleDetailImg[0].middleimg;
					if(img == ""){img = "images/003.jpg";}
				}
				var url ;
				if(def1==1){
					if(def2==1){
						url = basePath +"topic_pk/"+hishuati[k].circledetailid+".html";
					}else{
						url = basePath +"topic_tu/"+hishuati[k].circledetailid+".html";
					}
					
				}else{
					url = basePath + "det/" +cid+ "/" +hishuati[k].circledetailid+".html";
				}
				var content = hishuati[k].circlecontent;
				if(content.length > 300){
					content = content.substring(0,300);
					content = content+"";
				}
				var fayan = "" +
					"<div class=\"personal_middle2_wenzi\">" +
						"<dl>" +
							"<dt><a href=\""+url+"\"><img src=\""+img+"\" /></a></dt>" +
							"<dd>" +
								"<div class=\"personal_middle2_wenzi_top\">" +
									"<p><a href=\""+url+"\">"+hishuati[k].title+"</a></p>" +
									"<p><span>"+hishuati[k].createdatetime+" • "+hishuati[k].comcount+"条评论</span></p>" +
								"</div>" +
								"<div class=\"clear\"></div>" +
								"<div class=\"personal_middle2_wenzi_middle\">" +
									"<p>"+content+"</p>" +
								"</div>" +
								"<div class=\"personal_middle2_wenzi_middle\">" +
									"<span><a href=\""+url+"\">查看更多>></a></span>" +
								"</div>" +
							"</dd>" +
						"</dl>" +
					"</div>";
				$("#hesaid").append(fayan);;
			}
		},
		error : function(data){
			
		}
	});	
}