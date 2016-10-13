package com.jamp.io.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jamp.io.model.pojo.Participant;

@Component
public class ParticipantValidator implements Validator {

    @Autowired
    @Qualifier("basicValidator")
    private Validator basicValidator;

	@Override
	public boolean supports(Class<?> clazz) {
		return Participant.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
        basicValidator.validate(target, errors);

        Participant user = (Participant) target;
        if(user.getMentor() == null) {
        	 errors.rejectValue("mentor", "", "Value should be an ID of existing Mentor.");
        }
	}
	
}
