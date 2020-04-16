<%@page import="java.util.List"%>
<%@page import="com.login.dto.MyMemberDto"%>
<%@page import="com.login.biz.MyMemberBizImpl"%>
<%@page import="com.login.biz.MyMemberBiz"%>
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
	String command = request.getParameter("command");
	System.out.println("["+command+"]");
	
	MyMemberBiz biz = new MyMemberBizImpl();
	
	if(command.equals("login")){
		String myid = request.getParameter("id");
		String mypw = request.getParameter("pw");
	
		MyMemberDto dto = biz.login(myid, mypw);
		
		if(dto != null){
			// session : 만료되기 전까지 어플리케이션 전체에서 사용 가능
			session.setAttribute("dto", dto);
			// setMaxInactiveInterval(10*60); 해당 초 만큼 활동이 없으면 session을 invalidate한다.(default :30분 /음수 : 무제한)
			session.setMaxInactiveInterval(10*60);
			
			if(dto.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp"); //객체에 값을 전달해 줄 수 없다.
			}else if(dto.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
			}
			
		}else{
%>
	<script type="text/javascript">
		alert("id와 pw를 다시한번 확인해 주세요!");
		location.href="index.jsp";
	</script>

<%			
		}
	
	}else if(command.equals("logout")){
		// 만료
		session.invalidate();
		response.sendRedirect("index.jsp");
		
	}else if(command.equals("selectlist")){
		//db에서 유저 정보 다 가져와라
		//담아라
		List<MyMemberDto> list = biz.selectList();
		
		request.setAttribute("list", list);
		
		pageContext.forward("userselectlist.jsp"); //보내라
		
	}else if (command.equals("selectenabled")){
		
		List<MyMemberDto> list = biz.selectEnabled();
		
		request.setAttribute("list", list);
		
		pageContext.forward("userselectenabled.jsp");
		// userselectenabled.jsp로 보내라
		
	}else if(command.equals("updateroleform")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MyMemberDto dto = biz.selectUser(myno);
		request.setAttribute("select", dto);
		
		pageContext.forward("updaterole.jsp");
		
		
	}else if(command.equals("updateroleres")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("role");
		
		int res = biz.updateRole(myno, myrole);
		
		
		
		if(res>0){
		%>
		<script type="text/javascript">
			alert("등급이 변경 되었습니다.");
			location.href="logincontroller.jsp?command=selectenabled";
		</script>
		<%	
		}else{
			%>
			<script type="text/javascript">
			alert("등급이 변경 되지 않았습니다.")
			location.href="logincontroller.jsp?command=selectenabled";
			</script>
			<%
		}
				
		
	}else if(command.equals("myinfo")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		MyMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		
		pageContext.forward("userselectone.jsp");
		
	}else if(command.equals("registform")){
		response.sendRedirect("registform.jsp");
		
		
	}else if(command.equals("idchk")){
		String myid = request.getParameter("id");
		
		MyMemberDto dto =biz.idChk(myid);
		
		boolean idnotused = true;
		if(dto != null){ //daoimpl MyMemberDto dto = new MyMemberDto 로그인컨트롤러에서 if(dto.getMyud() != null){
			idnotused = false;
		}
		
		response.sendRedirect("idchk.jsp?idnotused="+idnotused);
		
		
	}else if(command.equals("registres")){
		
		String myid = request.getParameter("myid");
		String mypw = request.getParameter("mypw");
		String myname = request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone = request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		MyMemberDto dto = new MyMemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = biz.insertUser(dto);
		
		if(res>0){
			%>
			<script type="text/javascript">
				alert("가입 되었습니다.")
				location.href = "index.jsp";
			</script>
			<%
			
		}else{
			
			%>
			<script type="text/javascript">
				alert("가입 실패!")
				location.href = "index.jsp";
			</script>
			
			<%
		}
		
	}else if(command.equals("userinfoupdate")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		String mypw = request.getParameter("mypw");
		String myname =request.getParameter("myname");
		String myaddr = request.getParameter("myaddr");
		String myphone=request.getParameter("myphone");
		String myemail = request.getParameter("myemail");
		
		MyMemberDto dto = new MyMemberDto();
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		dto.setMyno(myno);
		
		int res = biz.updateUser(dto);
		
		if(res>0){
			%>
			<script type="text/javascript">
				alert("수정이 완료 되었습니다.")
				location.href="logincontroller.jsp?command=myinfo&myno=<%=myno%>";
			</script>
			
			<%
		}else{
			
		%>
		<script type="text/javascript">
			alert("수정에 실패했습니다.")
			location.href="logincontroller.jsp?command=userinfoupdate&myno=<%=myno%>"
		</script>
		
		<% 			
		}
		
	}else if(command.equals("userupdate")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		
		MyMemberDto dto = biz.selectUser(myno);
		request.setAttribute("dto", dto);
		
		pageContext.forward("userinfoupdate.jsp");
	}

%>

<h1>잘못왔다</h1>

</body>
</html>