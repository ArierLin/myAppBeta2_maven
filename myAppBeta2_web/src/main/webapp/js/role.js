//保存一个角色
function saveRole(){
	
	var name = $("#name").val();
	var type = $("#type").val();
	var desc = $("#desc").val();
	
	var length = $("input:checkbox[name='checkOne']:checked").length;
	if(length==0){
		if(confirm("确定不为角色设置权限？")){
			$.ajax({
				url:"role/saveRole",
				type:"post",
				data:{
					name:name,
					type:type,
					desc:desc,
				},
				success:function(data){
					if(data){
						goToUrl("role/toRoleManagementPage");
					}else{
						alert("保存失败，请重试！");
					}
				},
				error:function(){
					alert("保存失败，请重试！");
				}
			});
		}
	}else{
		var ids = [];
		$("input:checkbox[name='checkOne']:checked").each(function(){
			var id = $(this).val();
			ids.push(id);
		});
		var idStr = ids.join(",");
		$.ajax({
			url:"role/saveRole",
			type:"post",
			data:{
				name:name,
				type:type,
				desc:desc,
				idStr:idStr
			},
			success:function(data){
				if(data){
					goToUrl("role/toRoleManagementPage");
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

//跳转到编辑角色信息页面
function toUpdateOneRolePage(){
	var length = $("input:checkbox[name='checkOne']:checked").length;
	if(length==0||length>1){
		alert("请选择一个角色。");
		return;
	}
	var roleId = $("input:checkbox[name='checkOne']:checked").val();
	$.ajax({
		url:"role/toUpdateOneRolePage",
		type:"get",
		data:{roleId:roleId},
		success:function(e){
			$(".right").html("");
			$(".right").html(e);
		},
		error:function(){
			alert("查询角色信息失败，请重试！");
		}
	});
	
}

//修改一个角色
function updateRole(roleId){
	
	var name = $("#name").val();
	var type = $("#type").val();
	var desc = $("#desc").val();
	
	var length = $("input:checkbox[name='checkOne']:checked").length;
	if(length==0){
		if(confirm("确定不为角色设置权限？")){
			$.ajax({
				url:"role/updateRole",
				type:"post",
				data:{
					id:roleId,
					name:name,
					type:type,
					desc:desc,
				},
				success:function(data){
					if(data){
						goToUrl("role/toRoleManagementPage");
					}else{
						alert("保存失败，请重试！");
					}
				},
				error:function(){
					alert("保存失败，请重试！");
				}
			});
		}
	}else{
		var ids = [];
		$("input:checkbox[name='checkOne']:checked").each(function(){
			var id = $(this).val();
			ids.push(id);
		});
		var idStr = ids.join(",");
		$.ajax({
			url:"role/updateRole",
			type:"post",
			data:{
				id:roleId,
				name:name,
				type:type,
				desc:desc,
				idStr:idStr
			},
			success:function(data){
				if(data){
					goToUrl("role/toRoleManagementPage");
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

//批量删除角色
function multiDeleteRole(){
	var length = $("input:checkbox[name='checkOne']:checked").length;
	if(length==0){
		alert("请选择一个角色。");
		return;
	}
	if(confirm("是否要删除选中的角色？")){
		var ids = [];
		$("input:checkbox[name='checkOne']:checked").each(function(){
			var id = $(this).val();
			ids.push(id);
		});
		var idStr = ids.join(",");
		$.ajax({
			url:"role/multiDeleteRole",
			type:"post",
			data:{idStr:idStr},
//			dataType: "text",	
			dataType: "html",
			success:function(result){
				if("success"==result){
					goToUrl("role/toRoleManagementPage");
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






