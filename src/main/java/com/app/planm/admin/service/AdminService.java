package com.app.planm.admin.service;

import java.util.HashMap;
import java.util.List;

import com.app.planm.admin.vo.AdminDTO;

public interface AdminService {

	public HashMap<String, Object> getCmpInfo(String cmpCode) throws Exception;
	
	public List<HashMap<String,Object>> getDeptList(String cmpCode) throws Exception;
	
	public List<HashMap<String,Object>> getPartList(AdminDTO adminDTO) throws Exception;
	

}