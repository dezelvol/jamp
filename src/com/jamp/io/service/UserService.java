package com.jamp.io.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamp.io.annotations.ProxyThis;
import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.model.pojo.Participant;
import com.jamp.io.model.pojo.User;

@Service
public interface UserService {

	@Transactional
	public void addUser(User user);

	@ProxyThis
	public List<User> getUserList();

	@ProxyThis
	public User getUser(long id);

    @Transactional
	public void deleteUser(long id);

	@ProxyThis
	public User getUser(String name);

	
	@Transactional
	public void addMentor(Mentor user);

	@ProxyThis
	public List<Mentor> getMentorList();

	@ProxyThis
	public Mentor getMentor(long id);

    @Transactional
	public void deleteMentor(long id);

	@ProxyThis
	public Mentor getMentor(String name);

	
	@Transactional
	public void addParticipant(Participant user);

	@ProxyThis
	public List<Participant> getParticipantList();

	@ProxyThis
	public Participant getParticipant(long id);

    @Transactional
	public void deleteParticipant(long id);

	@ProxyThis
	public Participant getParticipant(String name);
	
}
