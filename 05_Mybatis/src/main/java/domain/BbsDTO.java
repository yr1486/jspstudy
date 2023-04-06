package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 얘가 게터세터투스트링 다 들어 있으니까. @data 하나로 쓸수 있으니까. 얘를 씀
@NoArgsConstructor // 매개변수 없는 생성자 생성할때 필요
@AllArgsConstructor // 매개변수 받는 생성자 생성할때 필요

public class BbsDTO {

	private int bbsNo;
	private String title;
	private String content;
	private String modifiedDate;
	private String createdDate;
	// mybatis 때문에 가능한 카멜
}
