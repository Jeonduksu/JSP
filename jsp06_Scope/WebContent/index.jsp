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
	//page : jsp페이지 내에서만 객체를 공유하는 영역 ex)< % % > 
	//request : 요청을 받아서 응답하기 까지 객체가 유요햔 영역 ex)forward
	//session : 하나의 브라우저당 1개의 session객체가 공유 ex)request.getSession()
	//application : 하나의 app 당 1개의 application객체가 공유 ex)request.getServletContext()
	
	//page < request < session < application
	
	pageContext.setAttribute("pageId","myPageIdVal");
	request.setAttribute("reqId","myReqIdVal");
	session.setAttribute("sessionId","mySessionVal");
	application.setAttribute("appId", "myAppIdVal");
%>
	
<h1>index</h1>	

pageId : <%=pageContext.getAttribute("pageId") %> <br>
requestId : <%=request.getAttribute("reqId") %><br>
sessionId : <%=session.getAttribute("sessionId") %> <br>
applicationId : <%=application.getAttribute("appId") %> <Br>

<br>
<a href="result.jsp">이동(result.jsp)</a>
<a href="myservlet.do">이동(controller)</a>






</body>
</html>