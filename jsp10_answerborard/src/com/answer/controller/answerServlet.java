package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.answerBIz;
import com.answer.dto.answerDto;

/**
 * Servlet implementation class answerServlet
 */
@WebServlet("/answer.do")
public class answerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public answerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatch = request.getRequestDispatcher(url);
    	dispatch.forward(request, response);
    	
    }
    
    private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
    	PrintWriter out = response.getWriter();
    	out.println("<script>");
    	out.println("alert('"+msg+"')");
    	out.println("location.href='"+url+"'");
    	out.println("</script>");
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("<"+command+">");
		
		answerBIz biz = new answerBIz();
		
		if(command.equals("list")) {
			
			List<answerDto> list = biz.SelectList();
			
			request.setAttribute("list", list);
			
			dispatch("answerlist.jsp", request, response);
			
		}else if(command.equals("detail")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			answerDto dto = biz.SelectOne(boardno);
			
			request.setAttribute("dto", dto);
			
			dispatch("answerdetail.jsp", request, response);
			
		}else if(command.equals("insert")) {
			dispatch("answerInsert.jsp", request, response);
			
		}else if(command.equals("insertres")) {
		
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			answerDto dto = new answerDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.insert(dto);
			
			if(res>0) {
				jsResponse("글 등록 성공", "answer.do?command=list", response);
			}else {
				jsResponse("글 등록 실패", "answer.do?command=list", response);
			}
		}else if(command.equals("update")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			answerDto dto = biz.SelectOne(boardno);
			request.setAttribute("dto", dto);
			
			dispatch("answerupdate.jsp", request, response);
		}else if(command.equals("updateres")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			answerDto dto = new answerDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setBoardno(boardno);
			
			int res = biz.update(dto);
			
			if(res>0) {
				jsResponse("글 수정 성공", "answer.do?command=list", response);
			}else {
				jsResponse("글 수정 실패", "answer.do?command=list", response);
			}
		
		}else if(command.equals("answerinsert")) {
		
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			answerDto dto =new answerDto();
			dto.setBoardno(boardno);
			
			request.setAttribute("dto", dto);
			
			dispatch("insertRe.jsp", request, response);
			
			
		}else if(command.equals("insertRe")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			answerDto dto = new answerDto();
			dto.setBoardno(boardno);
			dto.setContent(content);
			dto.setTitle(title);
			dto.setWriter(writer);
			
			int res = biz.insertRe(dto);
			
			if(res>0) {
				jsResponse("답글 작성 성공", "answer.do?command=list", response);
			}else {
				jsResponse("답글 작성 실패", "answer.do?command=list", response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	}

}
