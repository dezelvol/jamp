package com.jamp.io.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamp.io.model.dao.UserDao;
import com.jamp.io.model.pojo.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
    @Transactional
	public void saveUser(User user) {
    	userDao.saveUser(user);
	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	public User getUser(long id) {
		return userDao.getUser(id);
	}

    @Transactional
	public void deleteUser(long id) {
    	userDao.deleteUser(id);
	}
}
