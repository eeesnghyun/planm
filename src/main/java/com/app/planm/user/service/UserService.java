package com.app.planm.user.service;

import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

public interface UserService {

	public UserVO getUserInfo(UserDTO userDTO) throws Exception;
	
	public void saveUser(UserDTO userDTO) throws Exception;
	
}
