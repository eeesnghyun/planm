package com.app.planm.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.planm.admin.dao.AdminDao;
import com.app.planm.admin.service.AdminService;
import com.app.planm.admin.vo.AdminDTO;
import com.app.planm.common.util.CommonUtil;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDao adminDao;
	
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public HashMap<String, Object> getCmpInfo(String cmpCode) throws Exception {
		HashMap<String, Object> map = adminDao.getCmpInfo(cmpCode);
		
		String email = CommonUtil.null2Str(map.get("email"), "");
		
		if ("".equals(email)) {
			map.put("email", "");
			map.put("emailServer", "");			
		} else {
			String[] fullEmail = email.split("@");
			map.put("email", fullEmail[0]);
			map.put("emailServer", fullEmail[1]);
		}
		
		return map;
	}

	@Override
	public void saveCmp(AdminDTO adminDTO) throws Exception {
		adminDao.saveCmp(adminDTO);
	}
	
	@Override
	public List<HashMap<String, Object>> getDeptList(String cmpCode) throws Exception {		
		return adminDao.getDeptList(cmpCode);
	}

	@Override
	public List<HashMap<String, Object>> getPartList(AdminDTO adminDTO) throws Exception {
		return adminDao.getPartList(adminDTO);
	}

	
}
