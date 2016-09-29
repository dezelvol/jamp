package com.jamp.io.model.dao;

import java.util.Date;
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
	
	public T saveUser(T user) {
		T ret = entityManager.merge(user);
		entityManager.flush();
		return ret;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		return entityManager.createQuery("from " + type.getSimpleName() + " u").getResultList();
	}

	public T getUser(long id) {
		return (T) entityManager.find(type, id);
	}
	
	public T getUser(String name) {
		Query query = entityManager.createQuery("from " + type.getSimpleName() + " u where name=?");
		query.setParameter(1, name);
		List<T> users = query.getResultList();
		return users.size() > 0 ? users.get(0) : null;
	}

	public void deleteUser(long id) {
		User us = entityManager.find(User.class, id);
		if(us.getId() != 1)entityManager.remove(us);
	}

	@Override
	public T setCreatedBy(T user, User author) {
		user = this.getUser(user.getId());
		user.setCreatedBy(author);
		return entityManager.merge(user);
	}

	@Override
	public T setLastUpdatedBy(T user, User author) {
		user = this.getUser(user.getId());
		user.setLastUpdatedBy(author);
		return entityManager.merge(user);
	}

	@Override
	public T setUpdateDate(T user, Date date) {
		user = this.getUser(user.getId());
		user.setLastUpdate(date);
		return entityManager.merge(user);
	}

	@Override
	public T setCreateDate(T user, Date date) {
		user = this.getUser(user.getId());
		user.setCreated(date);
		return entityManager.merge(user);
	}

	@Override
	public User getCreatedBy(T user) {
		return this.getUser(user.getId()).getCreatedBy();
	}

	@Override
	public User getLastUpdatedBy(T user) {
		return this.getUser(user.getId()).getLastUpdatedBy();
	}

	@Override
	public Date getUpdateDate(T user) {
		return this.getUser(user.getId()).getLastUpdate();
	}

	@Override
	public Date getCreateDate(T user) {
		return this.getUser(user.getId()).getCreated();
	}
}
