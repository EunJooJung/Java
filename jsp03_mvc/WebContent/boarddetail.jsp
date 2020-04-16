<%@page import="com.mvc.dao.MVCDaoImpl"%>
<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="com.mvc.dao.MVCDao"%>
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

	MVCDto dto = (MVCDto)request.getAttribute("dto");

%>

	<h2>글보기</h2>
	
		<table border="1">
			<tr>
				<th>글쓴이</th>
				<td><%=dto.getwriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=dto.gettitle() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getcontetn() %></textarea></td>
			</tr>
			<tr>
				<td>
					<input type="button" value="수정" onclick="location.href='controller.jsp?command=update&seq=<%=dto.getSeq()%>'"/>
					<input type="button" value="삭제" name ="chk" onclick="location.href='controller.jsp?command=delete&seq=<%=dto.getSeq()%>'"/>
					<input type="button" value="목록" onclick="location.href='controller.jsp?command=list'"/>
				</td>
			</tr>
		</table>
	


</body>
</html>