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
 * Servlet implementation class ScoerAjax
 */
@WebServlet("/score.cal")
public class ScoerAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScoerAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        String name = request.getParameter("name");
        int kor = Integer.parseInt(request.getParameter("kor"));
        int eng = Integer.parseInt(request.getParameter("eng"));
        int math = Integer.parseInt(request.getParameter("math"));
        
        int sum = kor+eng+math;
        double avg = sum/3.0;
        
        //import org.json.simple.JSONObject;
        //json simple jar 파일에 있음
        JSONObject obj = new JSONObject();
        //HashMap 기반이라서 <Generic> 선언하라고 warning 뜨는 것
        obj.put("name", name);
        obj.put("sum", sum);
        obj.put("avg", avg);
        String res = obj.toJSONString();
        System.out.println("servlet에서 만들어진 결과(json) : " + res);
        
        PrintWriter out = response.getWriter();
        out.println(res);
        
     }


     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
        doGet(request, response);
     }

}
