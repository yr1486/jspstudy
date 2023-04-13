package com.gdu.ex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.service.ExDetailService;
import com.gdu.ex.service.ExListService;
import com.gdu.ex.service.ExService;
import com.gdu.ex.service.RemoveService;
import com.gdu.ex.service.exSaveService;
// 모든 요청을 받는. 컨트롤러! 컨트롤러에서 호출할건 서비스임
// 컨트롤러는 서블릿으로 만든다!!!!!!!!!!
@WebServlet("*.do") // .do라고 하는것만 처리할 수 있다는거
public class ExController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
											// exNO가 리퀘스트에 파라미터로 들어가있어. 그래서 아래에 path에 전달이됨
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();					/*/ex/list.do */
		String contextPath = request.getContextPath();					/*/ex  ===> 3인거야.  이 전체식에서 얘를 뺴기위해서. 렝스가 사용된거야.*/ 
		String urlMapping = requestURI.substring(contextPath.length()); /*/list.do가 만들어질수있음*/
		
		ExService service = null;
		
		// String path = null; // 경로를 하나 만들거야. ExListService (서비스)의 실행결과를 받으려고(얘가 타입이 스트링임)
		// 위에 얘는 아래에 액션 포워드로 바꿔 줬음! 왜, 둘다 가져와야 해서.
		ActionForward af = null;
		
		switch(urlMapping) {
		case "/list.do":
			service = new ExListService(); // 실행이 아니라 만든거
			break;
		case "/detail.do":
			service = new ExDetailService();
			break;
		case "/write.do":
			//ex/write.jsp로 forward하기. 디비말고. 이 화면으로. 포워드가 그냥 기본이기때문에 그래. 여기선 전달하기없고 그냥 단순이동. 단순이동은 포워드로 처리하자!!!!
			// 서비스에서 봐바. 어디제이에스피로 펄스로 이동해줘 할떄 액션포워드 만들어서 갔거든. 여기도 똑같아. 그렇게 갈거야. 그럼 액션 포워드 객체만들어서 가는거야
			af = new ActionForward("ex/write.jsp", false); // 포워드할떄 제이에스피!!!!!!!
			break;
		case "/save.do":
			service = new exSaveService(); /// 얘는 아래 이프문에서 이프가 실행
			break;
		case "/remove.do":
			service = new RemoveService(); //
			break;
		
		
		}
		// 서비스가 만들어지면 아래 if가 실행되고
		// write인경우에는 엘스가 실행됨
				
		if(service != null ) {
			af = service.execute(request, response); // 컨트롤러가 주는거임. // 여기서 널포인트익셉션발생 왜냐 위에 exservice service가 널이라고 선언했자나. 그랫는데 service.이 안되지 그니까. if씌워서. 서비스가 널이 아닐떄 실행할거라고 해준거야
			//af에 뉴액션포워드값이 그대로 담긴거야.
			
		}
		
		if(af.isRedirect()) { // 이게 지금 펄스, 엘스가 실행되는거임 - write의 경우
			
			response.sendRedirect(af.getPath());
			
			// af에 저장된 path를꺼내세요여서 af.getpath인거임, 그리고 isredirect도꺼내야하니까 , af.isRedirect()
			// 게터이름이 불린타입의 게터는 게터를 안쓴다! 겟이 아니라, isRedirect를 써야해. 약속이야.
		
		} else {
			request.getRequestDispatcher(af.getPath()).forward(request, response);
			// 엘스가 실행되는거임!!! 이프문은 괄호안에 펄스가 들어가있느거야
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
