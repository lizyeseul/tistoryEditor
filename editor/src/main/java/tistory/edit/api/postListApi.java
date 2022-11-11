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
public class postListApi implements tistoryApi {
	@Autowired
	private connectionApi connect;
	@Autowired 
	apiUtils apiUtils;
	@Autowired
	private SqlSession sqlSession;

	public static final String resource = "post/list";
	public static final String NAMESPACE = "post-list";
	private List<String> paramList = Arrays.asList("blogName");
	
	private Map<String, String> param = new HashMap<>();
	
	public postListApi() {
		param.put("output", "json");
	}
	
	@Override
	public Boolean setContent(Map parameter) {
		parameter.put("blogName", sqlSession.selectOne("blog-list.selectBlogNm", parameter));
		if(apiUtils.validParam(paramList, parameter) == false) {
			return false;
		}
		else {
			apiUtils.addParam(paramList, this.param, parameter);
		}
		sqlSession.delete(NAMESPACE+".deletePosts", parameter);

		param.put("page", Integer.toString(1));
		JSONObject main = connect.getContent("GET", resource, apiUtils.makeUrlParam(param));
		JSONObject tistory = (JSONObject) main.get("tistory");
		JSONObject item = (JSONObject) tistory.get("item");
		
		int count = MapUtils.getIntValue(item, "count");
		int totalCount = MapUtils.getIntValue(item, "totalCount");
		int totalPage = (totalCount-1) / count + 1; 
		insertPost(item,parameter);
		
		for(int i=2; i<=totalPage; i++) {
			param.put("page", Integer.toString(i));
			main = connect.getContent("GET", resource, apiUtils.makeUrlParam(param));
			tistory = (JSONObject) main.get("tistory");
			item = (JSONObject) tistory.get("item");
			insertPost(item, parameter);
		}
		return true;
	}
	
	private void insertPost(JSONObject item, Map parameter) {
		JSONArray posts = (JSONArray) item.get("posts");
		for(Object post : posts) {
			JSONObject tempPost = (JSONObject)post;
			Map<String, Object> content = null;
			try {
				content = new ObjectMapper().readValue(tempPost.toJSONString(), Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			content.put("post_uuid", UUID.randomUUID().toString());
			content.put("blog_uuid", MapUtils.getString(parameter, "blog_uuid"));
			sqlSession.insert(NAMESPACE+".insertPost", content);
		}
	}
}
