
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
	//id, pw �ʼ��Է� üũ
	if(frm.id.value == "") {
		window.alert("id �Է�");
		frm.id.focus();
		return false;
	}
	if(frm.pw.value == "") {
		alert("pw �Է�");
		frm.pw.focus();
		return false;
	}
	//job(select �±�) ���õǾ����� Ȯ��
	if(frm.job.value == "") {
	//if(frm.job.selectedIndex > 0) {}
		alert("job����");
		frm.job.focus();
		return false;
	}
	//radio, checkbox
	var gender =
		document.querySelectorAll("[name='gender']:checked").length;
	if(gender == 0) {
		alert("���� ��� �ϳ��� ����");
		return false;
	}
	//ȸ�������� ����
	//frm.submit();
	return true;
}
</script>
</head>
<body>
<% MemberVO member = (MemberVO) session.getAttribute("login"); %>
<h3>ȸ�����</h3>
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
		<label>����</label><!-- ������ �Ϸ��� �ϴµ� �ڵ����� üũ �Ǿ� �ְ� �Ϸ��� db�� ������ �޾ƿͼ� ���Ŀ� ������ �ش�. -->
		<input type="radio" name="gender" value="male" 
			<%if("male".equals(member.getGender())) { out.print("checked='checked'"); }%>>��</input>
		<input type="radio" name="gender" value="female"
			<%if("female".equals(member.getGender())) { out.print("checked='checked'"); } %>>��</input>
	</div>
	<div>
		<label for="job">����</label>
		<select name="job" id="job" length="4">
			<option value="">����</option>
			<option value="professor" 
				<%if("professor".equals(member.getJob())) { out.print("selected='selected'"); }%>>����</option>
			<option value="doctor" 
				<%if("doctor".equals(member.getJob())) { out.print("selected='selected'"); }%>>�ǻ�</option>
			<option value="salesperson"
				<%if("salesperson".equals(member.getJob())) { out.print("selected='selected'"); }%>>�Ǹſ�</option>
			<option value="student"
				<%if("student".equals(member.getJob())) { out.print("selected='selected'"); }%>>�л�</option>
		</select>	
	</div>
	<div>
		<label for="reason">���Ե���</label>
		<textarea id="reason" name="reason"><%=member.getReason()%></textarea>
	</div>
	<div>
		<label>���ϼ��ſ���</label>
		<input type="checkbox" name="mailyn" value="y"></input>
	</div>
	<div>
		<label for="hobby">���</label>
		<input type="checkbox" name="hobby" value="read"
			<%if((member.getHobby().contains("read"))) { out.print("checked='checked'"); }%>>����
		<input type="checkbox" name="hobby" value="game"
			<%if((member.getHobby().contains("game"))) { out.print("checked='checked'"); }%>>����
		<input type="checkbox" name="hobby" value="ski"
			<%if((member.getHobby().contains("ski"))) { out.print("checked='checked'"); }%>>��Ű
	</div>
	<div>
		<button type="reset" name="reset">�ʱ�ȭ</button>
		<button>���</button>
		<!-- <button type="button" name="submit" onclick="inputCheck()">���</button> -->
	</div>
</form>
</div>
</body>
</html>