package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 1번쨰 방식은 클라스 만들어서 상속해서 만들면 된다 상속은 HTTPSERVLTE HTTP서블릿 
// 2번쨰 방식은 바로 이거처럼 만드는거
@WebServlet("/HelloController")
public class HelloController extends HttpServlet {
	private String initParam;
	private static final long serialVersionUID = 1L;
         
	//생성자는 객체 만들때 실행
    public HelloController() {
        System.out.println("servlet constructor!");
    }

    @Override
	public void init(ServletConfig config) throws ServletException {
    	System.out.println("servlet init!!");
    	
    	System.out.println("context param: " + config.getServletContext().getInitParameter("url"));
    	
    	System.out.println("init param: " + config.getInitParameter("id"));
    	initParam = config.getInitParameter("id");
    	
    
    }

    //종료될때 실행
    @Override
	public void destroy() {
    	System.out.println("servlet destroy!!");

	}

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 방식!");
		System.out.println(request.getParameter("command") );
		System.out.println("initParam : " + initParam);
		//이부분이 실행
	
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		//HTML 코드 가져온다
		PrintWriter out = response.getWriter();
		out.print("<h1 style='background-color:skyblue;'>HelloServlet</h1>");
		out.print("<h2>servlet 라이프사키을, url mapping</h2>");
		out.print("<a href='home.jsp'>돌아가기</a>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post!!");
		doGet(request, response);
	}

}
