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

	<h1>RESULT</h1>
	
	page : <%=pageContext.getAttribute("pageId") %></br> <!-- 페이지내에서만 값을 가지고 있다. -->
	request : <%=request.getAttribute("requestId") %></br> <!--  -->
	session : <%=session.getAttribute("sessionId") %></br>	<!-- 세션만료 전까지 프로젝트 전체에서 사용 가능 -->
	application : <%=application.getAttribute("applicationId") %></br> <!-- 프로젝트 실행했을 때 부터 끝날 때 까지 -->

</body>
</html>