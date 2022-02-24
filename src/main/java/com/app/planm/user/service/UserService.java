package com.app.planm.user.service;

import java.util.List;

import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

public interface UserService {

	public List<UserVO> getUserList(String cmpCode) throws Exception;
	
	public UserVO getUserInfo(UserDTO userDTO) throws Exception;
	
	public void saveUser(UserDTO userDTO) throws Exception;
	
}
