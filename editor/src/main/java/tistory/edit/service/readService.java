package tistory.edit.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import tistory.edit.api.blogInfoApi;

@Service
public class readService {
	//http://localhost:8080/tistory/apitest/home
	public String getBlogInfo(Model model) {
		blogInfoApi api = new blogInfoApi();
		api.setConnection();
		api.makeBody(null);
		api.getContent();
		model.addAttribute("name", api.getName());
		model.addAttribute("secondaryUrl", api.getUrl());
		return "home";
	}
}
