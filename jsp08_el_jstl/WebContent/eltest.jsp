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
	
%>
	<h1>EL Test</h1>
	
	<table border="1">
		<tr>
			<th colspan="2">${hong.name}님의 점수는</th>
		</tr>
		<tr>
			<th>국어</th>
			<td>${hong.kor }</td>	<!-- 해당 객체의 getKor()을 호출해 준다 -->
		</tr>
		<tr>
			<th>영어</th>
			<td>${hong.eng }</td>
		</tr>
		<tr>
			<th>수학</th>
			<td>${hong.math }</td>
		</tr>
		<tr>
			<th>총점</th>
			<td>${hong.sum }</td>
		</tr>
		<tr>
			<th>평균</th>
			<td>${hong.avg }</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${hong.grade }</td>
		</tr>
	</table>

</body>
</html>