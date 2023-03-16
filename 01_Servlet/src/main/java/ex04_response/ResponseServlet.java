package ex04_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ResponseServlet")


public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ResponseServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청 파라미터 처리하기
		request.setCharacterEncoding("UTF-8");
		// null 처리해주기
		String model = request.getParameter("model");
		String strPrice = request.getParameter("price");
		
		int price = 0;
		if(strPrice != null && strPrice.isEmpty() == false) {
			price = Integer.parseInt(strPrice);
		}
		
		/*
		 	응답 
		 	서버가 클라이언트한테 보내는게 응답
		 	1. 서버 => 클라이언트로 보내는 것이 응답 (Response) 이다.
		 	2. 응답처리는 누가 하냐 HttpServletResponse 클래스가 응답을 처리한다.
		 	3. 어떤 MIME 타입으로 응답할 것인지 (json인지, xml인지 등) 결정해야 한다. 반환 타입 리턴 비슷한.
		 		1) HTML의 MIME 타입 : text/html         (태그를 만들어서 반환하는 경우)  // 태그를 만들어서 태그를 반환한다 생각하기.
		 		2) XML			  : application/xml    (ajax 응답이 XML인 경우)
		 		3) JSON			  : application/json   (ajax 응답이 JSON인 경우)
		 */
		
		// 2.  응답 만들기
		
		// 1). 응답할 데이터의 MIME 타입(contentType)과 UTF-8 인코딩
		response.setContentType("text/html; charset=UTF-8"); // 태그를 만들어서 반환할거야. // 마임타입과 유티에프파일 같이 하는거
		
		// 2). 응답 스트림 생성(IOException 처리가 필요하다 -> 이미 처리되어 있다.
		PrintWriter out = response.getWriter(); // PrintWriter의 출력 메소드 : append(), write(), print(), println() 등
		// 서블릿의 응답 만드는 방법
		// 3). 응답 만들기
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>나의 첫 응답</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>모델명: " + model + "</h1>");
		out.println("<h1>가격: " + price + "원</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();  // (혹시) 출력 스트림에 남아 있는 데이터를 모두 내보내기
		out.close();

		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
