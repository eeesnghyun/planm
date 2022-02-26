package com.app.planm.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.planm.common.util.SecureAlgorithm;
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
	public List<UserVO> getUserList(String cmpCode) throws Exception {
		return userDao.getUserList(cmpCode);
	}
	
	@Override
	public UserVO getUserInfo(UserDTO userDTO) throws Exception {
		return userDao.getUserInfo(userDTO);
	}

	@Override
	public void saveUser(UserDTO userDTO) throws Exception {
		//유저생성시 생년월일로 임시 패스워드(SHA256) 생성
		String tempPass = userDTO.getBirthYmd().replaceAll("-", "");
		String salt = SecureAlgorithm.getSalt();
		String encryptPass = SecureAlgorithm.encryptSha256(tempPass, salt);
		
		userDTO.setPassword(encryptPass);
		userDTO.setSalt(salt);
		
		userDao.saveUser(userDTO);
	}

	@Override
	public void updateUser(UserDTO userDTO) throws Exception {
		userDao.updateUser(userDTO);
	}
	
}
