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

import com.app.planm.document.service.DocumentService;
import com.app.planm.document.vo.DocumentDTO;
import com.app.planm.document.vo.DocumentVO;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String document(Model model) throws Exception {
		DocumentDTO documentDTO = new DocumentDTO();
		documentDTO.setCmpCode("0000");
		
		List<DocumentVO> resultList = documentService.getDocumentList(documentDTO);		
		
		model.addAttribute("resultList", resultList);
		
		return "document/index";
	}
	
	@RequestMapping(value = "/getDocList", method = RequestMethod.POST)
	public Map<String, Object> getDocList(HttpSession session, @RequestBody DocumentDTO documentDTO) throws Exception {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		documentDTO.setCmpCode("0000");
		
		List<DocumentVO> resultList = documentService.getDocumentList(documentDTO);		
		
		return resultMap;
	}
	
}
