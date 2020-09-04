<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>memberSelect</title>
</head>
<body>
	<div>
		<span class="label">아이디</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">패스워드</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">직업</span>
		<span>교수</span>
	</div>
	<div>
		<span class="label">성별</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">가입동기</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">메일수신여부</span>
		<span>hoil</span>
	</div>
	<button type="button" id="btnPage">목록으로</button>
<script>
	btnPage.addEventListener("click", goPage);
	function goPage() {
		//history.back();
		//history.go(-1); //이전페이지로 이동
		//location.href="memberAll.jsp"
		location.assign("memberAll.jsp");
	}
</script>
</body>
</html>