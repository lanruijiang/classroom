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
			
			
	<form class="form-signin" action="<%=webStie %>/studentServer" method="post">
        <h2 class="form-signin-heading">修改学生信息</h2>
       姓名 <input type="hidden" name="op" value="studentUpdate"/>
        <input type="text" id="studentName" name="studentName" class="form-control" 
        value="${requestScope.student.studentName }" required autofocus>

		电子邮件<input type="email" id="studentEmail" name="studentEmail" class="form-control" 
        value="${requestScope.student.studentEmail }" required autofocus>
        	
		<input type="hidden" name="studentId" value="${requestScope.student.studentId }"/>
		<!-- 完善修改功能 2020-03-03  lrj-->
		联系地址<input type="text" id="studentAddr" name="studentAddr" class="form-control" 
        value="${requestScope.student.studentAddr }" required autofocus>
        
        电话号码<input type="text" id="studentPhone" name="studentPhone" class="form-control" 
        value="${requestScope.student.studentPhone }" required autofocus>
        
       出生日期<input type="date" id="studentBirthday" name="studentBirthday" class="form-control" 
        value="${requestScope.student.studentBirthday }" required autofocus>
		
		<div class="selectButton">
        	<select class="form-control" id="classesId" name="classesId">
        	    <option value="${requestScope.student.classesId }">如果不修改班级则不需要选择班级</option>
        		<c:forEach items="${requestScope.classeslist }" var="classes">
        		<option value="${classes.classesId }">${classes.classesName }</option>
        		</c:forEach>
        	</select>	
        </div>
        简介
        <textarea rows="10" cols="" id="studentDescribe" name="studentDescribe" class="form-control" style="width: 700px;">
        ${requestScope.student.studentDescribe }
        </textarea>
        
        
        <button class="btn btn-lg btn-primary btn-block btn-style" type="submit">修改学生信息</button>
     </form>
		</div>
	</body>

</html>