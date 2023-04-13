package com.gdu.ex.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionForward {
	private String path;  // Service 실행 후 이동할 경로를 저장합니다. forward는 jsp이름을 작성하고, redirect는 mapping을 작성합니다.
	private boolean isRedirect;  // 이동 방식을 저장합니다. forward는 false이고, redirect는 true입니다. boolean 타입의 필드는 디폴트 값으로 false 값을 가지므로 기본 이동 방식은 forward라는 의미가 코드에 나타나 있습니다.

	// 액션타입의 기본값은 포워드 = 펄스. 

// 포워드 : 전달. jsp이름. 
// 리다이렉트: 맵핑
}

// 전달할게 하나가 아니라 두개니까. 두개 이상이면 객체로 만들어서 전달하는거임. 그게 액션포워드가 된거고
// @data 롬복을 써서 이게 게터세터가 있으니까. 게터세터로 꺼내고 저장하는 거임
// 하나만 쓰면 변수에 저장해서 하면되는데. 지금은 전달할게 경로랑 머시기니까. 객체를 쓰는거임
