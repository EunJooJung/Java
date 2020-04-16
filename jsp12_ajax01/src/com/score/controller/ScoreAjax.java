package com.score.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class ScoreAjax
 */
@WebServlet("/socre.cal")
public class ScoreAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoreAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/thml; charset=utf-8");
		
		String name =request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int math = Integer.parseInt(request.getParameter("math"));
		
		int sum = kor + eng + math;
		double avg = sum/3.0;
		
		//json simple jar 파일 안에 있던 class
		JSONObject obj = new JSONObject();
		//hashMap을 기준으로 만들어져 있기 때문에 Generic을 설정하라는 경고가 뜬다.(마우스를 올려 add@Super를 해주면 사라짐)
		
		obj.put("name",name);
		obj.put("sum", sum);
		obj.put("avg", avg);
		String res = obj.toJSONString();
		System.out.println("servlet에서 만들어짐"+res);
		
		PrintWriter out = response.getWriter();
		out.print(res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
