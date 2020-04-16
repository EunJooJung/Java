<%@page import="com.mvc.dto.MVCDto"%>
<%@page import="java.util.List"%>
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

	<h1>글 작성</h1>
	<form action="mvc.do" method="post">
		<input type="hidden" name="command" value="insertres"/>
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><input type="text" name="writer"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="글쓰기"/>
				<input type="button" value="목록으로" onclick="location.href='mvc.do?command=list'"/>
			</td>
		</tr>
	
	
	</table>
	
	</form>	

</body>
</html>