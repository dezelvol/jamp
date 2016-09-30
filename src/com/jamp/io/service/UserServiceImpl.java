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

	public void addUser(User user) {
    	userDao.addUser(user);
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

	
	public void addMentor(Mentor user) {
		mentorDao.addUser(user);
	}

	public List<Mentor> getMentorList() {
		return mentorDao.getUserList();
	}
	
	public Mentor getMentor(long id) {
		return mentorDao.getUser(id);
	}

	public void deleteMentor(long id) {
		mentorDao.deleteUser(id);
	}

	@Override
	public Mentor getMentor(String name) {
		return mentorDao.getUser(name);
	}
	

	
	public void addParticipant(Participant user) {
		participantDao.addUser(user);
	}

	public List<Participant> getParticipantList() {
		return participantDao.getUserList();
	}
	
	public Participant getParticipant(long id) {
		return participantDao.getUser(id);
	}

	public void deleteParticipant(long id) {
		mentorDao.deleteUser(id);
	}

	@Override
	public Participant getParticipant(String name) {
		return participantDao.getUser(name);
	}
	
	
}
