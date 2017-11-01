<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/common/common.jsp"%>
<title>商品类型管理</title>
</head>
<body>
	<div>
		<c:choose>
			<c:when test="${empty goodTypes}">
				没有查询到相应的数据，请重试！
			</c:when>
			<c:otherwise>
				<table class="div_table" border="0" cellpadding="0" cellspacing="3" width="90%" align="center">
					<tr class="tbtitle">
						<td>商品类型id</td>
						<td>类型名称</td>
						<td>操作</td>
					</tr>
				<c:forEach var="goodType" items="${goodTypes}">
					<tr class="normaltab_tr">
						<td >${goodType.id}</td>
						<td>${goodType.name}</td>
						<td>
							<a id="edit" onclick="toEditGoodTypePage();">编辑</a>/
							<a id="delete" onclick="deleteOneGoodType('${goodType.id}');">删除</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>