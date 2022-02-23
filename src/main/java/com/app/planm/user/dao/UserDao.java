package com.app.planm.user.dao;

import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

public interface UserDao {

	public UserVO getUserInfo(UserDTO userDTO) throws Exception;
	
	public void saveUser(UserDTO userDTO) throws Exception;
	
}
