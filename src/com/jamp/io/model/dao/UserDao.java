package com.jamp.io.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jamp.io.model.pojo.User;

@Repository
public interface UserDao<T extends User> {

	public T addUser(T user);
	
	public T updateUser(T user);

	public List<T> getUserList();

	public T getUser(long id);
	
	public T getUser(String name);

	public void deleteUser(long id);
}
