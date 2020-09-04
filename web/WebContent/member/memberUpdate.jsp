
<%@page import="member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script>
function inputCheck() {
	//id, pw 필수입력 체크
	if(frm.id.value == "") {
		window.alert("id 입력");
		frm.id.focus();
		return false;
	}
	if(frm.pw.value == "") {
		alert("pw 입력");
		frm.pw.focus();
		return false;
	}
	//job(select 태그) 선택되었는지 확인
	if(frm.job.value == "") {
	//if(frm.job.selectedIndex > 0) {}
		alert("job선택");
		frm.job.focus();
		return false;
	}
	//radio, checkbox
	var gender =
		document.querySelectorAll("[name='gender']:checked").length;
	if(gender == 0) {
		alert("성별 적어도 하나는 선택");
		return false;
	}
	//회원가입폼 제출
	//frm.submit();
	return true;
}
</script>
</head>
<body>
<% MemberVO member = (MemberVO) session.getAttribute("login"); %>
<h3>회원등록</h3>
<div class="regist">
	<form method="post" name="frm" id="frm" 
		  action="memberUpdate" 
		  onsubmit="return inputCheck()">
	<div>
		<label>ID </label>
		<input type="textfiled" name="id" value="<%=member.getId()%>">
	</div>
	<div>
		<label>PW </label>
		<input type="password" name="pw" value="<%=member.getPw()%>">
	</div>
	<div>
		<label>성별</label><!-- 수정을 하려고 하는데 자동으로 체크 되어 있게 하려면 db의 정보를 받아와서 비교후에 설정을 준다. -->
		<input type="radio" name="gender" value="male" 
			<%if("male".equals(member.getGender())) { out.print("checked='checked'"); }%>>남</input>
		<input type="radio" name="gender" value="female"
			<%if("female".equals(member.getGender())) { out.print("checked='checked'"); } %>>여</input>
	</div>
	<div>
		<label for="job">직업</label>
		<select name="job" id="job" length="4">
			<option value="">선택</option>
			<option value="professor" 
				<%if("professor".equals(member.getJob())) { out.print("selected='selected'"); }%>>교수</option>
			<option value="doctor" 
				<%if("doctor".equals(member.getJob())) { out.print("selected='selected'"); }%>>의사</option>
			<option value="salesperson"
				<%if("salesperson".equals(member.getJob())) { out.print("selected='selected'"); }%>>판매원</option>
			<option value="student"
				<%if("student".equals(member.getJob())) { out.print("selected='selected'"); }%>>학생</option>
		</select>	
	</div>
	<div>
		<label for="reason">가입동기</label>
		<textarea id="reason" name="reason"><%=member.getReason()%></textarea>
	</div>
	<div>
		<label>메일수신여부</label>
		<input type="checkbox" name="mailyn" value="y"></input>
	</div>
	<div>
		<label for="hobby">취미</label>
		<input type="checkbox" name="hobby" value="read"
			<%if((member.getHobby().contains("read"))) { out.print("checked='checked'"); }%>>독서
		<input type="checkbox" name="hobby" value="game"
			<%if((member.getHobby().contains("game"))) { out.print("checked='checked'"); }%>>게임
		<input type="checkbox" name="hobby" value="ski"
			<%if((member.getHobby().contains("ski"))) { out.print("checked='checked'"); }%>>스키
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