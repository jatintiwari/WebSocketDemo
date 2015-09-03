package com.websocket.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.dao.UserDao;
import com.websocket.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	
	@Transactional
	@Override
	public Collection<User> getUsersList() {
		return userDao.getUsersList();
	}


	@Transactional
	@Override
	public User getUserByUserName(String currentUsername) {
		return userDao.findUserByUsername(currentUsername);
	}

}
