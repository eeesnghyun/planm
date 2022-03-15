package com.app.planm.common.security;

import java.security.NoSuchAlgorithmException;

import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.CredentialExpiredException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.app.planm.common.util.SecureAlgorithm;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final CustomUserDetailService customUserDetailService;
	
	public CustomAuthenticationProvider(CustomUserDetailService customUserDetailService) {
		this.customUserDetailService = customUserDetailService;
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();		
		String password = authentication.getCredentials().toString();
				
		CustomUserDetails user = null;		
				
		try {						
			user = (CustomUserDetails) customUserDetailService.loadUserByUsername(username);

			String salt = user.getSalt();							
			String encodePassword = SecureAlgorithm.encryptSha256(password, salt);
	       	 		        
			boolean isValid = encodePassword.equals(user.getPassword()) ? true : false;
						
			/**
			 * 예외 처리
			 * 계정 사용, 만료, 잠금여부는 true로 고정 
			 */
			if (!isValid) 
				throw new BadCredentialsException("Password is invalid");			
			
			//계정 사용가능 여부
			if (!user.isEnabled())
				throw new LockedException("Can't use account");
				
			//계정 만료 여부
			if (!user.isAccountNonExpired())
				throw new CredentialExpiredException("Account is expired");
			
			//계정 비밀번호 만료 여부
			if (!user.isCredentialsNonExpired())
				throw new CredentialExpiredException("Credentials is expired");
			
			//계정 Lock 여부
			if (!user.isAccountNonLocked())
				throw new AccountLockedException("Account is locked");			 	          	           
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		} catch (CredentialExpiredException e) {
			e.printStackTrace();
		} catch (AccountLockedException e) {
			e.printStackTrace();
		} 
		
		//인증이 완료 후 객체 리턴
		return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}
