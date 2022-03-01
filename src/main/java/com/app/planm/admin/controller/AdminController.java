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
import com.app.planm.admin.vo.AdminVO;

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
		String cmpCode = "0000";
		
		AdminVO resultMap = adminService.getCmpInfo(cmpCode);
		
		model.addAttribute("data", resultMap);
		
		return "admin/index";
	}
	
	@RequestMapping(value = "/saveCmp", method = RequestMethod.POST)
	public String saveCmp(
			HttpSession session, Model model, AdminDTO adminDTO) throws Exception {		
		adminDTO.setCmpCode("0000");
		
		adminService.saveCmp(adminDTO);
			
		model.addAttribute("msg", "저장되었습니다.");
		model.addAttribute("url", "/admin");
		
		return "common/msg";
	}
	
	@RequestMapping(value = "/updatePos", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePos(
			HttpSession session, @RequestBody AdminDTO adminDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		adminDTO.setCmpCode("0000");
		adminService.updatePos(adminDTO);
		
		resultMap.put("code", "ok");		
		resultMap.put("posCategory", adminDTO.getPosCategory());
		
		return resultMap;
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
	
	@RequestMapping(value = "/updateDept", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateDept(
			HttpSession session, @RequestBody AdminDTO adminDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		adminDTO.setCmpCode("0000");
		adminService.updateDept(adminDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/updatePart", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updatePart(
			HttpSession session, @RequestBody AdminDTO adminDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		adminDTO.setCmpCode("0000");
		adminService.updatePart(adminDTO);
		
		resultMap.put("code", "ok");
		resultMap.put("deptCode", adminDTO.getDeptCode());
		
		return resultMap;
	}
}
