package com.app.planm.cmn.service;

import java.util.HashMap;
import java.util.List;

import com.app.planm.cmn.vo.CmnDTO;

public interface CmnService {

	public List<HashMap<String,Object>> getCodeList(CmnDTO cmnDTO) throws Exception;
	
}
