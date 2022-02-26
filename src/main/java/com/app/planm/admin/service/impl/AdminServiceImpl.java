package com.app.planm.admin.service.impl;

import org.springframework.stereotype.Service;

import com.app.planm.admin.dao.AdminDao;
import com.app.planm.admin.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	private final AdminDao adminDao;
	
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	
	
}
