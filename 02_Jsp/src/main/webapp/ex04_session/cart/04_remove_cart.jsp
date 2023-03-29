<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// session에 저장된 cart 속성 지우기
		// 골라서 지우기
		session.removeAttribute("cart");
		// 다시 쇼핑하러 가기
		response.sendRedirect("03_cart_list.jsp"); // 장바구니가 비어있는지 목록 확인하러 가야하니까
	%>
	
	
	
</body>
</html>