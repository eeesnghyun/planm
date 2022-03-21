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
		String type = documentDTO.getType();
		
		if ("sign".equals(type)) {
			return documentDao.getDocumentSignList(documentDTO);
		} else {
			return documentDao.getDocumentList(documentDTO);
		}		
	}

	@Override
	public List<DocumentVO> getDocumentInfo(DocumentDTO documentDTO) throws Exception {
		String type = documentDTO.getType();
		
		if ("sign".equals(type)) {
			return documentDao.getSignDocumentInfo(documentDTO);
		} else {
			return documentDao.getDocumentInfo(documentDTO);	
		}				
	}

	@Override
	public DocumentVO getUserLeave(DocumentDTO documentDTO) throws Exception {
		return documentDao.getUserLeave(documentDTO);
	}
	
	@Override
	public void saveDoc(DocumentDTO documentDTO) throws Exception {
		String docNo = documentDao.getMaxDocNo();
		documentDTO.setDocNo(docNo);
		documentDTO.setDocStatus("0004");	//임시저장
		
		//문서 생성
		documentDao.saveDoc(documentDTO);				
	}
	
	@Override
	public void requestDoc(DocumentDTO documentDTO) throws Exception {
		String docNo = documentDao.getMaxDocNo();
		documentDTO.setDocNo(docNo);
		documentDTO.setDocStatus("0001");	//결재대기
		
		//문서 생성
		documentDao.saveDoc(documentDTO);
		
		/**
		 * 휴가신청서 등록시 결재신청서 자동 생성
		 * 0001:제증명
		 * 0002:휴가신청서
		 */
		String docType = documentDTO.getDocType();
		String[] signUser = documentDTO.getSignUser().split(",");
		
		if ("0002".equals(docType)) {
			documentDao.saveDocLeave(documentDTO);
			
			for (String userCode : signUser) {
				documentDTO.setSignUser(userCode);
				documentDao.saveDocSign(documentDTO);
			}
		}
	}

	@Override
	public List<String> getHoliday() throws Exception {
		return documentDao.getHoliday();
	}

	@Override
	public String getUserAutoSign(DocumentDTO documentDTO) throws Exception {	
		return documentDao.getUserAutoSign(documentDTO);
	}
	
	@Override
	public List<DocumentVO> getSignUser(DocumentDTO documentDTO) throws Exception {	
		return documentDao.getSignUser(documentDTO);
	}



	
}
