package tistory.edit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import tistory.edit.service.tokenService;
import tistory.edit.token.Token;

@Controller
@RequestMapping (value = "/**/tistory/api/")
@SuppressWarnings("unchecked")
public class tokenController {
	
	@Autowired
	tokenService tokenService;
	@Autowired
	Token token;

	@RequestMapping(value = "getAuthCode.do", method = {RequestMethod.GET})
	public @ResponseBody Map<String, Object> getAuthCode(Model model) {
		return tokenService.getAuthCode(model);
	}
	
	@RequestMapping(value = "getAccessToken.do", method = {RequestMethod.POST})
	public @ResponseBody Map getAccessToken(@RequestBody Map param) {
		return tokenService.getAccessToken(param);
	}
	
	@RequestMapping(value = "getTokenValid.do", method = {RequestMethod.GET})
	public @ResponseBody Map getTokenValid() {
		Map param = new HashMap<String, Object>();
		param.put("token_valid", token.getToken_valid());
		return param;
	}
	
	
}
