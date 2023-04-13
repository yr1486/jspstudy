package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.repository.ExDao;

public class RemoveService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		// 리퀘스트에잇는거 꺼내자
		int exNo = Integer.parseInt(request.getParameter("exNo"));
		
		int result = ExDao.getInstance().remove(exNo);
		
		return new ActionForward(request.getContextPath() + "/list.do", true); // 리다이렉트. 삽입이니까.
		
		
		// 삽입삭제 알면 수정은 혼자할수있다...해보자..........
		
	}

}
