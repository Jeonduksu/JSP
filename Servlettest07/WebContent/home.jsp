<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="HelloServlet.do?command=hello">Hello</a>
	
	<h1>Hello</h1>
	<form action="HelloServlet.do" method="post">
		<input type="hidden" name="command" value="helloGet">
		<input type="submit" value="hello">
	</form>
</body>
</html>