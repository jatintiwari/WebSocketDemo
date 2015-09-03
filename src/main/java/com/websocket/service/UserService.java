package com.websocket.service;

import java.util.Collection;

import com.websocket.model.User;

public interface UserService {

	Collection<User> getUsersList();

	User getUserByUserName(String currentUsername);

}
