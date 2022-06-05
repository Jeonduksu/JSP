package com.el.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.el.score.Score;


@WebServlet("/controller.do")
public class Mycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		System.out.println("[command: " + command + "]");
		
		if(command.equals("elpage")) {
			response.sendRedirect("basic.jsp");
		
		}else if(command.equals("eltest")) {
			Score sc = new Score("홍길동",100,64,98);
			
			request.setAttribute("score", sc);
			
			RequestDispatcher disp = request.getRequestDispatcher("eltest.jsp");
			disp.forward(request, response);
		
		}else if(command.equals("jstlpage")){
			List<Score> res = new ArrayList<>();
			for(int i=1; i<6; i++) {
				Score sc = new Score("이름"+i,70+i,80+i,90+i);
				res.add(sc);
			}
			request.setAttribute("list", res);
			RequestDispatcher disp = request.getRequestDispatcher("jstlpage.jsp");
			disp.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
