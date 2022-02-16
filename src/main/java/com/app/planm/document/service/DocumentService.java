package com.app.planm.document.service;

import java.util.List;

import com.app.planm.document.vo.DocumentDTO;
import com.app.planm.document.vo.DocumentVO;

public interface DocumentService {

	public List<DocumentVO> getDocumentList(DocumentDTO documentDTO) throws Exception;
	
	public void saveDoc(DocumentDTO documentDTO) throws Exception;
	
}
