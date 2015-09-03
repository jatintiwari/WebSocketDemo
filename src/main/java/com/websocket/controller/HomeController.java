package com.websocket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.log.Log;
import com.websocket.model.User;
import com.websocket.service.UserService;
import com.websocket.util.LoginUtil;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(request.isRequestedSessionIdValid()){
			Log.info("Logout!!");
			session.invalidate();
		}
		
		return "login.jsp";
	}
	
	
	@RequestMapping(value="/logout")
	public void logout(HttpServletRequest request) {
	}
	
	
	
	@RequestMapping(value="settings",method=RequestMethod.GET)
	public @ResponseBody String settings() throws Exception{
		JSONObject jsonObject = new JSONObject();
		User user = userService.getUserByUserName(LoginUtil.getCurrentUsername());
		if(user.equals(null)){
			return jsonObject.put("success", "false").toString();
		}
		try{
			jsonObject.put("username", user.getUsername());
			jsonObject.put("firstName", user.getFirstname());
			jsonObject.put("lastName", user.getLastname());
			jsonObject.put("userType", user.getUsername());
			jsonObject.put("id", user.getId());
			return jsonObject.toString();
		}catch(Exception e){
			e.printStackTrace();
			return jsonObject.put("success", "false").toString();
		}
	}
}
