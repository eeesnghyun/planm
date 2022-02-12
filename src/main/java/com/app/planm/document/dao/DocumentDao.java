package com.app.planm.document.dao;

import java.util.List;

import com.app.planm.document.vo.DocumentVO;

public interface DocumentDao {

	public List<DocumentVO> getDocumentList() throws Exception;
	
}
