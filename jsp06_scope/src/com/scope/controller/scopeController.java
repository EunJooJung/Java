package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class scopeController
 */
@WebServlet("/scopeController")
public class scopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public scopeController() {
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * 서블릿 만들고 나면 인코딩부터 하기!!!
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("Controller 도착!!");
		
		String requestId = (String)request.getAttribute("requestId");
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		ServletContext application = getServletContext();
		String applicationId = (String)application.getAttribute("applicationId");
		
		System.out.println("request : "+requestId);
		System.out.println("session : "+sessionId);
		System.out.println("application : "+applicationId);
		
		String param = request.getParameter("req");
		System.out.println("requset parameter : "+param);
		
		/*
		PrintWriter out = response.getWriter();
		out.println("<h1>응답</h1>");
		out.println("<table border='1'/>");
		out.println("<tr><th>scope</th><th>값</th></tr>");
		out.println("<tr>page<td>"+requestId+"</td></tr>");
		
		out.println("</table>");
		*/
		
		RequestDispatcher dispatch = request.getRequestDispatcher("result.jsp");
		request.setAttribute("requestId", "servlet에서 보내준 request");
		dispatch.forward(request, response);
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		doGet(request, response);
	}

}
