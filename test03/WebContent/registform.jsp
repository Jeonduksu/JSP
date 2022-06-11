<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 회원가입 페이지 -->
	<h1>회 원 가 입</h1>
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="insertuser">
		
	</form>
</body>
</html>