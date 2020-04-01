<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=webStie %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=webStie %>/css/justified-nav.css" />
<link rel="stylesheet" href="<%=webStie %>/css/main.css" />
</head>
<body>
<jsp:include page="/navigation.jsp"></jsp:include>
<div class="container">
				<table class="table table-hover table-wh">
				<tr>
					<td>学生照片</td>
					<td>
					<img  style="width:300px;" alt="" src="/RSIMAGE/${student.studentHeadImage }">
					</td>
				</tr>
				<tr>
					<td>学生姓名</td>
					<td>${student.studentName}</td>
				</tr>
				<tr>
					<td>学生性别</td>
					<td>${student.studentGender }</td>
				</tr>
				<tr>
					<td>学生出生日期</td>
					<td>${student.studentBirthday }</td>
				</tr>
				<tr>
					<td>学生电子邮件</td>
					<td>${student.studentEmail }</td>
				</tr>
				<tr>
					<td>学生手机号码</td>
					<td>${student.studentPhone }</td>
				</tr>
				<tr>
					<td>学生联系方式</td>
					<td>${student.studentAddr }</td>
				</tr>
				<tr>
					<td>学生简介</td>
					<td><span class="text-content">${student.studentDescribe }</span></td>
				</tr>
				<tr>
					<td colspan="2">
					<a href="<%=webStie%>/studentServer?op=studentQueryByid&studentId=${student.studentId}">修改个人信息</a>
					<a href="<%=webStie%>/studentServer?op=studentQueryByid&studentId=${student.studentId}&type=updateHeadImage">更换个人头像</a>
					</td>
				</tr>
				</table>
			</div>
</body>
</html>