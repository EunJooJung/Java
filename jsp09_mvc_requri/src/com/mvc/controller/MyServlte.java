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
 * Servlet implementation class MyServlte
 */
@WebServlet(urlPatterns= {"/mylist","/detail","/writeform" , "/mywriteres","/mydelete","/myupdate","/updateres"})
public class MyServlte extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MyBiz biz;
	
	private void getRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		biz = new MyBizImpl();
		
		String command = request.getRequestURI();
		System.out.printf("[%s]\n", command);
		
		if(command.endsWith("/mylist")) {
			doMyList(request, response);
		}else if(command.endsWith("detail")) {
			doDetail(request, response);
			
		}else if(command.endsWith("/writeform")) {
			doMyWriteForm(request, response);
			
		}else if(command.endsWith("/mywriteres")) {
			doMyWriteRes(request, response);
			
		}else if(command.endsWith("/mydelete")) {
			doMyDelete(request, response);
			
		}else if(command.endsWith("/myupdate")) {
			doMyUpdate(request, response);
			
		}else if(command.endsWith("/updateres")) {
			doUpdateRes(request, response);
		}
	}
       
    private void doUpdateRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	int seq = Integer.parseInt(request.getParameter("seq"));
    	
    	MyDto dto = new MyDto();
    	dto.setTitle(title);
    	dto.setContent(content);
    	dto.setSeq(seq);
    	
    	int res = biz.update(dto);
    	System.out.println(res);
    	if(res>0) {
    		jsResponse("글 수정 성공", "detail?seq="+seq, response);
    	}else {
    		jsResponse("글 수정 실패", "detail?seq="+seq, response);
    	}
    	
		
	}

	private void doMyUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	int seq = Integer.parseInt(request.getParameter("seq"));
    	
    	MyDto dto = biz.selectOne(seq);
    	
    	request.setAttribute("dto", dto);
    	
    	dispatch("updateres.jsp", request, response);
		
	}

	private void doMyDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	int seq = Integer.parseInt(request.getParameter("seq"));
    	
    	int res = biz.delete(seq);
    	
    	if(res>0) {
    		jsResponse("글 삭제 성공", "mylist", response);
    	}else {
    		jsResponse("글 삭제 실패", "detail&seq="+seq, response);
    	}
		
	}

	private void doMyWriteRes(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		MyDto dto = new MyDto(0, writer, title,  content, null);
		
		int res = biz.insert(dto);
		
		if(res>0) {
			jsResponse("글 작성 성공", "mylist", response);
		}else {
			jsResponse("글 작성 실패", "writeform", response);
		}
				
		
	}

	private void doMyWriteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect("mywrite.jsp");
		
	}

	private void doDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		MyDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		dispatch("mydetail.jsp", request, response);
		
	}

	private void doMyList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<MyDto> list = biz.selectList();
		request.setAttribute("list", list);
		dispatch("mylist.jsp", request, response);
		
	}
    
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

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
	}

}
