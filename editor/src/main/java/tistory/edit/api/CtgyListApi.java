package tistory.edit.api;

import java.util.Arrays;
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

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@SuppressWarnings({"unchecked","rawtypes"})
public class CtgyListApi implements tistoryApi {
	@Autowired
	private connectionApi connect;
	@Autowired 
	apiUtils apiUtils;
	@Autowired
	private SqlSession sqlSession;

	public static final String resource = "category/list";
	public static final String NAMESPACE = "ctgy-list";
	private List<String> paramList = Arrays.asList("blogName");
	
	private Map<String, String> param = new HashMap<>();
	
	public CtgyListApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		List<Map> blogList = sqlSession.selectList("blog-list.selectBlogList");
		for(Map blog : blogList) {
			insertCtgy(blog);
		}
		return true;
	}

	private void insertCtgy(Map blog) {
		blog.put("blog_uuid", MapUtils.getString(blog, "BLOG_UUID"));
		sqlSession.delete(NAMESPACE+".deleteCtgys", blog);
		param.put("blogName", MapUtils.getString(blog, "BLOG_NM"));
		
		JSONObject main = connect.getContent("GET", resource, apiUtils.makeUrlParam(param));
		JSONObject tistory = (JSONObject) main.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		JSONArray categories = (JSONArray) item.get("categories");
		for(Object ctgy : categories) {
			JSONObject tempCtgy = (JSONObject)ctgy;
			Map<String, Object> content = null;
			try {
				content = new ObjectMapper().readValue(tempCtgy.toJSONString(), Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			content.put("parent", MapUtils.getInteger(content, "parent", null));
			content.put("blog_uuid", MapUtils.getString(blog, "blog_uuid"));
			content.put("ctgy_uuid", UUID.randomUUID().toString());
			sqlSession.insert(NAMESPACE+".insertCategory", content);
		}
	}
}
