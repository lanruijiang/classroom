<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=webStie %>/css/bootstrap.min.css" />
<link rel="stylesheet" href="<%=webStie %>/css/signin.css" />
</head>
<body>
  <div class="container">

      <form class="form-signin" action="<%=webStie %>/adminserver" method="post">
        <h2 class="form-signin-heading">管理员登录</h2>
        <input type="hidden" name="op" value="login"/>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="adminName" name="adminName" class="form-control" placeholder="admin name" value="admin" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="adminPass" class="form-control" placeholder="admin Password" value="admin" required>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>

    </div> <!-- /container -->

	</body>
</html>