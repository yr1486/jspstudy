package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;
import service.MemberAddService;
import service.MemberDetailService;
import service.MemberListService;
import service.MemberModifyService;
import service.MemberRemoveService;

@WebServlet("*.do")

public class MemberRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩 // 응답의 인코딩이 없다는거 차이 , 각 서비스별로 응답을 만들때 사용할거라서
		request.setCharacterEncoding("UTF-8");
		
		// URLMapping
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String urlMapping = requestURI.substring(contextPath.length());
		
		// 모든 서비스의 공통 타입(인터페이스)
		IMemberService service = null;
		
		// URLMapping에 따른 서비스 선택
		switch(urlMapping) {
		case "/list.do":
			service = new MemberListService();
			break;
		case "/detail.do":
			service = new MemberDetailService();
			break;
		case "/add.do":
			service = new MemberAddService();
			break;
		case "/modify.do":
			service = new MemberModifyService();
			break;
		case "/remove.do":
			service = new MemberRemoveService();
			break;
			
		}
		// 선택된 서비스 실행
		if(service != null) {
			try {
				service.execute(request, response);
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	// 에이작은 포워드도 없고 리다이렉트도 없음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
