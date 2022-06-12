package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloController")
public class HelloController extends HttpServlet {
	private String initParam;
	private static final long serialVersionUID = 1L;
       
	
    public HelloController() {
        System.out.println("servler constructor!");
    }
    //시작할때 실행
    @Override
    public void init(ServletConfig config) {
    	System.out.println("servlet init!!");
    	
    	System.out.println("context param: " + config.getServletContext().getInitParameter("url"));
    	
    	System.out.println("init param:" + config.getInitParameter("id"));
    	initParam = config.getInitParameter("id");
    	
    }
    
    @Override
    public void destroy() {
    	System.out.println("servlet destroy!!");
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 방식!");
		System.out.println(request.getParameter("command"));
		System.out.println("initParam: " + initParam);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print("<h1 style='background-color:skyblue;'>HelloServlet</h1>");
		out.print("<h2>servlet 라이프사키을, url mapping</h2>");
		out.print("<a href='home.jsp'>돌아가기</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
