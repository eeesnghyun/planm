package com.app.planm.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.planm.admin.vo.AdminDTO;
import com.app.planm.admin.vo.AdminVO;

public interface AdminService {

	public AdminVO getCmpInfo(String cmpCode) throws Exception;
	
	public void saveCmp(AdminDTO adminDTO) throws Exception;
	
	public void updatePos(AdminDTO adminDTO) throws Exception;
	
	public List<HashMap<String,Object>> getDeptList(String cmpCode) throws Exception;
	
	public List<HashMap<String,Object>> getPartList(AdminDTO adminDTO) throws Exception;
	
	public String getAutoSign(AdminDTO adminDTO) throws Exception;
	
	public List<HashMap<String,Object>> getUserList(AdminDTO adminDTO) throws Exception;
		
	public List<HashMap<String,Object>> getSignUserList(AdminDTO adminDTO) throws Exception;
	
	public void updateDept(AdminDTO adminDTO) throws Exception;
	
	public void updatePart(AdminDTO adminDTO) throws Exception;
	
	public void saveSignUser(Map<String, Object> paramMap) throws Exception;
	
	public void saveCompanySign(Map<String, Object> paramMap) throws Exception;
	
}
