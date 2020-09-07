<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>empSelectAll.jsp</title>
</head>
<body>
<h3>사원 리스트</h3>
<table border="1">
<c:forEach items="${empAllList}" var="emp">
	<tr>
		<td>${emp.getEmployee_id()}</td>
		<td>${emp.getFirst_name()}</td>
		<td>${emp.getLast_name()}</td>
		<td>${emp.getEmail()}</td>
		<td>${emp.getHire_date()}</td>
		<td>${emp.getDepartment_id()}</td>
		<td>${emp.getJob_id()}</td>
		<td>${emp.getManager_id()}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>