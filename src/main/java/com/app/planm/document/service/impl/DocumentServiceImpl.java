package com.app.planm.document.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.planm.document.dao.DocumentDao;
import com.app.planm.document.service.DocumentService;
import com.app.planm.document.vo.DocumentDTO;
import com.app.planm.document.vo.DocumentVO;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;
	
	@Override
	public List<DocumentVO> getDocumentList(DocumentDTO documentDTO) throws Exception {		
		return documentDao.getDocumentList(documentDTO);
	}

}
