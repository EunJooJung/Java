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

import com.mvc.biz.MyBiz;
import com.mvc.biz.MyBizImpl;
import com.mvc.dto.MyDto;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/con.do") //어노테이션이 con.do로 가면 myservlet으로 보내줌
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
    }
    
    private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
    	PrintWriter out  = response.getWriter();
    	out.println("<script type='text/javascript'>");
    	out.println("alert('"+msg+"')");
    	out.println("location.href='"+url+"'");
    	out.println("</script>");
    }
    
    private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatch = request.getRequestDispatcher(url);
    	dispatch.forward(request, response);
    	
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command  = request.getParameter("command");
		System.out.println("<"+command+">");

		MyBiz biz = new MyBizImpl();
		
		if(command.equals("list")) {
			
			List<MyDto> list = biz.selectList();	//받을게 있는지
			request.setAttribute("list", list);		//담아서 보내주는거
			
			dispatch("mylist.jsp", request, response); //여기로 보내준다
			
		}else if(command.equals("detail")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MyDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			
			dispatch("mydetail.jsp", request, response);
			
		}else if(command.equals("delete")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int res = biz.delete(seq);
			
			if(res>0) {
				jsResponse("글 삭제 성공", "con.do?command=list", response);
			}else {
				jsResponse("글 등록 실패", "con.do?command=detail", response);
			}
			
			
		}else if(command.equals("insert")) {
			dispatch("myinsert.jsp", request, response);
			
			
		}else if(command.equals("insertres")) {
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MyDto dto = new MyDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.insert(dto);
			
			if(res>0) {
				
				jsResponse("글 등록 성공", "con.do?command=list", response);
			}else {
				
				jsResponse("글 등록 실패", "con.do?command=list", response);
			}
			
			
		}else if(command.equals("update")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MyDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch("updateres.jsp", request, response);
			
			
		}else if(command.equals("updateres")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MyDto dto = new MyDto();
			dto.setContent(content);
			dto.setTitle(title);
			dto.setSeq(seq);
			
			int res = biz.update(dto);
			
			if(res>0) {
				
				jsResponse("글 수정이 성공", "con.do?seq="+seq+"&command=detail", response);
				
			}else {
				
				jsResponse("글 수정 실패", "con.do?seq="+seq+"&command=detail", response);
				
			}
			
			
		}
			
		
		
		response.getWriter().append("<h1><a href='con.do?command=list'>잘못왔다.</a></h1>");
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
