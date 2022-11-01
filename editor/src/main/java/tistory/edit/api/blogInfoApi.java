package tistory.edit.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@SuppressWarnings({"unchecked","rawtypes"})
public class blogInfoApi implements tistoryApi {
	@Autowired
	private connectionApi connect;
	@Autowired 
	apiUtils apiUtils;

	public static final String resource = "blog/info";
	private Boolean hasContent = false;
	
	private JSONObject responseBody;
	private Map<String, String> param = new HashMap<>();
	
	private Map<String, Object> blogList = new HashMap<>();
	private String defaultBlogName;
	
	public blogInfoApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		if(hasContent == false) {
			responseBody = connect.getContent("GET", resource, apiUtils.makeUrlParam(param));
			hasContent = parseContent();
		}
		return true;
	}
	
	
	private Boolean parseContent() {
		JSONObject main = this.responseBody;
		JSONObject tistory = (JSONObject) main.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		JSONArray blogs = (JSONArray) item.get("blogs");
		for(Object blog : blogs) {
			Map<String, Object> b = parseBlog((JSONObject)blog);
			blogList.put(MapUtils.getString(b, "name"), b);
			if("Y".equals(MapUtils.getString(b, "default", ""))) {
				defaultBlogName = MapUtils.getString(b, "name");
			}
		}
		if(blogList.size() > 0) {
			return true;
		}
		return false;
	}
	
	private Map<String, Object> parseBlog(JSONObject blog) {
		Map<String, Object> content = null;
		try {
			content = new ObjectMapper().readValue(blog.toJSONString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> stat = (Map<String, Object>) MapUtils.getMap(content, "statistics");
		content.put("post", MapUtils.getInteger(stat, "post", 0));
		content.put("post", MapUtils.getInteger(stat, "comment", 0));
		content.put("post", MapUtils.getInteger(stat, "trackback", 0));
		content.put("post", MapUtils.getInteger(stat, "guestbook", 0));
		content.put("post", MapUtils.getInteger(stat, "invitation", 0));
		return content;
	}


	public Map getBlogList() {
		return blogList;
	}

	public String getDefaultBlogName() {
		return defaultBlogName;
	}
	
	public Map getDefaultBlogInfo() {
		if(defaultBlogName == null) {
			return null;
		}
		return MapUtils.getMap(blogList, defaultBlogName);
	}
}
