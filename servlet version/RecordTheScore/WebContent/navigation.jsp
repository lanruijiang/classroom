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
<link rel="stylesheet" href="<%=webStie %>/css/bootstrap-theme.min.css" />
</head>
<body>
<div class="container">
		<div class="masthead">
			<h3 class="text-muted">爱课堂积分系统</h3>
			<nav>
			<ul class="nav nav-justified">
				<li class="active"><a href="<%=webStie %>/main/main.jsp">首页</a></li>
				<li role="presentation" class="dropdown"><a
					data-toggle="dropdown" href="#"> 班级管理 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<%=webStie %>/classes/classesCreate.jsp">创建班级</a></li>
						<li><a href="<%=webStie %>/classesServer?op=classesQueryAll">查看所有班级</a></li>
						<li><a href="<%=webStie %>/classesServer?op=classesQueryAll&type=adminOwner">查看教师所管理的班级</a></li>
					</ul></li>
				<li role="presentation" class="dropdown"><a
					data-toggle="dropdown" href="#">学生信息管理 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<%=webStie %>/classesServer?op=classesQueryAlltoCreateStudent">创建学生信息</a></li>
						<li><a href="<%=webStie %>/classesServer?op=studentToSearchPage">查看学生信息</a></li>
					</ul></li>
				<li role="presentation" class="dropdown"><a
					data-toggle="dropdown" href="#">积分管理 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<%=webStie %>/classesServer?op=classesQueryAll&type=showScorePage">进入管理员管理的班级</a></li>
					</ul></li>
				<li role="presentation" class="dropdown"><a
					data-toggle="dropdown" href="#">管理员账户管理 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu">
						<li><a href="<%=webStie %>/admin/adminCreate.jsp">创建管理员</a></li>
						<li><a href="<%=webStie %>/admin/adminShow.jsp">查看个人信息</a></li>

					</ul></li>
			</ul>
			</nav>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=webStie %>/js/jquery.min.js"></script>
<script type="text/javascript" src="<%=webStie %>/css/bootstrap.min.js"></script>
</html>