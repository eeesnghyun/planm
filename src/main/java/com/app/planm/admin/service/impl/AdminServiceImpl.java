package com.app.planm.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.app.planm.admin.dao.AdminDao;
import com.app.planm.admin.service.AdminService;
import com.app.planm.admin.vo.AdminDTO;
import com.app.planm.admin.vo.AdminVO;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDao adminDao;
	
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public AdminVO getCmpInfo(String cmpCode) throws Exception {
		return adminDao.getCmpInfo(cmpCode);	
	}

	@Override
	public void saveCmp(AdminDTO adminDTO) throws Exception {
		adminDao.saveCmp(adminDTO);
	}

	@Override
	public void updatePos(AdminDTO adminDTO) throws Exception {
		adminDao.updatePos(adminDTO);
	}
	
	@Override
	public List<HashMap<String, Object>> getDeptList(String cmpCode) throws Exception {		
		return adminDao.getDeptList(cmpCode);
	}

	@Override
	public List<HashMap<String, Object>> getPartList(AdminDTO adminDTO) throws Exception {
		return adminDao.getPartList(adminDTO);
	}
	
	@Override
	public List<HashMap<String, Object>> getSignUserList(AdminDTO adminDTO) throws Exception {
		return adminDao.getSignUserList(adminDTO);
	}

	@Override
	public void updateDept(AdminDTO adminDTO) throws Exception {
		adminDao.updateDept(adminDTO);
	}

	@Override
	public void updatePart(AdminDTO adminDTO) throws Exception {
		adminDao.updatePart(adminDTO);
	}

	@Override
	public void saveSignUser(Map<String, Object> paramMap) throws Exception {
		String cmpCode = (String) paramMap.get("cmpCode");
		String docType = (String) paramMap.get("docType");
		String deptCode = (String) paramMap.get("deptCode");
		String data = (String) paramMap.get("data");
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(data);
		JSONArray jsonArray = (JSONArray) obj;
		
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject json = (JSONObject) jsonArray.get(i);
			
			Map<String, Object> newMap = new HashMap<String, Object>();
			newMap.put("cmpCode", cmpCode);
			newMap.put("docType", docType);
			newMap.put("deptCode", deptCode);
			newMap.put("userCode", json.get("userCode"));					
			
			adminDao.saveSignUser(newMap);	
		}		
	}
	
}
