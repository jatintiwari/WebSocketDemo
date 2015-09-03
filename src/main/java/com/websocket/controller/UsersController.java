package com.websocket.controller;

import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.model.User;
import com.websocket.service.MessageService;
import com.websocket.service.UserService;
import com.websocket.util.LoginUtil;


@Controller
public class UsersController {

	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
	@RequestMapping(value="userList", method=RequestMethod.GET)
	public @ResponseBody String usersList(){
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject;
		try{
			Collection<User> usersList = userService.getUsersList();
			for(User user: usersList){
				if(!user.getUsername().equals(LoginUtil.getCurrentUsername())){
					jsonObject = new JSONObject();
					jsonObject.put("username", user.getUsername());
					jsonObject.put("unReadMessages", messageService.getUnReadMessageCount(user.getUsername(), LoginUtil.getCurrentUsername()));
					jsonObject.put("id", user.getId());
					jsonArray.put(jsonObject);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return jsonArray.toString();
	}
}
