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

<% MVCDto dto = (MVCDto)request.getAttribute("dto"); %>

<body>

	<h1>detail</h1>

	<table border="1">

		<tr>
			<th>번호</th>
			<td><%=dto.getSeq() %></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>	
		<tr>	
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>	
		<tr>	
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea></td>
		</tr>	
		<tr>	
			<th>작성일</th>
			<td><%=dto.getRegdate() %></td>
		</tr>		
		<tr>
			<td>
				<input type="button" value="수정" onclick="location.href='mvc.do?command=update&seq=<%=dto.getSeq()%>'"/>
				<input type="button" value="삭제" onclick="location.href='mvc.do?command=delete&seq=<%=dto.getSeq()%>'"/>
				<input type="button" value="뒤로" onclick=""/>
			</td>
		</tr>
	
	
	</table>


</body>
</html>