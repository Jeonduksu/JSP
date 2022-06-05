<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@ page import="com.my.dto.MyBoardDto" %>
<%@ page import="com.my.dao.MyBoardDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 이게controller 라고 생각 
	Parameter로 데이터를 받아오자
	-->
<%
	String myname = request.getParameter("myname");
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");

	MyBoardDto dto = new MyBoardDto();
	dto.setMyname(myname);
	dto.setMytitle(mytitle);
	dto.setMycontent(mycontent);
	
	MyBoardDao dao = new MyBoardDao();
	int res = dao.insert(dto);
	/* 성공 실패를 받아오는 값 */
	/* 결과 값에 따라 글이 등록했는지 못했는지 알수 있다. */
	if(res>0){
%>	
	<h1>글 등록 성공</h1>
	<button onclick="location.href='mylist.jsp'">목록으로 이동</button>
	
	<script type="text/javascript">
		/* alert("글 등록 성공"); */
	</script>
<%	
	}else{
%>
	<script type="text/javascript">
		alert("글 등록 실패");
		location.href='myinsert.jsp';
	</script>
		
<%	
	}
%>


</body>
</html>