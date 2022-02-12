package com.app.planm.document.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.planm.document.service.DocumentService;
import com.app.planm.document.vo.DocumentVO;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String document(Model model) throws Exception {		
		List<DocumentVO> resultList = documentService.getDocumentList();		
		
		model.addAttribute("resultList", resultList);
		
		return "document/index";
	}
	
}
