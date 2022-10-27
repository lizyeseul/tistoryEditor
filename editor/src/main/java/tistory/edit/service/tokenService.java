package tistory.edit.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.util.Strings;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tistory.edit.AppConst;

@Service
public class tokenService {
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String response_type="code";
	private String base_url="https://www.tistory.com/oauth/";
	
	@Autowired
	private AppConst tistoryConst;
	
	private String getTokenCodeUrl() {
		this.client_id = tistoryConst.getClientId();
		this.redirect_uri = tistoryConst.getRedirectUri();
		
		if(Strings.isEmpty(client_id) || Strings.isEmpty(redirect_uri)) {
			return "";
		}
		
		String requestUrl	= base_url+"authorize"
							+"?client_id="+client_id
							+"&redirect_uri="+redirect_uri
							+"&response_type="+response_type;
		return requestUrl;
	}
	
	public Map getAuthCode(Model model) {
		HttpURLConnection conn = null;
		Map<String,Object> result = new HashMap<>();
		result.put("url", getTokenCodeUrl());
		return result;
	}

	
	private String getAccessTokenUrl(String code) {
		this.client_id = tistoryConst.getClientId();
		this.redirect_uri = tistoryConst.getRedirectUri();
		this.client_secret = tistoryConst.getSecretKey();
//		this.redirect_uri = tistoryConst.getHomeUrl()+"tokenCode.do";
		
		if(Strings.isEmpty(client_id) || Strings.isEmpty(redirect_uri)) {
			return "";
		}
		
		String requestUrl	= base_url+"access_token"
							+"?client_id="+client_id
							+"&client_secret="+client_secret
							+"&redirect_uri="+redirect_uri
							+"&code="+code
							+"&grant_type=authorization_code";
		return requestUrl;
	}
	
	public Map getAccessToken(Map param) {
		String url = (String) param.get("url");
		if(url == null) {
			return new HashMap<>();
		}
		String code = "";
		Pattern pattern = Pattern.compile("code=([^&]*)");
		Matcher matcher = pattern.matcher(url);
		if (matcher.find())
		{
			code = matcher.group(1);
		}
		
		HttpURLConnection conn = null;
		JSONObject response = null;
		try {
//			log.info(serviceUrl);
			URL getTokenUrl = new URL(getAccessTokenUrl(code));
			conn = (HttpURLConnection) getTokenUrl.openConnection();
			conn.setRequestMethod("GET"); // http 메서드
			conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
			conn.setDoInput(true); // 서버에 전달할 값이 있다면 true
			conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

			// 토큰 시간
			
			
			// 서버로부터 데이터 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
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
