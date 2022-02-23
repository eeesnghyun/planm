package com.app.planm.user.service.impl;

import org.springframework.stereotype.Service;

import com.app.planm.user.dao.UserDao;
import com.app.planm.user.service.UserService;
import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
		
	@Override
	public UserVO getUserInfo(UserDTO userDTO) throws Exception {
		return userDao.getUserInfo(userDTO);
	}

	@Override
	public void saveUser(UserDTO userDTO) throws Exception {
		//패스워드 암호화 로직 추가
		userDTO.setPassword(userDTO.getBirthYmd());
		
		userDao.saveUser(userDTO);
	}
	
}
