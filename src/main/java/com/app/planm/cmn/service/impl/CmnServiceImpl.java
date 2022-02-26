package com.app.planm.cmn.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.planm.cmn.dao.CmnDao;
import com.app.planm.cmn.service.CmnService;
import com.app.planm.cmn.vo.CmnDTO;

@Service
public class CmnServiceImpl implements CmnService {

	private final CmnDao cmnDao;
	
	public CmnServiceImpl(CmnDao cmnDao) {
		this.cmnDao = cmnDao;
	}

	@Override
	public List<HashMap<String, Object>> getCodeList(CmnDTO cmnDTO) throws Exception {		
		return cmnDao.getCodeList(cmnDTO);
	}
	
	
}
