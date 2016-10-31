package com.jamp.io.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.service.UserService;

public class StringToMentorConverter implements Converter<String, Mentor> {
	
	@Autowired
	UserService service;

	/**
	 * Converts mentorId to mentor
	 * Is required to use auto binding and validation of Participant
	 * @see Participant
	 * @see ParticipantValidator
	 */
	@Override
	public Mentor convert(String idString) {
		long id = 0;
		try {  
			id = Long.parseLong(idString);
		} catch(Throwable t) {}
		return service.getMentor(id);
	}
}
