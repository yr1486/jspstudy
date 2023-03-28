<%@page import="java.util.Optional"%>
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
		//자바코드
		request.setCharacterEncoding("UTF-8");
		String model = request.getParameter("model");
		Optional<String> opt = Optional.ofNullable(request.getParameter("price"));
		// 이게 널이아닐수도 있고, 널일수도 있고
		// price처리할때는 항상 전달안되면 0으로 처리해달라.단, 널은 잡지만, 빈문자열은 못잡음
		int price = Integer.parseInt(opt.orElse("0"));
	%>
	
	<h1>모델 : <%=model%></h1>
	<h1>가격 : <%=price%></h1>
	
</body>
</html>