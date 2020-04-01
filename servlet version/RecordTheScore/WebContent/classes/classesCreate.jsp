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
<link rel="stylesheet" href="<%=webStie %>/css/msg.css" />
</head>
<body>

<jsp:include page="/navigation.jsp"></jsp:include>
		<c:if test="${param.msg == 'classesEmpty' }">
			<div class="msgStyle">
			请先创建班级.....
			</div>
			</c:if>
		<div class="container">
			
	
			<form class="form-signin" action="<%=webStie %>/classesServer" method="post">
        <h2 class="form-signin-heading">创建班级</h2>
        <input type="hidden" name="op" value="classesCreate"/>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="classesName" name="classesName" class="form-control" placeholder="班级名称" required autofocus>

        	
        	<textarea class="form-control form-textarea" rows="5" name="classesDesc" placeholder="班级介绍" required></textarea>

        
        <button class="btn btn-lg btn-primary btn-block btn-style" type="submit">创建班级信息</button>
      </form>
		</div>
	</body>

</html>