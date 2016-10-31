package com.jamp.io.utils.servicebeans;

import java.io.Serializable;

import com.jamp.io.enums.LoginEventType;

/**
 * Event object that is used to sent information about user login activity by JMS
 * Contains of information of login event type and user name
 */
public class UserLoginEvent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public final LoginEventType eventType;
	public final String user;
	
	public UserLoginEvent(LoginEventType eventType, String user) {
		super();
		this.eventType = eventType;
		this.user = user;
	}
	
	@Override
	public String toString() {
		return this.eventType + this.user;
	}
}
