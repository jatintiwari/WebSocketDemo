package com.websocket.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.websocket.dao.UserDao;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	
	@Autowired
	UserDao userDao;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		com.websocket.model.User user = userDao.findUserByUsername(username);
		return buildUserForAuthentication(user);
	}

	private User buildUserForAuthentication(com.websocket.model.User user) {
		return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, buildUserAuthority(user.getRole()));
	}
	
	private List<GrantedAuthority> buildUserAuthority(String userRole) {
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>();
		Result.add(new SimpleGrantedAuthority(userRole));
		return Result;
	}

}
