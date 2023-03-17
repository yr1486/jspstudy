package ex05_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RedirectServlet1")

public class RedirectServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// a링크로 요청했으니. 여기 겟으로 옴
		
		
		// 2.
		// 리다이렉트 이전(첫 번쨰 요청)의 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("RedirectServlet1 : " + model);
		
		// 1.
		// redirect를 이용해서 다른 서블릿(다른 서버 경로 = 주소)으로 이동하기.
		response.sendRedirect("/01_Servlet/RedirectServlet2");
		// 100번으로 다시 전화 거세요
		// /01_Servlet/ 이거뒤에 다른 서블릿인 : RedirectServlet2 를 적어주고, 얘를 하나 만들어 줌.
		// 만들어준걸 확인해야하니까. RedirectServlet2 doGet에서 콘솔에 찍어보려고 시스템아웃씀.
		
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
