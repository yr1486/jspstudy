package ex05_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RedirectServlet2")

public class RedirectServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	// System.out.println("리다이렉트 도착");
		
	// 리다이렉트 이후 ( 두번째 요청) 파라미터 확인
	
		String model = request.getParameter("model");  // 두 번째 요청에 파라미터 model이 없기 때문에 null 값이 저장된다.
		System.out.println("RedirectServlet2 : " + model);
		
		
		// 한 후  클라이언트 가서 실행 하면
		// 콘솔에 
		//RedirectServlet1 : TV
		//RedirectServlet2 : null
		//찍힘.
		
		// 중간에 끊어지기떄문에!!!!!!!!!!!! 파라미터가 전달되지 않는다.
		// 즉 첫번째는 볼 수 있어. 두번째는 끊어져서 전달 안돼.
		// 정리 리다이렉트는 2번쨰 전달이 안되는 특징이 있다.
		
		
		// 되게 하려면
		

		// 두번째 요청에 model이 없기 때문에 null 값이 저장된다. 즉 두번쨰 요청에 모델을 붙여주면 된다.
		// 첫번째 요청 :/01_Servlet/RedirectServlet1?model=TV
		// 두 번째 요청 : /01_Servlet/RedirectServlet2
		
		// 되게 하는거는 practice03에서 해보자.
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
