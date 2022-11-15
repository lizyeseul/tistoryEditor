package tistory.edit.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
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
		param.put("postId", "60");
		
		postReadApi.setContent(param);
		
		String content = postReadApi.getPostContent();
		String before = MapUtils.getString(param,"before", "");
		String after = MapUtils.getString(param,"after", "");

		String result = content;
		while(result.indexOf(before) > 0) {
			result = result.replace(before, after);
		}

		JSONObject postParam = new JSONObject();
		postParam.put("blogName", param.get("blogName"));
		postParam.put("postId", param.get("postId"));
		postParam.put("title", postReadApi.getTitle());
		postParam.put("content", result);
//		try {
//			postParam.put("content", URLEncoder.encode(result, "UTF-8"));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		Boolean valid = postEditApi.setContentPOST(postParam);

		response.put("response_result", (valid==true)?"S":"E");
		return response;
	}
}
