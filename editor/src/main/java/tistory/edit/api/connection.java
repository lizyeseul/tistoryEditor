package tistory.edit.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@PropertySource("classpath:application.properties")
public class connection {
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

//	@Value("${tistory.token}")
	private String token="";
//	@Value("${tistory.url}")
	private String baseUrl="https://www.tistory.com/apis/";
	
	private String requestType;
	private JSONObject response;
	private JSONObject body;
	private String  serviceUrl;
	
	public HttpURLConnection getConnection() {
		HttpURLConnection conn = null;
		try {
			log.info(serviceUrl);
			URL url = new URL(serviceUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(this.requestType); // http 메서드
			conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
			conn.setDoInput(true); // 서버에 전달할 값이 있다면 true
			conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	@SuppressWarnings("unchecked")
	public connection(String type, String api) {
		this.requestType = type;
		this.serviceUrl = this.baseUrl+api+"?"
						+ "access_token="+this.token;
		log.info("service url : "+this.serviceUrl);
	}
	
	public void addUrl(String key, String value) {
		this.serviceUrl += "&"+key
						+  "="+value;
	}
	
	public JSONObject send() {
		HttpURLConnection connection = this.getConnection();
		BufferedWriter bw;
		try {
			if("POST".equals(this.requestType)) {
				bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				bw.write(this.body.toString()); // 버퍼에 담기
				bw.flush(); // 버퍼에 담긴 데이터 전달
				bw.close();
			}

			// 서버로부터 데이터 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
				sb.append(line);
			}
			JSONParser jsonParser = new JSONParser();
			response = (JSONObject) jsonParser.parse(sb.toString()); // json으로 변경 (역직렬화)
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
