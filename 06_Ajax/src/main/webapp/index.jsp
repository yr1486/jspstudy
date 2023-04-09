<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("contextPath", request.getContextPath());
%>


<!-- 에이작처리에서는 서브밋을 처리하지 않는다. 액션, 메소드 버튼 필요 없다는 뜻. -->
<!-- 폼안에 회원번호가 들어 있어야 함. hidden 히든은 버튼 주변에 모아두면 편함 -->
</head>
<body>

	<div class="wrap">
		<h1>회원 관리</h1>
		<form id="frm_member">
			<div>
				<label for="id">아이디</label> <input type="text" id="id" name="id">
			</div>
			<div>
				<label for="name">이름</label> <input type="text" id="name"
					name="name">
			</div>
			<div>
				<input type="radio" id="male" name="gender" value="M"> <label
					for="male">남자</label> <input type="radio" id="female" name="gender"
					value="F"> <label for="female">여자</label>
			</div>
			<!-- 반드시 밸류를 줘야. 디비에서 사용할 수 있다. -->
			<div>
				<label for="address">주소</label> <input type="text" id=address
					name="address">
			</div>

			<div>
				<input type="hidden" id="memberNo" name="memberNo"> 
				<input type="button" value="초기화" onclick="fnInit()"> 
				<input type="button" value="신규등록" onclick="fnAddMember()"> 
				<input type="button" value="변경저장" onclick="fnModifyMember()"> 
				<input type="button" value="삭제" onclick="fnRemoveMember()">
			</div>
		</form>

		<hr>

		<table border="1">
			<caption>
				전체 회원 수 : <span id="member_count"></span>명
			</caption>
			<!-- 돔 객체 필요-->
			<thead>
				<tr>
					<td>회원번호</td>
					<td>아이디</td>
					<td>이름</td>
					<td>성별</td>
					<td>주소</td>
					<td>버튼</td>
				</tr>
			</thead>
			<tbody id="member_list"></tbody>
			<!-- 돔조작을 통해서 만들거임 -->
		</table>
	</div>

	<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>

<script>

	/* 함수 호출. */
	fnInit();
	fnGetAllMember();
	
	/* 함수 정의  */
	
	function fnInit(){ // 초기화
		$('#id').val('').prop('disabled', false);
		$('#name').val('');
		$(':radio[name=gender]').prop('checked', false); // 체크없애주는
		$('#address').val('');
		
	}
	
	function fnGetAllMember() {
		$.ajax({
			// 요청
			type: 'get',
			url:'${contextPath}/list.do',
			
			// 응답
			dataType: 'json',
			success: function(resData){ // 응답 데이터는 resData로 전달된다.
				// console.log(resData);
				/*
					resData <--- out.println(obj.toString())
					resData = {
						"memberCount": 2,
						"memberList": [
							{ },
							{ }
						]
					}
				*/
				$('#member_count').text(resData.memberCount); // text(resData['memberCount']) 도 가능.
				
				let memberList = $('#member_list');
				memberList.empty(); // 기존의 회원 목록을 지운다. // 초기화. 페이지 안바꾸고 데이터만 가져오는게 에이작이라서 초기화를 하는거임
				
				if(resData.memberCount === 0) { // 회원이 없으면 == 도 가능
					memberList.append('<tr><td colspan="6">회원이 없습니다.</td></tr>');
				} else {
					/* $.each(배열, (인덱스, 요소)=>{}) 		  */
					/* $.each(배열, function(인덱스, 요소){}) */
					$.each(resData.memberList, function(i, element){ // element는 하나의 회원 객체를 의미한다.
						let str = '<tr>';
						str += '<td>' + element.memberNo + '</td>';
						str += '<td>' + element.id + '</td>';
						str += '<td>' + element.name + '</td>';
						str += '<td>' + (element.gender === 'M' ? '남자' : '여자') + '</td>';
						str += '<td>' + element.address + '</td>';
						str += '<td><input type="button" value="조회" class="btn_detail" onclick="fnGetMember('+ element.memberNo +')"></td>';
						memberList.append(str);
					})
					
				}
			}
		})
	}
	
	function fnAddMember(){
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/add.do',
			data:$('#frm_member').serialize(), // 폼의 모든 입력 요소를 '파라미터'로 전송한다. (입력 요소에는 name속성이 필요하다)
			
			
			// 응답
			// 성공하면 JSON
			dataType: 'json',
			success: function(resData){ // try문의 응답이 resData에 저장된다. resData = {"insertResult": 1}
				if(resData.insertResult === 1) {
					alert('신규 회원 등록되었습니다.');
					fnInit();		  // 입력란 초기화
					fnGetAllMember(); // 새로운 회원 목록으로 갱신하기
				}
				else {
					alert('신규 회원 등록이 실패했습니다.');
				}
				
			},
			error: function(jqXHR){ // jqXHR 객체에는 예외코드(응답코드:404, 500등)과 예외메시지 등이 저장된다.
									// catch문의 응답 코드는 jqXHR 객체의 status 속성에 저장된다.
									// catch문의 응답은 jqXHR 객체의 responseText 속성에 저장된다.
				// 응답코드도 같이 넘어오는걸 생각하기. 예외 응답코드, 예외 응답 메세지 등이니까. 객체로 넘어와야함.
				alert(jqXHR.responseText + '(' + jqXHR.status + ')');
			}
		})
		
	}
	// onclick="fnGetMember(element.memberNo)"
	// fnGetMember() 함수를 호출할 때 회원번호를(element.memberNo) 인수로 전달하면 매개변수 memberNo가 받는다.
	function fnGetMember(memberNo){ // 이게 매개변수 멤버
		$.ajax({
			// 요청
			type: 'get',
			url: '${contextPath}/detail.do',
			data: 'memberNo=' + memberNo, // 이게 파라미터 이름
			// 응답
			dataType: 'json',
			success: function(resData){ // resData = {"member": {"memberNo":회원번호, "gender": "M", ...}}
				alert('회원 정보가 조회되었습니다.');
				$('#id').val(resData.member.id).prop('disabled', true); //리스펀스데이터의 멤버프로퍼터의 아이디다.
				$('#name').val(resData.member.name);
				$(':radio[name=gender][value='+ resData.member.gender +']').prop('checked', true); // 네임=젠더 까지만 하면 젠더가 2개라서 프로퍼터 체크드 투르가 말이안됨. 그래서 밸류를 하나 더 넣어 준거
				$('#address').val(resData.member.address);
				$('#memberNo').val(resData.member.memberNo); // <input type="hidden">에 저장하는 값. 히든이라서 눈에 보이지 않음 하지만 분명히 폼에 넣어놨다는 거.

			}
		})
	}
	

	function fnModifyMember() {
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/modify.do',
			data: $('#frm_member').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData) { //resData = {"updateResult":1}
				if (resData.updateResult == 1) {
					alert('회원 정보가 수정되었습니다.');
					fnGetAllMember(); // 새로운 회원 정보로 갱신
				} else {
					alert('회원 정보 수정이 실패했습니다.');
				}

			}
		})

	}
	// 이름에 30 byte 이상의 데이터를 입력받았을 때..........죽지않게 .해야 할..... 처리 => MemberModifyService 가서 try-catch로

	function fnRemoveMember() {
		$.ajax({
			// 요청
			type: 'post',
			url: '${contextPath}/remove.do',
			data: $('#frm_member').serialize(),
			// 응답
			dataType: 'json',
			success: function(resData) {
				if(deleteResult === 1) {
					alert('정말로 삭제하시겠습니까?');
					fnGetAllMember();
					
				}
			}
			
		})
		
	}
</script>

	


















</body>
</html>