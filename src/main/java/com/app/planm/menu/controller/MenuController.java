package com.app.planm.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		
		return "index";
	}
	
	@RequestMapping(value = "/{root}/{page}.load", method = {RequestMethod.GET, RequestMethod.POST})
	public String loadPage(
			Model model,
			@PathVariable("root") String root,
			@PathVariable("page") String page) {
		
		return root + "/" + page;
	}
	
}
