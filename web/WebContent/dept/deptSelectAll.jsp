<%@page import="dept.DeptVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>deptSelectAll.jsp</title>
<style>
	.pagination li {
		display : inline-block;
		border : 1px solid gray;
	}
	
	.pagination .active {
		border : 1px solid gray;
		background-color : gray;
	}
</style>
</head>
<body>
<form name="searchFrm">
	<input type="hidden" name="p" value="1">
	<input name="department_name" value="${param.department_name}">
	<button>검색</button>
</form>
<table border="1">
<c:forEach items="${list}" var="dept">
	<%--
	ArrayList<DeptVO> list = (ArrayList<DeptVO>)request.getAttribute("list");
	for (DeptVO dept : list) { 
	--%>
	<tr>
		<td><a href="deptSelect?department_id=${dept.getDepartment_id()}">${dept.getDepartment_id()}</a></td><!-- deptSelect?department_id= (deptSelect:서블릿주소,department_id=:파라미터 값) -->
		<td>${dept.getDepartment_name()}</td>
		<td>${dept.getLocation_id()}</td>
		<td>${dept.getManager_id()}</td>
	</tr>
<%-- } --%>
</c:forEach>
</table>

<my:paging paging="${paging}" jsfunc="gopage"/>

<script>
	function gopage(p) {
		searchFrm.p.value = p;
		searchFrm.submit();
		//location.href="deptSelectAll?p=" + p;

	}
</script>
</body>
</html>