package practice07;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PapagoServlet")

public class PapagoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 요청 인코딩
		request.setCharacterEncoding("UTF-8");

		// 요청 파라미터
		// 파리미터가 3개. 소스 타겟 텍스트 파파고 화면으로부터 넘어오는거 3개
		String source = request.getParameter("source"); // 원본 언어의 코드 (ko, en, ja 중 하나)
		String target = request.getParameter("target"); // 목적 언어 코드 (ko, en, ja 중 하나)
		String text = request.getParameter("text"); // 번역할 텍스트 // 얘 유티에프팔로 번역해줘야함

		// 클라이언트 아이디, 시크릿 (네이버개발자센터에서 발급받은 본인 정보 사용하면 됨)
		String clientId = "BZew9qtT5TV33w42pBME";
		String clientSecret = "DK9yJg2hej";

		// API 주소
		// 개발자홈피에서 API레퍼런스 들어가서 요청 URL 찾기
		String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

		// URL
		URL url = new URL(apiURL);

		// 네트워크접속할떄 항상 필요한 2가지
		// HttpURLConnection

		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// 요청 메소드
		con.setRequestMethod("POST"); // 얘도 개발자페이지에서 HTTP 메서드 보고 POST쓰는거임

		// 개발자페이지에서 참고사항에 보면 나와 있음
		// API를 요청할 때 다음 예와 같이 HTTP 요청 헤더에 클라이언트 아이디와 클라이언트 시크릿을 추가해야 합니다.
		// 요청 헤더에 포함하는 내용 (API읽어보고 포함하라는 내용이 있어서 아래와 같이 3개 포함한거임)
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		con.setRequestProperty("X-Naver-Client-Id", clientId);
		con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

		// 포스트방식은 주소뒤에 주렁주렁 매다는거 안됨
		// 커넥션이라는게 네이버와 연결된 통로 클라이언트 ->con-> 파파고 // 네이버로 보내줘야하는 출력스트림 // 결과받아올때는 입력스트림
		// 바이트기반인데 스트링으로 보내주는 애. DataOutputStream !! 문자의 경우 직접 UTF로 보낼수있는애. (writeUTF 이
		// 메소드를 가지고 있음.) 근데 얘가 잘안먹어서 다시 write로쓰고 겟바이츠써서 스트링을 바이트로 바꿔줌// 복습: javastudy에
		// 있음

		// Papago API로 보내야 하는 파라미터(source, target, text)
		String params = "source=" + source + "&target=" + target + "&text=" + URLEncoder.encode(text, "UTF-8"); // 스트링으로
																												// 만들고
		// 파라미터들

		// Papago API로 파라미터를 보내기 위해서 출력 스트림 생성
		con.setDoOutput(true);
		DataOutputStream dos = new DataOutputStream(con.getOutputStream()); // 바이트별로 보내는 스트림밖에 없다

		// Papago API로 파라미터 보내기
		// 이제 보내주는애
		dos.write(params.getBytes()); // 스트링을 바이트로 바꿔서.
		// 혹시 모르니 불어주고
		dos.flush();
		// 끝내주고
		dos.close();

		// 필요한거 다보냄.. 요청이 끝난거. 이제 받아와야함

		// Papago API로부터 번역 결과를 받아 올 입력 스트림 생성하기
		BufferedReader reader = null;
		if (con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}

		// Papago API 로부터 번역 결과를 받아서 스티링빌더에 저장
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}

		// StringBuilder의 번역 결과를 client.html의 ajax으로 보내기
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(sb.toString());
		out.flush();
		out.close();

		// 네이버개발자에서 구현 예제 보고 공부해보기.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
