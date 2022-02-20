package com.app.planm.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.planm.user.dao.UserDao;
import com.app.planm.user.service.UserService;
import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public UserVO getUserInfo(UserDTO userDTO) throws Exception {
		return userDao.getUserInfo(userDTO);
	}
	
}
