package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.BbsDTO;

public class BbsDAO {
	
	// 필드
	private SqlSessionFactory factory;
	
	// Singleton Pattern
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {
		
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
			// 공장을 만들었다 = 공장만드는 사람 불러가지고.
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	/* 메소드명과 쿼리문의 id를 맞추자. */
	
	// mapper의 namespace
	// 여러번 나오니, 변수로 담아서쓰자.
	private final String NS = "mybatis.mapper.bbs.";
	//                         	<mapper namespace=
	
	// 1.목록
	public List<BbsDTO> selectAllBbsList() {
		SqlSession ss = factory.openSession();
		List<BbsDTO> bbsList = ss.selectList(NS + "selectAllBbsList"); // mapper's namespace + query's id
													// 여긴 실행쿼리문 id  
		ss.close();
		return bbsList;
	}
	
	// 2. 상세
	public BbsDTO selectBbsByNo(int bbsNo) {
		SqlSession ss = factory.openSession();
		BbsDTO bbs = ss.selectOne(NS + "selectBbsByNo", bbsNo);
		ss.close();
		return bbs;
	}
	
	// 3. 삽입
	public int insertBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false); // autoCommit을 하지 않고, 직접 commit 할거임.
		int insertResult = ss.insert(NS + "insertBbs", bbs);
		if(insertResult == 1) { // 삽입 성공 시
			ss.commit();		// commit 하시오 (수동커밋)
		}
		ss.close();
		return insertResult;
	}
	
	// 4. 수정
	public int updateBbs(BbsDTO bbs) {
		SqlSession ss = factory.openSession(false);
		int updateResult = ss.update(NS + "updateBbs", bbs);
		if(updateResult == 1) {
			ss.commit();
		}
		ss.close();
		return updateResult;
	}
	
	public int deleteBbs(int bbsNo) {
		SqlSession ss = factory.openSession(false);
		int deleteResult = ss.delete(NS + "deleteBbs", bbsNo);
		if(deleteResult == 1) {
			ss.commit();
		}
		ss.close();
		return deleteResult;
	}

}












