<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		<c:if></c:if>
		1. java의 if문을 대체하는 태그이다.
		2. else문이 지원되지 않는다.
		3. 형식
			<c:if test="조건식">
				실행문
			</c:if>
	 --%>
	 
	 <c:set var="age" value="32" scope="page" />
	 
	 <c:if test="${age <= 100}">
	 	<h1>살아있네~~~</h1>
	 </c:if>
	 <c:if test="${age > 100}">
	 	<h1>죽었네ㅠㅠ</h1>
	 </c:if>
	 
	 <c:set var="score" value="100" scope="page" />
	 <c:if test="${score >= 90 and score <= 100}">
	 	<h1>${score}점은 A등급</h1>
	 </c:if>
	 <c:if test="${score >= 80 and score < 90}">
	 	<h1>${score}점은 B등급</h1>
	 </c:if>
	 <c:if test="${score >= 70 and score < 80}">
	 	<h1>${score}점은 C등급</h1>
	 </c:if>
	 <c:if test="${score >= 60 and score < 70}">
	 	<h1>${score}점은 D등급</h1>
	 </c:if>
	 <c:if test="${score >=0 and score < 60}">
	 	<h1>${score}점은 F등급</h1>
	 </c:if>
	 <%--else 가 없는 if 코드 구조로 위와 같은 코드가 됨 --%>

</body>
</html>











