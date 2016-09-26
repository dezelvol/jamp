package com.jamp.io.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jamp.io.model.pojo.User;

@Repository
public interface UserDao {

	public void saveUser(User user);

	public List<User> getUserList();

	public User getUser(long id);
	
	public User getUser(String name);

	public void deleteUser(long id);
}
