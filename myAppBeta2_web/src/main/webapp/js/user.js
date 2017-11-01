//判断用户名是否可以使用
var validUser = function validateUserName(){
	$("#validUserName").html("");
	var flag = false;
	var name = $("#name").val();
	if(name==null||name==undefined||name.trim()==""){
		alert("用户名不能为空。");
		return flag;
	}
	
	var userName = $("#userName").val();
	if(userName==name){
		$("#validUserName").html("用户名可以使用！");
		return true;
	}
	
	$.ajax({
		async:false,
		url:"user/validateUserName",
		type:"post",
		data:{name:name},
		success:function(e){
			if(e){
				$("#validUserName").html("用户名可以使用！");
				flag = true;
				return flag;
			}else{
				$("#validUserName").html("用户名重复！请换一个用户名！");
				return flag;
			}
		},
		error:function(){
			return flag;
		}
	});
	return flag;
}

//保存一个用户
function saveUser(){
	
	if(validUser()){
		var name = $("#name").val();
		var password = $("#password").val();
		var secondPass = $("#secondPass").val();
		if(password==null||password==undefined||password.trim()==""||password.trim().length<6){
			$('#passstrength').html('密码不符合要求!密码不能少于6位。');
			return false;
		}
		if(password!=secondPass){
			$('#passEqual').html('两次密码输入不一致，请重新确认。');
			return false;
		}
		
		var length = $("input:checkbox[name='checkOne']:checked").length;
		if(length==0){
			alert("请为用户设置至少一种角色！");
			return false;
		}
		var ids = [];
		$("input:checkbox[name='checkOne']:checked").each(function(){
			var id = $(this).val();
			ids.push(id);
		});
		var idStr = ids.join(",");
		
		$.ajax({
			url:"user/saveUser",
			type:"post",
			data:{
				name:name,
				password:password,
				idStr:idStr,
			},
			success:function(data){
				if(data){
					goToUrl("user/toUserManagementPage");
				}else{
					alert("保存失败，请重试！");
				}
			},
			error:function(){
				alert("保存失败，请重试！");
			}
		});
	}
	
}

//跳转到编辑用户信息页面
function toUpdateOneUserPage(){
	var length = $("input:checkbox[name='checkOne']:checked").length;
	if(length==0||length>1){
		alert("请选择一个用户。");
		return;
	}
	var userId = $("input:checkbox[name='checkOne']:checked").val();
	$.ajax({
		url:"user/toUpdateUserPage",
		type:"get",
		data:{userId:userId},
		success:function(e){
			$(".right").html("");
			$(".right").html(e);
		},
		error:function(){
			alert("查询用户信息失败，请重试！");
		}
	});
	
}

//修改一个用户
function updateUser(userId){
	
	
	
	if(validUser()){
		var name = $("#name").val();
		var length = $("input:checkbox[name='checkOne']:checked").length;
		if(length==0){
			alert("请为用户设置至少一种角色！");
			return false;
		}
		var ids = [];
		$("input:checkbox[name='checkOne']:checked").each(function(){
			var id = $(this).val();
			ids.push(id);
		});
		var idStr = ids.join(",");
		
		$.ajax({
			url:"user/updateUser",
			type:"post",
			data:{
				id:userId,
				name:name,
				idStr:idStr,
			},
			success:function(data){
				if(data){
					goToUrl("user/toUserManagementPage");
				}else{
					alert("保存失败，请重试！");
				}
			},
			error:function(){
				alert("保存失败，请重试！");
			}
		});
	}
}

//批量删除用户
function multiDeleteUser(){
	var length = $("input:checkbox[name='checkOne']:checked").length;
	if(length==0){
		alert("请选择一个用户。");
		return;
	}
	if(confirm("是否要删除选中的用户？")){
		var ids = [];
		$("input:checkbox[name='checkOne']:checked").each(function(){
			var id = $(this).val();
			ids.push(id);
		});
		var idStr = ids.join(",");
		$.ajax({
			url:"user/multiDeleteUser",
			type:"post",
			data:{idStr:idStr},
//			dataType: "text",	
			dataType: "html",
			success:function(result){
				if("success"==result){
					goToUrl("user/toUserManagementPage");
					alert("删除成功！");
				}else{
					alert(result);
				}
			},
			error:function(){
				alert("删除失败！");
			}
		});
	}
}






