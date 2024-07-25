<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키 목록 보기</title>
</head>
<body>
	<p>값 수정 기능 구현해보기(수정시 input에 공백이 포함된 채로는 요청을 보내지 않도록 만들어보기)</p>
	
	
	<ul>
		<% for (Cookie cookie : (Cookie[])(request.getAttribute("cookies"))) { %>
			<li>
				<% String cookieName = cookie.getName(); %>
				<%=cookieName %>=<%=cookie.getValue() %> 
				<form class="modi_form" action="./modify" method="GET">
					<input type="hidden" name="to_modi" value="<%=cookieName %>" />
					[<input id="modi_value" type="text" name="modi_value" /> <button id="modi_btn">값 수정</button>]
				</form>
				[<a href="./delete?cookie-name=<%=cookieName %>">삭제</a>]
			</li>
		<% } %>
	</ul>
	
	<a href="../cookie_index.jsp">쿠키 인덱스로</a>
	
	<script src="/chap04/resources/js/modi_cookie.js"></script>
</body>
</html>