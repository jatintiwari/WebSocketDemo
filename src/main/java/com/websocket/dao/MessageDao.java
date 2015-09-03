package com.websocket.dao;

import java.util.Collection;

import com.websocket.model.WebMessage;

public interface MessageDao {

	Collection<WebMessage> getConversation(String currentUsername,String otherUser);
	Long saveMessage(WebMessage message);
	Long getUnReadMessageCount(String fromUser, String toUser);
	void markeMessagesAsRead(String fromUser, String toUser);
	Long getUnReadMessageCountForUser(String username);

}
