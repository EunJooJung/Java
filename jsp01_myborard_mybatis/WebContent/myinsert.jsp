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

	<h1>글쓰기</h1>
	
	<form action="myinsertres.jsp" method="post">	<!-- 데이터보내주는(name속성) 액션속성에 적어준 주소로 post타입으로 -->
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name ="myname"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name ="mytitle"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="mycontent"></textarea></td>
			</tr>
			<tr>
			<td colspan="2" align="right">
				<input type ="submit" value="글쓰기"/>	<!--submit은 데이터 보내는 애 /폼태그안에있는 네임으로 붙어져있는 애들이 액션속성경로로 보내짐 -->
				<input type ="button" value="취소" onclick="" />
			</td>
			</tr>
		</table>
	</form>

</body>
</html>