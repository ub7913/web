<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<title>boardClient.jsp</title>
<script >
$(function() {
	//목록조회
	function boardList(){
		$.ajax("../BoardSelectAllAjaxServ" , {
			dataType : "json",
			success : function(datas) { //BoardSelectAllAjaxServ 에서 받아온 전체결과가 변수 datas에 들어 온다, 변수는 아무거나 상관 없음
				for(i=0; i<datas.length; i++) {
					$("<div>").append(datas[i].no)
							.append(datas[i].poster)
							.append(datas[i].subject)
							.data("no", datas[i].no)
							.append($("<button>").html("삭제").addClass("btnDel"))
							.appendTo($("#list")); //리스트안에 값들을 집어 넣는다는 의미
				}
			}
		})
	}	
	
	//삭제버튼
	$("#list").on("click", ".btnDel", function() {
		no = $(this).parent().data("no");
		div = $(this).parent();
		$.ajax("../BoardDeleteAjaxServ", { //데이터를 보내야 하는 서버
			//method : "post" //디폴트는 get이다
			dataType : "json", //서버에서 넘오는 데이터타입을 의미, 
			data : {no : no}, //서버에서오는 no임, "BoardDeleteAjaxServ(서버)?no="+no와 같은 의미, 서버에서 json방식으로 데이터를 보내서 {} 방식으로 쓴것 
			success : function(data) { 
				alert(data.no + " 삭제완료");
				div.remove();
			}
		});
	});
	
	//저장버튼
	$("#btnSave").on("click", function() {
		$.ajax("../BoardInsertAjaxServ", {
			dataType : "json",
			data : $("form").serialize(),
			success : function(data) { //서블릿(서버)에서 성공적으로 네트웍 되면 여기로 넘어 온다, 그 값들을 function안의 변수가 받는다 
				$("<div>").append(data.no)
						  .append(data.poster)
						  .append(data.subject)
						  .data("no", data.no)
						  .append($("<button>").html("삭제").addClass("btnDel"))
						  .appendTo($("list"))
			}
		})
	});
	
	boardList();
		
});
</script>
</head>
<body>
<div data-id="4" data-goods="book" id="divid">데이터 연습</div>
<!-- 목록 -->
<div id="list">
<form>
	<input type="text" name="poster" placeholder="작성자"/>
	<input type="text" name="subject"placeholder="제목"/>
	<textarea rows="4" cols="50" name="contents" placeholder="내용"></textarea>
	<button type="button" id="btnSave">등록</button>
</form>
</div>
</body>
</html>