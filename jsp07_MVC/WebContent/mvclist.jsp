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
<%
	List<MVCDto> list = (List<MVCDto>)request.getAttribute("list");

%>
<body>

	<h1>글 목록</h1>
	
	<table border="1">
	<colgroup>
		<col width="50"/>
		<col width="100"/>
		<col width="300"/>
		<col width="100"/>
	</colgroup>
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	
	<%
		for(MVCDto dto : list){
			
	
	%>
	<tr>
		<td><%=dto.getSeq() %></td>
		<td><%=dto.getWriter() %></td>
		<td><a href="mvc.do?command=detail&seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
		<td><%=dto.getRegdate() %></td>
	</tr>
	
	<%
		}
	%>
	
	<tr>
		<td colspan="4">
			<button onclick="location.href='mvc.do?command=insertform'">글쓰기</button>
		</td>
	</tr>
	</table>	
</body>
</html>