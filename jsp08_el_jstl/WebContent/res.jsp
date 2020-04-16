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

	<h1>${lee.name }</h1>
	
	<jsp:useBean id="lee" class="com.test.dto.Score" scope="session"></jsp:useBean>
	
	<h1><jsp:getProperty property="name" name="lee"/></h1>

</body>
</html>