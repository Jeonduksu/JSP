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
<h1>상세 글 보기</h1>
<table border="1">
	<tr>
		<th>N O</th>
		<td>${dto.seq }</td>
	</tr>
	<tr>
		<th>DATE</th>
		<td>${dto.regdate }</td>
	</tr>
	<tr>
		<th>WRITER</th>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<th>TITLE</th>
		<td>${dto.title }</td>
	</tr>
	<tr>
		<th>CONTENT</th>
		<td><textarea rows="10" cols="60" readonly=readonly>${dto.content }</textarea>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="수정" onclick="location.href='controller.do?command=updateform&seq=${dto.seq}'";>
			<input type="button" value="삭제" onclick="location.href='controller.do?command=delete&seq=${dto.seq}'";>
			<input type="button" value="목록" onclick="location.href='mycontroller.do?command=list'";>
		</td>
	</tr>
</table>
</body>
</html>