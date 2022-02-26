package com.app.planm.document.controller;

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

import com.app.planm.document.service.DocumentService;
import com.app.planm.document.vo.DocumentDTO;
import com.app.planm.document.vo.DocumentVO;
import com.app.planm.user.service.UserService;
import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

@Controller
@RequestMapping("/document")
public class DocumentController {
	
	private final DocumentService documentService;		
	private final UserService userService;
	
	@Autowired
	public DocumentController(
			DocumentService documentService,
			UserService userService) {
		this.documentService = documentService;
		this.userService = userService;
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String document(Model model) throws Exception {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setCmpCode("0000");
		documentDTO.setUserCode("lsh");
		documentDTO.setDocStatus("all");
		
		List<DocumentVO> resultList = documentService.getDocumentList(documentDTO);		
		
		model.addAttribute("resultList", resultList);
		
		return "document/index";
	}
	
	@RequestMapping(value = "/getDocList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDocList(HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode("0000");
		documentDTO.setUserCode("lsh");
		
		List<DocumentVO> resultList = documentService.getDocumentList(documentDTO);		
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/getUserLeave", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserLeave(HttpSession session) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setCmpCode("0000");
		documentDTO.setUserCode("lsh");
		
		DocumentVO documentVO = documentService.getUserLeave(documentDTO);		
		
		resultMap.put("code", "ok");
		resultMap.put("result", documentVO);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createDoc(Model model) throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setCmpCode("0000");
		userDTO.setUserCode("lsh");
		
		UserVO userVO = userService.getUserInfo(userDTO);
		
		List<String> holiday = documentService.getHoliday();
		
		model.addAttribute("userVO", userVO);
		model.addAttribute("holiday", holiday);
		
		return "document/create";
	}
	
	@RequestMapping(value = "/saveDoc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveDoc(Model model, @RequestBody DocumentDTO documentDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode("0000");
		documentDTO.setUserCode("lsh");
		
		documentService.saveDoc(documentDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/requestDoc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> requestDoc(Model model, @RequestBody DocumentDTO documentDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode("0000");
		documentDTO.setUserCode("lsh");
		
		documentService.requestDoc(documentDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
}
