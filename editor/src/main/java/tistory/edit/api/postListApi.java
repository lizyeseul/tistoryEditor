package tistory.edit.api;

import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class postListApi implements tistoryApi {

	public static final String resource = "post/list";
	@Autowired
	private connectionApi connect;
	private JSONObject content;
	
	private String output = "json";
	
	private List<String> paramList = Arrays.asList("blogName", "page");
	
	public postListApi() {
	}
	
	@Override
	public void setConnection() {
		connect.setApiConnection("GET", resource);
	}
	
	@Override
	public Boolean validateParam(JSONObject param) { //파라미터 없음
		return true;
	}

	@Override
	public void makeBody(JSONObject param) {
		if(!validateParam(param)) {
			return;
		}
		connect.addUrl("output", output);
		for(Object key :  param.keySet()) {
			String value = (String) param.get(key);
			connect.addUrl(key.toString(), value);
		}
	}

	@Override
	public void getContent() {
		content = connect.send();
		return;
	}
	
	public JSONObject getFirstBlog() {
		JSONObject main = this.content;
		JSONObject tistory = (JSONObject) main.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		JSONArray blogs = (JSONArray) item.get("blogs");
		if(blogs.isEmpty()) {
			return null;
		}
		return (JSONObject) blogs.get(0);
	}
	
	public String getUrl() {
		JSONObject blog = getFirstBlog();
		return (String) blog.get("url");
	}
	
	public String getName() {
		JSONObject blog = getFirstBlog();
		return (String) blog.get("name");
	}
}
