package com.jamp.io.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jamp.io.model.dao.UserDao;
import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.model.pojo.Participant;
import com.jamp.io.model.pojo.User;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao<User> userDao;

	@Autowired
	private UserDao<Mentor> mentorDao;
	
	@Autowired
	private UserDao<Participant> participantDao;

	@Override
	public void addUser(User user) {
    	userDao.addUser(user);
	}

	@Override
	public List<User> getUserList() {
		List<User> userList = userDao.getUserList();
		return userList;
	}

	@Override
	public User getUser(long id) {
		return userDao.getUser(id);
	}

	@Override
	public void deleteUser(long id) {
    	userDao.deleteUser(id);
	}

	@Override
	public User getUser(String name) {
		return userDao.getUser(name);
	}


	@Override
	public void addMentor(Mentor user) {
		mentorDao.addUser(user);
	}

	@Override
	public List<Mentor> getMentorList() {
		return mentorDao.getUserList();
	}

	@Override
	public Mentor getMentor(long id) {
		return mentorDao.getUser(id);
	}

	@Override
	public void deleteMentor(long id) {
		mentorDao.deleteUser(id);
	}

	@Override
	public Mentor getMentor(String name) {
		return mentorDao.getUser(name);
	}
	


	@Override
	public void addParticipant(Participant user) {
		participantDao.addUser(user);
	}

	@Override
	public List<Participant> getParticipantList() {
		return participantDao.getUserList();
	}

	@Override
	public Participant getParticipant(long id) {
		return participantDao.getUser(id);
	}

	@Override
	public void deleteParticipant(long id) {
		mentorDao.deleteUser(id);
	}

	@Override
	public Participant getParticipant(String name) {
		return participantDao.getUser(name);
	}
	
	
}
