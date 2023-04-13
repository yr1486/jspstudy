<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%-- 							 ${ } => el쓰고 있는거임. --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 펑션은 위든 아래든 어디에 만들어도 펑션부터 읽어서 아무데나 놔둬 상관없음. 호이스팅이라고 함 -->

<script>
	function fnDetail(exNo){
		location.href = '${contextPath}/detail.do?exNo=' + exNo;
		
	}
	function fnWrite(){
		location.href = '${contextPath}/write.do';
	}
</script>






</head>
<body>
<!-- exListService에서 나온거임 -->
<!-- exDTO가 하나씩하나씩나올거라고. -->
<!-- 온클릭의 fnDetail()는 호출이라서 값을 넣는게 가능하다. 1,2,3 게시글 이렇게 보려고 // 이제 function fnDetail(${ex.exNo}) -->
	
	<c:forEach items="${exList}" var="ex">
		<div style="border: 1px solid gray; cursor: pointer;" onclick="fnDetail(${ex.exNo})">
		<div>${ex.exNo}</div>
		<div>${ex.exContent}</div>
		<div>${ex.exCreatedAt}</div>
		</div>
	
	
	</c:forEach>
	
	<input type="button" value="작성하러가기" onclick="fnWrite()">
	
	
	
	
	
	
	
	
</body>
</html>



















