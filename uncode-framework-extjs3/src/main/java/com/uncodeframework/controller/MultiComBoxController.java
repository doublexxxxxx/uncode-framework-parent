package com.uncodeframework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo/*")
public class MultiComBoxController {

	@RequestMapping(value="multicombox.do")
	public @ResponseBody String multicombox() {
		String str = "[{name: 'abc', value: 1}, {name: 'def', value: 2}]";
		return str;
	}
	
	@RequestMapping(value="multicombosearch.do")
	public @ResponseBody String multicombosearch() {
		String str = "[[name: 'abc', value: 1], [name: 'def', value: 2]]";
		return str;
	}

}