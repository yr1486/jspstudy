<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:set var="str" value="Hello World" scope="page" />
	
	<h3>${fn:length(str)}</h3>	
	<%-- 펑션은 태그가 아님 --%>
	
	<h3>${fn:substring(str, 0, 5)}</h3>
	<%-- 						5 이전 까지 --%>
	
	<h3>${fn:substringBefore(str, ' ')}</h3>
	<%--                          W 이전의 텍스트 --%>
	
	<h3>${fn:substringAfter(str, ' ')}</h3>
	
	<h3>${fn:indexOf(str, 'l')}</h3>
	<%-- lastIndexOf는 없으니, 자바에서 구해 오기. --%>
	
	<h3>${fn:replace(str, 'l', 'z')}</h3>
	<%-- replaceAll 할 필요 없이 모든 'l'을 바꿈 (자바처럼) 자바스크립트의 replace는 하나만 바꿈--%>
	
	<hr>
	
	<%-- 보안 관련 --%>
	<c:set var="str2" value="<script>location.href='/';</script>" />
	<h3>${fn:escapeXml(str2)}</h3>
	<%--&lt;h3&gt;HelloWorld&lt;/h3&gt; --%>
	
	<c:if test="${fn:startsWith(str, 'Hello')}">
		<h3>Hello로 시작한다.</h3>
	</c:if>
	<c:if test="${fn:endsWith(str, 'World')}">
		<h3>World로 끝난다.</h3>
	</c:if>
	<c:if test="${fn:contains(str, 'h')}">
		<h3>h를 포함한다.</h3>
	</c:if>
	<c:if test="${fn:containsIgnoreCase(str, 'h')}">
		<h3>H, h를 포함한다. 대소문자를 무시한다.</h3>
	</c:if>
	
	<c:set var="str3" value="a,b,c,d,e,f,g,h,i,j" scope="page" />
	<c:set var="items" value="${fn:split(str3, ',')}" scope="page" />
	<%-- ,로 분리해서 배열로 만들어 달라 --%>
	<c:forEach var="item" items="${items}" varStatus="vs">
		<div>${vs.index} - ${item}</div>
	</c:forEach>
	
	<c:set var="str4" value="${fn:join(items, ',')}" scope="page" />
	<h3>${str4}</h3>
	
	
	
	
</body>
</html>


























