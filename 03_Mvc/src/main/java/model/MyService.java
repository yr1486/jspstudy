package model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface MyService {
	
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
	// 인터페이스는 public abstract를 자동으로 부여하기 때문에 이부분을 생략할 수 있다. 없더라도 자동추가됨
	// 다만 수업시간에 생략할때는 abstract만 생략할 예정
}
