<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardInsert.jsp</title>
<script>
function inputCheck() {
	//poster, subject 필수입력 체크
	if(frm.poster.value == "") {
		window.alert("작성자를 입력하세요");
		frm.poster.focus();
		return false;
	}
	if(frm.subject.value == "") {
		alert("제목을 입력하세요");
		frm.subject.focus();
		return false;
	}
}
</script>
</head>
<body>
<h3>게시판 등록</h3>
<div class="regist">
	<form method="post" name="frm" id="frm"
		  action="boardInsert.do"
		  enctype="multipart/form-data"
		  onsubmit="return inputCheck()">
		<div>
			<label>번호</label>
			<input type="text" name="no">
		</div>
		<div>
			<label>작성자</label>
			<input type="text" name="poster">
		</div>
		<div>
			<label>제목</label>
			<input type="text" name="subject">
		</div>
		<div>
			<label for="contents">내용</label>
			<textarea id="contents" name="contents"></textarea>
		</div>
		<div>
			<label>파일첨부</label>
			<input type="file" name="filename" size="30"></input>
		</div>
		<div>
			<button type="reset" name="reset">초기화</button>
			<button>등록</button>
			<!-- <button type="button" name="submit" onclick="inputCheck()">등록</button> -->
		</div>
	</form>	  
</div>
</body>
</html>