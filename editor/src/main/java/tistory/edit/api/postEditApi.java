package tistory.edit.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@SuppressWarnings({"unchecked","rawtypes"})
public class postEditApi implements tistoryApi {
	@Autowired
	private connectionApi connect;
	@Autowired 
	apiUtils apiUtils;

	public static final String resource = "post/modify";
	private Boolean hasContent = false;
	private List<String> paramList = Arrays.asList("blogName", "postId", "title");
	
	private JSONObject responseBody;
	private Map<String, String> param = new HashMap<>();
	
	private List<Map<String, Object>> postList = new ArrayList();
	private String title, postContent, categoryId, date;
	
	public postEditApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		return true;
	}

	public Boolean setContentPOST(JSONObject parameter) {
		responseBody = connect.setContent("POST", resource, apiUtils.makeUrlParam(param), parameter);
		return parseContent();
	}

	private Boolean parseContent() {
		JSONObject main = this.responseBody;
		JSONObject tistory = (JSONObject) main.get("tistory");
		
		if("200".equals(tistory.get("status"))) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
