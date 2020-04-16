<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.biz.MVCBizImpl"%>
<%@page import="com.mvc.biz.MVCBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("<"+command+">");
	
	MVCBiz biz = new MVCBizImpl();
	
	
	if(command.equals("list")){
		//1. 받을 데이터 x
		//2. db에서 slecte list에서 list가져옴
		List<MVCDto> list = biz.selectList();
		request.setAttribute("list", list);
		//3. boardlist.jsp로 갈 것이다.
		pageContext.forward("boardlist.jsp");	
		//forward 는 a가 b에 요청하면 b의 권한을 c에게 주고 c를 응답해준다
		
	}else if(command.equals("writeform")){
		//controller에서 기억해야할 세가지
		//1. 받을 데이터가 있는지?
		
		//2. db에서 가져올 데이터가 있는지?
				
		//3. 어디로 갈건지?
		response.sendRedirect("boardwrite.jsp");
		
	}else if(command.equals("writeres")){
		//1. 받을 데이터가 있는지?
		String writer = request.getParameter("writer");		//데이터를 가져온다
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCDto dto = new MVCDto();				//가져온 데이터를 dto에 담는다
		dto.setwriter(writer);
		dto.settitle(title);
		dto.setcontent(content);
		
		//2. db에서 가져올 데이터가 있는지?
		int res = biz.insert(dto);	
						
		//3. 어디로 갈건지?
		if(res >0){
%>
	<script type="text/javascript">
		alert("새로운 글을 등록 완료하였습니다.");
		location.href="controller.jsp?command=list";
	</script>

<%
			
		}else{
			
%>
	<script type="text/javascript">
		alert("글 등록에 실패하였습니다.");
		location.href="controller.jsp?command=writeform";
	</script>

<% 			
		}
		
	}else if(command.equals("muldel")){
		//1. 받을 데이터가 있는지?
		String[] seqs = request.getParameterValues("chk");
	
		//2. db에서 가져올 데이터가 있는지?
		boolean res = biz.multiDelete(seqs);
				
		//3. 어디로 갈건지?
		if(res){
%>
	<script type="text/javascript">
		alert("선택된 글들을 모두 삭제 완료하였습니다.");
		location.href="controller.jsp?command=list";
	
	</script>
<%			
		}else{
			
%>
	<script type="text/javascript">
		alert("선택된 글들을 삭제 실패하였습니다.");
		location.href="controller.jsp?command=list";

	</script>

<%			
		
	}
		}else if(command.equals("detail")){
		
			//1. 받을 데이터가 있는지?
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			
			//2. db에서 가져올 데이터가 있는지?
			MVCDto dto = new MVCDto();
			
			dto = biz.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			//3. 어디로 갈건지?
			pageContext.forward("boarddetail.jsp");
			
			
			
		}else if(command.equals("delete")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			int res = biz.delete(seq);
			
			if(res>0){
				%>
				<script type="text/javascript">
					alert("글 삭제 성공");
					location.href="controller.jsp?command=list";
				
				</script>
				<% 
				
			}else{
				
				%>
				
				<script type="text/javascript">
					alert("글 삭제 실패");
					location.href="controller.jsp?command=detail&seq=<%=seq%>";
				</script>
				
				<%
				
			}
			
		
		
		}else if(command.equals("update")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MVCDto dto = biz.selectOne(seq);
			request.setAttribute("dto", dto);
			
			pageContext.forward("boardupdate.jsp");
			
			
		}else if(command.equals("updateres")){
			
			String title =request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			MVCDto dto = new MVCDto();
			dto.settitle(title);
			dto.setcontent(content);
			dto.setSeq(seq);
			
			int res = biz.update(dto);
			
			if(res>0){
%>
				<script type="text/javascript">
					alert("수정 성공");
					location.href="controller.jsp?command=detail&seq=<%=seq%>";
				</script>

<%				
				
				
			}else{
				
%>

				<script type="text/javascript">
					alert("수정 실패");
					location.href="controller.jsp?command=update&seq=<%=seq%>";
				</script>

<%				
				
			}
			
			
		}

%>


	<h1>잘못왔다</h1>
</body>
</html>