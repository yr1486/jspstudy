package practice01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Practice01")

public class Practice01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Practice01() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		String name = request.getParameter("name");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String gender = request.getParameter("gender");
		String email= request.getParameter("email");
		String tel = request.getParameter("tel");
		String country = request.getParameter("country");
		
		// 응답 타입 및 인코딩
		response.setContentType("text/html; charset=UTF-8");
		
		// 응답 스트림 생성
		PrintWriter out = response.getWriter();
		
		// 응답
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"ko\">");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Insert title here</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<ul>");
		out.println("<li>아이디:" + id + "</li>");
		out.println("<li>비밀번호:" + pw + "</li>");
		out.println("<li>이름:" + name + "</li>");
			int m = month.isEmpty() ? 0 : Integer.parseInt(month);
			int d = day.isEmpty() ? 0 : Integer.parseInt(day);
			
		out.println("<li>생년월일: " + year + "년 " + String.format("%02d", m) + "월 " + String.format("%02d", d) + "일" + "</li>");
		out.println("<li>성별: " + (gender.equals("male") ? "남자" : gender.equals("female") ? "여자" : "선택안함") + "</li>");
		out.println("<li>이메일: " + (email.isEmpty() ? "없음" : email) + "</li>");
		out.println("<li>휴대전화: " + country + " " + tel.substring(1) + "</li>");
		out.println("</body>");
		out.println("</html>");
		out.flush();  // (혹시) 출력 스트림에 남아 있는 데이터를 모두 내보내기
		out.close();
		
				

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
