package ex08_api;

import java.io.BufferedReader;
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


@WebServlet("/AirKoreaApiServlet")

public class AirKoreaApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		// 요청 파라미터
		String sidoName = request.getParameter("sidoname");
		String returnType = request.getParameter("returnType");

		// 서비스 키
		String serviceKey = "DLjEGtKd0aG9T74nl2maZRl/blGjHeobRhUq0XPHXjKETff59wf/SvKbgvSLtNHqpc+MlmuKNQ46v51Q46bL7g==";
		
		// 요청 주소 만들기
		String apiURL = "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		apiURL += "?serviceKey=" + URLEncoder.encode(serviceKey, "UTF-8");
		apiURL += "&sidoName=" + URLEncoder.encode(sidoName, "UTF-8");
		apiURL += "&returnType=" + URLEncoder.encode(returnType, "UTF-8");
		// +쓰면 성능이 떨어지니까.  Stringbuilder 써서 해도 됨. 요정도는 + 써도 괜찮음
		
		// URL
		URL url = new URL(apiURL); // try catch 안할꺼라서 그냥가능
		
		// HttpURLConnection
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		
		// 요청 메소드
		con.setRequestMethod("GET");
		
		// returnType에 따른 Content-Type
		con.setRequestProperty("Content-Type", "application/" + returnType + "; charset=UTF-8"); 
		
		// 입력 스트림 선언 및 생성
		BufferedReader reader = null;
		
		if(con.getResponseCode() == 200) {
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			
		}
		else {
			reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		
		//
		StringBuilder sb = new StringBuilder();
		String line = null;
		
		while((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		reader.close();
		con.disconnect();
		
		// System.out.println(sb.toString());
		
		// API  결과를   ajax  응답 처리하기
		 response.setContentType("application/" + returnType + "; charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 out.println(sb.toString());
		 out.flush();
		 out.close();
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
