package service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberListService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 응답을 만들어 줄거임
		// 디비로부터 데이터를 받아와서 처리하는거
		// 하나의 서비스는 여러 개의 DAO 메소드를 호출 할 수 있다.
		MemberDAO dao = MemberDAO.getInstance();
		List<Member> memberList = dao.selectAllMembers();
		int memberCount = dao.getMemberCount();
		
		// 응답할 JSON 데이터를 만들어 주자
		
		/*
		  	객체로 만들자(자바스크립트 객체)
		  
		  	{
		  		"memberCount": 2,
		  		"memberList": [
		  			{
		  				"memberNo": 회원번호,
		  				"id": "회원아이디",
		  				"name": "회원명",
		  				"gender": "회원성별",
		  				"address": "회원주소"
		  			},
		  			{
		  				"memberNo": 회원번호,
		  				"id": "회원아이디",
		  				"name": "회원명",
		  				"gender": "회원성별",
		  				"address": "회원주소"
		  			}
		  		]
		  	}
		*/
		
		// 중괄호로 묶여있어서 JSON Object
		JSONObject obj = new JSONObject();
		obj.put("memberCount", memberCount);
		obj.put("memberList", memberList);		//JSON라이브러리가 JAVA의 ArrayList를 자바스크립트 배열([])로 바꾸고, Java의 Member 객체를 JavaScript의 객체({})로 알아서 바꾼다.
												// 어레이리스트만 준비하면 된다.
		// 응답하기 (요청한 곳으로 그대로 응답된다. 즉, ajax() , 에이작 메소드로 응답처리 된다.) // 요청했던곳으로그대로 돌아간다 =>index.jsp의 ajax로!!
		response.setContentType("application/json; charset=UTF-8"); // "text/html" 아님!!!! 데이터 자체를 넘기는거야. 응답은 제이슨이다!!!!!!
		PrintWriter out = response.getWriter();
		out.println(obj.toString()); // JSON데이터를 텍스트 형식으로 변경해서 반환하기
		out.flush();
		out.close();
		
		
		
		
		
	}

}
