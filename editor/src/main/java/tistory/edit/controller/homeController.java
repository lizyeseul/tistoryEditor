package tistory.edit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tistory.edit.service.homeService;


@Controller
@RequestMapping (value = "/**/tistory/api")
public class homeController {
	@Autowired
	private homeService homeService;
	
	@GetMapping("/")
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "refreshDB.do", method = {RequestMethod.POST})
	public @ResponseBody Map getPostDetail(@RequestBody Map param) {
		return homeService.refreshDB(param);
	}
}
