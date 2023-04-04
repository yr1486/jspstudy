package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardDTO;

public class BoardDAO {
	
	// 모든 메소드가 사용할 공통 필드
	private Connection con; //필드는 기본적으로 널값을 가지고 있음
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	// Connection 관리를 위한 DataSource 필드
	private DataSource dataSource;
	
	// Singleton Pattern 으로 DAO 생성하기
	private static BoardDAO dao = new BoardDAO();
	
		
	private BoardDAO()	{
		// 다오를 필요로하는 것들(서비스들)에게 new를 허용하지 않겠다.
		
		// context.xml에서 <Resource name="jdbc/GDJ61" />인 Resource를 읽어서 DataSource 객체 만들기(생성하기)(JNDI 방식)
		try {
			
			Context context = new InitialContext();
			Context envContext = (Context)context.lookup("java:comp/env"); // 톰캣
			dataSource = (DataSource)envContext.lookup("jdbc/GDJ61"); // 진짜이름지정	
			
			/*
			 	위 3줄을 아래와 같이 2줄로도 가능
			  	Context context = new InitialContext();
			 	dataSource = (DataSource)envContext.lookup("java:comp/env/jdbc/GDJ61"); 
			 */
			
		}catch(NamingException e) { // 이름이 없으면 실행할수 없으니까
			e.printStackTrace();
		}

	}
	public static BoardDAO getInstance() {
		return dao;
		
		
	}
	// 나중에 보드다오 호출하려고 하면 뉴를 막아놨기 때문에
	// 객체로 호출하는 객체.메소드()가 안됨
	// 그래서 클래스로 호출하는 클래스.메소드()로 호출해야함
	
	// 자원(Connection, PreparedStatement, ResultSet) 반납하기
	private void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 게시글 목록 반환하기
	public List<BoardDTO> selectBoardList(){
		
		// 1. ArrayList 생성
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		
		try {
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD ORDER BY BOARD_NO DESC";
					
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();
			
			// 6. ResultSet 객체(결과 집합)를 이용해서 ArrayList를 만듬 
			while(rs.next()) {
				
				// Step1. Board 테이블의 결과 행(ROW)을 읽는다.
				int board_no = rs.getInt("BOARD_NO");
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// Step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				BoardDTO board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
				// Step3. BoardDTO 객체를 ArrayList에 추가한다.
				boardList.add(board);
				
			}
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally { // 문제가 있든 없든 언제나 실행되는 파이널리
			// 예외 발생 여부와 상관없이 항상 자원의 반납을 해야한다.
			close();
		}
		//System.out.println(boardList.toString());
		
		// 7. ArrayList 반환
		return boardList;
	}

	// 게시글 반환하기
	public BoardDTO selectBoardByNo(int board_no) {
		
		// 1. 게시글 반환하기
		BoardDTO board = null;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "SELECT BOARD_NO, TITLE, CONTENT, MODIFIED_DATE, CREATED_DATE FROM BOARD WHERE BOARD_NO = ?";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setInt(1, board_no);   // 1번째 물음표(?)에 board_no 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(SELECT문 실행은 executeQuery 메소드로 한다.)
			rs = ps.executeQuery();
			
			// 7. ResultSet 객체(결과 집합)를 이용해서 BoardDTO를 만듬

			if(rs.next()) {
				
				// Step1. Board 테이블의 결과 행(ROW)을 읽는다.
				String title = rs.getString("TITLE");
				String content = rs.getString("CONTENT");
				Date modified_date = rs.getDate("MODIFIED_DATE");
				Date created_date = rs.getDate("CREATED_DATE");
				
				// Step2. 읽은 정보를 이용해서 BoardDTO 객체를 만든다.
				board = new BoardDTO(board_no, title, content, modified_date, created_date);
				
			}
			
			}catch(Exception e) {
				e.printStackTrace();
			} finally { // 문제가 있든 없든 언제나 실행되는 파이널리
				// 예외 발생 여부와 상관없이 항상 자원의 반납을 해야한다.
				close();
			}

		// 8. 조회된 게시글 BoardDTO board 반환
		return board;
		
		
	}
	
	// 게시글 삽입하기
	public int insertBoard(BoardDTO board) {
		
		// 1. 삽입 결과 변수 선언
		int insertResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, NULL, SYSDATE)";
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setString(1, board.getTitle());   // 1번째 물음표(?)에 title 전달하기
			ps.setString(2, board.getContent()); // 2번째 물음표(?)에 content 전달하기
			
			// 6. PreparedStatement 객체를 이용해 쿼리문 실행(INSERT문 실행은 executeUpdate 메소드로 한다.)
			insertResult = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			// 예외 발생 여부와 상관 없이 항상 자원의 반납을 해야 한다.
			close();
		}
		// 7. 삽입 결과 반환
		return insertResult;

		
		
	}
	
	// 게시글 수정하기
	public int updateBoard(BoardDTO board) {
		
		// 1. 수정 결과 변수 선언
		int updateResult = 0;
		
		try {
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, MODIFIED_DATE = SYSDATE WHERE BOARD_NO = ?";
			// 물음표 변수 처리
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setString(1, board.getTitle()); 		//  1번째 물음표에 title 전달하기
			ps.setString(2, board.getContent()); 	//  2번째 물음표에 title 전달하기
			ps.setInt(3, board.getBoard_no());
		
			// 6. PreparedStatement 객체를 이용해 쿼리문실행(UPDATE문 실행은 executeUpdate
			updateResult = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(); // 자원의 반납을 파이널리에서 하기.
		}
		
		return updateResult;
	}
	
	// 게시글 삭제하기
	public int deleteBoard(int board_no) {
		
		// 1. 삭제 결과 변수 선언
		int deleteResult = 0;
		
		try {
			
			// 2. DataSource로부터 Connection 얻어 오기
			con = dataSource.getConnection();
			
			// 3. 실행할 쿼리문
			sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
			// 물음표 변수 처리
			
			// 4. 쿼리문을 실행할 PreparedStatement 객체 생성
			ps = con.prepareStatement(sql);
			
			// 5. 쿼리문에 변수 값 전달하기
			ps.setInt(1, board_no);
		
			// 6. PreparedStatement 객체를 이용해 쿼리문실행(DELETE문 실행은 
			deleteResult = ps.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(); // 자원의 반납을 파이널리에서 하기.
		}
		
		// 7. 삭제 결과 반환
		return deleteResult;
	}
	
	
	
}
