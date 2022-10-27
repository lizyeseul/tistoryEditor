package tistory.edit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tistory.edit.service.tokenService;

@Controller
@RequestMapping (value = "/**/tistory/api/")
@SuppressWarnings("unchecked")
public class tokenController {
	
	@Autowired
	tokenService tokenService;
	
	@GetMapping("getAuthCode.do")
	//http://localhost:8080/tistory/api/getAuthCode.do
	public @ResponseBody Map<String, Object> getAuthCode(Model model) {
		return tokenService.getAuthCode(model);
	}
	
	@GetMapping("getAccessToken.do")
	//http://localhost:8080/tistory/api/getAccessToken.do
	public @ResponseBody Map getAccessToken(@RequestBody Map param) {
		return tokenService.getAccessToken(param);
	}
}
