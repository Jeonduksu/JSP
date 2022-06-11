<%@page import="java.util.List"%>
<%@page import="com.login.dto.MyMemberDto"%>
<%@page import="com.login.dao.MyMemberDao"%>
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
	//command먼저 만들기
	String command = request.getParameter("command");
	System.out.println("[command"+command+"]");
	
	MyMemberDao dao = new MyMemberDao();
	
	if(command.equals("login")){
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MyMemberDto dto = dao.login(id, pw);
		
		//가져왓는데 null일때
		if(dto.getMyid() != null){
			session.setAttribute("dto",dto);
			
			session.setMaxInactiveInterval(60*60);
			//dto.myrole를 가져와서 ADMIN인지 아닌지 비교후
			//맞으면 adminmain.jsp로 이동 하겠다
			//아니면 usermain.jsp로 이동
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
			}else if(dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
		}else{
%>
		<script type="text/javascript">
			alert("로그인 실패");
			location.href="index.jsp";
		</script>
<%			
		}
		
	}else if(command.equals("logout")){
		//session 정보 삭제
		session.invalidate();
		response.sendRedirect("index.jsp");
	}else if(command.equals("userlistall")){
		List<MyMemberDto> list=dao.selectAll();
		
		request.setAttribute("list",list);
		pageContext.forward("userlistall.jsp");
	}else if(command.equals("regisform")){
		response.sendRedirect("registform.jsp");
	}else if(command.equals("idchk")){
		String myid = request.getParameter("id");
		String res = dao.idChk(myid);
		
		boolean idnotused = true;
		
		if(res != null){
			idnotused = false;
		}
		
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
	
	
	}else if(command.equals("insertuser")){
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		MyMemberDto dto = new MyMemberDto();
		
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = dao.insertUser(dto);
		
		if(res>0){
%>
		<script type="text/javascript">
			alert("회원가입 성공");
			location.href="index.jsp";
		</script>
<%		
		}else{
%>			
		<script type="text/javascript">
			alert("회원가입 실패");
			location.href="logincontroller.jsp?command=registform";
		</script>	
<%			
		}
	}else if(command.equals("updateroleform")){
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = dao.selectUser(myno);
		
		request.setAttribute("selectone",dto);
		pageContext.forward("updateroleform.jsp");
	}else if(command.equals("updaterole")){
		int myno=Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("myrole");
		
		int res = dao.updateRole(myno, myrole);
		
		if(res>0){
%>			
		<script type="text/javascript">
			alert("회원 등급 조정 성공");
			location.href="logincontroller.jsp?command=userlistall";
		</script>
<%			
		}else{
%>			
		<script type="text/javascript">
			alert("회원 등급 조정 실패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno%>";
		</script>	
<%			
		}
	}
	
%>
</body>
</html>