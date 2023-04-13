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

	function fnRemove(){
		if(confirm('삭제하시겠습니까?')){
			location.href="${contextPath}/remove.do?exNo=${ex.exNo}";
		}
	}
</script>




</head>
<body>

<!-- 디테일서비스에서 셋에트리뷰트로 넣은 "ex"이름과 아래 ex와 같게 맞춰주는거임. 셋에서 뭐라고 지어도 여기서 뭐라고로 적으면 되는거임 -->
		<div>${ex.exNo}</div>
		<div>${ex.exContent}</div>
		<div>${ex.exCreatedAt}</div>
		<div><input type="button" value="삭제" onclick="fnRemove()"></div>
	
	
	
	
	
	
	
	
	
</body>
</html>



















