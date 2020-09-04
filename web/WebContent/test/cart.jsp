<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cart.jsp</title>
</head>
<body>
<h3>장바구니 조회</h3>
<table>
<c:forEach items="${cartList}" var="cart">
	<tr>
		<td>${cart}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>