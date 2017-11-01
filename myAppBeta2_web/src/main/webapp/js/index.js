function goToUrl(url) {
	$.ajax({
		type : "post",
		url : url,
		async : false,
		success : function(data, textStatus) {
			$(".right").html("");
			$(".right").html(data);
			$(window).resize();// 重新调整页面宽度
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
		}
	});
}


//checkbox全选全不选
function checkAllOrNot(obj){
	$("input:checkbox[name='checkOne']").prop("checked",obj.checked);
	if(obj.checked){
//		$("tr .normaltab_tr:not(:last)").addClass("tr_checked");
		$("tr .normaltab_tr").addClass("tr_checked");
	}else{
		$("tr .normaltab_tr").removeClass("tr_checked")
	}
}
//
function checkOne(obj) {
	//没选中
	if (!obj.checked) {
		$("input[id='checkAll']").prop("checked", obj.checked);
	} else {
		var checkedLength = $("input:checkbox[name='checkOne']:checked").length;
		var checkBoxLength = $("input:checkbox[name='checkOne']").length;
		if (checkedLength == checkBoxLength) {
			$("input[id='checkAll']").prop("checked", obj.checked);
		}
	}
}
//点击行选中
function changeTrState(obj) {
	$(obj).toggleClass("tr_checked");
	if ($(obj).hasClass("tr_checked")) {
		$(obj).children().first().children().prop("checked", true);
	} else {
		$(obj).children().first().children().prop("checked", false);
	}
	var checkedLength = $("input:checkbox[name='checkOne']:checked").length;
	var checkBoxLength = $("input:checkbox[name='checkOne']").length;
	if (checkedLength == checkBoxLength) {
		$("input[id='checkAll']").prop("checked", true);
	} else {
		$("input[id='checkAll']").prop("checked", false);
	}
}












