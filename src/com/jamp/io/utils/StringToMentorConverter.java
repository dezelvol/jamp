package com.jamp.io.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.jamp.io.model.pojo.Mentor;
import com.jamp.io.service.UserService;

public class StringToMentorConverter implements Converter<String, Mentor> {
	
	@Autowired
	UserService service;


	@Override
	public Mentor convert(String idString) {
		long id = 0;
		try {  
			id = Long.parseLong(idString);
		} catch(Throwable t) {}
		return service.getMentor(id);
	}
}
