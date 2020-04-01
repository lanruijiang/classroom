<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/base.jsp" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="container">
			<div class="masthead">
				<h3 class="text-muted">课堂回答问题计分系统</h3>
				<nav>
					<ul class="nav nav-justified">
						<li class="active">
							<a href="#">首页</a>
						</li>
						<li>
							<a href="#">班级</a>
						</li>
						<li>
							<a href="#">学员</a>
						</li>
						<li>
							<a href="#">计分</a>
						</li>
						 <li><a href="#">管理员账户分配</a></li>


					</ul>
				</nav>
			</div>
		<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
		<c:if test="${param.msg == 'creareOk' }">
		<span class="showmsg">管理员账户分配成功</span>
		</c:if>
		
		<c:if test="${param.msg == 'creareError' }">
		<span class="showmsg">管理员账户分配失败</span>
		</c:if>
		
		<c:if test="${param.msg == 'createClassesOk' }">
		<span class="showmsg">班级创建成功</span>
		</c:if>
		
		<c:if test="${param.msg == 'createClassesError' }">
		<span class="showmsg">班级创建失败</span>
		</c:if>
		</div>
		<div class="col-sm-3"></div>
		
		</div>
		</div>
</body>
</html>