package com.app.planm.common.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.planm.common.security.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private Log logger = LogFactory.getLog(this.getClass());

	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	public SecurityConfig(CustomAuthenticationProvider customAuthenticationProvider) {
		this.customAuthenticationProvider = customAuthenticationProvider;
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/css/**",
        		"/fonts/**",				
        		"/js/**",
        		"/images/**",
        		"/plugins/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    			.antMatchers("/login").permitAll()
    			
    			//인사관리는 MANAGER 권한이 있는 경우
    			.antMatchers("/user/**").hasRole("MANAGER")
    			
    			//관리자메뉴는 ADMIN 권한이 있는 경우
    			.antMatchers("/admin/**").hasRole("ADMIN")
		        .antMatchers("/**").authenticated();
		
    	//로그인 설정
		http.formLogin()
		        .loginPage("/login")
		        .loginProcessingUrl("/authenticate")
		        .failureUrl("/login?error=true")
		        .defaultSuccessUrl("/")		        
		        .permitAll();
		
		http.logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		        .logoutSuccessUrl("/login")
		        .invalidateHttpSession(true);
		
		http.exceptionHandling()
		        .accessDeniedPage("/denied");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	//사용자 인증 방법 정의(AuthenticationProvider 구현체 - CustomAuthenticationProvider)
    	auth.authenticationProvider(customAuthenticationProvider);
    }	
}
