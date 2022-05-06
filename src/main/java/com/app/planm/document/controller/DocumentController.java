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
import org.springframework.web.bind.annotation.RequestParam;
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
	public String document(
			HttpSession session, Model model) throws Exception {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		documentDTO.setDocStatus("all");
		
		List<DocumentVO> resultList = documentService.getDocumentList(documentDTO);		
		
		model.addAttribute("resultList", resultList);
		
		return "document/index";
	}
	
	@RequestMapping(value = "/getDocList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getDocList(
			HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		List<DocumentVO> resultList = documentService.getDocumentList(documentDTO);		
		
		resultMap.put("code", "ok");
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getDocInfo(
			HttpSession session, Model model,
			@RequestParam String type,
			@RequestParam String docNo) throws Exception {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		documentDTO.setDocNo(docNo);		
		
		DocumentVO docInfo = documentService.getSignDocumentInfo(documentDTO);
		List<DocumentVO> signList = documentService.getDocumentSign(documentDTO);	
			
		model.addAttribute("type"    , type);
		model.addAttribute("docType" , docInfo.getDocType());
		model.addAttribute("docInfo" , docInfo);		
		model.addAttribute("signList", signList);
		
		return "document/docInfo";
	}
	
	@RequestMapping(value = "/getUserLeave", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUserLeave(HttpSession session) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		DocumentVO documentVO = documentService.getUserLeave(documentDTO);		
		
		resultMap.put("code", "ok");
		resultMap.put("result", documentVO);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createDoc(
			HttpSession session, Model model) throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		userDTO.setUserCode((String) session.getAttribute("userCode"));
		
		UserVO userVO = userService.getUserInfo(userDTO);
		
		List<String> holiday = documentService.getHoliday();
		
		model.addAttribute("userVO", userVO);
		model.addAttribute("holiday", holiday);
		
		return "document/create";
	}
	
	@RequestMapping(value = "/saveDoc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> saveDoc(
			Model model, HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		documentService.saveDoc(documentDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/requestDoc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> requestDoc(
			Model model, HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		documentService.requestDoc(documentDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/getSignUser", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getSignUser(
			HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		String autoSign = documentService.getUserAutoSign(documentDTO);
		List<DocumentVO> resultList = documentService.getSignUser(documentDTO);		
		
		resultMap.put("code", "ok");
		resultMap.put("autoSign", autoSign);
		resultMap.put("resultList", resultList);
		
		return resultMap;
	}
	
	@RequestMapping(value = "/signDoc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateDocSign(
			Model model, HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		documentService.updateDocSign(documentDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
	@RequestMapping(value = "/returnDoc", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> updateDocReturn(
			Model model, HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode((String) session.getAttribute("cmpCode"));
		documentDTO.setUserCode((String) session.getAttribute("userCode"));
		
		documentService.updateDocReturn(documentDTO);
		
		resultMap.put("code", "ok");		
		
		return resultMap;
	}
	
	
}
