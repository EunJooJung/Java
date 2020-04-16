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

	MyMemberDto dto = (MyMemberDto)request.getAttribute("dto");
%>

<h1>내정보보기</h1>

<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>등급</th>
		</tr>
		<tr>
			<td><%=dto.getMyno() %></td>
			<td><%=dto.getMyid() %></td>
			<td><%=dto.getMypw() %></td>
			<td><%=dto.getMyname() %></td>
			<td><%=dto.getMyaddr() %></td>
			<td><%=dto.getMyphone() %></td>
			<td><%=dto.getMyemail() %></td>
			<td><%=dto.getMyrole() %></td>
		</tr>
		
		<tr>
		<td colspan="10">
			<input type="button" value="수정" onclick="location.href='logincontroller.jsp?command=userupdate&myno=<%=dto.getMyno()%>'"/>
			<button onclick="location.href='adminmain.jsp'">메인으로</button>
		</td>
	</tr>
	</table>



</body>
</html>