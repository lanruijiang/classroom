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
			<c:redirect url="/classes/classesCreate.jsp">
			<c:param name="msg" value="classesEmpty"></c:param>
			</c:redirect>
			</c:when>
			<c:otherwise>	
		<form class="form-signin" action="<%=webStie %>/studentServer?op=studentCreate" enctype="multipart/form-data" method="post">
        <h2 class="form-signin-heading">创建学生信息</h2>
        
        
        <input type="text" id="studentName" name="studentName" class="form-control" placeholder="学生姓名" >

        <input type="email" id="studentEmail" name="studentEmail" class="form-control" placeholder="学生电子邮件" >
        
        <!-- ######################### -->
        <div class="form-control">
        <input type="radio" id="studentGender" name="studentGender"  value="男" checked="checked">男
        <input type="radio" id="studentGender" name="studentGender"  value="女">女
        </div>
        <input type="file" id="studentHeadImage" name="studentHeadImage" class="form-control" placeholder="学生头像" >
        <input type="text" id="studentPhone" name="studentPhone" class="form-control" placeholder="学生电话号码" >
        <input type="text" id="studentAddr" name="studentAddr" class="form-control" placeholder="学生联系地址" >
        <input type="date" id="studentBirthday" name="studentBirthday" class="form-control" placeholder="学生电子邮件" >
        
         <!-- ######################### -->
        	
        <div class="selectButton">
        	<select class="form-control" id="classesId" name="classesId">
        		<c:forEach items="${requestScope.classeslist }" var="classes">
        		<option value="${classes.classesId }">${classes.classesName }</option>
        		</c:forEach>
        	</select>	
        </div>
        <textarea rows="10" cols="" id="studentDescribe" name="studentDescribe" class="form-control" placeholder="学生信息描述"  style="width: 700px;"></textarea>
        
        <button class="btn btn-lg btn-primary btn-block btn-style" type="submit">创建学生信息</button>
      </form>
      </c:otherwise>	
      </c:choose>
      
		</div>
	</body>

</html>