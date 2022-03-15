package com.app.planm.common.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 3090978848430641441L;
	private String cmpCode;
	private String userCode;
	private String password;
	private String salt;
	private String userAuth;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
		
		/**
		 * 권한을 확인하는 ExpressionUrlAuthorizationConfigurer 클래스의 hasRole메소드에 사용되는 rolePrefix는 "ROLE_"로 정해져있다.
		 * 경우에 따라 커스텀이 가능하지만 "ROLE_" prefix 규칙을 따라 권한을 등록한다.
		 */
		auth.add(new SimpleGrantedAuthority(userAuth));
		return auth;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userCode;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
