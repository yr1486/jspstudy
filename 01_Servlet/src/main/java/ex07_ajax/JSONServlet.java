package ex07_ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/JSONServlet")

/*
 	Dynamic Web Project에서 외부 라이브러리(*.jar) 사용하는 방법
 	
 	방법1. CATALINA_HOME\lib 디렉터리에 사용할 라이브러리를 추가한다.
 	방법2. 컨텍스트(프로젝트)에 사용할 라이브러리를 추가한다.				 (수업에서는 이 방법으로 사용할 거임)
 		   src/main/webapp/WEB-INF/lib 디렉터리에 외부 라이브러리 추가
 */


public class JSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			request.setCharacterEncoding("UTF-8");
			
			// 요청 파라미터
			String name = request.getParameter("name");
			String strAge = request.getParameter("age");
			int age = 0;
			if(strAge != null && strAge.isEmpty() == false) {
				age = Integer.parseInt(strAge);
			}
  			//System.out.println(name + ", " + age);
			
			// 이름 예외 처리
			 if(name.length() < 2 || name.length() > 6) {
				 throw new NameHandleException(name + "은 잘못된 이름입니다.", 601);
			 }
			
			// 나이 예외 처리
			if(age < 0 || age > 100) {
				throw new AgeHandleException(age + "살은 잘못된 나이입니다.", 600);
			}
			
			// 응답할  JSON 데이터
			JSONObject obj = new JSONObject();
			obj.put("name", name);
			obj.put("age", age);
			System.out.println(obj.toString()); // {"name": "마돈나", "age": 50}
			
			// 응답 데이터 타입
			response.setContentType("application/json; charset=UTF-8"); //text/plain;  쓰게되면 제이슨.파스 써야함
			
			// 출력 스트림 생성
			PrintWriter out = response.getWriter();
			
			// 출력
			String resData = obj.toString(); //JSON.parse(resDate) 없이 사용가능 // 스트링을 보내지만 제이슨으로 받을 수 있음
			out.println(resData);
			
			//out.println(obj.toString()); // 한줄로도 만들수 있음 이름맞추려고 2줄로 한거 // 텍스트 형식으로 된 JSON 데이터를 응답한다.
			out.flush();
			out.close();

		}catch(AgeHandleException e) { // e.getMessage(),
			
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(e.getErrorCode());
			
			response.getWriter().println(e.getMessage());
			
			
		}catch(MyHandleException e) { 
			
			response.setContentType("text/plain; charset=UTF-8");
			
			response.setStatus(e.getErrorCode());
			
			response.getWriter().println(e.getMessage());

		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
