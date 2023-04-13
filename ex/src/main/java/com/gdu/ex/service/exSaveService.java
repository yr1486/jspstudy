package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.domain.ExDto;
import com.gdu.ex.repository.ExDao;

public class exSaveService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String exContent = request.getParameter("exContent"); // 사용자가 입력한값
		
		ExDto ex = new ExDto(); // 디티오로 바뀌고
		ex.setExContent(exContent);
		
		              // 세이브를 호출,       전달
		int result = ExDao.getInstance().save(ex); // 디비에 전달되었음 #{exContent}에!!
		// 노란줄신경쓰지말기
		// Insert 이후에는 redirect 하자
		// redirect 경로 : mapping을 작성한다. 그래서 ContextPath부터 시작하는 경로로 작성한다.
		
		// 컨텍스트패스가 저장된 장소는 리 퀘 스 트 !
		return new ActionForward(request.getContextPath() + "/list.do", true); //리다이렉트니까 트루.
									// 컨택스트패스 - 컨트롤러에도 뽑아놨어. 기억하기
	}

}

//