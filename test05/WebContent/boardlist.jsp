<%@page import="com.mvc.dto.MVCBoardDto"%>
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
</head>
<body>
<%
	List<MVCBoardDto> list = (List<MVCBoardDto>)request.getAttribute("alllist");
%>

	<h1>글 목록</h1>
	<form id="muldelform" action="mycontroller.jsp" method="post">
		<input type="hidden" name="command" value="muldel">
		<table border="1">
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked);"></th>
				<th>NO</th>
				<th>WRITER</th>
				<th>TITLE</th>
				<th>CONTENT</th>
				<th>DATE</th>
			</tr>
			
<%
		if(list.size()==0){
%>			
		<tr>
			<td colspan="5"><b>--------글이 존재하지 않습니다-------</b></td>
		</tr>	
<%			
		}else {
			for(MVCBoardDto dto : list){
%>				
		<tr>
			<td><input type="checkbox" name="chk" value="<%=dto.getSeq() %>"></td>
			<td><%=dto.getSeq() %></td>
			<td><%=dto.getWriter() %></td>
			<td><%=dto.getTitle() %></td>
			<td><a href="mycontroller.jsp?command=boarddetail&seq=<%=dto.getSeq()%>"><%=dto.getContent() %></a></td>
			<td><%=dto.getRegdate() %></td>
		</tr>
<%				
			}
		}
%>
		<tr>
			<td colspan="5">
				<input type="submit" value="삭제">
				<input type="button" value="글쓰기" onclick="location.href='mycontroller.jsp?command=boardinsertform'">
			</td>
		</tr>
	
		</table>
	</form>
</body>
</html>