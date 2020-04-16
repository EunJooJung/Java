<%@page import="com.md.dto.MdDto"%>
<%@page import="com.md.dao.MdDao"%>
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
<%@ include file = "./form/header.jsp"%>

	<h1>글 수정</h1>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	MdDao dao = new MdDao();
	MdDto dto = dao.selectOne(seq);


%>	
	<form action="mdupdateres.jsp" method = "post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type ="text" name ="title" value="<%=dto.getTitle()%>"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea row="10" cols="60" name ="content"><%=dto.getContent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type = "hidden" name = "seq" value="<%=dto.getSeq() %>"/>
					<input type = "submit" value="수정"/>
					<input type = "button" value="취소" onclick=""/>
				</td>
			</tr>
		
		</table>
	
	
	</form>



<%@ include file = "./form/footer.jsp"%>

</body>
</html>