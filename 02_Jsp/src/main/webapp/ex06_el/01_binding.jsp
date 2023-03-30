.<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		Jsp의 binding (속성을 저장할 수 있는 영역이다.)
		1. pageContext  : this, 현재 Jsp 페이지에서 접근할 수 있다.	(보통 this는 객체를 가리키는데, 얘는 jsp파일(=01_binding.jsp를 가리킴. 보이진않지만 이파일의 클래스가 생기고 어찌고가 되는, 
	 	2. request		: HttpServletRequest, 하나의 요청에서 접근할 수 있다.
	 	3. session		: HttpSession, 브라우저 종료까지 접근할 수 있다. 기본 세팅 30분.
	 	4. application	: ServletContext, 애플리케이션 종료까지 접근할 수 있다
	 
	 
	 --%>

	 
	 <%
	 	pageContext.setAttribute("a", 1);
	 	request.setAttribute("a", 10);
	 	session.setAttribute("a", 100);
	 	application.setAttribute("a", 1000);
	 %>
	 
	 <%-- 표현식 --%>
	 <%-- 이제 표현식을 표현언어로 바꿔볼거임(el) --%>
	 <h1><%=pageContext.getAttribute("a")%></h1>
	 <h1><%=request.getAttribute("a")%></h1>
	 <h1><%=session.getAttribute("a")%></h1>
	 <h1><%=application.getAttribute("a")%></h1>
	 
	 
	 
	 
</body>
</html>




















