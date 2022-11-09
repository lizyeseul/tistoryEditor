package tistory.edit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import tistory.edit.api.postListApi;
import tistory.edit.service.readService;

@Controller
@RequiredArgsConstructor
@RequestMapping (value = "/**/tistory/api/")
public class readController {
	@Autowired
	private readService readService;
	
	@GetMapping("getBlogInfo.do")
	public @ResponseBody Map getBlogInfo() {
		return readService.getBlogInfo();
	}

	@RequestMapping(value = "getPostList.do", method = {RequestMethod.POST})
	public @ResponseBody Map getPostList(@RequestBody Map param) {
		return readService.getPostList(param);
	}

	@RequestMapping(value = "getPostDetail.do", method = {RequestMethod.POST})
	public @ResponseBody Map getPostDetail(@RequestBody Map param) {
		return readService.getPostDetail(param);
	}
	
	@GetMapping("dbTest.do")
	public @ResponseBody List doDBTest() {
		return readService.doDBTest();
	}
}
