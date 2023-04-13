package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.repository.ExDao;

public class ExListService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 컨트롤러가 주는거임 리퀘스트 리스펀스
		// 리퀘스트에 저장해두면 컨트롤러가 포워드를 할거다.
		
		// Dao를 불러서 목록을 가져온 다음 request에 저장해서 forward 준비를 해준다.
		request.setAttribute("exList", ExDao.getInstance().list());
										// ,다오를 불러서 목록가져온거
		return new ActionForward("ex/list.jsp", false); // ex폴더 아래 lis t.jsp로 forward 해 달라는 의미로 경로를 반환한다.
		// jspㅍ ㅏ일은 무조건 webapp에 있어야함.
		// 슬래쉬 앞에는 폴더명임. ex폴더를 만들고 jsp파일을 만드는거.
	}

}
