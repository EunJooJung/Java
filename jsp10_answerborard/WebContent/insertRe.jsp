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
	<h1>답글쓰기</h1>
	
	<form action="answer.do" method="get">
		<input type="hidden" name="command" value="insertRe">
		<input type="hidden" name="boardno" value="${dto.boardno }">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="RE:"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
		<tr>
			<td>
				<input type="submit" value="답글 달기">
				<input type="button" value="취소" onclick="location.href='answer.do?command=list'"/>
			</td>
		</tr>
		</table>
	</form>
</body>
</html>