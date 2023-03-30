<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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

	<%
		request.setCharacterEncoding("UTF-8");
	
		String item = request.getParameter("item");
		int itemCount = Integer.parseInt(request.getParameter("item_count"));
		// 제품명 + 구매 수량을 하나의 Map으로 저장한다.
		Map<String, Object> map = new HashMap<>();
		map.put("item", item);
		map.put("itemCount", itemCount);
		
		
		// session에 저장된 cart 속성이 있는지 확인한 뒤 없다면 새로운 cart를 만들어서 session에 저장한다.
		List<Map<String, Object>> cart = (List<Map<String, Object>>)session.getAttribute("cart"); // 캐스팅 후 꺼내자.
		// 캐스팅할때 경고메세지 뜨는거 무시하믄 됨.
		if(cart == null) { // 없으면.
			cart = new ArrayList<>();
			session.setAttribute("cart", cart); //세션에 저장하기
			// 주소값을 공유한다고 생각하기.
		}
		
		// session의 cart에 Map 저장하기
		cart.add(map);

	%>
	<script>
		if(confirm('${param}을 장바구니에 추가했습니다. \n 장바구니를 확인하려면 "확인", 계속 쇼핑하려면 취소 "버튼"을 누르세요')){
			location.href = '03_cart_list.jsp';
			//alert말고 컨펌창은 확인 취소 누를수 있는 창임
		}
		else {
			location.href = '01_form.jsp';
		}
		
	
	</script>
	
	
	<%-- 
		// 참고

		class Product {
			String item;
			int itemCount;
			Product(String item, int itemCount){
				this.item = item;
				this.itemCount =  itemCount;
			}
		}
		Product product = new Product(item, itemCount);

		 자바스크립트에서는 객체, 자바에서는 맵 
			자바스크립트 객체를 json이라고 봐도 됨.
			
		--%>

</body>
</html>