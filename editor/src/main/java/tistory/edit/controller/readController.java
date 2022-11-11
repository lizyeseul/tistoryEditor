package tistory.edit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import tistory.edit.service.readService;

@Controller
@RequiredArgsConstructor
@RequestMapping (value = "/**/tistory/api/")
public class readController {
	@Autowired
	private readService readService;
	
	@GetMapping("getBlogList.do")
	public @ResponseBody List getBlogList() {
		return readService.getBlogList();
	}
	
	@RequestMapping(value = "getBlogInfo.do", method = {RequestMethod.POST})
	public @ResponseBody Map getBlogInfo(@RequestBody Map param) {
		return readService.getBlogInfo(param);
	}

	@RequestMapping(value = "getPostList.do", method = {RequestMethod.POST})
	public @ResponseBody Map getPostList(@RequestBody Map param) {
		return readService.getPostList(param);
	}

	@RequestMapping(value = "getPostDetail.do", method = {RequestMethod.POST})
	public @ResponseBody Map getPostDetail(@RequestBody Map param) {
		return readService.getPostDetail(param);
	}
}
