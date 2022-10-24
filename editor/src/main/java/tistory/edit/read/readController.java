package tistory.edit.read;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tistory.edit.api.blogInfoApi;

@Controller
@RequestMapping (value = "/**/tistory/apitest/")
public class readController {
	@GetMapping("home")
	//http://localhost:8080/tistory/apitest/home
	public String home(Model model) {
		blogInfoApi api = new blogInfoApi();
		api.makeBody(null);
		api.getContent();
		model.addAttribute("name", api.getName());
		model.addAttribute("secondaryUrl", api.getUrl());
		return "home";
	}
}
