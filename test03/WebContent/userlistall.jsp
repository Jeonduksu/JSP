<%@page import="com.login.dto.MyMemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function updateRole(myno){
		location.href="logincontroller.jsp?command=updateroleform&myno="+myno;
	};
</script>
</head>
<body>
<%
	List<MyMemberDto> list = (List<MyMemberDto>)request.getAttribute("list");
%>

	<h1>회원정보조회</h1>
	<table border="1">
		<tr>
			<th>NO</th>
			<th>ID</th>
			<th>NAME</th>
			<th>EMAIL</th>
			<th>ROLE</th>
			<th>ROLE_UPDATE</th>
		</tr>
<%
	for(MyMemberDto dto : list){
%>
	<tr>
		<th><%=dto.getMyno() %></th>
		<th><%=dto.getMyid() %></th>
		<th><%=dto.getMyname() %></th>
		<th><%=dto.getMyemail() %></th>
		<th><%=dto.getMyrole() %></th>
		<th><button onclick="updateRole(<%=dto.getMyno()%>);">변경</button></th>
	</tr>
<%
	}
%>		
	<tr>
		<td colspan="6">
		<button onclick="location.href='logincontroller.jsp?command=login&id=<%=((MyMemberDto)session.getAttribute("dto")).getMyid()%>&pw=<%=((MyMemberDto)session.getAttribute("dto")).getMypw()%>">메인</button>
	</tr>	
	</table>
</body>
</html>