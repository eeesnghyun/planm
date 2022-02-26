package com.app.planm.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String admin(Model model) throws Exception {
			
		return "admin/index";
	}
	
}
