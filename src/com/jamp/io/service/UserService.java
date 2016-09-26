package com.jamp.io.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamp.io.model.pojo.User;

@Service
public interface UserService {
	
	@Transactional
	public void saveUser(User user);

	public List<User> getUserList();
	
	public User getUser(long id);

    @Transactional
	public void deleteUser(long id);

	public User getUser(String name);
}
