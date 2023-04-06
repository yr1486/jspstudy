package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.BoardDTO;
import repository.BoardDAO;

public class BoardAddService implements IBoardService {

	// 스위치문에 담긴 후 아래가 실행된다.
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 1. 요청 파라미터
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. BoardDTO 객체 생성
		BoardDTO board = new BoardDTO();
		// 가방에 담아 놓기 위해서 생성해줌
		// 보드 디티오에 있는애들이 이 보드 변수에 담기는거 .
		
		board.setTitle(title); // b조
		board.setContent(content); // 화이팅!
		// 가방에 타이틀과. 컨탠트가 담겼어
		
		
		// 3. 삽입을 위해서 DB로 BoardDTO를 전달(BoardDAO의 insertBoard 메소드)
		// 삽입 결과가 0 아니면 1로 넘어온다.
		int insertResult = BoardDAO.getInstance().insertBoard(board);
		//                           겟인스턴스:dao에 전달.
		System.out.println(insertResult == 1 ? "삽입성공" : "삽입실패");
		
		// 4. 어디로 and 어떻게 이동 ..
		return new ActionForward(request.getContextPath() + "/getAllBoardList.do", true);
					// 액션포워드는 화면전환임!!! 모든 목록리스트로 가겠다.               리다이렉트
										// 겟컨텍스트패스가:04~
		
		
		//리다이렉트는 이응답을 끊고 전환하는거. 인설트딜리트업데이트 전화를 끊고 다시.
		// 포워드는 응답을 유지한 채 전환하는거 -- 내선번호로 연결해 드릴게요.
		
	}

}









