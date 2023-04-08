package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IMemberService {

	// actionforward 가 없음.그래서 반환 타입이 없음
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	//트라이캐치 안하려고.
}
