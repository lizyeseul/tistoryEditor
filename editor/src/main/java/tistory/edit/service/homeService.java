package tistory.edit.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tistory.edit.api.CtgyListApi;
import tistory.edit.api.blogInfoApi;
import tistory.edit.api.postListApi;
import tistory.edit.token.Token;

@Service
public class homeService {
	@Autowired
	Token token;
	@Autowired
	blogInfoApi blogInfoApi;
	@Autowired
	postListApi postListApi;
	@Autowired
	CtgyListApi ctgyListApi;
	public Map refreshDB(Map param) {
		Map<String, Object> response = new HashMap<String, Object>();
		if(token.getToken_valid() == false) {
			response.put("response_result", "E");
			return response;
		}

		if("info".equals(MapUtils.getString(param,"tab"))) {
			//1.blog list
			blogInfoApi.setContent(null);
			//2.category list
			ctgyListApi.setContent(null);
		}
		else if("list".equals(MapUtils.getString(param,"tab"))) {
			//3.post list
			postListApi.setContent(param);
		}
		response.put("response_result", "S");
		return response;
	}
}
