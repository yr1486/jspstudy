<%@page import="java.text.SimpleDateFormat"%>
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
		String sessionId = session.getId();
		long creationTime = session.getCreationTime();
		String strCreationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(creationTime);
		long lastAccessedTime = session.getLastAccessedTime();
		String strLastAccessedTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastAccessedTime);
		int maxInactiveInterval = session.getMaxInactiveInterval();
	
		
	%>
	<h1>세션 아이디 : <%=sessionId%></h1>
	<h1>세션 생성시간 : <%=creationTime%></h1>
	<h1>세션 생성시간 : <%=strCreationTime%></h1>
	<h1>세션 최종접속시간 : <%=lastAccessedTime%></h1>
	<h1>세션 최종접속시간 : <%=strLastAccessedTime%></h1>
	<h1>세션 유지시간 : <%=maxInactiveInterval%>초</h1>
<%-- 
브라우저를 누가 열었는지 모르니, 랜덤하게 넘버를 부여. 쿠키에 저장됨.
이 값을 알고있는사람은. 열었던 사람이다. 라고 판단할 수 있는거. 이전에 접속한 유저였는지를 판단하기위한 값으로 쓰일 수 있다. 자동로그인
이 16진수의 값을. 브라우저를 연 사람의 아이디로 쓰겠다.

나중에 이걸 db에 저장시켜논다.
(회원정보db에)
다음에 비교할 정보는. 디비말고 비교를 할 저장공간이 필요하니까. 그걸 어디에 적어놓냐면 쿠키에 적어논다.
쿠키=브라우저를 닫든말든 상관없이 저장됨. max age 로 기간을 정해서 쓸 수 있어. 한달. 일년 이렇게..
그러니까 2군데 적어놓는거야. 자기회원정보에 한번, 쿠키에 한번 적어놓고
그다음에 로그인하려고 들어가면 쿠키에 있는값과 db에 있는값을 찾아가지고 그사람의 정보를 가지고 오는. 이게 자동 로그인.
쿠키가 어느정도 기억하냐에 따라서. 자동로그인의 유효가 결정되는. 보통 사이트에서는 한달정도? 이렇게 설정해져 있을거임.

--%>
<%-- 세션아이디가 같을 때, 다를 때: 다른 사용자라고 인식 //  세션아이디는 자동로그인을 구현할 때 쓴다. --%>

</body>
</html>