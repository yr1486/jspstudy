package domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Value Object = DTO와 동급으로 씀

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString

public class PostVO {
	
	private int post_no;
	private String writer;
	private String title;
	private String content;
	private String ip;
	private Date modified_date;
	private Date created_date;
	
}
