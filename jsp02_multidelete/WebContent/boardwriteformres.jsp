<%@page import="com.md.dao.MdDao"%>
<%@page import="com.md.dto.MdDto"%>
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
	String mdwriter = request.getParameter("mdwriter");
	String mdtitle = request.getParameter("mdtitle");
	String mdcontent = request.getParameter("mdcontent");
	
	
	MdDto dto = new MdDto();	//디비에 보내기 전에 dto로 감싼다
	dto.setWriter(mdwriter);
	dto.setTitle(mdtitle);
	dto.setContent(mdcontent);
	
	MdDao dao = new MdDao();
	
	int res = dao.insert(dto);
	
	if(res>0){

%>
<script type="text/javascript">
	alert("글 작성 성공");
	location.href="boardlist.jsp";
</script>
<%
	} else{
%>
<script type="text/javascript">
	alert("글 작성 실패");
	history.back(); //뒤로가기와 똑같은 역할
</script>
<%
	}
%>


</body>
</html>