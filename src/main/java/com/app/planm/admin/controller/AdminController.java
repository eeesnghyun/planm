package com.app.planm.admin.controller;

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

import com.app.planm.admin.service.AdminService;
import com.app.planm.admin.vo.AdminDTO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;
	
	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String admin(Model model) throws Exception {
			
		return "admin/index";
	}
	
	@RequestMapping(value = "/getDeptList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDeptList(HttpSession session) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		String cmpCode = "0000";
		
		List<HashMap<String,Object>> resultList = adminService.getDeptList(cmpCode);
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/getPartList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPartList(HttpSession session, @RequestBody AdminDTO adminDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		adminDTO.setCmpCode("0000");
		
		List<HashMap<String,Object>> resultList = adminService.getPartList(adminDTO);
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}	
	
	@RequestMapping(value = "/getPosList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getPosList(@RequestBody AdminDTO adminDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();						
		
		List<HashMap<String,Object>> resultList = adminService.getPosList(adminDTO);
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}	
}
