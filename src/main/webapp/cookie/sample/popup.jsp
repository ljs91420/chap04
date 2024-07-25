<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>저는 팝업창입니다.</title>
</head>
<body>
	팝업창의 내용입니다.
	
	<div style="position: fixed; right: 10px; bottom: 10px;">
		오늘은 그만 보기 <input id="chk-no-more" type="checkbox" />
	</div>
	
	<script>
		const chkNoMore = document.getElementById("chk-no-more");
		
		chkNoMore.addEventListener("change", (e) => {
			console.log(e.target.checked);
			
			if (e.target.checked) {
				// opener : 팝업창에서 부모 window를 가리키는 객체
				opener.location.href = "/chap04/popup/nomore";
				// 내가 요청 방식을 변경하지 않는 모든 요청은 기본적으로 GET 방식이다.
				
				
				window.close(); // 현재 창 닫기
			}
		});
	</script>
</body>
</html>