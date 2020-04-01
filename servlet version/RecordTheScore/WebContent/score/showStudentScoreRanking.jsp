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
<link rel="stylesheet" href="<%=webStie%>/css/msg.css" />
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
					<td>当前积分</td>
					<td>加分</td>
					<td>减分</td>
				</tr>
				<c:forEach items="${requestScope.scoreRankinges }" var="scoreRanking">
					<tr>
						<td>
						<a href="<%=webStie%>/studentServer?op=studentQueryByid&type=show&studentId=${scoreRanking.studentId}">${scoreRanking.studentName }</a>
						
						
						</td>
						<td>${scoreRanking.scoreNumber }</td>
						<td>
						<form action="<%=webStie %>/scoreServer?op=scoreAppend" method="post">
						<input type="hidden" name="scoreId" value="${scoreRanking.scoreId }">
						<input type="hidden" name="classesId" value="${scoreRanking.classesId }">
						<select name="num">
						<option value="1">1分</option>
						<option value="2">2分</option>
						<option value="3">3分</option>
						<option value="4">4分</option>
						<option value="5">5分</option>
						</select>
						<input style="width:30px"  type="image" src="<%=webStie%>/imgs/add.jpg"/>
						</form>
						</td>
						<td>
						<form action="<%=webStie %>/scoreServer?op=scoreReduce" method="post">
						<input  type="hidden" name="scoreId" value="${scoreRanking.scoreId }">
						<input type="hidden" name="classesId" value="${scoreRanking.classesId }">
						<select name="num">
						<option value="1">1分</option>
						<option value="2">2分</option>
						<option value="3">3分</option>
						<option value="4">4分</option>
						<option value="5">5分</option>
						</select>
						<input style="width:30px" type="image"  src="<%=webStie%>/imgs/reduce.jpg"/>
						</form>
						</td>
					</tr>
				</c:forEach>
			</table>
			<ul class="pagination">
			<c:forEach items="${requestScope.scoreRankinges}" begin="1" end="${requestScope.totalPages}" var="scoreRankinge" varStatus="status">
				<li><a href="<%=webStie%>/scoreServer?op=scoreRanking&currentPageNum=${status.index}&classesId=${scoreRankinge.classesId}">${status.index}</a>
			</li>
			</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>