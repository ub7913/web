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
<h3>ȸ�����</h3>
<div class="regist">
	<form method="post" name="frm" id="frm" 
		  action="memberInsert.do" 
		  onsubmit="return inputCheck()">
	<div>
		<label>ID </label>
		<input type="textfiled" name="id">
	</div>
	<div>
		<label>PW </label>
		<input type="password" name="pw">
	</div>
	<div>
		<label>����</label>
		<input type="radio" name="gender" value="male" checked>��</input>
		<input type="radio" name="gender" value="female">��</input>
	</div>
	<div>
		<label>����</label>
		<select name="job" id="job" length="4">
			<option value="">����</option>
			<option value="professor">����</option>
			<option value="doctor">�ǻ�</option>
			<option value="salesperson">�Ǹſ�</option>
			<option value="student">�л�</option>
		</select>	
	</div>
	<div>
		<label for="reason">���Ե���</label>
		<textarea id="reason" name="reason"></textarea>
	</div>
	<div>
		<label>���ϼ��ſ���</label>
		<input type="checkbox" name="mailyn" value="y"></input>
	</div>
	<div>
		<label for="hobby">���</label>
		<input type="checkbox" name="hobby" value="read">����
		<input type="checkbox" name="hobby" value="game">����
		<input type="checkbox" name="hobby" value="ski">��Ű
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