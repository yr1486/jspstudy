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
	파라미터 확인을 위한 EL객체가 따로 있다.
	1. param
	2. paramValues

 --%>
 
 <h1>${param.a}</h1>
 <h1>${paramValues.b[0]}</h1>
 <h1>${paramValues.b[1]}</h1>
 <h1>${paramValues.b[2]}</h1>
 
 <%-- 파라미터 x, y의 크기비교(파라미터는 String이기 떄문에 사전 편찬순으로 크기를 비교한다. --%>
 <ul>
 	<li>${param.x lt param.y}</li>
 	<li>${param.x le param.y}</li>
 	<li>${param.x gt param.y}</li>
 	<li>${param.x ge param.y}</li>
 </ul>
 
 
 
 <%-- 파라미터 x, y의 크기 비교 해결(EL에서 "10" - "5" == 5이다. 자바에서는문자열빼기여서 오류날텐데 EL에서는 오류 안나고 자동으로 숫자로 인식하고 계산해준다) --%>
  <ul>
 	<li>${param.x - param.y lt 0}</li>
 	<li>${param.x - param.y le 0}</li>
 	<li>${param.x - param.y gt 0}</li>
 	<li>${param.x - param.y ge 0}</li>
 </ul>
 
 
</body>
</html>



























