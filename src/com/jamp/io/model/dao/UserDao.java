package com.jamp.io.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jamp.io.model.pojo.User;

@Repository
public class UserDao {
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

	public void deleteUser(long id) {
		entityManager.remove(entityManager.find(User.class, id));
	}
}
