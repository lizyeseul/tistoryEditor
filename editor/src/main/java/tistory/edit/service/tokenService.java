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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tistory.edit.config.AppConst;
import tistory.edit.token.Token;

@Service
public class tokenService {
	private String client_id;
	private String client_secret;
	private String redirect_uri;
	private String response_type="code";
	private String base_url="https://www.tistory.com/oauth/";
	
	@Autowired
	private AppConst tistoryConst;
	@Autowired
	Token token;

	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
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
	
	private String extractCode(String param) {
		String code = "";
		Pattern pattern = Pattern.compile("code=([^&]*)");
		Matcher matcher = pattern.matcher(param);
		if (matcher.find())
		{
			code = matcher.group(1);
		}
		return code;
	}
	
	public Map getAccessToken(Map param) {
		Map<String, Object> result = new HashMap<>();
		if(token.getToken_valid() == true) {
			result.put("access_token", token.getAccess_token());
			result.put("token_valid", token.getToken_valid());
			return result;
		}
		
		String url = (String) param.get("url");
		if(url == null) {
			return new HashMap<>();
		}
		String code = extractCode(url);

		HttpURLConnection conn = null;
		try {
			URL getTokenUrl = new URL(getAccessTokenUrl(code));
			conn = (HttpURLConnection) getTokenUrl.openConnection();
			conn.setRequestMethod("GET"); // http 메서드
			conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
			conn.setDoInput(true); // 서버에 전달할 값이 있다면 true
			conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true

			// 토큰 시간
			
			if(conn.getResponseCode() == 200) {
				// 서버로부터 데이터 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuilder sb = new StringBuilder();
				String line = null;
				
				while((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
					sb.append(line);
				}

				String access_token = "";
				Pattern pattern = Pattern.compile("access_token=([^&]*)");
				Matcher matcher = pattern.matcher(sb.toString());
				if (matcher.find())
				{
					access_token = matcher.group(1);
				}
				log.info("\naccess_token : "+access_token);
				token.setAccess_token(access_token);
				token.setToken_valid(true);
			}
			else {
				token.setToken_valid(false);
			}

//			token.setAccess_token("c8b4389e06653bfb631f89189683c128_7e013a3472851e068233a2ecd1dd6e5e");
//			token.setToken_valid(true);
			
			result.put("access_token", token.getAccess_token());
			result.put("token_valid", token.getToken_valid());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
