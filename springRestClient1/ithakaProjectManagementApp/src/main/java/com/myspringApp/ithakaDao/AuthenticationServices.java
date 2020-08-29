package com.myspringApp.ithakaDao;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.myspringApp.ithakaModel.Users;

@Service
public class AuthenticationServices implements UserDetailsService{
	
	@Autowired
	private userDao userdao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users userInfo =  userdao.getUserInfo(username);
		
		GrantedAuthority authority = new SimpleGrantedAuthority(userInfo.getUserRole());
		
		/*
		 * UserDetails userdetails = (UserDetails) new User(userInfo.getUserName(),
		 * userInfo.getPassword(), Arrays.asList(authority));
		 */
		UserDetails userdetails = (UserDetails) new User(userInfo.getUserId(),
				  userInfo.getPassword(), Arrays.asList(authority));
		  
		  return userdetails;
		 
		/*
		 * return new Users(userInfo.getUserName(), userInfo.getPassword(), true, true,
		 * true, true, userInfo.getAuthorities(), userInfo.getUserId());
		 */
	}

}
