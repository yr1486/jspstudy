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
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 로그인 시나리오
		// id=admin , pw=1234인 경우에 로그인 성공
		if(id.equals("admin") && pw.equals("1234")){
			
			// 로그인 한 회원 정보를 session에 보관하기
			session.setAttribute("loginId", id); // 아이디저장
			session.setMaxInactiveInterval(60*10); //세선유효시간: 600초 (10분) // 인액티브 세션에서 없어진다.ex)은행로그인 10분동안만 유지할 때
		}
		
		// 다시 로그인 폼으로 돌아가기
		response.sendRedirect(request.getContextPath() + "/ex04_session/login/01_form.jsp");
	%>

</body>
</html>