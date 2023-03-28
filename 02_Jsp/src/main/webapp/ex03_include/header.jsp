<%@page import="java.util.Optional"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%
	request.setCharacterEncoding("UTF-8");
	Optional<String> opt = Optional.ofNullable(request.getParameter("title"));
	String title = opt.orElse("환영합니다.");
%>
<title><%=title%></title>
<%-- request.getContextPath() ==/02_Jsp --%>
<%-- 컨텍스트패스는 주소. 인터넷 주소임. 02라는 주소를 찍으면  webapp을(경로를) 참고한다는 뜻. --%>
<%-- <!-- 이 주석 사용 하믄 안됌 jsp 주석사용하기 --%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/header.css">

<%-- href 경로 찾아서 포함 시키기:contextpath가 webapp임!!!!!! 중요 --%>
<%-- 경로에 자바변수가 들어왔음 --%>

<script src="<%=request.getContextPath()%>/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	<nav>
		<ul>
			<li><a href="body1.jsp">body1</a></li>
			<li><a href="body2.jsp">body2</a></li>
			<li><a href="body3.jsp">body3</a></li>
		</ul>
	</nav>

	<hr>
