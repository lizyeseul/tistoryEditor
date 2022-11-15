package tistory.edit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import tistory.edit.service.editService;

@Controller
@RequiredArgsConstructor
@RequestMapping (value = "/**/tistory/api/")
public class editController {
	@Autowired
	private editService editService;
	
	@RequestMapping (value = "/**/requestEditTest.do")
	public Map requestEditTest(@RequestBody Map param) {
		return editService.requestEditTest(param);
	}
}
