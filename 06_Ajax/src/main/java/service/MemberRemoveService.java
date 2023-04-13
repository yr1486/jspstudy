package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import domain.Member;
import repository.MemberDAO;

public class MemberRemoveService implements IMemberService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 요청 파라미터(삭제해야 할 회원 번호)
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		
		// int memberNo 객체의 정보를 이용해 DB의 내용을 삭제
		int deleteResult = MemberDAO.getInstance().deleteMember(memberNo);
		
		response.setContentType("application/json; charset=UTF-8");	

		
		
		try {
			
			int deleteResult = MemberDAO.getInstance().insertMember(member);
			
			
			JSONObject obj = new JSONObject();
			obj.put("deleteResult", deleteResult); // 1아니면 0
			
			 
			PrintWriter out = response.getWriter();
			out.println(obj.toString());
			out.flush();
			out.close();
				
			} catch(Exception e) {
				response.setContentType("text/plain; charset=UTF-8");
				
				// 응답 코드 만들기
				response.setStatus(500);
				// 응답 데이터 만들기
				String msg = "신규 등록이 실패했습니다.\n입력 데이터를확인하세요.";
				
				// 응답(catch문의 응답이므로 ajax의 error로 전달된다.)
				PrintWriter out = response.getWriter();
				out.println(msg);
				out.flush();
				out.close();
				
				// 메세지와 응답코드 모두 jqXHR로 간다.
			}

		
	}

}
