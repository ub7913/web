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
		<span class="label">���̵�</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">�н�����</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">����</span>
		<span>����</span>
	</div>
	<div>
		<span class="label">����</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">���Ե���</span>
		<span>hoil</span>
	</div>
	<div>
		<span class="label">���ϼ��ſ���</span>
		<span>hoil</span>
	</div>
	<button type="button" id="btnPage">�������</button>
<script>
	btnPage.addEventListener("click", goPage);
	function goPage() {
		//history.back();
		//history.go(-1); //������������ �̵�
		//location.href="memberAll.jsp"
		location.assign("memberAll.jsp");
	}
</script>
</body>
</html>