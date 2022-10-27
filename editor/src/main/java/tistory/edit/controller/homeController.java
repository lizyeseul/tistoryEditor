package tistory.edit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tistory.edit.token.Token;


@Controller
@RequestMapping (value = "/**/tistory/api")
public class homeController {
	@Autowired
	Token token;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("token_valid", token.getToken_valid());
		return "home";
	}
}
