package com.websocket.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websocket.dao.MessageDao;
import com.websocket.model.WebMessage;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	MessageDao messageDao;

	@Transactional
	@Override
	public Collection<WebMessage> getConversation(String currentUsername,
			String otherUser) {
		return messageDao.getConversation(currentUsername,otherUser);
	}

	@Transactional
	@Override
	public Long saveMessage(WebMessage message) {
		return messageDao.saveMessage(message);
	}

	@Transactional
	@Override
	public Long getUnReadMessageCount(String fromUser, String toUser) {
		return messageDao.getUnReadMessageCount(fromUser, toUser);
	}

	@Transactional
	@Override
	public void markeMessagesAsRead(String fromUser, String toUser) {
		messageDao.markeMessagesAsRead(fromUser, toUser);
	}
	@Transactional
	@Override
	public Long getUnReadMessageCountForUser(String username) {
		return messageDao.getUnReadMessageCountForUser(username);
	}

}
