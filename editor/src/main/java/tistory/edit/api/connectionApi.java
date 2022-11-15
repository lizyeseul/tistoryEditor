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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tistory.edit.config.AppConst;
import tistory.edit.token.Token;
@Component
//@Scope(value = "request")
public class connectionApi {
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	private String requestType;
	private JSONObject body;
	private String  serviceUrl;
	private String  urlParam;
	
	@Autowired 
	AppConst appConst;
	@Autowired 
	Token token;
	
	public connectionApi() {
		
	}
	
	public HttpURLConnection getConnection() {
		HttpURLConnection conn = null;
		try {
//			log.info("\nurl   : "+this.serviceUrl+this.urlParam);
			URL url = new URL(this.serviceUrl+this.urlParam);
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
	
	public JSONObject getContent(String type, String api, String param) {
		setApiConnection(type, api, param);
		return send();
	}
	
	public JSONObject setContent(String type, String api, String param, JSONObject body) {
		this.body = body;
		setApiConnection(type, api, param);
		return send();
	}

	private void setApiConnection(String type, String api, String param) {
		this.requestType = type;
		this.serviceUrl = appConst.getBaseUrl()
						+api+"?"
						+ "access_token="+token.getAccess_token();
		this.urlParam = param;
	}
	
	private JSONObject send() {
		HttpURLConnection connection = this.getConnection();
		BufferedWriter bw;
		JSONObject response = null;
		try {
			if("POST".equals(this.requestType)) {
				bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
				bw.write(this.body.toJSONString()); // 버퍼에 담기
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
			
//			log.info("\nresult : "+sb.toString());
			
			JSONParser jsonParser = new JSONParser();
			response = (JSONObject) jsonParser.parse(sb.toString()); // json으로 변경 (역직렬화)
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
}
