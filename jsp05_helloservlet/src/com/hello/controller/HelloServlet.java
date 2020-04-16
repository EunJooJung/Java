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
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {	//com.hello.controller.helloSErvlet()
    	System.out.println("Servlet 생성자!!");
    	
    }
    
    private String initParam;
    private String contextParam;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("Servlet init!!!");
	
		
		contextParam = config.getServletContext().getInitParameter("jdbcurl");	//전체 서블릿에서 사용되는 애들
		initParam = config.getInitParameter("driver");	//init된 서블릿에서만 사용되는 애들
	}



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html); charset=UTF-8");
		
		System.out.println("GET 방식으로 넘어옴!!");
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("POST방식으로 넘어옴!!");
		
		System.out.println("initParam : "+ initParam);
		System.out.println("contextParam : "+contextParam);
		
		
		String command = request.getParameter("command");
		System.out.println("command : "+ command);
		
		
		PrintWriter out = response.getWriter();
		out.println("<h1 style='background-color:skyblue'>HelloServlet</h1>");
		out.println("<h3>init-service-doGet.doPost-destroy</h3>");
		out.println("<a href='home.html'>돌아가기</a>");
		
		
	}
	
	@Override
	public void destroy() {
		System.out.println("servlet destroy!!!");
	}

}
