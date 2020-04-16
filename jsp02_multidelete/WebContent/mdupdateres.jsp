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
<%@ include file="./form/header.jsp" %>

<%
	int seq =Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MdDto dto = new MdDto();
	dto.setSeq(seq);
	dto.setTitle(title);
	dto.setContent(content);
	
	MdDao dao = new MdDao();
	
	int res = dao.update(dto);
	if(res>0){
	
%>

<script type="text/javascript">
	alert("수정 성공");
	location.href="boarddetail.jsp?seq=<%=seq%>";
</script>
<%
	}else{
%>
<script type="text/javascript">
	alert("수정 실패!")
	location.href="mdupdate.jsp?seq=<%=seq%>";
</script>

<%
	}
%>

<%@ include file="./form/footer.jsp" %>
</body>
</html>