package tistory.edit.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tistory.edit.api.blogInfoApi;
import tistory.edit.api.postListApi;
import tistory.edit.api.postReadApi;

@Service
@SuppressWarnings("rawtypes")
public class readService {
	@Autowired
	blogInfoApi blogInfoApi;
	public Map getBlogInfo() {
		Map<String, Object> param = new HashMap<String, Object>();
		blogInfoApi.setContent(null);
		param.put("blogMap", blogInfoApi.getBlogList());
		param.put("defaultBlogName", blogInfoApi.getDefaultBlogName());
		return param;
	}
	
	@Autowired
	postListApi postListApi;
	public Map getPostList(Map param) {
		Map<String, Object> response = new HashMap<String, Object>();
		Boolean valid = postListApi.setContent(param);
		if(valid == false) {
			response.put("response_result", "E");
			return response;
		}
		response.put("postList", postListApi.getPostList());
		response.put("count", postListApi.getCount());
		response.put("totalCount", postListApi.getTotalCount());
		response.put("totalPage", postListApi.getTotalPage());
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
