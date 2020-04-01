<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=webStie %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=webStie %>/css/justified-nav.css" />
</head>
<body>
<jsp:include page="/navigation.jsp"></jsp:include>
	<div class="container">
		

		<div>

			<h3 class="text-muted">管理员信息展示</h3>
			<table class="table table-hover">
				<tr><td>管理员账户</td><td>${sessionScope.admin.adminName }</td><tr>
				<tr><td>管理员简介</td><td>${sessionScope.admin.adminDesc }</td><tr>
			</table>
		</div>
	</div>
</body>
</html>