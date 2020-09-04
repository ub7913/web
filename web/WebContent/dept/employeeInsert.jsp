<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<style>
	label { display: inline-block; width : 100px}
</style>
</head>
<body>
   <h1>사원등록</h1>
   <form action="empInsert" method="post">  
    <div>
    	<label>employeeId</label> <input name="employeeId">
    </div>
    <div>
    	<label>firstName</label> <input name="firstName">
    </div>
    <div>
    	<label>lastName</label> <input name="lastName">
    </div>
    <div>
    	<label>email</label> <input name="email">
    </div>
    <div>
    	<label>hireDate</label> <input name="hireDate">
    </div>
    <div>
    	<label>department_id</label> 
    	<c:forEach items="${deptList}" var="deptId">
    		<input type="radio" name="deptId" value="${deptId.department_id}">${deptId.department_name}
    	</c:forEach>
    </div>
    <div><label>jobId</label> 
    	  <select name="jobId">
    	  		<option value="">선택
    	  <c:forEach items="${jobList}" var="job">
    	  		<option value="${job.job_id}">${job.job_title}
 		  </c:forEach>
    	  </select>
   	</div>
    <div>
    	<label>manager_id</label> 
    	<select name="manager_id">
    		<option value="">선택
    	<c:forEach items="${empList}" var="manager">
    	  	<option value="${manager.employee_id}">${manager.first_name}  ${manager.last_name}
 		</c:forEach>
    	</select>
    </div>
    <button>등록</button>
    </form>
</body>
</html>