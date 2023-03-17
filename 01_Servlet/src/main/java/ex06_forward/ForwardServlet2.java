package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet2")

public class ForwardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 포워드 이후(두번째 요청) 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("ForwardServlet2 : " + model);
		
		//콘솔에 이렇게 찍히고
		//forwardServlet1 : TV
		//forwardServlet2 : TV
		
		// 주소창에
		//http://localhost:9090/01_Servlet/forwardServlet1?model=TV
		//forwardServlet1 이라고 찍힘
		// 서버 안에서 이루어지기 때문에 포워드2로 이동했다는게 안보임. (자기들끼리 내선 돌려서)
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
