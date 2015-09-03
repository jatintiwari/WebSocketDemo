package com.websocket.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websocket.model.WebMessage;

@Repository
public class MessageDaoImpl implements MessageDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Collection<WebMessage> getConversation(String currentUsername,
			String otherUser) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from WebMessage where (fromUser =:fromUser or toUser=:fromUser)and(fromUser =:toUser or toUser=:toUser) ");
		query.setString("fromUser", currentUsername);
		query.setString("toUser", otherUser);
		return query.list();
	}

	@Override
	public Long saveMessage(WebMessage message) {
		Session session  = sessionFactory.getCurrentSession();
		return (Long) session.save(message);
	}

	@Override
	public Long getUnReadMessageCount(String fromUser, String toUser) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from WebMessage where (fromUser =:fromUser and toUser=:toUser) and messageRead=0");
		query.setString("fromUser", fromUser);
		query.setString("toUser", toUser);
		return (Long)query.uniqueResult();
	}

	@Override
	public void markeMessagesAsRead(String fromUser, String toUser) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery("Update WebMessage set messageRead=1 where (fromUser =:fromUser and toUser=:toUser) and messageRead=0");
		query.setString("fromUser", fromUser);
		query.setString("toUser", toUser);
		query.executeUpdate();
	}

	@Override
	public Long getUnReadMessageCountForUser(String username) {
		Session session  = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from WebMessage where toUser=:toUser and messageRead=0");
		query.setString("toUser", username);
		return (Long)query.uniqueResult();
	}

}
