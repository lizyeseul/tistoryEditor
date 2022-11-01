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
public class postListApi implements tistoryApi {
	@Autowired
	private connectionApi connect;
	@Autowired 
	apiUtils apiUtils;

	public static final String resource = "post/list";
	private Boolean hasContent = false;
	private List<String> paramList = Arrays.asList("blogName", "page");
	
	private JSONObject responseBody;
	private Map<String, String> param = new HashMap<>();
	
	private List<Map<String, Object>> postList = new ArrayList();
	private int page, count, totalCount, totalPage;
	
	
	public postListApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		if(MapUtils.getIntValue(parameter, "page") != this.getPage()) {
			hasContent = false;
		}
		if(hasContent == false) {
			if(apiUtils.validParam(paramList, parameter) == false) {
				return false;
			}
			else {
				param.putAll(parameter);
				this.setPage(MapUtils.getIntValue(parameter, "page"));
				
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
		if(MapUtils.getIntValue(item, "page") != this.getPage()) {
			return false;
		}
		this.setCount(MapUtils.getIntValue(item, "count"));
		this.setTotalCount(MapUtils.getIntValue(item, "totalCount"));
		int tp = this.getTotalCount() / this.getCount(); 
		if(this.getTotalCount() % this.getCount() != 0) {
			tp ++;
		}
		this.setTotalPage(tp);
		
		JSONArray posts = (JSONArray) item.get("posts");
		postList.clear();
		for(Object post : posts) {
			postList.add(parsePost((JSONObject)post));
		}
		if(postList.size() > 0) {
			return true;
		}
		return false;
	}
	
	private Map<String, Object> parsePost(JSONObject post) {
		Map<String, Object> content = null;
		try {
			content = new ObjectMapper().readValue(post.toJSONString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
