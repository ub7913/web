<%@page import="dept.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptSelectAll.jsp</title>
</head>
<body>
<table border="1">
<% 
	ArrayList<DeptVO> list = (ArrayList<DeptVO>)request.getAttribute("list");
	for (DeptVO dept : list) { 
%>
	<tr>
		<td><a href="deptSelect?department_id=<%=dept.getDepartment_id() %>"><%=dept.getDepartment_id() %></a></td>
		<td><%=dept.getDepartment_name() %></td>
		<td><%=dept.getLocation_id() %></td>
		<td><%=dept.getManager_id() %></td>
	</tr>
<% } %>
</table>
</body>
</html>