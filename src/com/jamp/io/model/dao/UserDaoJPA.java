package com.jamp.io.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jamp.io.model.pojo.User;

public class UserDaoJPA implements UserDao {
	
    @PersistenceContext
	protected EntityManager entityManager;
	
	public void saveUser(User user) {
		entityManager.persist(user);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		return entityManager.createQuery("from User u").getResultList();
	}

	public User getUser(long id) {
		return entityManager.find(User.class, id);
	}
	
	public User getUser(String name) {
		Query query = entityManager.createQuery("from User u where name=?");
		query.setParameter(1, name);
		List<User> users = query.getResultList();
		return users.size() > 0 ? users.get(0) : null;
	}

	public void deleteUser(long id) {
		User us = entityManager.find(User.class, id);
		if(us.getId() != 1)entityManager.remove(us);
	}
}
