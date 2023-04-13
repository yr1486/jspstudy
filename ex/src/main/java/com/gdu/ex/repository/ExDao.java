package com.gdu.ex.repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.ex.domain.ExDto;

//임폴트 정리 : ctrl + shift + o

public class ExDao { // EX테이블(DB)의 접근하는 객체로 알면 됨
	// mybatis-config를 읽어 들이는 애.
	// 읽어들이는 코드를 작성한다. => 마이바티스홈페이지에서 긁어왔음. try안에있는 3줄
	
	
	// 필드
	private SqlSessionFactory factory; // SqlSession을 만드는 공장.
	// SqlSession은 마이바티스에서 만든 클래스 => 뭐하는 애냐.
	// SqlSession이란?
	// MyBatis에서 사용하는 인터페이스(클래스)
	// 뭐할떄쓰냐면 마이바티스는 꼭있어야하는게 맵퍼임.쿼리작성하는 맵퍼!!
	// mapper에 있는 쿼리문을 읽는 애. 읽어서 실행하는 애.
	// sql세션은 공장이 필요함. 그래서 공장먼저 만들어야해.. 양말공장세워서 양말몇백개 생성하는것처럼. 공장하나 짓고. 양말백개만들고.
	// 그래서 마이바티스 홈피에서 팩토리뽑는 3줄을 안내하고 있음 그게 트라이캐치안에있는 3줄. 세션, 세션팩토리는 마이바티스에서만 사용하는 전용이라고 생각하면 됨
	// sql세션공장에서 sql세션하나씩뺴가지고 쿼리문을 실행하고 하는거임 (쿼리문이 있는곳 ex.xml파일)
	
	// 메소드 1개가 쿼리문 1개를 실행한다
	
	
	
	// Singleton Pattern
	private static ExDao dao = new ExDao();
	
	private ExDao() {
		
		try {
			String resource = "com/gdu/ex/config/mybatis-config.xml";
			// 이게 파일이자나. 파일의 내용을 읽어야하니까. 입력스트림씀. 인풋스트림.
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
			
			//그 읽어들인 내용으로 공장을 만들었다 짓겟다. = 공장만드는 사람 불러가지고.
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static ExDao getInstance() {
		return dao;
	}
	
	private final String NS = ""; // mapper의 namespace. 
	// 수업시간에는 NS에 담아서 썼어. 리스트를쓸떄 헷갈리니까. 경로.list로 쓰기로 했거든 그걸 변수에 담아서 쓰는거임.
	
	public List<ExDto> list() { // 메소드 1개가 쿼리문 1개 // 메소드명은 쿼리문의 id와 같게쓰지고 수업때 약속했음. spring 대비떄문에.
		SqlSession ss = factory.openSession(); // 공장에서 뽑는거 하나씩.
		List<ExDto> result = ss.selectList("com.gdu.ex.repository.ex.list"); // NS + list 써도됨 // 마이바티스에서는 <>안에 아이디로 찾는걸 문법으로 막아놈 왜, 맵퍼가 여러개일수있는데 어느 맵퍼파일에 아이디를 부르는지 모르니까(파일은 다르고, 아이디 이름이 중복이면 뭘불러올지 몰라서, 주소를 쓰는거지)
		// 셀렉트리스트! 니까. 여러개니까. 리스트 리절트에 담아두는거임.
		ss.close(); // sqlsession 그만쓸게요 하는거임 // 언제나 디비의 마지막부분은 닫기임. 자원반납
		// 마이바티스가 con, ps, rs를 처리해주기 때문에 ss.클로즈는 니가쓴거 알아서 다닫아라 라는거임
		// 모든작업 모든 메소드 단위로 자원반납해주는거임 // 모든 메소드의 리턴앞에는 클로즈가 있다는걸 알아두기.
		return result;
		
		/*
		ss.selectList()	: SELECT 결과 행이 2개 이상일 때 사용한다 // 얘를 쓰면 어레이리스트가 따라다니는거임. 왜냐 여러개니까.
		ss.selectOne()	: SELECT 결과 행이 1개 일 때 사용한다.
		ss.insert()		: INSERT 실행할때 사용한다.
		ss.update()		: UPDATE 실행할때 사용한다.
		ss.delete()		: DELETE 실행할때 사용한다.
		*/
		// 진짜 디비 열어서 결과로 따지는게 아니라, 쿼리문만 보고
	}
	
	
	
	public ExDto detail(int exNo) { //반환타입이 쿼리문에서 1개이기떄문에 리스트가 아니라 ExDto인거임 
		
		SqlSession ss = factory.openSession(); // 공장에서 오픈세션으로 뽑는거 하나씩.
		ExDto result = ss.selectOne(NS + "detail", exNo);  // exNo가 detail 쿼리로 전달되는 parameter이다. exNo이름이 a로바뀌면 쿼리문에 웨얼절에 있는애도 #{a}로 바뀌는 거임, 그리고 매개변수이름도!!a로
		
		ss.close();
		
		return result;
		
		
	}
	
	// 메소드 만들때 ex.xml보면서 만들어야하는거임 원래.
	
	// View <- controller <- Service <- Dao 이렇게 다오에서 넘어가는 거임
	
	// 인설트반환타입 인트인거 잊지말기
	public int save(ExDto ex) {
		SqlSession ss = factory.openSession(false); // 수동커밋하겠다. commit코드를 직접 넣겠다는거. 임폴트할때 변수명을 잘 보기. 나와있음
		int result = ss.insert(NS + "save", ex); // 1이면 성공 1개 삽입했어요. 1이거나 0이거나.
		if(result == 1) { // 1이면 실행하겠다. 잘 삽입이 됐으면 실행하겠다
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	public int remove(int exNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(NS + "remove", exNo); // 삭제하고자하는 애 번호까지 전달
		if(result == 1) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	
	
	
	
	

}
