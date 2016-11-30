package com.jamp.io.data;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.internal.Lists;

public class UsersService {
	List<User> users = new ArrayList<>();
	
	public UsersService() {
		users.add(new User("Vasya"));
		users.add(new User("Kostya"));
		users.add(new User("Kolya"));
		users.add(new User("Zhenya"));
	}
	
	public List<User> getAll() {
		return users;
	}
	
	public User getUser(String name) {
		for(User u : users) {
			if(u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	public void addUser(String name) {
		users.add(new User(name));
	}
}
