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


<form action="logincontroller.jsp" method="post">
		<input type ="hidden" name="command" value="userinfoupdate"/>
		<input type="hidden" name ="seq" value="<%=dto.getMyno() %>">
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>가입여부</th>
			<th>등급</th>
		</tr>
		<tr>
			<td><%=dto.getMyno() %></td>
			<td><%=dto.getMyid() %></td>
			<td><input type="text" name="mypw" value="<%=dto.getMypw() %>"/></td>
			<td><input type="text" name="myname" value="<%=dto.getMyname() %>"/></td>
			<td><input type="text" name="myaddr" value="<%=dto.getMyaddr() %>"/></td>
			<td><input type="text" name="myphone" value="<%=dto.getMyphone() %>"/></td>
			<td><input type="text" name="myemail" value="<%=dto.getMyemail() %>"/></td>
			<td><%=dto.getMyenabled().equals("Y")?"가입":"탈퇴" %></td>
			<td><%=dto.getMyrole() %></td>
		</tr>
		
		<tr>
		<td colspan="10">
			<input type="submit" value="수정하기"/>
			<button onclick="location.href='index.jsp'">메인으로</button>
		</td>
	</tr>
	</table>
</form>

</body>
</html>