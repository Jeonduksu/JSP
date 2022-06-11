package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.dto.MyMVCDto;
import com.mvc.service.MyMVCBiz;
import com.mvc.service.MyMVCBizImpl;


@WebServlet("/MyMVCServlet")
public class MyMVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		MyMVCBiz biz = new MyMVCBizImpl();
		
		if(command.equals("list")) {
			List<MyMVCDto> list = biz.selectAll();
			
			request.setAttribute("list", list);
			dispatch("boardlist.jsp",request,response);
		}else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyMVCDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			dispatch("boarddetail.jsp",request,response);
		}else if(command.equals("writeform")) {
			response.sendRedirect("boardwrite.jsp");
		}else if(command.equals("boardwrite")) {
			String write = request.getParameter("write");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MyMVCDto dto = new MyMVCDto();
			dto.setWriter(write);
			dto.setTitle(title);
			dto.setContent(content);
			
			boolean res = biz.insert(dto);
			
			if(res) {
				jsResponse("글 작성 성공","controller.do?command=list",response);
			}else {
				jsResponse("글 작성 실패","controller.do?command=writeform",response);
			}
		}else if(command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			MyMVCDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			dispatch("boardupdate.jsp",request,response);
		}else if(command.equals("boardupdate")) {
			String title=request.getParameter("title");
			String content=request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MyMVCDto dto = new MyMVCDto();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			
			boolean res = biz.update(dto);
			
			if(res) {
				jsResponse("글 수정 성공","controller.do?command=detail&seq="+seq,response);
			}else {
				jsResponse("글 수정 실패","controller.do?command=updateform&seq="+seq,response);
			}
		}else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			boolean res = biz.delete(seq);
			if(res) {
				dispatch("controller.do?command=list",request,response);
			}else {
				dispatch("controller.do?command=detail&seq="+seq,request,response);
			}
		}
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String str = "<script type='text/javascript'>"+"alert('"+msg+"');"+"location.href='"+url+"';"+"</script>";
		PrintWriter out = response.getWriter();
		out.print(str);
	}
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
