<%@page import="com.login.dto.MyMemberDto"%>
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
	MyMemberDto dto = (MyMemberDto)session.getAttribute("dto");
%>
	<h1><%=dto.getMyname() %>님 환영합니다.</h1>
	
	<div>
		<td>
			<input type="button" value="내정보보기" onclick="location.href='logincontroller.jsp?myno=<%=dto.getMyno()%>&command=myinfo'"/>
		</td>
	</div>

</body>
</html>