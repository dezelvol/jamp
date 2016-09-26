package com.jamp.io.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import com.jamp.io.model.dao.UserDao;
import com.jamp.io.model.pojo.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
		
	public void saveUser(User user) {
    	userDao.saveUser(user);
	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}
	
	public User getUser(long id) {
		return userDao.getUser(id);
	}

	public void deleteUser(long id) {
    	userDao.deleteUser(id);
	}

	@Override
	public User getUser(String name) {
		return userDao.getUser(name);
	}
}
