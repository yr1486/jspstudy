package practice06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FileResponseServlet")
public class FileResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 	1. 전달 받은 파일을 이용해서 경고창 만들기
		 		예시) 2023-03-17-방예림-금요일이다.txt 파일이 생성되었습니다.
		 		
		 	2. 작성 화면으로 돌아가기
		 		client.html로 돌아기기
		 */
		
		request.setCharacterEncoding("UTF-8");
		String filename = request.getParameter("filename");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('" + filename + "  파일이 생성되었습니다.');");
		out.println("location.href='/01_Servlet/practice06/client.html';");
		out.println("</script>");
		out.flush();
		out.close();
		// print하면 한줄로만들어져서 안돌아감 스크립트사이에 알럿하고 로케이션을 인식못함
		
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
