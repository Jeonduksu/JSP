<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>글쓰기</h1>
	<!-- action 데이터를 어디로 넘길거냐
		get은 url에 값이 나오고
		post로 보내면 url에 값이 안나온다
	 -->
	<form action="myinsert_res.jsp" method="get">
		<table>
			<tr>
				<th>이름</th>
				<td><input type="text" name="myname"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="mytitle"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10 cols="60" name="mycontent"></textarea>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="입력">
					<input type="button" value="취소" onclick="location.href='mylist.jsp'">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>