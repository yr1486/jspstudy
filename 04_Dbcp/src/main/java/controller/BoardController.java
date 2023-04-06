package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import service.BoardAddService;
import service.BoardDetailService;
import service.BoardListService;
import service.BoardModifyService;
import service.BoardRemoveService;
import service.IBoardService;

// 맵핑하기.
@WebServlet("*.do") 
// 목록보기에서 사용할 맵핑이름 : getAllBoardList.do getBoardByNo.do writeBoard.do  addBoard.do modifyBoard.do removeBoard.do

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인 (보안상 맵핑하는 거)
		String requestURI = request.getRequestURI(); 						/*  /04_Dbcp/getAllBoardList.do	*/ 
		String contextPath = request.getContextPath();						/*	/04_Dbcp					*/
		String urlMapping = requestURI.substring(contextPath.length()); 	/*  /getAllBoardList.do			*/ // 8임.
										// 잘라낸다.
		// 모든 서비스의 공통 타입 선언
		// 모든 서비스는 IBoardService 타입으로 선언할 수 있다.
		IBoardService service = null;
		
		// ActionForward 선언
		ActionForward af = null;
		
		// URLMapping에 따른 서비스 생성
		switch(urlMapping) {
		case "/getAllBoardList.do":
			//이런 요청이 들어오면.
			service = new BoardListService();
			//서비스를 생성한다.
			break;
		case "/getBoardByNo.do":
			service = new BoardDetailService();
			break;
		case "/addBoard.do":
			service = new BoardAddService();
			break;
		case "/modifyBoard.do":
			service = new BoardModifyService();
			break;
		case "/removeBoard.do":
			service = new BoardRemoveService();
			break;
		case "/writeBoard.do" :
			af = new ActionForward("board/write.jsp", false); // board 폴더 아래 write.jsp로 forward한다.(단순 이동의 경우 forward한다.)
			break;
		}
		
		// 뉴 다음에는 실행이 필요하니까.
		// 위에 서비스를 널로 해놨으니까. 널포인트 익셉션 필요
		// 서비스 실행
		if(service != null) {
			af = service.execute(request, response); // af에는 어디로 어떻게 이동할지가 저장되는거	
			// 이동할떄 필요한 애 액션포워드
		}
		// 응답 View로 이동
		if(af != null) {
			if(af.isRedirect()) {
				response.sendRedirect(af.getPath());
			}
			else {
				request.getRequestDispatcher(af.getPath()).forward(request, response);
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}





