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
<link rel="stylesheet" href="<%=webStie%>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=webStie%>/css/justified-nav.css" />
</head>
<body>
	<jsp:include page="/navigation.jsp"></jsp:include>
	<div class="container">


		<div>

			<h3 class="text-muted">班级学生信息信息展示</h3>
			<c:if test="${param.msg == 'empty' }">
				<h3 class="text-muted">没有查到任何信息</h3>
			</c:if>
			<table class="table table-hover">
				<tr>
					<td>学生姓名</td>
					<td>学生电子邮件</td>
					<td>操作学生信息</td>
				</tr>
				<c:forEach items="${requestScope.studentList }" var="student">
					<tr>
						<td><a
							href="<%=webStie%>/studentServer?op=studentQueryByid&type=show&studentId=${student.studentId}">${student.studentName }</a>


						</td>
						<td>${student.studentEmail }</td>
						<td><a
							href="<%=webStie %>/studentServer?op=studentDelete&studentId=${student.studentId }&classesId=${student.classesId }">删除</a>
							<a
							href="<%=webStie %>/studentServer?op=studentQueryByid&studentId=${student.studentId }">修改</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<ul class="pagination">
			<c:forEach items="${requestScope.studentList}" begin="1" end="${requestScope.totalPages}" var="student" varStatus="status">
				<li><a href="<%=webStie%>/studentServer?op=studentQueryByClassesId&currentPageNum=${status.index}&classesId=${student.classesId}">${status.index}</a>
			</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>