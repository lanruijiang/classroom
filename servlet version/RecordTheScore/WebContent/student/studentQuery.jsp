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
<link rel="stylesheet" href="<%=webStie %>/css/creatsignin.css" />
</head>
<body>
<jsp:include page="/navigation.jsp"></jsp:include>
<div class="container">	
<c:choose>
<c:when test="${param.msg == 'empty' }">
<h2>该系统还没有录入任何学生信息</h2>
</c:when>
<c:otherwise>

		<form class="form-signin" action="<%=webStie %>/studentServer" method="post">
        <h2 class="form-signin-heading">通过学生姓名查询</h2>     
        <input type="hidden" name="op" value="studentQueryByName"/>    
        <input type="text" id="studentName" name="studentName" class="form-control" placeholder="通过学生姓名查询" > 
        <button class="btn btn-lg btn-primary btn-block btn-style" type="submit">查询</button>
      </form>
      
      
      <form class="form-signin" action="<%=webStie %>/studentServer" method="post">
      <h2 class="form-signin-heading">通过班级查询</h2>     
      <input type="hidden" name="op" value="studentQueryByClassesId"/>  
       <div class="selectButton">
        	<select class="form-control" id="classesId" name="classesId"  placeholder="通过班级查询" >
        		<c:forEach items="${requestScope.classeslist }" var="classes">
        		<option value="${classes.classesId }">${classes.classesName }</option>
        		</c:forEach>
        	</select>	
        </div>
         <button class="btn btn-lg btn-primary btn-block btn-style" type="submit">查询</button>
        </form>
		
</c:otherwise>
</c:choose>
</div>

		
	</body>

</html>