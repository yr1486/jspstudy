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
	// session의 모든 속성 지우기
	session.invalidate();

// 아래 쿠키 삭제 2개 안해주면. 로그아웃을해도 로그아웃이 안먹힘.
// 그리고 아래 쿠키 삭제 2개가 잘 먹혀 있는지 보려면 실행 시킨 후
// 자동로그인 체크한다음에 로그인하기. 그다음에 현재 브라우저주소 복사한후 현재열려있는 크롬창 다 닫고 새로 켰을떄 자동로그인이 되어 있어야 함
// 반대로 자동 로그인 해제하고 똑같이하면 자동로그인이 풀려 있어야 함.
// 요새는 자동로그인 잘 안함.......................................


	// session_id 쿠키를 삭제한다.
	Cookie cookie1 = new Cookie("session_id", "");
	cookie1.setMaxAge(0);
	cookie1.setPath(request.getContextPath());
	response.addCookie(cookie1);
	
	// login_id 쿠키를 삭제한다.
	Cookie cookie2 = new Cookie("login_id", "");
	cookie2.setMaxAge(0);
	cookie2.setPath(request.getContextPath());
	response.addCookie(cookie2);

	// 다시 로그인 화면으로 이동하기
	response.sendRedirect("01_form.jsp");

%>

</body>
</html>