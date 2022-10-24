package tistory.edit.api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class blogInfoApi implements tistoryApi {

	public static final String resource = "blog/info";
	private connection connect;
	private JSONObject content;
	
	private String output = "json";
	
	public blogInfoApi() {
		connect = new connection("GET", resource);
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
