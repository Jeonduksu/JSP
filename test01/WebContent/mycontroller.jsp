<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.dao.MVCBoardDao"%>
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
	String command = request.getParameter("command");

	MVCBoardDao dao = new MVCBoardDao();
	
	System.out.println("[command:"+command+"]");
	
	if(command.equals("boardlist")){
		List<MVCBoardDto> list = dao.selectAll();
		
		request.setAttribute("allList", list);
		pageContext.forward("boardlist.jsp");
	}else if(command.equals("boardinsertform")){
		response.sendRedirect("boardinsert.jsp");
	}else if(command.equals("boardinsert")){
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto();
		dto.setWriter(writer);
		dto.setTitle(title);
		dto.setContent(content);
		
		int res = dao.insert(dto);
		
		if(res>0){
%>			
		<script type="text/javascript">
			alert("글 작성 성공");
			location.href='mycontroller.jsp?command=boardlist';
		</script>	
<%			
		}else {
%>			
		<script type="text/javascript">
			alert("글 작성 실패");
			location.href='mycontroller.jsp?command=boarddetail&seq='+seq;
		</script>	
<%			
		response.sendRedirect("mycontroller.jsp?command=boardinsertform");		

		}
	}else if(command.equals("boarddetail")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MVCBoardDto dto = dao.selectOne(seq);
		
		request.setAttribute("dto",dto);
		pageContext.forward("boarddetail.jsp");
	}else if(command.equals("boardupdate")){
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = dao.selectOne(seq);
		
		request.setAttribute("dto", dto);
		pageContext.forward("boardupdate.jsp");	
	}else if(command.equals("boardupdate")){
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		MVCBoardDto dto = new MVCBoardDto();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setSeq(seq);
		
		int res = dao.update(dto);
		
		if(res>0){
%>

		<script type="text/javascript">
			alert("글 수정 성공");
			location.href="mycontroller.jsp?command=boarddetail&seq="+<%=seq%>
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("글 수정 삭제");
			location.href="mycontroller.jsp?command=boardupdateform&seq="+<%=seq%>
		</script>
<%			
		}
	}else if(command.equals("boarddelete")){
		String[] seq = new String[1];
		seq[0] = request.getParameter("seq");
		
		int res = dao.multiDelete(seq);
		
		if(res>0){
%>
		<script type="text/javascript">
			alert("글 삭제 성공");
			location.href='mycontroller.jsp?command=boardlist';
		</script>
<%			
		}else{
%>
		<script type="text/javascript">
			alert("글 삭제 실패");
			location.href='mycontroller.jsp?command=boardlist';
		</script>
<%			
		}
	}
%>
</body>
</html>