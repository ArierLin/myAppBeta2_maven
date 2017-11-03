<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp"%>
<title>myApp_beta2_maven后台首页</title>
<script type="text/javascript">
	$(function() {
		$(window).resize(function() {
			$(".left").width("15%");
			$(".buttom").height($(window).height() - $(".head").height());
			//$(".right").width($(window).width() - $(".left").width() - 2);
			//$(".right").width("85%"- 2);
			$("#dirTree").height($(window).height() - $(".head").height());
		});
		loadAuthoritiesTreeData();
	}); 
	var timer = null;
	if(timer==null){
		//timer = setInterval("getMarqueeContent()",500);
	}
	function getMarqueeContent(){
		var marqueeContent = $("#marqueeContent");
		$.ajax({
			url:"getMarqueeContent",
			type:"post",
			data:{},
			success:function(e){
				marqueeContent.find("STRONG").html(e);
			},
			error:function(){}
		});
	}
	//加载左侧权限列表
	function loadAuthoritiesTreeData(){
		var setting = {
				view : {
					dblClickExpand : true
				},
				data : {
					simpleData : {
						enable : true,
//						idKey:"id",
//						pIdKey:"parent"
						id:"id",
						pId:"parentId"
					}
				},
				callback : {
					onClick : showData
				},
			};
		$.ajax({
			url:"loadAuthoritiesTreeData",
			type:"post",
			data:{
				
			},
			//dataType:'json',
			success:function(authorities){
				var zNodes = [];
				for(var i = 0;i<authorities.length;i++){
					var authority = authorities[i];
					if("/" != authority.url){
						zNodes.push(
								{'id' : authority.id,'pId' : authority.parentId,'name' : authority.name,'open' : true,'urlValue' : authority.url}
						);
					}else{
						zNodes.push(
								{'id' : authority.id,'pId' : authority.parentId,'name' : authority.name,'open' : true,'urlValue' : ""}		
						);
					}
				}
				$.fn.zTree.init($("#dirTree"), setting, zNodes);
				$(window).resize();
			},
			error:function(){
				alert("暂无权限数据！");
			}
		});
		
	}
	function showData(event, treeId, treeNode) {
		if(treeNode.pId==0){
			$.ajax({
				url:treeNode.urlValue,
				async : false,
				type:"post",
				success:function(data){
					$(".right").html(data);
					$(window).resize();
				},
				error:function(){
				}
			});
		}else{
			if ("" != treeNode.urlValue && null != treeNode.urlValue) {
				goToUrl(treeNode.urlValue);
			}
		}
	}
</script>
</head>
<body>
	<!-- 页面头部 -->
	<%@include file="/common/header.jsp"%>
	<!-- 页面下部 -->
	<div class="bottom">
		<!-- 页面左部 -->
		<%@include file="/common/leftTree.jsp"%>
		<!-- 页面右部 -->
		<%--<div class="right" style="float:left;" >--%>
		<div class="right">
			<%@include file="/common/version.jsp" %>
		</div>
	</div>
</body>
</html>