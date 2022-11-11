package tistory.edit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tistory.edit.api.blogInfoApi;
import tistory.edit.api.postListApi;
import tistory.edit.api.postReadApi;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class readService {
	@Autowired
	private SqlSession sqlSession;
	
	public static final String NAMESPACE_BLOG = "blog-list";
	public static final String NAMESPACE_POST = "post-list.";
	
	public List getBlogList() {
		return (List) sqlSession.selectList(NAMESPACE_BLOG+".selectBlogList");
	}
	public Map getBlogInfo(Map param) {
		return sqlSession.selectOne(NAMESPACE_BLOG+".selectBlogInfo", param);
	}
	
	public Map getPostList(Map param) {
		Map<String, Object> response = new HashMap<String, Object>();
		List postList = sqlSession.selectList(NAMESPACE_POST+"selectPostList", param);
		response.put("postList", postList);
		response.put("totalPage", (postList.size()-1) / MapUtils.getIntValue(param, "post_per_page", 1) +1);
		return response;
	}
	
	@Autowired
	postReadApi postReadApi;
	public Map getPostDetail(Map param) {
		Map<String, Object> response = new HashMap<String, Object>();
		Boolean valid = postReadApi.setContent(param);
		if(valid == false) {
			response.put("response_result", "E");
			return response;
		}
		response.put("title", postReadApi.getTitle());
		response.put("postContent", postReadApi.getPostContent());
		response.put("categoryId", postReadApi.getCategoryId());
		response.put("date", postReadApi.getDate());
		return response;
	}
}
