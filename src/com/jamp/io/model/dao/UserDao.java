package com.jamp.io.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.jamp.io.model.pojo.User;

@Repository
public interface UserDao<T extends User> {

	public T saveUser(T user);

	public List<User> getUserList();

	public T getUser(long id);
	
	public T getUser(String name);

	public void deleteUser(long id);

	public T setCreatedBy(T user, User author);

	public T setLastUpdatedBy(T user, User author);

	public T setUpdateDate(T user, Date date);
	
	public T setCreateDate(T user, Date date);
	
	public User getCreatedBy(T user);

	public User getLastUpdatedBy(T user);

	public Date getUpdateDate(T user);
	
	public Date getCreateDate(T user);
}
