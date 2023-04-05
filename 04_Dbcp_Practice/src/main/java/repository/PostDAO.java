package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import domain.PostVO;

public class PostDAO {
	
	private Connection con; // 데이터베이스연결
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql = ""; // 빈문자열에 플러스 시켜서 넣으려고.
	
	private DataSource dataSource;// 커넥션풀 관리하는애

	// singleton
	private static PostDAO dao = new PostDAO();
	private PostDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle11g");
		}catch(Exception e) {
			e.printStackTrace(); // 이거 생략하면 오류나 
		}
		
	}
	public static PostDAO getInstance() {
		return dao;
	}
	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<PostVO> getAllPosts() throws Exception {
		List<PostVO> posts = new ArrayList<PostVO>();
		con = dataSource.getConnection();
		sql = "SELECT POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE";
		sql += " FROM POST";
		sql += " ORDER BY POST_NO DESC";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			PostVO post = PostVO.builder()
							.post_no(rs.getInt(1)) 			// setPost_no(rs.getInt(1))과 같은 역할이다.
							.writer(rs.getString(2))		// 세터 쓰는거보다 이게빠르다. 메소드 체이닝이 가능하다.
							.title(rs.getString(3))
							.content(rs.getString(4))
							.ip(rs.getString(5))
							.modified_date(rs.getDate(6))
							.created_date(rs.getDate(7))
							.build(); // 메소드 이름과 필드이름 똑같이 맞춘 패턴
			posts.add(post);
			// 객체를 만들어서. 집어넣기까지.
		}
		close();
		return posts; // 어레이리스트 반환
	}
	
	public int savePost(PostVO post) throws Exception {
		con = dataSource.getConnection();
		sql = "INSERT INTO POST(POST_NO, WRITER, TITLE, CONTENT, IP, MODIFIED_DATE, CREATED_DATE)";
		sql += "VALUES(POST_SEQ.NEXTVAL, ?, ?, ?, ?, NULL, SYSDATE)";
		ps = con.prepareStatement(sql);
		ps.setString(1, post.getWriter());
		ps.setString(2, post.getTitle());
		ps.setString(3, post.getContent());
		ps.setString(4, post.getIp());
		int saveResult = ps.executeUpdate();
		close();
		return saveResult;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
