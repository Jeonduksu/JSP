<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>글 목록</h1>
<table border="1">
	<col width="50"><col width="100"><col width="300"><col width="100">
	<tr>
		<th>NO</th>
		<th>WRITER</th>
		<th>TITLE</th>
		<th>DATE</th>
	</tr>
	<c:choose>
		<c:when test="${empty list }">
			<tr>
				<td colspan="4">---------작성된 글이 존재하지 않습니다----</td>
			</tr>
		</c:when>
	<c:otherwise>
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.seq }</td>
				<td>${dto.writer }</td>
				<td>${dto.title }</td>
				<td>${dto.regdate }</td>
			</tr>
		</c:forEach>	
	</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="4">
			<input type="button" value="글쓰기" onclick="location.href='controller.do?writeform'">
	</tr>
</table>
</body>
</html>