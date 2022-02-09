package com.app.planm.document.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.planm.document.dao.DocumentDao;
import com.app.planm.document.service.DocumentService;
import com.app.planm.document.vo.DocumentDTO;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

	private DocumentDao documentDao;
	
	@Override
	public List<DocumentDTO> getDocumentList() throws Exception {		
		return documentDao.getDocumentList();
	}

}
