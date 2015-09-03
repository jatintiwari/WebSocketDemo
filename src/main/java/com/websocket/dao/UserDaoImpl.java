package com.websocket.dao;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websocket.model.User;


@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User findUserByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.getNamedQuery("User.FindByUsername");
		query.setString("username", username);
		return (User)query.uniqueResult();
	}

	@Override
	public Collection<User> getUsersList() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User");
		return query.list();
	}

}
