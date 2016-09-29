package com.jamp.io.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jamp.io.model.dao.UserDao;
import com.jamp.io.model.pojo.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao<User> userDao;
		
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
