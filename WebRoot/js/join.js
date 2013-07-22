function checkpwd(){
    if($.trim($("#join_pwd").val()) != ""){
    	$("#checkpwd").html("");
    }else{
    	$("#checkpwd").html("密码太过简单，请重新输入。");
    }
}
function joinUser(){
		if($.trim($("#join_name").val()) == ""){
	        alert("请输入姓名");
	        $("#join_name").focus();
	        return;
	    }
    	if($.trim($("#join_email").val()) == ""){
            alert("请输入邮箱");
            $("#join_email").focus();
            return;
        }
    	if($("#join_email").val().search("@") < 0 ){
            alert("邮箱格式不正确，请重新输入");
            $("#join_email").focus();
            return;
        }
    	var pwd = $("#join_pwd").val();
        if($.trim($("#join_pwd").val()) == ""){
            alert("请输入密码！");
            $("#join_pwd").focus();
            return;
        }
        if($.trim(pwd).length < 6){
        	alert("密码不能少于6位！");
            $("#join_pwd").focus();
            return;
        }
        var repwd = $("#join_repwd").val();
        if($.trim($("#join_repwd").val()) == ""){
            alert("请输入确认密码！");
            $("#join_repwd").focus();
            return;
        }
        if(repwd != pwd){
        	 alert("两次密码输入不一致");
             $("#join_repwd").focus();
             return;
        }
        var ajaxLoginsURL = "userInfoAction_regLogin.do";
        var email = $("#join_email").val();
        var ref = location.href;
          $.ajax({
        	url:ajaxLoginsURL,
    		data:{email : email,pwd : pwd,ref : ref,name:$("#join_name").val()},
    		type:"POST",
    		dataType:"json",
    		beforeSend:function() {
			 	$("#sub").hide();
            	$("#loadding").show();
			},
    		success:function(data)
    		{
				$("#loadding").hide();
//				var user  = data.data.user;
        		if(data.flag){
        			location.href = basePath+"registerinfo.html";
        		}else{
        			$("#sub").show();
        			alert(data.msg);
        		}
    		},
    		error:function(data){
    			alert("ee"+data);
    		}
    		
    	});
    }

function checkedxieyi(){
	var check = document.getElementById("agreeornot").checked;
	if(check){
		document.getElementById("join_submit").style.display= "block";
	}else{
		document.getElementById("join_submit").style.display = "none";
	}
}

function joinUserLogined(){
	if($.trim($("#join_name").val()) == ""){
        alert("请输入姓名");
        $("#join_name").focus();
        return;
    }
	if($.trim($("#join_email").val()) == ""){
        alert("请输入邮箱");
        $("#join_email").focus();
        return;
    }
	if($("#join_email").val().search("@") < 0 ){
        alert("邮箱格式不正确，请重新输入");
        $("#join_email").focus();
        return;
    }
	var pwd = $("#join_pwd").val();
    if($.trim($("#join_pwd").val()) == ""){
        alert("请输入密码！");
        $("#join_pwd").focus();
        return;
    }
    if($.trim(pwd).length < 6){
    	alert("密码不能少于6位！");
        $("#join_pwd").focus();
        return;
    }
    var repwd = $("#join_repwd").val();
    if($.trim($("#join_repwd").val()) == ""){
        alert("请输入确认密码！");
        $("#join_repwd").focus();
        return;
    }
    if(repwd != pwd){
    	 alert("两次密码输入不一致");
         $("#join_repwd").focus();
         return;
    }
    var ajaxLoginsURL = "userInfoAction_bangdingTenAndSinaUser.do";
    var email = $("#join_email").val();
    var ref = location.href;
      $.ajax({
    	url:ajaxLoginsURL,
		data:{email : email,pwd : pwd,ref : ref,name:$("#join_name").val()},
		type:"POST",
		dataType:"json",
		beforeSend:function() {
		 	$("#sub").hide();
        	$("#loadding").show();
		},
		success:function(data)
		{
			$("#loadding").hide();
//			var user  = data.data.user;
    		if(data.flag){
    			location.href = basePath+"registerinfo.html";
    		}else{
    			$("#sub").show();
    			alert(data.msg);
    		}
		},
		error:function(data){
			alert("ee"+data);
		}
		
	});
}