package com.app.planm.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.planm.admin.dao.AdminDao;
import com.app.planm.admin.service.AdminService;
import com.app.planm.admin.vo.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDao adminDao;
	
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
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
	public List<HashMap<String, Object>> getPosList(AdminDTO adminDTO) throws Exception {
		return adminDao.getPosList(adminDTO);
	}
	
	
	
}
