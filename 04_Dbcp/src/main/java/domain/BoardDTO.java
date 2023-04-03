package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 도메인은 '빈'을 모아 두는 곳.
// 클래스 명이 board , boardVO, boardDTO 등
// boardDTO와 board.sql은 1대1로 만들어야함. 다섯개면 다섯개 똑같이.

//2.
@NoArgsConstructor  // public BoardDTO(){} 와 같은거임.. 롬복이니까 @이로
@AllArgsConstructor // generate Constructor using filed 임
@Getter
@Setter



//1.
public class BoardDTO {
	
	private int board_no;
	private String title;
	private String content;
	private Date modified_date; 
	private Date created_date;

}
