package com.app.planm.cmn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.planm.cmn.service.CmnService;
import com.app.planm.cmn.vo.CmnDTO;

@Controller
public class CmnController {

	private final CmnService cmnService;
	
	@Autowired
	public CmnController(CmnService cmnService) {
		this.cmnService = cmnService;
	}
	
	@RequestMapping(value = "/cmn/getCodeList", method = RequestMethod.POST)	
	public @ResponseBody Map<String, Object> getCodeList(@RequestBody CmnDTO cmnDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> resultList = cmnService.getCodeList(cmnDTO);
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}
}
