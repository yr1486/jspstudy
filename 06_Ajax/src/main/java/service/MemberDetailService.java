package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberDetailService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 요청 파라미터
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		// DB에서 memberNo값을 가진 회원 정보 받아오기
		Member member = MemberDAO.getInstance().selectMemberByNo(memberNo);
	
		/* 오브젝트 객체로 만들어서 콘솔에 찍어보기
			JSONObject obj = new JSONObject(member);
			System.out.println(obj);
		*/
	
		// 응답 데이터 형식 (JSON)
		response.setContentType("application/json; charset=UTF-8");
		
		// 응답 데이터 만들기1 (JSON)
		/*
		JSONObject obj1 = new JSONObject(member);
		System.out.println(obj1.toString());
		객체 전체를 전달 . 프로퍼티가 5개
		 */
		
		// 응답 데이터 만들기2
		/*
		  {
		  	"member": {
		  		"memberNo": 회원번호,
		  		"id":회원아이디,
		  		"name": 회원명,
		  		"gender": 성별,
		  		"address":주소
		  	}
		  }
		 
		 */
		JSONObject obj	 = new JSONObject();
		obj.put("member", new JSONObject(member));
		// System.out.println(obj.toString());

	
		// 응답
		PrintWriter out = response.getWriter();
		out.println(obj.toString());
		out.flush();
		out.close();
	
	
	
	
	}

}













