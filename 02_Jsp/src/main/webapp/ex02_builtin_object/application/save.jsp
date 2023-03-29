<%@page import="java.io.FileWriter"%>
<%@page import="java.io.BufferedWriter"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		// 요청 파라미터 처리
		request.setCharacterEncoding("UTF-8");
	
		String createdDate = request.getParameter("created_date");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		// 이정보들을 파일로 저장해보는 연습, 그다음이 데이터베이스로 저장해보는 연습.
	
		
		// 작성 IP를 알아낼 수 있다.
	 	String ip = request.getRemoteAddr();
				
		
		// real path
		//String realPath = request.getServletContext().getRealPath("storage");
									//얘가 ServletContext 임 == jsp에서의 이름은  application 임!!
		String realPath = application.getRealPath("storage"); //ServletContext객체가 application 내장 객체로 만들어져 있음
		
				
		// 디렉터리
		File dir = new File(realPath);
		if(dir.exists() == false){
			dir.mkdirs();
		}
		
		
		
		/*
			IPv4 :	127.0.0.1 (아이피주소가 마침표로 되어 있는걸 말함) 	=> 127_0_0_1
			IPv6 :	0:0:0:0:0:0:0:1 (파일명으로 콜론을 쓸수 없으니까)	=> 0_0_0_0_0_0_0_1
		*/
		
		// 파일명 : 작성IP_작성일.txt
		// 바꿔주는 자바 메소드1,2
		String filename = ip.replace('.', '_').replace(':', '_') + "_" + createdDate.replace("-", "") + ".txt";
		// String filename = ip.replaceAll("[.:]", "_") + "_" + createdDate.replace("-", "") + ".txt";
			// 리플레이스올은 정규식으로 표현 // 정규식 :또는 임

		// 파일 객체 만들기
		File file = new File(dir, filename);

		
		// 문자 파일 출력 스트림 생성
		BufferedWriter bw  = new BufferedWriter(new FileWriter(file));
		
		// 파일 생성
		bw.write("작성일자 :" + createdDate);
		bw.newLine(); // 줄바꾸는 메소드
		bw.write("작성자 : " + writer);
		bw.newLine();
		bw.write("제목 : " + title);
		bw.newLine();
		bw.write("내용");
		bw.newLine();
		bw.write(content);
		bw.flush();
		bw.close();
		
	%>
	
	<script>
		var isCreated = <%=file.exists()%> //var isCreated = false; var isCreated = true;
		if(isCreated){
			alert('<%=filename%> 파일이 생성되었습니다.');
		}
		else {
			alert('<%=filename%> 파일이 생성되지 않았습니다.');	
		}
		location.href = '<%=request.getContextPath()%>/ex02_builtin_object/application/write_form.jsp'; 
		// 다시 작성페이지로 돌아가는거
		history.back(); 
		// 이전 페이지로 돌아가고 작성한 내용이 남아있음.
		
		// 실행은 write_form 에서
		// 실행 누르고. 작성완료된 파일은 리얼패스에 있음.
		// 나중되면은 이 파일 말고 디비에 저장할거임!!
	</script>

</body>
</html>










