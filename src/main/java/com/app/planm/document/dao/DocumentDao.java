package com.app.planm.document.dao;

import java.util.List;

import com.app.planm.document.vo.DocumentDTO;
import com.app.planm.document.vo.DocumentVO;

public interface DocumentDao {

	public List<DocumentVO> getDocumentList(DocumentDTO documentDTO) throws Exception;
	
	public DocumentVO getDocumentInfo(DocumentDTO documentDTO) throws Exception;
	
	public List<DocumentVO> getDocumentSignList(DocumentDTO documentDTO) throws Exception;
	
	public List<DocumentVO> getDocumentAllList(DocumentDTO documentDTO) throws Exception;
	
	public DocumentVO getUserLeave(DocumentDTO documentDTO) throws Exception;
	
	public String getMaxDocNo() throws Exception;
	
	public void saveDoc(DocumentDTO documentDTO) throws Exception;
	
	public void saveDocLeave(DocumentDTO documentDTO) throws Exception;
	
	public void saveDocSign(DocumentDTO documentDTO) throws Exception;
	
	public List<String> getHoliday() throws Exception;

	public String getUserAutoSign(DocumentDTO documentDTO) throws Exception;
	
	public List<DocumentVO> getSignUser(DocumentDTO documentDTO) throws Exception;
	
}
