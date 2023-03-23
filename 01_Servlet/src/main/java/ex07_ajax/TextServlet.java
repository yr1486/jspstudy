package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/TextServlet")

public class TextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 요청 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 요청 파라미터
			String model = request.getParameter("model");
			String strPrice = request.getParameter("price");
			int price = 0;
			if(strPrice != null && strPrice.isEmpty() == false) {
				price = Integer.parseInt(strPrice);
			}
			
			// 마이너스 금액의 예외 처리
			if(price < 0 ) {
				throw new RuntimeException(price + "원은 입력이 불가능한 금액입니다"); // 메세지임 message. 이메세지 꺼내려면 getmessage.k
			}
			
			// 응답 데이터 타입
			response.setContentType("text/plain; charset=UTF-8"); //예외메세지 텍스트로 처리했음
			
			// 출력 스트림 생성
			PrintWriter out = response.getWriter();
			
			// 출력
			//out.println("모델은" + model + "이고, 가격은 " + price + "원 입니다.");
			String resData = "모델은" + model + "이고, 가격은 " + price + "원 입니다.";
			out.println(resData);
			// 두문장 같은 문장. 변수이름을 맞춰줌
			out.flush();
			out.close();
			
			
		}catch(NumberFormatException e) { // 캐치는 여러개 만들 수 있음. 캐치를 하나 더 달자. -600 숫자같은 처리를 해야하니까.
			// 예외 상황에 따른 응답 만들기
			// 응답 코드 : 600 (임의로 작성)
			// 응답메시지 : 가격을 확인하세요
			
			// 응답메시지 타입
			response.setContentType("text/plain; charset=UTF-8");
			
			// 응답코드
			response.setStatus(600);
			
			// 응답메시지(responseText)
			PrintWriter out = response.getWriter();
			out.println("가격을 확인하세요"); // getmessage
			out.flush();
			out.close();
		} catch(RuntimeException e) {
			// 예외 상황에 따른 응답 만들기
			// 응답 코드 : 601 (임의로 작성)
			// 응답메시지 : 예외 객체 e의 저장된 message 필드 값
			
			// 응답메시지 타입
			response.setContentType("text/plain; charset=UTF-8");
			
			// 응답코드(status)
			response.setStatus(600);
			
			// 응답메시지(responseText)
			PrintWriter out = response.getWriter();
			out.println(e.getMessage());
			out.flush();
			out.close();
			
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
