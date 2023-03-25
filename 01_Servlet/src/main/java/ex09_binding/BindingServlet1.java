package ex09_binding;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BindingServlet1")

public class BindingServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*
		 	1. stateless
		 		1) 상태 없음
		 		2) 웹 페이지간의 이동은 stateless한 이동이다. ==> 리다이렉트라고 볼 수 있음. 리다이렉트는 값을 가지고 넘어가지 않음. 리다이렉트는 <a href="">와 같다. 자바스크립트는 location.href="" 얘도. 아무것도 안가지고 넘어가는게 원래 디폴트 이걸 스테이트레스라고 하는거임 그와다르게 포워드는 전달하는 애니까. 값을 넘져준다. 리퀘스트나, 리스펀스를. 리스펀스는 저장소가아님. 리퀘스트가 저장소임!! 리퀘스트전달하는 이동방법은 포워드!!
		 		3) 현재 페이지에는 이전 페이지의 정보가 없다.
		 		
		 	2. binding
		 		1) 값을 저장할 수 있는 영역에 속성(Attribute)의 형태로 값을 저장하는 것을 말한다.
		 		2) 저장 영역은 서버가 제공한다.
		 		2) 3개 영역
		 			(1) HttpServletRequest : 하나의 요청 내에서 값을 저장할 수 있다. (1회용)
		 			(2) HttpSession		   : 웹 브라우저 종료 전까지 값을 저장할 수 있다. (시간 지정 가능)
		 			(3) ServletContext	   : 컨텍스트(프로젝트, 애플리케이션) 종료 전 까지 값을 저장할 수 있다.
		 			
		 		4) 속성(Attribute) 관련 메소드
		 			(1) getAttribute('속성') 		: 값 가져오기 (캐스팅이 필요할 수 있다. 값을 저장할 때 오브젝트 타입으로 저장해서)
		 			(2) setAttribute('속성', 값)	: 값 저장하기 (object 타입으로 저장한다. 오브젝트는 정수 실수 가지리않고 모두 다다다 저장할 수 있다) 그래서 꺼내 쓸때 캐스팅 필요. 형변환 필요 즉 오브젝트에 저장한건 캐스팅해서 쓴다.
		 			(3) removeAttribute('속성')	: 값 제거하기
		 			
		 */
		
		
		// 겟메소드의 특징 리퀘스트를 사용할 수 있다. 아무자바나 리퀘스트를 쓸 수 있는게 아님.
		
		
		
		
		
		// HttpServletRequest에 저장하기
		request.setAttribute("a", 100);
		
		// HttpSession 에 만들고 저장하기
		HttpSession session = request.getSession();
		session.setAttribute("a", 200); // a는 저장소가 다르니까. 같아도 상관없음
		// 객체가있고.메소드를 부르는 방식
		// servletContext에 저장하기
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("a", 300);
		
		
		// 저장소가 다르니까 a 같은 이름 상관없음
		
		// 페이지 이동하기
		// 서블릿에서 페이지 이동하는 방법은  2가지가 있음 // 1. redirect 2. forward  중요
		
		// 1. HttpServletRequest의 전달이 없는 이동 : redirect, <a href="">, location.href=""ㄴ
		//response.sendRedirect("/01_Servlet/BindingServlet2");
		// 얘는 회사이름/부서이름 다적어줌.
		
		
		// 2. HttpServletRequest의 전달이 있는 이동 : forward
		// BindingServlet1번 실행시키는 거 잊지말기. 2번에서 돌리는 거 아님
		// 포워드는 내부이동. 서버이동임. 내부이동이니까. 부서이름만 적어줌.
		request.getRequestDispatcher("/BindingServlet2").forward(request, response);
		
		// 리퀘스트에 전달이 없는 리다이렉트는 저장해도 소용이 없음
		// ex09 완전중요
		
		
		// 저장소 3개를 배웠고.
		// 이제 클라이언트 단에서 저장소가 있는. 쿠키라는 걸 배울거임.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
