package ex10_Cookie;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

                           
@WebServlet("/CookieServlet2")

public class CookieServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 여기는 서버. 쿠키는 클라이언트에 저장. 클라이언트에서 서버로 가져올라면 이일을 하는 애는 request
		// 전체 쿠키 가져오기
		Cookie[] cookies = request.getCookies();
		// 쿠키서블릿2번이 확인 할수있는 애 다
		
		// 전체 쿠키 확인하기
		if(cookies != null) {  // 쿠키가 하나도 없을수 있으니까. 널체크
			for(int i = 0; i < cookies.length; i++) {
				System.out.println("쿠키이름 : " + cookies[i].getName() + ", 쿠키값 : " + URLDecoder.decode(cookies[i].getValue(), "UTF-8"));
			}
		
		}
		
		// 쿠키 삭제하기  (Max-Age가 0인 쿠키로 덮어쓰기)
		Cookie cookie1 = new Cookie("name", "");
		Cookie cookie2 = new Cookie("job", "");
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0); //지웠으니까 동작시키면 쿠키3에서 네임,잡이 안보일거임
		
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		// CookieServlet3으로 redirect
		response.sendRedirect("/01_Servlet/CookieServlet3");
		
	}

	// 어딘가 저장할 공간이 필요해서
	// 서버에서는 3군데
	// 클라이언트에서는 1군데가 있는거
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
