<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 세션에서 내려받아서 보여줄 수 있는 코드 --%>
	
	<%
		// session에 저장된 cart 속성 가져오기.
		List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart");
	
	%>
	
	<%
		if(cart == null) { %>
			<div>장바구니가 비었습니다.</div>
	<% } else { %>
		<div>장바구니 목록</div>
		<% for(int i = 0; i < cart.size(); i++){ %>
			<div><%=cart.get(i).get("item")%> <%=cart.get(i).get("itemCount")%>개</div> 
		<% } %>
	<% } %>
	
	<div>
		<input type="button" value="계속쇼핑하기" onclick="goShopping()">
		<input type="button" value="장바구니비우기" onclick="removeCart()">
	</div>
	<script>
		function goShopping(){
			location.href = '01_form.jsp';
		}
		function removeCart(){
			if(confirm('장바구니를 비울까요?')){
				location.href = '04_remove_cart.jsp';
			}
			else {
				alert('취소되었습니다.');
			}
		}
	</script>
	
</body>
</html>










