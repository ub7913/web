<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<%=request.getAttribute("errormsg")%>
	
	<form method="post" name="frm" id="frm" 
		  action="login">
		<div>
			<label>ID </label>
			<input type="text" name="id">
		</div>
		<div>
			<label>PW </label>
			<input type="password" name="pw">
		</div>
		<button>로그인</button>
	</form>
</body>
</html>