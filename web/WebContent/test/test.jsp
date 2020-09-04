<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>내장객체</h3><!-- //서블릿과 다르게 객체 생성없이 바로 사용 가능 -->
<%= session.getCreationTime() %>
<%= application.getMajorVersion() %>
<% out.print("test"); %>
</body>
</html>