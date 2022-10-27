package tistory.edit.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tistory.edit.api.blogInfoApi;

@Service
public class readService {
	@Autowired
	blogInfoApi blogInfoApi;
	//http://localhost:8080/tistory/apitest/home
	public Map getBlogInfo() {
		Map<String, Object> param = new HashMap<String, Object>();
		blogInfoApi api = blogInfoApi;
		api.setConnection();
		api.makeBody(null);
		api.getContent();
		param.put("name", api.getName());
		param.put("secondaryUrl", api.getUrl());
		return param;
	}
}
