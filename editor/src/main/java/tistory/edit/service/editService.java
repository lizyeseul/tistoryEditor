package tistory.edit.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tistory.edit.api.postEditApi;
import tistory.edit.api.postReadApi;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class editService {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	postReadApi postReadApi;
	@Autowired
	postEditApi postEditApi;
	
	public static final String NAMESPACE_BLOG = "blog-list.";
	public static final String NAMESPACE_POST = "post-list.";
	public static final String NAMESPACE_CTGY = "ctgy-list.";
	
	public Map requestEditTest(Map param) {
		Map<String, Object> response = new HashMap<String, Object>();


//		param.put("blogName", sqlSession.selectOne(NAMESPACE_BLOG+"selectBlogNm", param));
//		param.put("postId", MapUtils.getString(sqlSession.selectOne(NAMESPACE_POST+"selectPostIdByUuid", param), "POST_ID", ""));
		param.put("blogName", "hellorecord98");
		List<Map<String, Object>> postList = sqlSession.selectList(NAMESPACE_POST+"selectEditList");
		Boolean valid = true;
		for(Map p : postList) {
			param.put("postId", MapUtils.getString(p, "post_id"));
			valid = editPost(param);
		}

		response.put("response_result", (valid==true)?"S":"E");
		return response;
	}
	private Boolean editPost(Map postparam) {
		postReadApi.setContent(postparam);
		
		String content = postReadApi.getPostContent();
		String before = MapUtils.getString(postparam,"before", "");
		String after = MapUtils.getString(postparam,"after", "");

		String result = content;
		while(result.indexOf(before) > 0) {
			result = result.replace(before, after);
		}

		JSONObject postParam = new JSONObject();
		postParam.put("blogName", postparam.get("blogName"));
		postParam.put("postId", postparam.get("postId"));
		postParam.put("title", postReadApi.getTitle());
		postParam.put("content", result);
		return postEditApi.setContentPOST(postParam);
	}
}
