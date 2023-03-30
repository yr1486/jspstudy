<%@page import="ex06_el.Book"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	 <%--
	 	JSP binding 우선 순위
	 	1. 같은 이름의 속성이 서로 다른 영역에 저장될 수 있다.
	 	2. 같은 이름이 들어가있으면 누가 호출될 것인가 
	 	=>	접근 범위(Scope)가 좁을 수록 높은 우선 순위를 가진다.
	 		pageContext > request > session > application
	 	3. 각 영역을 지정하는 표현언어(EL)내장 객체가 있다.
	 				영역			내장 객체
	 		1) pageContext  : pageScope
	 		2) request 		: requestScope
	 		3) session		: sessionScope
	 		4) application	: applicationScope
	 	4. Jsp binding 영역에 저장된 값은 모두 EL로 표현할 수 있다.
	  --%>
	  
	  <%-- 안되는 경우 
	  <%int a =1; %>
	  ${a}
	   --%>
	   
	 <%
	 	pageContext.setAttribute("a", 1);
	 	request.setAttribute("a", 10);
	 	session.setAttribute("a", 100);
	 	application.setAttribute("a", 1000);
	 %>
	 
	 <%-- 우선순위 확인 --%>
	  <h1>${a}</h1>

	 <%-- 모든 영역의 속성 a 확인 --%>
	 <h3>${pageScope.a}</h3>
	 <h3>${requestScope.a}</h3>
	 <h3>${sessionScope.a}</h3>
	 <h3>${applicationScope.a}</h3>
	 
	 <%--
	 	EL 연산자
	 	1. 산술 : + - * /(div) %(mod)     
	 	(기본적으로 자바 연산자를 쓰는데, el전용 연산자가 몇개 있음.)
	 	2. 관계 : <(lt) <=(le) >(gt) >=(ge) ==(eq) !=(ne)
	 	3. 논리 : &&(and) ||(or) !(not)
	 	4. 조건 : (조건식) ? 참 : 거짓
	 		EL연산자 기억안나면 자바연산자 쓰면됨. ()가 EL연산자코드임
	 		
	  --%>
	  
	  <% 
	  // EL 사용을 위해서 pageContext에 저장한다.
	  pageContext.setAttribute("x", 5); 
	  pageContext.setAttribute("y", 2);
	  
	  %>
	  
	  <ul>
	  	<li>${x + y}</li>
	  	<li>${x - y}</li>
	  	<li>${x * y}</li>
	  	<li>${x div y}</li>
	  	<li>${x mod y}</li>
	  </ul>
	  
	  <ul>
	  	<li>${x lt y}</li>
	  	<li>${x le y}</li>
	  	<li>${x gt y}</li>
	  	<li>${x ge y}</li>
	  	<li>${x eq y}</li>
	  	<li>${x ne y}</li>
	  </ul>
	  
	  <ul>
	  	<li>${x eq 5 and y eq 2}</li>
	  	<li>${x eq 6 or y eq 2}</li>
	  	<li>${not (x eq 5)}</li>
	  	<%-- 괄호 없을 경우 오류 남 --%>
	  </ul>
	  

	<%-- Map 사용하기 --%>
	<%
		Map<String, Object> book = new HashMap<>();
		book.put("author", "생택쥐베리");
		book.put("title", "어린왕자");
		book.put("price", 10000);
		pageContext.setAttribute("book", book);
		
	%>
	
	<ul>
		<li>저자: ${book.author}</li>
		<li>제목: ${book.title}</li>
		<li>가격: ${book.price}</li>
	</ul>
	
	<%-- 객체 사용하기 --%>
	
	<%
		Book book2 = new Book();
		book2.setAuthor("황순원");
		book2.setTitle("소나기");
		book2.setPrice(10000);
		pageContext.setAttribute("book2", book2);
	%>
	
	<ul>
		<li>저자 : ${book2.author}</li> <%--자동으로 ${book2.getAuthor()} 호출되어 실행된다. --%>
		<li>제목 : ${book2.title}</li>  <%--자동으로 ${book2.getTitle()} 호출되어 실행된다. --%>
		<li>가격 : ${book2.price}</li>  <%--자동으로 ${book2.getPrice()} 호출되어 실행된다. --%>
	</ul>
	  <%-- 개발환경에서 맵과 객체는 동일하다 --%>
	  
	  
	  
	  
	  
	  
</body>
</html>























