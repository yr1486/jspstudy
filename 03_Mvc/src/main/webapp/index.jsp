<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	
	<h3><a href="${contextPath}/today.do">오늘은 며칠입니까?</a></h3>

	<form action="${contextPath}/age.do">
		<select name="birthyear">
			<c:forEach var="y" begin="1923" end="2023" step="1">
			<option value="${y}">${y}년</option>
			</c:forEach>
		</select>
		<button>몇 살입니까?</button>
	</form>
	
	<div>
		<input type="text" id="height" placeholder="키(cm)를 입력하세요">
		<input type="text" id="weight" placeholder="몸무게(kg)를 입력하세요">
		<input type="button" id="btn_bmi" value="BMI 계산하기">
	</div>
	<script>
		$('#btn_bmi').on('click', function(event){
			let heightValue = ${'#height'}.val();
			let weightValue = ${'weight'}.val();
			heightValue = heightValue || '0'; // heightValue가 빈 문자열('')이면 '0'	을 사용하시오.
			weightValue = weightValue || '0'; // weightValue가 빈 문자열('')이면 '0'을 사용하시오.
			location.href = '${contextPath}/bmi.do?height=' + heightValue + '&weight=' + weightValue;
		})
	</script>
	
	
	
	
	
	
	
	<%-- 
	
		db 셀렉트의 결과를 보여주는 페이지로 넘어갈때... 오늘배운것처럼 넘어간다,,(포워드써서)
		리다이렉트는 셀렉트 이외의 모든 작업에서 (ex) 인설트딜리트업데이트같은거
		
		((로케이션 혹은 에이링크도 리다이렉트다. 포워드만 아니면 다 리다이렉트다.))
		
		이렇게 분리하는 이유
		서블릿을 하나만 쓰기 위해서. 하나의 서블릿이 모든 요청을 다받는거지
		그리고 얘가 다골라서 뿌린다.
		..컨트롤러를 하나로 모을거야. 모으는게 어렵다.
	
	 --%>
	
</body>
</html>














