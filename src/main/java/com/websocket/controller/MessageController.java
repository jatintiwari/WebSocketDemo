package com.websocket.controller;

import java.security.Principal;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.log.Log;
import com.websocket.model.HelloMessage;
import com.websocket.model.User;
import com.websocket.model.WebMessage;
import com.websocket.service.MessageService;
import com.websocket.service.UserService;
import com.websocket.util.LoginUtil;
import com.websocket.util.TimeConversion;

@Controller
public class MessageController {


	@Autowired
	SimpMessagingTemplate template;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MessageService messageService;
	
    @MessageMapping("/message")
    public synchronized void greeting(Message<Object> message1,WebMessage message, Principal principal) throws Exception {
    	try{
    		if(principal.equals(null)){
    			Log.info("User unknonwn!!");
    			return;
    		}
    		String fromUser = principal.getName();
    		Log.info("Message form "+fromUser+" to user "+message.getToUser()+" is "+message.getMessage());
    		message.setFromUser(fromUser);
    		message.setTime(System.currentTimeMillis());
    		
    		//prepare to send message
    		JSONObject jsonObject = new JSONObject();
    		jsonObject.put("fromUser", fromUser);
    		jsonObject.put("toUser", message.getToUser());
    		jsonObject.put("message", message.getMessage());
    		jsonObject.put("time", TimeConversion.getCurrentTime());
    		jsonObject.put("date", TimeConversion.getCurrentDate());
    		//saving message
    		Long id = messageService.saveMessage(message);
    		Log.info("Message saved with id  :: "+id);
    		jsonObject.put("id", id);
    		this.template.convertAndSendToUser(message.getToUser(), "/websocket/message", jsonObject.toString());
    		Log.info("Message sent!");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    //@Scheduled(fixedDelay=1000)
    public void getPortfolios() {
    	this.template.convertAndSendToUser("abc", "/websocket/messages", "123");
    }
    
    @MessageMapping(value="/messages/read")
    public void markAsRead(WebMessage message,Principal principal){
    	String currentUsername = principal.getName();
    	User user = userService.getUserByUserName(currentUsername);
    	if(!user.equals(null)){
    		Log.info("from  :: "+message.getFromUser()+"  to ::"+message.getToUser());
    		Long unReadMessages = messageService.getUnReadMessageCount(message.getFromUser(),message.getToUser());
    		if(unReadMessages>0){
    			try{
    				JSONObject jsonObject = new JSONObject();
    				jsonObject.put("markRead", "TRUE");
    				jsonObject.put("fromUser", message.getFromUser());
    				jsonObject.put("toUser", message.getToUser());
    				messageService.markeMessagesAsRead(message.getFromUser(),message.getToUser());
    				this.template.convertAndSendToUser(message.getFromUser(), "/websocket/message", jsonObject.toString());
    				Log.info("Messages marked as read");
    			}catch(Exception e){
    				e.printStackTrace();
    				Log.info("Messages not marked as read");
    			}
    		}
    	}
    }
    
    
  //sends the collection of messages :: the conversation between the two users.
  	@RequestMapping(value="/messages", method=RequestMethod.GET)
  	public @ResponseBody String getMessagesList(@RequestParam(value="otherUser",required=false)String otherUser) throws Exception{
  		String currentUsername = LoginUtil.getCurrentUsername();
  		User user = userService.getUserByUserName(currentUsername);
  		if(user==null){
  			Log.info("user is not logged in!!");
  			return "{\"success\":\"false\", \"message\":\"Cannot access messages list.\"}";
  		}else if(otherUser == "" || otherUser.equals(null)){
  			Log.info("user is not logged in!!");
  			return "{\"success\":\"false\", \"message\":\"Unknown user\"}";
  		}

  		JSONObject jsonObject;
  		JSONArray jsonArray = new JSONArray();
  		try{
  			Collection<WebMessage> converstation = messageService.getConversation(currentUsername,otherUser);
  			if(converstation.size()!=0){
  				for(WebMessage message : converstation){
  					jsonObject =  new JSONObject();
  					jsonObject.put("id", message.getId());
  					jsonObject.put("fromUser", message.getFromUser());
  					jsonObject.put("toUser", message.getToUser());
  					jsonObject.put("message", message.getMessage());
  					jsonObject.put("time", TimeConversion.getCurrentTime());
  		    		jsonObject.put("date", TimeConversion.getCurrentDate());
  		    		jsonObject.put("messageRead", message.isMessageRead());
  					jsonArray.put(jsonObject);
  				}
  				Log.info("Successfully retrieved the conversation between "+currentUsername+" and "+otherUser+" :: "+jsonArray.toString());
  				return jsonArray.toString();
  			}
  			Log.info("No conversation found.");
  			jsonObject =  new JSONObject();
  			jsonObject.put("success","false");
  			jsonObject.put("fromUser",otherUser);
  			jsonObject.put("toUser",currentUsername );
  			jsonObject.put("message","Start your conversation.");
  			return jsonObject.toString();
  			
  		}catch(Exception e){
  			e.printStackTrace();
  			Log.error("Exception retrieving conversation",e);
  			return "{\"success\":\"Error\", \"message\":\"Sorry!! We are looking into this problem.\"}";
  		}
  		

  	}

}
