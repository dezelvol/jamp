package com.jamp.io.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserDao {
    @PersistenceContext
	protected EntityManager entityManager;
	
    @Transactional
	public void saveUser(User user) {
		entityManager.persist(user);
		entityManager.flush();
	}

	public List<User> getUserList() {
		return entityManager.createQuery("from User u").getResultList();
	}
}
