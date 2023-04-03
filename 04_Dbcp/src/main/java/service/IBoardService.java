package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface IBoardService {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	// 모든 서비스가 다 만들어 질 수 있음
	

}
