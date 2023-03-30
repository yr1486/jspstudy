<%@page import="ex07_jstl.Webtoon"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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
		<c:forEach>
		1. 반복문을 처리하는 태그이다.
		2. 배열이나 리스트의 요소를 처리하는 향상 for문의 역할도 수행한다.
		3. 형식
			1) 일반 for문
				<c:forEach var="변수" begin="시작값" end="종료값" step="증가값">
					반복실행문
				</c:forEach>
			2) 향상 for문
				<c:forEach var="요소" items="배열 또는 리스트">
					반복실행문
				</c:forEach>
	
	 --%>
	 
	 <%-- 1 ~ 5 출력 --%>
	 <c:forEach var="n" begin="1" end="5" step="1">
	 	<span>${n}</span>
	 </c:forEach>
	 
	 <hr>
	 
	 <%-- 5 ~ 1 출럭 --%>
	 <c:forEach var="n" begin="1" end="5" step="1">
	 	<span>${6 - n}</span>
	 </c:forEach>
	 
	 <hr>
	 
	 <%-- 배열 --%>
	 <%
	 	String[] seasons = {"봄", "여름", "가을", "겨울"};
	 	pageContext.setAttribute("seasons", seasons);
	 	
	 %>
	 <%-- EL사용을 위해서 4개중 1개에 저장이 되야함 --%>
	<c:forEach var="season" items="${seasons}" varStatus="vs">
		<div>인덱스 : ${vs.index}, 순번 : ${vs.count} 요소 : ${season}</div>
	</c:forEach>
	 
	 <%-- 리스트 --%>
	 
	 <%
	 	List<String> jobs = Arrays.asList("개발자", "기획자", "PM", "퍼블리셔");
	 	pageContext.setAttribute("jobs", jobs);
	 %>
	 <c:forEach var="job" items="${jobs}" varStatus="k">
	 	<div>인덱스 : ${k.index}, 순번 : ${k.count}, 요소 : ${job}</div>
	 </c:forEach>
	 

	<%-- 객체 + 리스트 --%>
	<%-- 
		객체를 여러개 저장하고 있는 리스트 
		forEach를 이거 때문에 배웠다고 할 수 있음.
	--%>
	
	<%
	List<Webtoon> webtoonList = new ArrayList<>();
	webtoonList.add(new Webtoon(415, "트와일라잇", 9.95, LocalDate.of(2022, 11, 16)));
	webtoonList.add(new Webtoon(416, "내시반청", 9.95, LocalDate.of(2022, 11, 16)));
	webtoonList.add(new Webtoon(417, "보통의 시선", 9.95, LocalDate.of(2022, 11, 16)));
	webtoonList.add(new Webtoon(417, "텐션", 9.95, LocalDate.of(2022, 11, 16)));
	webtoonList.add(new Webtoon(418, "HBLJ", 9.95, LocalDate.of(2022, 11, 16)));
	pageContext.setAttribute("webtoonList", webtoonList);
		
	%>
	
	<table border="1">
		<tbody>
		<c:forEach var="webtoon" items="${webtoonList}">
			<tr>
				<td>${webtoon.webtoonNo}</td>
				<td>${webtoon.title}</td>
				<td>${webtoon.uploadDate}</td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>

	
	
</body>
</html>






























