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
	
	
	<jsp:useBean id="dto" class="com.mvc.dto.MyDto" scope="request"></jsp:useBean>
		
		<h1>상세글 보기</h1>
	
		<table border="1">
		
		<tr>
			<th>작성자</th>
			<td><jsp:getProperty property="writer" name="dto"/></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><jsp:getProperty property="title" name="dto"/></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><jsp:getProperty property="content" name="dto"/></textarea></td>
		</tr>
		<tr>
			<th>작성날짜</th>
			<td><jsp:getProperty property="regdate" name="dto"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='con.do?command=update&seq=${dto.seq}'">
				<input type="button" value="삭제" onclick="location.href='con.do?command=delete&seq=${dto.seq}'">
				<input type="button" value="목록" onclick="location.href='con.do?command=list'">
			</td>
		</tr>
		
		</table>
		
		

</body>
</html>