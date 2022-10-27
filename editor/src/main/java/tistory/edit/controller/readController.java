package tistory.edit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import tistory.edit.api.postListApi;
import tistory.edit.service.readService;

@Controller
@RequiredArgsConstructor
@RequestMapping (value = "/**/tistory/api/")
public class readController {
	private readService readService;
	
	@GetMapping("home")
	public String home(Model model) {
		return readService.getBlogInfo(model);
	}

	@GetMapping("post/list")
	//http://localhost:8080/tistory/apitest/home
	public String postList(Model model) {
		postListApi api = new postListApi();
		api.makeBody(null);
		api.getContent();
		model.addAttribute("name", api.getName());
		model.addAttribute("secondaryUrl", api.getUrl());
		return "home";
	}
}
