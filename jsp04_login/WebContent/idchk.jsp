<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	
	onload=function(){
		var id = opener.document.getElementsByName("myid")[0].value;
		document.getElementsByName("id")[0].value = id;
	}

	function myconfirm(bool){
		
		if(bool=='true'){
			opener.document.getElementsByName("mypw")[0].focus();
			opener.document.getElementsByName("myid")[0].title='y';
		}else{
			opener.document.getElementsByName("myid")[0].focus();
		}
		
		self.close();
	}

</script>
</head>
<body>

<%
	String idnotused = request.getParameter("idnotused");
	//request에 무언갈 담아 보내면 string으로 바뀐다 idnotused boolean값이었지만 String으로 바뀌어서 보내짐
%>

</body>

<table border="1">
	<tr>
		<td><input type="text" name="id"/></td>
	</tr>
	<tr>
		<td><%=idnotused.equals("true")?"아이디 생성 가능":"중복된 아이디 존재" %></td>
	</tr>
	<tr>
		<td><input type="button" value="확인" onclick="myconfirm('<%=idnotused%>');"/></td>
	</tr>
</table>
</html>