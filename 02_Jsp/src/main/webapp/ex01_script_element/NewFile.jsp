<%--
	지시어 (directive)
	1. page 지시어 : page 설정, import 등
	2. include 지시어 : 다른 JSP를 포함
	3. taglib 지시어 : JSTL/EL 포함
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- JSP의 주석 : HTML 주석과 달리 "페이지 소스 보기"에서 보이지 않는다. --%>

<%-- 스크립트릿 : 일반 자바 코드 --%>

	<%
		int a = 1;
	%>
	
	<h1> a = <%=a %></h1>
	
	<%!
		public int getNumber(){
			return (int)(Math.random() * 10);
	}
	%>
	<h1>발생한 난수 : <%=getNumber()%></h1>
	
	<hr>
	
	<%--
	
	Java와 JavaScript의 사용
	1. Java 변수를 Javascript에서 사용할 수 있다 --%>
	
	<% String str = "Hello World"; %>
	<script>
		alert('<%=str%>');
	</script>
	
	<% if(getNumber() >= 5) { %>
		<h1>큰 수</h1>
	<% } else { %>
		<h1>작은 수</h1>
	<% } %>
	
	<select name="month">
		<% for(int m = 1; m <= 12; m++) { %>
			<option value="<%=m%>"><%=m%>월</option>
		<% } %>
	</select>
</body>
</html>




























