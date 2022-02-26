package com.app.planm.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.planm.user.service.UserService;
import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
		
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String user(Model model) throws Exception {
			
		return "user/index";
	}
	
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserList(HttpSession session) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String cmpCode = "0000";
		
		List<UserVO> resultList = userService.getUserList(cmpCode);
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveUser(
			HttpSession session, @RequestBody UserDTO userDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		userDTO.setCmpCode("0000");
	
		userService.saveUser(userDTO);
		
		resultMap.put("code", "ok");
		
		return resultMap;
	}
	
}
