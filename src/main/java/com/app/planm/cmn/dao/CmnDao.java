package com.app.planm.cmn.dao;

import java.util.HashMap;
import java.util.List;

import com.app.planm.cmn.vo.CmnDTO;

public interface CmnDao {

	public List<HashMap<String,Object>> getCodeList(CmnDTO cmnDTO) throws Exception;
	 
}
