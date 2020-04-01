<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=webStie %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=webStie %>/css/justified-nav.css" />
<link rel="stylesheet" href="<%=webStie %>/css/creatsignin.css" />
</head>
<body>

<jsp:include page="/navigation.jsp"></jsp:include>
		<div class="container">
			
			
			<form class="form-signin" action="<%=webStie %>/adminserver" method="post">
        <h2 class="form-signin-heading">分配管理员</h2>
        <input type="hidden" name="op" value="adminCreate"/>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="adminName" name="adminName" class="form-control" placeholder="管理员姓名" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="adminPass" class="form-control" placeholder="管理员密码" required>
       
        <div>
        	
        	<textarea class="form-control form-textarea" rows="5" name="adminDesc" placeholder="管理员介绍" required></textarea>
        </div>
        
        <button class="btn btn-lg btn-primary btn-block btn-style" type="submit">创建管理员账户</button>
      </form>
		</div>
	</body>

</html>