<%@page import="com.login.dto.MyMemberDto"%>
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
<%
	MyMemberDto dto = (MyMemberDto)session.getAttribute("dto");
%>
<div>
	<span><%=dto.getMyname() %>님 환영합니다.(등급: <%=dto.getMyrole() %>)</span>
	<a href="logincontroller.jsp?command=logout">로그아웃</a>
</div>

<h1>메인화면</h1>
<div>
	<div>
		<a href="logincontroller.jsp?command=userinfo&myno<%=dto.getMyno()%>">내정보보기</a>
	</div>
	<div>
		<a href="mycontroller.jsp?command=boardlist">게시판 목록</a>
	</div>
</div>

</body>
</html>