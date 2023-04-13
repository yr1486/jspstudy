package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;
import com.gdu.ex.domain.ExDto;
import com.gdu.ex.repository.ExDao;

public class ExDetailService implements ExService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// 반환타입이 액션포워드 
		
		// 상세보기할 게시글의 번호가 나온거임 exNo
		int exNo = Integer.parseInt(request.getParameter("exNo"));
		
		// exNo값을 가진 레코드(행, 데이터)를 db에서 가져온다
		ExDto ex = ExDao.getInstance().detail(exNo);
		
		// 가져온 데이터를 request에 저장해서 응답 Jsp(ex/detail.jsp)로 전달할 준비를 한다.
		request.setAttribute("ex", ex);
		
		// 응답 Jsp명과 이동방식을 반환한다.
		// 그래서 반환타입이 스트링이 아니라 액션포워드로 바꿔줘야 둘다 바꿀 수 있는거임
		return new ActionForward("ex/detail.jsp", false); // 쟤로 포워드하시오 뜻.
	}

}
