package ex06_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ForwardServlet1")

public class ForwardServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 여기도 한글 안쓰니 생략
		// 포워드 이전(첫번째 요청) 파라미터 확인
		String model = request.getParameter("model");
		System.out.println("ForwardServlet1 : " + model);
		
		// 포워드(전달임)
		//ForwardServlet2 얘로 리퀘스트랑 리스펀스를 둘다 전달 하겠다.
		request.getRequestDispatcher("/ForwardServlet2").forward(request, response); // URLMapping만 적는다. 
		
		//							이미 서버 내부이동이라서. 전체경로를 적지 않는다. 맵핑만 적는다!!!!!!!! 전체경로 적으면 도리어 오류가 남. 안에서 내선번호 누를때 간단히 123만 누르는 것처럼
		// 리다이렉트는 다적고. 포워드는 안적고. 꼭 기억하기
		// 리다이렉트는 전체경로, 포워드는 맵핑만
		// 파라미터가 전달안되는거 리다이렉트 . 전달되는거 포워드
	}	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
