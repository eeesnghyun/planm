package com.app.planm.document.service;

import java.util.List;

import com.app.planm.document.vo.DocumentDTO;

public interface DocumentService {

	public List<DocumentDTO> getDocumentList() throws Exception;
}
