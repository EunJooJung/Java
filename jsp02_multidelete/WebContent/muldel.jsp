<%@page import="com.md.dao.MdDao"%>
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
	//muldel.jsp?chk=1&chk=2&chk=5 
	//같은 이름의 여러 값을 받을 때 사용 request.getParameterValues("");
	String[] seq = request.getParameterValues("chk");
	
	if(seq==null || seq.length==0){

%>
	<script type="text/javascript">
		alert("삭제할 글을 1개 이상 선택해 주세요");
		location.href="./boardlist.jsp"
	
	</script>
	
<%
	} else{
		MdDao dao = new MdDao();
		int res = dao.multiDelete(seq);
		if(res>0){

%>	

<script type="text/javascript">
	alert("선택한 글들을 삭제 성공하였습다.");
	location.href="boardlist.jsp";

</script>

<%
		}else{
%>

<script type="text/javascript">
	alert("선택한 글들을 삭제 실패하였습니다.");
	location.href="boardlist.jsp";

</script>

<%
		}
	}
%>

</body>
</html>