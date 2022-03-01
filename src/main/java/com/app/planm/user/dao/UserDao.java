package com.app.planm.user.dao;

import java.util.List;

import com.app.planm.user.vo.UserDTO;
import com.app.planm.user.vo.UserVO;

public interface UserDao {

	public List<UserVO> getUserList(String cmpCode) throws Exception;
	
	public UserVO getUserInfo(UserDTO userDTO) throws Exception;
	
	public void saveUser(UserDTO userDTO) throws Exception;
	
	public void updateUser(UserDTO userDTO) throws Exception;
	
	public List<UserVO> getDeptUser(UserDTO userDTO) throws Exception;
	
	public List<UserVO> getPartUser(UserDTO userDTO) throws Exception;
	
}
