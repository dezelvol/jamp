package com.jamp.io.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.jamp.io.model.pojo.User;

public class UserDaoJPA<T extends User> implements UserDao<T> {
	final Class<T> type;
	
    public UserDaoJPA(Class<T> type) {
		super();
		this.type = type;
	}

	@PersistenceContext
	protected EntityManager entityManager;

	public T addUser(T user) {
		entityManager.persist(user);
		entityManager.flush();
		return user;
	}
	
	public T updateUser(T user) {
		T ret = entityManager.merge(user);
		entityManager.flush();
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<T> getUserList() {
		return entityManager.createQuery("from " + type.getSimpleName() + " u").getResultList();
	}

	public T getUser(long id) {
		return (T) entityManager.find(type, id);
	}
	
	@SuppressWarnings("unchecked")
	public T getUser(String name) {
		Query query = entityManager.createQuery("from " + type.getSimpleName() + " u where name=?");
		query.setParameter(1, name);
		List<T> users = query.getResultList();
		return users.size() > 0 ? users.get(0) : null;
	}

	public void deleteUser(long id) {
		User us = entityManager.find(type, id);
		if(us.getId() != 1)entityManager.remove(us);
	}
}
