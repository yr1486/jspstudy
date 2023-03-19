package practice05;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Practice05_2")

public class Practice05_2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 냉장고 syso로 출력
		
		// 다시 인코딩 해주고 요청이 2번이라서 캐랙터인코딩도 2번
		request.setCharacterEncoding("UTF-8");
		
		// 꺼내고
		String model = request.getParameter("model"); // 두번째 요청에 model이 없기 때문에 null 값이 저장된다
		System.out.println("Practice05_2 : " + model);
		
		//System.out.println(request.getServletContext().getRealPath("Practice05_2")); //컨택스트 = 프로젝트 임!!!! 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
