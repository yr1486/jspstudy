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
		// session에 저장된 '모든' 정보 지우기(초기화하기)
		session.invalidate(); // 암기!! 세션 초기화 코드
		
		// 다시 로그인 폼으로 돌아가기
		response.sendRedirect(request.getContextPath() + "/ex07_jstl/practice01_login/01_form.jsp");
			
	%>
</body>
</html>