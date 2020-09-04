<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
<c:if test="${empty sessionScope.id}">
	<li><a href="/web/member/login.jsp">로그인</a>
</c:if>
<c:if test="${not empty sessionScope.id}">
	<li>${sessionScope.id}님<a href="/web/member/logout">로그아웃</a>
	<li><a href="/web/member/memberUpdate">정보수정</a>
</c:if>
	<li><a href="/web/dept/deptInsert">부서등록폼</a>
	<li><a href="/web/dept/deptSelectAll">부서전체조회</a>
	<li><a href="<%=application.getContextPath()%>/member/memberInsert.do">회원가입</a><!-- 배포할때 컨택스트 경로가 바뀔수 있으므로 가져오게끔 코딩, 절대경로를 해야 하기 때문에 /가 web앞에 붙어야 함 -->
	<li><a href="member/memberSelectAll.do">회원전체조회</a>
</ul>