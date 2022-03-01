package com.app.planm.admin.dao;

import java.util.HashMap;
import java.util.List;

import com.app.planm.admin.vo.AdminDTO;
import com.app.planm.admin.vo.AdminVO;

public interface AdminDao {

	public AdminVO getCmpInfo(String cmpCode) throws Exception;
	
	public void saveCmp(AdminDTO adminDTO) throws Exception;
	
	public void updatePos(AdminDTO adminDTO) throws Exception;
	
	public List<HashMap<String,Object>> getDeptList(String cmpCode) throws Exception;
	
	public List<HashMap<String,Object>> getPartList(AdminDTO adminDTO) throws Exception;
	
	
}
