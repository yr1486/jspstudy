package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDAO {
	private SqlSessionFactory factory;
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {
		
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static MemberDAO getInstance() {
		return dao;
		
	}
	// mapper's namespace (어떤 mapper인지 인식하기 위함)
	private final String NS = "mybatis.mapper.member.";
	
	// 메소드명은 쿼리의 id와 동일한 이름을 사용하자
	
	// 목록
	public List<Member> selectAllMembers() {
		SqlSession ss = factory.openSession();
		List<Member> members = ss.selectList(NS + "selectAllMembers"); // mybatis.mapper.member.selectAllMembers
		ss.close();
		return members;
	}
	 
	
	// 전체 회원수
	public int getMemberCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(NS + "getMemberCount"); // NS : mybatis.mapper.member. + getMemberCount // 그냥 NS 안쓰고 풀어서 적어도 됨
		ss.close();
		return count;
	}
	
	
	// 상세 보기 (멤버 한명의 정보)
	public Member selectMemberByNo(int memberNo) {
		SqlSession ss = factory.openSession();
		Member member = ss.selectOne(NS + "selectMemberByNo", memberNo);
		ss.close();
		return member;
	}
	


	// 삽입
	public int insertMember(Member member) {
		SqlSession ss = factory.openSession(false);
		int insertResult = ss.insert(NS+ "insertMember", member); //객체로
		if(insertResult == 1) {
			ss.commit();
		}
		ss.close();
		return insertResult;
	}
	
	
	// 수정
	public int updateMember(Member member) {
		SqlSession ss = factory.openSession();
		int updateResult = ss.update(NS + "updateMember", member);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
		
	}
	
	
	// 삭제
		public int deleteMember(int memberNo) {
			SqlSession ss = factory.openSession(false);
			int deleteResult = ss.delete(NS + "deleteMember", memberNo);
			if(deleteResult == 1) {
				ss.commit();
			}
			ss.close();
			return deleteResult;
			
	}
		
		
}














