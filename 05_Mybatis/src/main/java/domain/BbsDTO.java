package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BbsDTO {

	private int bbsNo;
	private String title;
	private String content;
	private String modifiedDate;
	private String createdDate;
	// mybatis 때문에 가능한 카멜
}
