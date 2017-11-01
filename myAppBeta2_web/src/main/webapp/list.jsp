<%@page import="javax.servlet.descriptor.TaglibDescriptor"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>list page</title>
</head>
<body>
	
	Hello:<shiro:principal></shiro:principal>

	<h4>list page</h4>
	<shiro:hasRole name="admin">
		<a href="admin.jsp">to admin jsp</a>
		<br>
	</shiro:hasRole>
		<a href="user.jsp">to user jsp</a>
	<br>
		<a href="shiro-test">测试方法权限shiro test</a>
	<br>
		<a href="shiro-logout">logout</a>
	<br>
	
</body>
</html>