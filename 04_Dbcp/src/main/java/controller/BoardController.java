package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 맵핑하기.
@WebServlet("*.do") 
// 목록보기에서 사용할 맵핑이름 : getAllBoardList.do 상세보기 getBoardByNo.do  addBoard.do modifyBoard.do removeBoard.do

public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 요청과 응답 인코딩
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// URLMapping 확인
		String requestURI = request.getRequestURI(); 						/*  /04_Dbcp/getAllBoardList.do	*/ // 얘가 컨택스트 
		String contextPath = request.getContextPath();						/*	/04_Dbcp					*/
		String urlMapping = requestURI.substring(contextPath.length()); 	/*  /getAllBoardList.do			*/ // 8임.
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}





