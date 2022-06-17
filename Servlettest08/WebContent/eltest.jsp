<%@page import="com.el.score.Score"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Score sc = (Score)request.getAttribute("score");
%>
	<h1>EL page</h1>
	<table border="1">
		<tr>
			<th colspan="2"><%=sc.getName() %>님의 점수는 ..</th>
		</tr>
		<tr>
			<th>국어</th>
			<td><%=sc.getKor() %></td>
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng }</td>
		</tr>
	</table>
</body>
</html>