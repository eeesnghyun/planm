package com.app.planm.menu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpSession session) {
		
		session.getAttribute("cmpCode");
		session.getAttribute("userCode");
		
		return "index";
	}
	
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(Model model) {
		model.addAttribute("msg", "권한이 없습니다.");
		model.addAttribute("url", "/document");
		
		return "common/msg";
	}
	
	@RequestMapping(value = "/{root}/{page}.load", method = {RequestMethod.GET, RequestMethod.POST})
	public String loadPage(
			Model model,
			@PathVariable("root") String root,
			@PathVariable("page") String page) {
		
		return root + "/" + page;
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login() {
		return "login";
	}
	
}
