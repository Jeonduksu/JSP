package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloController
 */
@WebServlet("/HelloController")
public class HelloController extends HttpServlet {
	private String initParam;
	private static final long serialVersionUID = 1L;
  
    public HelloController() {
        System.out.println("servlet constructor!");
    }

	public void init(ServletConfig config) throws ServletException {
		System.out.println("servlet init!!");
		
		System.out.println("context param: "+ config.getServletContext().getInitParameter("url"));
		
		System.out.println("init param : " + config.getInitParameter("id"));
		initParam = config.getInitParameter("id");
	}


	public void destroy() {
		System.out.println("Servlet destroy!!");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get 방식!");
		System.out.println(request.getParameter("command"));
		System.out.println("initParam : " + initParam);
		
		request.setCharacterEncoding("URF-8");
		response.setContentType("text/html; chatset=UTF-8");
		
		PrintWriter out = response.getWriter();
		out.print("<h1 style='background-color:skyblue;'>HelloServle</h1>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post!!");
		doGet(request, response);
	}

}
