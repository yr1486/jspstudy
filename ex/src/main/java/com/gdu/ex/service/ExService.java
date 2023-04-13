package com.gdu.ex.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdu.ex.common.ActionForward;

public interface ExService {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	// 받아오는 주체가 컨트롤러임. 컨트롤러에서 리퀘스트 리스펀스를 받아온다는 거
	

}
