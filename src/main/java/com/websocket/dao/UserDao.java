package com.websocket.dao;

import java.util.Collection;

import com.websocket.model.User;

public interface UserDao {

	User findUserByUsername(String username);

	Collection<User> getUsersList();
}
