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

import com.mvc.biz.MVCBiz;
import com.mvc.biz.MVCBizImpl;
import com.mvc.dto.MVCDto;

/**
 * Servlet implementation class MVCServlet
 */
@WebServlet("/MVCServlet")
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MVCServlet() {
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.printf("<%s>\n",command);
		
		MVCBiz biz = new MVCBizImpl();
		
		if(command.equals("list")) {
			
			//받을 값 있는지
			
			//가져올게 있는지
			List<MVCDto> list = biz.selectList();
			request.setAttribute("list", list);
			
			//어디로 보낼건지
			
			dispatch("mvclist.jsp",request,response);
			
			
		}else if(command.equals("insertform")) {
			//받을 값 있는지
			//가져올게 있는지
			
			//어디로 보낼건지
			dispatch("insertform.jsp", request, response);
			
		}else if(command.equals("insertres")) {
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCDto dto = new MVCDto();
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.insert(dto);
			
			if(res >0) {
				
				//dispatch("mvc.do?command=list", request, response);
				jsResponse("글 등록 성공!", "mvc.do?command=list", response);
				
			}else {
				/*
				List<MVCDto> list = biz.selectList();
				request.setAttribute("list", list);
				dispatch("mvclist.jsp", request, response);
				*/
				
				jsResponse("글 등록 실패ㅠ", "mvc.do?command=insertform", response);
			}
			
			
		}else if(command.equals("detail")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MVCDto dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch("detail.jsp", request, response);
			
		}else if(command.equals("delete")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int res = biz.delete(seq);
			
			dispatch("mvc.do?command=list", request, response);
			
			
		}else if(command.equals("update")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCDto dto =biz.selectOne(seq);
			request.setAttribute("dto", dto);
			dispatch("updateres.jsp", request, response);
			
		}else if(command.equals("updateres")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCDto dto = new MVCDto();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = biz.update(dto);
			
			if(res >0) {
				
				jsResponse("글 수정 성공!", "mvc.do?command=list", response);
				
			}else {
				
				jsResponse("글 수정 실패ㅠ", "mvc.do?command=update&seq="+seq, response);
			}
			
		}
		
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String res = "<script type='text/javascript'>"+" alert('"+msg+"');"+" location.href='"+url+"';"+"</script>";
		
		PrintWriter out = response.getWriter();
		out.println(res);
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
