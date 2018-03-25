<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/lib.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
	<h1>hello node1</h1>
	<shiro:hasRole name="Admin">
		<h2>管理员</h2>
	</shiro:hasRole>
	<shiro:hasPermission name="user:query">
		<h2>查询</h2>
	</shiro:hasPermission>
	
	<shiro:hasPermission name="user1:add1">
		<h2>Add</h2>
	</shiro:hasPermission>
	
	
	<a href="http://127.0.0.1:8081/app1/shiro-cas">节点1</a>|
	<a href="http://127.0.0.1:8083/app1/shiro-cas">节点1-2</a>|
	<a href="http://127.0.0.1:8082/app2/shiro-cas">节点2</a>|
	
	<a href="http://127.0.0.1:8081/app1/logout">单点登出</a>
</body>
</html>