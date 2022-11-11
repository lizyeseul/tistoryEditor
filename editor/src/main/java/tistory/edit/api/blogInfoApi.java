package tistory.edit.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.session.SqlSession;
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
	@Autowired
	private SqlSession sqlSession;

	public static final String resource = "blog/info";
	public static final String NAMESPACE = "blog-list";
	
	private Map<String, String> param = new HashMap<>();
	
	public blogInfoApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		sqlSession.delete(NAMESPACE+".deleteBlogs");
		
		JSONObject main = connect.getContent("GET", resource, apiUtils.makeUrlParam(param));
		JSONObject tistory = (JSONObject) main.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		JSONArray blogs = (JSONArray) item.get("blogs");
		for(Object blog : blogs) {
			sqlSession.insert(NAMESPACE+".insertBlogList", parseBlog((JSONObject)blog));
		}
		return true;
	}
	
	private Map<String, Object> parseBlog(JSONObject blog) {
		Map<String, Object> content = null;
		try {
			content = new ObjectMapper().readValue(blog.toJSONString(), Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		content.put("blog_uuid", UUID.randomUUID().toString());
		content.putAll((Map<String, Object>) MapUtils.getMap(content, "statistics"));
		return content;
	}
}
