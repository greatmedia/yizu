function registerUser(){
		if($.trim($("#regname").val()) == "" || $.trim($("#regname").val()) == "姓名"){
	        alert("请输入姓名");
	        $("#regname").focus();
	        return;
	    }
    	if($.trim($("#regemail").val()) == "" || $.trim($("#regemail").val()) == "邮箱地址"){
            alert("请输入邮箱");
            $("#regemail").focus();
            return;
        }
    	if($("#regemail").val().search("@") < 0 ){
            alert("邮箱格式不正确，请重新输入");
            $("#regemail").focus();
            return;
        }
    	var pwd = $("#regpwd").val();
        if($.trim($("#regpwd").val()) == "" || $.trim($("#regpwd").val()) == "请输入密码"){
            alert("请输入密码！");
            $("#regpwd").focus();
            return;
        }
        var ajaxLoginsURL = "userInfoAction_regLogin.do";
        var email = $("#regemail").val();
        var ref = location.href;
          $.ajax({
        	url:ajaxLoginsURL,
    		data:{email : email,pwd : pwd,ref : ref,name:$("#regname").val()},
    		type:"POST",
    		dataType:"json",
    		beforeSend:function() {
			 	$("#reg_submit").hide();
            	$("#loadding").show();
			},
    		success:function(data)
    		{
				if(data.msg == '密码不能少于6位'){
					location.href = "/join.html";
					return;
				}
				$("#loadding").hide();
//				var user  = data.data.user;
//				alert("注册成功。");
        		if(data.flag){
        			location.href = basePath+"registerinfo.html";
        		}else{
        			$("#reg_submit").show();
        			alert(data.msg);
        		}
    		},
    		error:function(data){
    			alert("ee"+data);
    		}
    		
    	});
    }