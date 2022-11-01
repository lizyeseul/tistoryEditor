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
public class postReadApi implements tistoryApi {
	@Autowired
	private connectionApi connect;
	@Autowired 
	apiUtils apiUtils;

	public static final String resource = "post/read";
	private Boolean hasContent = false;
	private List<String> paramList = Arrays.asList("blogName", "postId");
	
	private JSONObject responseBody;
	private Map<String, String> param = new HashMap<>();
	
	private List<Map<String, Object>> postList = new ArrayList();
	private String title, postContent, categoryId, date;
	
	public postReadApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		if(hasContent == false) {
			if(apiUtils.validParam(paramList, parameter) == false) {
				return false;
			}
			else {
				param.putAll(parameter);
				
			}
			responseBody = connect.getContent("GET", resource, apiUtils.makeUrlParam(param));
			hasContent = parseContent();
		}
		return true;
	}

	private Boolean parseContent() {
		JSONObject main = this.responseBody;
		JSONObject tistory = (JSONObject) main.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		
		this.setTitle(MapUtils.getString(item, "title"));
		this.setPostContent(MapUtils.getString(item, "content"));
		this.setCategoryId(MapUtils.getString(item, "categoryId"));
		this.setDate(MapUtils.getString(item, "date"));
		
		return false;
	}
	
}
