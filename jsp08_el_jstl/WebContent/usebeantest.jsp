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
	<!-- bean은 java의 class -->
	<jsp:useBean id="lee" class="com.test.dto.Score" scope="session"></jsp:useBean> <!-- com.test.dto.Score를 lee라는 이름으로 객체를 만들 것이다 -->
	<!-- Score lee = new Score(); -->
	
	<jsp:setProperty property="name" name="lee" value="이순신"/>
	<!-- lee.setName("이순신"); -->
	
	<jsp:getProperty property="name" name="lee"/>
	<!-- lee.getName(); -->
	
	<a href="res.jsp">result</a>
</body>
</html>