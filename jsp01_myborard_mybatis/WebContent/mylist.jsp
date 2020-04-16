<%@page import="java.util.ArrayList"%>
<%@page import="com.my.dto.MyDto"%>
<%@page import="com.my.dao.MyDao"%>
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
<%

	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();

%>
	<h1>List</h1>
	
	<table border="1">
	
	<colgroup>
		<col width="50">
		<col width="100">
		<col width="200">
		<col width="100">
	</colgroup>
	
	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	
<%
	for(MyDto dto : list){
		
%>

	<tr>
		<td><%=dto.getMyno() %></td>
		<td><%=dto.getMyname() %></td>
		<td><a href='mydetail.jsp?myno=<%=dto.getMyno()%>'><%=dto.getMytitle() %></a></td>
		<td><%=dto.getMydate() %></td>
	</tr>

<%
	}
%>
	
	
	
	<tr>
		<td colspan="4" align="right">
			<input type ="button" onclick="location.href='myinsert.jsp'" value="글쓰기"/>
		</td>
	</tr>
	
	</table>

</body>
</html>