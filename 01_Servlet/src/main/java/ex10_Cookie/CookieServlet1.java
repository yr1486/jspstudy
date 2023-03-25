package ex10_Cookie;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CookieServlet1")

public class CookieServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 	Cookie
		 	1. 서버가 만들어서 클라이언트가 저장한다. // response 처리 해줘야 함
		 	2. 보안에 취약하다.
			
			쿠키 예시) 오늘더이상이창을열지않음 팝업창에 체크 // 로그인할때 아이디 기억에 체크 표시 서버에 저장되고 이런게아니라 쿠키에 저장된다.
		 */
		
		// 알아두기: 서블릿에서 작업하면 서버임!!! 백단에서 동작하는 언어. 쿠키는. 서버가 만든다.
		
		// 쿠키 만들기
		Cookie cookie1 = new Cookie("name", "방예림");
		Cookie cookie2 = new Cookie("address", URLEncoder.encode("서울시 금천구 가산동", "UTF-8")); // 띄어쓰기 세미콜론 등. 이렇게만 쓰면 오류남 // 유효하지 않은 문자(대표적으로 공백)는 UTF-8
		Cookie cookie3 = new Cookie("job", URLEncoder.encode("요양 보호사", "UTF-8"));
		
		
		// 쿠키가 저장될 경로 설정하기
		cookie1.setPath("/01_Servlet"); 				 // 컨텍스트패스 : request.getContextPath()  컨택스트패스의 경로를 가져오려면 리퀘스트.겟해가지고 가져오는거. 컨택스트패스는 경로. 컨택스트=프로젝트!! 지금의 컨택스트패스는 01_Servlet이 되고, 실행시키면 웹주소의 느낌표 부분이 되는 거. -> http://localhost:9090/!!!!!01_Servlet!!!!/CookieServlet3 
		cookie2.setPath("/01_Servlet/CookieServlet1");   // 서블릿경로 request.getRequestURI()
														 // cookie3은 경로 설정을 생략했으므로 컨텍스트패스에 저장된다.

		
		// 주소가 달라지면 쿠키가 달라진다. 
	
		// 쿠키 유효시간 설정하기(생략하면 세션쿠키가 된다. : 브라우저를 닫을때까지 보관된다. 
		// 쿠키 유효시간은 초당으로 설정하는 거
		//	cookie1.setMaxAge(1); // 1초 
			cookie1.setMaxAge(60 * 60 * 24 * 7); // 7일간 보관되는 쿠키
			cookie2.setMaxAge(60 * 60); //10초 동안 보관되는 쿠키
										// cookie3은 유효기간 설정을 생략했으므로 세션쿠키가 된다.
			
		// 쿠키 저장하기(응답으로 처리해야 한다)
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
		// 동일한 경로에 동일한 이름이 있으면 쿠키는 덮어쓰기 됨
		
		// CookieServlet2으로 redirect 이동
		response.sendRedirect("/01_Servlet/CookieServlet2");
		// 쿠키2는 서블릿1으로 잡은거라서 2에서는 2를 확인할 수 없다. 즉 어드레스를 확인할수없다. 
		
		
		
		// 쿠키 삭제하는 방법
		// 쿠키 유효시간을 0으로 덮어쓰기 하기. 저장되자마자 지워지게 됨
		// 쿠키 삭제 코드가 별도로 있는게 아니다. 저장하는 코드 삭제하는 코드가 같음
		// cookie1.setMaxAge(0); // 삭제
		// cookie1.setMaxAge(1); // 1초 
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
