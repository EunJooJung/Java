<%@page import="com.mvc.dto.MVCDto"%>
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
<%
	MVCDto dto = (MVCDto) request.getAttribute("dto");

%>
<body>

	<form action="controller.jsp" method="post">
		<input type ="hidden" name="command" value="updateres"/>
		<input type="hidden" name ="seq" value="<%=dto.getSeq()%>">
		
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getwriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<%=dto.gettitle() %>"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content" ><%=dto.getcontetn() %></textarea></td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="수정">
					<input type="button" value="취소" onclick="">
				</td>
			</tr>
		
		
		</table>
	</form>



</body>
</html>