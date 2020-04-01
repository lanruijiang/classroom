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

			<h3 class="text-muted">
			
			${sessionScope.admin.adminName}所管理的班级的信息展示
		
			
			</h3>
			<c:if test="${param.msg == 'empty' }">
			<h3 class="text-muted">没有查询到所任何信息</h3>
			</c:if>
			<table class="table table-hover">
			<tr>
			<td>班级名称</td>
			<td>班级创建时间</td>
			<td>班级简介</td>
			<td>班级管理</td>
			</tr>
				<c:forEach items="${requestScope.classeslist }" var="classes">
					<tr>
						<td>${classes.classesName }</td>
						<td>${classes.classesCreateTime }</td>
						<td>${classes.classesDesc}</td>
						<td><a href="<%=webStie%>/scoreServer?op=scoreRanking&classesId=${classes.classesId }">进入班级</a></td>
					</tr>
				</c:forEach>
			</table>
			<div>
			<ul class="pagination">
		<c:forEach begin="1" end="${requestScope.totalPages}" var="currentPageNum">

	<li>	<a href="<%=webStie%>/classesServer?op=classesQueryAll&currentPageNum=${currentPageNum}&type=showScorePage">${currentPageNum}</a>
</li>
		
		
		</c:forEach>
		</ul>
		</div>
		</div>
	</div>
</body>
</html>