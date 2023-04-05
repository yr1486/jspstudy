package test;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.BbsDTO;
import repository.BbsDAO;

public class BbsTest {
	
	// Bbs DAO의 메소드 단위로 테스트를 진행한다. 다오는 싱글턴이라서. 뉴 못만듦.
	private BbsDAO dao = BbsDAO.getInstance();

	//@Test
	public void 목록테스트() {
		assertEquals(2, dao.selectAllBbsList().size());
		
	}
	// insert에 2개 넣어놨으니 어레이
	
	//@Test
	public void 상세테스트() {
		assertNotNull(dao.selectBbsByNo(1));
	}
	
	//@Test
	public void 삽입테스트() {
		BbsDTO bbs = new BbsDTO();
		bbs.setTitle("테스트제목");
		bbs.setContent("테스트내용");
		assertEquals(1, dao.insertBbs(bbs));
	}

}
