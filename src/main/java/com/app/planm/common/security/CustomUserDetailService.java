package com.app.planm.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.planm.user.dao.UserDao;

@Service
public class CustomUserDetailService implements UserDetailsService {

	private final UserDao userDao;
	
	public CustomUserDetailService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		CustomUserDetails user = userDao.loadUser(username);
		
		if (user == null) {
			throw new UsernameNotFoundException(username + " is not found");
		}		
				
		return user;
	}

}
