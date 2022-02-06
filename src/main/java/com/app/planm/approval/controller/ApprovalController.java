package com.app.planm.approval.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/approval")
public class ApprovalController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model) {
		
		
		
		return "approval/index";
	}
	
}
