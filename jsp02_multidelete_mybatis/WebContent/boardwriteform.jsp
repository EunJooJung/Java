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

<%@ include file="./form/header.jsp" %>	<!-- include 경로 다시 설명 필요!(상대경로, 절대경로) -->

	<h1>글쓰기</h1>
	<form action ="boardwriteformres.jsp" method="post" >	<!-- 데이터를 한번에 보낼 때 form태그 -->
	<table border="1">
	<tr>
		<th>작성자</th>
		<td><input type = "text" name = "mdwriter"></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type = "text" name = "mdtitle"></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="6" cols="60" name = "mdcontent"></textarea></td>
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type = "submit" value="작성"/>
			<input type = "button" value="취소" onclick="location.href='boardlist.jsp'"/>
		</td>
	</tr>
		
	</table>
	</form>


<%@ include file="./form/footer.jsp" %>
</body>
</html>