package com.app.planm.document.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.planm.document.service.DocumentService;
import com.app.planm.document.vo.DocumentDTO;

@Controller
@RequestMapping("/document")
public class DocumentController {

	private DocumentService documentService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		
		List<DocumentDTO> resultList = documentService.getDocumentList();		
		System.out.println(resultList.toString());
		model.addAttribute("result", resultList);
		
		return "document/index";
	}
	
}
